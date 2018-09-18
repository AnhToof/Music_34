package com.framgia.music_34.utils;

import java.text.SimpleDateFormat;

public class DateUtils {

    public static String formatDate(Object input, String outputFormat) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(input);
    }
}
