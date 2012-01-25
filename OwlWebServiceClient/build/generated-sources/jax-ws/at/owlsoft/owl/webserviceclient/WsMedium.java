
package at.owlsoft.owl.webserviceclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsMedium complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsMedium">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mediumExemplaCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mediumID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsMedium", propOrder = {
    "mediumExemplaCount",
    "mediumID",
    "name",
    "publisher"
})
public class WsMedium {

    protected int mediumExemplaCount;
    protected int mediumID;
    protected String name;
    protected String publisher;

    /**
     * Gets the value of the mediumExemplaCount property.
     * 
     */
    public int getMediumExemplaCount() {
        return mediumExemplaCount;
    }

    /**
     * Sets the value of the mediumExemplaCount property.
     * 
     */
    public void setMediumExemplaCount(int value) {
        this.mediumExemplaCount = value;
    }

    /**
     * Gets the value of the mediumID property.
     * 
     */
    public int getMediumID() {
        return mediumID;
    }

    /**
     * Sets the value of the mediumID property.
     * 
     */
    public void setMediumID(int value) {
        this.mediumID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisher(String value) {
        this.publisher = value;
    }

}
