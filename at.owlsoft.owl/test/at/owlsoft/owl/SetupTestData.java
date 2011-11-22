package at.owlsoft.owl;

import org.junit.Test;

import db4oTestObjectGenerator.TestDataBaseConstructor;

public class SetupTestData
{
    @Test
    public void insertTestData()
    {
        TestDataBaseConstructor.setUpDataBase();
    }
}
