package at.owlsoft.owl.model;

public class SearchField implements ISearchField
{
    private static final long serialVersionUID = 1250114775836058087L;
    private String            _key;
    private String            _value;

    public SearchField()
    {
    }

    public SearchField(String key, String value)
    {
        super();
        _key = key;
        _value = value;
    }

    @Override
    public String getKey()
    {
        return _key;
    }

    @Override
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
