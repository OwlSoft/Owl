package at.owlsoft.owl.communication.rmi;

import java.rmi.RMISecurityManager;
import java.security.Permission;

public class AllPermissionSecurityManager extends RMISecurityManager
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
