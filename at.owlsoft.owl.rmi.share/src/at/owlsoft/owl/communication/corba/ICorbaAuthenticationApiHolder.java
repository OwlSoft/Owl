package at.owlsoft.owl.communication.corba;

/**
* at/owlsoft/owl/communication/corba/ICorbaAuthenticationApiHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class ICorbaAuthenticationApiHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi value = null;

  public ICorbaAuthenticationApiHolder ()
  {
  }

  public ICorbaAuthenticationApiHolder (at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.type ();
  }

}
