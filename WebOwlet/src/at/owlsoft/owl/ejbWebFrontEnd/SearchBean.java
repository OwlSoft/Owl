package at.owlsoft.owl.ejbWebFrontEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.owlsoft.owl.communication.ejb.SearchApiRemote;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.ISearchFieldDefinition;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.IMedium;

@ManagedBean
@SessionScoped
public class SearchBean
{

    private SearchApiRemote                     _searchApi;

    private Map<String, ISearchFieldCategory>   _searchFieldCategories;
    private Map<String, ISearchFieldDefinition> _searchFieldDefinitions;
    private Map<String, SearchFieldType>        _searchTypes;
    private List<IMedium>                       _searchResults;

    private ISearchFieldCategory                _searchCategory;
    private String                              _searchFieldDefinition;

    private String                              _searchValue;

    public SearchBean()
    {

        _searchApi = EJBContext.getInstance().getFactory().createSearchApi();

        _searchTypes = new HashMap<String, SearchFieldType>();
        for (SearchFieldType type : SearchFieldType.values())
        {
            _searchTypes.put(type.name(), type);
        }
    }

    @PostConstruct
    private void updateDefinitions()
    {
        java.util.List<ISearchFieldCategory> categories = _searchApi
                .getSearchFieldCategories();

        _searchFieldCategories = new HashMap<String, ISearchFieldCategory>();
        _searchFieldDefinitions = new HashMap<String, ISearchFieldDefinition>();
        for (ISearchFieldCategory category : categories)
        {
            _searchFieldCategories.put(category.getLabel(), category);
            for (ISearchFieldDefinition definition : category.getSearchFields())
            {
                _searchFieldDefinitions.put(definition.getLabel(), definition);
            }
        }
    }

    public List<String> getSearchFieldCategories()
    {
        if (_searchFieldCategories == null)
        {
            updateDefinitions();
        }
        return new ArrayList<String>(_searchFieldCategories.keySet());
    }

    public List<String> getSearchFieldDefinitions()
    {
        return new ArrayList<String>(_searchFieldDefinitions.keySet());
    }

    public List<String> getSearchTypes()
    {
        return new ArrayList<String>(_searchTypes.keySet());
    }

    public List<IMedium> getSearchResults()
    {
        return _searchResults;
    }

    public UUID addNewSearchField()
    {
        UUID newUid = UUID.randomUUID();
        _searchApi.addNewSearchField(newUid);
        return newUid;
    }

    public void setSearchFieldData(UUID uniqueId,
            ISearchFieldDefinition definition, String value)
    {
        if (definition != null)
        {
            _searchApi.setSearchFieldData(uniqueId, definition.getKey(), value);
        }

    }

    public void removeSearchField(UUID uniqueId)
    {
        _searchApi.removeSearchField(uniqueId);
    }

    public void doSearch()
    {
        UUID searchFieldId = addNewSearchField();
        setSearchFieldData(searchFieldId,
                _searchFieldDefinitions.get(getSearchFieldDefinition()),
                getSearchValue());

        _searchResults = _searchApi.search();
        _searchApi.removeSearchField(searchFieldId);
    }

    public ISearchFieldCategory getSearchCategory()
    {
        return _searchCategory;
    }

    public void setSearchCategory(ISearchFieldCategory searchCategory)
    {
        _searchCategory = searchCategory;
    }

    public String getSearchValue()
    {
        return _searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        _searchValue = searchValue;
    }

    public String getSearchFieldDefinition()
    {
        return _searchFieldDefinition;
    }

    public void setSearchFieldDefinition(String searchFieldDefinition)
    {
        _searchFieldDefinition = searchFieldDefinition;
    }

}
