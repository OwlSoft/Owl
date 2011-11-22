package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.SearchField;

public class SearchApi extends ApiBase implements ISearchApi
{
    private SearchFieldDefinitionController _controller;

    private Map<UUID, SearchField>          _searchFields;

    public SearchApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = new SearchFieldDefinitionController();
        _searchFields = new HashMap<UUID, SearchField>();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8248250017928395289L;

    @Override
    public List<ISearchFieldCategory> getSearchFieldCategories()
            throws RemoteException
    {
        return new ArrayList<ISearchFieldCategory>(
                _controller.getAllCategories());
    }

    @Override
    public ISearchField addNewSearchField(UUID uniqueId)
    {
        SearchField newField = new SearchField();
        _searchFields.put(uniqueId, newField);
        dump();
        return newField;
    }

    @Override
    public void removeSearchField(UUID uniqueId)
    {
        _searchFields.remove(uniqueId);
        dump();
    }

    @Override
    public void setSearchFieldData(UUID uniqueId, String key, String value)
    {
        SearchField field = _searchFields.get(uniqueId);

        if (field != null)
        {
            field.setKey(key);
            field.setValue(value);
        }
    }

    private void dump()
    {
        System.out.println("Dump!");
        for (Entry<UUID, SearchField> entry : _searchFields.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println("    k:" + entry.getValue().getKey());
            System.out.println("    v:" + entry.getValue().getValue());
            System.out.println("---");
        }
    }
}
