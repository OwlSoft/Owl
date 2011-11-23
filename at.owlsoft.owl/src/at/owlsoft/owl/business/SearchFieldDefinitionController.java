package at.owlsoft.owl.business;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldDataType;
import at.owlsoft.owl.model.SearchFieldDefinition;

public class SearchFieldDefinitionController extends ControllerBase implements
        ISearchFieldValueConverter
{
    private Map<String, SearchFieldDefinition> _mapping;
    private List<SearchFieldCategory>          _allCategories;
    private Logger                             _logger = Logger.getLogger(SearchFieldDefinitionController.class);

    public List<SearchFieldCategory> getAllCategories()
    {
        return _allCategories;
    }

    public SearchFieldDefinitionController(OwlApplicationContext context)
    {
        super(context);
        _mapping = new HashMap<String, SearchFieldDefinition>();
        _allCategories = new ArrayList<SearchFieldCategory>();

        reInitParsXml(SearchFieldDefinitionController.class
                .getResourceAsStream("/SearchFieldCategories.xml"));
    }

    public SearchFieldDefinitionController(OwlApplicationContext context,
            InputStream stream)
    {
        super(context);
        _mapping = new HashMap<String, SearchFieldDefinition>();
        _allCategories = new ArrayList<SearchFieldCategory>();

        reInitParsXml(stream);
    }

    public void reInitParsXml(InputStream stream)
    {
        _mapping = new HashMap<String, SearchFieldDefinition>();
        _allCategories = new ArrayList<SearchFieldCategory>();

        try
        {
            DocumentBuilderFactory parserFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder parser = parserFactory.newDocumentBuilder();

            Document dom = parser.parse(stream);
            Element root = dom.getDocumentElement();
            NodeList searchFieldCategories = root
                    .getElementsByTagName("SearchFieldCategory");

            if (searchFieldCategories != null
                    && searchFieldCategories.getLength() > 0)
            {
                for (int i = 0; i < searchFieldCategories.getLength(); i++)
                {

                    Element searchFieldCategory = (Element) searchFieldCategories
                            .item(i);
                    String key = searchFieldCategory.getAttribute("key");
                    String label = searchFieldCategory.getAttribute("label");
                    NodeList searchFieldDefinitions = searchFieldCategory
                            .getElementsByTagName("SearchFieldDefinition");

                    SearchFieldCategory category = new SearchFieldCategory(
                            label);

                    _allCategories.add(category);

                    if (searchFieldCategory != null)
                    {
                        for (int j = 0; j < searchFieldDefinitions.getLength(); j++)
                        {

                            Element childNode = (Element) searchFieldDefinitions
                                    .item(j);

                            String definitionKey = childNode
                                    .getAttribute("key");
                            String definitionLabel = childNode
                                    .getAttribute("label");

                            String definitionType = childNode
                                    .getAttribute("type");

                            SearchFieldDefinition definition = new SearchFieldDefinition(
                                    definitionKey, definitionLabel,
                                    SearchFieldDataType.Convert(definitionType));

                            category.addSearchField(definition);
                            _mapping.put(definition.getKey(), definition);
                        }

                    }

                    // String type = searchFieldCategory.getAttribute("type");

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

    public SearchFieldDefinition getDefinitionForField(SearchField field)
    {
        return _mapping.get(field.getKey());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(ISearchField searchField)
    {
        SearchFieldDefinition searchFieldDefinition = _mapping.get(searchField
                .getKey());

        SearchFieldDataType type = SearchFieldDataType.Undefinied;

        if (searchFieldDefinition != null)
        {
            _logger.debug("no values for the included key found");
            type = searchFieldDefinition.getKeyType();
        }

        try
        {
            switch (type)
            {
                case DateTime:

                    String dateFormat = "dd.MM.yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

                    return (T) sdf.parse(searchField.getValue());

                case Integer:

                    return (T) new Integer(searchField.getValue());

                case String:
                    return (T) searchField.getValue();
                case UUID:
                    return (T) UUID.fromString(searchField.getValue());

                default:
                    _logger.debug("no convertable type found correct type found");
                    return null;
            }
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }
}
