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

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.corbamodel.media.ITagPOA;
import at.owlsoft.owl.corbamodel.media.TagType;
import at.owlsoft.owl.model.media.ITag;

public class CorbaTag extends ITagPOA
{

    private ITag _tag;
    private POA  _rootPOA;

    @Override
    public String getValue()
    {
        return _tag.getValue();
    }

    @Override
    public TagType getTagType()
    {
        if (_tag.getTagType().equals(TagType.Category))
        {
            return TagType.Category;
        }

        switch (_tag.getTagType())
        {
            case Category:
                return TagType.Category;
            case AgeRestriction:
                return TagType.AgeRestriction;
            case MediaMeta:
                return TagType.MediaMeta;
            default:
                throw new RuntimeException("Unknown TagType");
        }
    }

    @Override
    public ICorbaMedium getMedium()
    {
        try
        {
            CorbaMedium cMedium = new CorbaMedium();
            cMedium.setMedium(_tag.getMedium());
            cMedium.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref;
            ref = _rootPOA.servant_to_reference(cMedium);
            return ICorbaMediumHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setTag(ITag tag)
    {
        _tag = tag;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }
}
