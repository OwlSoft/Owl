package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldCategory;
import at.owlsoft.owl.model.media.IMedium;

public interface ISearchApi extends Remote
{
    List<ISearchFieldCategory> getSearchFieldCategories()
            throws RemoteException;

    ISearchField addNewSearchField(UUID uniqueId) throws RemoteException;

    void removeSearchField(UUID uniqueId) throws RemoteException;

    void setSearchFieldData(UUID uniqueId, String key, String value)
            throws RemoteException;

    List<IMedium> search() throws RemoteException;
}