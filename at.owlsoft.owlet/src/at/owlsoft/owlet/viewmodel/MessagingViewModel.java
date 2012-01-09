package at.owlsoft.owlet.viewmodel;

import java.util.UUID;

import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.ejb.MessagingApiRemote;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.MessageState;
import at.owlsoft.owlet.context.EjbContext;
import at.owlsoft.owlet.util.PivotUtils;

public class MessagingViewModel
{

    private static MessagingViewModel _instance;
    private MessagingApiRemote        _messagingApi;
    private int                       _countedMessages;
    private List<IMessage>            _openMessages;

    public MessagingViewModel()
    {
        try
        {
            _messagingApi = EjbContext.getInstance().getFactory()
                    .createMessagingApi();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        refresh();

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
        return _countedMessages;
    }

    public List<IMessage> getOpenMessages()
    {
        return _openMessages;
    }

    public void markMessage(MessageState state, IMessage message)
    {
        markMessage(state, message.getMessageId());

    }

    public boolean markMessage(MessageState state, UUID message)
    {
        try
        {
            _messagingApi.markMessage(message, state);
            return true;
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public boolean refresh()
    {
        try
        {
            _openMessages = PivotUtils.toPivotList(_messagingApi
                    .getOpenMessages());
            _countedMessages = _openMessages.getLength();
            return true;
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
}
