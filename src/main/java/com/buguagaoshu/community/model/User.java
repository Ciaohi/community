package com.buguagaoshu.community.model;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Pu Zhiwei {@literal puzhiweipuzhiwei@foxmail.com}
 * create          2019-08-12 21:19
 * 用户类
 * 使用 JSR-303 进行数据检验
 */
@Data
public class User {
    private long id = -1;


    private String userId;


    private String userName;


    private String password;

    private String email;

    private int age;


    private String birthday;

    private String sex;
    private String school;
    private String major;
    private String creationTime;
    private String headUrl;
    private String lastTime;
    private String userTopPhotoUrl;

    /**
     * 一句话介绍
     * */
    private String simpleSelfIntroduction;

    /**
     * 自我介绍
     * */
    private String selfIntroduction;

    /**
     * 爱好
     * */
    private String likes;

    private int power;

    /**
     * 清除敏感数据
     * */
    public void clean() {
        this.password = null;
    }
}
