package at.owlsoft.owl.corbamodel.accounting;

/**
* at/owlsoft/owl/corbamodel/accounting/ICorbaReservationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class ICorbaReservationHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.accounting.ICorbaReservation value = null;

  public ICorbaReservationHolder ()
  {
  }

  public ICorbaReservationHolder (at.owlsoft.owl.corbamodel.accounting.ICorbaReservation initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.accounting.ICorbaReservationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.accounting.ICorbaReservationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.accounting.ICorbaReservationHelper.type ();
  }

}
