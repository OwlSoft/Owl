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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.SystemUser;

public abstract class Activity implements IActivity
{
    private static final long         serialVersionUID = -1202400625902106682L;

    private Date                      _startDate;
    private List<ActivityStatusEntry> _activityStatusEntries;
    private MediumExemplar            _mediumExemplar;
    private Medium                    _medium;
    private SystemUser                _customer;
    private SystemUser                _creator;

    protected Activity()
    {
        _activityStatusEntries = new ArrayList<ActivityStatusEntry>();
    }

    protected Activity(Date startDate, MediumExemplar mediumExemplar,
            SystemUser customer, SystemUser creator)
    {
        _startDate = startDate;
        _activityStatusEntries = new ArrayList<ActivityStatusEntry>();
        _mediumExemplar = mediumExemplar;
        _customer = customer;
        _creator = creator;
    }

    @Override
    public Date getStartDate()
    {
        return _startDate;
    }

    public void setStartDate(Date startDate)
    {
        _startDate = startDate;
    }

    @Override
    public int getActivityStatusEntryCount()
    {
        return _activityStatusEntries.size();
    }

    @Override
    public ActivityStatusEntry getActivityStatusEntry(int index)
    {
        return _activityStatusEntries.get(index);
    }

    @Override
    public List<ActivityStatusEntry> getActivityStatusEntries()
    {
        return Collections.unmodifiableList(_activityStatusEntries);
    }

    public void clearActivityStatusEntries()
    {
        _activityStatusEntries.clear();
    }

    public void addActivityStatusEntry(ActivityStatusEntry activityStatusEntry)
    {
        activityStatusEntry.setActivity(this);
        _activityStatusEntries.add(activityStatusEntry);
    }

    public void removeActivityStatusEntry(
            ActivityStatusEntry activityStatusEntry)
    {
        _activityStatusEntries.remove(activityStatusEntry);
    }

    @Override
    public MediumExemplar getMediumExemplar()
    {
        return _mediumExemplar;
    }

    public void setMediumExemplar(MediumExemplar mediumExemplar)
    {
        if (_medium != null && mediumExemplar != null)
        {
            throw new InvalidOperationException(
                    "This activity is already assigned to a Medium");
        }
        _mediumExemplar = mediumExemplar;
    }

    @Override
    public SystemUser getCustomer()
    {
        return _customer;
    }

    public void setCustomer(SystemUser customer)
    {
        _customer = customer;
    }

    @Override
    public SystemUser getCreator()
    {
        return _creator;
    }

    public void setCreator(SystemUser creator)
    {
        _creator = creator;
    }

    @Override
    public Medium getMedium()
    {
        return _medium;
    }

    public void setMedium(Medium medium)
    {
        if (_mediumExemplar != null && medium != null)
        {
            throw new InvalidOperationException(
                    "This activity is already assigned to a MediumExemplar");
        }
        _medium = medium;
    }
}
