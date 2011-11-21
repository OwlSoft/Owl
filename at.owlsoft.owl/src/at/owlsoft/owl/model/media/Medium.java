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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.model.accounting.Activity;

public abstract class Medium implements IMedium
{
    private static final long    serialVersionUID = -5970696696729453857L;

    private int                  _mediumID;
    private String               _publisher;
    private String               _name;
    private Date                 _entryDate;
    private Date                 _publishedDate;
    private List<Tag>            _tags;
    private List<MediumExemplar> _mediumExemplars;
    private List<Activity>       _activities;

    public Medium()
    {
        _tags = new ArrayList<Tag>();
        _mediumExemplars = new ArrayList<MediumExemplar>();
        _activities = new ArrayList<Activity>();
    }

    public Medium(int mediumID, String publisher, String name, Date entryDate,
            Date publishedDate)
    {
        super();
        _mediumID = mediumID;
        _publisher = publisher;
        _name = name;
        _entryDate = entryDate;
        _publishedDate = publishedDate;
        _tags = new ArrayList<Tag>();
        _mediumExemplars = new ArrayList<MediumExemplar>();
        _activities = new ArrayList<Activity>();
    }

    @Override
    public int getMediumID()
    {
        return _mediumID;
    }

    public void setMediumID(int mediumID)
    {
        _mediumID = mediumID;
    }

    @Override
    public String getPublisher()
    {
        return _publisher;
    }

    public void setPublisher(String publisher)
    {
        _publisher = publisher;
    }

    @Override
    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    @Override
    public Date getEntryDate()
    {
        return _entryDate;
    }

    public void setEntryDate(Date entryDate)
    {
        _entryDate = entryDate;
    }

    @Override
    public Date getPublishedDate()
    {
        return _publishedDate;
    }

    public void setPublishedDate(Date publishedDate)
    {
        _publishedDate = publishedDate;
    }

    @Override
    public int getTagCount()
    {
        return _tags.size();
    }

    @Override
    public Tag getTag(int index)
    {
        return _tags.get(index);
    }

    @Override
    public List<Tag> getTags()
    {
        return Collections.unmodifiableList(_tags);
    }

    @Override
    public boolean hasTag(String tagValue)
    {
        for (Tag t : _tags)
        {
            if (t.getValue().equals(tagValue))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasTag(String tagValue, TagType type)
    {
        for (Tag t : _tags)
        {
            if (t.getTagType() == type && t.getValue().equals(tagValue))
            {
                return true;
            }
        }
        return false;
    }

    public void clearTags()
    {
        _tags.clear();
    }

    public void addTag(Tag tag)
    {
        tag.setMedium(this);
        _tags.add(tag);
    }

    public void removeTag(Tag tag)
    {
        _tags.remove(tag);
    }

    @Override
    public int getMediumExemplarCount()
    {
        return _mediumExemplars.size();
    }

    @Override
    public MediumExemplar getMediumExemplar(int index)
    {
        return _mediumExemplars.get(index);
    }

    @Override
    public List<MediumExemplar> getMediumExemplars()
    {
        return Collections.unmodifiableList(_mediumExemplars);
    }

    public void clearMediumExemplars()
    {
        _mediumExemplars.clear();
    }

    public void addMediumExemplar(MediumExemplar mediumExemplar)
    {
        mediumExemplar.setMedium(this);
        _mediumExemplars.add(mediumExemplar);
    }

    public void removeMediumExemplar(MediumExemplar mediumExemplar)
    {
        _mediumExemplars.remove(mediumExemplar);
    }

    @Override
    public int getActivityCount()
    {
        return _activities.size();
    }

    @Override
    public Activity getActivity(int index)
    {
        return _activities.get(index);
    }

    @Override
    public List<Activity> getActivities()
    {
        return Collections.unmodifiableList(_activities);
    }

    public void clearActivities()
    {
        _activities.clear();
    }

    public void addActivtiy(Activity activity)
    {
        activity.setMedium(this);
        _activities.add(activity);
    }

    public void removeActivity(Activity activity)
    {
        _activities.remove(activity);
    }
}
