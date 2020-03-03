package com.sonic.mp.beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * User
 *
 * @author Sonic
 * @since 2020/3/3
 */
@Data
public class User {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT) //插入的时候自动填充
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;
}
