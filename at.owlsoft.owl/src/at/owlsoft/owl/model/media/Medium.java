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
import java.util.Date;
import java.util.List;

public abstract class Medium
{
    private int                  _mediumID;
    private String               _publisher;
    private String               _name;
    private Date                 _entryDate;
    private Date                 _publishedDate;
    private List<Tag>            _tags;
    private List<MediumExemplar> _mediumExemplar;

    public Medium()
    {
        _tags = new ArrayList<Tag>();
        _mediumExemplar = new ArrayList<MediumExemplar>();
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
        _mediumExemplar = new ArrayList<MediumExemplar>();
    }

    public int getMediumID()
    {
        return _mediumID;
    }

    public void setMediumID(int mediumID)
    {
        _mediumID = mediumID;
    }

    public String getPublisher()
    {
        return _publisher;
    }

    public void setPublisher(String publisher)
    {
        _publisher = publisher;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public Date getEntryDate()
    {
        return _entryDate;
    }

    public void setEntryDate(Date entryDate)
    {
        _entryDate = entryDate;
    }

    public Date getPublishedDate()
    {
        return _publishedDate;
    }

    public void setPublishedDate(Date publishedDate)
    {
        _publishedDate = publishedDate;
    }

    public Iterable<Tag> getTags()
    {
        return Collections.unmodifiableList(_tags);
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

    public Iterable<MediumExemplar> getMediumExemplar()
    {
        return Collections.unmodifiableList(_mediumExemplar);
    }

    public void clearMediumExemplar()
    {
        _tags.clear();
    }

    public void addMediumExemplar(MediumExemplar mediumExemplar)
    {
        mediumExemplar.setMedium(this);
        _mediumExemplar.add(mediumExemplar);
    }

    public void removeMediumExemplar(MediumExemplar mediumExemplar)
    {
        _mediumExemplar.remove(mediumExemplar);
    }
}
