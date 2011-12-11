package at.owlsoft.owlet.controller;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TopicSubscriber;
import javax.naming.NamingException;

import at.owlsoft.owlet.jsm.JmsUtils;
import at.owlsoft.owlet.jsm.JmsUtils.JmsInfo;

public class TopicController
{

    private JmsInfo                    _jmsInfo;
    private static String              _topicDefaultName = "InfoTopic";
    private TopicSubscriber            _subscriber;
    private ArrayList<MessageListener> _messageListeners;

    public TopicController(String topicName, String clientId)
    {
        try
        {
            _jmsInfo = JmsUtils.GetTopic(topicName, clientId);
            _subscriber = _jmsInfo.createDurableSubscriber();
            _messageListeners = new ArrayList<MessageListener>();
            setObserverTopicListener();
        }
        catch (NamingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            _jmsInfo.close();
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            _jmsInfo.close();
        }
    }

    private void setObserverTopicListener()
    {
        try
        {
            _subscriber.setMessageListener(new MessageListener()
            {
                @Override
                public void onMessage(Message arg0)
                {
                    for (MessageListener listener : _messageListeners)
                    {
                        listener.onMessage(arg0);
                    }
                }
            });
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addMessageListener(MessageListener listener)
    {
        _messageListeners.add(listener);
    }

    public TopicController(String clientId)
    {
        this(_topicDefaultName, clientId);
    }

    public void start()
    {
        try
        {
            _jmsInfo.getConnection().start();
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            _jmsInfo.close();
        }
    }

    public void close()
    {

        try
        {
            _jmsInfo.getConnection().stop();
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _jmsInfo.close();
    }
}
