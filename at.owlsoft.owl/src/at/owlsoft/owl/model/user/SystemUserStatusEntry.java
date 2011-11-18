/*
 * This file is part of OwlSoft Owimport java.util.Date;
is free software: you can redistribute it and/or modify
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
package at.owlsoft.owl.model.user;

import java.util.Date;

public class SystemUserStatusEntry
{
    private Date             _date;
    private String           _comment;
    private SystemUserStatus _systemUserStatus;
    private SystemUser       _systemUser;

    public SystemUserStatusEntry()
    {
    }

    public SystemUserStatusEntry(Date date, String comment,
            SystemUserStatus systemUserStatus, SystemUser systemUser)
    {
        super();
        _date = date;
        _comment = comment;
        _systemUserStatus = systemUserStatus;
        _systemUser = systemUser;
    }

    public Date getDate()
    {
        return _date;
    }

    public void setDate(Date date)
    {
        _date = date;
    }

    public String getComment()
    {
        return _comment;
    }

    public void setComment(String comment)
    {
        _comment = comment;
    }

    public SystemUserStatus getSystemUserStatus()
    {
        return _systemUserStatus;
    }

    public void setSystemUserStatus(SystemUserStatus systemUserStatus)
    {
        _systemUserStatus = systemUserStatus;
    }

    public SystemUser getSystemUser()
    {
        return _systemUser;
    }

    public void setSystemUser(SystemUser systemUser)
    {
        _systemUser = systemUser;
    }

}
