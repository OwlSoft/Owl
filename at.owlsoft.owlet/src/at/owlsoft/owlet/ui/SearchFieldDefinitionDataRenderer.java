package at.owlsoft.owlet.ui;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;

import at.owlsoft.owl.model.ISearchFieldDefinition;

public class SearchFieldDefinitionDataRenderer extends ListButtonDataRenderer
{
    public static final SearchFieldDefinitionDataRenderer INSTANCE = new SearchFieldDefinitionDataRenderer();

    @Override
    public void render(Object data, Button btn, boolean highlight)
    {
        if (data instanceof ISearchFieldDefinition)
        {
            ISearchFieldDefinition searchFieldDefinition = (ISearchFieldDefinition) data;
            data = new ButtonData(searchFieldDefinition.getLabel());
        }
        super.render(data, btn, highlight);
    }
}
