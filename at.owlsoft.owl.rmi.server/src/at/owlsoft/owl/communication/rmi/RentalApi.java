package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.UUID;

import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.usecases.ExtensionController;

public class RentalApi extends ApiBase implements IRentalApi
{
    private ExtensionController _extensionController;

    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);

        _extensionController = factory.getContext().getExtensionController();
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
}
