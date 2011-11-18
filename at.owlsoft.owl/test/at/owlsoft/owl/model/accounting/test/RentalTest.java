package at.owlsoft.owl.model.accounting.test;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.accounting.FilingExtension;
import at.owlsoft.owl.model.accounting.Rental;

public class RentalTest
{
    @Test
    public void testFilingExtensionRelation()
    {
        Rental rental = new Rental();

        FilingExtension extension = new FilingExtension();

        // Adding / Count
        rental.addFilingExtension(extension);
        Assert.assertEquals(1, rental.getFilingExtensionCount());
        Assert.assertEquals(extension, rental.getFilingExtension(0));
        Assert.assertEquals(rental.getFilingExtensions().get(0),
                rental.getFilingExtension(0));
        Assert.assertEquals(rental, extension.getRental());

        // Removing / Count
        rental.removeFilingExtension(extension);
        Assert.assertEquals(0, rental.getFilingExtensionCount());

        // Clear / Count
        rental.addFilingExtension(extension);
        rental.clearFilingExtensions();
        Assert.assertEquals(0, rental.getFilingExtensionCount());
    }
}
