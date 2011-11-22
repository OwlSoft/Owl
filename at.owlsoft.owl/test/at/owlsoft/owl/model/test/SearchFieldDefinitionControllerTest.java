package at.owlsoft.owl.model.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.PrivatAccessorFramework;
import at.owlsoft.owl.business.SearchFieldDefinitionController;
import at.owlsoft.owl.model.SearchFieldCategory;
import at.owlsoft.owl.model.SearchFieldDefinition;

public class SearchFieldDefinitionControllerTest
{

    @Test
    public void testXmlParsing()
    {
        StringBuilder bld = new StringBuilder();
        bld.append("<?xml version=\"1.0\"?>");
        bld.append("<SearchFieldCategories>");
        bld.append("    <SearchFieldCategory key=\"GENERAL\" label=\"General\">");
        bld.append("        <SearchFieldDefinition key=\"_name\" type=\"String\" label=\"Name\" />");
        bld.append("    </SearchFieldCategory>");
        bld.append("    <SearchFieldCategory key=\"BOOK\" label=\"Books\">");
        bld.append("        <SearchFieldDefinition key=\"_publisher\" type=\"String\" label=\"Publisher\" /> ");
        bld.append("        <SearchFieldDefinition key=\"_publishedDate\" type=\"DateTime\" label=\"\" />");
        bld.append("        <SearchFieldDefinition key=\"_author\" type=\"String\" label=\"Publisher\" />");
        bld.append("        <SearchFieldDefinition key=\"_title\" type=\"String\" label=\"\" />");
        bld.append("        <SearchFieldDefinition key=\"_abstract\" type=\"String\" label=\"Publisher\" />  ");
        bld.append("        <SearchFieldDefinition key=\"_pages\" type=\"Integer\" label=\"\" />");
        bld.append("        <SearchFieldDefinition key=\"_isbn10\" type=\"String\" label=\"Publisher\" />");
        bld.append("        <SearchFieldDefinition key=\"_isbn13\" type=\"String\" label=\"\" />");
        bld.append("    </SearchFieldCategory>");
        bld.append(" <SearchFieldCategory key=\"SYSTEMUSER\" label=\"SystemUsers\">");
        bld.append("      <SearchFieldDefinition key=\"_firstName\" type=\"String\" label=\"Vorname\" />");
        bld.append("      <SearchFieldDefinition key=\"_lastName\" type=\"String\" label=\"Nachname\" /> ");
        bld.append("  </SearchFieldCategory>");
        bld.append("</SearchFieldCategories>");

        InputStream stream = new ByteArrayInputStream(bld.toString().getBytes());
        SearchFieldDefinitionController controller = new SearchFieldDefinitionController();
        controller.reInitParsXml(stream);

        Map<String, SearchFieldDefinition> mapping = PrivatAccessorFramework
                .getPrivateField(controller, "_mapping");
        List<SearchFieldCategory> allCategories = PrivatAccessorFramework
                .getPrivateField(controller, "_allCategories");

        Assert.assertNotNull(mapping);
        Assert.assertNotNull(allCategories);
        Assert.assertEquals(mapping.size(), 11);
        Assert.assertEquals(allCategories.size(), 3);
        Assert.assertEquals(allCategories.get(0).getSearchFields().size(), 1);
        Assert.assertEquals(allCategories.get(1).getSearchFields().size(), 8);
        Assert.assertEquals(allCategories.get(2).getSearchFields().size(), 2);

        for (Entry<String, SearchFieldDefinition> entry : mapping.entrySet())
        {
            Assert.assertNotNull(entry.getValue().getKey());
            Assert.assertNotNull(entry.getValue().getKeyType());
            Assert.assertNotNull(entry.getValue().getLabel());
        }

    }
}
