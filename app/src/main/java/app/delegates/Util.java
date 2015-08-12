package app.delegates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.sql.Timestamp;
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

    public static String dateToString(Date date){
        Timestamp ts=new Timestamp(date.getTime());
        return ts.toString();
    }
}
