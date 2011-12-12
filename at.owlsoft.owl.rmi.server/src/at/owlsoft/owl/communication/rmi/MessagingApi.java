package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

/**
 * This api allows access to messages which should be processed
 */
public class MessagingApi extends ApiBase implements IMessagingApi
{
    private static final long serialVersionUID = -8053045096106258762L;

    public MessagingApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
    }

    @Override
    public List<IMessage> getOpenMessages() throws RemoteException
    {
        try
        {
            List<Message> messages = getFactory().getContext()
                    .getMessageController().getOpenMessages();
            return new ArrayList<IMessage>(messages);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void markMessage(UUID uid, MessageState state)
            throws RemoteException
    {
        try
        {
            getFactory().getContext().getMessageController()
                    .markMessage(uid, state);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
    }
}
