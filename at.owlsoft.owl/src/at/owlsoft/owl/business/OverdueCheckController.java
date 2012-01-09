package at.owlsoft.owl.business;

import java.util.Date;
import java.util.List;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.messaging.NewOverdueMessage;

public class OverdueCheckController extends ControllerBase implements Runnable
{
    /**
     * 
     */
    private static final long serialVersionUID = 5501397618257210230L;

    private Thread  _checkerThread;

    private int     _interval;
    private boolean _running;

    public OverdueCheckController(OwlApplicationContext context)
    {
        super(context);
        _interval = 1000 * 60 * 1;
    }

    public void startChecker()
    {
        _running = true;
        _checkerThread = new Thread(this);
        _checkerThread.start();
    }

    public void stopChecker()
    {
        _running = false;
        _checkerThread.interrupt();
    }

    @Override
    public void run()
    {
        while (_running)
        {
            checkForOverdue();

            try
            {
                Thread.sleep(_interval);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    private void checkForOverdue()
    {
        System.out.println("Checking overdues");
        IRentalDao dao = DaoManager.getInstance().getRentalDao();
        List<Rental> rentals = dao.getAll();

        int overdues = 0;
        Date now = new Date();
        for (Rental rental : rentals)
        {

            if (!rental.getLastActivityStatusEntry().getActivityStatus()
                    .equals(ActivityStatus.Closed)
                    && !rental.getLastActivityStatusEntry().getActivityStatus()
                            .equals(ActivityStatus.Returned)
                    && rental.getEndDate().before(now))
            {
                notifyOverdue(dao, rental);
                overdues++;
            }
        }
        System.out.println("Found and notified " + overdues + " overdues!");
    }

    private void notifyOverdue(IRentalDao dao, Rental rental)
    {
        ActivityStatusEntry entry = new ActivityStatusEntry(new Date(), rental,
                ActivityStatus.Overdue);
        rental.addActivityStatusEntry(entry);
        dao.store(rental);

        NewOverdueMessage overdue = new NewOverdueMessage(rental);
        getContext().getMessageController().addMessage(overdue);
    }
}
