package com.radodosev.fivedayweather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rado on 8/25/2017.
 */

public final class CommonUtils {

    public static Date parseDate(final String date) {
        final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return parser.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String formatDate(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
