package at.owlsoft.owl.communication.ejb;

import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.MessageState;

@Remote
public interface MessagingApiRemote extends IApiBase
{
    public static final String JNDI_NAME = "owl/MessagingApiRemote";

    public List<IMessage> getOpenMessages() throws NoPermissionException;

    public void markMessage(UUID uid, MessageState state)
            throws NoPermissionException;

    public void startListening();
}
