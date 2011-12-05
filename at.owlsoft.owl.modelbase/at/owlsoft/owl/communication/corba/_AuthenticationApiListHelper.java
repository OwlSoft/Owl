package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/_AuthenticationApiListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class _AuthenticationApiListHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/communication/corba/AuthenticationApiList:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (at.owlsoft.owl.communication.corba._AuthenticationApiListHelper.id (), "AuthenticationApiList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi[] read (org.omg.CORBA.portable.InputStream istream)
  {
    at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi value[] = null;
    int _len0 = istream.read_long ();
    value = new at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      at.owlsoft.owl.communication.corba.ICorbaAuthenticationApiHelper.write (ostream, value[_i0]);
  }

}
