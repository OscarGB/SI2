/**
 * Pr&aacute;ctricas de Sistemas Inform&aacute;ticos II
 * VisaCancelacionJMSBean.java
 */

package ssii2.visa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.ActivationConfigProperty;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @author jaime
 */
@MessageDriven(mappedName = "jms/VisaPagosQueue")
public class VisaCancelacionJMSBean extends DBTester implements MessageListener {
  static final Logger logger = Logger.getLogger("VisaCancelacionJMSBean");
  @Resource
  private MessageDrivenContext mdc;

  private static final String UPDATE_CANCELA_QRY = 
            "update pago " + 
            "set codrespuesta=999 " +
            "where idautorizacion=?";

  private static final String RECTIFICA_QRY = 
            "update tarjeta " + 
            "set saldo=saldo+pago.importe " +
            "from pago " +
            "where pago.idAutorizacion=? and tarjeta.numerotarjeta = pago.numerotarjeta"; 


  public VisaCancelacionJMSBean() {
  }

  // TODO : Método onMessage de ejemplo
  // Modificarlo para ejecutar el UPDATE definido más arriba,
  // asignando el idAutorizacion a lo recibido por el mensaje
  // Para ello conecte a la BD, prepareStatement() y ejecute correctamente
  // la actualización
  public void onMessage(Message inMessage) {
      TextMessage msg = null;
      Connection con = null;

      try {
          if (inMessage instanceof TextMessage) {
              msg = (TextMessage) inMessage;
              logger.info("MESSAGE BEAN: Message received: " + msg.getText());
              int idautorizacion = Integer.parseInt(msg.getText());
              con = getConnection();

              PreparedStatement pstmt = con.prepareStatement(UPDATE_CANCELA_QRY);
              pstmt.setInt(1, idautorizacion);
              if(pstmt.execute() || pstmt.getUpdateCount() != 1){
                logger.warning("Error en update cancelacion de pago");
              }
              pstmt.close();

              pstmt = con.prepareStatement(RECTIFICA_QRY);
              pstmt.setInt(1, idautorizacion);
              if(pstmt.execute() || pstmt.getUpdateCount() != 1){
                logger.warning("Error en rectificacion saldo en cancelacion de pago");
              }
              pstmt.close();

          } else {
              logger.warning(
                      "Message of wrong type: "
                      + inMessage.getClass().getName());
          }
      } catch (JMSException e) {
          e.printStackTrace();
          mdc.setRollbackOnly();
      } catch (Throwable te) {
          te.printStackTrace();
      }
  }


}
