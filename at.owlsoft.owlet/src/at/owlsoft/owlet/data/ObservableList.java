package at.owlsoft.owlet.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObservableList<T> extends ArrayList<T>
{
    private List<INotifyCollectionChangedListener> _collectionChangedListeners;

    public ObservableList()
    {
        super();
        _collectionChangedListeners = new ArrayList<INotifyCollectionChangedListener>();
    }

    public ObservableList(Collection<? extends T> c)
    {
        super(c);
        _collectionChangedListeners = new ArrayList<INotifyCollectionChangedListener>();
    }

    public ObservableList(int initialCapacity)
    {
        super(initialCapacity);
        _collectionChangedListeners = new ArrayList<INotifyCollectionChangedListener>();
    }

    @Override
    public T set(int index, T element)
    {
        try
        {
            return super.set(index, element);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Replace);
        }
    }

    private void raiseCollectionChangedEvent(
            NotifyCollectionChangedAction action)
    {
        for (INotifyCollectionChangedListener listener : _collectionChangedListeners)
        {
            listener.collectionChanged(new NotifyCollectionChangedEvent<T>(
                    this, action));
        }
    }

    @Override
    public boolean add(T e)
    {
        try
        {
            return super.add(e);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Add);
        }
    }

    @Override
    public void add(int index, T element)
    {
        try
        {
            super.add(index, element);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Add);
        }
    }

    @Override
    public T remove(int index)
    {
        try
        {
            return super.remove(index);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Remove);
        }
    }

    @Override
    public boolean remove(Object o)
    {
        try
        {
            return super.remove(o);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Remove);
        }
    }

    @Override
    public void clear()
    {
        try
        {
            super.clear();
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Reset);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        try
        {
            return super.addAll(c);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Add);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        try
        {
            return super.addAll(index, c);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Add);
        }
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        try
        {
            super.removeRange(fromIndex, toIndex);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Remove);
        }
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        try
        {
            return super.removeAll(c);
        }
        finally
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Remove);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        if (super.retainAll(c))
        {
            raiseCollectionChangedEvent(NotifyCollectionChangedAction.Remove);
            return true;
        }
        return false;
    }
}
