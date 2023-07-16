package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.result.LoginResult;
import com.heima.model.user.vo.UserVo;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;
import java.util.Objects;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:45
 */

@Slf4j
@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Override
    public ResponseResult login(LoginDto loginDto) {
        if (StringUtils.isNoneBlank(loginDto.getPhone(), loginDto.getPassword())) {
            ApUser dbUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            if (Objects.isNull(dbUser)) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户信息不存在");
            }

            String pwd = DigestUtils.md5DigestAsHex((loginDto.getPassword() + dbUser.getSalt()).getBytes());
            if (dbUser.getPassword().equals(pwd)) {
                String token = AppJwtUtil.getToken(dbUser.getId().longValue());
                return ResponseResult.okResult(new LoginResult(token, new UserVo(dbUser)));
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
        }

        String token = AppJwtUtil.getToken(0L);
        return ResponseResult.okResult(new LoginResult(token, null));
    }
}
