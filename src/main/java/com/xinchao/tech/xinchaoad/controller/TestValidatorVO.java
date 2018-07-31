package com.xinchao.tech.xinchaoad.controller;

import com.xinchao.tech.xinchaoad.annotation.Phone;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author: luhanyu
 * @Date: 2018/7/11 15:24
 * @Description:
 */
public class TestValidatorVO {
   @Length(min = 11,max =11 ,message = "这不是一个有效电话号码")
    @NotBlank
    @Setter@Getter
    private String phone;

}
