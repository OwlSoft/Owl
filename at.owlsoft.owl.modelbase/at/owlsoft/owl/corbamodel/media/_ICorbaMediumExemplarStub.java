package at.owlsoft.owl.corbamodel.media;


/**
* at/owlsoft/owl/corbamodel/media/_ICorbaMediumExemplarStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class _ICorbaMediumExemplarStub extends org.omg.CORBA.portable.ObjectImpl implements at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar
{

  public int getExemplarID ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getExemplarID", true);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getExemplarID (        );
            } finally {
                _releaseReply ($in);
            }
  } // getExemplarID

  public String getMetaData (String key)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMetaData", true);
                $out.write_string (key);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMetaData (key        );
            } finally {
                _releaseReply ($in);
            }
  } // getMetaData

  public String[] getMetaDataKeys ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMetaDataKeys", true);
                $in = _invoke ($out);
                String $result[] = at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarPackage._metaDataKeysHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMetaDataKeys (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMetaDataKeys

  public at.owlsoft.owl.corbamodel.media.ICorbaMedium getMedium ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMedium", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.ICorbaMedium $result = at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMedium (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMedium

  public at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntry[] getMediumExemplarStatusEntries ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMediumExemplarStatusEntries", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarStatusEntry $result[] = at.owlsoft.owl.corbamodel.media._MediumExemplarStatusEntryListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMediumExemplarStatusEntries (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMediumExemplarStatusEntries

  public at.owlsoft.owl.corbamodel.accounting.ICorbaActivity[] getActivities ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActivities", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.accounting.ICorbaActivity $result[] = at.owlsoft.owl.corbamodel.accounting._ActivityListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActivities (        );
            } finally {
                _releaseReply ($in);
            }
  } // getActivities

  public at.owlsoft.owl.corbamodel.accounting.ICorbaRental getLastRental ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLastRental", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.accounting.ICorbaRental $result = at.owlsoft.owl.corbamodel.accounting.ICorbaRentalHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLastRental (        );
            } finally {
                _releaseReply ($in);
            }
  } // getLastRental

  public at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus getCurrentState ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCurrentState", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatus $result = at.owlsoft.owl.corbamodel.media.CorbaMediumExemplarStatusHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCurrentState (        );
            } finally {
                _releaseReply ($in);
            }
  } // getCurrentState

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/media/ICorbaMediumExemplar:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ICorbaMediumExemplarStub
