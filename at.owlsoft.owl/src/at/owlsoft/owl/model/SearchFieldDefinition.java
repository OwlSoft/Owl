package at.owlsoft.owl.model;

public class SearchFieldDefinition implements ISearchFieldDefinition
{
    private static final long   serialVersionUID = 6935396387381422618L;

    private String              _key;
    private String              _label;
    private SearchFieldDataType _keyType;

    public SearchFieldDefinition()
    {
        super();
    }

    public SearchFieldDefinition(String key, String label,
            SearchFieldDataType type)
    {
        this();
        _key = key;
        _label = label;
        _keyType = type;
    }

    /**
     * @return the keyType
     */
    public SearchFieldDataType getKeyType()
    {
        return _keyType;
    }

    /**
     * @param keyType the keyType to set
     */
    public void setKeyType(SearchFieldDataType keyType)
    {
        _keyType = keyType;
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
