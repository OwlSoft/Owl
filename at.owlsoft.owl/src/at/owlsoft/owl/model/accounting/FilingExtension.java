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
public package at.owlsoft.owl.model.accounting;

import java.util.Date;

public class FilingExtension
{
    private Date   _creationDate;
    private Date   _newEndDate;
    private Rental _rental;

    public FilingExtension()
    {
    }

    public FilingExtension(Date creationDate, Date newEndDate, Rental rental)
    {
        super();
        _creationDate = creationDate;
        _newEndDate = newEndDate;
        _rental = rental;
    }

    public Date getCreationDate()
    {
        return _creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        _creationDate = creationDate;
    }

    public Date getNewEndDate()
    {
        return _newEndDate;
    }

    public void setNewEndDate(Date newEndDate)
    {
        _newEndDate = newEndDate;
    }

    public Rental getRental()
    {
        return _rental;
    }

    public void setRental(Rental rental)
    {
        _rental = rental;
    }

}
