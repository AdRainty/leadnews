package com.heima.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:42
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登陆", tags = "app端用户登陆")
public class ApUserLoginController {

    private final ApUserService apUserService;

    @ApiOperation("用户登陆")
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        return apUserService.login(loginDto);
    }

}
