package at.owlsoft.owl.corbamodel;

/**
* at/owlsoft/owl/corbamodel/NoPermissionExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class NoPermissionExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.NoPermissionException value = null;

  public NoPermissionExceptionHolder ()
  {
  }

  public NoPermissionExceptionHolder (at.owlsoft.owl.corbamodel.NoPermissionException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.NoPermissionExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.NoPermissionExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.NoPermissionExceptionHelper.type ();
  }

}
