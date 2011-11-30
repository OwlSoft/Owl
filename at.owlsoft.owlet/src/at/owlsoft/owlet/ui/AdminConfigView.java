package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.ListenerList;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewSelectionListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

public class AdminConfigView extends OwletView
{
    private TextInput           _configSearchField;
    private PushButton          _addNewParamButton;
    private ListView            _configParamsDisplayed;
    private TextInput           _configParamValue;
    private PushButton          _removeButton;
    private PushButton          _saveButton;

    private Map<String, String> _configParamsAll;
    private Map<String, String> _configParamsChangedNew;
    private List<String>        _configParamsToRemove;

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);

        // TODO Fetch from Controller
        _configParamsAll = new HashMap<String, String>();
        _configParamsAll.put("Nahrungsmittel.Obst.Banane", "12");
        _configParamsAll.put("Nahrungsmittel.Obst.Apfel", "13");
        _configParamsAll.put("Nahrungsmittel.Obst.Orange", "14");
        _configParamsAll.put("Nahrungsmittel.Obst.Apfelsine", "15");
        _configParamsAll.put("Nahrungsmittel.Gemüse.Selerie", "16");
        _configParamsAll.put("Nahrungsmittel.Gemüse.Salat", "17");
        _configParamsAll.put("Nahrungsmittel.Gemüse.Tomate", "18");
        _configParamsAll.put("Nahrungsmittel.Süßes.Schoki", "19");
        _configParamsAll.put("Nahrungsmittel.Süßes.Zimststerne", "20");
        _configParamsAll.put("Nahrungsmittel.Süßes.Nutella", "21");

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
        _configParamValue.setEnabled(false);
        _configParamValue.getTextInputContentListeners().add(
                new TextInputContentListener.Adapter()
                {

                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        updateParamValue(textInput.getText());
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

                if (_configParamsChangedNew.containsKey(param))
                {
                    // If the param is in the changed-or-new list, remove it
                    // from there
                    _configParamsAll.remove(param);
                }

                if (_configParamsAll.containsKey(param))
                {
                    // If the param was in our param source list, set it to the
                    // remove list
                    _configParamsToRemove.add(param);
                }

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
                // TODO continue
                saveChanges();
            }
        });

    }

    private String getSelectedParam()
    {
        int itemSelected = _configParamsDisplayed.getSelectedIndex();
        List<String> displayedItems = (List<String>) _configParamsDisplayed
                .getListData();
        String param = displayedItems.get(itemSelected);
        return param;
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
        ListenerList<TextInputContentListener> listeners = _configParamValue
                .getTextInputContentListeners();
        for (TextInputContentListener listener : listeners)
        {
            _configParamValue.getTextInputContentListeners().remove(listener);

        }

        _configParamValue.invalidate();
        _configParamValue.setText(newValue);
        _configParamValue.invalidate();

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
        List<String> tempList = new ArrayList<String>();
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

        return tempList;
    }

    private void saveChanges()
    {
        // TODO Auto-generated method stub
        if (validateParams())
        {

        }
    }

    private boolean validateParams()
    {
        // TODO implement validation
        return false;
    }

}
