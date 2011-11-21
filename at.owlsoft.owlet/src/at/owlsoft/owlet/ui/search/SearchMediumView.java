package at.owlsoft.owlet.ui.search;

import at.owlsoft.owlet.data.DataContextManager;
import at.owlsoft.owlet.ui.OwletView;

public class SearchMediumView extends OwletView
{
    private static final String KEY              = "SEARCH_MEDIUM";
    private static final long   serialVersionUID = 980097204234955190L;

    private DataContextManager  _dataContexts;

    public SearchMediumView()
    {
        super(KEY, "Search Medium");
    }

    @Override
    protected void initializeComponents()
    {
        _dataContexts = new DataContextManager();

    }

}
