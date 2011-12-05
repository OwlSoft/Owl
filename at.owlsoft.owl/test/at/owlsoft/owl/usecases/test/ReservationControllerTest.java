package at.owlsoft.owl.usecases.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import at.owlsoft.owl.OwlTestSuite;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.ReservationController;

public class ReservationControllerTest extends OwlTestSuite
{

    @Test
    public void testNoFieldsFilled()
    {
        try
        {
            ReservationController reservationController = getContext()
                    .getReservationController();
            reservationController.newReservation();
            reservationController.save();
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
            ReservationController reservationController = getContext()
                    .getReservationController();

            reservationController.newReservation();

            Medium book = new Book();
            reservationController.setMedium(book);
            // _reservationController.setReservationStartDate(new Date());
            reservationController.save();
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
            ReservationController reservationController = getContext()
                    .getReservationController();

            reservationController.newReservation();

            SystemUser customer = new SystemUser();
            reservationController.setCustomer(customer);
            // _reservationController.setReservationStartDate(new Date());
            reservationController.save();
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
            ReservationController reservationController = getContext()
                    .getReservationController();

            reservationController.newReservation();

            SystemUser customer = new SystemUser();
            reservationController.setCustomer(customer);
            Medium book = new Book();
            reservationController.setMedium(book);
            reservationController.save();
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
            ReservationController reservationController = getContext()
                    .getReservationController();

            SystemUser customer = new SystemUser();
            reservationController.setCustomer(customer);

            Medium book = new Book();
            reservationController.setMedium(book);

            MediumExemplar mex = new MediumExemplar(0, book);
            book.addMediumExemplar(mex);
            MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry(
                    new Date(), mex, MediumExemplarStatus.Rentable);
            mex.addMediumExemplarStatusEntry(mese);

            // _reservationController.setReservationStartDate(new Date());
            reservationController.save();
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
            ReservationController reservationController = getContext()
                    .getReservationController();
            reservationController.newReservation();

            Medium book = new Book();
            reservationController.setMedium(book);

            int reservationCount = book.getActivityCount();

            SystemUser customer = new SystemUser();
            reservationController.setCustomer(customer);

            MediumExemplar mex = new MediumExemplar(0, book);
            book.addMediumExemplar(mex);
            MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry(
                    new Date(), mex, MediumExemplarStatus.Rentable);
            mex.addMediumExemplarStatusEntry(mese);

            // _reservationController.setReservationStartDate(new Date());
            reservationController.save();

            Assert.assertEquals(reservationCount + 1, book.getActivityCount());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("No exception expected, but " + e.getClass()
                    + " (" + e.getMessage() + ") thrown", true);
        }

    }
}
