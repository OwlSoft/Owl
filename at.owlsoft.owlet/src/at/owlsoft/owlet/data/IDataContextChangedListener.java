package at.owlsoft.owlet.data;

import java.util.EventListener;

public interface IDataContextChangedListener extends EventListener
{
    void dataContextChange(DataContextChangeEvent evt);
}
