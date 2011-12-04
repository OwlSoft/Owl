package at.owlsoft.owl.business.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.owlsoft.owl.OwlTestSuite;
import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.model.user.ISystemUser;

public class LdapUserAuthControllerTest extends OwlTestSuite
{

    private static AuthenticationController _authenticationController;

    @Override
    @Before
    public void setup()
    {
        super.setup();
        _authenticationController = getContext().getAuthenticationController();
    }

    @Test
    public void testCheckLdapAuth()
    {
        // Use you own data, you won't get mine.
        String userName = "";
        String password = "";

        ISystemUser result = _authenticationController
                .login(userName, password);

        Assert.assertTrue("User " + userName
                + "was found, password is correct.", result != null);
    }

    @Test
    public void testCheckLdapAuthFalseData()
    {
        String userName = "nisi";
        String password = "wrongpasswort";

        ISystemUser result = _authenticationController
                .login(userName, password);

        Assert.assertFalse("User/pw combination was invalid", result == null);
    }
}
