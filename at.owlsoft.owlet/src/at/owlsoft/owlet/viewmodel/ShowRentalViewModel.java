package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.ejb.RentalApiRemote;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owlet.context.EjbContext;

public class ShowRentalViewModel
{
    private RentalApiRemote _currentRentalApi;

    private ISystemUser     _systemUser;
    private List<IRental>   _rentals;
    private IRental         _activeRental;
    private Integer         _userCardId;

    /**
     * @return the activeRental
     */
    public IRental getActiveRental()
    {
        return _activeRental;
    }

    /**
     * @return the userCardId
     */
    public Integer getUserCardId()
    {
        return _userCardId;
    }

    /**
     * @param userCardId the userCardId to set
     */
    public void setUserCardId(Integer userCardId)
    {
        _userCardId = userCardId;
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
    public RentalApiRemote getCurrentRental()
    {
        return _currentRentalApi;
    }

    /**
     * @param currentRental the currentRental to set
     */
    public void setCurrentRental(RentalApiRemote currentRental)
    {
        _currentRentalApi = currentRental;
    }

    public void initialize() throws InvalidOperationException
    {
    }

    public ShowRentalViewModel()
    {
        try
        {
            _currentRentalApi = EjbContext.getInstance().getFactory()
                    .createRentalApi();
        }
        catch (NamingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void reloadSystemUser()
    {
        try
        {
            setSystemUser(_currentRentalApi
                    .getRentalsForSystemUserCardId(_userCardId));
            updateDefinitions();
        }
        catch (RemoteException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateDefinitions() throws RemoteException
    {
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

        if (_activeRental != null)
        {
            for (IRental rental : _rentals)
            {
                if (rental.getUUID().equals(_activeRental.getUUID()))
                {
                    _activeRental = rental;
                }
            }

        }

    }

    public void createNewExtension(IRental activeRental)
    {
        try
        {
            _currentRentalApi.createNewExtension(activeRental.getUUID());
            reloadSystemUser();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void returnRental(IRental activeRental)
    {
        _currentRentalApi.returnRental(activeRental.getUUID());
        reloadSystemUser();

    }
}
