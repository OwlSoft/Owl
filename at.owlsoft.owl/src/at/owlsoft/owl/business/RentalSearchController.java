package at.owlsoft.owl.business;

import java.util.List;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.usecases.SearchController;

public class RentalSearchController extends SearchController<Rental>
{

    public RentalSearchController(OwlApplicationContext context)
    {
        super(context);
    }

    @Override
    public List<Rental> search(List<SearchField> searchFields)
    {
        ISearchFieldValueConverter converter = getContext()
                .getServerSearchFieldDefinitionController();

        IDaoFactory daoFactory = DaoManager.getInstance();
        IRentalDao dao = daoFactory.getRentalDao();

        return dao.queryByPropertyList(searchFields, converter);
    }

}
