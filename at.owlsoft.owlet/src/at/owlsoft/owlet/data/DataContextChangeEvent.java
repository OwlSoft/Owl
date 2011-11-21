package at.owlsoft.owlet.data;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

public class DataContextChangeEvent extends PropertyChangeEvent
{
    private static final long serialVersionUID = -2852455430351295059L;

    public DataContextChangeEvent(Component source, String propertyName,
            Object oldValue, Object newValue)
    {
        super(source, propertyName, oldValue, newValue);
    }

    @Override
    public Component getSource()
    {
        return (Component) super.getSource();
    }
}
