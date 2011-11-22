package at.owlsoft.owl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivatAccessorFramework
{

    @SuppressWarnings("unchecked")
    static public <T> T getPrivateField(Object o, String name)
    {
        try
        {
            Field field = o.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return (T) field.get(o);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    static public <T> T callPrivateMethod(Object o, String name,
            Object... params)
    {
        try
        {
            Class<?>[] types = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++)
            {
                types[i] = params.getClass();
            }

            Method method = o.getClass().getDeclaredMethod(name, types);
            method.setAccessible(true);
            return (T) method.invoke(o, params);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    static public void callPrivateMethodNoReturn(Object o, String name,
            Object... params)
    {
        try
        {
            Class<?>[] types = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++)
            {
                types[i] = params.getClass();
            }

            Method method = o.getClass().getMethod(name, types);
            method.setAccessible(true);
            method.invoke(o, params);
        }
        catch (Exception e)
        {
        }
    }
}
