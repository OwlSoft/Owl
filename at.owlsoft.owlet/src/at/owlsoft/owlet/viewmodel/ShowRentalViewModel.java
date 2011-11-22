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
    private IRentalApi _currentRentalApi;

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
        ISystemUser user = _currentRentalApi.getRentalsForSystemUserCardId(0);
        List<IRental> rentals = new ArrayList<IRental>();

        if (user != null)
        {
            for (int i = 0; i < user.getActivities().size(); i++)
            {
                IActivity activity = user.getActivity(i);

                if (activity instanceof IRental)
                {
                    rentals.add((IRental) activity);
                }
            }
        }

    }
}
