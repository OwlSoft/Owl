package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

import at.owlsoft.owl.business.GlobalContextController;
import at.owlsoft.owl.model.user.ISystemUser;

public class RentalApi extends ApiBase implements IRentalApi
{
    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);

    }

    /**
     * 
     */
    private static final long serialVersionUID = 3430317522763589741L;

    @Override
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
    {
        return GlobalContextController.getSystemUserSearchController().search(
                cardId);
    }
}
