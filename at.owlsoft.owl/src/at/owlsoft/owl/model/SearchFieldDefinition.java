package at.owlsoft.owl.model;

public class SearchFieldDefinition implements ISearchFieldDefinition
{
    private static final long serialVersionUID = 6935396387381422618L;

    private String            _key;
    private String            _label;

    public SearchFieldDefinition()
    {
    }

    public SearchFieldDefinition(String key, String label)
    {
        super();
        _key = key;
        _label = label;
    }

    @Override
    public String getKey()
    {
        return _key;
    }

    public void setKey(String key)
    {
        _key = key;
    }

    @Override
    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String label)
    {
        _label = label;
    }

    // TODO: A search field definition is no searchfield provider! a controller
    // should provide this list of definitions
    // @Override
    // public List<ISearchField> getKeys()
    // {
    //
    // List<ISearchField> temp = new ArrayList<ISearchField>();
    // DocumentBuilderFactory parserFactory = DocumentBuilderFactory
    // .newInstance();
    // DocumentBuilder parser;
    // try
    // {
    // parser = parserFactory.newDocumentBuilder();
    // File file = new File("searchfields.xml");
    // Document dom = parser.parse(file);
    // Element root = dom.getDocumentElement();
    // NodeList nl = root.getChildNodes();
    // if (nl != null && nl.getLength() > 0)
    // {
    // for (int i = 0; i < nl.getLength(); i++)
    // {
    // Element element = (Element) nl.item(i);
    // String key = element.getFirstChild().getNodeValue();
    // SearchField sf = new SearchField();
    // sf.setKey(key);
    // temp.add(sf);
    // }
    // }
    //
    // }
    // catch (ParserConfigurationException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // catch (SAXException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // catch (IOException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // return temp;
    //
    // }

}
