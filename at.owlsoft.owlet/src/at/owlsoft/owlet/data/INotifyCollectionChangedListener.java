package at.owlsoft.owlet.data;

import java.util.EventListener;

public interface INotifyCollectionChangedListener extends EventListener
{
    void collectionChanged(NotifyCollectionChangedEvent e);
}
