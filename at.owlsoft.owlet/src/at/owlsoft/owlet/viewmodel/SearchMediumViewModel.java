package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;
import java.util.UUID;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.rmi.ISearchApi;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.ISearchFieldDefinition;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owlet.context.RmiContext;
import at.owlsoft.owlet.util.PivotUtils;

public class SearchMediumViewModel
{
    private List<ISearchFieldCategory>   _searchFieldCategories;
    private List<ISearchFieldDefinition> _searchFieldDefinitions;

    private List<SearchFieldType>        _searchTypes;

    private ISearchApi                   _searchApi;

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
            _searchApi = RmiContext.getInstance().getFactory()
                    .createSearchApi();
            updateDefinitions();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }
    }

    private void updateDefinitions() throws RemoteException
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
        try
        {
            UUID newUid = UUID.randomUUID();
            _searchApi.addNewSearchField(newUid);
            return newUid;
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Remote server error:"
                    + e.getMessage(), e);
        }
    }

    public void setSearchFieldData(UUID uniqueId,
            ISearchFieldDefinition definition, String value)
    {
        try
        {
            _searchApi.setSearchFieldData(uniqueId, definition.getKey(), value);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Remote server error:"
                    + e.getMessage(), e);
        }

    }

    public void removeSearchField(UUID uniqueId)
    {
        try
        {
            _searchApi.removeSearchField(uniqueId);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Remote server error:"
                    + e.getMessage(), e);
        }
    }

    public void doSearch()
    {
        try
        {
            _searchResults = PivotUtils.toPivotList(_searchApi.search());
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException("Remote server error:"
                    + e.getMessage(), e);
        }
    }

}