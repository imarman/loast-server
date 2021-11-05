package com.loast.utils;

import cn.hutool.crypto.digest.MD5;

/**
 * @author Arman
 * @date 2021/11/4 23:21
 */
public class Md5Utils {

    public static String encrypt(String originPwd, String salt) {
        return MD5.create().setSalt(salt.getBytes()).digestHex(originPwd);
    }
}
