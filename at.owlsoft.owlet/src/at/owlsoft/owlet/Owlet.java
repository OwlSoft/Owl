/*
 * This file is part of OwlSoft Owlet.
 *
 *  OwlSoft Owlet is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  alphaTab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with alphaTab.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.owlsoft.owlet;

import java.awt.Font;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Theme;
import org.apache.pivot.wtk.Window;

import at.owlsoft.owl.communication.rmi.AllPermissionSecurityManager;
import at.owlsoft.owl.communication.rmi.IApiService;
import at.owlsoft.owlet.ui.MainWindow;
import at.owlsoft.owlet.ui.ViewController;

/**
 * This is the main application class of the Owlet client.
 */
public class Owlet implements Application
{
    private static final String LOG4J_CONFIGURATION = "/log4j.xml";
    private static final Logger logger              = Logger.getLogger(Owlet.class);

    private Window              _mainWindow         = null;

    /**
     * @see org.apache.pivot.wtk.Application#resume()
     */
    @Override
    public void resume() throws Exception
    {
    }

    /**
     * @see org.apache.pivot.wtk.Application#shutdown(boolean)
     */
    @Override
    public boolean shutdown(boolean arg0) throws Exception
    {
        logger.trace("Shutdown application");
        if (_mainWindow != null)
        {
            _mainWindow.close();
        }
        return false;
    }

    /**
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display,
     *      org.apache.pivot.collections.Map)
     */
    @Override
    public void startup(Display display, Map<String, String> args)
            throws Exception
    {
        // Load custom styles
        logger.trace("Setup RMI");
        String host = args.containsKey("host") ? args.get("host") : "localhost";
        int port = args.containsKey("port") ? Integer.parseInt("port")
                : Registry.REGISTRY_PORT;
        String serviceName = args.containsKey("service") ? args.get("service")
                : IApiService.DEFAULT_RMI_NAME;
        // EjbContext.initialize(host, port, serviceName);
        System.setProperty("java.naming.provider.url", host + ":" + port);

        // Load custom styles
        logger.trace("Setup GUI Styles");
        ApplicationContext
                .applyStylesheet("/at/owlsoft/owlet/ui/OwlStyles.json");
        Theme.getTheme().setFont(new Font("Arial", Font.PLAIN, 12));

        // Startup mainwindow
        logger.trace("Loading UI");
        BXMLSerializer bxmlSerializer = new BXMLSerializer();

        ViewController.getInstance();
        _mainWindow = (Window) bxmlSerializer.readObject(MainWindow.class,
                "MainWindow" + ViewController.PIVOT_FILE_EXTENSION);

        // "EasyDietMainWindow" + ViewController.PIVOT_FILE_EXTENSION);

        _mainWindow.open(display);
    }

    /**
     * @see org.apache.pivot.wtk.Application#suspend()
     */
    @Override
    public void suspend() throws Exception
    {
        logger.trace("Suspending Owl");
    }

    /**
     * The applications main entry point.
     * 
     * @param args The console arguments formatted in --key=value per element.
     */
    public static void main(String[] args)
    {
        System.setSecurityManager(new AllPermissionSecurityManager());
        DOMConfigurator.configure(Owlet.class.getResource(LOG4J_CONFIGURATION));

        java.util.List<String> s = new ArrayList<String>(Arrays.asList(args));
        s.add("--maximized=true");
        DesktopApplicationContext.main(Owlet.class, s.toArray(new String[0]));
    }
}
