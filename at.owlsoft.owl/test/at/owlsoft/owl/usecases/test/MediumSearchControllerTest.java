package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class MediumSearchControllerTest
{

    private String _publisher = "EA Sports";
    private Medium _mediumExpected;

    @Before
    public void setUp()
    {

        // setUP
        Medium m1 = new Book();
        Medium m2 = new Book();

        m1.setPublisher(_publisher);
        Calendar grec = Calendar.getInstance();
        grec.set(2000, 0, 1);
        m1.setPublishedDate(grec.getTime());
        grec.set(2010, 0, 1);
        m2.setPublisher(_publisher);
        m2.setPublishedDate(grec.getTime());
        _mediumExpected = m2;

        ObjectContainer db = Db4oEmbedded.openFile("DB40FILENAME");

        db.store(m1);
        db.store(m2);

    }

    @Test
    public void testSearchListOfSearchField()
    {

        MediumSearchController controller = new MediumSearchController();
        List<SearchField> criteria = new ArrayList<SearchField>();

        Calendar grec = Calendar.getInstance();
        grec.set(2010, 0, 1);

        criteria.add(new SearchField("_publisher", _publisher));
        // criteria.add(new SearchField("_publishedDate", grec.getTime()
        // .toString()));

        List<Medium> result = controller.search(criteria);
        List<Medium> result2 = controller.search(new ArrayList<SearchField>());
        System.out.println(result2.size());

        assertEquals(2, result.size());
        assertEquals(_mediumExpected, result.get(0));

    }
}
