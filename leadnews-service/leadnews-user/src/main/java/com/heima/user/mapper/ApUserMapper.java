package com.heima.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.pojos.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:45
 */

@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
