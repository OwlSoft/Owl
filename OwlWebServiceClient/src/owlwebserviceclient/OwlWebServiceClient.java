/*
 */
package owlwebserviceclient;

import at.owlsoft.owl.webserviceclient.NoPermissionException_Exception;
import at.owlsoft.owl.webserviceclient.WsMedium;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class OwlWebServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoPermissionException_Exception {
        
        String session = getSession("mika.mustermensch", "password");
        System.out.println("Loging in as Mika Mustermensch with SessionID: "+session);
        List<WsMedium> media = searchMedium(session, "Faust", null, null);
        for(WsMedium medium:media){
            System.out.println(medium.getName()+", "+medium.getMediumID()+", "+medium.getPublisher()+", "+medium.getMediumExemplaCount());
        }
        System.out.println("Reserving Faust: "+reserveMedium(session, 0));
        System.out.println("Rent Faust: " +rentMedium(session, 0));
        
    }

    private static String getSession(java.lang.String username, java.lang.String password) throws NoPermissionException_Exception {
        at.owlsoft.owl.webserviceclient.OwlWebServiceService service = new at.owlsoft.owl.webserviceclient.OwlWebServiceService();
        at.owlsoft.owl.webserviceclient.OwlWebService port = service.getOwlWebServicePort();
        return port.getSession(username, password);
    }

    private static boolean rentMedium(java.lang.String session, int mediumId) throws NoPermissionException_Exception {
        at.owlsoft.owl.webserviceclient.OwlWebServiceService service = new at.owlsoft.owl.webserviceclient.OwlWebServiceService();
        at.owlsoft.owl.webserviceclient.OwlWebService port = service.getOwlWebServicePort();
        return port.rentMedium(session, mediumId);
    }

    private static boolean reserveMedium(java.lang.String session, int mediumId) throws NoPermissionException_Exception {
        at.owlsoft.owl.webserviceclient.OwlWebServiceService service = new at.owlsoft.owl.webserviceclient.OwlWebServiceService();
        at.owlsoft.owl.webserviceclient.OwlWebService port = service.getOwlWebServicePort();
        return port.reserveMedium(session, mediumId);
    }

    private static java.util.List<at.owlsoft.owl.webserviceclient.WsMedium> searchMedium(java.lang.String session, java.lang.String title, java.lang.String author, java.lang.String publisher) {
        at.owlsoft.owl.webserviceclient.OwlWebServiceService service = new at.owlsoft.owl.webserviceclient.OwlWebServiceService();
        at.owlsoft.owl.webserviceclient.OwlWebService port = service.getOwlWebServicePort();
        return port.searchMedium(session, title, author, publisher);
    }

    
}
