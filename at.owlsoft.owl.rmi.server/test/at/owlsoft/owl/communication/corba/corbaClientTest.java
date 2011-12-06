package at.owlsoft.owl.communication.corba;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import at.owlsoft.owl.corbamodel.user.ICorbaRole;
import at.owlsoft.owl.corbamodel.user.ICorbaRoleHelper;

public class corbaClientTest
{

    private ICorbaRole _role;

    @Before
    public void setup()
    {
        corbaTestServer.main(new String[0]);
    }

    @Test
    public void test()
    {
        try
        {
            String[] corbaArgs = new String[4];
            corbaArgs[0] = "-ORBInitialHost";
            corbaArgs[1] = "localhost";
            corbaArgs[2] = "-ORBInitialPort";
            corbaArgs[3] = "1049";

            ORB orb = ORB.init(corbaArgs, null);

            System.out.println("Client: ORB connected");

            org.omg.CORBA.Object ref = orb
                    .resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(ref);

            System.out.println("Client: ns fetched");

            String name = "Role";
            _role = ICorbaRoleHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Client: narrowed");

            Assert.assertNotNull(_role.getKey());
            Assert.assertNotNull(_role.getValue());
            Assert.assertFalse("Key is emptry", !(_role.getKey().equals("")));
            Assert.assertFalse("Value is emptry",
                    !(_role.getValue().equals("")));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("Exception " + e.getClass() + " thrown", true);
        }
    }

}
