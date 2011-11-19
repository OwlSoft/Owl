package at.owlsoft.owl.dao.db4o;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import at.owlsoft.owl.dao.IDao.IGeneralDao;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public class GeneralDb4ODao extends GeneralDb4oDaoBase<Object> implements
        IGeneralDao
{

    private static GeneralDb4ODao _factory;

    static GeneralDb4ODao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new GeneralDb4ODao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private GeneralDb4ODao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

    private ObjectContainer _db;

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Object object)
    {
        _db.delete(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(Object object)
    {
        _db.store(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> queryByExample(Object object)
    {
        List<Object> tempList = new ArrayList();
        tempList.addAll(_db.queryByExample(object));
        return tempList;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> queryByPropertyList(
            List<Entry<String, String>> keyValuePairs)
    {
        Query query = _db.query();
        List<Object> tempList = new ArrayList();
        for (Entry<String, String> entry : keyValuePairs)
        {
            query.descend(entry.getKey()).constrain(entry.getValue());
        }
        tempList.addAll(query.execute());

        return tempList;

    }
}
