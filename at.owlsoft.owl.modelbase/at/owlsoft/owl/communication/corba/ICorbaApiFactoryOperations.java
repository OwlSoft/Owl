package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/ICorbaApiFactoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public interface ICorbaApiFactoryOperations 
{
  at.owlsoft.owl.communication.corba.ICorbaExtendApi createExtendApi ();
  at.owlsoft.owl.communication.corba.ICorbaRentalApi createRentalApi ();
  at.owlsoft.owl.communication.corba.ICorbaReservationApi createReservationApi ();
  at.owlsoft.owl.communication.corba.ICorbaReturnApi createReturnApi ();
  at.owlsoft.owl.communication.corba.ICorbaSystemUserApi createSystemUserApi ();
  at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi createAuthenticationApi ();
} // interface ICorbaApiFactoryOperations
