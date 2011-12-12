import java.util.Date;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import at.owlsoft.owl.communication.corba.ICorbaApiFactory;
import at.owlsoft.owl.communication.corba.ICorbaApiService;
import at.owlsoft.owl.communication.corba.ICorbaApiServiceHelper;
import at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi;
import at.owlsoft.owl.communication.corba.ICorbaRentalApi;
import at.owlsoft.owl.communication.corba.ICorbaSearchApi;
import at.owlsoft.owl.communication.corba.ICorbaSystemUserApi;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;

public class corbaClientTest
{

    Logger           log4j = Logger.getLogger(corbaClientTest.class);

    ICorbaApiFactory _apiFactory;

    @Before
    public void preTest()
    {
        try
        {
            ICorbaApiService _service;
            ORB orb = ORB.init(new String[0], null);

            log4j.debug("Client: ORB connected");

            org.omg.CORBA.Object ref = orb
                    .resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(ref);

            log4j.debug("Client: ns fetched");

            String name = "ApiService";
            _service = ICorbaApiServiceHelper.narrow(ncRef.resolve_str(name));

            log4j.debug("Client: narrowed");

            _apiFactory = _service.createApiFactory();

            Assert.assertNotNull(_apiFactory);

            authenticate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.assertFalse("Exception " + e.getClass() + " thrown", true);
        }
    }

    public void authenticate()
    {
        ICorbaAuthenticationApi authenticationApi = _apiFactory
                .createAuthenticationApi();

        authenticationApi.login("dni7431", "******");

        Assert.assertEquals(
                authenticationApi.getRolesForCurrentUser().length > 0, true);

        log4j.debug("user authenticated");
    }

    private int _exemplarId;

    @Test
    public void searchMediumTest()
    {
        ICorbaSearchApi _searchApi = _apiFactory.createSearchApi();

        UUID uniqueId = UUID.randomUUID();

        _searchApi.setSearchFieldData(uniqueId.toString(), "_isbn13",
                "978-3-15-000001-4");

        ICorbaMedium[] searchResult = _searchApi.search();

        Assert.assertNotNull(searchResult);
        Assert.assertEquals(searchResult.length == 1, true);

        _exemplarId = searchResult[0].getMediumExemplars()[0].getExemplarID();

        log4j.debug("Exemplar Id found: " + _exemplarId);

    }

    @Test
    public void createRentalTest()
    {

        ICorbaRentalApi rentalApi = _apiFactory.createRentalApi();
        log4j.debug("rental api loaded");

        int cardId = 0;
        ICorbaSystemUserApi systemUserApi = _apiFactory.createSystemUserApi();

        rentalApi.setCustomer(cardId);

        ICorbaSystemUser user = systemUserApi.getSystemUserByCardID(cardId);

        for (ICorbaActivity activity : user.getActivities())
        {
            if (activity.getMediumExemplar() != null)
            {
                if (activity.getMediumExemplar().getExemplarID() == _exemplarId)
                {
                    try
                    {
                        rentalApi.returnRental(activity.getUUID());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        rentalApi.newRental();
        rentalApi.setCustomer(0);
        rentalApi.setMediumExemplar(0);
        rentalApi.setStartDate(new Date().getTime());
        rentalApi.getRental();

        log4j.debug("trying to save rental");
        rentalApi.store();
        log4j.debug("rental saved");

        ICorbaRental rental = rentalApi.getRental();

        log4j.debug("rent book for user:" + rental.getCustomer().getFirstName()
                + " " + rental.getCustomer().getLastName() + "End Date: "
                + rental.getEndDate());

    }

    @Test
    public void createReservationTest()
    {
        ICorbaRentalApi rentalApi = _apiFactory.createRentalApi();
        log4j.debug("rental api loaded");

        int cardId = 0;
        ICorbaSystemUserApi systemUserApi = _apiFactory.createSystemUserApi();

        rentalApi.setCustomer(cardId);

        ICorbaSystemUser user = systemUserApi.getSystemUserByCardID(cardId);

        for (ICorbaActivity activity : user.getActivities())
        {
            if (activity.getMediumExemplar() != null)
            {
                if (activity.getMediumExemplar().getExemplarID() == _exemplarId)
                {
                    rentalApi.createNewExtension(activity.getUUID());
                }
            }

        }
        log4j.debug("extension created");

        rentalApi.store();

    }
}
