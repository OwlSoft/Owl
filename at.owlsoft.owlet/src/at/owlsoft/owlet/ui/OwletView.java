package at.owlsoft.owlet.ui;

import javax.swing.JPanel;

public abstract class OwletView extends JPanel
{
    private static final long serialVersionUID = 278712600508864416L;

    private String            _title;
    private String            _key;

    public String getTitle()
    {
        return _title;
    }

    public String getKey()
    {
        return _key;
    }

    public OwletView(String key, String title)
    {
        super();
        _key = key;
        _title = title;
        initializeComponents();
    }

    protected abstract void initializeComponents();

    protected boolean onViewClosing()
    {
        return true;
    }

    protected void onViewClosed()
    {

    }

    protected boolean onViewOpening()
    {
        return true;
    }

    protected void onViewOpened()
    {

    }

}
