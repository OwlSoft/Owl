public package at.owlsoft.owl.dao.IDao;

import java.util.List;

import at.owlsoft.owl.model.SearchFieldDefinition;

public interface IDao<T>
{

    public abstract void delete(T object);

    public abstract void store(T object);

    public abstract List<T> queryByExample(T object);

    /**
     * not sure if this code works correctly
     * 
     * @param keyValuePairs
     * @return
     */
    public abstract List<T> queryByPropertyList(
            SearchFieldDefinition definitions);

}