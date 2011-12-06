package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionPOA;
import at.owlsoft.owl.corbamodel.user.TransactionType;
import at.owlsoft.owl.model.user.ISystemUserTransaction;

public class CorbaSystemUserTransaction extends ICorbaSystemUserTransactionPOA
{

    private ISystemUserTransaction _transaction;

    @Override
    public long getDate()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getAmount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getComment()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransactionType getTransactionType()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaSystemUser getSystemUser()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTransaction(ISystemUserTransaction transaction)
    {
        _transaction = transaction;

    }

}
