/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.proceso;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;



/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class Proceso {
	
	
	private static Logger LOG = Logger.getLogger(Proceso.class);	
	
	@Autowired
	private GenerarComponentesBO  generarComponentesBO ;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		LOG.info("***Entrando al contexto****") ;
	}
	
	public void writeHtml() 
	{
		LOG.info("************ INICIA writeHtml ***********");
		try {
			generarComponentesBO.writeHtml();			
		LOG.info("************ FIN writeHtml ***********");
		} catch (Exception e) {
			LOG.error("Exception en writeNewsML [ Proceso ]",e);
		}
	}
}
