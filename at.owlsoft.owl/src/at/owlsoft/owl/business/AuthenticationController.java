package at.owlsoft.owl.business;

import at.owlsoft.owl.model.user.SystemUser;

public class AuthenticationController extends ControllerBase
{
    public AuthenticationController(OwlApplicationContext context)
    {
        super(context);
    }

    public SystemUser getCurrentUser()
    {
        // Next timebox: Login!
        return null;
    }
}
