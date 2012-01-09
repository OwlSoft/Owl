package at.owlsoft.owl.communication.ejb;

import javax.ejb.Remote;

import at.owlsoft.owl.model.user.ISystemUser;

@Remote
public interface SystemUserApiRemote extends IApiBase
{
    ISystemUser getSystemUserByCardID(int cardId);
}
