/*
 * This file is part of OwlSoft Owl.
 *
 *  OwlSoft Owl is free software: you can redistribute it and/or modify
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
package at.owlsoft.owl;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This is the main application class of the Owl service.
 */
public class Owl
{
    private static final String LOG4J_CONFIGURATION = "/log4j.xml";
    private static final Logger logger              = Logger.getLogger(Owl.class);

    /**
     * This is the main application entry point.
     * 
     * @param args the commandline argument passed to the application
     */
    public static void main(String[] args)
    {
        DOMConfigurator.configure(Owl.class.getResource(LOG4J_CONFIGURATION));

        logger.debug("Hoot Hoot");
        logger.debug("Hoot Hoot");
        logger.debug("Hoot Hoot");

    }
}
