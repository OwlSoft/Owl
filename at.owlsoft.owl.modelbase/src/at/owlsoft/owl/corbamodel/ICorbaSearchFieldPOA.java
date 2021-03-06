package at.owlsoft.owl.corbamodel;


/**
* at/owlsoft/owl/corbamodel/ICorbaSearchFieldPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaSearchFieldPOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.corbamodel.ICorbaSearchFieldOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getKey", new java.lang.Integer (0));
    _methods.put ("getValue", new java.lang.Integer (1));
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
       case 0:  // at/owlsoft/owl/corbamodel/ICorbaSearchField/getKey
       {
         String $result = null;
         $result = this.getKey ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // at/owlsoft/owl/corbamodel/ICorbaSearchField/getValue
       {
         String $result = null;
         $result = this.getValue ();
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
    "IDL:at/owlsoft/owl/corbamodel/ICorbaSearchField:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaSearchField _this() 
  {
    return ICorbaSearchFieldHelper.narrow(
    super._this_object());
  }

  public ICorbaSearchField _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaSearchFieldHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaSearchFieldPOA
