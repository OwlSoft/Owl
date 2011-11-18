package at.owlsoft.owl.model.media.test;

import junit.framework.Assert;

import org.junit.Test;

import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.Tag;

public class MediumTest
{
    @Test
    public void testTagRelation()
    {
        Medium medium = new Medium()
        {
        };

        Tag tag = new Tag();

        // Adding / Count
        medium.addTag(tag);
        Assert.assertEquals(1, medium.getTagCount());
        Assert.assertEquals(tag, medium.getTag(0));
        Assert.assertEquals(medium.getTags().get(0), medium.getTag(0));
        Assert.assertEquals(medium, tag.getMedium());

        // Removing / Count
        medium.removeTag(tag);
        Assert.assertEquals(0, medium.getTagCount());

        // Clear / Count
        medium.addTag(tag);
        medium.clearTags();
        Assert.assertEquals(0, medium.getTagCount());
    }

    @Test
    public void testMediumExemplarRelation()
    {
        Medium medium = new Medium()
        {
        };

        MediumExemplar exemplar = new MediumExemplar();

        // Adding / Count
        medium.addMediumExemplar(exemplar);
        Assert.assertEquals(1, medium.getMediumExemplarCount());
        Assert.assertEquals(exemplar, medium.getMediumExemplar(0));
        Assert.assertEquals(medium.getMediumExemplars().get(0),
                medium.getMediumExemplar(0));
        Assert.assertEquals(medium, exemplar.getMedium());

        // Removing / Count
        medium.removeMediumExemplar(exemplar);
        Assert.assertEquals(0, medium.getMediumExemplarCount());

        // Clear / Count
        medium.addMediumExemplar(exemplar);
        medium.clearMediumExemplars();
        Assert.assertEquals(0, medium.getMediumExemplarCount());
    }
}
