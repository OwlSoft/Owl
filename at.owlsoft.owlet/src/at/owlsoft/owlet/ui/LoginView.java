package at.owlsoft.owlet.ui;

import java.net.URL;

import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;

public class LoginView extends OwletView
{
    private String     _userName          = null;
    private String     _userPassword      = null;
    private PushButton _loginButton       = null;

    private TextInput  _userNameTextInput = null;

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);
        _userNameTextInput = (TextInput) ns.get("UsernameTextInput");
        _loginButton = (PushButton) ns.get("loginButton");
        _loginButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button source)
            {

            }
        });
    }

}
