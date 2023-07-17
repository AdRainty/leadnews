package com.heima.model.wemedia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/17 22:54
 */

@AllArgsConstructor
@Getter
public enum WmNewsStatus {

    NORMAL(0, "草稿"),
    SUBMIT(1, "提交待审核"),
    FAIL(2, "审核失败"),
    ADMIN_AUTH(3, "人工审核"),
    ADMIN_SUCCESS(4, "人工审核通过"),
    SUCCESS(8, "审核通过待发布"),
    PUBLISHED(9, "已发布");

    private final Integer code;

    private final String msg;

}
