package at.owlsoft.owl.corbamodel.user;


/**
* at/owlsoft/owl/corbamodel/user/_SystemUserStatusEntryListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class _SystemUserStatusEntryListHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/corbamodel/user/SystemUserStatusEntryList:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (at.owlsoft.owl.corbamodel.user._SystemUserStatusEntryListHelper.id (), "SystemUserStatusEntryList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry[] read (org.omg.CORBA.portable.InputStream istream)
  {
    at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry value[] = null;
    int _len0 = istream.read_long ();
    value = new at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryHelper.write (ostream, value[_i0]);
  }

}
