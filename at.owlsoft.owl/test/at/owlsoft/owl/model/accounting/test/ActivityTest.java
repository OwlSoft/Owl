package at.owlsoft.owl.model.accounting.test;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.accounting.Activity;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;

public class ActivityTest
{
    @Test
    public void testActivityStatusEntryRelation()
    {
        Activity activity = new Activity()
        {

            /**
             * 
             */
            private static final long serialVersionUID = -3914442260582078608L;
            // anonymous test
        };

        ActivityStatusEntry entry = new ActivityStatusEntry();

        // Adding / Count
        activity.addActivityStatusEntry(entry);
        Assert.assertEquals(1, activity.getActivityStatusEntryCount());
        Assert.assertEquals(entry, activity.getActivityStatusEntry(0));
        Assert.assertEquals(activity.getActivityStatusEntries().get(0),
                activity.getActivityStatusEntry(0));
        Assert.assertEquals(activity, entry.getActivity());

        // Removing / Count
        activity.removeActivityStatusEntry(entry);
        Assert.assertEquals(0, activity.getActivityStatusEntryCount());

        // Clear / Count
        activity.addActivityStatusEntry(entry);
        activity.clearActivityStatusEntries();
        Assert.assertEquals(0, activity.getActivityStatusEntryCount());
    }
}
