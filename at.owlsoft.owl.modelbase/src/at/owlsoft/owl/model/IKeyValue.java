package at.owlsoft.owl.model;

public interface IKeyValue<K,V>
{
    K getKey();
    void setKey(K key);
    V getValue();
    void setValue(V value);
}
