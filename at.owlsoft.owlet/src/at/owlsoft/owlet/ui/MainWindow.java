package at.owlsoft.owlet.ui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.MenuBar;
import org.apache.pivot.wtk.MenuBar.Item;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.Window;

import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owlet.context.RmiContext;

public class MainWindow extends Window implements Bindable
{
    // private static final String TITLE_TEMPLATE = "Owlsoft Owlet - %s";

    private BoxPane _viewBox;
    private MenuBar _menu;

    private Logger  LOGGER = Logger.getLogger(MainWindow.class);

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

        updateViewRoles();
        ViewController.getInstance().loadContent("DashboardView", this);
    }

    public void updateViewRoles()
    {
        try
        {
            Item fileMenu = _menu.getItems().get(0);
            List<org.apache.pivot.wtk.Menu.Item> items = new ArrayList<org.apache.pivot.wtk.Menu.Item>();

            for (org.apache.pivot.wtk.Menu.Item item : fileMenu.getMenu()
                    .getSections().get(0))
            {
                items.add(item);
            }

            for (org.apache.pivot.wtk.Menu.Item item : items)
            {
                fileMenu.getMenu().getSections().get(0).remove(item);
            }

            List<IRole> roles = RmiContext.getInstance().getFactory()
                    .createAuthenticationApi().getRolesForCurrentUser();

            for (IRole iRole : roles)
            {

            }

            for (org.apache.pivot.wtk.Menu.Item item : items)
            {
                fileMenu.getMenu().getSections().get(0).add(item);
            }

        }
        catch (RemoteException e)
        {
            LOGGER.debug("failed roles loading");
            e.printStackTrace();
        }

    }
}
