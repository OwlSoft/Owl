package at.owlsoft.owl.corbamodel;


/**
* at/owlsoft/owl/corbamodel/NoPermissionException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class NoPermissionException extends org.omg.CORBA.UserException
{

  public NoPermissionException ()
  {
    super(NoPermissionExceptionHelper.id());
  } // ctor


  public NoPermissionException (String $reason)
  {
    super(NoPermissionExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class NoPermissionException