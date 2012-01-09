package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.OwlContextBeanLocal;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

/**
 * Session Bean implementation class MessagingApi
 */
@Stateful(mappedName = MessagingApiRemote.JNDI_NAME)
public class MessagingApi implements MessagingApiRemote
{
    @EJB
    private OwlContextBeanLocal _context;

    public OwlApplicationContext getContext()
    {
        return (OwlApplicationContext) _context.getContext();
    }

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
}
