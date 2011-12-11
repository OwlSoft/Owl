package at.owlsoft.owl.model.messaging;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public interface IMessage extends Serializable
{
    public UUID getMessageId();

    public MessageState getState();

    public Date getInsertDate();

    public String getDisplayString();

    /**
     * @see IMessageEventStrings
     * @return
     */
    public String getEventType();
}