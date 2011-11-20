package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.model.SearchField;

public abstract class SearchController<T>
{

    public abstract List<T> search(List<SearchField> searchFields);

}
