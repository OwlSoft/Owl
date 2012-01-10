package at.owlsoft.owl.communication.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class SystemUserApi
 */
@Stateless(mappedName = SystemUserApi.JNDI_NAME)
public class SystemUserApi extends ApiBase implements SystemUserApiRemote,
        SystemUserApiLocal
{

    public SystemUserApi()
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

    @Resource
    private SessionContext _session;

    @Override
    public SystemUserApiRemote getRemoteObject()
    {
        return _session.getBusinessObject(SystemUserApiRemote.class);

    }
}
