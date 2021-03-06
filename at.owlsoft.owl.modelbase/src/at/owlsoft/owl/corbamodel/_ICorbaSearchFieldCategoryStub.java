package at.owlsoft.owl.corbamodel;

/**
 * at/owlsoft/owl/corbamodel/_ICorbaSearchFieldCategoryStub.java . Generated by
 * the IDL-to-Java compiler (portable), version "3.2" from corbamodel.idl
 * Montag, 05. Dezember 2011 12:57 Uhr MEZ
 */

public class _ICorbaSearchFieldCategoryStub extends
        org.omg.CORBA.portable.ObjectImpl implements
        at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory
{

    @Override
    public String getLabel()
    {
        org.omg.CORBA.portable.InputStream $in = null;
        try
        {
            org.omg.CORBA.portable.OutputStream $out = _request("getLabel",
                    true);
            $in = _invoke($out);
            String $result = $in.read_string();
            return $result;
        }
        catch (org.omg.CORBA.portable.ApplicationException $ex)
        {
            $in = $ex.getInputStream();
            String _id = $ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException $rm)
        {
            return getLabel();
        }
        finally
        {
            _releaseReply($in);
        }
    } // getLabel

    @Override
    public at.owlsoft.owl.corbamodel.ISearchFieldDefinition[] getSearchFieldDefinitions()
    {
        org.omg.CORBA.portable.InputStream $in = null;
        try
        {
            org.omg.CORBA.portable.OutputStream $out = _request(
                    "getSearchFieldDefinitions", true);
            $in = _invoke($out);
            at.owlsoft.owl.corbamodel.ISearchFieldDefinition $result[] = at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategoryPackage._SearchFieldDefinitionListHelper
                    .read($in);
            return $result;
        }
        catch (org.omg.CORBA.portable.ApplicationException $ex)
        {
            $in = $ex.getInputStream();
            String _id = $ex.getId();
            throw new org.omg.CORBA.MARSHAL(_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException $rm)
        {
            return getSearchFieldDefinitions();
        }
        finally
        {
            _releaseReply($in);
        }
    } // getSearchFieldDefinitions

    // Type-specific CORBA::Object operations
    private static String[] __ids =
                                  { "IDL:at/owlsoft/owl/corbamodel/ICorbaSearchFieldCategory:1.0" };

    @Override
    public String[] _ids()
    {
        return __ids.clone();
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException
    {
        String str = s.readUTF();
        String[] args = null;
        java.util.Properties props = null;
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
        try
        {
            org.omg.CORBA.Object obj = orb.string_to_object(str);
            org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)
                    ._get_delegate();
            _set_delegate(delegate);
        }
        finally
        {
            orb.destroy();
        }
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException
    {
        String[] args = null;
        java.util.Properties props = null;
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
        try
        {
            String str = orb.object_to_string(this);
            s.writeUTF(str);
        }
        finally
        {
            orb.destroy();
        }
    }
} // class _ICorbaSearchFieldCategoryStub
