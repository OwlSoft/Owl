/*
 * This file is part of OwlSoft Owl.
 *
 *  OwlSoft Owl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  alphaTab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with alphaTab.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.owlsoft.owl.communication.corba;

import org.apache.log4j.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.communication.rmi.AllPermissionSecurityManager;

public class CorbaServer
{

    private static final String LOG4J_CONFIGURATION = "/log4j.xml";
    private static final Logger logger              = Logger.getLogger(CorbaApiService.class);

    public static void main(String[] args)
    {
        System.setSecurityManager(new AllPermissionSecurityManager());

        // try
        // {
        // DOMConfigurator.configure(Owl.class
        // .getResource(LOG4J_CONFIGURATION));
        //
        // logger.info("Starting service");
        // ApiService.startRmiService("localhost", Registry.REGISTRY_PORT,
        // IApiService.DEFAULT_RMI_NAME);
        //
        // logger.info("Service registered");
        // }
        // catch (Exception e)
        // {
        // logger.error("Could not start service", e);
        // }

        // String[] corbaArgs = new String[4];
        // corbaArgs[0] = "-ORBInitialHost";
        // corbaArgs[1] = "localhost";
        // corbaArgs[2] = "-ORBInitialPort";
        // corbaArgs[3] = "1050";

        ORB orb = ORB.init(new String[0], null);
        try
        {
            POA rootPoa = POAHelper.narrow(orb
                    .resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            // get the root naming context
            org.omg.CORBA.Object objRef = orb
                    .resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            CorbaApiService apiService = new CorbaApiService(rootPoa);
            apiService.setOrb(orb);

            org.omg.CORBA.Object reference = rootPoa
                    .servant_to_reference(apiService);
            ICorbaApiService href = ICorbaApiServiceHelper.narrow(reference);

            String name = "ApiService";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            orb.run();
        }
        catch (InvalidName e)
        {
            logger.fatal("Could not fetch root POA or Name Service from ORB; is orbd running?");
            e.printStackTrace();
        }
        catch (ServantNotActive e)
        {
            logger.fatal("Servant not active");
            e.printStackTrace();
        }
        catch (WrongPolicy e)
        {
            logger.fatal("Wrong Policy");
            e.printStackTrace();
        }
        catch (org.omg.CosNaming.NamingContextPackage.InvalidName e)
        {
            logger.fatal("Invalid name; Object could not be bound");
            e.printStackTrace();
        }
        catch (NotFound e)
        {
            logger.fatal("Not found; rebind in Name Service failed");
            e.printStackTrace();
        }
        catch (CannotProceed e)
        {
            logger.fatal("Cannot proceed; rebind in Name Service failed");
            e.printStackTrace();
        }
        catch (AdapterInactive e)
        {
            logger.fatal("RootPOA inactive.");
            e.printStackTrace();
        }
    }
}
