package at.owlsoft.owl.corbamodel.user;

/**
* at/owlsoft/owl/corbamodel/user/CorbaSystemUserStatusHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class CorbaSystemUserStatusHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus value = null;

  public CorbaSystemUserStatusHolder ()
  {
  }

  public CorbaSystemUserStatusHolder (at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatusHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatusHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatusHelper.type ();
  }

}
