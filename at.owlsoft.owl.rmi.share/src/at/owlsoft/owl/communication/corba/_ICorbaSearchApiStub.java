package at.owlsoft.owl.communication.corba;


/**
* at/owlsoft/owl/communication/corba/_ICorbaSearchApiStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corbamodel.idl
* Montag, 05. Dezember 2011 12:57 Uhr MEZ
*/

public class _ICorbaSearchApiStub extends org.omg.CORBA.portable.ObjectImpl implements at.owlsoft.owl.communication.corba.ICorbaSearchApi
{

  public at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory[] getSearchFieldCategories ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getSearchFieldCategories", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory $result[] = at.owlsoft.owl.corbamodel._SearchFieldCategoryListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getSearchFieldCategories (        );
            } finally {
                _releaseReply ($in);
            }
  } // getSearchFieldCategories

  public at.owlsoft.owl.corbamodel.ICorbaSearchField addNewSearchField (String uniqueId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addNewSearchField", true);
                $out.write_string (uniqueId);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.ICorbaSearchField $result = at.owlsoft.owl.corbamodel.ICorbaSearchFieldHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return addNewSearchField (uniqueId        );
            } finally {
                _releaseReply ($in);
            }
  } // addNewSearchField

  public void removeSearchField (String uniqueId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeSearchField", true);
                $out.write_string (uniqueId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                removeSearchField (uniqueId        );
            } finally {
                _releaseReply ($in);
            }
  } // removeSearchField

  public void setSearchFieldData (String uniqueId, String key, String value)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setSearchFieldData", true);
                $out.write_string (uniqueId);
                $out.write_string (key);
                $out.write_string (value);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                setSearchFieldData (uniqueId, key, value        );
            } finally {
                _releaseReply ($in);
            }
  } // setSearchFieldData

  public at.owlsoft.owl.corbamodel.media.ICorbaMedium[] search ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("search", true);
                $in = _invoke ($out);
                at.owlsoft.owl.corbamodel.media.ICorbaMedium $result[] = at.owlsoft.owl.corbamodel.media._MediumListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return search (        );
            } finally {
                _releaseReply ($in);
            }
  } // search

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:at/owlsoft/owl/communication/corba/ICorbaSearchApi:1.0"};

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
} // class _ICorbaSearchApiStub