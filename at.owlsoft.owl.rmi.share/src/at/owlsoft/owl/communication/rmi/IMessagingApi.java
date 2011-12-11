package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.List;

/**
 * This api allows access to messages which should be processed
 */
public interface IMessagingApi
{
    public List<IMessage> getOpenMessages() throws RemoteException;
}