package at.owlsoft.owl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFieldCategory implements ISearchFieldCategory
{
    private static final long serialVersionUID = 7452532013084192465L;

    private String            _label;
    private List<SearchField> _searchFields;

    public SearchFieldCategory()
    {
        _searchFields = new ArrayList<SearchField>();
    }

    public SearchFieldCategory(String label)
    {
        super();
        _label = label;
        _searchFields = new ArrayList<SearchField>();
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
    public SearchField getSearchField(int index)
    {
        return _searchFields.get(index);
    }

    @Override
    public List<SearchField> getSearchFields()
    {
        return Collections.unmodifiableList(_searchFields);
    }

    public void clearSearchFields()
    {
        _searchFields.clear();
    }

    public void addSearchField(SearchField searchField)
    {
        _searchFields.add(searchField);
    }

    public void removeSearchField(SearchField searchField)
    {
        _searchFields.remove(searchField);
    }

}
