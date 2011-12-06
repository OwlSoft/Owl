package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryPOA;
import at.owlsoft.owl.model.user.ISystemUserStatusEntry;

public class CorbaSystemUserStatusEntry extends ICorbaSystemUserStatusEntryPOA
{

    private ISystemUserStatusEntry _statusEntry;
    private POA                    _rootPOA;

    public void setStatusEntry(ISystemUserStatusEntry statusEntry)
    {
        _statusEntry = statusEntry;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public long getDate()
    {
        return _statusEntry.getDate().getTime();
    }

    @Override
    public String getComment()
    {
        return _statusEntry.getComment();
    }

    @Override
    public CorbaSystemUserStatus getSystemUserStatus()
    {
        switch (_statusEntry.getSystemUserStatus())
        {
            case Active:
                return CorbaSystemUserStatus.Active;
            case InactiveBecauseMembershipFee:
                return CorbaSystemUserStatus.InactiveBecauseMembershipFee;
            case InactiveBecauseOverdue:
                return CorbaSystemUserStatus.InactiveBecauseOverdue;
            default:
                throw new RuntimeException("SystemUserStatus not known.");
        }
    }

    @Override
    public ICorbaSystemUser getSystemUser()
    {
        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setUser(_statusEntry.getSystemUser());
            cuser.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cuser);
            return ICorbaSystemUserHelper.narrow(ref);
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
}
