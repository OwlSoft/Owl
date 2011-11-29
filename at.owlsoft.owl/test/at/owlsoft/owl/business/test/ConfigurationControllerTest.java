package at.owlsoft.owl.business.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import at.owlsoft.owl.business.ConfigurationController;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.model.media.IMedium;

public class ConfigurationControllerTest
{

    @Test
    public void testConfigurationController()
    {

        int defaultDuration = 30;
        int specificDuration = 14;

        ConfigurationController controller = new ConfigurationController(
                new OwlApplicationContext());
        int result = controller.getInt(
                IMedium.class.getName().concat("extensionDuration"),
                defaultDuration);
        assertEquals(defaultDuration, result);

        controller.set(IMedium.class.getName(), specificDuration);
        result = controller.getInt(
                IMedium.class.getName().concat("extensionDuration"),
                specificDuration);
        assertEquals(specificDuration, result);

    }

    @Test
    public void setAllTest() throws IOException
    {
        Map<String, String> testMap = new HashMap<String, String>();
        testMap.put("hugo", "3");
        ConfigurationController controller = new ConfigurationController(
                new OwlApplicationContext());
        controller.setAll(testMap);
        int hugo = controller.getInt("hugo");
        controller.store();
        Assert.assertTrue(hugo == 3);
        List<String> temp = new ArrayList<String>();
        temp.add("hugo");
        controller.removeProperty(temp);
        controller.store();
    }
}
