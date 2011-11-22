package at.owlsoft.owl.model.dao.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.user.SystemUser;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DaoTests
{
    private static final String TEST_DB = "testDb";

    @BeforeClass
    public static void setup()
    {
        // this test must be executed as administrator otherwise the db file
        // will not be deleted

        File file = new File(TEST_DB);
        if (file.exists())
        {
            if (!file.delete())
            {
                System.out
                        .println("this test must be executed as administrator otherwise the db file will not be deleted");
                System.out.println("fehler beim löschen der Datenbank");
            }
        }

        ObjectContainer _db = Db4oEmbedded.openFile(
                Db4oEmbedded.newConfiguration(), TEST_DB);

        SystemUser manuel = new SystemUser();
        manuel.setFirstName("Manuel");
        manuel.setLastName("Tscholl");

        SystemUser hans = new SystemUser();
        hans.setFirstName("hans");
        hans.setLastName("plast");

        _db.store(hans);
        _db.store(manuel);
        _db.commit();
        _db.close();
    }

    @AfterClass
    public static void tearDown()
    {
        new File(TEST_DB).delete();
    }

    @Test
    public void testQueryByExistingEntry()
    {
        // test 1 only manuel must come out of database
        System.out.println("test1");

        List<SearchField> criterias = new ArrayList<SearchField>();
        criterias.add(new SearchField("_firstName", "Manuel"));

        List<SystemUser> users = DaoManager.getInstance(TEST_DB)
                .getSystemUserDao().queryByPropertyList(criterias);

        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
        }
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void testQueryByNonExistingEntry()
    {
        // test 2 no match --> no users must come out of database
        System.out.println("test2");

        List<SearchField> criterias = new ArrayList<SearchField>();
        criterias.add(new SearchField("_firstName", "asdfsadfsadfsadf"));

        List<SystemUser> users = DaoManager.getInstance(TEST_DB)
                .getSystemUserDao().queryByPropertyList(criterias);

        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
        }

        Assert.assertEquals(0, users.size());
    }

    @Test
    public void testResultSet()
    {
        // test 3 --> we have to diffrent users in database only 1 (manuel) must
        // come out with this query
        System.out.println("test 3");
        List<SearchField> criterias = new ArrayList<SearchField>();
        criterias.add(new SearchField("_firstName", "Manuel"));
        List<SystemUser> users = DaoManager.getInstance(TEST_DB)
                .getSystemUserDao().queryByPropertyList(criterias);

        int countUsers = 0;
        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
            countUsers++;
        }
        System.out.println(countUsers);

        Assert.assertTrue(users.size() > 0);
        Assert.assertEquals(users.size(), countUsers);

    }
}
