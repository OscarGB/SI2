package ssii2;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import javax.jms.QueueBrowser;
import java.util.Enumeration;
import javax.naming.InitialContext;

public class VisaQueueMessageProducer {

    // TODO: Anotar los siguientes objetos para
    // conectar con la connection factory y con la cola
    // definidas en el enunciado
    private static ConnectionFactory connectionFactory;
    private static Queue queue;

    // Método de prueba
    public static void browseMessages(Session session)
    {
      try
      {
        Enumeration messageEnumeration;
        TextMessage textMessage;
        QueueBrowser browser = session.createBrowser(queue);
        messageEnumeration = browser.getEnumeration();
        if (messageEnumeration != null)
        {
          if (!messageEnumeration.hasMoreElements())
          {
            System.out.println("Cola de mensajes vacía!");
          }
          else
          {
            System.out.println("Mensajes en cola:");
            while (messageEnumeration.hasMoreElements())
            {
              textMessage =
                (TextMessage) messageEnumeration.nextElement();
              System.out.println(textMessage.getText());
            }
          }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        TextMessage message = null;

        if (args.length != 1) {
          System.err.println("Uso: VisaQueueMessageProducer [-browse | <msg>]");
          return;
        }

        try {
          // TODO: Inicializar connectionFactory
          // y queue mediante JNDI

          connection = connectionFactory.createConnection();
          session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
          if (args[0].equals("-browse")) {
            browseMessages(session); 
          } else {
            // TODO: Enviar argv[0] como mensaje de texto
          }
        } catch (Exception e) {
            System.out.println("Excepcion : " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            } // if

            System.exit(0);
        } // finally
    } // main
} // class
