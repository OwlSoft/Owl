package at.owlsoft.owlet.ui.search;

import javax.swing.JButton;

import at.owlsoft.owlet.ui.OwletView;

public class SearchMediumView extends OwletView
{
    private static final String KEY              = "SEARCH_MEDIUM";
    private static final long   serialVersionUID = 980097204234955190L;

    public SearchMediumView()
    {
        super(KEY, "Search Medium");
    }

    @Override
    protected void initializeComponents()
    {
        add(new JButton(getTitle()));
    }
}
