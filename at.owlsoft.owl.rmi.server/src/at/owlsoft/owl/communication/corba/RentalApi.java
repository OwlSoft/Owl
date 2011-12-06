package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessage;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.ExtensionController;
import at.owlsoft.owl.usecases.RentalController;
import at.owlsoft.owl.usecases.ReturnController;

public class RentalApi extends ICorbaRentalApiPOA
{

    private RentalController    _controller;

    private ExtensionController _extensionController;
    private ReturnController    _returnController;

    private ApiFactory          _factory;

    public RentalApi(ApiFactory factory)
    {
        _controller = factory.getContext().getRentalController();
        _extensionController = factory.getContext().getExtensionController();
        _returnController = factory.getContext().getReturnController();
    }

    @Override
    public ICorbaSystemUser getRentalsForSystemUserCardId(int cardID)
    {
        SystemUser user = _factory.getContext().getSystemUserSearchController()
                .search(cardID);

        CorbaSystemUser cuser = new CorbaSystemUser();
        cuser.setRootPOA(_factory.getRootPOA());
        cuser.setUser(user);
        org.omg.CORBA.Object ref;
        try
        {
            ref = _factory.getRootPOA().servant_to_reference(
                    new CorbaSystemUser());
            return ICorbaSystemUserHelper.narrow(ref);
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
    public void newRental()
    {
        try
        {
            _controller.newRental();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaSystemUser setCustomer(int cardId)
    {
        SystemUser user = _factory.getContext().getSystemUserSearchController()
                .search(cardId);
        try
        {
            _controller.setCustomer(user);
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        CorbaSystemUser cuser = new CorbaSystemUser();
        cuser.setRootPOA(_factory.getRootPOA());
        cuser.setUser(user);
        org.omg.CORBA.Object ref;
        try
        {
            ref = _factory.getRootPOA().servant_to_reference(
                    new CorbaSystemUser());
            return ICorbaSystemUserHelper.narrow(ref);
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
    public ICorbaMediumExemplar setMediumExemplar(int exemplarId)
    {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public ICorbaValidationMessage[] getValidationMessages()
    {
        // TODO Auto-generated method stub
        CorbaValidationMessage copy = new CorbaValidationMessage();
        return null;
    }

    @Override
    public void createNewExtension(String uuid)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnRental(String uuid)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public ICorbaRental getRental()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStartDate(long time)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean store()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
