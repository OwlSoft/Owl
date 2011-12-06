package at.owlsoft.owl.communication.corba;

public class ReservationApi extends ICorbaReservationApiPOA
{

    private ApiFactory _factory;

    public ReservationApi(ApiFactory factory)
    {
        _factory = factory;
    }

}
