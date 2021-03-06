package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/_ICorbaRentalApiStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class _ICorbaRentalApiStub extends org.omg.CORBA.portable.ObjectImpl implements at.owlsoft.owl.communication.corba.ICorbaRentalApi
{

  public at.owlsoft.owl.corbamodel.user.ICorbaSystemUser getRentalsForSystemUserCardId (int cardID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRentalsForSystemUserCardId", true);
                $out.write_long (cardID);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRentalsForSystemUserCardId (cardID        );
            } finally {
                _releaseReply ($in);
            }
  } // getRentalsForSystemUserCardId

  public void newRental ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("newRental", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                newRental (        );
            } finally {
                _releaseReply ($in);
            }
  } // newRental

  public at.owlsoft.owl.corbamodel.user.ICorbaSystemUser setCustomer (int cardId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setCustomer", true);
                $out.write_long (cardId);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.user.ICorbaSystemUser $result = at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return setCustomer (cardId        );
            } finally {
                _releaseReply ($in);
            }
  } // setCustomer

  public at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar setMediumExemplar (int exemplarId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setMediumExemplar", true);
                $out.write_long (exemplarId);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar $result = at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplarHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return setMediumExemplar (exemplarId        );
            } finally {
                _releaseReply ($in);
            }
  } // setMediumExemplar

  public at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessage[] getValidationMessages ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getValidationMessages", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessage $result[] = at.owlsoft.owl.corbamodel.validation._ValidationMessageListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getValidationMessages (        );
            } finally {
                _releaseReply ($in);
            }
  } // getValidationMessages

  public void createNewExtension (String uuid)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createNewExtension", true);
                $out.write_string (uuid);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createNewExtension (uuid        );
            } finally {
                _releaseReply ($in);
            }
  } // createNewExtension

  public void returnRental (String uuid)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("returnRental", true);
                $out.write_string (uuid);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                returnRental (uuid        );
            } finally {
                _releaseReply ($in);
            }
  } // returnRental

  public at.owlsoft.owl.corbamodel.accounting.ICorbaRental getRental ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRental", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.accounting.ICorbaRental $result = at.owlsoft.owl.corbamodel.accounting.ICorbaRentalHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRental (        );
            } finally {
                _releaseReply ($in);
            }
  } // getRental

  public void setStartDate (long time)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setStartDate", true);
                $out.write_longlong (time);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                setStartDate (time        );
            } finally {
                _releaseReply ($in);
            }
  } // setStartDate

  public boolean store ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("store", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return store (        );
            } finally {
                _releaseReply ($in);
            }
  } // store

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/communication/corba/ICorbaRentalApi:1.0"};

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
} // class _ICorbaRentalApiStub
