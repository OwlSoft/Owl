package at.owlsoft.owl.corbamodel.accounting;


/**
* at/owlsoft/owl/corbamodel/accounting/ICorbaRentalOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public interface ICorbaRentalOperations  extends at.owlsoft.owl.corbamodel.accounting.ICorbaActivityOperations
{
  long getEndDate ();
  int getFilingExtensionCount ();
  at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtension[] getFilingExtensions ();
} // interface ICorbaRentalOperations
