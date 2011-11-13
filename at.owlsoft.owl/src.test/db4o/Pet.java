package db4o;

public class Pet
{
    private String _name;

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public Pet(String name)
    {
        super();
        _name = name;
    }

}
