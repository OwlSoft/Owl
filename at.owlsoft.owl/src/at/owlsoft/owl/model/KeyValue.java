package at.owlsoft.owl.model;

public class KeyValue<K, V> implements IKeyValue<K, V>
{
    private K _key;
    private V _value;

    @Override
    public K getKey()
    {
        // TODO Auto-generated method stub
        return _key;
    }

    @Override
    public void setKey(K key)
    {
        // TODO Auto-generated method stub
        _key = key;

    }

    @Override
    public V getValue()
    {
        // TODO Auto-generated method stub
        return _value;
    }

    @Override
    public void setValue(V value)
    {
        // TODO Auto-generated method stub
        _value = value;
    }

}
