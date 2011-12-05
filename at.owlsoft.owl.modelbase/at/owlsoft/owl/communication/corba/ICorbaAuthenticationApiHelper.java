package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/ICorbaAuthenticationApiHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class ICorbaAuthenticationApiHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/communication/corba/ICorbaAuthenticationApi:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.id (), "ICorbaAuthenticationApi");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ICorbaAuthenticationApiStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi)
      return (at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      at.owlsoft.owl.communication.corba._ICorbaAuthenticationApiStub stub = new at.owlsoft.owl.communication.corba._ICorbaAuthenticationApiStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi)
      return (at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      at.owlsoft.owl.communication.corba._ICorbaAuthenticationApiStub stub = new at.owlsoft.owl.communication.corba._ICorbaAuthenticationApiStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
