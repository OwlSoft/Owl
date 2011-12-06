package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumPOA;
import at.owlsoft.owl.corbamodel.media.ITag;
import at.owlsoft.owl.corbamodel.media.ITagHelper;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.IMediumExemplar;

public class CorbaMedium extends ICorbaMediumPOA
{

    private IMedium _medium;
    private POA     _rootPOA;

    @Override
    public int getMediumID()
    {
        return _medium.getMediumID();
    }

    @Override
    public String getPublisher()
    {
        return _medium.getPublisher();
    }

    @Override
    public String getName()
    {
        return _medium.getName();
    }

    @Override
    public long getEntryDate()
    {
        return _medium.getEntryDate().getTime();
    }

    @Override
    public long getPublishedDate()
    {
        return _medium.getPublishedDate().getTime();
    }

    @Override
    public ITag[] getTags()
    {
        try
        {
            ITag[] temp = new ITag[_medium.getTags().size()];
            int index = 0;
            for (at.owlsoft.owl.model.media.ITag tag : _medium.getTags())
            {
                CorbaTag corbaTag = new CorbaTag();
                corbaTag.setTag(tag);
                corbaTag.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(corbaTag);
                temp[index] = ITagHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaMediumExemplar[] getMediumExemplars()
    {
        try
        {
            ICorbaMediumExemplar[] temp = new ICorbaMediumExemplar[_medium
                    .getMediumExemplars().size()];
            int index = 0;
            for (IMediumExemplar medium : _medium.getMediumExemplars())
            {
                CorbaMediumExemplare cMedium = new CorbaMediumExemplare();
                cMedium.setMediumExemplare(medium);
                cMedium.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cMedium);
                temp[index] = ICorbaMediumExemplarHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaActivity[] getActivities()
    {
        try
        {
            ICorbaActivity[] temp = new ICorbaActivity[_medium.getActivities()
                    .size()];
            int index = 0;
            for (IActivity activity : _medium.getActivities())
            {
                CorbaActivity cactivity = new CorbaActivity();
                cactivity.setActivity(activity);
                cactivity.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cactivity);
                temp[index] = ICorbaActivityHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setMedium(IMedium medium)
    {
        _medium = medium;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

}
