package at.owlsoft.owl.corbamodel.media;

/**
* at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class ICorbaMediumExemplarHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar value = null;

  public ICorbaMediumExemplarHolder ()
  {
  }

  public ICorbaMediumExemplarHolder (at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.type ();
  }

}
