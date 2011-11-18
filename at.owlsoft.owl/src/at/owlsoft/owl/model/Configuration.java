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
package at.owlsoft.owl.model;

import java.util.Properties;

public class Configuration
{
    private Properties _settings;

    public Configuration()
    {
        _settings = new Properties();
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
            Byte value = (Byte) _settings.get(name);
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
            Short value = (Short) _settings.get(name);
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
            Integer value = (Integer) _settings.get(name);
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
            Long value = (Long) _settings.get(name);
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
        _settings.put(name, value);
    }

    public void set(String name, byte value)
    {
        _settings.put(name, value);
    }

    public void set(String name, short value)
    {
        _settings.put(name, value);
    }

    public void set(String name, int value)
    {
        _settings.put(name, value);
    }

    public void set(String name, long value)
    {
        _settings.put(name, value);
    }
}
