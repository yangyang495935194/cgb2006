package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@TableName("user")//MP   表和对象关联
public class User implements Serializable {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
}
