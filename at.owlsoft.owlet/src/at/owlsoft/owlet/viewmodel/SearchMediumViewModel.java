package at.owlsoft.owlet.viewmodel;

import at.owlsoft.owl.model.ISearchFieldCategory;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

public class SearchMediumViewModel
{
    private ObservableList<ISearchFieldCategory> _searchFieldCategories;

    public ObservableList<ISearchFieldCategory> getSearchFieldCategories()
    {
        return _searchFieldCategories;
    }

    public SearchMediumViewModel()
    {
        _searchFieldCategories = new ArrayListModel<ISearchFieldCategory>();
    }
}
