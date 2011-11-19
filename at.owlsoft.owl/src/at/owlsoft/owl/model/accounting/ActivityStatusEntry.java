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

public class ActivityStatusEntry
{
    private Date           _date;
    private Activity       _activity;
    private ActivityStatus _activityStatus;

    public ActivityStatusEntry()
    {
    }

    public ActivityStatusEntry(Date date, Activity activity,
            ActivityStatus activityStatus)
    {
        super();
        _date = date;
        _activity = activity;
        _activityStatus = activityStatus;
    }

    public Date getDate()
    {
        return _date;
    }

    public void setDate(Date date)
    {
        _date = date;
    }

    public Activity getActivity()
    {
        return _activity;
    }

    public void setActivity(Activity activity)
    {
        _activity = activity;
    }

    public ActivityStatus getActivityStatus()
    {
        return _activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus)
    {
        _activityStatus = activityStatus;
    }
}
