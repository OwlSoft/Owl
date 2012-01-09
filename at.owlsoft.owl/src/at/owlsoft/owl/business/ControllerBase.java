package at.owlsoft.owl.business;

import java.io.Serializable;

public abstract class ControllerBase implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 7878593861738394095L;
    private OwlApplicationContext _context;

    protected OwlApplicationContext getContext()
    {
        return _context;
    }

    public ControllerBase(OwlApplicationContext context)
    {
        super();
        _context = context;
    }
}
