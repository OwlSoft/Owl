package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;

import at.owlsoft.owl.corbamodel.ISearchFieldDefinitionPOA;
import at.owlsoft.owl.model.ISearchFieldDefinition;

public class CorbaSearchFieldDefinition extends ISearchFieldDefinitionPOA
{

    private ISearchFieldDefinition _definition;
    private POA                    _rootPOA;

    @Override
    public String getKey()
    {
        return _definition.getKey();
    }

    @Override
    public String getLabel()
    {
        return _definition.getLabel();
    }

    public void setDefinition(
            at.owlsoft.owl.model.ISearchFieldDefinition definition)
    {
        _definition = definition;

    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;

    }

}
