package at.owlsoft.owlet.ui.rental;

import javax.swing.JButton;

import at.owlsoft.owlet.ui.OwletView;

public class CreateRentalView extends OwletView
{
    private static final String KEY              = "CREATE_RENTAL";
    private static final long   serialVersionUID = -4899345618975421730L;

    public CreateRentalView()
    {
        super(KEY, "Create new Rental");
    }

    @Override
    protected void initializeComponents()
    {
        add(new JButton(getTitle()));
    }
}
