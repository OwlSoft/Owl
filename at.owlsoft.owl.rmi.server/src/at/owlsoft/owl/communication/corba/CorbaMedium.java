package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumPOA;
import at.owlsoft.owl.corbamodel.media.ITag;

public class CorbaMedium extends ICorbaMediumPOA
{

    @Override
    public int getMediumID()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getPublisher()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getEntryDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getPublishedDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ITag[] getTags()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMediumExemplar[] getMediumExemplars()
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

}
