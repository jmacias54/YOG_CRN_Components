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
import mx.com.amx.yog.components.crn.model.Deporte;
import mx.com.amx.yog.components.crn.model.Magazine;
import mx.com.amx.yog.components.crn.model.TipoVideo;
import mx.com.amx.yog.components.crn.utils.PropertiesUtils;
import mx.com.amx.yog.components.crn.ws.CategoriasCallWS;
import mx.com.amx.yog.components.crn.ws.DeportesCallWS;
import mx.com.amx.yog.components.crn.ws.MagazinesCallWS;
import mx.com.amx.yog.components.crn.ws.TipoVideoCallWS;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class GenerarComponentesBO {
	private final Logger logger = Logger.getLogger(GenerarComponentesBO.class);

	@Autowired
	private CategoriasCallWS categoriasCallWS;
	
	// @Autowired private ComponentesBO componentesBO;
	
	@Autowired
	private MagazinesCallWS magazinesCallWS;
	
	@Autowired
	private DeportesCallWS deportesCallWS;
	
	@Autowired
	private TipoVideoCallWS tipoVideoCallWS;
	
	@Autowired
	private JsonBO jsonBO;
	
	

	
	public void writeHtml() throws BOException {
		logger.debug(" --- writeHtml  [ GenerarComponentesBO ]---- ");
		
		

		List<Categoria> listaCatgeoria = null;
		List<Magazine> listaMagazine = null;
		List<TipoVideo> listaTipoVideo = null;
		List<Deporte> listaDeportes = null;
		 ParametrosDTO parametros = null;
		try {
			
			
			PropertiesUtils props = new PropertiesUtils();
			parametros = new ParametrosDTO();
			parametros = props.obtenerPropiedades();

			logger.debug(" --- se obtienen las categorias---- ");
			
			listaCatgeoria = categoriasCallWS.findAll();
			listaMagazine = magazinesCallWS.findAll();
			listaTipoVideo = tipoVideoCallWS.findAll();
			listaDeportes = deportesCallWS.findAll();
			
			
			
			logger.info("==================================================================================== ");
			logger.info("===============    crear json categoria    =================== ");
			logger.info("==================================================================================== ");
			
			if(listaCatgeoria != null && listaCatgeoria.size() > 0)
			jsonBO.crearJsonPorCategoria(listaCatgeoria, parametros);
			
			logger.info("==================================================================================== ");
			logger.info("===============    crear json deportes    =================== ");
			logger.info("==================================================================================== ");
			if(listaDeportes != null && listaDeportes.size() > 0)
			jsonBO.crearJsonPorDeporte(listaDeportes, parametros);

			logger.info("==================================================================================== ");
			logger.info("===============    crear json tipo video    =================== ");
			logger.info("==================================================================================== ");
			if(listaTipoVideo != null && listaTipoVideo.size() > 0)
			jsonBO.crearJsonPorTipoVideo(listaTipoVideo, parametros);
				   

			
			

		} catch (Exception e) {
			logger.error(" ¡ Error  writeHtml  [ GenerarComponentesBO ] !" + e.getMessage());
			throw new BOException(e.getMessage());
		}

	}

	
}
