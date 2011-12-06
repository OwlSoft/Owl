package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;

import at.owlsoft.owl.corbamodel.ICorbaSearchFieldPOA;
import at.owlsoft.owl.model.ISearchField;

public class CorbaSearchField extends ICorbaSearchFieldPOA
{

    private ISearchField _searchField;
    private POA          _rootPOA;

    public void setSearchField(ISearchField searchField)
    {
        _searchField = searchField;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public String getKey()
    {
        return _searchField.getKey();
    }

    @Override
    public String getValue()
    {
        return _searchField.getValue();
    }

}
