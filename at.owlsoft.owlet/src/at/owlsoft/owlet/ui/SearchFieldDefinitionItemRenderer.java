package at.owlsoft.owlet.ui;

import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.owlsoft.owl.model.ISearchFieldDefinition;

public class SearchFieldDefinitionItemRenderer extends ListViewItemRenderer
{
    public static final SearchFieldDefinitionItemRenderer INSTANCE = new SearchFieldDefinitionItemRenderer();

    @Override
    public void render(Object item, int index, ListView listView,
            boolean selected, boolean checked, boolean highlighted,
            boolean disabled)
    {
        if (item instanceof ISearchFieldDefinition)
        {
            label.setText(((ISearchFieldDefinition) item).getLabel());
            return;
        }

        super.render(item, index, listView, selected, checked, highlighted,
                disabled);
    }

    //
    // @Override
    // public void render(Object data, Button btn, boolean highlight)
    // {
    // if (data instanceof ISearchFieldDefinition)
    // {
    // ISearchFieldDefinition searchFieldDefinition = (ISearchFieldDefinition)
    // data;
    // data = new ButtonData(searchFieldDefinition.getLabel());
    // }
    // super.render(data, btn, highlight);
    // }
}
