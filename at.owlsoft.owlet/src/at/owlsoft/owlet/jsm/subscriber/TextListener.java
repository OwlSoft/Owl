package at.owlsoft.owlet.jsm.subscriber;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextListener implements MessageListener
{
    @Override
    public void onMessage(Message message)
    {
        TextMessage msg = null;

        try
        {
            if (message instanceof TextMessage)
            {
                msg = (TextMessage) message;
                System.out.println("Message received: " + msg.getText());
            }
            else
            {
                System.out.println("Message of wrong type: "
                        + message.getClass().getName());
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading message");
            e.printStackTrace();
        }
    }

}
