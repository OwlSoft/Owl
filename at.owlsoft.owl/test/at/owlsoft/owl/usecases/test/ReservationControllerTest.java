package at.owlsoft.owl.usecases.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.RequiredFieldsNotFilledException;
import at.owlsoft.owl.usecases.ReservationController;

public class ReservationControllerTest
{

    private static ReservationController _reservationController;

    private static OwlApplicationContext _context;

    @BeforeClass
    public static void setup()
    {
        _context = new OwlApplicationContext();
        _reservationController = _context.getReservationController();
        _reservationController.newReservation();

    }

    @Test
    public void testNoFieldsFilled()
    {
        try
        {
            _reservationController.save();
        }
        catch (RequiredFieldsNotFilledException e)
        {
            Assert.assertTrue("RequiredFieldsNotFilledException thrown", true);
        }
        catch (Exception e)
        {
            Assert.assertFalse("Expected exception not thrown, " + e.getClass()
                    + " thrown instead", true);
        }
    }

    @Test
    public void testNoCustomerSet()
    {
        try
        {
            Medium book = new Book();
            _reservationController.setMedium(book);
            _reservationController.setReservationStartDate(new Date());
            _reservationController.save();
        }
        catch (RequiredFieldsNotFilledException e)
        {
            Assert.assertTrue("RequiredFieldsNotFilledException thrown", true);
        }
        catch (Exception e)
        {
            Assert.assertFalse("Expected Exception not thrown, " + e.getClass()
                    + " thrown instead", true);
        }
    }

    @Test
    public void testNoMediumSet()
    {
        try
        {
            SystemUser customer = new SystemUser();
            _reservationController.setCustomer(customer);
            _reservationController.setReservationStartDate(new Date());
            _reservationController.save();
        }
        catch (RequiredFieldsNotFilledException e)
        {
            Assert.assertTrue("RequiredFieldsNotFilledException thrown", true);
        }
        catch (Exception e)
        {
            Assert.assertFalse("Expected Exception not thrown, " + e.getClass()
                    + " thrown instead", true);
        }
    }

    @Test
    public void testNoDateSet()
    {
        try
        {
            SystemUser customer = new SystemUser();
            _reservationController.setCustomer(customer);
            Medium book = new Book();
            _reservationController.setMedium(book);
            _reservationController.save();
        }
        catch (Exception e)
        {
            Assert.assertFalse("Expected Exception not thrown, " + e.getClass()
                    + " thrown instead", true);
        }
    }

    @Test
    public void testAllFieldsSet()
    {
        try
        {
            SystemUser customer = new SystemUser();
            _reservationController.setCustomer(customer);

            Medium book = new Book();
            _reservationController.setMedium(book);

            MediumExemplar mex = new MediumExemplar(0, book);
            book.addMediumExemplar(mex);
            MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry(
                    new Date(), mex, MediumExemplarStatus.Rentable);
            mex.addMediumExemplarStatusEntry(mese);

            _reservationController.setReservationStartDate(new Date());
            _reservationController.save();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("No exception expected, but " + e.getClass()
                    + " (" + e.getMessage() + ") thrown", true);
        }
    }

    @Test
    public void testReservationSaved()
    {
        try
        {
            Medium book = new Book();
            _reservationController.setMedium(book);

            int reservationCount = book.getActivityCount();

            SystemUser customer = new SystemUser();
            _reservationController.setCustomer(customer);

            MediumExemplar mex = new MediumExemplar(0, book);
            book.addMediumExemplar(mex);
            MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry(
                    new Date(), mex, MediumExemplarStatus.Rentable);
            mex.addMediumExemplarStatusEntry(mese);

            _reservationController.setReservationStartDate(new Date());
            _reservationController.save();

            Assert.assertEquals(reservationCount, book.getActivityCount());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("No exception expected, but " + e.getClass()
                    + " (" + e.getMessage() + ") thrown", true);
        }

    }
}
