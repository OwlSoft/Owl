package at.owlsoft.owlet.viewmodel;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.ISearchFieldDefinition;

public class SearchMediumViewModel
{
    private List<ISearchFieldCategory>   _searchFieldCategories;
    private List<ISearchFieldDefinition> _searchFieldDefinitions;

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
        updateDefinitions();
    }

    private void updateDefinitions()
    {
        // TODO: RMI get
        _searchFieldCategories = new ArrayList<ISearchFieldCategory>();
        _searchFieldDefinitions = new ArrayList<ISearchFieldDefinition>();
        for (ISearchFieldCategory category : _searchFieldCategories)
        {
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