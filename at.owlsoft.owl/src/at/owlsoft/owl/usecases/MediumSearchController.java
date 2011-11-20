package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IDao.IMediumDao;
import at.owlsoft.owl.model.MyEntry;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.media.Medium;

public class MediumSearchController extends SearchController<Medium>
{

    @Override
    public List<Medium> search(List<SearchField> searchFields)
    {

        IDaoFactory daoFactory = DaoManager.getDb4ODaoInstance();
        IMediumDao dao = daoFactory.getMediumDao();

        List<Entry<String, String>> properties = new ArrayList<Entry<String, String>>();
        for (SearchField sf : searchFields)
        {
            properties.add(new MyEntry<String, String>(sf.getKey(), sf
                    .getValue()));
        }

        return dao.queryByPropertyList(properties);
    }

}
