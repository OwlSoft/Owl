package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.messaging.IMessage;

/**
 * This api allows access to messages which should be processed
 */
public interface IMessagingApi
{
    public List<IMessage> getOpenMessages() throws RemoteException;
}