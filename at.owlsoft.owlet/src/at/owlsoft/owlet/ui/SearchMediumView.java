package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;

import at.owlsoft.owlet.viewmodel.SearchMediumViewModel;

public class SearchMediumView extends OwletView
{
    private static final String   KEY              = "SEARCH_MEDIUM";
    private static final long     serialVersionUID = 980097204234955190L;

    private SearchMediumViewModel _viewModel;
    private BoxPane               _searchFieldPane;

    public SearchMediumView()
    {
        _viewModel = new SearchMediumViewModel();
    }

    @Override
    public void initialize(Map<String, Object> ns, URL location,
            Resources resources)
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

        _searchFieldPane = (BoxPane) ns.get("searchFieldPane");
        ButtonPressListener addSearchFieldListener = new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                addNewSearchField();
            }
        };

        PushButton addSearchFieldButton = (PushButton) ns
                .get("addSearchFieldButton");
        addSearchFieldButton.getButtonPressListeners().add(
                addSearchFieldListener);
    }

    private void addNewSearchField()
    {
        SearchFieldContainer container = new SearchFieldContainer(_viewModel);
        _searchFieldPane.add(container);
    }
}
