/**
 * 
 */
package mx.com.amx.yog.components.crn.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.weaver.Utils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.yog.components.crn.bo.exception.BOException;
import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Nota;
import mx.com.amx.yog.components.crn.ws.NNotaCallWS;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class JsonBO {
	
	
	private final static Logger logger = Logger.getLogger(JsonBO.class);
	
	
	@Autowired
	private NNotaCallWS nNotaCallWS;

	//@Autowired
	//private ITagNotaCallWS iTagNotaCallWS;
	
	
	@Autowired
	private Utils utils;
	
	private List<Nota> listaNota = null;
	
	private SimpleDateFormat formatter;
	private SimpleDateFormat df;

	/**
	 * 
	 */
	private JsonBO() {
		// TODO Auto-generated constructor stub

	
		
		
		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		df = new SimpleDateFormat("dd/MM/yyyy");

	}
	
	
	public void crearJsonNota(ParametrosDTO parametros) throws BOException {
		logger.debug(" --- crearJsonNota [ JsonBO ]---- ");
		
		try {
			
			listaNota = nNotaCallWS.findByIdTag(parametros.getIdTagJSON(), 100 , parametros.getURL_WS_BASE());
			
			
			logger.debug(" --- listaNota : "+listaNota.size()+" [ JsonBO ] ---- ");
			JSONObject jsonHome = new JSONObject();
			JSONArray jsonItemNotas = new JSONArray();

			
			for (Nota nota : listaNota) {
				
				JSONObject jsonNota = new JSONObject();
				
				jsonNota.put("id_contenido", nota.getIdContenido());
				jsonNota.put("titulo", nota.getTitulo());
				jsonNota.put("descripcion", nota.getDescripcion());
				
				Date date = formatter.parse(nota.getFechaPublicacion());
				jsonNota.put("fecha_publicacion", df.format(date));
				
				jsonNota.put("tipo_nota", tipoNotaClass(nota.getTipoNota()));
				jsonNota.put("imagen_principal", nota.getImagenPrincipal());
				
				String imagenMin = nota.getImagenPrincipal();
				imagenMin = imagenMin.replace("Principal", "Miniatura");
				jsonNota.put("imagen_miniatura", imagenMin);
				
				
				
				
				jsonNota.put("id_categoria", nota.getIdCategoria());
				jsonNota.put("id_seccion", nota.getIdSeccion());
				jsonNota.put("desc_categoria", nota.getCategoriaDescripcion());
				jsonNota.put("desc_seccion", nota.getSeccionDescripcion());
				
				List<ITagNota> listaTags = iTagNotaCallWS.findByIdContenido(nota.getIdContenido(), parametros.getURL_WS_BASE());
				JSONArray  tagsArray = new JSONArray ();
				
				
				for (ITagNota iTagNota : listaTags) {
					
					tagsArray.put(iTagNota.getFcIdTag());
				}
				
				
				
				jsonNota.put("tags", tagsArray);
				
				
				jsonNota.put("url_nota", nota.getFriendlyUrl());
				jsonItemNotas.put(jsonNota);	

				
				
			}
			
			
			jsonHome.put("listaNotas", jsonItemNotas);
			jsonHome.put("mensaje", "OK");
			jsonHome.put("codigo", "0");
			jsonHome.put("causa_error", "");	
			
			
			String name ="/elecciones-2018.json";
			String basePath = parametros.getRutaArchivos();
			basePath=basePath.replace(Constants.FOLDER_COMPONENTES_ESTATICOS_V5, "");
			String path =  basePath+ parametros.getFolderJSON();
			
			if (utils.createFolders(path)) {
				
				utils.writeJson(path+name, jsonHome.toString());
				logger.info("-Se genero el archivo json");
			}
			

			
		}catch (Exception e) {
			logger.error(" --- ¡ Error Exception crearJsonNota [ JsonBO ]  ! : "+e.getMessage()+"---- ");
			throw new BOException(e.getMessage());
		}
	}

	private String tipoNotaClass(String tipoNota) throws BOException {

		logger.debug(" --- tipoNotaClass [ JsonBO ] ---- ");

		String clazz = "";

		try {

			logger.debug(" --- [ TIPO_NOTA  : " + tipoNota + " ] --- ");
			/* valida si el tipo de nota es video o multimedia */
			if (tipoNota.equals(Constants.TIPO_NOTA_VIDEO) || tipoNota.equals(Constants.TIPO_NOTA_MULTIMEDIA)) {
				logger.debug(" --- VIDEO y/o MULTIMEDIA --- ");

				clazz = "far fa-play";

				/* valida si el tipo de nota es galeria */
			} else if (tipoNota.equals(Constants.TIPO_NOTA_GALERIA)) {
				logger.debug(" --- GALERIA--- ");
				clazz = "far fa-images";

				/* valida si el tipo de nota es infografia */
			} else if (tipoNota.equals(Constants.TIPO_NOTA_INFOGRAFIA)) {
				logger.debug(" --- INFOGRAFIA --- ");

				clazz = "far fa-image";
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en tipoNotaClass ![ JsonBO ]: ", e);
			throw new BOException(e.getMessage());
		}

		return clazz;

	}
}
