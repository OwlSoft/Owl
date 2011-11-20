package at.owlsoft.owl.usecases.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import at.owlsoft.owl.model.SearchField;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.usecases.MediumSearchController;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class MediumSearchControllerTest
{

    @Test
    public void testSearchListOfSearchField()
    {
        // setUP
        Medium m1 = new Book();
        Medium m2 = new Book();
        String publisher = "EA Sports";
        m1.setPublisher(publisher);
        Calendar grec = Calendar.getInstance();
        grec.set(2000, 0, 1);
        m1.setPublishedDate(grec.getTime());
        grec.set(2010, 0, 1);
        m2.setPublisher(publisher);
        m2.setPublishedDate(grec.getTime());

        ObjectContainer db = Db4oEmbedded.openFile("DB40FILENAME");

        db.store(m1);
        db.store(m2);

        MediumSearchController controller = new MediumSearchController();
        List<SearchField> criteria = new ArrayList<SearchField>();

        criteria.add(new SearchField("_publisher", publisher));
        criteria.add(new SearchField("_publishedDate", grec.getTime()
                .toString()));

        List<Medium> result = controller.search(criteria);

        assertEquals(1, result.size());
        assertEquals(m2, result.get(0));
    }

}
