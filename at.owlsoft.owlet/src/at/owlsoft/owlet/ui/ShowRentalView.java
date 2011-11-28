package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.owlsoft.owl.model.accounting.IFilingExtension;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owlet.util.PivotUtils;
import at.owlsoft.owlet.viewmodel.ShowRentalViewModel;

public class ShowRentalView extends OwletView
{
    private ShowRentalViewModel _viewModel;
    private BoxPane             _userPane;
    private BoxPane             _exemplarPane;
    private BoxPane             _historyPane;
    private BoxPane             _extensionPane;
    private Label               _userFirstName;
    private Label               _userLastName;
    private Button              _loadDefaultUserButton;
    private Label               _exemplarID;
    private Label               _exemplarName;
    private Label               _extensionCount;
    private ListButton          _allRentalsListButton;
    private TableView           _extensionDataTableView;
    private TableView           _historyDataTableView;
    private static Logger       LOGGER = Logger.getLogger(ShowRentalView.class);

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
        _exemplarID = (Label) ns.get("exemplarID");
        _exemplarName = (Label) ns.get("exemplarName");
        _extensionCount = (Label) ns.get("extensionCount");
        _allRentalsListButton = (ListButton) ns.get("allRentalsListButton");
        _extensionDataTableView = (TableView) ns.get("extensionDataTableView");
        _historyDataTableView = (TableView) ns.get("historyDataTableView");

        Button _createNewExtensionButton = (Button) ns
                .get("createNewExtensionButton");

        _createNewExtensionButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        if (_viewModel.getActiveRental() != null)
                        {
                            _viewModel.createNewExtension(_viewModel
                                    .getActiveRental());
                            refresh();
                        }
                    }
                });

        Button _returnRentalButton = (Button) ns.get("returnRentalButton");
        _returnRentalButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        _viewModel.returnRental(_viewModel.getActiveRental());
                        refresh();
                    }
                });

        _allRentalsListButton.getListButtonSelectionListeners().add(
                new ListButtonSelectionListener.Adapter()
                {

                    @Override
                    public void selectedItemChanged(ListButton arg0,
                            Object previousItem)
                    {
                        _viewModel.setActiveRental((IRental) arg0
                                .getSelectedItem());
                        setRentalData();

                    }
                });

        _loadDefaultUserButton = (Button) ns.get("loadDefaultUserButton");
        _loadDefaultUserButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        _viewModel.reloadSystemUser();
                        setUserData();
                    }
                });

        ((TextInput) ns.get("userId")).getTextInputContentListeners().add(
                new TextInputContentListener.Adapter()
                {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        Integer cardId = _viewModel.getUserCardId();
                        try
                        {
                            cardId = Integer.parseInt(textInput.getText());
                        }
                        catch (Exception e)
                        {
                            textInput.setText(cardId.toString());
                        }

                        _viewModel.setUserCardId(cardId);
                    }

                });

        _allRentalsListButton
                .setDataRenderer(RentalFieldDefinitionDataRenderer.INSTANCE);
        _allRentalsListButton
                .setItemRenderer(RentalFieldDefinitionItemRenderer.INSTANCE);

    }

    private void refresh()
    {
        setUserData();
        setRentalData();

    }

    private void setUserData()
    {
        _userFirstName.setText(_viewModel.getSystemUser().getFirstName());
        _userLastName.setText(_viewModel.getSystemUser().getLastName());

        IRental active = _viewModel.getActiveRental();
        _allRentalsListButton.setListData(_viewModel.getRentals());
        if (_viewModel.getActiveRental() != null)
        {
            _allRentalsListButton.setSelectedItem(active);
        }
        _viewModel.setActiveRental(active);

    }

    private void setRentalData()
    {
        if (_viewModel.getActiveRental() == null)
        {
            return;
        }

        List<IFilingExtension> extensions = (List<IFilingExtension>) PivotUtils
                .toPivotList(_viewModel.getActiveRental().getFilingExtensions());

        List<IFilingExtension> historyData = (List<IFilingExtension>) PivotUtils
                .toPivotList(_viewModel.getActiveRental()
                        .getActivityStatusEntries());

        LOGGER.debug("History Datensätze:" + historyData.getLength());

        _extensionDataTableView.setTableData(extensions);
        _historyDataTableView.setTableData(historyData);

        _exemplarID.setText(new Integer(_viewModel.getActiveRental()
                .getMediumExemplar().getExemplarID()).toString());

        _exemplarName.setText(_viewModel.getActiveRental().getMediumExemplar()
                .getMedium().getName());

        _extensionCount.setText(new Integer(_viewModel.getActiveRental()
                .getFilingExtensionCount()).toString());

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
