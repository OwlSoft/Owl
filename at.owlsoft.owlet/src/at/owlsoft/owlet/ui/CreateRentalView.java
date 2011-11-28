package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

import at.owlsoft.owlet.viewmodel.CreateRentalViewModel;

public class CreateRentalView extends OwletView
{
    private CreateRentalViewModel _viewModel;

    private Label                 _customerNameLabel;
    private Label                 _exemplarTitleLabel;
    private Label                 _cardIDLabel;
    private Label                 _exemplarIDLabel;

    private TableView             _errorView;
    private TableView             _warningView;

    public CreateRentalView()
    {
        _viewModel = new CreateRentalViewModel();
    }

    @Override
    protected void onViewOpened()
    {
        setEnabled(true);
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

    @Override
    public void initialize(Map<String, Object> ns, URL url, Resources resources)
    {
        final TablePane infoPane = (TablePane) ns.get("infoPane");

        _customerNameLabel = (Label) ns.get("customerNameLabel");
        _exemplarTitleLabel = (Label) ns.get("exemplarTitleLabel");
        _cardIDLabel = (Label) ns.get("cardIDLabel");
        _exemplarIDLabel = (Label) ns.get("exemplarIDLabel");
        _errorView = (TableView) ns.get("errorView");
        _warningView = (TableView) ns.get("warningView");

        final TextInput cardIDInput = (TextInput) ns.get("cardID");
        final TextInput exemplarIDInput = (TextInput) ns.get("exemplarID");

        PushButton loadCustomer = (PushButton) ns.get("loadCustomer");
        loadCustomer.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button b)
            {
                int cardId;

                try
                {
                    cardId = Integer.parseInt(cardIDInput.getText());
                }
                catch (Exception e)
                {
                    Prompt.prompt("Invalid cardID format", getWindow());
                    return;
                }

                _viewModel.loadCustomer(cardId);
                updateUI();
            }
        });
        PushButton loadExemplar = (PushButton) ns.get("loadExemplar");
        loadExemplar.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button b)
            {
                int exemplarId;

                try
                {
                    exemplarId = Integer.parseInt(exemplarIDInput.getText());
                }
                catch (Exception e)
                {
                    Prompt.prompt("Invalid exemplarID format", getWindow());
                    return;
                }

                _viewModel.loadExemplar(exemplarId);
                infoPane.load(_viewModel);
                updateUI();
            }
        });
    }

    private void updateUI()
    {
        if (_viewModel.getCustomer() == null)
        {
            _customerNameLabel.setText("No user loaded");
        }
        else
        {
            _customerNameLabel.setText(String.format("%s %s", _viewModel
                    .getCustomer().getFirstName(), _viewModel.getCustomer()
                    .getLastName()));
            _cardIDLabel.setText(String.format("%s", _viewModel.getCustomer()
                    .getCardID()));
        }

        if (_viewModel.getExemplar() == null)
        {
            _exemplarIDLabel.setText("No exemplar loaded");
        }
        else
        {
            _exemplarTitleLabel.setText(_viewModel.getExemplar().getMedium()
                    .getName());
            _exemplarIDLabel.setText(String.format("%s", _viewModel
                    .getExemplar().getExemplarID()));
        }

        _exemplarIDLabel.setText("");

        _errorView.setTableData(_viewModel.getErrorMessages());
        _warningView.setTableData(_viewModel.getWarningMessages());

    }
}