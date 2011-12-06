package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionPOA;
import at.owlsoft.owl.corbamodel.user.TransactionType;
import at.owlsoft.owl.model.user.ISystemUserTransaction;

public class CorbaSystemUserTransaction extends ICorbaSystemUserTransactionPOA
{

    private ISystemUserTransaction _transaction;
    private POA                    _rootPOA;

    @Override
    public long getDate()
    {
        return _transaction.getDate().getTime();
    }

    @Override
    public float getAmount()
    {
        return _transaction.getAmount();
    }

    @Override
    public String getComment()
    {
        return _transaction.getComment();
    }

    @Override
    public TransactionType getTransactionType()
    {
        switch (_transaction.getTransactionType())
        {
            case Donation:
                return TransactionType.Donation;
            case MemberFee:
                return TransactionType.MemberFee;
            case OverdueFee:
                return TransactionType.OverdueFee;
            default:
                throw new RuntimeException("TransactionType not known.");
        }
    }

    @Override
    public ICorbaSystemUser getSystemUser()
    {
        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setUser(_transaction.getSystemUser());
            cuser.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cuser);
            return ICorbaSystemUserHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setTransaction(ISystemUserTransaction transaction)
    {
        _transaction = transaction;

    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

}
