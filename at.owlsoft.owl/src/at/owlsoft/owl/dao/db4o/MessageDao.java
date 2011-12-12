package at.owlsoft.owl.dao.db4o;

import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.dao.IMessageDao;
import at.owlsoft.owl.model.messaging.Message;
import at.owlsoft.owl.model.messaging.MessageState;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class MessageDao extends Db4oDaoBase<Message> implements IMessageDao
{

    MessageDao(Db4ODaoFactory factory)
    {
        super(factory, Message.class);
    }

    @Override
    public List<Message> getOpenMessages()
    {
        Query query = getFactory().getDb().query();
        query.constrain(Message.class);
        query.descend("_state").constrain(MessageState.Open).equal();

        return query.execute();
    }

    @Override
    public Message getByUUID(UUID uuid)
    {
        Query query = getFactory().getDb().query();
        query.constrain(Message.class);
        query.descend("_uuid").constrain(uuid).equal();

        ObjectSet<Message> result = query.execute();
        if (result.isEmpty())
        {
            return null;
        }
        return result.get(0);
    }

}
