package com.ebaocloud.sample.pub;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guo Rui on 5/3/17.
 */
public class Utils {
    public static String toChar(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
}
