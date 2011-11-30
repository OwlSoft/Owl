package at.owlsoft.owlet.ui;

import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owlet.util.PivotUtils;

public class RentalFieldDefinitionItemRenderer extends ListViewItemRenderer
{
    public static final RentalFieldDefinitionItemRenderer INSTANCE = new RentalFieldDefinitionItemRenderer();

    @Override
    public void render(Object item, int index, ListView listView,
            boolean selected, boolean checked, boolean highlighted,
            boolean disabled)
    {
        if (item instanceof IRental)
        {
            label.setText(PivotUtils.convertIRentalToString((IRental) item));
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
