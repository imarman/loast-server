package com.loast.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author Arman
 */
public class GenerateUtils {

    public static final Integer SALT_LENGTH = 8;

    /**
     * 用雪花算法生成 ID
     *
     * @return 生成的 ID
     */
    public static Long generateId() {
        return IdUtil.getSnowflake().nextId();
    }

    /**
     * 生成一个激活码，在用户激活时使用
     *
     * @return 激活码
     */
    public static String makeActiveCode() {
        return IdUtil.fastSimpleUUID();
    }

    /**
     * 生成随机的盐值
     *
     * @return 盐值
     */
    public static String generateSalt() {
        StringBuilder result = new StringBuilder();
        char[] str = "abcdefghlgklmnopqrstuvwsyzABCDEFGHLGKLMNOPQRSTUVWSYZ12345".toCharArray();
        for (int i = 0; i < SALT_LENGTH; i++) {
            int indexNumber = (int) (Math.random() * str.length);
            result.append(str[indexNumber]);
        }
        return result.toString();
    }

}
