package at.owlsoft.owl.corbamodel.user;

/**
* at/owlsoft/owl/corbamodel/user/ICorbaSystemUserTransactionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class ICorbaSystemUserTransactionHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransaction value = null;

  public ICorbaSystemUserTransactionHolder ()
  {
  }

  public ICorbaSystemUserTransactionHolder (at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransaction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionHelper.type ();
  }

}