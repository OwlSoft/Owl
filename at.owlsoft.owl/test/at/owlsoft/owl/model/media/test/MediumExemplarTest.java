package at.owlsoft.owl.model.media.test;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.accounting.Activity;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;

public class MediumExemplarTest
{
    @Test
    public void testMediumExemplarStatusEntryRelation()
    {
        MediumExemplar mediumExemplar = new MediumExemplar();

        MediumExemplarStatusEntry entry = new MediumExemplarStatusEntry();

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
}
