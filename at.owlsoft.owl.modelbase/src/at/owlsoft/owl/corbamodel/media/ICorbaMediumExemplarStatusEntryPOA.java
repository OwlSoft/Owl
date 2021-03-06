package at.owlsoft.owl.corbamodel.media;


/**
* at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarStatusEntryPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public abstract class ICorbaMediumExemplarStatusEntryPOA extends org.omg.PortableServer.Servant
 implements at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntryOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getDate", new java.lang.Integer (0));
    _methods.put ("getMediumExemplar", new java.lang.Integer (1));
    _methods.put ("getMediumExemplarStatus", new java.lang.Integer (2));
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
       case 0:  // at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarStatusEntry/getDate
       {
         long $result = (long)0;
         $result = this.getDate ();
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 1:  // at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarStatusEntry/getMediumExemplar
       {
         at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar $result = null;
         $result = this.getMediumExemplar ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.write (out, $result);
         break;
       }

       case 2:  // at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarStatusEntry/getMediumExemplarStatus
       {
         at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus $result = null;
         $result = this.getMediumExemplarStatus ();
         out = $rh.createReply();
         at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatusHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarStatusEntry:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaMediumExemplarStatusEntry _this() 
  {
    return ICorbaMediumExemplarStatusEntryHelper.narrow(
    super._this_object());
  }

  public ICorbaMediumExemplarStatusEntry _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaMediumExemplarStatusEntryHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaMediumExemplarStatusEntryPOA
