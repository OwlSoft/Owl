package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;

import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owlet.viewmodel.SearchMediumViewModel;

public class SearchMediumView extends OwletView
{
    private static final Logger   logger = Logger.getLogger(SearchMediumView.class);

    private SearchMediumViewModel _viewModel;
    private BoxPane               _searchFieldPane;
    private BoxPane               _resultView;

    public SearchMediumView()
    {
        _viewModel = new SearchMediumViewModel();
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
    public void initialize(Map<String, Object> ns, URL location,
            Resources resources)
    {
        _resultView = (BoxPane) ns.get("resultView");

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

        PushButton doSearchButton = (PushButton) ns.get("doSearchButton");
        doSearchButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button b)
            {
                for (Component panel : _searchFieldPane)
                {
                    SearchFieldContainer container = (SearchFieldContainer) panel;
                    container.updateServerModel();
                }
                _viewModel.doSearch();

                _resultView.clear();
                for (IMedium result : _viewModel.getSearchResults())
                {
                    createPanelForResult(result);
                }
            }
        });
    }

    private void addNewSearchField()
    {
        SearchFieldContainer container = new SearchFieldContainer(_viewModel);
        _searchFieldPane.add(container);
    }

    private void createPanelForResult(IMedium result)
    {
        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            SearchResultPanel content = (SearchResultPanel) serializer
                    .readObject(SearchFieldContainer.class, "SearchResultPanel"
                            + ViewController.PIVOT_FILE_EXTENSION);
            content.updatePane(result);
            _resultView.add(content);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
