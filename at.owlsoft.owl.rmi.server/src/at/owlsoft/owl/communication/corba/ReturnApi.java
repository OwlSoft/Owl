package at.owlsoft.owl.communication.corba;

public class ReturnApi extends ICorbaReturnApiPOA
{

    private ApiFactory _factory;

    public ReturnApi(ApiFactory factory)
    {
        _factory = factory;
    }

}
