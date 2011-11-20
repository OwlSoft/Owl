package at.owlsoft.owl.dao.db4o;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import at.owlsoft.owl.dao.IDao.IDao;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class GeneralDb4oDaoBase<T> implements IDao<T>
{

    private ObjectContainer _db;

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

    protected GeneralDb4oDaoBase(ObjectContainer db)
    {
        super();
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
        List<T> tempList = new ArrayList();
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
    public List<T> queryByPropertyList(List<Entry<String, String>> keyValuePairs)
    {
        Query query = _db.query();
        List<T> tempList = new ArrayList();
        for (Entry<String, String> entry : keyValuePairs)
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
