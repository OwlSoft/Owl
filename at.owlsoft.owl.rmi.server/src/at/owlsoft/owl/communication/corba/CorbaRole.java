package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.user.ICorbaRolePOA;
import at.owlsoft.owl.model.user.IRole;

public class CorbaRole extends ICorbaRolePOA
{

    private IRole _role;

    public void setRole(IRole role)
    {
        _role = role;
    }

    @Override
    public String getKey()
    {
        // TODO Auto-generated method stub
        return "blub";
    }

    @Override
    public String getValue()
    {
        // TODO Auto-generated method stub
        return "gurgel";
    }

}
