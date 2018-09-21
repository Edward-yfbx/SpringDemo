package com.yfbx.demo.util;

import java.util.UUID;

public class Utils {

    public static String createId() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
