package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategoryPOA;
import at.owlsoft.owl.corbamodel.ISearchFieldDefinition;
import at.owlsoft.owl.corbamodel.ISearchFieldDefinitionHelper;
import at.owlsoft.owl.model.ISearchFieldCategory;

public class CorbaSearchFieldCategory extends ICorbaSearchFieldCategoryPOA
{

    private POA                  _rootPOA;
    private ISearchFieldCategory _searchFieldCategory;

    public void setSearchFieldCategory(ISearchFieldCategory searchFieldCategory)
    {
        _searchFieldCategory = searchFieldCategory;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public String getLabel()
    {
        return _searchFieldCategory.getLabel();
    }

    @Override
    public ISearchFieldDefinition[] getSearchFieldDefinitions()
    {
        try
        {
            ISearchFieldDefinition[] temp = new ISearchFieldDefinition[_searchFieldCategory
                    .getSearchFields().size()];
            int index = 0;
            for (at.owlsoft.owl.model.ISearchFieldDefinition definition : _searchFieldCategory
                    .getSearchFields())
            {
                CorbaSearchFieldDefinition cDefinition = new CorbaSearchFieldDefinition();
                cDefinition.setDefinition(definition);
                cDefinition.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cDefinition);
                temp[index] = ISearchFieldDefinitionHelper.narrow(ref);
                index++;
            }
            return temp;
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

}
