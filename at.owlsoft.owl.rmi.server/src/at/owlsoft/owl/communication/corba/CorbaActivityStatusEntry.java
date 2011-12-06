package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityHelper;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntryPOA;
import at.owlsoft.owl.model.accounting.IActivityStatusEntry;

public class CorbaActivityStatusEntry extends ICorbaActivityStatusEntryPOA
{

    private POA                  _rootPOA;
    private IActivityStatusEntry _statusEntry;

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    public void setActivityStatusEntry(IActivityStatusEntry statusEntry)
    {
        _statusEntry = statusEntry;
    }

    @Override
    public long getDate()
    {
        // TODO Auto-generated method stub
        return _statusEntry.getDate().getTime();
    }

    @Override
    public ICorbaActivity getActivity()
    {
        try
        {
            CorbaActivity activity = new CorbaActivity();
            activity.setActivity(_statusEntry.getActivity());
            activity.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(activity);
            return ICorbaActivityHelper.narrow(ref);
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
    public CorbaActivityStatus getActivityStatus()
    {
        switch (_statusEntry.getActivityStatus())
        {
            case Closed:
                return CorbaActivityStatus.Closed;
            case Extended:
                return CorbaActivityStatus.Extended;
            case Open:
                return CorbaActivityStatus.Open;
            case Overdue:
                return CorbaActivityStatus.Overdue;
            case Returned:
                return CorbaActivityStatus.Returned;
            default:
                throw new RuntimeException("Activity Status not known.");
        }
    }

}
