package at.owlsoft.owl.model;

public class NoPermissionException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -2688850801502760136L;

    public NoPermissionException()
    {

    }

    public NoPermissionException(String message)
    {
        super(message);
    }

}
