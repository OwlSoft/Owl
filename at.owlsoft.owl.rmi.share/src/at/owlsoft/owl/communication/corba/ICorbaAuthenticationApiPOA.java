package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/ICorbaAuthenticationApiPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaAuthenticationApiPOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("login", new java.lang.Integer (0));
    _methods.put ("getRolesForCurrentUser", new java.lang.Integer (1));
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
       case 0:  // at/owlsoft/owl/communication/corba/ICorbaAuthenticationApi/login
       {
         String userName = in.read_string ();
         String password = in.read_string ();
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = null;
         $result = this.login (userName, password);
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.write (out, $result);
         break;
       }

       case 1:  // at/owlsoft/owl/communication/corba/ICorbaAuthenticationApi/getRolesForCurrentUser
       {
         at.owlsoft.owl.corbamodel.user.ICorbaRole $result[] = null;
         $result = this.getRolesForCurrentUser ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user._RoleListHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/communication/corba/ICorbaAuthenticationApi:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaAuthenticationApi _this() 
  {
    return ICorbaAuthenticationApiHelper.narrow(
    super._this_object());
  }

  public ICorbaAuthenticationApi _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaAuthenticationApiHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaAuthenticationApiPOA
