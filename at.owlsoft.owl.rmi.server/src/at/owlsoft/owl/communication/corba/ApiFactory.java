package at.owlsoft.owl.communication.corba;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.business.OwlApplicationContext;

public class ApiFactory extends ICorbaApiFactoryPOA
{

    private OwlApplicationContext _context;
    private POA                   _rootPOA;

    public ApiFactory()
    {
        super();
        _context = new OwlApplicationContext();
    }

    public OwlApplicationContext getContext()
    {
        return _context;
    }

    @Override
    public ICorbaExtendApi createExtendApi()
    {
        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new ExtendApi());
            return ICorbaExtendApiHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaRentalApi createRentalApi()
    {
        ORB orb = ORB.init();
        _rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        _rootPOA.the_POAManager().activate();

        org.omg.CORBA.Object ref = _rootPOA
                .servant_to_reference(new RentalApi());
        return ICorbaRentalApiHelper.narrow(ref);
    }

    @Override
    public ICorbaReservationApi createReservationApi()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaReturnApi createReturnApi()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUserApi createSystemUserApi()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaAuthenticationApi createAuthenticationApi()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
