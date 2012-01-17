package at.owlsoft.owl.jms;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;
import javax.naming.NamingException;

import at.owlsoft.owl.jms.JmsUtils.JmsInfo;

public class TopicProduceController
{
    private JmsInfo        _jmsInfo;
    private TopicPublisher _publisher;

    public TopicProduceController(String topicName) throws JMSException,
            NamingException
    {
        _jmsInfo = JmsUtils.GetTopic(topicName, null);
        _publisher = _jmsInfo.createPublisher();
    }

    public void sendText(String text) throws JMSException
    {
        TextMessage message = _jmsInfo.getSession().createTextMessage(text);
        _publisher.publish(message);
    }

    public void close()
    {
        _jmsInfo.close();
        try
        {
            _publisher.close();
        }
        catch (JMSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
