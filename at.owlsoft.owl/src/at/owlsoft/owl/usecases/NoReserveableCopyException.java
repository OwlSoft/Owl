package at.owlsoft.owl.usecases;

public class NoReserveableCopyException extends RentalNotAllowedException
{
    /**
     * 
     */
    private static final long serialVersionUID = -5699371487222775463L;

    public NoReserveableCopyException()
    {
        super();
    }

    public NoReserveableCopyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoReserveableCopyException(String message)
    {
        super(message);
    }

    public NoReserveableCopyException(Throwable cause)
    {
        super(cause);
    }

}
