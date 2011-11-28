package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.FilingExtension;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;

public class ExtensionController extends ControllerBase
{

    public ExtensionController(OwlApplicationContext context)
    {
        super(context);
    }

    private List<ValidationMessage> _messages;

    public List<ValidationMessage> extend(MediumExemplar copy)
    {
        Rental rental = copy.getLastRental();

        if (validate(rental))
        {

            FilingExtension fex = new FilingExtension();
            fex.setCreationDate(new Date());

            Date endDate = new Date(rental.getEndDate().getTime()
                    + getExtensionPeriode());
            fex.setNewEndDate(endDate);
            fex.setRental(rental);

            rental.addFilingExtension(fex);

            DaoManager.getInstance().getRentalDao().store(rental);
        }

        return _messages;

    }

    private long getExtensionPeriode()
    {
        // TODO Read extension periode from config.
        return 7;
    }

    private boolean validate(Rental rental)
    {

        boolean hasNoError = true;

        _messages = new ArrayList<ValidationMessage>();
        // TODO Read from config maximum FilingExtension number
        int maxExtensions = 3;

        if (rental.getFilingExtensionCount() >= maxExtensions)
        {
            _messages.add(new ValidationMessage("Maximum extensions reached.",
                    ValidationMessageStatus.Error));
            hasNoError = false;
        }

        if (rental.getCustomer().getLastSystemUserStatusEntry()
                .getSystemUserStatus() != SystemUserStatus.Active)
        {
            _messages.add(new ValidationMessage("Customer not active.",
                    ValidationMessageStatus.Warning));
        }

        return hasNoError;

    }

}
