package at.owlsoft.owl.communication.corba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.corbamodel.ICorbaSearchField;
import at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategory;
import at.owlsoft.owl.corbamodel.ICorbaSearchFieldCategoryHelper;
import at.owlsoft.owl.corbamodel.ICorbaSearchFieldHelper;
import at.owlsoft.owl.corbamodel.media.ICorbaMedium;
import at.owlsoft.owl.corbamodel.media.ICorbaMediumHelper;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;

public class SearchApi extends ICorbaSearchApiPOA
{

    private static final Logger             logger = Logger.getLogger(SearchApi.class);

    private ApiFactory                      _factory;

    private SearchFieldDefinitionController _fieldcontroller;
    private MediumSearchController          _searchController;

    private Map<UUID, SearchField>          _searchFields;

    public SearchApi(ApiFactory apiFactory)
    {
        _factory = apiFactory;
        _fieldcontroller = _factory.getContext()
                .getServerSearchFieldDefinitionController();
        _searchFields = new HashMap<UUID, SearchField>();
        _searchController = _factory.getContext().getMediumSearchController();
    }

    @Override
    public ICorbaSearchFieldCategory[] getSearchFieldCategories()
    {
        try
        {
            ICorbaSearchFieldCategory[] temp = new ICorbaSearchFieldCategory[_fieldcontroller
                    .getAllCategories().size()];
            int index = 0;
            for (SearchFieldCategory category : _fieldcontroller
                    .getAllCategories())
            {
                CorbaSearchFieldCategory cCategory = new CorbaSearchFieldCategory();
                cCategory.setSearchFieldCategory(category);
                cCategory.setRootPOA(_factory.getRootPOA());
                org.omg.CORBA.Object ref = _factory.getRootPOA()
                        .servant_to_reference(cCategory);
                temp[index] = ICorbaSearchFieldCategoryHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaSearchField addNewSearchField(String uniqueId)
    {
        try
        {
            SearchField searchField = new SearchField();

            CorbaSearchField cField = new CorbaSearchField();
            _searchFields.put(UUID.fromString(uniqueId), searchField);
            cField.setRootPOA(_factory.getRootPOA());
            cField.setSearchField(searchField);
            org.omg.CORBA.Object ref = _factory.getRootPOA()
                    .servant_to_reference(new CorbaSystemUser());
            return ICorbaSearchFieldHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSearchField(String uniqueId)
    {
        _searchFields.remove(UUID.fromString(uniqueId));
        dump();
    }

    private void dump()
    {
        System.out.println("Dump!");
        for (Entry<UUID, SearchField> entry : _searchFields.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println("    k:" + entry.getValue().getKey());
            System.out.println("    v:" + entry.getValue().getValue());
            System.out.println("---");
        }
    }

    @Override
    public void setSearchFieldData(String uniqueId, String key, String value)
    {
        UUID uuid = UUID.fromString(uniqueId);
        SearchField field = _searchFields.get(uuid);

        if (field != null)
        {
            field.setKey(key);
            field.setValue(value);
        }
        else
        {
            field = new SearchField(key, value, SearchFieldType.Equals);
            _searchFields.put(uuid, field);
        }
        dump();

    }

    @Override
    public ICorbaMedium[] search()
    {
        try
        {
            List<SearchField> fields = new ArrayList<SearchField>();
            logger.trace("building criterias");
            for (SearchField searchField : _searchFields.values())
            {
                if (searchField != null && searchField.getKey() != null
                        && searchField.getValue() != null
                        && !searchField.getValue().trim().isEmpty())
                {
                    if (searchField.getType() == null)
                    {
                        searchField.setType(SearchFieldType.Equals);
                    }
                    logger.trace("  Search includes " + searchField.getKey()
                            + ":" + searchField.getValue());

                    fields.add(searchField);
                }
            }

            logger.trace("Searching with " + fields.size() + " criterias");

            List<Medium> result = _searchController.search(fields);

            logger.trace("found: " + result.size());

            try
            {
                ICorbaMedium[] temp = new ICorbaMedium[result.size()];
                int index = 0;
                for (Medium medium : result)
                {
                    CorbaMedium cMedium = new CorbaMedium();
                    cMedium.setMedium(medium);
                    cMedium.setRootPOA(_factory.getRootPOA());
                    org.omg.CORBA.Object ref = _factory.getRootPOA()
                            .servant_to_reference(cMedium);
                    temp[index] = ICorbaMediumHelper.narrow(ref);
                    index++;
                }
                return temp;
            }
            catch (ServantNotActive e)
            {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            catch (WrongPolicy e)
            {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        catch (Exception e)
        {
            logger.error("Could not search", e);
            throw new RuntimeException(e.toString());
        }
    }

}
