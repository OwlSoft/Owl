package at.owlsoft.owl.communication;

import javax.ejb.Local;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;

/**
 * Session Bean implementation class OwlContextBean
 */
@Stateful
@Local(OwlContextBeanLocal.class)
public class OwlContextBean implements OwlContextBeanLocal
{
    private OwlApplicationContext _context;

    public OwlApplicationContext getContext()
    {
        return _context;
    }

    public OwlContextBean()
    {
        _context = new OwlApplicationContext();
    }
}
