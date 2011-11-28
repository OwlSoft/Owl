package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TableView;

import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owlet.util.PivotUtils;

public class SearchResultPanel extends Border implements Bindable
{
    private Label     _mediumTitle;
    private Label     _copyCount;
    private Label     _publisher;
    private TableView _copiesView;

    @Override
    public void initialize(Map<String, Object> ns, URL url, Resources resources)
    {
        _mediumTitle = (Label) ns.get("mediumTitle");
        _copyCount = (Label) ns.get("copyCount");
        _publisher = (Label) ns.get("publisher");
        _copiesView = (TableView) ns.get("copiesView");
    }

    public void updatePane(IMedium medium)
    {
        _mediumTitle.setText(medium.getName());
        _copyCount.setText(new Integer(medium.getMediumExemplarCount())
                .toString());
        _publisher.setText(medium.getPublisher());
        _copiesView.setTableData(PivotUtils.toPivotList(medium
                .getMediumExemplars()));
    }
}
