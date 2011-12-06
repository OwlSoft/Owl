package at.owlsoft.owl.corbamodel;

/**
 * at/owlsoft/owl/corbamodel/ICorbaSearchFieldCategoryPOA.java . Generated by
 * the IDL-to-Java compiler (portable), version "3.2" from corbamodel.idl
 * Montag, 05. Dezember 2011 12:57 Uhr MEZ
 */

public abstract class ICorbaSearchFieldCategoryPOA extends
        org.omg.PortableServer.Servant implements
        at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategoryOperations,
        org.omg.CORBA.portable.InvokeHandler
{

    // Constructors

    private static java.util.Hashtable _methods = new java.util.Hashtable();
    static
    {
        _methods.put("getLabel", new java.lang.Integer(0));
        _methods.put("getSearchFieldDefinitions", new java.lang.Integer(1));
    }

    @Override
    public org.omg.CORBA.portable.OutputStream _invoke(String $method,
            org.omg.CORBA.portable.InputStream in,
            org.omg.CORBA.portable.ResponseHandler $rh)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        java.lang.Integer __method = (java.lang.Integer) _methods.get($method);
        if (__method == null)
        {
            throw new org.omg.CORBA.BAD_OPERATION(0,
                    org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        switch (__method.intValue())
        {
            case 0: // at/owlsoft/owl/corbamodel/ICorbaSearchFieldCategory/getLabel
            {
                String $result = null;
                $result = this.getLabel();
                out = $rh.createReply();
                out.write_string($result);
                break;
            }

            case 1: // at/owlsoft/owl/corbamodel/ICorbaSearchFieldCategory/getSearchFieldDefinitions
            {
                at.owlsoft.owl.corbamodel.ISearchFieldDefinition $result[] = null;
                $result = this.getSearchFieldDefinitions();
                out = $rh.createReply();
                at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategoryPackage._SearchFieldDefinitionListHelper
                        .write(out, $result);
                break;
            }

            default:
                throw new org.omg.CORBA.BAD_OPERATION(0,
                        org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        return out;
    } // _invoke

    // Type-specific CORBA::Object operations
    private static String[] __ids =
                                  { "IDL:at/owlsoft/owl/corbamodel/ICorbaSearchFieldCategory:1.0" };

    @Override
    public String[] _all_interfaces(org.omg.PortableServer.POA poa,
            byte[] objectId)
    {
        return __ids.clone();
    }

    public ICorbaSearchFieldCategory _this()
    {
        return ICorbaSearchFieldCategoryHelper.narrow(super._this_object());
    }

    public ICorbaSearchFieldCategory _this(org.omg.CORBA.ORB orb)
    {
        return ICorbaSearchFieldCategoryHelper.narrow(super._this_object(orb));
    }

} // class ICorbaSearchFieldCategoryPOA
