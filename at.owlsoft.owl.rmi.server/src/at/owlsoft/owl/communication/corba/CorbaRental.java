package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry;
import at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtension;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRentalPOA;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;

public class CorbaRental extends ICorbaRentalPOA
{

    @Override
    public long getEndDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getFilingExtensionCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ICorbaFilingExtension[] getFilingExtensions()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUUID()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getStartDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ICorbaActivityStatusEntry[] getActivityStatusEntries()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMediumExemplar getMediumExemplar()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getCustomer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getCreator()
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

}
