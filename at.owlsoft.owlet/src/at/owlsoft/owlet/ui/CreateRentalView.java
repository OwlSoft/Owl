package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;

import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owlet.util.PivotUtils;
import at.owlsoft.owlet.viewmodel.CreateRentalViewModel;

public class CreateRentalView extends OwletRoleView
{
    private CreateRentalViewModel _viewModel;

    private Label                 _customerNameLabel;
    private Label                 _exemplarTitleLabel;
    private Label                 _cardIDLabel;
    private Label                 _exemplarIDLabel;
    private Label                 _customerStateLabel;
    private Label                 _exemplarStateLabel;

    private TableView             _errorView;
    private TableView             _warningView;

    private CalendarButton        _startDate;
    private Label                 _endDate;
    private Label                 _duration;

    private PushButton            _store;

    public CreateRentalView()
    {
        super(IDefaultRoles.RENTAL_CREATE);
        _viewModel = new CreateRentalViewModel();
    }

    @Override
    protected void onViewOpened()
    {
        setEnabled(true);
        try
        {
            _viewModel.initialize();
            updateUI();
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
        _customerStateLabel = (Label) ns.get("customerStateLabel");
        _exemplarStateLabel = (Label) ns.get("exemplarStateLabel");
        _store = (PushButton) ns.get("store");

        _store.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button b)
            {
                try
                {
                    if (_viewModel.store())
                    {
                        Prompt.prompt(
                                "Medium Copy was successfully rented, thanks for using Owl!",
                                getWindow());
                        _viewModel.initialize();
                    }
                    else
                    {
                        Prompt.prompt("Error saving rental!", getWindow());
                    }
                }
                catch (Exception e)
                {
                    Prompt.prompt("Error on saving rental: " + e.getMessage(),
                            getWindow());
                }

                updateUI(false);
            }
        });

        _endDate = (Label) ns.get("endDate");
        _startDate = (CalendarButton) ns.get("startDate");
        _startDate.getCalendarButtonSelectionListeners().add(
                new CalendarButtonSelectionListener()
                {

                    @Override
                    public void selectedDateChanged(CalendarButton calendar,
                            CalendarDate previousDate)
                    {
                        try
                        {
                            _viewModel.setStartDate(calendar.getSelectedDate()
                                    .toCalendar().getTime());
                        }
                        catch (Exception e)
                        {
                            Prompt.prompt(
                                    "Error on updating date range: "
                                            + e.getMessage(), getWindow());
                        }

                        updateUI(false);
                    }
                });
        _duration = (Label) ns.get("duration");

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
        updateUI(true);
    }

    private void updateUI(boolean updateCalendars)
    {
        if (_viewModel.getCustomer() == null)
        {
            _cardIDLabel.setText("No user loaded");
            _customerNameLabel.setText("");
            _customerStateLabel.setText("");
        }
        else
        {

            _customerNameLabel.setText(String.format("%s %s", _viewModel
                    .getCustomer().getFirstName(), _viewModel.getCustomer()
                    .getLastName()));
            _cardIDLabel.setText(String.format("%s", _viewModel.getCustomer()
                    .getCardID()));
            _customerStateLabel.setText(_viewModel.getCustomer()
                    .getCurrentStatus().toString());

        }

        if (_viewModel.getExemplar() == null)
        {
            _exemplarIDLabel.setText("No exemplar loaded");
            _exemplarTitleLabel.setText("");
            _exemplarStateLabel.setText("");
        }
        else
        {
            _exemplarTitleLabel.setText(_viewModel.getExemplar().getMedium()
                    .getName());
            _exemplarIDLabel.setText(String.format("%s", _viewModel
                    .getExemplar().getExemplarID()));
            _exemplarStateLabel.setText(_viewModel.getExemplar()
                    .getCurrentState().toString());
        }

        if (updateCalendars)
        {
            _startDate.setSelectedDate(PivotUtils.toCalendarDate(_viewModel
                    .getRental().getStartDate()));
        }
        _endDate.setText(PivotUtils.formatDate(_viewModel.getRental()
                .getEndDate()));

        _duration.setText(durationString(PivotUtils.daysBetween(_viewModel
                .getRental().getStartDate(), _viewModel.getRental()
                .getEndDate())));

        _errorView.setTableData(_viewModel.getErrorMessages());
        _warningView.setTableData(_viewModel.getWarningMessages());

    }

    private String durationString(long duration)
    {
        return String.format("Duration: %d Days", duration);
    }
}