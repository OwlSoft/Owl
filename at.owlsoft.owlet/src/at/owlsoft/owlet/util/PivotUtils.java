package at.owlsoft.owlet.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.CalendarDate;

public class PivotUtils
{
    public static <T> List<T> toPivotList(Collection<T> t)
    {
        List<T> lst = new ArrayList<T>();
        for (T i : t)
        {
            lst.add(i);
        }
        return lst;
    }

    public static CalendarDate toCalendarDate(Date date)
    {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return new CalendarDate(c);
    }

    static final long ONE_HOUR = 60 * 60 * 1000L;

    public static long daysBetween(Date d1, Date d2)
    {
        return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
    }

    private static DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static String formatDate(Date date)
    {
        return FORMAT.format(date);
    }
}
