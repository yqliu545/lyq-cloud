package com.ruoyi.auth.controller;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.client.utils.ValidatorUtils;
import com.ruoyi.auth.domain.vo.LoginVo;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.LoginBody2;
import com.ruoyi.auth.service.IAuthStrategy;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.auth.form.SocialLoginBody;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.social.config.properties.SocialLoginConfigProperties;
import com.ruoyi.common.social.config.properties.SocialProperties;
import com.ruoyi.common.social.utils.SocialUtils;
import com.ruoyi.system.api.model.LoginUser;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证
 *
 * @author Lion Li
 */

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private  SocialProperties socialProperties;
    @Autowired
    private  SysLoginService loginService;
    @Autowired
    private TokenService tokenService;
//    private final SysRegisterService registerService;
//    private final ISysConfigService configService;
//    private final ISysTenantService tenantService;
//    private final ISysSocialService socialUserService;
//    private final ISysClientService clientService;
//    private final ScheduledExecutorService scheduledExecutorService;


    /**
     * 登录方法
     *
     * @param body 登录信息
     * @return 结果
     */
//    @ApiEncrypt
    @PostMapping("/social/login")
    public R<LoginVo> login(@RequestBody String body) {
        LoginBody2 loginBody = JsonUtils.parseObject(body, LoginBody2.class);


        // 授权类型和客户端id
        String clientId = loginBody.getClientId();
        String grantType = loginBody.getGrantType();

        SocialLoginBody loginBody2 = JsonUtils.parseObject(body, SocialLoginBody.class);
        AuthResponse<AuthUser> response = SocialUtils.loginAuth(
                loginBody2.getSource(), loginBody2.getSocialCode(),
                loginBody2.getSocialState(), socialProperties);
        if (!response.ok()) {
            throw new ServiceException(response.getMsg());
        }
        AuthUser authUserData = response.getData();

        //获取用户信息
//        LoginVo loginVo = IAuthStrategy.login(body, client, grantType);
        //新增用户
        LoginUser loginUser = loginService.isHaveLogined(authUserData.getUsername());
        //feign->新增用户相关表sys_social

        //创建token
        Map<String, Object> token = tokenService.createToken(loginUser);
        //封装loginVo
        LoginVo loginVo = new LoginVo();
        loginVo.setClientId(clientId);
        loginVo.setAccessToken(token.get("access_token").toString());
        loginVo.setExpireIn((long)token.get("expire_in"));
        return R.ok(loginVo);
    }

    /**
     * 第三方登录请求
     *
     * @param source 登录来源
     * @return 结果
     */
    @GetMapping("/binding/{source}")
    public R<String> authBinding(@PathVariable("source") String source,
                                 @RequestParam String tenantId, @RequestParam String domain) {
        SocialLoginConfigProperties obj = socialProperties.getType().get(source);
        if (ObjectUtil.isNull(obj)) {
            return R.fail(source + "平台账号暂不支持");
        }
        AuthRequest authRequest = SocialUtils.getAuthRequest(source, socialProperties);
        Map<String, String> map = new HashMap<>();
        map.put("tenantId", tenantId);
        map.put("domain", domain);
        map.put("state", AuthStateUtils.createState());
        String authorizeUrl = authRequest.authorize(Base64.encode(JsonUtils.toJsonString(map), StandardCharsets.UTF_8));
        return R.ok("操作成功", authorizeUrl);
    }

    /**
     * 第三方登录回调业务处理 绑定授权
     *
     * @param loginBody 请求体
     * @return 结果
     */
    @PostMapping("/social/callback")
    public R<Void> socialCallback(@RequestBody SocialLoginBody loginBody) {
        // 获取第三方登录信息
        AuthResponse<AuthUser> response = SocialUtils.loginAuth(
                loginBody.getSource(), loginBody.getSocialCode(),
                loginBody.getSocialState(), socialProperties);
        AuthUser authUserData = response.getData();
        // 判断授权响应是否成功
        if (!response.ok()) {
            return R.fail(response.getMsg());
        }
        //先判断再注册
        loginService.socialRegister(authUserData);
        return R.ok();
    }


    /**
     * 取消授权
     *
     * @param socialId socialId
     */
//    @DeleteMapping(value = "/unlock/{socialId}")
//    public R<Void> unlockSocial(@PathVariable Long socialId) {
//        Boolean rows = socialUserService.deleteWithValidById(socialId);
//        return rows ? R.ok() : R.fail("取消授权失败");
//    }


    /**
     * 退出登录
     */
//    @PostMapping("/logout")
//    public R<Void> logout() {
//        loginService.logout();
//        return R.ok("退出成功");
//    }

    /**
     * 用户注册
     */
//    @ApiEncrypt
//    @PostMapping("/register")
//    public R<Void> register(@Validated @RequestBody RegisterBody user) {
//        if (!configService.selectRegisterEnabled(user.getTenantId())) {
//            return R.fail("当前系统没有开启注册功能！");
//        }
//        registerService.register(user);
//        return R.ok();
//    }


}
