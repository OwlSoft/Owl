package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.Window;

import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.messaging.IMessage;
import at.owlsoft.owl.model.messaging.MessageState;
import at.owlsoft.owlet.viewmodel.MessagingViewModel;

public class ShowMessagesView extends OwletRoleView
{
    private TableView  _messageView;
    private PushButton _updateStateButton;
    private PushButton _refreshButton;

    public ShowMessagesView()
    {
        super(IDefaultRoles.OPERATOR);
    }

    @Override
    public void initialize(Map<String, Object> ns, URL url, Resources res)
    {
        _messageView = (TableView) ns.get("messageView");
        _updateStateButton = (PushButton) ns.get("updateStateButton");
        _refreshButton = (PushButton) ns.get("refreshButton");

        _refreshButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button b)
            {
                try
                {
                    refresh();
                }
                catch (Exception e)
                {
                    Prompt.prompt(e.getMessage(), getWindow());
                }
            }
        });

        _updateStateButton.getButtonPressListeners().add(
                new ButtonPressListener()
                {

                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        updateMessageState();
                    }
                });

        _messageView.getTableViewSelectionListeners().add(
                new TableViewSelectionListener.Adapter()
                {
                    @Override
                    public void selectedRowChanged(TableView tableView,
                            Object previousSelectedRow)
                    {
                        updateButtonText();
                    }
                });
        updateButtonText();
    }

    protected void refresh() throws Exception
    {
        if (!MessagingViewModel.getInstance().refresh())
        {
            throw new Exception("Could not load messages!");
        }

        updateMessages();
    }

    protected void updateMessageState()
    {
        IMessage message = (IMessage) _messageView.getSelectedRow();
        if (message == null)
        {
            return;
        }

        MessageState newState = null;

        if (message.getState().equals(MessageState.Open))
        {
            newState = MessageState.Processing;
        }
        else if (message.getState().equals(MessageState.Processing))
        {
            newState = MessageState.Finished;
        }
        else
        {
            return;
        }

        try
        {
            MessagingViewModel.getInstance().markMessage(newState, message);
            refresh();
        }
        catch (Exception e)
        {
            Prompt.prompt(e.getMessage(), getWindow());
        }
    }

    protected void updateButtonText()
    {
        IMessage message = (IMessage) _messageView.getSelectedRow();
        if (message == null)
        {
            _updateStateButton.setEnabled(false);
            return;
        }

        _updateStateButton.setEnabled(true);
        if (message.getState().equals(MessageState.Open))
        {
            _updateStateButton
                    .setButtonData("Set message state to 'processing'");
        }
        else if (message.getState().equals(MessageState.Processing))
        {
            _updateStateButton.setButtonData("Set message state to 'finished'");
        }
        else
        {
            _updateStateButton.setButtonData("Message is finished");
            _updateStateButton.setEnabled(false);
        }
    }

    @Override
    protected boolean onViewOpening(MainWindow mainWindow)
    {
        updateMessages(mainWindow);
        return true;
    }

    private void updateMessages(Window mainWindow)
    {
        setEnabled(true);
        try
        {
            _messageView.setTableData(MessagingViewModel.getInstance()
                    .getOpenMessages());
        }
        catch (Exception e)
        {
            Prompt.prompt(e.getMessage(), getWindow());
            setEnabled(false);
        }

    }

    private void updateMessages()
    {
        updateMessages(getWindow());
    }

    @Override
    public String getTitle()
    {
        return "Show Messages";
    }

}
