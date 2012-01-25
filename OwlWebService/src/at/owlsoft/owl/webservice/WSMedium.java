package at.owlsoft.owl.webservice;

import javax.xml.bind.annotation.XmlTransient;

import at.owlsoft.owl.model.media.IMedium;

public class WSMedium
{

    @XmlTransient
    IMedium        _iMedium;

    private int    _mediumID;
    private String _publisher;
    private String _name;
    private int    _mediumExemplaCount;

    @XmlTransient
    public void setIMedium(IMedium medium)
    {
        setMediumID(medium.getMediumID());
        setMediumExemplaCount(medium.getMediumExemplarCount());
        setName(medium.getName());
        setPublisher(medium.getPublisher());
    }

    public int getMediumID()
    {
        return _mediumID;
    }

    public void setMediumID(int mediumID)
    {
        _mediumID = mediumID;
    }

    public int getMediumExemplaCount()
    {
        return _mediumExemplaCount;
    }

    public void setMediumExemplaCount(int mediumExemplaCount)
    {
        _mediumExemplaCount = mediumExemplaCount;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getPublisher()
    {
        return _publisher;
    }

    public void setPublisher(String publisher)
    {
        _publisher = publisher;
    }

}
