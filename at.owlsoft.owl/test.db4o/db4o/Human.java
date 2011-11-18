package db4o;

import java.util.List;

public class Human implements IHuman
{
    private List<Pet> _slaves;
    private String    _name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public List<Pet> getSlaves()
    {
        return _slaves;
    }

    public void setSlaves(List<Pet> slaves)
    {
        _slaves = slaves;
    }

    public Human(List<Pet> slaves, String name)
    {
        super();
        _slaves = slaves;
        _name = name;
    }

}
