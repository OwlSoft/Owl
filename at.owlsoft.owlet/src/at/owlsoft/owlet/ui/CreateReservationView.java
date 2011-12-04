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
import at.owlsoft.owlet.viewmodel.CreateReservationViewModel;

public class CreateReservationView extends OwletRoleView
{
    private CreateReservationViewModel _viewModel;

    private Label                      _customerNameLabel;
    private Label                      _mediumTitleLabel;
    private Label                      _cardIDLabel;
    private Label                      _mediumIDLabel;
    private Label                      _customerStateLabel;

    private TableView                  _errorView;
    private TableView                  _warningView;

    private CalendarButton             _startDate;

    private PushButton                 _store;

    private TextInput                  _desiredDuration;

    public CreateReservationView()
    {
        super(IDefaultRoles.RESERVATION_CREATE);
        _viewModel = new CreateReservationViewModel();
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
        _mediumTitleLabel = (Label) ns.get("mediumTitleLabel");
        _cardIDLabel = (Label) ns.get("cardIDLabel");
        _mediumIDLabel = (Label) ns.get("mediumIDLabel");
        _errorView = (TableView) ns.get("errorView");
        _warningView = (TableView) ns.get("warningView");
        _customerStateLabel = (Label) ns.get("customerStateLabel");
        _store = (PushButton) ns.get("store");
        _desiredDuration = (TextInput) ns.get("desiredDuration");
        _desiredDuration.setText("10");

        _store.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button b)
            {
                try
                {
                    int duration;
                    try
                    {
                        duration = Integer.parseInt(_desiredDuration.getText());
                    }
                    catch (Exception e)
                    {
                        Prompt.prompt("Invalid duration!", getWindow());
                        return;
                    }

                    _viewModel.setDuration(duration);
                    if (_viewModel.store())
                    {
                        Prompt.prompt(
                                "Medium was successfully reserved, thanks for using Owl!",
                                getWindow());
                        _viewModel.initialize();
                    }
                    else
                    {
                        Prompt.prompt("Error saving reservation!", getWindow());
                    }
                }
                catch (Exception e)
                {
                    Prompt.prompt(
                            "Error on saving reservation: " + e.getMessage(),
                            getWindow());
                }

                updateUI(false);
            }
        });

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

        final TextInput cardIDInput = (TextInput) ns.get("cardID");
        final TextInput mediumIDInput = (TextInput) ns.get("mediumID");

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
        PushButton loadMedium = (PushButton) ns.get("loadMedium");
        loadMedium.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button b)
            {
                int mediumId;

                try
                {
                    mediumId = Integer.parseInt(mediumIDInput.getText());
                }
                catch (Exception e)
                {
                    Prompt.prompt("Invalid mediumID format", getWindow());
                    return;
                }

                _viewModel.loadMedium(mediumId);
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

        if (_viewModel.getReservation().getMedium() == null)
        {
            _mediumIDLabel.setText("No medium loaded");
            _mediumTitleLabel.setText("");
        }
        else
        {
            _mediumTitleLabel.setText(_viewModel.getReservation().getMedium()
                    .getName());
            _mediumIDLabel.setText(String.format("%s", _viewModel
                    .getReservation().getMedium().getMediumID()));
        }

        if (updateCalendars)
        {
            _startDate.setSelectedDate(PivotUtils.toCalendarDate(_viewModel
                    .getReservation().getStartDate()));
        }

        _errorView.setTableData(_viewModel.getErrorMessages());
        _warningView.setTableData(_viewModel.getWarningMessages());

    }
}
