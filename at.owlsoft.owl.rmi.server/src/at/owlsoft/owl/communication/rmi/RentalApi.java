package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.UUID;

import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.usecases.ExtensionController;
import at.owlsoft.owl.usecases.ReturnController;

public class RentalApi extends ApiBase implements IRentalApi
{
    private ExtensionController _extensionController;
    private ReturnController    _returnController;

    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);

        _extensionController = factory.getContext().getExtensionController();
        _returnController = factory.getContext().getReturnController();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3430317522763589741L;

    @Override
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
    {
        return getFactory().getContext().getSystemUserSearchController()
                .search(cardId);
    }

    @Override
    public void createNewExtension(UUID uuid)
    {
        _extensionController.extend(uuid);

    }

    @Override
    public void returnRental(UUID uuid)
    {
        _returnController.returnMediumCopy(uuid);
    }
}
