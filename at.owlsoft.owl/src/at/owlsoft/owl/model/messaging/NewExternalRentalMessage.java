package at.owlsoft.owl.model.messaging;

import at.owlsoft.owl.model.accounting.Rental;

public class NewExternalRentalMessage extends Message
{
    private static final long serialVersionUID = -3699933286285392899L;

    private Rental            _rental;

    public Rental getRental()
    {
        return _rental;
    }

    public NewExternalRentalMessage(Rental rental)
    {
        super();
        _rental = rental;
    }

    @Override
    public String getDisplayString()
    {
        return "The external user '" + _rental.getCustomer().getUsername()
                + "' rented '"
                + _rental.getMediumExemplar().getMedium().getName() + "'";
    }

    @Override
    public String getEventType()
    {
        return IMessageEventStrings.NEW_EXTERNAL_RENTAL;
    }

}
