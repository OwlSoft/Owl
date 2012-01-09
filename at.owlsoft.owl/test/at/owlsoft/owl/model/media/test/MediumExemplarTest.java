package at.owlsoft.owl.model.media.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.accounting.Activity;
import at.owlsoft.owl.model.media.Book;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;

public class MediumExemplarTest
{
    @Test
    public void testMediumExemplarStatusEntryRelation()
    {
        MediumExemplar mediumExemplar = new MediumExemplar();

        MediumExemplarStatusEntry entry = new MediumExemplarStatusEntry();

        mediumExemplar.clearMediumExemplarStatusEntries();

        // Adding / Count
        mediumExemplar.addMediumExemplarStatusEntry(entry);
        Assert.assertEquals(1,
                mediumExemplar.getMediumExemplarStatusEntryCount());
        Assert.assertEquals(entry,
                mediumExemplar.getMediumExemplarStatusEntry(0));
        Assert.assertEquals(mediumExemplar.getMediumExemplarStatusEntries()
                .get(0), mediumExemplar.getMediumExemplarStatusEntry(0));
        Assert.assertEquals(mediumExemplar, entry.getMediumExemplar());

        // Removing / Count
        mediumExemplar.removeMediumExemplarStatusEntry(entry);
        Assert.assertEquals(0,
                mediumExemplar.getMediumExemplarStatusEntryCount());

        // Clear / Count
        mediumExemplar.addMediumExemplarStatusEntry(entry);
        mediumExemplar.clearMediumExemplarStatusEntries();
        Assert.assertEquals(0,
                mediumExemplar.getMediumExemplarStatusEntryCount());
    }

    @Test
    public void testActivityRelation()
    {
        MediumExemplar mediumExemplar = new MediumExemplar();

        Activity activity = new Activity()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 6191042107243561121L;
        };

        // Adding / Count
        mediumExemplar.addActivity(activity);
        Assert.assertEquals(1, mediumExemplar.getActivityCount());
        Assert.assertEquals(activity, mediumExemplar.getActivity(0));
        Assert.assertEquals(mediumExemplar.getActivities().get(0),
                mediumExemplar.getActivity(0));
        Assert.assertEquals(mediumExemplar, activity.getMediumExemplar());

        // Removing / Count
        mediumExemplar.removeActivity(activity);
        Assert.assertEquals(0, mediumExemplar.getActivityCount());

        // Clear / Count
        mediumExemplar.addActivity(activity);
        mediumExemplar.clearActivities();
        Assert.assertEquals(0, mediumExemplar.getActivityCount());
    }

    @Test
    public void testGetCurrentState()
    {
        // create another not rentable test medium

        Calendar calendar = Calendar.getInstance();

        calendar.set(2006, 0, 1);
        Date publishedDate = calendar.getTime();

        calendar.set(2000, 0, 1);
        Medium bookOne = new Book(1, "Reclam", "Poetik", new Date(),
                publishedDate, "Aristoteles", "Poetik", "Karthasis", 182, null,
                "978-3-15-007828-0");

        // add medium exemplare
        MediumExemplar bookOneCopy = new MediumExemplar(2, bookOne);

        try
        {

            Thread.sleep(50);
        }
        catch (Exception e)
        {
            System.out.println("Warning");
        }

        bookOne.addMediumExemplar(bookOneCopy);
        bookOneCopy.addMediumExemplarStatusEntry(new MediumExemplarStatusEntry(
                new Date(), bookOneCopy, MediumExemplarStatus.StockItem));

        Assert.assertEquals(MediumExemplarStatus.StockItem,
                bookOneCopy.getCurrentState());
    }
}
