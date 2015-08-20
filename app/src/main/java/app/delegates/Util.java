package app.delegates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steve on 8/11/2015.
 */
public class Util {

    public static Date stringToDate(String s) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date date) {
        Timestamp ts = new Timestamp(date.getTime());
        return ts.toString();
    }

    public static String dateToStringShort(Date date) {
        DateFormat df = new SimpleDateFormat("MM-dd");
        String str = df.format(date);
        return str;
    }

    public static String dateToStringShortWithYear(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String str = df.format(date);
        return str;
    }

    public static float round(double val) {
        return new BigDecimal(val).setScale(1, RoundingMode.HALF_UP).floatValue();
    }

}
