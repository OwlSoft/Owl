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
public package at.owlsoft.owl.model.media;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import at.owlsoft.owl.model.accounting.IActivity;
import at.owlsoft.owl.model.accounting.IRental;

public interface IMediumExemplar extends Serializable
{
    int getExemplarID();

    Object getMetaData(String key);

    Set<String> getMetaDataKeys();

    Object getMetaData(String key, Object defaultValue);

    IMedium getMedium();

    int getMediumExemplarStatusEntryCount();

    IMediumExemplarStatusEntry getMediumExemplarStatusEntry(int index);

    List<? extends IMediumExemplarStatusEntry> getMediumExemplarStatusEntries();

    int getActivityCount();

    IActivity getActivity(int index);

    List<? extends IActivity> getActivities();

    IRental getLastRental();
}
