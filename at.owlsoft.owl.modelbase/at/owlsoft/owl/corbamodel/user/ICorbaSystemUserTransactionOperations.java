package at.owlsoft.owl.corbamodel.user;


/**
* at/owlsoft/owl/corbamodel/user/ICorbaSystemUserTransactionOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public interface ICorbaSystemUserTransactionOperations 
{
  long getDate ();
  float getAmount ();
  String getComment ();
  at.owlsoft.owl.corbamodel.user.TransactionType getTransactionType ();
  at.owlsoft.owl.corbamodel.user.ICorbaSystemUser getSystemUser ();
} // interface ICorbaSystemUserTransactionOperations