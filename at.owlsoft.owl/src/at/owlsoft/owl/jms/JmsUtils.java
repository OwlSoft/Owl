package at.owlsoft.owl.jms;

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
        // TODO: Anderer Server
        context = new InitialContext();
        // get factory from application server
        connectionFactory = (TopicConnectionFactory) context
                .lookup("TopicConnectionFactory");

        // get topic connection and session
        connection = connectionFactory.createTopicConnection();
        if (clientId != null)
        {
            connection.setClientID(clientId);
        }
        else
        {
            connection.setClientID(UUID.randomUUID().toString()
                    .substring(0, 10));
        }
        session = connection
                .createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // lookup topic and return info
        topic = (Topic) context.lookup(topicName);

        return new JmsInfo(connection, topic, session, clientId);
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

        public JmsInfo(TopicConnection connection, Topic topic,
                TopicSession session, String clientId)
        {
            super();
            _connection = connection;
            _topic = topic;
            _session = session;
            _clientId = clientId;
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
            return _session.createDurableSubscriber(_topic, _clientId);
        }

        public boolean close()
        {
            try
            {
                _connection.close();
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }
}
