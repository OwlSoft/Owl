package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;

public interface IReservationApi extends Remote
{
    public void newReservation() throws RemoteException;

    public ISystemUser setCustomer(int cardId) throws RemoteException;

    public IReservation getReservation() throws RemoteException;

    public IMedium setMedium(int mediumId) throws RemoteException;

    public List<ValidationMessage> getValidationMessages()
            throws RemoteException;

    public void setStartDate(Date time) throws RemoteException;

    public void setDuration(int duration) throws RemoteException;

    public boolean store() throws RemoteException;
}