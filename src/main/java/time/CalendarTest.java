package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间与日期
 *
 * @author yafei.hou  on 2018/5/7
 */
public class CalendarTest {

    public static void main(String[] args) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        System.out.println(calendar.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = simpleDateFormat.format(new Date());
        System.out.println(simpleDateFormat.parse(str));

        Calendar gregorianCalendar = GregorianCalendar.getInstance();

        int remainDay = (int) (new Date().getTime() - calendar.getTime().getTime()) / (3600 * 24*1000) + 1;
        System.out.println(remainDay);
    }
}
