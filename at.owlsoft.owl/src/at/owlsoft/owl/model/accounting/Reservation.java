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

import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.SystemUser;

public class Reservation extends Activity
{
    private int _desiredDuration;

    public Reservation()
    {
        super();
    }

    public Reservation(Date startDate, MediumExemplar mediumExemplar,
            SystemUser customer, SystemUser creator, int desiredDuration)
    {
        super(startDate, mediumExemplar, customer, creator);
        _desiredDuration = desiredDuration;
    }

    public int getDesiredDuration()
    {
        return _desiredDuration;
    }

    public void setDesiredDuration(int desiredDuration)
    {
        this._desiredDuration = desiredDuration;
    }

}
