package at.owlsoft.owl.corbamodel.user;


/**
* at/owlsoft/owl/corbamodel/user/ICorbaSystemUserPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaSystemUserPOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.corbamodel.user.ICorbaSystemUserOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getUserID", new java.lang.Integer (0));
    _methods.put ("getCardID", new java.lang.Integer (1));
    _methods.put ("getUsername", new java.lang.Integer (2));
    _methods.put ("getPassword", new java.lang.Integer (3));
    _methods.put ("getEmail", new java.lang.Integer (4));
    _methods.put ("getFirstName", new java.lang.Integer (5));
    _methods.put ("getLastName", new java.lang.Integer (6));
    _methods.put ("getBirthday", new java.lang.Integer (7));
    _methods.put ("getActivityCount", new java.lang.Integer (8));
    _methods.put ("getActivities", new java.lang.Integer (9));
    _methods.put ("getRoles", new java.lang.Integer (10));
    _methods.put ("getSystemUserTransactions", new java.lang.Integer (11));
    _methods.put ("getSystemUserStatusEntries", new java.lang.Integer (12));
    _methods.put ("getCurrentStatus", new java.lang.Integer (13));
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
       case 0:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getUserID
       {
         int $result = (int)0;
         $result = this.getUserID ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getCardID
       {
         int $result = (int)0;
         $result = this.getCardID ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 2:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getUsername
       {
         String $result = null;
         $result = this.getUsername ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getPassword
       {
         String $result = null;
         $result = this.getPassword ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getEmail
       {
         String $result = null;
         $result = this.getEmail ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getFirstName
       {
         String $result = null;
         $result = this.getFirstName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 6:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getLastName
       {
         String $result = null;
         $result = this.getLastName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 7:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getBirthday
       {
         long $result = (long)0;
         $result = this.getBirthday ();
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 8:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getActivityCount
       {
         int $result = (int)0;
         $result = this.getActivityCount ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 9:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getActivities
       {
         at.owlsoft.owl.corbamodel.accounting.ICorbaActivity $result[] = null;
         $result = this.getActivities ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.accounting._ActivityListHelper.write (out, $result);
         break;
       }

       case 10:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getRoles
       {
         at.owlsoft.owl.corbamodel.user.ICorbaRole $result[] = null;
         $result = this.getRoles ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user._RoleListHelper.write (out, $result);
         break;
       }

       case 11:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getSystemUserTransactions
       {
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransaction $result[] = null;
         $result = this.getSystemUserTransactions ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user._SystemUserTransactionListHelper.write (out, $result);
         break;
       }

       case 12:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getSystemUserStatusEntries
       {
         at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry $result[] = null;
         $result = this.getSystemUserStatusEntries ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user._SystemUserStatusEntryListHelper.write (out, $result);
         break;
       }

       case 13:  // at/owlsoft/owl/corbamodel/user/ICorbaSystemUser/getCurrentStatus
       {
         at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus $result = null;
         $result = this.getCurrentStatus ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatusHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/user/ICorbaSystemUser:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaSystemUser _this() 
  {
    return ICorbaSystemUserHelper.narrow(
    super._this_object());
  }

  public ICorbaSystemUser _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaSystemUserHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaSystemUserPOA
