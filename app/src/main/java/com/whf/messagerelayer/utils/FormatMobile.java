package com.whf.messagerelayer.utils;

/**
 * Created by WHF on 2017/3/30.
 */

public class FormatMobile {

    public static Boolean hasPrefix(String mobile){
        return mobile.startsWith("+86");
    }

    public static String formatMobile(String mobile){
        return mobile.substring(3);
    }
}
