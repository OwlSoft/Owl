/*
 * This file is part of OwlSoft Owl.
 *
 *  OwlSoft Owl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  alphaTab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with alphaTab.  If not, see <http://www.gnu.org/licenses/>.
 */
public package at.owlsoft.owl.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Configuration
{
    private Properties _settings;

    public Configuration()
    {
        _settings = new Properties();
    }

    public void storeToXML(OutputStream os, String comment, String encoding)
            throws IOException
    {
        _settings.store(os, comment);
    }

    public void removeProperties(List<String> properties)
    {
        for (String key : properties)
        {
            _settings.remove(key);
        }
    }

    public Map<String, String> getAllPropterties()
    {
        Map<String, String> temp = new HashMap<String, String>();
        for (Entry<Object, Object> entry : _settings.entrySet())
        {
            temp.put((String) entry.getKey(), (String) entry.getValue());
        }
        return temp;
    }

    public String getString(String name)
    {
        return getString(name, null);
    }

    public String getString(String name, String defaultValue)
    {
        String value = _settings.getProperty(name);
        return value == null ? defaultValue : value;
    }

    public byte getByte(String name)
    {
        return getByte(name, (byte) 0);
    }

    public byte getByte(String name, byte defaultValue)
    {
        try
        {
            String property = _settings.getProperty(name);
            Byte value = null;
            if (property != null && !property.isEmpty())
            {
                value = Byte.parseByte(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as byte");
        }
    }

    public short getShort(String name)
    {
        return getShort(name, (short) 0);
    }

    public short getShort(String name, short defaultValue)
    {
        try
        {
            String property = _settings.getProperty(name);
            Short value = null;
            if (property != null && !property.isEmpty())
            {
                value = Short.parseShort(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as short");
        }
    }

    public int getInt(String name)
    {
        return getInt(name, 0);
    }

    public int getInt(String name, int defaultValue)
    {
        try
        {
            String property = _settings.getProperty(name);
            Integer value = null;
            if (property != null && !property.isEmpty())
            {
                value = Integer.parseInt(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {

            throw new InvalidOperationException(
                    "Could not parse property as integer");

        }
    }

    public long getLong(String name)
    {
        return getLong(name, 0l);
    }

    public long getLong(String name, Long defaultValue)
    {
        try
        {
            String property = _settings.getProperty(name);
            Long value = null;
            if (property != null && !property.isEmpty())
            {
                value = Long.parseLong(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as long");
        }
    }

    public void set(String name, String value)
    {
        _settings.setProperty(name, value);
    }

    public void set(String name, byte value)
    {
        _settings.setProperty(name, Byte.toString(value));
    }

    public void set(String name, short value)
    {
        _settings.setProperty(name, Short.toString(value));
    }

    public void set(String name, int value)
    {
        _settings.setProperty(name, Integer.toString(value));
    }

    public void set(String name, long value)
    {
        _settings.setProperty(name, Long.toString(value));
    }

    public void loadProperties(Properties properties)
    {
        _settings.putAll(properties);
    }

    //
    // Class specific
    //

    public String getString(Class<?> clz, String name)
    {
        return getString(name, null);
    }

    public String getString(Class<?> clz, String name, String defaultValue)
    {
        String value = _settings.getProperty(getClassProperty(clz, name));
        return value == null ? defaultValue : value;
    }

    public byte getByte(Class<?> clz, String name)
    {
        return getByte(name, (byte) 0);
    }

    public byte getByte(Class<?> clz, String name, byte defaultValue)
    {
        try
        {
            String property = _settings
                    .getProperty(getClassProperty(clz, name));
            Byte value = null;
            if (property != null && !property.isEmpty())
            {
                value = Byte.parseByte(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as byte");
        }
    }

    public short getShort(Class<?> clz, String name)
    {
        return getShort(name, (short) 0);
    }

    public short getShort(Class<?> clz, String name, short defaultValue)
    {
        try
        {
            String property = _settings
                    .getProperty(getClassProperty(clz, name));
            Short value = null;
            if (property != null && !property.isEmpty())
            {
                value = Short.parseShort(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as short");
        }
    }

    public int getInt(Class<?> clz, String name)
    {
        return getInt(name, 0);
    }

    public int getInt(Class<?> clz, String name, int defaultValue)
    {
        try
        {
            String property = _settings
                    .getProperty(getClassProperty(clz, name));
            Integer value = null;
            if (property != null && !property.isEmpty())
            {
                value = Integer.parseInt(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as integer");
        }
    }

    public long getLong(Class<?> clz, String name)
    {
        return getLong(name, 0l);
    }

    public long getLong(Class<?> clz, String name, Long defaultValue)
    {
        try
        {
            String property = _settings
                    .getProperty(getClassProperty(clz, name));
            Long value = null;
            if (property != null && !property.isEmpty())
            {
                value = Long.parseLong(property);
            }
            return value == null ? defaultValue : value;
        }
        catch (Exception e)
        {
            throw new InvalidOperationException(
                    "Could not parse property as long");
        }
    }

    public void set(Class<?> clz, String name, String value)
    {
        _settings.put(getClassProperty(clz, name), value);
    }

    public void set(Class<?> clz, String name, byte value)
    {
        _settings.put(getClassProperty(clz, name), value);
    }

    public void set(Class<?> clz, String name, short value)
    {
        _settings.put(getClassProperty(clz, name), value);
    }

    public void set(Class<?> clz, String name, int value)
    {
        _settings.put(getClassProperty(clz, name), value);
    }

    public void set(Class<?> clz, String name, long value)
    {
        _settings.put(getClassProperty(clz, name), value);
    }

    private String getClassProperty(Class<?> clz, String name)
    {
        return clz.getName().concat(".").concat(name);
    }

}
