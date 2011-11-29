package at.owlsoft.owl.business;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

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
                IMedium.class.getName().concat("ExtensionDuration"),
                defaultDuration);
        assertEquals(defaultDuration, result);

        controller.set(IMedium.class.getName(), specificDuration);
        result = controller.getInt(
                IMedium.class.getName().concat("ExtensionDuration"),
                specificDuration);
        assertEquals(14, result);

        try
        {
            controller.store();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
