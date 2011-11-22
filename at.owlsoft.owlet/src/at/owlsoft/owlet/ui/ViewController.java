package at.owlsoft.owlet.ui;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Component;

/**
 * This controller loads the different views
 */
public class ViewController
{
    /**
     * This is the file extension of pivot files
     */
    public static final String    PIVOT_FILE_EXTENSION = ".bxml";

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static ViewController _singleton;

    /**
     * Initializes a new instance of the {@link ViewController} class.
     */
    private ViewController()
    {

    }

    /**
     * Gets the instance of the {@link ViewController}
     * 
     * @return The instance of the {@link ViewController}
     */
    public static ViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ViewController();
        }
        return _singleton;
    }

    /**
     * Which content to load into the Content pane
     * 
     * @param file BXML file
     * @param component The calling component
     */
    public void loadContent(String file, Component component)
    {
        MainWindow wnd = (MainWindow) component.getWindow();
        loadContent(file, wnd);
    }

    /**
     * Which content to load into the Content pane
     * 
     * @param file BXML file
     * @param component The calling component
     */
    public void loadContent(String file, OwletView component)
    {
        MainWindow wnd = (MainWindow) component.getWindow();
        loadContent(file, wnd);
    }

    /**
     * Which content to load into the Content pane
     * 
     * @param file BXML file
     * @param mainWindow The main window of our application
     */
    public void loadContent(String file, MainWindow mainWindow)
    {

        OwletView oldView = mainWindow.getViewBox().getLength() == 0 ? null
                : (OwletView) mainWindow.getViewBox().get(0);

        // do onClose method
        if (oldView == null || oldView.onViewClosing())
        {
            // closing is allowed
            try
            {
                OwletView newView = (OwletView) new BXMLSerializer()
                        .readObject(ViewController.class, file
                                + PIVOT_FILE_EXTENSION);

                // do onLoad method
                newView.onViewOpening();

                mainWindow.getViewBox().removeAll();
                mainWindow.getViewBox().add(newView);

                if (oldView != null)
                {
                    oldView.onViewClosed();
                }
                newView.onViewOpened();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (SerializationException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }
    }
}
