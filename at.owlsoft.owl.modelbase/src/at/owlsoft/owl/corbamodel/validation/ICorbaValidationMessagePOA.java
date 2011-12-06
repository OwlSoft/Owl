package at.owlsoft.owl.corbamodel.validation;


/**
* at/owlsoft/owl/corbamodel/validation/ICorbaValidationMessagePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaValidationMessagePOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessageOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getStatus", new java.lang.Integer (0));
    _methods.put ("getMessage", new java.lang.Integer (1));
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
       case 0:  // at/owlsoft/owl/corbamodel/validation/ICorbaValidationMessage/getStatus
       {
         at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatus $result = null;
         $result = this.getStatus ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatusHelper.write (out, $result);
         break;
       }

       case 1:  // at/owlsoft/owl/corbamodel/validation/ICorbaValidationMessage/getMessage
       {
         String $result = null;
         $result = this.getMessage ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/validation/ICorbaValidationMessage:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaValidationMessage _this() 
  {
    return ICorbaValidationMessageHelper.narrow(
    super._this_object());
  }

  public ICorbaValidationMessage _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaValidationMessageHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaValidationMessagePOA