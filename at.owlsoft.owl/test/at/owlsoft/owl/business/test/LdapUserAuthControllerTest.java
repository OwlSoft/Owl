package at.owlsoft.owl.business.test;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.owlsoft.owl.business.LdapUserAuthController;
import at.owlsoft.owl.business.OwlApplicationContext;

public class LdapUserAuthControllerTest
{

    private static LdapUserAuthController _ldapUserAuthController;
    private static OwlApplicationContext  _context;

    @Before
    public void setup()
    {
        _context = new OwlApplicationContext();
        _ldapUserAuthController = _context.getLdapUserAuthController();
    }

    @Test
    public void testCheckLdapAuth()
    {
        String userName = "nisi";
        String password = "passwort";

        try
        {
            boolean result = _ldapUserAuthController.CheckLdapAuth(userName,
                    password);

            Assert.assertTrue("User " + userName
                    + "was found, password is correct.", result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("Exception thrown while looking for user "
                    + userName, true);
        }
    }

    @Test
    public void testCheckLdapAuthFalseData()
    {
        String userName = "nisi";
        String password = "wrongpasswort";

        try
        {
            boolean result = _ldapUserAuthController.CheckLdapAuth(userName,
                    password);

            Assert.assertFalse("User " + userName
                    + "was found, password is correct.", result);
        }
        catch (NamingException e)
        {
            Assert.assertTrue("User/pw combination is wrong", true);
        }
        catch (Exception e)
        {
            Assert.assertTrue("Unintended Exception " + e.getClass()
                    + " thrown", true);
        }

    }
}
