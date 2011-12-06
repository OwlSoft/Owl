package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryPOA;
import at.owlsoft.owl.model.user.ISystemUserStatusEntry;

public class CorbaSystemUserStatusEntry extends ICorbaSystemUserStatusEntryPOA
{

    private ISystemUserStatusEntry _statusEntry;

    public void setStatusEntry(ISystemUserStatusEntry statusEntry)
    {
        _statusEntry = statusEntry;
    }

    @Override
    public long getDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getComment()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CorbaSystemUserStatus getSystemUserStatus()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getSystemUser()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
