/**
 * 
 */
package mx.com.amx.yog.components.crn.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.yog.components.crn.bo.exception.BOException;
import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Categoria;
import mx.com.amx.yog.components.crn.model.Magazine;
import mx.com.amx.yog.components.crn.utils.PropertiesUtils;
import mx.com.amx.yog.components.crn.ws.CategoriasCallWS;
import mx.com.amx.yog.components.crn.ws.MagazinesCallWS;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class GenerarComponentesBO {
	private final Logger logger = Logger.getLogger(GenerarComponentesBO.class);

	@Autowired
	private CategoriasCallWS categoriasCallWS;
	@Autowired 
	private ComponentesBO componentesBO;
	
	@Autowired
	private MagazinesCallWS magazinesCallWS;
	
	@Autowired
	private JsonBO jsonBO;
	
	

	
	public void writeHtml() throws BOException {
		logger.debug(" --- writeHtml  [ GenerarComponentesBO ]---- ");
		
		

		List<Categoria> listaCatgeoria = null;
		List<Magazine> listaMagazine = null;
		 ParametrosDTO parametros = null;
		try {
			
			
			PropertiesUtils props = new PropertiesUtils();
			parametros = new ParametrosDTO();
			parametros = props.obtenerPropiedades();

			logger.debug(" --- se obtienen las categorias---- ");
			
			listaCatgeoria = categoriasCallWS.findAll();
			listaMagazine = magazinesCallWS.findAll();
			
			logger.info("==================================================================================== ");
			logger.info("===============    creaMagazineYNotasAutomticasPorCategoria    =================== ");
			logger.info("==================================================================================== ");
			
			
			//Automaticas
			componentesBO.creaMagazineYNotasAutomticasPorCategoria(listaCatgeoria,parametros);

			
			logger.info("================================================================== ");
			logger.info("===============    Crear magazines     =================== ");
			logger.info("================================================================== ");
			//Crear magazines 
			componentesBO.creaMagazine(listaMagazine, parametros);
			
			logger.info("================================================================== ");
			logger.info("===============    Manuales    =================== ");
			logger.info("================================================================== ");
			//Manuales
			componentesBO.creaNotasCategoria(parametros);
			componentesBO.creaNotasSeccion(parametros);
			componentesBO.creaNotasTags(parametros);
			componentesBO.creaNotasSeccioTags(parametros);
			
			logger.info("================================================================== ");
			logger.info("===============    creaMagazinesVertical    =================== ");
			logger.info("================================================================== ");
			//Magazine vertical
			componentesBO.creaMagazinesVertical(parametros);
			
			logger.info("================================================================== ");
			logger.info("===============    HOME    =================== ");
			logger.info("================================================================== ");
			
			//HOME
			componentesBO.creaHome(parametros.getHome());
			
			logger.info("================================================================== ");
			logger.info("===============    elecciones    =================== ");
			logger.info("================================================================== ");
			//elecciones
			componentesBO.creaDebates(parametros);
			componentesBO.creaPropuestas(parametros);
			componentesBO.creaEncuestas(parametros);
			
			
			logger.info("================================================================== ");
			logger.info("===============    crearJsonNota    =================== ");
			logger.info("================================================================== ");
			
			// JSON 
			jsonBO.crearJsonNota(parametros);
			
			
			logger.info("================================================================== ");
			logger.info("===============    creaNotasTipoSeccionTags    =================== ");
			logger.info("================================================================== ");
			componentesBO.creaNotasTipoSeccionTags(parametros);
			

		} catch (Exception e) {
			logger.error(" ¡ Error  writeHtml  [ GenerarComponentesBO ] !" + e.getMessage());
			throw new BOException(e.getMessage());
		}

	}

	
}
