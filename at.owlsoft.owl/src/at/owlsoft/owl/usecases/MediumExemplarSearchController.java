package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.ISearchFieldValueConverter;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IMediumExemplarDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.MediumExemplar;

public class MediumExemplarSearchController extends
        SearchController<MediumExemplar>
{

    /**
     * 
     */
    private static final long serialVersionUID = -6694623090502930362L;

    public MediumExemplarSearchController(OwlApplicationContext context)
    {
        super(context);
    }

    @Override
    public List<MediumExemplar> search(List<SearchField> searchFields)
    {
        ISearchFieldValueConverter converter = getContext()
                .getServerSearchFieldDefinitionController();

        IDaoFactory daoFactory = DaoManager.getInstance();
        IMediumExemplarDao dao = daoFactory.getMediumExemplarDao();

        return dao.queryByPropertyList(searchFields, converter);
    }

    public MediumExemplar search(int exemplarID)
    {
        List<SearchField> fields = new ArrayList<SearchField>();
        fields.add(new SearchField("_exemplarID", new Integer(exemplarID)
                .toString(), SearchFieldType.Equals));

        List<MediumExemplar> exemplar = search(fields);

        if (exemplar != null && exemplar.size() > 0)
        {
            return exemplar.get(0);
        }

        return null;

    }
}
