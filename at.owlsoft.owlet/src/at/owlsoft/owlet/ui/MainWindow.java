package at.owlsoft.owlet.ui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.HashSet;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Menu.Section;
import org.apache.pivot.wtk.MenuBar;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.content.ButtonData;

import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owlet.RoleAction;
import at.owlsoft.owlet.context.RmiContext;
import at.owlsoft.owlet.controller.AuthenticationController;
import at.owlsoft.owlet.controller.TopicController;

public class MainWindow extends Window implements Bindable, MessageListener
{
    private BoxPane         _viewBox;
    private MenuBar         _menu;

    private Section         _actionContainer;
    private Label           _statusLabel;
    private PushButton      _notificationButton;

    private TopicController _topic;

    private Logger          LOGGER = Logger.getLogger(MainWindow.class);

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
            public void setEnabled(boolean enabled)
            {
                super.setEnabled(enabled);
            }

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
        Action.getNamedActions().put("createRental",
                new RoleAction(IDefaultRoles.RENTAL_CREATE)
                {
                    @Override
                    public void perform(Component source)
                    {
                        ViewController.getInstance().loadContent(
                                "CreateRentalView",
                                source.getWindow().getRootOwner());
                    }
                });
        Action.getNamedActions().put("createReservation",
                new RoleAction(IDefaultRoles.RESERVATION_CREATE)
                {
                    @Override
                    public void perform(Component source)
                    {
                        ViewController.getInstance().loadContent(
                                "CreateReservationView",
                                source.getWindow().getRootOwner());
                    }
                });
        Action.getNamedActions().put("showRental",
                new RoleAction(IDefaultRoles.RENTAL_SHOW)
                {
                    @Override
                    public void perform(Component source)
                    {
                        ViewController.getInstance().loadContent(
                                "ShowRentalView",
                                source.getWindow().getRootOwner());
                    }
                });
        Action.getNamedActions().put("adminConfig",
                new RoleAction(IDefaultRoles.ADMIN_CONFIG)
                {
                    @Override
                    public void perform(Component source)
                    {
                        ViewController.getInstance().loadContent(
                                "AdminConfigView",
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

    public MainWindow()
    {
    }

    @Override
    public void initialize(Map<String, Object> ns, URL loc, Resources es)
    {
        _viewBox = (BoxPane) ns.get("content");
        _menu = (MenuBar) ns.get("menu");
        _actionContainer = (Section) ns.get("actionContainer");
        _statusLabel = (Label) ns.get("statusLabel");
        _notificationButton = (PushButton) ns.get("notificationButton");
        _notificationButton.setVisible(false);

        updateViewRoles();
        ViewController.getInstance().loadContent("DashboardView", this);
    }

    public void updateViewRoles()
    {
        try
        {

            for (org.apache.pivot.wtk.Menu.Item item : _actionContainer)
            {
                Action action = item.getAction();
                if (action == null || action instanceof RoleAction)
                {
                    item.setVisible(false);
                }
            }

            List<IRole> roles = RmiContext.getInstance().getFactory()
                    .createAuthenticationApi().getRolesForCurrentUser();

            org.apache.pivot.collections.Set<String> roleKeys = new HashSet<String>();
            for (IRole iRole : roles)
            {
                roleKeys.add(iRole.getKey());
            }

            for (org.apache.pivot.wtk.Menu.Item item : _actionContainer)
            {
                Action action = item.getAction();
                if (action != null && action instanceof RoleAction)
                {
                    RoleAction roleAction = (RoleAction) action;
                    item.setVisible(roleKeys.contains(roleAction.getRole()));
                }
            }

            if (AuthenticationController.getInstance().getCurrentUser() != null)
            {
                _statusLabel.setText("Logged in as: "
                        + AuthenticationController.getInstance()
                                .getCurrentUser().getUsername());

                if (AuthenticationController.getInstance().getCurrentUser()
                        .hasRole(IDefaultRoles.OPERATOR))
                {
                    _notificationButton.setVisible(true);
                    rebindTopic();
                }
                updateMessageCount(0);

            }
            else
            {
                _statusLabel.setText("Logged out");
                _notificationButton.setVisible(false);
            }
        }
        catch (RemoteException e)
        {
            LOGGER.debug("failed roles loading");
            e.printStackTrace();
        }

    }

    private void rebindTopic()
    {
        if (_topic != null)
        {
            _topic.close();
        }
        _topic = new TopicController(AuthenticationController.getInstance()
                .getCurrentUser().getUsername());
        _topic.addMessageListener(this);
    }

    private void updateMessageCount(int i)
    {
        ((ButtonData) _notificationButton.getButtonData()).setText(i
                + " Messages");
    }

    @Override
    public void onMessage(Message message)
    {
        TextMessage msg = null;

        try
        {
            if (message instanceof TextMessage)
            {
                handleMessage(((TextMessage) message).getText());
            }
            else
            {
                System.out.println("Message of wrong type: "
                        + message.getClass().getName());
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading message");
            e.printStackTrace();
        }
    }

    private void handleMessage(String text)
    {

    }
}
