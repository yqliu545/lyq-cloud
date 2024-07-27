package com.ruoyi.common.social.utils;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.social.config.properties.SocialLoginConfigProperties;
import com.ruoyi.common.social.config.properties.SocialProperties;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.*;

/**
 * 认证授权工具类
 *
 * @author thiszhc
 */
public class SocialUtils  {

    private static final AuthRedisStateCache STATE_CACHE = SpringUtils.getBean(AuthRedisStateCache.class);

    @SuppressWarnings("unchecked")
    public static AuthResponse<AuthUser> loginAuth(String source, String code, String state, SocialProperties socialProperties) throws AuthException {
        AuthRequest authRequest = getAuthRequest(source, socialProperties);
        AuthCallback callback = new AuthCallback();
        callback.setCode(code);
        callback.setState(state);
        return authRequest.login(callback);
    }

    public static AuthRequest getAuthRequest(String source, SocialProperties socialProperties) throws AuthException {
        SocialLoginConfigProperties obj = socialProperties.getType().get(source);
         if (ObjectUtil.isNull(obj)) {
            throw new AuthException("不支持的第三方登录类型");
        }
        AuthConfig.AuthConfigBuilder builder = AuthConfig.builder()
            .clientId(obj.getClientId())
            .clientSecret(obj.getClientSecret())
            .redirectUri(obj.getRedirectUri())
            .scopes(obj.getScopes());
        AuthRequest authRequest = null;
         switch (source.toLowerCase()) {
            case "dingtalk" :
                authRequest=new AuthDingTalkRequest(builder.build(), STATE_CACHE);
                break;
            case "baidu" :
                authRequest=new AuthBaiduRequest(builder.build(), STATE_CACHE);
                break;
            case "github" :
                authRequest=new AuthGithubRequest(builder.build(), STATE_CACHE);
                break;
            case "gitee" :
                authRequest=new AuthGiteeRequest(builder.build(), STATE_CACHE);
                break;
            case "weibo" :
                authRequest=new AuthWeiboRequest(builder.build(), STATE_CACHE);
                break;
            case "coding" :
                authRequest=new AuthCodingRequest(builder.build(), STATE_CACHE);
                break;
            case "oschina" :
                authRequest=new AuthOschinaRequest(builder.build(), STATE_CACHE);
                break;
            // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
            case "alipay_wallet" :
                authRequest=new AuthAlipayRequest(builder.build(), socialProperties.getType().get("alipay_wallet").getAlipayPublicKey(), STATE_CACHE);
                break;
            case "qq" :
                new AuthQqRequest(builder.build(), STATE_CACHE);
                break;
            case "wechat_open" :
                authRequest=new AuthWeChatOpenRequest(builder.build(), STATE_CACHE);
                break;
            case "taobao" :
                authRequest=new AuthTaobaoRequest(builder.build(), STATE_CACHE);
                break;
            case "douyin" :
                authRequest=new AuthDouyinRequest(builder.build(), STATE_CACHE);
                break;
            case "linkedin" :
                authRequest=new AuthLinkedinRequest(builder.build(), STATE_CACHE);
                break;
            case "microsoft" :
                authRequest=new AuthMicrosoftRequest(builder.build(), STATE_CACHE);
                break;
            case "renren" :
                authRequest=new AuthRenrenRequest(builder.build(), STATE_CACHE);
                break;
            case "stack_overflow" :
                authRequest=new AuthStackOverflowRequest(builder.stackOverflowKey("").build(), STATE_CACHE);
                break;
            case "huawei" :
                authRequest=new AuthHuaweiRequest(builder.build(), STATE_CACHE);
                break;
            case "wechat_enterprise" :
                authRequest=new AuthWeChatEnterpriseQrcodeRequest(builder.agentId("").build(), STATE_CACHE);
                break;
            case "gitlab" :
                authRequest=new AuthGitlabRequest(builder.build(), STATE_CACHE);
                break;
            case "wechat_mp" :
                authRequest=new AuthWeChatMpRequest(builder.build(), STATE_CACHE);
                break;
            case "aliyun" :
                authRequest=new AuthAliyunRequest(builder.build(), STATE_CACHE);
                break;
            default : throw new AuthException("未获取到有效的Auth配置");
        };
        return authRequest;
    }
}

