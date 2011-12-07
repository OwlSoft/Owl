package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/ICorbaApiFactoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Mittwoch, 07. Dezember 2011 09:11 Uhr MEZ
*/

public interface ICorbaApiFactoryOperations 
{
  at.owlsoft.owl.communication.corba.ICorbaExtendApi createExtendApi ();
  at.owlsoft.owl.communication.corba.ICorbaRentalApi createRentalApi ();
  at.owlsoft.owl.communication.corba.ICorbaReservationApi createReservationApi ();
  at.owlsoft.owl.communication.corba.ICorbaReturnApi createReturnApi ();
  at.owlsoft.owl.communication.corba.ICorbaSystemUserApi createSystemUserApi ();
  at.owlsoft.owl.communication.corba.ICorbaAuthenticationApi createAuthenticationApi ();
  at.owlsoft.owl.communication.corba.ICorbaSearchApi createSearchApi ();
} // interface ICorbaApiFactoryOperations
