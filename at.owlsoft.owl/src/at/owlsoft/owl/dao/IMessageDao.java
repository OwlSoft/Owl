package at.owlsoft.owl.dao;

import java.util.List;

import at.owlsoft.owl.model.messaging.Message;

public interface IMessageDao extends IDao<Message>
{

    List<Message> getOpenMessages();

}
