package at.owlsoft.owl.dao.db4o;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.ISearchFieldValueConverter;
import at.owlsoft.owl.dao.IDao;
import at.owlsoft.owl.model.SearchField;

import com.db4o.query.Query;

/**
 * @param <T>
 */
public abstract class Db4oDaoBase<T> implements IDao<T>
{
    private static final String DEFAULT_DATABASE = "DB4OFILENAME";
    private Class<T>            _clazz;

    protected Db4oDaoBase(Class<T> clazz)
    {
        super();
        _clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T object)
    {
        Db4ODaoFactory.getInstance(DEFAULT_DATABASE).getDb().delete(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(T object)
    {
        Db4ODaoFactory.getInstance(DEFAULT_DATABASE).getDb().store(object);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> queryByExample(T object)
    {
        List<T> tempList = new ArrayList<T>();
        for (Object t : Db4ODaoFactory.getInstance(DEFAULT_DATABASE).getDb()
                .queryByExample(object))
        {
            tempList.add((T) t);
        }

        return tempList;

    }

    /**
     * {@inheritDoc} Checks for the SearchFieldType --> default use is equals
     */
    @Override
    public List<T> queryByPropertyList(List<SearchField> keyValuePairs,
            ISearchFieldValueConverter converter)
    {

        Query query = Db4ODaoFactory.getInstance(DEFAULT_DATABASE).getDb()
                .query();
        query.constrain(_clazz);
        List<T> tempList = new ArrayList<T>();
        // TODO: add constraint for class type
        for (SearchField entry : keyValuePairs)
        {
            switch (entry.getType())
            {
                case Greater:
                    query.descend(entry.getKey())
                            .constrain(converter.convert(entry)).greater();
                    break;

                case Lesser:
                    query.descend(entry.getKey())
                            .constrain(converter.convert(entry)).smaller();
                    break;

                default:
                    query.descend(entry.getKey()).constrain(
                            converter.convert(entry));
                    break;
            }

        }

        for (Object t : query.execute())
        {
            tempList.add((T) t);
        }

        return tempList;

    }
}
