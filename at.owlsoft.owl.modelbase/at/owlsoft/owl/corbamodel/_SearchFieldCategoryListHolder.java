package at.owlsoft.owl.corbamodel;


/**
* at/owlsoft/owl/corbamodel/_SearchFieldCategoryListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public final class _SearchFieldCategoryListHolder implements org.omg.CORBA.portable.Streamable
{
  public at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory value[] = null;

  public _SearchFieldCategoryListHolder ()
  {
  }

  public _SearchFieldCategoryListHolder (at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = at.owlsoft.owl.corbamodel._SearchFieldCategoryListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    at.owlsoft.owl.corbamodel._SearchFieldCategoryListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return at.owlsoft.owl.corbamodel._SearchFieldCategoryListHelper.type ();
  }

}
