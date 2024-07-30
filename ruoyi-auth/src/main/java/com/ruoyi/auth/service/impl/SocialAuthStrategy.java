package com.ruoyi.auth.service.impl;
import com.ruoyi.auth.domain.vo.LoginVo;
import com.ruoyi.auth.form.SocialLoginBody;
import com.ruoyi.auth.service.IAuthStrategy;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.SysClient;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.social.config.properties.SocialProperties;
import com.ruoyi.common.social.utils.SocialUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

/**
 * 第三方授权策略
 *
 * @author thiszhc is 三三
 */
@Slf4j
@Service("social" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class SocialAuthStrategy implements IAuthStrategy {

    private final SocialProperties socialProperties;
//    private final ISysSocialService sysSocialService;
//    private final SysUserMapper userMapper;
    private final SysLoginService loginService;

    /**
     * 登录-第三方授权登录
     *
     * @param body     登录信息
     * @param client   客户端信息
     */
    @Override
    public LoginVo login(String body, SysClient client) {
        SocialLoginBody loginBody = JsonUtils.parseObject(body, SocialLoginBody.class);
//        ValidatorUtils.validate(loginBody);
        AuthResponse<AuthUser> response = SocialUtils.loginAuth(
                loginBody.getSource(), loginBody.getSocialCode(),
                loginBody.getSocialState(), socialProperties);
        if (!response.ok()) {
            throw new ServiceException(response.getMsg());
        }
        AuthUser authUserData = response.getData();

        LoginVo loginVo = new LoginVo();
//        loginVo.setAccessToken(StpUtil.getTokenValue());
//        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

//    private SysUserVo loadUser(String tenantId, Long userId) {
//        return TenantHelper.dynamic(tenantId, () -> {
//            SysUserVo user = userMapper.selectVoById(userId);
//            if (ObjectUtil.isNull(user)) {
//                log.info("登录用户：{} 不存在.", "");
//                throw new UserException("user.not.exists", "");
//            } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
//                log.info("登录用户：{} 已被停用.", "");
//                throw new UserException("user.blocked", "");
//            }
//            return user;
//        });
//    }

}
