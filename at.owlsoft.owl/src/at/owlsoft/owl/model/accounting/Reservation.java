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

import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.SystemUser;

public class Reservation extends Activity implements IReservation
{
    private static final long serialVersionUID = -1983982838151622776L;

    private int               _desiredDuration;

    public Reservation()
    {
        super();
    }

    public Reservation(Date startDate, Medium medium, SystemUser customer,
            SystemUser creator, int desiredDuration)
    {
        super(startDate, medium, customer, creator);
        _desiredDuration = desiredDuration;
        this.addActivityStatusEntry(new ActivityStatusEntry(new Date(), this,
                ActivityStatus.Open));
    }

    @Override
    public int getDesiredDuration()
    {
        return _desiredDuration;
    }

    public void setDesiredDuration(int desiredDuration)
    {
        this._desiredDuration = desiredDuration;
    }

}
