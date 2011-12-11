package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.Message;

public class MessageController extends ControllerBase
{
    public MessageController(OwlApplicationContext context)
    {
        super(context);
    }

    public List<Message> getOpenMessages() throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.OPERATOR);

        List<Message> messages = new ArrayList<Message>();
        // TODO Load open messages from database
        return messages;
    }

    public void addMessage(Message message)
    {
        // TODO Add message to database
        // TODO Sent message key via JMS
    }
}