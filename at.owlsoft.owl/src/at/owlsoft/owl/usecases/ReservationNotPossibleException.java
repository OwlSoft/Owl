package at.owlsoft.owl.usecases;

public class ReservationNotPossibleException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 7667943875246340852L;

    public ReservationNotPossibleException()
    {
        super();
    }

    public ReservationNotPossibleException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }

    public ReservationNotPossibleException(String arg0)
    {
        super(arg0);
    }

    public ReservationNotPossibleException(Throwable arg0)
    {
        super(arg0);
    }

}
