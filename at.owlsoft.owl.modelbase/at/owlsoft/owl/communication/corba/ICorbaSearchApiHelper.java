package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/ICorbaSearchApiHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class ICorbaSearchApiHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/communication/corba/ICorbaSearchApi:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.communication.corba.ICorbaSearchApi that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.communication.corba.ICorbaSearchApi extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (at.owlsoft.owl.communication.corba.ICorbaSearchApiHelper.id (), "ICorbaSearchApi");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.communication.corba.ICorbaSearchApi read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ICorbaSearchApiStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.communication.corba.ICorbaSearchApi value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static at.owlsoft.owl.communication.corba.ICorbaSearchApi narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof at.owlsoft.owl.communication.corba.ICorbaSearchApi)
      return (at.owlsoft.owl.communication.corba.ICorbaSearchApi)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      at.owlsoft.owl.communication.corba._ICorbaSearchApiStub stub = new at.owlsoft.owl.communication.corba._ICorbaSearchApiStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static at.owlsoft.owl.communication.corba.ICorbaSearchApi unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof at.owlsoft.owl.communication.corba.ICorbaSearchApi)
      return (at.owlsoft.owl.communication.corba.ICorbaSearchApi)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      at.owlsoft.owl.communication.corba._ICorbaSearchApiStub stub = new at.owlsoft.owl.communication.corba._ICorbaSearchApiStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
