package at.owlsoft.owl.communication.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import at.owlsoft.owl.corbamodel.user.ICorbaRole;
import at.owlsoft.owl.corbamodel.user.ICorbaRoleHelper;
import at.owlsoft.owl.model.user.Role;

public class corbaTestServer
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        // Need to start ORB daemon beforehand
        // start orbd -port 1049 -ORBInitialPort 1050

        String[] corbaArgs = new String[4];
        corbaArgs[0] = "-ORBInitialHost";
        corbaArgs[1] = "localhost";
        corbaArgs[2] = "-ORBInitialPort";
        corbaArgs[3] = "900";

        ORB orb = ORB.init(corbaArgs, null);
        try
        {
            POA rootPoa = POAHelper.narrow(orb
                    .resolve_initial_references("RootPOA"));

            System.out.println("Root POA fetched");

            // get the root naming context
            org.omg.CORBA.Object objRef = orb
                    .resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            CorbaRole cRole = new CorbaRole();
            cRole.setRole(new Role("Bob", "Dole"));

            System.out.println("Role created");

            org.omg.CORBA.Object reference = rootPoa
                    .servant_to_reference(cRole);
            ICorbaRole href = ICorbaRoleHelper.narrow(reference);

            System.out.println("Role object for naming service created");

            String name = "Role";
            NameComponent path[] = ncRef.to_name(name);

            System.out.println("Name for Role constructed");

            ncRef.rebind(path, href);

            System.out.println("Role in naming service");

            orb.run();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
