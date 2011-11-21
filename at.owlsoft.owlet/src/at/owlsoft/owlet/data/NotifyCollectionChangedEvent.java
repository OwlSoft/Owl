package at.owlsoft.owlet.data;

import java.util.EventObject;

public class NotifyCollectionChangedEvent<T> extends EventObject
{
    private static final long             serialVersionUID = 2186216145880070405L;

    private NotifyCollectionChangedAction _action;

    public NotifyCollectionChangedAction getAction()
    {
        return _action;
    }

    /**
     * Initializes a new instance of the NotifyCollectionChangedEvent class as a
     * reset event.
     * 
     * @param source the raiser of the events.
     * @param actionThe action that caused the event.
     */
    public NotifyCollectionChangedEvent(Object source,
            NotifyCollectionChangedAction action)
    {
        super(source);
        _action = action;
    }
}
