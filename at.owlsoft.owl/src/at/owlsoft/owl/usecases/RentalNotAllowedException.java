package at.owlsoft.owl.usecases;

public class RentalNotAllowedException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 6604070735161207356L;

    public RentalNotAllowedException()
    {
        super();
    }

    public RentalNotAllowedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RentalNotAllowedException(String message)
    {
        super(message);
    }

    public RentalNotAllowedException(Throwable cause)
    {
        super(cause);
    }
}
