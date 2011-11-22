package at.owlsoft.owl.usecases;

public class RequiredFieldsNotFilledException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 3548863308304835487L;

    public RequiredFieldsNotFilledException()
    {
        super();
    }

    public RequiredFieldsNotFilledException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RequiredFieldsNotFilledException(String message)
    {
        super(message);
    }

    public RequiredFieldsNotFilledException(Throwable cause)
    {
        super(cause);
    }

}
