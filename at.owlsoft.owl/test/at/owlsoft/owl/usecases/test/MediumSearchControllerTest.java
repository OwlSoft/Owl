package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.SearchFieldType;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;
import db4oTestObjectGenerator.TestDataBaseConstructor;

public class MediumSearchControllerTest
{

    private static String                _publisher = "EA Sports";
    private static String                _name      = "FM2012 Strategy Guide";
    private static Medium                _mediumExpected;

    private static OwlApplicationContext _context;

    @BeforeClass
    public static void setup()
    {
        _context = new OwlApplicationContext();

        // // setUP
        Medium m1 = new Book();
        Medium m2 = new Book();

        m1.setPublisher(_publisher);
        Calendar grec = Calendar.getInstance();
        grec.set(2000, 0, 1);
        m1.setPublishedDate(grec.getTime());
        grec.set(2010, 0, 1);
        m2.setPublisher(_publisher);
        m2.setPublishedDate(grec.getTime());
        m2.setName(_name);
        _mediumExpected = m2;

        IMediumDao mdao = DaoManager.getInstance("MEDIUMSEARCHCONTROLLERTEST")
                .getMediumDao();
        mdao.store(m1);
        mdao.store(m2);
    }

    @AfterClass
    public static void tearDown()
    {
        DaoManager.closeDbConnection();
        new File("MEDIUMSEARCHCONTROLLERTEST").delete();
    }

    @Test
    public void testSearchListOfSearchField()
    {

        MediumSearchController controller = _context
                .getMediumSearchController();
        List<SearchField> criteria = new ArrayList<SearchField>();

        // test find many media
        criteria.add(new SearchField("_publisher", _publisher,
                SearchFieldType.Equals));
        List<Medium> result = controller.search(criteria);

        assertEquals(2, result.size());
        // test find exaclty one meda
        criteria.add(new SearchField("_name", _name, SearchFieldType.Equals));
        result = controller.search(criteria);
        assertEquals(_mediumExpected.getName(), result.get(0).getName());
        assertEquals(_mediumExpected.getPublisher(), result.get(0)
                .getPublisher());

    }

    @Test
    public void dataBaseTest()
    {
        TestDataBaseConstructor.setUpDataBase();
        MediumSearchController controller = _context
                .getMediumSearchController();
        List<SearchField> criteria = new ArrayList<SearchField>();
        criteria.add(new SearchField("_title", "Faust", SearchFieldType.Equals));
        List<Medium> result = controller.search(criteria);
        assertEquals(1, result.size());
        assertEquals("Faust", ((Book) result.get(0)).getTitle());
    }
}
