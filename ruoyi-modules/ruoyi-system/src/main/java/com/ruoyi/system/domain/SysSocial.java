package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 社会化关系对象 sys_social
 * 
 * @author lyq
 * @date 2024-08-07
 */
public class SysSocial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 租户id */
    @Excel(name = "租户id")
    private String tenantId;

    /** 平台+平台唯一id */
    @Excel(name = "平台+平台唯一id")
    private String authId;

    /** 用户来源 */
    @Excel(name = "用户来源")
    private String source;

    /** 平台编号唯一id */
    @Excel(name = "平台编号唯一id")
    private String openId;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 用户的授权令牌 */
    @Excel(name = "用户的授权令牌")
    private String accessToken;

    /** 用户的授权令牌的有效期，部分平台可能没有 */
    @Excel(name = "用户的授权令牌的有效期，部分平台可能没有")
    private Long expireIn;

    /** 刷新令牌，部分平台可能没有 */
    @Excel(name = "刷新令牌，部分平台可能没有")
    private String refreshToken;

    /** 平台的授权信息，部分平台可能没有 */
    @Excel(name = "平台的授权信息，部分平台可能没有")
    private String accessCode;

    /** 用户的 unionid */
    @Excel(name = "用户的 unionid")
    private String unionId;

    /** 授予的权限，部分平台可能没有 */
    @Excel(name = "授予的权限，部分平台可能没有")
    private String scope;

    /** 个别平台的授权信息，部分平台可能没有 */
    @Excel(name = "个别平台的授权信息，部分平台可能没有")
    private String tokenType;

    /** id token，部分平台可能没有 */
    @Excel(name = "id token，部分平台可能没有")
    private String idToken;

    /** 小米平台用户的附带属性，部分平台可能没有 */
    @Excel(name = "小米平台用户的附带属性，部分平台可能没有")
    private String macAlgorithm;

    /** 小米平台用户的附带属性，部分平台可能没有 */
    @Excel(name = "小米平台用户的附带属性，部分平台可能没有")
    private String macKey;

    /** 用户的授权code，部分平台可能没有 */
    @Excel(name = "用户的授权code，部分平台可能没有")
    private String code;

    /** Twitter平台用户的附带属性，部分平台可能没有 */
    @Excel(name = "Twitter平台用户的附带属性，部分平台可能没有")
    private String oauthToken;

    /** Twitter平台用户的附带属性，部分平台可能没有 */
    @Excel(name = "Twitter平台用户的附带属性，部分平台可能没有")
    private String oauthTokenSecret;

    /** 创建部门 */
    @Excel(name = "创建部门")
    private Long createDept;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }
    public void setAuthId(String authId) 
    {
        this.authId = authId;
    }

    public String getAuthId() 
    {
        return authId;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setAccessToken(String accessToken) 
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken() 
    {
        return accessToken;
    }
    public void setExpireIn(Long expireIn) 
    {
        this.expireIn = expireIn;
    }

    public Long getExpireIn() 
    {
        return expireIn;
    }
    public void setRefreshToken(String refreshToken) 
    {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() 
    {
        return refreshToken;
    }
    public void setAccessCode(String accessCode) 
    {
        this.accessCode = accessCode;
    }

    public String getAccessCode() 
    {
        return accessCode;
    }
    public void setUnionId(String unionId) 
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setScope(String scope) 
    {
        this.scope = scope;
    }

    public String getScope() 
    {
        return scope;
    }
    public void setTokenType(String tokenType) 
    {
        this.tokenType = tokenType;
    }

    public String getTokenType() 
    {
        return tokenType;
    }
    public void setIdToken(String idToken) 
    {
        this.idToken = idToken;
    }

    public String getIdToken() 
    {
        return idToken;
    }
    public void setMacAlgorithm(String macAlgorithm) 
    {
        this.macAlgorithm = macAlgorithm;
    }

    public String getMacAlgorithm() 
    {
        return macAlgorithm;
    }
    public void setMacKey(String macKey) 
    {
        this.macKey = macKey;
    }

    public String getMacKey() 
    {
        return macKey;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setOauthToken(String oauthToken) 
    {
        this.oauthToken = oauthToken;
    }

    public String getOauthToken() 
    {
        return oauthToken;
    }
    public void setOauthTokenSecret(String oauthTokenSecret) 
    {
        this.oauthTokenSecret = oauthTokenSecret;
    }

    public String getOauthTokenSecret() 
    {
        return oauthTokenSecret;
    }
    public void setCreateDept(Long createDept) 
    {
        this.createDept = createDept;
    }

    public Long getCreateDept() 
    {
        return createDept;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("tenantId", getTenantId())
            .append("authId", getAuthId())
            .append("source", getSource())
            .append("openId", getOpenId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("avatar", getAvatar())
            .append("accessToken", getAccessToken())
            .append("expireIn", getExpireIn())
            .append("refreshToken", getRefreshToken())
            .append("accessCode", getAccessCode())
            .append("unionId", getUnionId())
            .append("scope", getScope())
            .append("tokenType", getTokenType())
            .append("idToken", getIdToken())
            .append("macAlgorithm", getMacAlgorithm())
            .append("macKey", getMacKey())
            .append("code", getCode())
            .append("oauthToken", getOauthToken())
            .append("oauthTokenSecret", getOauthTokenSecret())
            .append("createDept", getCreateDept())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
