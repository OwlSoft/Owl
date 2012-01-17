package at.owlsoft.owl.communication.ejb;

import java.rmi.RMISecurityManager;
import java.security.Permission;

public class EJBAllPermissionSecurityManager extends RMISecurityManager
{
    @Override
    public void checkPermission(Permission perm)
    {
    }

    @Override
    public void checkPermission(Permission perm, Object context)
    {
    }
}
