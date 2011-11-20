package at.owlsoft.owl.model.test;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.Configuration;

public class ConfigurationTest
{
    private static final String TEST_KEY = "at.owlsoft.test";

    @Test
    public void testString()
    {
        String testValue = "Hello World";

        Configuration configuration = new Configuration();
        configuration.set(TEST_KEY, testValue);

        Assert.assertEquals(testValue, configuration.getString(TEST_KEY));
    }

    @Test
    public void testStringDefaultValue()
    {
        String testValue = "Hello World";

        Configuration configuration = new Configuration();
        Assert.assertEquals(testValue,
                configuration.getString(TEST_KEY, testValue));
    }

    @Test
    public void testByte()
    {
        byte testValue = (byte) 100;

        Configuration configuration = new Configuration();
        configuration.set(TEST_KEY, testValue);

        Assert.assertEquals(testValue, configuration.getByte(TEST_KEY));
    }

    @Test
    public void testByteDefaultValue()
    {
        byte testValue = (byte) 100;

        Configuration configuration = new Configuration();
        Assert.assertEquals(testValue,
                configuration.getByte(TEST_KEY, testValue));
    }

    @Test
    public void testShort()
    {
        short testValue = (short) 65000;

        Configuration configuration = new Configuration();
        configuration.set(TEST_KEY, testValue);

        Assert.assertEquals(testValue, configuration.getShort(TEST_KEY));
    }

    @Test
    public void testShortDefaultValue()
    {
        short testValue = (short) 65000;

        Configuration configuration = new Configuration();
        Assert.assertEquals(testValue,
                configuration.getShort(TEST_KEY, testValue));
    }

    @Test
    public void testInt()
    {
        int testValue = 2147483000;
        Configuration configuration = new Configuration();
        configuration.set(TEST_KEY, testValue);

        Assert.assertEquals(testValue, configuration.getInt(TEST_KEY));
    }

    @Test
    public void testIntDefaultValue()
    {
        int testValue = 2147483000;

        Configuration configuration = new Configuration();
        Assert.assertEquals(testValue,
                configuration.getInt(TEST_KEY, testValue));
    }

    @Test
    public void testLong()
    {
        long testValue = 9223372036854775000l;
        Configuration configuration = new Configuration();
        configuration.set(TEST_KEY, testValue);

        Assert.assertEquals(testValue, configuration.getLong(TEST_KEY));
    }

    @Test
    public void testLongDefaultValue()
    {
        long testValue = 9223372036854775000l;

        Configuration configuration = new Configuration();
        Assert.assertEquals(testValue,
                configuration.getLong(TEST_KEY, testValue));
    }
}
