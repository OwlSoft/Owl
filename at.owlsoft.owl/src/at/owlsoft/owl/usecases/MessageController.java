package at.owlsoft.owl.usecases;

import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.IDefaultRoles;

public class MessageController extends ControllerBase
{
    public MessageController(OwlApplicationContext context)
    {
        super(context);
    }

    public List<IMessage> getOpenMessages() throws RemoteException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.OPERATOR);

    }

}