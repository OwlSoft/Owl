package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.AccountMode;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;
import at.owlsoft.owl.usecases.ReturnController;

public class ReturnControllerTest
{
    private static final String          TEST_DB = "returnTestDb";
    private static MediumExemplar        _copy;

    private static OwlApplicationContext _context;

    @BeforeClass
    public static void setup()
    {
        _context = new OwlApplicationContext();
        // this test must be executed as administrator otherwise the db file
        // will not be deleted

        File file = new File(TEST_DB);
        if (file.exists())
        {
            if (!file.delete())
            {
                System.out
                        .println("this test must be executed as administrator otherwise the db file will not be deleted");
                System.out.println("fehler beim l�schen der Datenbank");
            }
        }

        Medium medium = new Book();
        medium.setMediumID(0);
        MediumExemplar toReturn = new MediumExemplar(0, medium);
        SystemUser returner = new SystemUser(0, 0, "user", "password", "",
                "R.E.", "Turner", new Date(), AccountMode.Ldap);
        returner.addSystemUserStatusEntry(new SystemUserStatusEntry(new Date(),
                "active", SystemUserStatus.Active, returner));

        SystemUser inactive = new SystemUser(0, 0, "user", "password", "",
                "R.E.", "Turner", new Date(), AccountMode.Ldap);
        inactive.addSystemUserStatusEntry(new SystemUserStatusEntry(new Date(),
                "active", SystemUserStatus.InactiveBecauseMembershipFee,
                returner));

        Rental rental = new Rental();
        rental.setMediumExemplar(toReturn);
        toReturn.addActivity(rental);
        rental.setCustomer(returner);

        DaoManager.getInstance(TEST_DB).getMediumExemplarDao().store(toReturn);
        DaoManager.getInstance(TEST_DB).getActivityDao().store(rental);
        DaoManager.getInstance(TEST_DB).getSystemUserDao().store(returner);
        DaoManager.getInstance(TEST_DB).getMediumDao().store(medium);

        _copy = toReturn;

    }

    @AfterClass
    public static void tearDown()
    {
        DaoManager.closeDbConnection();
        new File(TEST_DB).delete();
    }

    @Test
    public void testReturnMediumCopy()
    {
        ReturnController controller = _context.getReturnController();
        controller.returnMediumCopy(_copy);
        assertEquals(ActivityStatus.Returned, _copy.getActivity(0)
                .getActivityStatusEntry(0).getActivityStatus());
    }

}
