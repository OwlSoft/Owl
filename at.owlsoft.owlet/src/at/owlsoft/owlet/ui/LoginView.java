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
    private String     userName          = null;
    private String     userPassword      = null;
    private PushButton loginButton       = null;

    private TextInput  UsernameTextInput = null;

    @Override
    public void initialize(Map<String, Object> ns, URL arg1, Resources arg2)
    {
        setEnabled(true);
        UsernameTextInput = (TextInput) ns.get("UsernameTextInput");
        loginButton = (PushButton) ns.get("loginButton");
        loginButton.getButtonPressListeners().add(new ButtonPressListener()
        {

            @Override
            public void buttonPressed(Button source)
            {

            }
        });
    }

}
