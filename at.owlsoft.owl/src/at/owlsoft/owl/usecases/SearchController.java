package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.SearchField;

public abstract class SearchController<T> extends ControllerBase
{

    /**
     * 
     */
    private static final long serialVersionUID = -8597658137403424822L;

    public SearchController(OwlApplicationContext context)
    {
        super(context);
    }

    public abstract List<T> search(List<SearchField> searchFields);

}
