package at.owlsoft.owl.usecases;

import java.util.Date;
import java.util.UUID;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;

// FIXME: A exemplar return should get handled in the rentalcontroller
public class ReturnController extends ControllerBase
{

    public ReturnController(OwlApplicationContext context)
    {
        super(context);
    }

    public void returnMediumCopy(MediumExemplar copy)
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
    }

    /**
     * Searches the rental with the UUID and returns the medium
     * 
     * @param uuid Rental UUID
     */
    public void returnMediumCopy(UUID uuid)
    {
        // TODO Auto-generated method stub

    }

}
