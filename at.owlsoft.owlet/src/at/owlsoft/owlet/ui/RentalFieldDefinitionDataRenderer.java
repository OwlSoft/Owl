package at.owlsoft.owlet.ui;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;

import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owlet.util.PivotUtils;

public class RentalFieldDefinitionDataRenderer extends ListButtonDataRenderer
{
    public static final RentalFieldDefinitionDataRenderer INSTANCE = new RentalFieldDefinitionDataRenderer();

    @Override
    public void render(Object data, Button btn, boolean highlight)
    {
        if (data instanceof IRental)
        {
            data = new ButtonData(
                    PivotUtils.convertIRentalToString((IRental) data));
        }
        super.render(data, btn, highlight);
    }
}
