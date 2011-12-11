package at.owlsoft.owl.model.messaging;

import java.io.Serializable;
import java.util.Date;

public interface IMessage extends Serializable
{
    public MessageState getState();

    public Date getInsertDate();

    public String getDisplayString();

    /**
     * @see IMessageEventStrings
     * @return
     */
    public String getEventType();
}
