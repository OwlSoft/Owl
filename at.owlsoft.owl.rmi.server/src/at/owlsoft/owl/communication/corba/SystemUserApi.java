package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;

public class SystemUserApi extends ICorbaSystemUserApiPOA
{

    private ApiFactory _factory;

    public SystemUserApi(ApiFactory apiFactory)
    {
        _factory = apiFactory;
    }

    @Override
    public ICorbaSystemUser getSystemUserByCardID(int cardId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
