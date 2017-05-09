package com.blackniuza.stock.common.util;

import java.text.ParseException;
import java.util.Date;

import com.blackniuza.stock.common.type.DateFormat;
import com.google.common.base.Preconditions;
import org.apache.parquet.Strings;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class DateUtils {

    public static Date formatToDate(String dateStr, DateFormat dateFormat) throws ParseException {
        Preconditions.checkState(!Strings.isNullOrEmpty(dateStr));
        Preconditions.checkNotNull(dateFormat);
        return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, dateFormat.getFormat());
    }

}
