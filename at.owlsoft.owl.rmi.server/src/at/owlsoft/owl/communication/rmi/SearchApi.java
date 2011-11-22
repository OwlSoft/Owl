package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;

import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;

public class SearchApi extends ApiBase implements ISearchApi
{
    private static final Logger             logger = Logger.getLogger(SearchApi.class);

    private SearchFieldDefinitionController _fieldcontroller;
    private MediumSearchController          _searchController;

    private Map<UUID, SearchField>          _searchFields;

    public SearchApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _fieldcontroller = new SearchFieldDefinitionController();
        _searchFields = new HashMap<UUID, SearchField>();
        _searchController = new MediumSearchController();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8248250017928395289L;

    @Override
    public List<ISearchFieldCategory> getSearchFieldCategories()
            throws RemoteException
    {
        return new ArrayList<ISearchFieldCategory>(
                _fieldcontroller.getAllCategories());
    }

    @Override
    public ISearchField addNewSearchField(UUID uniqueId)
    {
        SearchField newField = new SearchField();
        _searchFields.put(uniqueId, newField);
        dump();
        return newField;
    }

    @Override
    public void removeSearchField(UUID uniqueId)
    {
        _searchFields.remove(uniqueId);
        dump();
    }

    @Override
    public void setSearchFieldData(UUID uniqueId, String key, String value)
    {
        SearchField field = _searchFields.get(uniqueId);

        if (field != null)
        {
            field.setKey(key);
            field.setValue(value);
        }
        dump();
    }

    private void dump()
    {
        System.out.println("Dump!");
        for (Entry<UUID, SearchField> entry : _searchFields.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println("    k:" + entry.getValue().getKey());
            System.out.println("    v:" + entry.getValue().getValue());
            System.out.println("---");
        }
    }

    @Override
    public List<IMedium> search() throws RemoteException
    {
        try
        {
            List<SearchField> fields = new ArrayList<SearchField>();
            logger.trace("building criterias");
            for (SearchField searchField : _searchFields.values())
            {
                if (searchField != null && searchField.getKey() != null
                        && searchField.getValue() != null
                        && !searchField.getValue().trim().isEmpty())
                {
                    if (searchField.getType() == null)
                    {
                        searchField.setType(SearchFieldType.Equals);
                    }
                    logger.trace("  Search includes " + searchField.getKey()
                            + ":" + searchField.getValue());

                    fields.add(searchField);
                }
            }

            logger.trace("Searching with " + fields.size() + " criterias");

            List<Medium> result = _searchController.search(fields);

            logger.trace("found: " + result.size());

            return new ArrayList<IMedium>(result);
        }
        catch (Exception e)
        {
            logger.error("Could not search", e);
            throw new RemoteException(e.toString());
        }
    }
}
