package at.owlsoft.owl.model;

public class SearchField
{
    private String _key;
    private String _value;

    public SearchField()
    {

    }

    public SearchField(String key, String value)
    {
        super();
        _key = key;
        _value = value;
    }

    public String getKey()
    {
        return _key;
    }

    public String getValue()
    {
        return _value;
    }

    public void setValue(String value)
    {
        _value = value;
    }

    public void setKey(String key)
    {
        _key = key;
    }

}
