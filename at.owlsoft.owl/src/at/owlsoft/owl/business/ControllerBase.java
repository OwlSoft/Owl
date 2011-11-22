package at.owlsoft.owl.business;

public abstract class ControllerBase
{
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
