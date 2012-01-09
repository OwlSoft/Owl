package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.OwlContextBeanLocal;
import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class SystemUserApi
 */
@Stateless(mappedName = SystemUserApi.JNDI_NAME)
public class SystemUserApi implements SystemUserApiRemote
{
    @EJB
    private OwlContextBeanLocal _context;

    public OwlApplicationContext getContext()
    {
        return (OwlApplicationContext) _context.getContext();
    }

    public SystemUserApi() throws RemoteException
    {

    }

    @Override
    public void reset()
    {

    }

    @PostConstruct
    public void init()
    {
    }

    @Override
    public ISystemUser getSystemUserByCardID(int cardId)
    {
        ISystemUser user = getContext().getSystemUserSearchController().search(
                cardId);
        return user;
    }

}
