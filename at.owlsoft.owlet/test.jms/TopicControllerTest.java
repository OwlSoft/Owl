import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import at.owlsoft.owlet.controller.TopicController;
import at.owlsoft.owlet.jsm.JmsUtils;
import at.owlsoft.owlet.jsm.JmsUtils.JmsInfo;

public class TopicControllerTest
{
    private int _messages = 0;

    @Test
    public void test()
    {
        _messages = 0;

        MessageListener listener1 = new MessageListener()
        {

            @Override
            public void onMessage(Message arg0)
            {
                if (arg0 instanceof TextMessage)
                {
                    try
                    {
                        System.out.println("hook 1 received "
                                + ((TextMessage) arg0).getText());

                        _messages++;
                    }
                    catch (JMSException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Assert.fail();
                    }
                }

            }
        };

        MessageListener listener2 = new MessageListener()
        {

            @Override
            public void onMessage(Message arg0)
            {
                if (arg0 instanceof TextMessage)
                {
                    try
                    {
                        System.out.println("hook 2 received "
                                + ((TextMessage) arg0).getText());
                        _messages++;
                    }
                    catch (JMSException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Assert.fail();
                    }
                }

            }
        };

        String topicName = "Topic";
        TopicController controller = new TopicController(topicName, "Manuel");
        controller.addMessageListener(listener1);
        controller.addMessageListener(listener2);
        controller.start();

        JmsInfo info = null;
        try
        {
            info = JmsUtils.GetTopic(topicName, "Producer");
            TopicPublisher publisher = info.createPublisher();

            String messageText;
            boolean quit;

            publisher.publish(info.getSession().createTextMessage("message 1"));
            publisher.publish(info.getSession().createTextMessage("message 2"));
            publisher.publish(info.getSession().createTextMessage("message 3"));

            controller.close();
            System.out.println("client logged out");

            publisher.publish(info.getSession().createTextMessage("message 4"));
            publisher.publish(info.getSession().createTextMessage("message 5"));
            publisher.publish(info.getSession().createTextMessage("message 6"));
            publisher.publish(info.getSession().createTextMessage("message 7"));

            System.out.println("should receive old messages");

            controller = new TopicController(topicName, "Manuel");
            controller.addMessageListener(listener1);
            controller.addMessageListener(listener2);
            controller.start();

            int miliSeconds = 2000;

            while (miliSeconds > 0)
            {
                try
                {
                    Thread.sleep(100);
                    miliSeconds -= 100;
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            Assert.assertTrue(_messages == 2 * 7);

            publisher.close();
            info.close();

        }
        catch (JMSException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (NamingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        controller.close();

    }
}
