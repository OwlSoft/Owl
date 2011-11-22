package at.owlsoft.owl.dao.db4o;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.dao.IDao;
import at.owlsoft.owl.model.SearchField;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

/**
 * @param <T>
 */
public abstract class Db4oDaoBase<T> implements IDao<T>
{
    private Class<T>        _clazz;
    private ObjectContainer _db;

    protected Db4oDaoBase(Class<T> clazz, ObjectContainer db)
    {
        super();
        _db = db;
        _clazz = clazz;
    }

    /**
     * @return the db
     */
    public ObjectContainer getDb()
    {
        return _db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(ObjectContainer db)
    {
        _db = db;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T object)
    {
        _db.delete(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(T object)
    {
        _db.store(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> queryByExample(T object)
    {
        List<T> tempList = new ArrayList<T>();
        for (Object t : _db.queryByExample(object))
        {
            tempList.add((T) t);
        }

        return tempList;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> queryByPropertyList(List<SearchField> keyValuePairs)
    {
        Query query = _db.query();
        query.constrain(_clazz);
        List<T> tempList = new ArrayList<T>();
        // TODO: add constraint for class type
        for (SearchField entry : keyValuePairs)
        {
            query.descend(entry.getKey()).constrain(entry.getValue());
        }

        for (Object t : query.execute())
        {
            tempList.add((T) t);
        }

        return tempList;

    }
}
