package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IMessageDao;
import at.owlsoft.owl.model.messaging.Message;

public class MessageDao extends Db4oDaoBase<Message> implements IMessageDao
{

    protected MessageDao(Db4ODaoFactory factory, Class<Message> clazz)
    {
        super(factory, clazz);
        // TODO Auto-generated constructor stub
    }

}
