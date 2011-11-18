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

public class SystemUserTransaction
{
    private Date            _date;
    private float           _amount;
    private String          _comment;
    private TransactionType _transactionType;
    private SystemUser      _systemUser;

    public SystemUserTransaction()
    {
    }

    public SystemUserTransaction(Date date, float amount, String comment,
            TransactionType transactionType, SystemUser systemUser)
    {
        _date = date;
        _amount = amount;
        _comment = comment;
        _transactionType = transactionType;
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

    public float getAmount()
    {
        return _amount;
    }

    public void setAmount(float amount)
    {
        _amount = amount;
    }

    public String getComment()
    {
        return _comment;
    }

    public void setComment(String comment)
    {
        _comment = comment;
    }

    public TransactionType getTransactionType()
    {
        return _transactionType;
    }

    public void setTransactionType(TransactionType transactionType)
    {
        _transactionType = transactionType;
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
