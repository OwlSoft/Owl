package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.apache.log4j.Logger;

import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;

/**
 * Session Bean implementation class SearchApi
 */
@Stateful
public class SearchApi implements SearchApiRemote
{
    @EJB
    private OwlContextBean                  _context;

    private static final Logger             logger = Logger.getLogger(SearchApi.class);

    private SearchFieldDefinitionController _fieldcontroller;
    private MediumSearchController          _searchController;

    private Map<UUID, SearchField>          _searchFields;

    public SearchApi()
    {
        _fieldcontroller = _context.getContext()
                .getClientSearchFieldDefinitionController();
        _searchFields = new HashMap<UUID, SearchField>();
        _searchController = _context.getContext().getMediumSearchController();
    }

    @Override
    public void reset()
    {
        _searchFields.clear();
    }

    @Override
    public List<ISearchFieldCategory> getSearchFieldCategories()
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
    public List<IMedium> search()
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
                logger.trace("  Search includes " + searchField.getKey() + ":"
                        + searchField.getValue());

                fields.add(searchField);
            }
        }

        logger.trace("Searching with " + fields.size() + " criterias");

        List<Medium> result = _searchController.search(fields);

        logger.trace("found: " + result.size());

        return new ArrayList<IMedium>(result);
    }
}
