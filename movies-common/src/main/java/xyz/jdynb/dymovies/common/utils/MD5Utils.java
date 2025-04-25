package xyz.jdynb.dymovies.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class MD5Utils {

    @Nullable
    public static String encrypt(String str) {
        try {
            return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return null;
        }
    }

}
