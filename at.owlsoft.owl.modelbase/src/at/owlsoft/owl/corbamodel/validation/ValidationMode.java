package at.owlsoft.owl.corbamodel.validation;


/**
* at/owlsoft/owl/corbamodel/validation/ValidationMode.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class ValidationMode implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static at.owlsoft.owl.corbamodel.validation.ValidationMode[] __array = new at.owlsoft.owl.corbamodel.validation.ValidationMode [__size];

  public static final int _Strict = 0;
  public static final at.owlsoft.owl.corbamodel.validation.ValidationMode Strict = new at.owlsoft.owl.corbamodel.validation.ValidationMode(_Strict);
  public static final int _NotStrict = 1;
  public static final at.owlsoft.owl.corbamodel.validation.ValidationMode NotStrict = new at.owlsoft.owl.corbamodel.validation.ValidationMode(_NotStrict);

  public int value ()
  {
    return __value;
  }

  public static at.owlsoft.owl.corbamodel.validation.ValidationMode from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected ValidationMode (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class ValidationMode