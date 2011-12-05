package at.owlsoft.owl.corbamodel.accounting;


/**
* at/owlsoft/owl/corbamodel/accounting/CorbaActivityStatusHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class CorbaActivityStatusHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/corbamodel/accounting/CorbaActivityStatus:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatusHelper.id (), "CorbaActivityStatus", new String[] { "Open", "Closed", "Overdue", "Returned", "Extended"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus read (org.omg.CORBA.portable.InputStream istream)
  {
    return at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus value)
  {
    ostream.write_long (value.value ());
  }

}
