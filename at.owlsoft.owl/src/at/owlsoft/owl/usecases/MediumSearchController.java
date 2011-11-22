package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.business.ISearchFieldValueConverter;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.media.Medium;

public class MediumSearchController extends SearchController<Medium>
{

    @Override
    public List<Medium> search(List<SearchField> searchFields)
    {

        // TODO
        // FIXME
        ISearchFieldValueConverter converter = null;

        IDaoFactory daoFactory = DaoManager.getInstance();
        IMediumDao dao = daoFactory.getMediumDao();

        return dao.queryByPropertyList(searchFields, converter);
    }
}
