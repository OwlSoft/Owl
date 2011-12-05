package at.owlsoft.owl.corbamodel.user;


/**
* at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntryPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaSystemUserStatusEntryPOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getDate", new java.lang.Integer (0));
    _methods.put ("getComment", new java.lang.Integer (1));
    _methods.put ("getSystemUserStatus", new java.lang.Integer (2));
    _methods.put ("getSystemUser", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntry/getDate
       {
         long $result = (long)0;
         $result = this.getDate ();
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 1:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntry/getComment
       {
         String $result = null;
         $result = this.getComment ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntry/getSystemUserStatus
       {
         at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus $result = null;
         $result = this.getSystemUserStatus ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatusHelper.write (out, $result);
         break;
       }

       case 3:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntry/getSystemUser
       {
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = null;
         $result = this.getSystemUser ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/user/ICorbaSystemUserStatusEntry:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaSystemUserStatusEntry _this() 
  {
    return ICorbaSystemUserStatusEntryHelper.narrow(
    super._this_object());
  }

  public ICorbaSystemUserStatusEntry _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaSystemUserStatusEntryHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaSystemUserStatusEntryPOA