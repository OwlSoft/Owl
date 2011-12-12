package at.owlsoft.owl.usecases;

import java.util.List;
import java.util.UUID;

import javax.jms.JMSException;
import javax.naming.NamingException;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IMessageDao;
import at.owlsoft.owl.jms.TopicProduceController;
import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

public class MessageController extends ControllerBase
{
    private TopicProduceController _topicProduceController;
    private static final String    TOPICNAME = "Topic";

    public MessageController(OwlApplicationContext context)
    {
        super(context);
        try
        {
            _topicProduceController = new TopicProduceController(TOPICNAME);
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NamingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        try
        {
            _topicProduceController.sendText(eventType);
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void markMessage(UUID uuid, MessageState state)
            throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.OPERATOR);

        IMessageDao dao = DaoManager.getInstance().getMessageDao();
        Message message = dao.getByUUID(uuid);
        message.setState(state);
        dao.store(message);
    }
}