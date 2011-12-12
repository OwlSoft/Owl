package at.owlsoft.owlet.viewmodel;

import java.util.UUID;

import org.apache.pivot.collections.ArrayList;

import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.MessageState;

public class MessagingViewModel
{

    private static MessagingViewModel _instance;

    public MessagingViewModel()
    {

    }

    public static MessagingViewModel getInstance()
    {
        if (_instance == null)
        {
            _instance = new MessagingViewModel();
        }

        return _instance;
    }

    public int countMessages()
    {
        return 0;
    }

    public ArrayList<IMessage> getOpenMessages()
    {

        return null;
    }

    public void markMessage(MessageState state, IMessage message)
    {
        markMessage(state, message.getMessageId());

    }

    public void markMessage(MessageState state, UUID message)
    {

    }

    public void refresh()
    {

    }

}
