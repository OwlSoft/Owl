package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;

// FIXME: A exemplar return should get handled in the rental controller
public class ReturnController extends ControllerBase
{

    private List<ValidationMessage> _messages;

    public ReturnController(OwlApplicationContext context)
    {
        super(context);
    }

    public List<ValidationMessage> returnMediumCopy(MediumExemplar copy)
    {
        Rental rental = copy.getLastRental();

        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setDate(new Date());
        ase.setActivityStatus(ActivityStatus.Returned);
        rental.addActivityStatusEntry(ase);

        DaoManager.getInstance().getActivityDao().store(rental);

        MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry();
        mese.setDate(new Date());
        mese.setMediumExemplarStatus(MediumExemplarStatus.Returned);
        mese.setMediumExemplar(copy);

        copy.addMediumExemplarStatusEntry(mese);

        DaoManager.getInstance().getMediumExemplarDao().store(copy);

        return _messages;
    }

    /**
     * Searches the rental with the UUID and returns the medium
     * 
     * @param uuid Rental UUID
     */
    public List<ValidationMessage> returnMediumCopy(UUID uuid)
    {
        List<ValidationMessage> temp = new ArrayList<ValidationMessage>();

        List<SearchField> searchFields = new ArrayList<SearchField>();
        searchFields.add(new SearchField("UUID", uuid.toString(),
                SearchFieldType.Equals));

        List<Rental> rentals = getContext().getRentalSearchController().search(
                searchFields);

        if (rentals == null || rentals.isEmpty())
        {
            temp.add(new ValidationMessage("No rental found.",
                    ValidationMessageStatus.Error));
        }
        else if (rentals.size() > 1)
        {
            temp.add(new ValidationMessage("To many rentals found.",
                    ValidationMessageStatus.Error));
        }
        else
        {
            temp = returnMediumCopy(rentals.get(0).getMediumExemplar());
        }

        return temp;
    }

}
