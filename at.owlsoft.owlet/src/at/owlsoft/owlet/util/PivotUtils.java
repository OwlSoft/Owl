package at.owlsoft.owlet.util;

import java.util.Collection;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

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
}
