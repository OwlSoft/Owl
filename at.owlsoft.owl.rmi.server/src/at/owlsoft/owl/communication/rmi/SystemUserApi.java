package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

import at.owlsoft.owl.business.SystemUserSearchController;
import at.owlsoft.owl.model.user.ISystemUser;

public class SystemUserApi extends ApiBase implements ISystemUserApi
{
    private SystemUserSearchController _userSearch;

    /**
     * 
     */
    private static final long          serialVersionUID = 4965043377325784533L;

    public SystemUserApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _userSearch = factory.getContext().getSystemUserSearchController();
    }

    @Override
    public ISystemUser getSystemUserByCardID(int cardId) throws Exception
    {
        ISystemUser user = _userSearch.search(cardId);
        return user;
    }

}
