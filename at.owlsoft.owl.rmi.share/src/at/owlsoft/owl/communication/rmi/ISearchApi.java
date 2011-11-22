package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.ISearchFieldCategory;

public interface ISearchApi extends Remote
{
    List<ISearchFieldCategory> getSearchFieldCategories()
            throws RemoteException;
}