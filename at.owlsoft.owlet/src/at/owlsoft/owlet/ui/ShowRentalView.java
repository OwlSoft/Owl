package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Prompt;

import at.owlsoft.owlet.viewmodel.ShowRentalViewModel;

public class ShowRentalView extends OwletView
{
    /**
     * 
     */
    private static final long   serialVersionUID = -424484658195002415L;

    private static final String KEY              = "SHOW_RENTAL";

    private ShowRentalViewModel _viewModel;
    private BoxPane             _userPane;
    private BoxPane             _exemplarPane;
    private BoxPane             _historyPane;
    private BoxPane             _extensionPane;

    public ShowRentalView()
    {
        _viewModel = new ShowRentalViewModel();
    }

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);
        try
        {
            _viewModel.initialize();
        }
        catch (Exception e)
        {
            Prompt.prompt(e.getMessage(), null);
            setEnabled(false);
        }

        _exemplarPane = (BoxPane) ns.get("exemplarPane");
        _userPane = (BoxPane) ns.get("userPane");
        _historyPane = (BoxPane) ns.get("historyPane");
        _extensionPane = (BoxPane) ns.get("extensionPane");

    }
}
