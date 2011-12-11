package at.owlsoft.owl.business;

import java.util.Date;
import java.util.List;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.messaging.NewOverdueMessage;

public class OverdueCheckController extends ControllerBase implements Runnable
{
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
        IRentalDao dao = DaoManager.getInstance().getRentalDao();
        List<Rental> rentals = dao.getAll();

        Date now = new Date();
        for (Rental rental : rentals)
        {
            if (rental.getEndDate().before(now))
            {
                notifyOverdue(dao, rental);
            }
        }
    }

    private void notifyOverdue(IRentalDao dao, Rental rental)
    {
        rental.setReminderCount(rental.getReminderCount() + 1);
        dao.store(rental);

        NewOverdueMessage overdue = new NewOverdueMessage(rental);
        getContext().getMessageController().addMessage(overdue);
    }
}
