package at.owlsoft.owl.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import at.owlsoft.owl.model.Configuration;
import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.NoPermissionException;

public class ConfigurationController extends ControllerBase
{
    /**
     * 
     */
    private static final long serialVersionUID = -8164032688659737769L;
    private static final Logger  logger               = Logger.getLogger(ConfigurationController.class);
    private static Configuration _configuration;
    private static Object        configLock           = new Object();
    private static final String  PROPERTIES_FILE_NAME = "settings.properties";

    public ConfigurationController(OwlApplicationContext context)
    {
        super(context);

        load(false);

    }

    public void load(boolean reload)
    {
        synchronized (configLock)
        {
            if (_configuration != null && !reload)
            {
                return;
            }

            _configuration = new Configuration();
            File fi = new File(PROPERTIES_FILE_NAME);

            if (fi.exists())
            {
                FileInputStream in = null;
                try
                {
                    in = new FileInputStream(fi);

                    Properties p = new Properties();
                    p.load(in);

                    _configuration.loadProperties(p);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    logger.warn("settings not loaded", e);
                }
                finally
                {
                    if (in != null)
                    {
                        try
                        {
                            in.close();
                        }
                        catch (IOException e)
                        {
                            logger.warn("settingsfile not closed", e);
                        }
                    }
                }
            }
        }
    }

    public void store() throws NoPermissionException, IOException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.ADMIN_CONFIG);

        File file = new File(PROPERTIES_FILE_NAME);
        if (!file.exists())
        {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        _configuration.storeToXML(out, null, "UTF-8");
    }

    public void removeProperty(List<String> properties)
            throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.ADMIN_CONFIG);

        _configuration.removeProperties(properties);
    }

    public Map<String, String> getAllProperties() throws NoPermissionException
    {
        return _configuration.getAllPropterties();
    }

    public void setAll(Map<String, String> properties)
            throws NoPermissionException
    {
        for (Entry<String, String> entry : properties.entrySet())
        {
            set(entry.getKey(), entry.getValue());
        }
    }

    public String getString(String name)
    {
        return _configuration.getString(name);
    }

    public String getString(String name, String defaultValue)
    {
        return _configuration.getString(name, defaultValue);
    }

    public byte getByte(String name)
    {
        return _configuration.getByte(name);
    }

    public byte getByte(String name, byte defaultValue)
    {
        return _configuration.getByte(name, defaultValue);
    }

    public short getShort(String name)
    {
        return _configuration.getShort(name);
    }

    public short getShort(String name, short defaultValue)
    {
        return _configuration.getShort(name, defaultValue);
    }

    public int getInt(String name)
    {
        return _configuration.getInt(name);
    }

    public int getInt(String name, int defaultValue)
    {
        return _configuration.getInt(name, defaultValue);
    }

    public long getLong(String name)
    {
        return _configuration.getLong(name);
    }

    public long getLong(String name, Long defaultValue)
    {
        return _configuration.getLong(name, defaultValue);
    }

    public void set(String name, String value)
    {
        _configuration.set(name, value);
    }

    public void set(String name, byte value)
    {
        _configuration.set(name, value);
    }

    public void set(String name, short value)
    {
        _configuration.set(name, value);
    }

    public void set(String name, int value)
    {
        _configuration.set(name, value);
    }

    public void set(String name, long value)
    {
        _configuration.set(name, value);
    }

    public String getString(Class<?> clz, String name)
    {
        return _configuration.getString(clz, name);
    }

    public String getString(Class<?> clz, String name, String defaultValue)
    {
        return _configuration.getString(clz, name, defaultValue);
    }

    public byte getByte(Class<?> clz, String name)
    {
        return _configuration.getByte(clz, name);
    }

    public byte getByte(Class<?> clz, String name, byte defaultValue)
    {
        return _configuration.getByte(clz, name, defaultValue);
    }

    public short getShort(Class<?> clz, String name)
    {
        return _configuration.getShort(clz, name);
    }

    public short getShort(Class<?> clz, String name, short defaultValue)
    {
        return _configuration.getShort(clz, name, defaultValue);
    }

    public int getInt(Class<?> clz, String name)
    {
        return _configuration.getInt(clz, name);
    }

    public int getInt(Class<?> clz, String name, int defaultValue)
    {
        return _configuration.getInt(clz, name, defaultValue);
    }

    public long getLong(Class<?> clz, String name)
    {
        return _configuration.getLong(clz, name);
    }

    public long getLong(Class<?> clz, String name, Long defaultValue)
    {
        return _configuration.getLong(clz, name, defaultValue);
    }

    public void set(Class<?> clz, String name, String value)
    {
        _configuration.set(clz, name, value);
    }

    public void set(Class<?> clz, String name, byte value)
    {
        _configuration.set(clz, name, value);
    }

    public void set(Class<?> clz, String name, short value)
    {
        _configuration.set(clz, name, value);
    }

    public void set(Class<?> clz, String name, int value)
    {
        _configuration.set(clz, name, value);
    }

    public void set(Class<?> clz, String name, long value)
    {
        _configuration.set(clz, name, value);
    }

}
