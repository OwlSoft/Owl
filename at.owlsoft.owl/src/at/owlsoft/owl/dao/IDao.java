package at.owlsoft.owl.dao;

import java.util.List;
import java.util.Map.Entry;

public interface IDao<T>
{

    public abstract void delete(Object object);

    public abstract void store(Object object);

    public abstract List<T> queryByExample(Object object);

    /**
     * not sure if this code works correctly
     * 
     * @param keyValuePairs
     * @return
     */
    public abstract List<T> queryByPropertyList(
            List<Entry<String, String>> keyValuePairs);

}