package at.owlsoft.owlet.ui;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;

import at.owlsoft.owl.model.ISearchField;
import at.owlsoft.owl.model.ISearchFieldDefinition;
import at.owlsoft.owlet.viewmodel.SearchMediumViewModel;

public class SearchFieldContainer extends BoxPane
{
    private static final Logger   logger = Logger.getLogger(SearchFieldContainer.class);

    private ListButton            _searchFieldKey;
    private TextInput             _searchFieldValue;
    private SearchMediumViewModel _viewModel;

    private ISearchField          _searchField;

    public SearchFieldContainer(SearchMediumViewModel viewModel)
    {
        _viewModel = viewModel;

        _searchField = _viewModel.addNewSearchField();

        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);
        getStyles().put("spacing", 5);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            Border content = (Border) serializer.readObject(
                    SearchFieldContainer.class, "SearchFieldContainerContent"
                            + ViewController.PIVOT_FILE_EXTENSION);
            add(content);
            _searchFieldKey = (ListButton) serializer.getNamespace().get(
                    "searchFieldKey");
            _searchFieldValue = (TextInput) serializer.getNamespace().get(
                    "searchFieldValue");

            PushButton deleteSearchField = (PushButton) serializer
                    .getNamespace().get("deleteSearchField");
            deleteSearchField.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {
                        @Override
                        public void buttonPressed(Button arg0)
                        {
                            removeSearchField();
                        }

                    });
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }

    private void removeSearchField()
    {
        _viewModel.removeSearchField(_searchField);
        getParent().remove(this);
    }

    public void updateViewModel()
    {
        _searchField = _viewModel.setSearchFieldData(_searchField,
                ((ISearchFieldDefinition) _searchFieldKey.getSelectedItem()),
                _searchFieldValue.getText());
    }
}
