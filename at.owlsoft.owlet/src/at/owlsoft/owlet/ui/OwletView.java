package at.owlsoft.owlet.ui;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Orientation;

public abstract class OwletView extends BoxPane implements Bindable
{
    public OwletView()
    {
        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);
        getStyles().put("padding", 10);
    }

    protected boolean onViewClosing()
    {
        return true;
    }

    protected void onViewClosed()
    {

    }

    protected void onViewOpened()
    {

    }

    protected boolean onViewOpening(MainWindow mainWindow)
    {
        return true;
    }

}
