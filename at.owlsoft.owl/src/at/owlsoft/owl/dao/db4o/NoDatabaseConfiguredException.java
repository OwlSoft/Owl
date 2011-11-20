package at.owlsoft.owl.dao.db4o;

public class NoDatabaseConfiguredException extends RuntimeException
{

    public NoDatabaseConfiguredException(String string)
    {
        super(string);
    }

    public NoDatabaseConfiguredException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public NoDatabaseConfiguredException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public NoDatabaseConfiguredException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
