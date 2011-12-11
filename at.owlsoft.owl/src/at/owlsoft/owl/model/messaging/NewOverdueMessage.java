package at.owlsoft.owl.model.messaging;

import at.owlsoft.owl.model.accounting.Rental;

public class NewOverdueMessage extends Message
{
    private static final long serialVersionUID = -3699933286285392899L;

    private Rental            _rental;

    public Rental getRental()
    {
        return _rental;
    }

    public NewOverdueMessage(Rental rental)
    {
        super();
        _rental = rental;
    }

    @Override
    public String getDisplayString()
    {
        return "The activity '" + _rental.getUUID() + "' of '"
                + _rental.getCustomer().getUsername()
                + "' is overdued (This is number '"
                + _rental.getReminderCount() + "').!";
    }

    @Override
    public String getEventType()
    {
        return IMessageEventStrings.NEW_RENTAL_OVERDUE;
    }

}
