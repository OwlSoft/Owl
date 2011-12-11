package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.MessageState;

/**
 * This api allows access to messages which should be processed
 */
public interface IMessagingApi
{
    public List<IMessage> getOpenMessages() throws RemoteException;

    public void markMessage(UUID uid, MessageState state)
            throws RemoteException;
}