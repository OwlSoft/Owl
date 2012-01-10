package at.owlsoft.owl.communication.ejb;

import at.owlsoft.owl.business.OwlApplicationContext;

public abstract class ApiBase implements IApiBase, ILocalApiBase
{
    private OwlApplicationContext _context;

    public OwlApplicationContext getContext()
    {
        return _context;
    }

    public void setContext(OwlApplicationContext context)
    {
        _context = context;
    }

    @Override
    public void setContext(Object context)
    {
        if (context instanceof OwlApplicationContext)
        {
            setContext((OwlApplicationContext) context);
        }

    }

    @Override
    public void reset()
    {
    }

}
