package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaFilingExtensionPOA;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRental;
import at.owlsoft.owl.corbamodel.accounting.ICorbaRentalHelper;
import at.owlsoft.owl.model.accounting.IFilingExtension;

public class CorbaFilingExtension extends ICorbaFilingExtensionPOA
{

    private IFilingExtension _filingExtension;
    private POA              _rootPOA;

    public void setFilingExtension(IFilingExtension filingExtension)
    {
        _filingExtension = filingExtension;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public long getCreationDate()
    {
        return _filingExtension.getCreationDate().getTime();
    }

    @Override
    public long getNewEndDate()
    {
        return _filingExtension.getNewEndDate().getTime();
    }

    @Override
    public ICorbaRental getRental()
    {
        try
        {
            CorbaRental rental = new CorbaRental();
            rental.setRental(_filingExtension.getRental());
            rental.setRootPOA(_rootPOA);
            org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(rental);
            return ICorbaRentalHelper.narrow(ref);
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

}
