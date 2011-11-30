package at.owlsoft.owl.business.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.business.OwlApplicationContext;

public class LdapUserAuthControllerTest
{

    private static AuthenticationController _authenticationController;
    private static OwlApplicationContext  _context;

    @Before
    public void setup()
    {
        _context = new OwlApplicationContext();
        _authenticationController = _context.getLdapUserAuthController();
    }

    @Test
    public void testCheckLdapAuth()
    {
        // Use you own data, you won't get mine.
        String userName = "";
        String password = "";

        boolean result = _authenticationController.CheckLdapAuth(userName,
                password);

        Assert.assertTrue("User " + userName
                + "was found, password is correct.", result);
    }

    @Test
    public void testCheckLdapAuthFalseData()
    {
        String userName = "nisi";
        String password = "wrongpasswort";

        boolean result = _authenticationController.CheckLdapAuth(userName,
                password);

        Assert.assertFalse("User/pw combination was invalid", result);
    }
}
