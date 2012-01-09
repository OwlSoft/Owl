package at.owlsoft.owl.communication;

import javax.annotation.PostConstruct;
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

    @Override
    public OwlApplicationContext getContext()
    {
        return _context;
    }

    @PostConstruct
    public void init()
    {
        System.out
                .println("OwlContextBean.OwlContextBean(): new context created");
        _context = new OwlApplicationContext();
    }

    public OwlContextBean()
    {

    }
}
