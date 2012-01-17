package at.owlsoft.owlet.jsm;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsUtils
{
    /**
     * Loads the topic with the defined client ID, for loading old messages
     * 
     * @param topicName
     * @param clientId
     * @return
     * @throws NamingException
     * @throws JMSException
     */
    public static JmsInfo GetTopic(String topicName, String clientId)
            throws NamingException, JMSException
    {
        InitialContext context;

        TopicConnectionFactory connectionFactory;
        TopicSession session;
        TopicConnection connection;
        Topic topic;

        // Initialize Connection
        context = new InitialContext();
        // get factory from application server
        connectionFactory = (TopicConnectionFactory) context
                .lookup("TopicConnectionFactory");

        // get topic connection and session
        connection = connectionFactory.createTopicConnection();
        if (clientId != null)
        {
            connection.setClientID(UUID.randomUUID().toString());
        }
        session = connection
                .createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // lookup topic and return info
        topic = (Topic) context.lookup(topicName);

        return new JmsInfo(connection, clientId, topic, session);
    }

    public static class JmsInfo
    {
        private TopicConnection _connection;
        private Topic           _topic;
        private TopicSession    _session;
        private String          _clientId;

        public TopicConnection getConnection()
        {
            return _connection;
        }

        public String getClientId()
        {
            return _clientId;
        }

        public Topic getTopic()
        {
            return _topic;
        }

        public void setTopic(Topic topic)
        {
            _topic = topic;
        }

        public TopicSession getSession()
        {
            return _session;
        }

        public JmsInfo(TopicConnection connection, String clientId,
                Topic topic, TopicSession session)
        {
            super();
            _connection = connection;
            _clientId = clientId;
            _topic = topic;
            _session = session;
        }

        /**
         * a publisher can post methods to a topic
         * 
         * @return
         * @throws JMSException
         */
        public TopicPublisher createPublisher() throws JMSException
        {
            return _session.createPublisher(_topic);
        }

        /**
         * a durable subscriber receives old messages
         * 
         * @return
         * @throws JMSException
         */
        public TopicSubscriber createDurableSubscriber() throws JMSException
        {
            return _session.createDurableSubscriber(_topic, getConnection()
                    .getClientID());
        }

        public boolean close()
        {
            try
            {
                _connection.close();
                _session.close();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }
}
