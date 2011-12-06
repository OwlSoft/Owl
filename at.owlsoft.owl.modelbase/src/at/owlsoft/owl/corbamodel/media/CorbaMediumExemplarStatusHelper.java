package at.owlsoft.owl.corbamodel.media;


/**
* at/owlsoft/owl/corbamodel/media/CorbaMediumExemplarStatusHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

abstract public class CorbaMediumExemplarStatusHelper
{
  private static String  _id = "IDL:at/owlsoft/owl/corbamodel/media/CorbaMediumExemplarStatus:1.0";

  public static void insert (org.omg.CORBA.Any a, at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatusHelper.id (), "CorbaMediumExemplarStatus", new String[] { "Rentable", "Rented", "Returned", "StockItem"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus read (org.omg.CORBA.portable.InputStream istream)
  {
    return at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus value)
  {
    ostream.write_long (value.value ());
  }

}