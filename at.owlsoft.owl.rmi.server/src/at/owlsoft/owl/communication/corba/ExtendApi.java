package at.owlsoft.owl.communication.corba;

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;

public class ExtendApi extends ICorbaExtendApiPOA implements ICorbaExtendApi
{

    @Override
    public boolean _is_equivalent(Object other)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int _hash(int maximum)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object _duplicate()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void _release()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Request _request(String operation)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation,
            NVList arg_list, NamedValue result)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation,
            NVList arg_list, NamedValue result, ExceptionList exclist,
            ContextList ctxlist)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object _set_policy_override(Policy[] policies,
            SetOverrideType set_add)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
