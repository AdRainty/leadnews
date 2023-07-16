package com.heima.model.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:53
 */

@Getter
@AllArgsConstructor
public enum LoadDataEnum {

    LOAD_MORE(1, "加载更多"),
    LOAD_NEW(2, "加载最新");

    private final int code;

    private final String msg;

}
