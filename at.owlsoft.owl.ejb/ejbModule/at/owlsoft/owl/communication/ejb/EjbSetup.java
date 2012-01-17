package at.owlsoft.owl.communication.ejb;

import org.apache.log4j.Logger;

import at.owlsoft.owl.communication.rmi.IApiFactory;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldCategory;

public class EjbSetup
{
    private static final Logger logger = Logger.getLogger(EjbSetup.class);
    private static boolean      _setupDone;

    public static void setup()
    {
        logger.warn("setup");
        if (_setupDone)
        {
            return;
        }

        try
        {
            logger.warn("Setting security manager");
            System.setSecurityManager(new EJBAllPermissionSecurityManager());
            logger.warn("securitymanager set");

            logger.warn("setting codebase");

            StringBuilder codeBase = new StringBuilder();
            codeBase.append(getCodeBaseUrl(SearchFieldCategory.class));
            codeBase.append(" ");
            codeBase.append(getCodeBaseUrl(ISearchFieldCategory.class));
            codeBase.append(" ");
            codeBase.append(getCodeBaseUrl(IApiFactory.class));

            System.setProperty("java.rmi.server.codebase", codeBase.toString());

            logger.warn("codebase set");

        }
        catch (Exception e)
        {
            logger.warn("Could not set security manager");
        }

        _setupDone = true;
    }

    private static Object getCodeBaseUrl(Class<?> clz)
    {
        return clz.getProtectionDomain().getCodeSource().getLocation()
                .toString();
    }
}
