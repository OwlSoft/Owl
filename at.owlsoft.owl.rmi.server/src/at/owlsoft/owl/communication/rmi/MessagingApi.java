package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.List;

/**
 * This api allows access to messages which should be processed
 */
public class MessagingApi implements IMessagingApi
{

    @Override
    public List<IMessage> getOpenMessages() throws RemoteException
    {
        return null;
    }

}
