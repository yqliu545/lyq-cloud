package com.ruoyi.common.core.domain.model;


import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录对象
 *
 * @author Lion Li
 */

@Data
public class LoginBody implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @NotBlank(message = "{auth.clientid.not.blank}")
    private String clientId;

    /**
     * 授权类型
     */
    @NotBlank(message = "{auth.grant.type.not.blank}")
    private String grantType;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
