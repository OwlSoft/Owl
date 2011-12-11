package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IMessageDao;
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

        List<Message> messages = DaoManager.getInstance().getMessageDao()
                .getOpenMessages();

        return messages;
    }

    public void addMessage(Message message)
    {
        IMessageDao dao = DaoManager.getInstance().getMessageDao();
        dao.store(message);

        sendJmsMessage(message.getEventType());
    }

    private void sendJmsMessage(String eventType)
    {
        // TODO Sent message key via JMS
    }
}