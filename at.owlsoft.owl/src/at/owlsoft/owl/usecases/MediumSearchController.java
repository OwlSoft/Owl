package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.ISearchFieldValueConverter;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.Medium;

public class MediumSearchController extends SearchController<Medium>
{

    /**
     * 
     */
    private static final long serialVersionUID = 9131618597524127130L;

    public MediumSearchController(OwlApplicationContext context)
    {
        super(context);
    }

    public Medium search(int mediumId)
    {
        List<SearchField> fields = new ArrayList<SearchField>();
        fields.add(new SearchField("_mediumID", new Integer(mediumId)
                .toString(), SearchFieldType.Equals));

        List<Medium> exemplar = search(fields);

        if (exemplar != null && exemplar.size() > 0)
        {
            return exemplar.get(0);
        }

        return null;

    }

    @Override
    public List<Medium> search(List<SearchField> searchFields)
    {

        ISearchFieldValueConverter converter = getContext()
                .getClientSearchFieldDefinitionController();

        IDaoFactory daoFactory = DaoManager.getInstance();
        IMediumDao dao = daoFactory.getMediumDao();

        return dao.queryByPropertyList(searchFields, converter);
    }
}
