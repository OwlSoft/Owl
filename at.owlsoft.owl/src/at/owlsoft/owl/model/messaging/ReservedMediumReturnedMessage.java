package at.owlsoft.owl.model.messaging;

import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.accounting.Reservation;

public class ReservedMediumReturnedMessage extends Message
{
    private static final long serialVersionUID = 544487840016729498L;

    private Rental            _returnedRental;
    private Reservation       _reservation;

    @Override
    public String getDisplayString()
    {
        return "An exemplar of '"
                + _returnedRental.getMediumExemplar().getMedium().getName()
                + "' was returned by '"
                + _returnedRental.getCustomer().getUsername() + "'"
                + ", the user '" + _reservation.getCustomer()
                + "' has reserved it, notify him!";
    }

    @Override
    public String getEventType()
    {
        return IMessageEventStrings.RESERVED_MEDIUM_RETURNED;
    }

    public ReservedMediumReturnedMessage()
    {
    }

    public ReservedMediumReturnedMessage(Rental returnedRental,
            Reservation reservation)
    {
        super();
        _returnedRental = returnedRental;
        _reservation = reservation;
    }

}
