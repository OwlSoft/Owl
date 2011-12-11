package at.owlsoft.owl.model.messaging;

import java.util.Date;

public abstract class Message implements IMessage
{
    /**
     * 
     */
    private static final long serialVersionUID = 471515101037614616L;

    private MessageState      _state;
    private Date              _insertDate;

    @Override
    public MessageState getState()
    {
        return _state;
    }

    @Override
    public Date getInsertDate()
    {
        return _insertDate;
    }

    public void setState(MessageState state)
    {
        _state = state;
    }

    public void setInsertDate(Date insertDate)
    {
        _insertDate = insertDate;
    }

    public Message()
    {
        _state = MessageState.Open;
        _insertDate = new Date();
    }

    public Message(MessageState state, Date insertDate)
    {
        super();
        _state = state;
        _insertDate = insertDate;
    }
}