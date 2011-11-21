package at.owlsoft.owl.model.dao.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldDefinition;
import at.owlsoft.owl.model.user.SystemUser;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DaoTests
{
    @Test
    /**
     * this test must be executed as administrator otherwise the db file will not be deleted
     */
    public void testQueryByPropertyList()
    {

        // this test must be executed as administrator otherwise the db file
        // will not be deleted

        File file = new File("testDb");
        if (file.delete())
        {
            System.out
                    .println("this test must be executed as administrator otherwise the db file will not be deleted");
            System.out.println("fehler beim löschen der Datenbank");
        }

        ObjectContainer _db = Db4oEmbedded.openFile(
                Db4oEmbedded.newConfiguration(), "testDb");

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

        // test 1 only manuel must come out of database
        System.out.println("test1");
        SearchFieldDefinition definition = new SearchFieldDefinition();

        definition.getKeys().add(new SearchField("_firstName", "Manuel"));

        List<SystemUser> users = DaoManager.getInstance("testDb")
                .getSystemUserDao().queryByPropertyList(definition);

        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
        }
        Assert.assertTrue(!(new Integer(0).equals(users.size())));

        // test 2 no match --> no users must come out of database
        System.out.println("test2");
        definition.setKeys(new ArrayList<SearchField>());
        definition.getKeys().add(
                new SearchField("_firstName", "asdfsadfsadfsadf"));
        users = DaoManager.getInstance("testDb").getSystemUserDao()
                .queryByPropertyList(definition);

        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
        }

        Assert.assertTrue((new Integer(0).equals(users.size())));

        // test 3 --> we have to diffrent users in database only 1 (manuel) must
        // come out with this query
        System.out.println("test 3");
        definition.setKeys(new ArrayList<SearchField>());
        definition.getKeys().add(new SearchField("_firstName", "Manuel"));
        users = DaoManager.getInstance("testDb").getSystemUserDao()
                .queryByPropertyList(definition);

        Integer countUsers = new Integer(0);
        for (SystemUser systemUser : users)
        {
            System.out.println(systemUser.getFirstName());
            countUsers++;
        }

        Assert.assertTrue(countUsers.equals(users.size()));

    }
}
