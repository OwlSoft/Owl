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
package at.owlsoft.owl.model.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.owlsoft.owl.model.accounting.Activity;

public class MediumExemplar
{
    private int                             _exemplarID;
    private Map<String, Object>             _metaData;
    private Medium                          _medium;
    private List<MediumExemplarStatusEntry> _mediumExemplarStatusEntries;
    private List<Activity>                  _activities;

    public MediumExemplar()
    {
        _mediumExemplarStatusEntries = new ArrayList<MediumExemplarStatusEntry>();
        _activities = new ArrayList<Activity>();
        _metaData = new HashMap<String, Object>();
    }

    public MediumExemplar(int exemplarID, Medium medium)
    {
        super();
        _exemplarID = exemplarID;
        _metaData = new HashMap<String, Object>();
        _medium = medium;
        _mediumExemplarStatusEntries = new ArrayList<MediumExemplarStatusEntry>();
        _activities = new ArrayList<Activity>();
    }

    public int getExemplarID()
    {
        return _exemplarID;
    }

    public void setExemplarID(int exemplarID)
    {
        _exemplarID = exemplarID;
    }

    public Object getMetaData(String key)
    {
        return getMetaData(key, null);
    }

    public Object getMetaData(String key, Object defaultValue)
    {
        Object value = _metaData.get(key);
        return (value == null) ? defaultValue : value;
    }

    public void setMetaData(String key, Object value)
    {
        _metaData.put(key, value);
    }

    public Medium getMedium()
    {
        return _medium;
    }

    public void setMedium(Medium medium)
    {
        _medium = medium;
    }

    public Iterable<MediumExemplarStatusEntry> getMediumExemplarStatusEntries()
    {
        return Collections.unmodifiableList(_mediumExemplarStatusEntries);
    }

    public void clearMediumExemplarStatusEntries()
    {
        _mediumExemplarStatusEntries.clear();
    }

    public void addMediumExemplarStatusEntry(
            MediumExemplarStatusEntry mediumExemplarStatusEntry)
    {
        mediumExemplarStatusEntry.setMediumExemplar(this);
        _mediumExemplarStatusEntries.add(mediumExemplarStatusEntry);
    }

    public void removeMediumExemplarStatusEntry(
            MediumExemplarStatusEntry mediumExemplarStatusEntry)
    {
        _mediumExemplarStatusEntries.remove(mediumExemplarStatusEntry);
    }

    public Iterable<Activity> getActivities()
    {
        return Collections.unmodifiableList(_activities);
    }

    public void clearActivities()
    {
        _mediumExemplarStatusEntries.clear();
    }

    public void addActivity(Activity activtiy)
    {
        activtiy.setMediumExemplar(this);
        _activities.add(activtiy);
    }

    public void removeActivity(Activity activity)
    {
        _activities.remove(activity);
    }
}
