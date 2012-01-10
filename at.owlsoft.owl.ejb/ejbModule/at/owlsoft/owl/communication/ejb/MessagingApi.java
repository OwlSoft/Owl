package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

/**
 * Session Bean implementation class MessagingApi
 */
@Stateful(mappedName = MessagingApiRemote.JNDI_NAME)
public class MessagingApi extends ApiBase implements MessagingApiRemote,
        MessagingApiLocal
{

    public MessagingApi()
    {
    }

    @PostConstruct
    public void init()
    {

    }

    @Override
    public List<IMessage> getOpenMessages() throws NoPermissionException
    {
        List<Message> messages = getContext().getMessageController()
                .getOpenMessages();
        return new ArrayList<IMessage>(messages);
    }

    @Override
    public void markMessage(UUID uid, MessageState state)
            throws NoPermissionException
    {
        getContext().getMessageController().markMessage(uid, state);
    }

    @Override
    public void startListening()
    {
        getContext().getMessageController().startListening();
    }

    @Resource
    private SessionContext _session;

    @Override
    public MessagingApiRemote getRemoteObject()
    {
        return _session.getBusinessObject(MessagingApiRemote.class);

    }
}
