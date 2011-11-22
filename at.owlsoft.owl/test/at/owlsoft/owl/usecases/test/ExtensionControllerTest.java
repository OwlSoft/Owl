package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;
import at.owlsoft.owl.usecases.ExtensionController;
import at.owlsoft.owl.usecases.ValidationMessage;

public class ExtensionControllerTest
{

    private static final String   TEST_DB = "extensionTestDb";
    private static Rental         _rental;
    private static MediumExemplar _copy;
    private static SystemUser     _customer;

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

        _rental = new Rental();

        _customer = new SystemUser();
        _customer.addSystemUserStatusEntry(new SystemUserStatusEntry(
                new Date(), "", SystemUserStatus.Active, null));

        _copy = new MediumExemplar();

        _rental.setMediumExemplar(_copy);
        _copy.addActivity(_rental);
        _rental.setCustomer(_customer);
        _customer.addActivity(_rental);
        _rental.setEndDate(new Date());

        DaoManager.getInstance(TEST_DB).getSystemUserDao().store(_customer);
        DaoManager.getInstance(TEST_DB).getRentalDao().store(_rental);
        DaoManager.getInstance(TEST_DB).getMediumExemplarDao().store(_copy);

    }

    @AfterClass
    public static void tearDown()
    {
        DaoManager.closeDbConnection();
        new File(TEST_DB).delete();
    }

    @Test
    public void test()
    {
        ExtensionController controller = new ExtensionController();
        controller.extend(_copy);
        assertEquals(1, _rental.getFilingExtensionCount());
        controller.extend(_copy);
        assertEquals(2, _rental.getFilingExtensionCount());
        controller.extend(_copy);
        assertEquals(3, _rental.getFilingExtensionCount());
        List<ValidationMessage> messages = controller.extend(_copy);
        assertEquals(true, messages.size() > 0);
        assertEquals(3, _rental.getFilingExtensionCount());
    }

}
