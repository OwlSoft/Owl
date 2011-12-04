package at.owlsoft.owl;

import org.junit.Before;

import at.owlsoft.owl.business.IOwlSecurityManager;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.SystemUser;

public class OwlTestSuite
{
    private OwlApplicationContext _context;

    public OwlApplicationContext getContext()
    {
        return _context;
    }

    @Before
    public void setup()
    {
        _context = new OwlApplicationContext();
        _context.getAuthenticationController().setSecurityManager(
                new IOwlSecurityManager()
                {

                    @Override
                    public void checkAccess(String role, SystemUser user)
                            throws NoPermissionException
                    {
                    }
                });
    }
}
