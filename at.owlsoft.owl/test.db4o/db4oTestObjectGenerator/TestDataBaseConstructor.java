package db4oTestObjectGenerator;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.FilingExtension;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.accounting.Reservation;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.AccountMode;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;

public class TestDataBaseConstructor
{

    private static final String TEST_DB = "DB4OFILENAME";

    public static String getTestDBName()
    {
        return TEST_DB;
    }

    public static void setUpDataBase()
    {

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

        Calendar calendar = Calendar.getInstance();

        // create active test customer
        calendar.set(1990, 0, 1);
        Date birthday = calendar.getTime();
        SystemUser userZero = new SystemUser(0, 0, "max.mustermann",
                "password", "max@mustermann.at", "Max", "Mustermann", birthday,
                AccountMode.Ldap);

        // create inactive test customer
        calendar.set(1990, 0, 1);
        birthday = calendar.getTime();
        SystemUser userOne = new SystemUser(1, 1, "maria.musterfrau",
                "password", "maria@musterfrau.at", "Maria", "Musterfrau",
                birthday, AccountMode.Ldap);
        userOne.addSystemUserStatusEntry(new SystemUserStatusEntry(new Date(),
                "INACTIVE", SystemUserStatus.InactiveBecauseMembershipFee, null));

        // create test medium
        calendar.set(2006, 0, 1);
        Date publishedDate = calendar.getTime();
        Medium bookZero = new Book(0, "Reclam", "Faust", new Date(),
                publishedDate, "Johann Wolfgang von G�the", "Faust",
                "G�the sprach zu Schiller...", 136, null, "978-3-15-000001-4");

        // add test medium exemplar
        MediumExemplar bookZeroCopy = new MediumExemplar(0, bookZero);
        bookZero.addMediumExemplar(bookZeroCopy);

        MediumExemplar bookZeroCopy2 = new MediumExemplar(1, bookZero);
        bookZero.addMediumExemplar(bookZeroCopy2);

        // create another not rentable test medium
        calendar.set(2000, 0, 1);
        publishedDate = calendar.getTime();
        Medium bookOne = new Book(1, "Reclam", "Poetik", new Date(),
                publishedDate, "Aristoteles", "Poetik", "Karthasis", 182, null,
                "978-3-15-007828-0");

        // add medium exemplare
        MediumExemplar bookOneCopy = new MediumExemplar(2, bookOne);

        try
        {

            Thread.sleep(50);
        }
        catch (Exception e)
        {
            System.out.println("Warning");
        }
        bookOne.addMediumExemplar(bookOneCopy);
        bookOneCopy.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), bookOneCopy, MediumExemplarStatus.StockItem));

        // add rental for systemuser zero
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + 7, 0, 0, 0);
        Rental rental = new Rental(calendar.getTime());
        rental.setStartDate(new Date());
        rental.setCustomer(userZero);
        userZero.addActivity(rental);
        rental.setMediumExemplar(bookZeroCopy);
        bookZeroCopy.addActivity(rental);
        bookZeroCopy
                .addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                        new Date(), bookZeroCopy, MediumExemplarStatus.Rented));

        // add filing extension for rental
        calendar.setTime(rental.getEndDate());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + 7, 0, 0, 0);
        rental.addFilingExtension(new FilingExtension(new Date(), calendar
                .getTime(), rental));

        // add third user

        // create active test customer
        calendar.set(1980, 0, 1);
        birthday = calendar.getTime();
        SystemUser userTwo = new SystemUser(0, 0, "mika.mustermensch",
                "password", "mika@mustermensch.at", "Mika", "Mustermensch",
                birthday, AccountMode.Ldap);

        // create third medium
        calendar.set(2000, 0, 1);
        publishedDate = calendar.getTime();
        Medium bookTwo = new Book(2, "Reclam", "Il Principe / Der F�rst",
                new Date(), publishedDate, "Niccolo", "Machiavelli",
                "Philosophie", 255, null, "978-3-15-001219-2");

        // create rentable copy for third medium
        MediumExemplar bookTwoCopy = new MediumExemplar(3, bookTwo);

        // create rental for third user
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + 7, 0, 0, 0);
        Rental rental2 = new Rental(calendar.getTime());
        rental2.setStartDate(new Date());
        rental2.setCustomer(userTwo);
        userTwo.addActivity(rental2);
        rental2.setMediumExemplar(bookTwoCopy);
        bookTwoCopy.addActivity(rental2);
        bookTwoCopy.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), bookTwoCopy, MediumExemplarStatus.Rented));

        // add reservation
        Reservation reservation = new Reservation(calendar.getTime(), bookTwo,
                userZero, null, 7);
        bookZero.addActivtiy(reservation);
        userZero.addActivity(reservation);

        DaoManager.getInstance(TEST_DB).getSystemUserDao().store(userOne);
        DaoManager.getInstance(TEST_DB).getSystemUserDao().store(userZero);
        DaoManager.getInstance(TEST_DB).getSystemUserDao().store(userTwo);
        DaoManager.getInstance(TEST_DB).getMediumDao().store(bookOne);
        DaoManager.getInstance(TEST_DB).getMediumDao().store(bookZero);
        DaoManager.getInstance(TEST_DB).getMediumDao().store(bookTwo);
        DaoManager.getInstance(TEST_DB).getMediumExemplarDao()
                .store(bookOneCopy);
        DaoManager.getInstance(TEST_DB).getMediumExemplarDao()
                .store(bookZeroCopy);
        DaoManager.getInstance(TEST_DB).getMediumExemplarDao()
                .store(bookZeroCopy2);
        DaoManager.getInstance(TEST_DB).getMediumExemplarDao()
                .store(bookTwoCopy);
        DaoManager.getInstance(TEST_DB).getRentalDao().store(rental);
        DaoManager.getInstance(TEST_DB).getRentalDao().store(rental2);
        DaoManager.getInstance(TEST_DB).getReservationDao().store(reservation);

    }

    public static void resetDataBase()
    {
        clearDataBase();
        setUpDataBase();
    }

    private static void clearDataBase()
    {
        DaoManager.closeDbConnection();
        new File(TEST_DB).delete();
    }

    public static void main(String[] args)
    {
        resetDataBase();
    }

}
