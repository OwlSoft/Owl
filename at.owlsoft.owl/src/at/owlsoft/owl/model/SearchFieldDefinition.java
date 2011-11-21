package at.owlsoft.owl.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SearchFieldDefinition
{

    public SearchFieldDefinition()
    {
        super();
        _keys = new ArrayList<SearchField>();
    }

    private List<SearchField> _keys;

    /**
     * @return the keys
     */
    public List<SearchField> getKeys()
    {
        return _keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(List<SearchField> keys)
    {
        _keys = keys;
    }

    public void loadKeysFromXml()
    {

        List<SearchField> temp = new ArrayList<SearchField>();
        DocumentBuilderFactory parserFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder parser;
        try
        {
            parser = parserFactory.newDocumentBuilder();
            File file = new File("searchfields.xml");
            Document dom = parser.parse(file);
            Element root = dom.getDocumentElement();
            NodeList nl = root.getChildNodes();
            if (nl != null && nl.getLength() > 0)
            {
                for (int i = 0; i < nl.getLength(); i++)
                {
                    Element element = (Element) nl.item(i);
                    String key = element.getFirstChild().getNodeValue();
                    SearchField sf = new SearchField();
                    sf.setKey(key);
                    temp.add(sf);
                }
            }

        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        _keys = temp;

    }

}
