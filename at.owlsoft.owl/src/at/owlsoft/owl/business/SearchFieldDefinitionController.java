package at.owlsoft.owl.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldDataType;
import at.owlsoft.owl.model.SearchFieldDefinition;

public class SearchFieldDefinitionController
{
    private Map<String, SearchFieldDefinition> _mapping;
    private List<SearchFieldCategory>          _allCategories;

    public List<SearchFieldCategory> getAllCategories()
    {
        return _allCategories;
    }

    public SearchFieldDefinitionController(String searchFieldCategoriesXmlPath)
    {
        _mapping = new HashMap<String, SearchFieldDefinition>();
        _allCategories = new ArrayList<SearchFieldCategory>();
    }

    public void reInitParsXml(String xmlPath)
    {
        _mapping = new HashMap<String, SearchFieldDefinition>();
        _allCategories = new ArrayList<SearchFieldCategory>();

        try
        {
            DocumentBuilderFactory parserFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder parser = parserFactory.newDocumentBuilder();

            File file = new File(xmlPath);
            Document dom = parser.parse(file);
            Element root = dom.getDocumentElement();
            NodeList searchFieldCategories = root
                    .getElementsByTagName("SearchFieldCategory");

            if (searchFieldCategories != null
                    && searchFieldCategories.getLength() > 0)
            {
                for (int i = 0; i < searchFieldCategories.getLength(); i++)
                {

                    Element element = (Element) searchFieldCategories.item(i);
                    String key = element.getAttribute("key");
                    String label = element.getAttribute("label");
                    NodeList searchFieldDefinitions = element.getChildNodes();

                    SearchFieldCategory category = new SearchFieldCategory(
                            label);

                    _allCategories.add(category);

                    if (searchFieldDefinitions != null
                            && searchFieldDefinitions.getLength() > 0)
                    {
                        for (int j = 0; j < searchFieldDefinitions.getLength(); j++)
                        {

                            String definitionKey = element.getAttribute("key");
                            String definitionLabel = element
                                    .getAttribute("label");

                            String definitionType = element
                                    .getAttribute("type");

                            SearchFieldDefinition definition = new SearchFieldDefinition(
                                    definitionKey, definitionLabel,
                                    SearchFieldDataType.Convert(definitionType));

                            category.addSearchField(definition);
                        }

                    }

                    String type = element.getAttribute("type");

                    SearchField sf = new SearchField();
                    sf.setKey(key);
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

    }

    public SearchFieldDefinitionController()
    {
        this("SearchFieldCategories.xml");
    }

    public SearchFieldDefinition getDefinitionForField(SearchField field)
    {
        return _mapping.get(field.getKey());
    }
}
