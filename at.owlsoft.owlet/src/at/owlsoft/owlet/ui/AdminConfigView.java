package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewSelectionListener;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owlet.util.PivotUtils;
import at.owlsoft.owlet.viewmodel.AdminConfigViewModel;

public class AdminConfigView extends OwletRoleView
{
    private AdminConfigViewModel _viewModel;

    private TextInput            _configSearchField;
    private PushButton           _addNewParamButton;
    private ListView             _configParamsDisplayed;
    private TextInput            _configParamValue;
    private PushButton           _removeButton;
    private PushButton           _saveButton;

    private Map<String, String>  _configParamsAll;
    private Map<String, String>  _configParamsChangedNew;
    private List<String>         _configParamsToRemove;
    private Logger               LOGGER = Logger.getLogger(AdminConfigView.class);

    public AdminConfigView()
    {
        super(IDefaultRoles.ADMIN_CONFIG);
        _viewModel = new AdminConfigViewModel();
    }

    @Override
    protected void onViewOpened()
    {
        setEnabled(true);
        try
        {
            _viewModel.initialize();
        }
        catch (NullPointerException e)
        {
            Prompt.prompt(e.getMessage(), getWindow());
        }
        catch (Exception e)
        {
            Prompt.prompt(e.getMessage(), getWindow());
            setEnabled(false);
        }
    }

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);

        try
        {
            _configParamsAll = PivotUtils.toPivotMap(_viewModel
                    .getAllProperties());
        }
        catch (NullPointerException e)
        {
            LOGGER.debug("No params from server");
            _configParamsAll = new HashMap<String, String>();
        }

        _configParamsChangedNew = new HashMap<String, String>();
        _configParamsToRemove = new ArrayList<String>();

        _configSearchField = (TextInput) ns.get("configSearchField");
        _configSearchField.getTextInputContentListeners().add(
                new TextInputContentListener.Adapter()
                {
                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        String searchText = textInput.getText();
                        _configParamsDisplayed.clear();
                        _configParamsDisplayed
                                .setListData(getVisibleParams(searchText));
                    }

                });

        _addNewParamButton = (PushButton) ns.get("addNewParamButton");
        _addNewParamButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {

                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        addParam(_configSearchField.getText());
                    }
                });

        _configParamsDisplayed = (ListView) ns.get("configParams");
        _configParamsDisplayed.setListData(getVisibleParams());
        _configParamsDisplayed.getListViewSelectionListeners().add(
                new ListViewSelectionListener.Adapter()
                {

                    @Override
                    public void selectedItemChanged(ListView listView,
                            Object arg1)
                    {
                        updateSelectionInfo(listView);
                    }
                });

        _configParamValue = (TextInput) ns.get("configParamValue");
        _configParamValue.setEnabled(true);
        _configParamValue.getTextInputContentListeners().add(
                new TextInputContentListener.Adapter()
                {
                    private boolean suspend = false;

                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        LOGGER.debug("textChanged");

                        if (suspend)
                        {
                            LOGGER.debug("returning");
                            return;
                        }

                        if (getSelectedParam() != null)
                        {
                            LOGGER.debug(textInput.getText());
                            updateParamValue(textInput.getText());

                        }
                        else
                        {
                            // fixing stack overflow
                            LOGGER.debug("setting text to empty string");
                            suspend = true;
                            textInput.setText("");
                            suspend = false;
                        }
                    }
                });

        _removeButton = (PushButton) ns.get("removeButton");
        _removeButton.setEnabled(false);
        _removeButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button arg0)
            {
                String param = getSelectedParam();

                removeParam(param);

                _configParamsDisplayed.clear();
                _configParamsDisplayed.setListData(getVisibleParams());
            }

        });

        _saveButton = (PushButton) ns.get("saveButton");
        _saveButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button arg0)
            {
                saveChanges();
            }
        });

    }

    protected void removeParam(String param)
    {
        if (_configParamsChangedNew.containsKey(param))
        {
            // If the param is in the changed-or-new list, remove it
            // from there
            _configParamsChangedNew.remove(param);
        }

        /*
         * if (_configParamsAll.containsKey(param)) { // If the param was in our
         * param source list, set it to the // remove list
         * _configParamsAll.remove(param); }
         */

        _configParamsToRemove.add(param);
    }

    private String getSelectedParam()
    {
        int itemSelected = _configParamsDisplayed.getSelectedIndex();
        @SuppressWarnings("unchecked")
        List<String> displayedItems = (List<String>) _configParamsDisplayed
                .getListData();

        if (itemSelected >= 0)
        {
            String param = displayedItems.get(itemSelected);
            return param;
        }
        return null;
    }

    private void updateSelectionInfo(ListView listView)
    {
        Sequence<Span> selectedRanges = listView.getSelectedRanges();
        if (selectedRanges.getLength() > 0)
        {

            String selectedParam = getSelectedParam();

            String value = "";

            if (_configParamsAll.containsKey(selectedParam))
            {
                boolean isRemoved = false;
                for (String str : _configParamsToRemove)
                {
                    if (selectedParam.equalsIgnoreCase(str))
                    {
                        isRemoved = true;
                    }
                }
                if (!isRemoved)
                {
                    value = _configParamsAll.get(selectedParam);
                }
            }

            if (_configParamsChangedNew.containsKey(selectedParam))
            {
                value = _configParamsChangedNew.get(selectedParam);
            }

            updateParamValue(value);
            _removeButton.setEnabled(true);
        }
        else
        {
            _removeButton.setEnabled(false);
        }
    }

    private void updateParamValue(String newValue)
    {
        String selectedParam = getSelectedParam();

        if (_configParamsChangedNew.containsKey(selectedParam))
        {
            _configParamsChangedNew.remove(selectedParam);
        }
        _configParamsChangedNew.put(selectedParam, newValue);

        _configParamValue.setEnabled(true);

        // Remove listeners so we don't go into an endless loop
        List<TextInputContentListener> listeners = new ArrayList<TextInputContentListener>();

        for (TextInputContentListener textInputContentListener : _configParamValue
                .getTextInputContentListeners())
        {
            listeners.add(textInputContentListener);
        }

        for (TextInputContentListener listener : listeners)
        {
            _configParamValue.getTextInputContentListeners().remove(listener);

        }

        _configParamValue.invalidate();
        _configParamValue.setText(newValue);
        _configParamValue.invalidate();
        _configParamValue.repaint();

        // Add listeners back
        for (TextInputContentListener listener : listeners)
        {
            _configParamValue.getTextInputContentListeners().add(listener);

        }
    }

    private void addParam(String param)
    {
        boolean entryExists = false;
        // Check if entry already exists
        if (_configParamsAll.containsKey(param)
                || _configParamsChangedNew.containsKey(param))
        {
            entryExists = true;
        }

        if (!entryExists)
        {
            _configParamsChangedNew.put(_configSearchField.getText(), "");
        }

        boolean removeEntry = false;

        // Check if entry is on to-remove list; if so, remove it
        for (String str : _configParamsToRemove)
        {
            if (str.equalsIgnoreCase(param))
            {
                removeEntry = true;
            }
        }

        if (removeEntry)
        {
            _configParamsToRemove.remove(param);
        }

        _configParamsDisplayed.clear();
        _configParamsDisplayed.setListData(getVisibleParams());

    }

    private List<String> getVisibleParams()
    {
        return getVisibleParams("");
    }

    private List<String> getVisibleParams(String searchString)
    {
        ArrayList<String> tempList = new ArrayList<String>();
        for (String str : _configParamsAll)
        {
            // If the current item fits the search string
            if (searchString.equals("")
                    || str.toLowerCase().contains(searchString.toLowerCase()))
            {
                // Check if not on remove list
                if (_configParamsToRemove.getLength() > 0)
                {
                    boolean removeParam = false;
                    for (String rmStr : _configParamsToRemove)
                    {
                        if (str.equalsIgnoreCase(rmStr))
                        {
                            removeParam = true;
                        }
                    }
                    if (!removeParam)
                    {
                        tempList.add(str);
                    }
                }
                else
                {
                    tempList.add(str);
                }
            }
        }

        // Temporary list for saving entries to the other temporary list
        List<String> tempTempList = new ArrayList<String>();

        // Add params from changed-or-new list
        for (String str : _configParamsChangedNew)
        {
            if (searchString.equals("")
                    || str.toLowerCase().contains(searchString.toLowerCase()))
            {
                boolean entryExists = false;

                // Check if param is already in list
                for (String str2 : tempList)
                {
                    if (str.equalsIgnoreCase(str2))
                    {
                        entryExists = true;
                    }
                }

                if (!entryExists)
                {
                    tempTempList.add(str);
                }
            }
        }

        for (String str : tempTempList)
        {
            tempList.add(str);
        }

        org.apache.pivot.collections.ArrayList.sort(tempList);

        return tempList;
    }

    private void saveChanges()
    {
        boolean changesMade = false;

        if (_configParamsChangedNew != null
                && _configParamsChangedNew.getCount() > 0)
        {
            _viewModel.setAll(PivotUtils.toJavaMap(_configParamsChangedNew));
            changesMade = true;
        }

        if (_configParamsToRemove != null
                && _configParamsToRemove.getLength() > 0)
        {
            _viewModel.removeProperties(PivotUtils
                    .toJavaList(_configParamsToRemove));
            changesMade = true;
        }

        _viewModel.store();

        if (changesMade)
        {
            Prompt.prompt("Booya, bitch", getWindow());
        }
        else
        {
            Prompt.prompt("No changes were made.", getWindow());
        }
    }

}
