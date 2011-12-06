package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;

import at.owlsoft.owl.corbamodel.user.ICorbaRolePOA;
import at.owlsoft.owl.model.user.IRole;

public class CorbaRole extends ICorbaRolePOA
{

    private IRole _role;
    private POA   _rootPoa;

    public void setRole(IRole role)
    {
        _role = role;
    }

    @Override
    public String getKey()
    {
        // TODO Auto-generated method stub
        return _role.getKey();
    }

    @Override
    public String getValue()
    {
        // TODO Auto-generated method stub
        return _role.getLabel();
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPoa = rootPOA;

    }

}
