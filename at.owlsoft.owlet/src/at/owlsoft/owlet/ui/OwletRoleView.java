package at.owlsoft.owlet.ui;

import org.apache.pivot.wtk.Prompt;

import at.owlsoft.owlet.controller.AuthenticationController;

public abstract class OwletRoleView extends OwletView
{
    private String _role;

    public String getRole()
    {
        return _role;
    }

    public OwletRoleView(String role)
    {
        super();
        _role = role;
    }

    @Override
    protected boolean onViewOpening(MainWindow mainWindow)
    {
        setEnabled(false);
        if (!AuthenticationController.getInstance().hasRole(_role))
        {
            Prompt.prompt("You don't have the permission to use this action!",
                    mainWindow);
            return false;
        }
        return super.onViewOpening(mainWindow);
    }
}