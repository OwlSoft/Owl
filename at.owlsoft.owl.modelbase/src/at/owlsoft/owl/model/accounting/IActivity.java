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

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;

public interface IActivity extends Serializable
{

    UUID getUUID();

    Date getStartDate();

    int getActivityStatusEntryCount();

    IActivityStatusEntry getActivityStatusEntry(int index);

    List<? extends IActivityStatusEntry> getActivityStatusEntries();

    IMediumExemplar getMediumExemplar();

    ISystemUser getCustomer();

    ISystemUser getCreator();

    IMedium getMedium();
}
