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

import java.util.Date;

public class MediumExemplarStatusEntry
{
    private Date                 _date;
    private MediumExemplar       _mediumExemplar;
    private MediumExemplarStatus _mediumExemplarStatus;

    public MediumExemplarStatusEntry()
    {
    }

    public MediumExemplarStatusEntry(Date date, MediumExemplar mediumExemplar,
            MediumExemplarStatus mediumExemplarStatus)
    {
        super();
        _date = date;
        _mediumExemplar = mediumExemplar;
        _mediumExemplarStatus = mediumExemplarStatus;
    }

    public Date getDate()
    {
        return _date;
    }

    public void setDate(Date date)
    {
        _date = date;
    }

    public MediumExemplar getMediumExemplar()
    {
        return _mediumExemplar;
    }

    public void setMediumExemplar(MediumExemplar mediumExemplar)
    {
        _mediumExemplar = mediumExemplar;
    }

    public MediumExemplarStatus getMediumExemplarStatus()
    {
        return _mediumExemplarStatus;
    }

    public void setMediumExemplarStatus(
            MediumExemplarStatus mediumExemplarStatus)
    {
        _mediumExemplarStatus = mediumExemplarStatus;
    }

}
