package at.owlsoft.owl.model;

public class SearchFieldDefinition implements ISearchFieldDefinition
{
    private static final long serialVersionUID = 6935396387381422618L;

    private String            _key;
    private String            _label;

    public SearchFieldDefinition()
    {
    }

    public SearchFieldDefinition(String key, String label)
    {
        super();
        _key = key;
        _label = label;
    }

    @Override
    public String getKey()
    {
        return _key;
    }

    public void setKey(String key)
    {
        _key = key;
    }

    @Override
    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String label)
    {
        _label = label;
    }

}
