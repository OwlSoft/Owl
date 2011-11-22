package at.owlsoft.owl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFieldCategory implements ISearchFieldCategory
{
    private static final long           serialVersionUID = 7452532013084192465L;

    private String                      _label;
    private List<SearchFieldDefinition> _searchFields;

    public SearchFieldCategory()
    {
        _searchFields = new ArrayList<SearchFieldDefinition>();
    }

    public SearchFieldCategory(String label)
    {
        super();
        _label = label;
        _searchFields = new ArrayList<SearchFieldDefinition>();
    }

    @Override
    public String getLabel()
    {
        return _label;
    }

    @Override
    public int getSearchFieldCount()
    {
        return _searchFields.size();
    }

    @Override
    public SearchFieldDefinition getSearchField(int index)
    {
        return _searchFields.get(index);
    }

    @Override
    public List<SearchFieldDefinition> getSearchFields()
    {
        return Collections.unmodifiableList(_searchFields);
    }

    public void clearSearchFields()
    {
        _searchFields.clear();
    }

    public void addSearchField(SearchFieldDefinition searchField)
    {
        _searchFields.add(searchField);
    }

    public void removeSearchField(SearchFieldDefinition searchField)
    {
        _searchFields.remove(searchField);
    }

}
