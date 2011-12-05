package at.owlsoft.owl.corbamodel.media;


/**
* at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplarOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public interface ICorbaMediumExemplarOperations 
{
  int getExemplarID ();
  String getMetaData (String key);
  String[] getMetaDataKeys ();
  at.owlsoft.owl.corbamodel.media.ICorbaMedium getMedium ();
  at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntry[] getMediumExemplarStatusEntries ();
  at.owlsoft.owl.corbamodel.accounting.ICorbaActivity[] getActivities ();
  at.owlsoft.owl.corbamodel.accounting.ICorbaRental getLastRental ();
  at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus getCurrentState ();
} // interface ICorbaMediumExemplarOperations
