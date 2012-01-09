package at.owlsoft.owlet.viewmodel;

import java.util.UUID;

import javax.naming.NamingException;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.ejb.SearchApiRemote;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.ISearchFieldDefinition;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owlet.context.EjbContext;
import at.owlsoft.owlet.util.PivotUtils;

public class SearchMediumViewModel
{
    private List<ISearchFieldCategory>   _searchFieldCategories;
    private List<ISearchFieldDefinition> _searchFieldDefinitions;

    private List<SearchFieldType>        _searchTypes;

    private SearchApiRemote              _searchApi;

    private List<IMedium>                _searchResults;

    public List<ISearchFieldCategory> getSearchFieldCategories()
    {
        return _searchFieldCategories;
    }

    public List<ISearchFieldDefinition> getSearchFieldDefinitions()
    {
        return _searchFieldDefinitions;
    }

    public List<SearchFieldType> getSearchTypes()
    {
        return _searchTypes;
    }

    public SearchMediumViewModel()
    {
        _searchTypes = new ArrayList<SearchFieldType>(SearchFieldType.values());
    }

    public List<IMedium> getSearchResults()
    {
        return _searchResults;
    }

    public void initialize() throws InvalidOperationException
    {
        try
        {
            _searchApi = EjbContext.getInstance().getFactory()
                    .createSearchApi();
            updateDefinitions();
        }
        catch (NamingException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }
    }

    private void updateDefinitions()
    {
        java.util.List<ISearchFieldCategory> categories = _searchApi
                .getSearchFieldCategories();

        _searchFieldCategories = new ArrayList<ISearchFieldCategory>();
        _searchFieldDefinitions = new ArrayList<ISearchFieldDefinition>();
        for (ISearchFieldCategory category : categories)
        {
            _searchFieldCategories.add(category);
            for (ISearchFieldDefinition definition : category.getSearchFields())
            {
                _searchFieldDefinitions.add(definition);
            }
        }
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
        _searchResults = PivotUtils.toPivotList(_searchApi.search());
    }

}