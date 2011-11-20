package at.owlsoft.owl.usecases;

/**
 * This is thrown if there is no rentable copy.
 */
public class NoRentableCopyException extends RentalNotAllowedException
{

    public NoRentableCopyException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 2566230011850976473L;

    public NoRentableCopyException(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

}
