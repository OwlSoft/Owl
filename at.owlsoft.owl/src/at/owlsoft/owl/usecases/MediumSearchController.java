package at.owlsoft.owl.usecases;

import java.util.List;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IDao.IMediumDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldDefinition;
import at.owlsoft.owl.model.media.Medium;

public class MediumSearchController extends SearchController<Medium>
{

    @Override
    public List<Medium> search(List<SearchField> searchFields)
    {

        IDaoFactory daoFactory = DaoManager.getInstance();
        IMediumDao dao = daoFactory.getMediumDao();

        SearchFieldDefinition properties = new SearchFieldDefinition();
        for (SearchField sf : searchFields)
        {
            properties.getKeys().add(
                    new SearchField(sf.getKey(), sf.getValue()));
        }

        return dao.queryByPropertyList(properties);
    }
}
