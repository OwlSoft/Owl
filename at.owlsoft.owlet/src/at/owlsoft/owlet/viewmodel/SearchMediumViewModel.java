package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.rmi.ISearchApi;
import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.ISearchFieldDefinition;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owlet.context.RmiContext;

public class SearchMediumViewModel
{
    private List<ISearchFieldCategory>   _searchFieldCategories;
    private List<ISearchFieldDefinition> _searchFieldDefinitions;

    private ISearchApi                   _searchApi;

    public List<ISearchFieldCategory> getSearchFieldCategories()
    {
        return _searchFieldCategories;
    }

    public List<ISearchFieldDefinition> getSearchFieldDefinitions()
    {
        return _searchFieldDefinitions;
    }

    public SearchMediumViewModel()
    {
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
            throw new InvalidOperationException(
                    "Could not establish connection to server", e);
        }
    }

    private void updateDefinitions() throws RemoteException
    {
        java.util.List<? extends ISearchFieldCategory> categories = _searchApi
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

    public ISearchField addNewSearchField()
    {
        // TODO: RMI create
        return null;
    }

    public ISearchField setSearchFieldData(ISearchField searchField,
            ISearchFieldDefinition iSearchFieldDefinition, String text)
    {
        // TODO: RMI update
        return null;
    }

    public void removeSearchField(ISearchField searchField)
    {
        // TODO: RMI delete
    }

}