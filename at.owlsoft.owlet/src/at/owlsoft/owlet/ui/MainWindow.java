package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.MenuBar;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Window;

public class MainWindow extends Window implements Bindable
{
    // private static final String TITLE_TEMPLATE = "Owlsoft Owlet - %s";

    private BoxPane _viewBox;
    private MenuBar _menu;

    /**
     * Actions
     */
    static
    {
        // Define the actions
        Action.getNamedActions().put("quitApplication", new Action()
        {
            @Override
            public void perform(Component source)
            {
                System.exit(0);
            }
        });

        Action.getNamedActions().put("todo", new Action()
        {
            @Override
            public void perform(Component source)
            {
                Prompt.prompt("This feature is not implemented yet!", source
                        .getWindow().getRootOwner());
            }
        });

        // other xml views
        Action.getNamedActions().put("searchMedium", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("SearchMediumView",
                        source.getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("createRental", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("CreateRentalView",
                        source.getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("createReservation", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent(
                        "CreateReservationView",
                        source.getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("showRental", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("ShowRentalView",
                        source.getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("adminConfig", new Action()
        {
            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("AdminConfigView",
                        source.getWindow().getRootOwner());
            }
        });
        Action.getNamedActions().put("loginView", new Action()
        {

            @Override
            public void perform(Component source)
            {
                ViewController.getInstance().loadContent("LoginView",
                        source.getWindow().getRootOwner());

            }
        });
    }

    public BoxPane getViewBox()
    {
        return _viewBox;
    }

    public MenuBar getMenu()
    {
        return _menu;
    }

    @Override
    public void initialize(Map<String, Object> ns, URL loc, Resources es)
    {
        _viewBox = (BoxPane) ns.get("content");
        _menu = (MenuBar) ns.get("menu");

        ViewController.getInstance().loadContent("DashboardView", this);
    }

}
