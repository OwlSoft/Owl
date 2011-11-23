package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.rmi.IRentalApi;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owlet.context.RmiContext;

public class ShowRentalViewModel
{
    private IRentalApi    _currentRentalApi;

    private ISystemUser   _systemUser;
    private List<IRental> _rentals;
    private IRental       _activeRental;

    /**
     * @return the activeRental
     */
    public IRental getActiveRental()
    {
        return _activeRental;
    }

    /**
     * @param activeRental the activeRental to set
     */
    public void setActiveRental(IRental activeRental)
    {
        _activeRental = activeRental;
    }

    /**
     * @return the rentals
     */
    public List<IRental> getRentals()
    {
        return _rentals;
    }

    /**
     * @param rentals the rentals to set
     */
    public void setRentals(List<IRental> rentals)
    {
        _rentals = rentals;
    }

    /**
     * @return the systemUser
     */
    public ISystemUser getSystemUser()
    {
        return _systemUser;
    }

    /**
     * @param systemUser the systemUser to set
     */
    public void setSystemUser(ISystemUser systemUser)
    {
        _systemUser = systemUser;
    }

    /**
     * @return the currentRental
     */
    public IRentalApi getCurrentRental()
    {
        return _currentRentalApi;
    }

    /**
     * @param currentRental the currentRental to set
     */
    public void setCurrentRental(IRentalApi currentRental)
    {
        _currentRentalApi = currentRental;
    }

    public void initialize() throws InvalidOperationException
    {
        try
        {
            setCurrentRental(RmiContext.getInstance().getFactory()
                    .createRentalApi());
            updateDefinitions();
        }
        catch (RemoteException e)
        {
            throw new InvalidOperationException(
                    "Could not establish connection to server", e);
        }

    }

    private void updateDefinitions() throws RemoteException
    {
        _systemUser = _currentRentalApi.getRentalsForSystemUserCardId(0);
        _rentals = new ArrayList<IRental>();

        if (_systemUser != null)
        {
            for (int i = 0; i < _systemUser.getActivities().size(); i++)
            {
                IActivity activity = _systemUser.getActivity(i);

                if (activity instanceof IRental)
                {
                    _rentals.add((IRental) activity);
                }
            }
        }

    }

    public void createNewExtension(IRental activeRental)
    {
        _currentRentalApi.createNewExtension(activeRental.getUUID());

    }

    public void returnRental(IRental activeRental)
    {
        _currentRentalApi.returnRental(activeRental.getUUID());

    }
}
