package com.luo.niukouoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码沙箱枚举
 *
 * @author luo
 */
public enum CodeSandBoxEnum {

    /**
     * 示例代码沙箱
     */
    EXAMPLE("示例", "example"),
    /**
     * 远程代码沙箱
     */
    REMOTE("远程", "remote"),

    /**
     * 第三方代码沙箱
     */
    THIRD_PART("第三方", "third_part");

    private final String text;

    private final String value;

    CodeSandBoxEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static CodeSandBoxEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (CodeSandBoxEnum anEnum : CodeSandBoxEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
