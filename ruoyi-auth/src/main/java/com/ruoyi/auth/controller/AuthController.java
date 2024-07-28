package com.ruoyi.auth.controller;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.client.utils.ValidatorUtils;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.SocialLoginBody;
import com.ruoyi.common.core.exception.auth.NotLoginException;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.social.config.properties.SocialLoginConfigProperties;
import com.ruoyi.common.social.config.properties.SocialProperties;
import com.ruoyi.common.social.utils.SocialUtils;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
//    @PostMapping("/login")
//    public R<LoginVo> login(@RequestBody String body) {
//        LoginBody loginBody = JsonUtils.parseObject(body, LoginBody.class);
//        ValidatorUtils.validate(loginBody);
//        // 授权类型和客户端id
//        String clientId = loginBody.getClientId();
//        String grantType = loginBody.getGrantType();
//        SysClientVo client = clientService.queryByClientId(clientId);
//        // 查询不到 client 或 client 内不包含 grantType
//        if (ObjectUtil.isNull(client) || !StringUtils.contains(client.getGrantType(), grantType)) {
//            log.info("客户端id: {} 认证类型：{} 异常!.", clientId, grantType);
//            return R.fail(MessageUtils.message("auth.grant.type.error"));
//        } else if (!UserConstants.NORMAL.equals(client.getStatus())) {
//            return R.fail(MessageUtils.message("auth.grant.type.blocked"));
//        }
//        // 校验租户
//        loginService.checkTenant(loginBody.getTenantId());
//        // 登录
//        LoginVo loginVo = IAuthStrategy.login(body, client, grantType);
//
//        Long userId = LoginHelper.getUserId();
//        scheduledExecutorService.schedule(() -> {
//            WebSocketMessageDto dto = new WebSocketMessageDto();
//            dto.setMessage("欢迎登录RuoYi-Vue-Plus后台管理系统");
//            dto.setSessionKeys(List.of(userId));
//            WebSocketUtils.publishMessage(dto);
//        }, 3, TimeUnit.SECONDS);
//        return R.ok(loginVo);
//    }

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
