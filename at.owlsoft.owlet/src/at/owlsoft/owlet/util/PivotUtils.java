package at.owlsoft.owlet.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.CalendarDate;

import at.owlsoft.owl.model.accounting.IRental;

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

    public static <T> java.util.List<T> toJavaList(
            org.apache.pivot.collections.List<T> pivotList)
    {
        java.util.List<T> javaList = new java.util.ArrayList<T>();

        for (T value : pivotList)
        {
            javaList.add(value);
        }

        return javaList;
    }

    public static <K, V> org.apache.pivot.collections.Map<K, V> toPivotMap(
            java.util.Map<K, V> javaMap)
    {
        org.apache.pivot.collections.Map<K, V> pivotMap = new org.apache.pivot.collections.HashMap<K, V>();

        for (java.util.Map.Entry<K, V> entry : javaMap.entrySet())
        {
            pivotMap.put(entry.getKey(), entry.getValue());
        }

        return pivotMap;
    }

    public static <K, V> java.util.Map<K, V> toJavaMap(
            org.apache.pivot.collections.Map<K, V> pivotMap)
    {

        java.util.Map<K, V> javaMap = new java.util.HashMap<K, V>();

        for (K key : pivotMap)
        {
            javaMap.put(key, pivotMap.get(key));
        }

        return javaMap;
    }

    public static String convertIRentalToString(IRental item)
    {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");

        if (item.getStartDate() != null)
        {
            builder.append(format.format(item.getStartDate()));
            builder.append(" - ");
        }
        if (item.getEndDate() != null)
        {
            builder.append(format.format(item.getEndDate()));
            builder.append(" : ");
        }
        builder.append(item.getMediumExemplar().getMedium().getName());

        return builder.toString();
    }
}
