package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.owlsoft.owl.business.SystemUserSearchController;
import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class SystemUserApi
 */
@Stateless
public class SystemUserApi implements SystemUserApiRemote
{
    @EJB
    private OwlContextBean             _context;

    private SystemUserSearchController _userSearch;

    public SystemUserApi() throws RemoteException
    {
        _userSearch = _context.getContext().getSystemUserSearchController();
    }

    @Override
    public void reset()
    {

    }

    @Override
    public ISystemUser getSystemUserByCardID(int cardId)
    {
        ISystemUser user = _userSearch.search(cardId);
        return user;
    }

}
