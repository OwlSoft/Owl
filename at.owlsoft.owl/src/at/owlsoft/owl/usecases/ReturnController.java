package at.owlsoft.owl.usecases;

import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;

public class ReturnController
{

    public void returnMediumCopy(MediumExemplar copy)
    {
        Rental rental = copy.getLastRental();

        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setDate(new Date());
        ase.setActivityStatus(ActivityStatus.Returned);
        rental.addActivityStatusEntry(ase);
        DaoManager.getDb4ODaoInstance().getActivityDao().store(rental);

        MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry();
        mese.setDate(new Date());
        mese.setMediumExemplarStatus(MediumExemplarStatus.Returned);
        mese.setMediumExemplar(copy);
        copy.addMediumExemplarStatusEntry(mese);
        DaoManager.getDb4ODaoInstance().getMediumExemplarDao().store(copy);
    }

}
