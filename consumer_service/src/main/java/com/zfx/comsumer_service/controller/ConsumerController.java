package com.zfx.comsumer_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zfx.comsumer_service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:40
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "defaultFallBack")    //默认失败方法
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

/*//    @Autowired
//    private RibbonLoadBalancerClient client;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        // 根据服务id获取实例
        //List<ServiceInstance> instanceList = discoveryClient.getInstances("USER-SERVICE");
        // 从实例中取出ip和端口
        //ServiceInstance serviceInstance = instanceList.get(0);  //写死了
        // 拼接ip, 端口, 接口
        //String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;

        //负载均衡(随机,轮询[默认],hash...)
        //ServiceInstance serviceInstance = client.choose("USER-SERVICE");
        //String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;

        String url = "http://user-service/user/" + id;
        return restTemplate.getForObject(url, User.class);
    }*/

    /**
     * @HystrixCommand: 开启服务降级
     *    fallbackMethod : 失败方法(无则使用默认)
     *    commandProperties={ @HystrixProperty( name ="", value="") } : 设置属性
     *          execution.isolation.thread.timeoutInMilliseconds ==> 超时时长(默认1s)
     *          circuitBreaker.requestVolumeThreshold ==> 触发熔断的最小请求次数(默认20s)
     *          circuitBreaker.errorThresholdPercentage ==> 触发熔断的失败请求最小占比(默认50%)
     *          circuitBreaker.sleepWindowInMilliseconds ==> 熔断开发后休眠时长(默认5s)
     */
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
    })
    public String findById(@PathVariable("id") Integer id) {

        if(id % 2 == 0) {
            System.out.println("失败");
            throw new RuntimeException();   //模拟失败
        }

        String url = "http://user-service/user/" + id;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * @description: 失败方式
     * @return: 与成功方法返回类型一致 == String
     */
    public String findByIdFallBack(Integer id) {
        return "不好意思! 服务器太拥挤了! ";
    }

    /**
     * @description: 默认失败方式
     */
    public String defaultFallBack() {
        return "不好意思! 服务器太拥挤了! ";
    }
}
