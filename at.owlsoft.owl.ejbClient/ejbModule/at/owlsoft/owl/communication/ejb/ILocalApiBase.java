package at.owlsoft.owl.communication.ejb;

public interface ILocalApiBase
{
    void setContext(Object context);

    Object getRemoteObject();
}
