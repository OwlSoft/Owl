package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;

import at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntryPOA;
import at.owlsoft.owl.model.accounting.IActivityStatusEntry;

public class CorbaActivityStatusEntry extends ICorbaActivityStatusEntryPOA
{

    private POA                  _rootPoa;
    private IActivityStatusEntry _statusEntry;

    private void setRootPOA(POA rootPOA)
    {
        _rootPoa = rootPOA;
    }

    private void setActivityStatusEntry(IActivityStatusEntry statusEntry)
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
        _activity.getCreator();
        return null;
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
