package at.owlsoft.owl.model;

import java.util.Map.Entry;

public class MyEntry<K, V> implements Entry<K, V>
{

    K key;
    V value;

    public MyEntry(K key)
    {
        this.key = key;
    }

    public MyEntry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey()
    {
        return this.key;
    }

    @Override
    public V getValue()
    {
        return this.value;
    }

    @Override
    public V setValue(V value)
    {
        V old = this.value;
        this.value = value;
        return old;
    }

}
