package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumExemplar;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.validation.ICorbaValidationMessage;

public class RentalApi extends ICorbaRentalApiPOA
{

    @Override
    public ICorbaSystemUser getRentalsForSystemUserCardId(int cardID)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void newRental()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public ICorbaSystemUser setCustomer(int cardId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaMediumExemplar setMediumExemplar(int exemplarId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaValidationMessage[] getValidationMessages()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createNewExtension(String uuid)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnRental(String uuid)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public ICorbaRental getRental()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStartDate(long time)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean store()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
