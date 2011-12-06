package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntryPOA;
import at.owlsoft.owl.model.media.IMediumExemplarStatusEntry;

public class CorbaMediumExemplarStatusEntry extends
        ICorbaMediumExemplarStatusEntryPOA
{

    private IMediumExemplarStatusEntry _mediumExemplarStatusEntry;
    private POA                        _rootPOA;

    @Override
    public long getDate()
    {
        return _mediumExemplarStatusEntry.getDate().getTime();
    }

    @Override
    public ICorbaMediumExemplar getMediumExemplar()
    {
        try
        {
            CorbaMediumExemplare cMediumExemplar = new CorbaMediumExemplare();
            cMediumExemplar.setMediumExemplare(_mediumExemplarStatusEntry
                    .getMediumExemplar());
            cMediumExemplar.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref;
            ref = _rootPOA.servant_to_reference(cMediumExemplar);
            return ICorbaMediumExemplarHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public CorbaMediumExemplarStatus getMediumExemplarStatus()
    {
        switch (_mediumExemplarStatusEntry.getMediumExemplarStatus())
        {
            case Rentable:
                return CorbaMediumExemplarStatus.Rentable;
            case Rented:
                return CorbaMediumExemplarStatus.Rented;
            case Returned:
                return CorbaMediumExemplarStatus.Returned;
            case StockItem:
                return CorbaMediumExemplarStatus.StockItem;
            default:
                throw new RuntimeException("Unknown CorbaMediumExemplarStatus");
        }
    }

    public void setMediumExemplarStatusEntry(IMediumExemplarStatusEntry mse)
    {
        _mediumExemplarStatusEntry = mse;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

}
