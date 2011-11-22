package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.SearchField;

public abstract class SearchController<T> extends ControllerBase
{

    public SearchController(OwlApplicationContext context)
    {
        super(context);
    }

    public abstract List<T> search(List<SearchField> searchFields);

}
