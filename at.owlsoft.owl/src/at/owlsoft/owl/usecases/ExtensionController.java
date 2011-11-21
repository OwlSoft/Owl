package at.owlsoft.owl.usecases;

import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.FilingExtension;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;

public class ExtensionController
{

    public void extend(MediumExemplar copy) throws ExtensionImpossibleException
    {
        Rental rental = copy.getLastRental();

        validate();

        FilingExtension fex = new FilingExtension();
        fex.setCreationDate(new Date());

        Date endDate = new Date(rental.getEndDate().getTime()
                + getExtensionPeriode());
        fex.setNewEndDate(endDate);
        fex.setRental(rental);

        rental.addFilingExtension(fex);

        DaoManager.getInstance().getRentalDao().store(rental);

    }

    private long getExtensionPeriode()
    {
        // TODO Read from config.
        return 0;
    }

    private void validate() throws ExtensionImpossibleException
    {
        // TODO Auto-generated method stub

    }

}
