package at.owlsoft.owl.corbamodel.validation;

/**
* at/owlsoft/owl/corbamodel/validation/CorbaValidationMessageStatusHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class CorbaValidationMessageStatusHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatus value = null;

  public CorbaValidationMessageStatusHolder ()
  {
  }

  public CorbaValidationMessageStatusHolder (at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatus initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatusHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatusHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel.validation.CorbaValidationMessageStatusHelper.type ();
  }

}