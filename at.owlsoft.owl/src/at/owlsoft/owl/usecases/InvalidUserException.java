package at.owlsoft.owl.usecases;

public class InvalidUserException extends RentalNotAllowedException
{
    /**
     * 
     */
    private static final long serialVersionUID = 6672274484092418079L;

    public InvalidUserException()
    {
        super();
    }

    public InvalidUserException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidUserException(String message)
    {
        super(message);
    }

    public InvalidUserException(Throwable cause)
    {
        super(cause);
    }
}
