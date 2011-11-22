package at.owlsoft.owlet.data;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContextManager
{
    private Map<Component, Object>            _dataContexts;
    private List<IDataContextChangedListener> _contextChangeListener;

    public DataContextManager()
    {
        _dataContexts = new HashMap<Component, Object>();
        _contextChangeListener = new ArrayList<IDataContextChangedListener>();
    }

    public void register(Component component, Object dataContext)
    {
        _dataContexts.put(component, dataContext);
    }

    public Object lookup(Component component)
    {
        Component current = component;

        while (current != null)
        {
            if (_dataContexts.containsKey(current))
            {
                return _dataContexts.get(current);
            }
            current = current.getParent();
        }

        return null;
    }

    public void addDataContextChangeListener(
            IDataContextChangedListener listener)
    {
        _contextChangeListener.add(listener);
    }

    public void removeDataContextChangeListener(
            IDataContextChangedListener listener)
    {
        _contextChangeListener.remove(listener);
    }

    protected void fireDataContextChange(Component component, Object oldValue,
            Object newValue)
    {
        for (IDataContextChangedListener listener : _contextChangeListener)
        {
            listener.dataContextChange(new DataContextChangeEvent(component,
                    "dataContext", oldValue, newValue));
        }
    }

}
