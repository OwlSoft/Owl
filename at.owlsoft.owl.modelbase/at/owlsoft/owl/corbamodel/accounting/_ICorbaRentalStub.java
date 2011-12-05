package at.owlsoft.owl.corbamodel.accounting;


/**
* at/owlsoft/owl/corbamodel/accounting/_ICorbaRentalStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class _ICorbaRentalStub extends org.omg.CORBA.portable.ObjectImpl implements at.owlsoft.owl.corbamodel.accounting.ICorbaRental
{

  public long getEndDate ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getEndDate", true);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getEndDate (        );
            } finally {
                _releaseReply ($in);
            }
  } // getEndDate

  public int getFilingExtensionCount ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getFilingExtensionCount", true);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getFilingExtensionCount (        );
            } finally {
                _releaseReply ($in);
            }
  } // getFilingExtensionCount

  public at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtension[] getFilingExtensions ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getFilingExtensions", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtension $result[] = at.owlsoft.owl.corbamodel.accounting._FilingExtensionListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getFilingExtensions (        );
            } finally {
                _releaseReply ($in);
            }
  } // getFilingExtensions

  public String getUUID ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUUID", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUUID (        );
            } finally {
                _releaseReply ($in);
            }
  } // getUUID

  public long getStartDate ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getStartDate", true);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getStartDate (        );
            } finally {
                _releaseReply ($in);
            }
  } // getStartDate

  public at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry[] getActivityStatusEntries ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActivityStatusEntries", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.accounting.ICorbaActivityStatusEntry $result[] = at.owlsoft.owl.corbamodel.accounting._ActivityStatusEntryListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActivityStatusEntries (        );
            } finally {
                _releaseReply ($in);
            }
  } // getActivityStatusEntries

  public at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar getMediumExemplar ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMediumExemplar", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar $result = at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMediumExemplar (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMediumExemplar

  public at.owlsoft.owl.corbamodel.user.ICorbaSystemUser getCustomer ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCustomer", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCustomer (        );
            } finally {
                _releaseReply ($in);
            }
  } // getCustomer

  public at.owlsoft.owl.corbamodel.user.ICorbaSystemUser getCreator ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCreator", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCreator (        );
            } finally {
                _releaseReply ($in);
            }
  } // getCreator

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

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/corbamodel/accounting/ICorbaRental:1.0", 
    "IDL:at/owlsoft/owl/corbamodel/accounting/ICorbaActivity:1.0"};

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
} // class _ICorbaRentalStub
