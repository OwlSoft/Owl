package at.owlsoft.owl.business;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.SystemUser;

public interface IOwlSecurityManager
{
    void checkAccess(String role, SystemUser user) throws NoPermissionException;
}
