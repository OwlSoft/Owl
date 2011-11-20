/*
 * This file is part of OwlSoft Owl.
 *
 *  OwlSoft Owl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  alphaTab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with alphaTab.  If not, see <http://www.gnu.org/licenses/>.
 */
public package at.owlsoft.owl.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.model.accounting.Activity;

public class SystemUser implements Serializable
{
    private int                         _userID;
    private int                         _cardID;
    private String                      _username;
    private String                      _password;
    private String                      _email;
    private String                      _firstName;
    private String                      _lastName;
    private Date                        _birthday;
    private List<Activity>              _activities;
    private AccountMode                 _accountMode;
    private List<Role>                  _roles;
    private List<SystemUserTransaction> _systemUserTransactions;
    private List<SystemUserStatusEntry> _systemUserStatusEntries;

    public SystemUser()
    {

    }

    public SystemUser(int userID, int cardID, String username, String password,
            String email, String firstName, String lastName, Date birthday,
            AccountMode accountMode)
    {
        super();
        _userID = userID;
        _cardID = cardID;
        _username = username;
        _password = password;
        _email = email;
        _firstName = firstName;
        _lastName = lastName;
        _birthday = birthday;
        _activities = new ArrayList<Activity>();
        _accountMode = accountMode;
        _roles = new ArrayList<Role>();
        _systemUserTransactions = new ArrayList<SystemUserTransaction>();
        _systemUserStatusEntries = new ArrayList<SystemUserStatusEntry>();
    }

    public int getUserID()
    {
        return _userID;
    }

    public void setUserID(int userID)
    {
        _userID = userID;
    }

    public int getCardID()
    {
        return _cardID;
    }

    public void setCardID(int cardID)
    {
        _cardID = cardID;
    }

    public String getUsername()
    {
        return _username;
    }

    public void setUsername(String username)
    {
        _username = username;
    }

    public String getPassword()
    {
        return _password;
    }

    public void setPassword(String password)
    {
        _password = password;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(String email)
    {
        _email = email;
    }

    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }

    public Date getBirthday()
    {
        return _birthday;
    }

    public void setBirthday(Date birthday)
    {
        _birthday = birthday;
    }

    public int getActivityCount()
    {
        return _activities.size();
    }

    public Activity getActivity(int index)
    {
        return _activities.get(index);
    }

    public List<Activity> getActivities()
    {
        return Collections.unmodifiableList(_activities);
    }

    public void clearActivities()
    {
        _activities.clear();
    }

    public void addActivity(Activity activity)
    {
        activity.setCustomer(this);
        _activities.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        _activities.remove(activity);
    }

    public AccountMode getAccountMode()
    {
        return _accountMode;
    }

    public void setAccountMode(AccountMode accountMode)
    {
        _accountMode = accountMode;
    }

    public int getRoleCount()
    {
        return _roles.size();
    }

    public Role getRole(int index)
    {
        return _roles.get(index);
    }

    public List<Role> getRoles()
    {
        return Collections.unmodifiableList(_roles);
    }

    public void clearRoles()
    {
        _roles.clear();
    }

    public void addRole(Role role)
    {
        _roles.add(role);
    }

    public boolean hasRole(String key)
    {
        for (Role r : _roles)
        {
            if (r.getKey().equalsIgnoreCase(key))
            {
                return true;
            }
        }
        return false;
    }

    public int getSystemUserTransactionCount()
    {
        return _systemUserTransactions.size();
    }

    public SystemUserTransaction getSystemUserTransaction(int index)
    {
        return _systemUserTransactions.get(index);
    }

    public List<SystemUserTransaction> getSystemUserTransactions()
    {
        return Collections.unmodifiableList(_systemUserTransactions);
    }

    public void clearSystemUserTransactions()
    {
        _systemUserTransactions.clear();
    }

    public void addSystemUserTransaction(
            SystemUserTransaction systemUserTransaction)
    {
        systemUserTransaction.setSystemUser(this);
        _systemUserTransactions.add(systemUserTransaction);
    }

    public void removeSystemUserTransaction(
            SystemUserTransaction systemUserTransaction)
    {
        _systemUserTransactions.remove(systemUserTransaction);
    }

    public int getSystemUserStatusEntryCount()
    {
        return _systemUserStatusEntries.size();
    }

    public SystemUserStatusEntry getSystemUserStatusEntry(int index)
    {
        return _systemUserStatusEntries.get(index);
    }

    public List<SystemUserStatusEntry> getSystemUserStatusEntries()
    {
        return Collections.unmodifiableList(_systemUserStatusEntries);
    }

    public void clearSystemUserStatusEntries()
    {
        _systemUserStatusEntries.clear();
    }

    public void addSystemUserStatusEntry(
            SystemUserStatusEntry systemUserStatusEntry)
    {
        systemUserStatusEntry.setSystemUser(this);
        _systemUserStatusEntries.add(systemUserStatusEntry);
    }

    public void removeSystemUserStatusEntry(
            SystemUserStatusEntry systemUserStatusEntry)
    {
        _systemUserStatusEntries.remove(systemUserStatusEntry);
    }
}
