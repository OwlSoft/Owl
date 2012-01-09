package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

/**
 * Session Bean implementation class MessagingApi
 */
@Stateful
public class MessagingApi implements MessagingApiRemote
{
    @EJB
    private OwlContextBean _context;

    public MessagingApi()
    {
    }

    @Override
    public List<IMessage> getOpenMessages() throws NoPermissionException
    {
        List<Message> messages = _context.getContext().getMessageController()
                .getOpenMessages();
        return new ArrayList<IMessage>(messages);
    }

    @Override
    public void markMessage(UUID uid, MessageState state)
            throws NoPermissionException
    {
        _context.getContext().getMessageController().markMessage(uid, state);
    }
}
