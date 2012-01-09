package at.owlsoft.owl.communication.ejb;

import javax.ejb.Remote;

import at.owlsoft.owl.model.user.ISystemUser;

@Remote
public interface SystemUserApiRemote extends IApiBase
{
    public static final String JNDI_NAME = "owl/SystemUserApiRemote";

    ISystemUser getSystemUserByCardID(int cardId);
}
