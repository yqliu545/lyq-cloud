package com.ruoyi.auth.form;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 三方登录对象
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SocialLoginBody extends LoginBody2 {

    /**
     * 第三方登录平台
     */
    @NotBlank(message = "{social.source.not.blank}")
    private String source;

    /**
     * 第三方登录code
     */
    @NotBlank(message = "{social.code.not.blank}")
    private String socialCode;

    /**
     * 第三方登录socialState
     */
    @NotBlank(message = "{social.state.not.blank}")
    private String socialState;

}
