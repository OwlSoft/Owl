package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
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
    private Label               _userFirstName;
    private Label               _userLastName;
    private Button              _loadDefaultUserButton;

    public ShowRentalView()
    {
        _viewModel = new ShowRentalViewModel();
    }

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);

        _exemplarPane = (BoxPane) ns.get("exemplarPane");
        _userPane = (BoxPane) ns.get("userPane");
        _historyPane = (BoxPane) ns.get("historyPane");
        _extensionPane = (BoxPane) ns.get("extensionPane");
        _userFirstName = (Label) ns.get("userFirstName");
        _userLastName = (Label) ns.get("userLastName");
        _loadDefaultUserButton = (Button) ns.get("loadDefaultUserButton");
        _loadDefaultUserButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        _userFirstName.setText(_viewModel.getSystemUser()
                                .getFirstName());
                        _userLastName.setText(_viewModel.getSystemUser()
                                .getLastName());
                    }
                });

    }

    @Override
    protected void onViewOpened()
    {
        try
        {
            _viewModel.initialize();
        }
        catch (Exception e)
        {
            Prompt.prompt(e.getMessage(), getWindow());
            setEnabled(false);
        }

    }
}
