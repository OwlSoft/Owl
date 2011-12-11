package at.owlsoft.owl.dao.db4o;

import java.util.List;

import at.owlsoft.owl.dao.IMessageDao;
import at.owlsoft.owl.model.messaging.Message;

public class MessageDao extends Db4oDaoBase<Message> implements IMessageDao
{

    protected MessageDao(Db4ODaoFactory factory)
    {
        super(factory, Message.class);
    }

    @Override
    public List<Message> getOpenMessages()
    {
        // TODO: query messages with state open
        return null;
    }

}
