package at.owlsoft.owlet;

public class NotImplementedException extends Exception
{
    private static final long serialVersionUID = -5737682912429140810L;

    public NotImplementedException()
    {
        super();
    }

    public NotImplementedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotImplementedException(String message)
    {
        super(message);
    }

    public NotImplementedException(Throwable cause)
    {
        super(cause);
    }

}
