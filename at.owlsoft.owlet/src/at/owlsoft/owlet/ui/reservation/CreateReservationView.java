package at.owlsoft.owlet.ui.reservation;

import javax.swing.JButton;

import at.owlsoft.owlet.ui.OwletView;

public class CreateReservationView extends OwletView
{
    private static final String KEY              = "CREATE_RESERVATION";
    private static final long   serialVersionUID = -7398460880181366123L;

    public CreateReservationView()
    {
        super(KEY, "Create new Reservation");
    }

    @Override
    protected void initializeComponents()
    {
        add(new JButton(getTitle()));
    }
}
