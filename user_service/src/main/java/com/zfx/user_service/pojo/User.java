package com.zfx.user_service.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:29
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
@Data
@Table(name = "user") //必须有
public class User implements Serializable {

    @Id //必须有
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
