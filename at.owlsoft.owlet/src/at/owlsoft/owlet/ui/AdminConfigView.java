package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

public class AdminConfigView extends OwletView
{
    private TextInput           _configSearchField;
    private PushButton          _addNewParamButton;
    private ListView            _configParams;
    private TextInput           _configParamValue;
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

        _configParams = (ListView) ns.get("configParams");
        _configParams.setListData(getVisibleParams());

        _configParamValue = (TextInput) ns.get("configParamValue");

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

        _configSearchField.getTextInputContentListeners().add(
                new TextInputContentListener.Adapter()
                {
                    @Override
                    public void textChanged(TextInput textInput)
                    {
                        String searchText = textInput.getText();
                        _configParams.clear();
                        _configParams.setListData(getVisibleParams(searchText));
                    }

                });
    }

    private void addParam(String param)
    {
        // Check if entry already exists
        if (!(_configParamsAll.containsKey(param) || _configParamsChangedNew
                .containsKey(param)))
        {
            // Add new entry to list of save
            _configParamsChangedNew.put(_configSearchField.getText(), "");
        }

        // Check if entry is on to-remove list; if so, remove it
        for (String str : _configParamsToRemove)
        {
            if (str.equalsIgnoreCase(param))
            {
                _configParamsToRemove.remove(str);
            }
        }

        _configParams.clear();
        _configParams.setListData(getVisibleParams());

    }

    private List<String> getVisibleParams()
    {
        return getVisibleParams("");
    }

    private List<String> getVisibleParams(String searchString)
    {
        // TODO check _configParamsChangedNew and _configParamsToRemove
        List<String> tempList = new ArrayList<String>();
        for (String str : _configParamsAll)
        {
            if (str.toLowerCase().contains(searchString.toLowerCase()))
            {
                System.out
                        .println("Found match at index "
                                + str.toLowerCase().indexOf(
                                        searchString.toLowerCase()));
                tempList.add(str);
            }

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
