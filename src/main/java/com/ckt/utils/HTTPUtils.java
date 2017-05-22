package com.ckt.utils;

import java.util.UUID;

/**
 * Created by admin on 2017/5/19.
 */
public class HTTPUtils {

    public static final String newToken(){
        return UUID.randomUUID().toString();
    }
}
