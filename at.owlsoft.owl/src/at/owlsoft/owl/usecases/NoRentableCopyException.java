package at.owlsoft.owl.usecases;

/**
 * This is thrown if there is no rentable copy.
 */
public class NoRentableCopyException extends RentalNotAllowedException
{

    /**
     * 
     */
    private static final long serialVersionUID = 2566230011850976473L;

    public NoRentableCopyException()
    {
        super();
    }

    public NoRentableCopyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoRentableCopyException(String message)
    {
        super(message);
    }

    public NoRentableCopyException(Throwable cause)
    {
        super(cause);
    }
}
