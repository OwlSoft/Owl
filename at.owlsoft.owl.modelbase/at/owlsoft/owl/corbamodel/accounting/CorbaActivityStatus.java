package at.owlsoft.owl.corbamodel.accounting;


/**
* at/owlsoft/owl/corbamodel/accounting/CorbaActivityStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class CorbaActivityStatus implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus[] __array = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus [__size];

  public static final int _Open = 0;
  public static final at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus Open = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus(_Open);
  public static final int _Closed = 1;
  public static final at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus Closed = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus(_Closed);
  public static final int _Overdue = 2;
  public static final at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus Overdue = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus(_Overdue);
  public static final int _Returned = 3;
  public static final at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus Returned = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus(_Returned);
  public static final int _Extended = 4;
  public static final at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus Extended = new at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus(_Extended);

  public int value ()
  {
    return __value;
  }

  public static at.owlsoft.owl.corbamodel.accounting.CorbaActivityStatus from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected CorbaActivityStatus (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class CorbaActivityStatus