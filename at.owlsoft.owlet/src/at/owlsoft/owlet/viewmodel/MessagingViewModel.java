package at.owlsoft.owlet.viewmodel;

import org.apache.pivot.collections.ArrayList;

import at.owlsoft.owl.model.messaging.IMessage;

public class MessagingViewModel
{

    private static MessagingViewModel _instance;

    public MessagingViewModel getInstance()
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

    public void markMessage()
    {

    }

}
