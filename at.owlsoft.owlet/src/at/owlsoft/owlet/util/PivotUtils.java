package at.owlsoft.owlet.util;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

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
