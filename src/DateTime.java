import java.text.SimpleDateFormat;
import java.util.Date;

class DateTime {
    //class to keep track of date and time.
    Date DateTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    public static String getDate() {
        Date DateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(DateTime);
    }
    public static String getTime() {
        Date DateTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        return timeFormat.format(DateTime);
    }
}