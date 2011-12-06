package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.rmi.AuthenticationApi;
import at.owlsoft.owl.communication.rmi.ReservationApi;
import at.owlsoft.owl.communication.rmi.ReturnApi;
import at.owlsoft.owl.communication.rmi.SystemUserApi;

public class ApiFactory extends ICorbaApiFactoryPOA
{

    private OwlApplicationContext _context;
    private POA                   _rootPOA;

    public ApiFactory()
    {
        super();
        _context = new OwlApplicationContext();
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    public POA getRootPOA()
    {
        return _rootPOA;
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

        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new RentalApi(this));
            return ICorbaRentalApiHelper.narrow(ref);
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaReservationApi createReservationApi()
    {
        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new ReservationApi(this));
            return ICorbaReservationApiHelper.narrow(ref);
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
    public ICorbaReturnApi createReturnApi()
    {
        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new ReturnApi(this));
            return ICorbaReturnApiHelper.narrow(ref);
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
    public ICorbaSystemUserApi createSystemUserApi()
    {
        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new SystemUserApi(this));
            return ICorbaSystemUserApiHelper.narrow(ref);
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
    public ICorbaAuthenticationApi createAuthenticationApi()
    {
        try
        {
            org.omg.CORBA.Object ref = _rootPOA
                    .servant_to_reference(new AuthenticationApi(this));
            return ICorbaAuthenticationApiHelper.narrow(ref);
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

}
