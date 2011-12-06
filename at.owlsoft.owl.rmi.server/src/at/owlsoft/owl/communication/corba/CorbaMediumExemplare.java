package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntry;

public class CorbaMediumExemplare extends ICorbaMediumExemplarPOA
{

    @Override
    public int getExemplarID()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getMetaData(String key)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getMetaDataKeys()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMedium getMedium()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMediumExemplarStatusEntry[] getMediumExemplarStatusEntries()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaActivity[] getActivities()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaRental getLastRental()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CorbaMediumExemplarStatus getCurrentState()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
