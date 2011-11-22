package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.model.ISearchFieldCategory;

public class SearchApi extends ApiBase implements ISearchApi
{
    private SearchFieldDefinitionController _controller;

    public SearchApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = new SearchFieldDefinitionController();
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

}
