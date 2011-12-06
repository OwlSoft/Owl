package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.corbamodel.accounting.ICorbaActivity;
import at.owlsoft.owl.corbamodel.accounting.ICorbaActivityHelper;
import at.owlsoft.owl.corbamodel.user.CorbaSystemUserStatus;
import at.owlsoft.owl.corbamodel.user.ICorbaRole;
import at.owlsoft.owl.corbamodel.user.ICorbaRoleHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserPOA;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntry;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserStatusEntryHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransaction;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserTransactionHelper;
import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.ISystemUserStatusEntry;
import at.owlsoft.owl.model.user.ISystemUserTransaction;

public class CorbaSystemUser extends ICorbaSystemUserPOA
{

    private ISystemUser _user;
    private POA         _rootPOA;

    public void setUser(ISystemUser systemUser)
    {
        _user = systemUser;
    }

    public void setRootPOA(POA rootPOA)
    {
        _rootPOA = rootPOA;
    }

    @Override
    public int getUserID()
    {
        return _user.getUserID();
    }

    @Override
    public int getCardID()
    {
        return _user.getCardID();
    }

    @Override
    public String getUsername()
    {
        return _user.getUsername();
    }

    @Override
    public String getPassword()
    {
        return _user.getPassword();
    }

    @Override
    public String getEmail()
    {
        return _user.getEmail();
    }

    @Override
    public String getFirstName()
    {
        return _user.getFirstName();
    }

    @Override
    public String getLastName()
    {
        return _user.getLastName();
    }

    @Override
    public long getBirthday()
    {
        return _user.getBirthday().getTime();
    }

    @Override
    public int getActivityCount()
    {
        return _user.getActivityCount();
    }

    @Override
    public ICorbaActivity[] getActivities()
    {
        try
        {
            ICorbaActivity[] temp = new ICorbaActivity[_user.getActivities()
                    .size()];
            int index = 0;
            for (IActivity activity : _user.getActivities())
            {
                CorbaActivity cactivity = new CorbaActivity();
                cactivity.setActivity(activity);
                cactivity.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cactivity);
                temp[index] = ICorbaActivityHelper.narrow(ref);
                index++;
            }
            return temp;
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

    @Override
    public ICorbaRole[] getRoles()
    {
        try
        {
            ICorbaRole[] temp = new ICorbaRole[_user.getRoles().size()];
            int index = 0;
            for (IRole role : _user.getRoles())
            {
                CorbaRole crole = new CorbaRole();
                crole.setRole(role);
                org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(crole);
                temp[index] = ICorbaRoleHelper.narrow(ref);
                index++;
            }
            return temp;
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

    @Override
    public ICorbaSystemUserTransaction[] getSystemUserTransactions()
    {
        try
        {
            ICorbaSystemUserTransaction[] temp = new ICorbaSystemUserTransaction[_user
                    .getSystemUserTransactions().size()];
            int index = 0;
            for (ISystemUserTransaction transaction : _user
                    .getSystemUserTransactions())
            {
                CorbaSystemUserTransaction ctransaction = new CorbaSystemUserTransaction();
                ctransaction.setTransaction(transaction);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(ctransaction);
                temp[index] = ICorbaSystemUserTransactionHelper.narrow(ref);
                index++;
            }
            return temp;
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

    @Override
    public ICorbaSystemUserStatusEntry[] getSystemUserStatusEntries()
    {
        try
        {
            ICorbaSystemUserStatusEntry[] temp = new ICorbaSystemUserStatusEntry[_user
                    .getSystemUserStatusEntries().size()];
            int index = 0;
            for (ISystemUserStatusEntry statusEntry : _user
                    .getSystemUserStatusEntries())
            {
                CorbaSystemUserStatusEntry cstatusEntry = new CorbaSystemUserStatusEntry();
                cstatusEntry.setStatusEntry(statusEntry);
                org.omg.CORBA.Object ref = _rootPOA
                        .servant_to_reference(cstatusEntry);
                temp[index] = ICorbaSystemUserStatusEntryHelper.narrow(ref);
                index++;
            }
            return temp;
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

    @Override
    public CorbaSystemUserStatus getCurrentStatus()
    {

        switch (_user.getCurrentStatus())
        {
            case Active:
                return CorbaSystemUserStatus.Active;
            case InactiveBecauseMembershipFee:
                return CorbaSystemUserStatus.InactiveBecauseMembershipFee;
            case InactiveBecauseOverdue:
                return CorbaSystemUserStatus.InactiveBecauseOverdue;
            default:
                throw new RuntimeException("Unknown SystemUserStatus");
        }
    }

}
