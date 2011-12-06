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
package at.owlsoft.owl.communication.corba;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 * 
 */
public class CorbaApiService extends ICorbaApiServicePOA
{
    private POA _rootPoa;
    private ORB _orb;

    public CorbaApiService(POA poa)
    {
        super();
        _rootPoa = poa;
    }

    @Override
    public ICorbaApiFactory createApiFactory()
    {
        try
        {
            ApiFactory apiFactory = new ApiFactory();
            apiFactory.setRootPOA(_rootPoa);

            org.omg.CORBA.Object ref = _rootPoa
                    .servant_to_reference(apiFactory);
            return ICorbaApiFactoryHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void setOrb(ORB orb)
    {
        _orb = orb;
    }

}
