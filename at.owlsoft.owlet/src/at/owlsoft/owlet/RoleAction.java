package at.owlsoft.owlet;

import org.apache.pivot.wtk.Action;

public abstract class RoleAction extends Action
{
    private String _role;

    public String getRole()
    {
        return _role;
    }

    public RoleAction(String role)
    {
        super();
        _role = role;
    }
}
