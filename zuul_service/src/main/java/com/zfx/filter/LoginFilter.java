package com.zfx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.scenario.effect.FilterContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登陆过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;   //前置过滤器
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1; //在处理头之前
    }

    @Override
    public boolean shouldFilter() {
        return true;    //要进行过滤
    }

    //业务逻辑
    @Override
    public Object run() throws ZuulException {
        // 获取请求上下文(zuul整个请求流程都可以找到)
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = ctx.getRequest();
        //判断请求参数(参照zuul中的过滤器源码)
        String token = request.getParameter("access-token");
        //判断是否存在
        if(StringUtils.isBlank(token)) {
            //不存在, 未登录, 则拦截
            ctx.setSendZuulResponse(false);
            //返回403状态码
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null; //默认放行
    }
}
