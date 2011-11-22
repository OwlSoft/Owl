package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.AccountMode;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;
import at.owlsoft.owl.usecases.RentalController;
import at.owlsoft.owl.usecases.ValidationMessage;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class RentalControllerTest
{

    private static final String   TEST_DB = "rentalTestDb";
    private static MediumExemplar _copy;
    private static Medium         _lendable;
    private static Medium         _unlendable;
    private static SystemUser     _activeUser;
    private static SystemUser     _inactiveUser;

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

        Medium lendable = new Book();
        lendable.setMediumID(0);
        MediumExemplar toLend = new MediumExemplar(0, lendable);
        lendable.addMediumExemplar(toLend);
        toLend.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), toLend, MediumExemplarStatus.Rentable));

        Medium unlendable = new Book();

        SystemUser active = new SystemUser(0, 0, "user", "password", "",
                "R.E.", "Turner", new Date(), AccountMode.Ldap);
        active.addSystemUserStatusEntry(new SystemUserStatusEntry(new Date(),
                "active", SystemUserStatus.Active, active));

        SystemUser inactive = new SystemUser(0, 0, "user", "password", "",
                "R.E.", "Turner", new Date(), AccountMode.Ldap);
        inactive.addSystemUserStatusEntry(new SystemUserStatusEntry(new Date(),
                "active", SystemUserStatus.InactiveBecauseMembershipFee, active));

        _db.store(toLend);
        _db.store(active);
        _db.store(inactive);
        _db.store(lendable);
        _db.store(unlendable);

        _lendable = lendable;
        _unlendable = unlendable;
        _activeUser = active;
        _inactiveUser = inactive;
        _copy = toLend;

    }

    @AfterClass
    public static void tearDown()
    {
        new File(TEST_DB).delete();
    }

    @Test
    public void test()
    {
        System.out.println("Test: user active, medium has a rentable");
        RentalController controller = new RentalController();
        controller.setCustomer(_activeUser);
        controller.setMediumExemplar(_lendable);
        List<ValidationMessage> messages = controller.save();
        assertEquals(
                MediumExemplarStatus.Rented,
                _copy.getMediumExemplarStatusEntry(
                        _copy.getMediumExemplarStatusEntryCount() - 1)
                        .getMediumExemplarStatus());
        assertNotNull(_copy.getLastRental());

        _copy.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), _copy, MediumExemplarStatus.Rentable));

        System.out.println("Test: user active, medium has no rentable");
        controller = new RentalController();
        controller.setCustomer(_activeUser);
        controller.setMediumExemplar(_unlendable);
        controller.save();

        assertEquals(
                MediumExemplarStatus.Rentable,
                _copy.getMediumExemplarStatusEntry(
                        _copy.getMediumExemplarStatusEntryCount() - 1)
                        .getMediumExemplarStatus());

        System.out.println("Test: user inactive, medium has rentable");
        controller = new RentalController();
        controller.setCustomer(_inactiveUser);
        controller.setMediumExemplar(_lendable);
        controller.save();

        assertEquals(
                MediumExemplarStatus.Rentable,
                _copy.getMediumExemplarStatusEntry(
                        _copy.getMediumExemplarStatusEntryCount() - 1)
                        .getMediumExemplarStatus());

        _copy.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), _copy, MediumExemplarStatus.Rentable));

        System.out.println("Test: user inactive, medium has no rentable");
        controller = new RentalController();
        controller.setCustomer(_inactiveUser);
        controller.setMediumExemplar(_unlendable);
        controller.save();

        assertEquals(
                MediumExemplarStatus.Rentable,
                _copy.getMediumExemplarStatusEntry(
                        _copy.getMediumExemplarStatusEntryCount() - 1)
                        .getMediumExemplarStatus());

    }

}
