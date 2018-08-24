/**
 * 
 */
package mx.com.amx.yog.components.crn.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.yog.components.crn.bo.exception.BOException;
import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Categoria;
import mx.com.amx.yog.components.crn.model.Deporte;
import mx.com.amx.yog.components.crn.model.Nota;
import mx.com.amx.yog.components.crn.model.TipoVideo;
import mx.com.amx.yog.components.crn.utils.Utils;
import mx.com.amx.yog.components.crn.ws.NNotaCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class JsonBO {

	private final static Logger logger = Logger.getLogger(JsonBO.class);

	@Autowired
	private NNotaCallWS nNotaCallWS;

	// @Autowired
	// private ITagNotaCallWS iTagNotaCallWS;

	@Autowired
	private Utils utils;

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

	public void crearJsonPorCategoria(List<Categoria> listaCatgeoria, ParametrosDTO parametros) throws BOException {
		logger.debug(" --- crearJsonPorCategoria [ JsonBO ]---- ");
		logger.debug(" --- listaCatgeoria : " + listaCatgeoria.size() + "---- ");

		List<Nota> lista = null;
		try {

			for (Categoria categoria : listaCatgeoria) {

				logger.debug(" --- categoria : " + categoria.getFcIdCategoria() + " [ JsonBO ] ---- ");

				lista = nNotaCallWS._getNotesByIdCategoria(categoria.getFcIdCategoria(), 21, "", "", parametros);
				logger.debug(" --- lista : " + lista.size() + " [ JsonBO ] ---- ");

				if (lista != null && lista.size() > 0) {

					JSONObject jsonHome = new JSONObject();
					JSONArray jsonItemNotas = new JSONArray();

					for (Nota nota : lista) {

						JSONObject jsonNota = new JSONObject();

						jsonNota.put("id_contenido", nota.getIdContenido());
						jsonNota.put("titulo", nota.getTitulo());
						jsonNota.put("descripcion", nota.getDescripcion());

						Date date = formatter.parse(nota.getFechaPublicacion());
						jsonNota.put("fecha_publicacion", df.format(date));

						// jsonNota.put("tipo_nota", tipoNotaClass(nota.getTipoNota()));
						jsonNota.put("tipo_nota", nota.getTipoNota());
						jsonNota.put("imagen_principal", nota.getImagenPrincipal());

						String imagenMin = nota.getImagenPrincipal();
						imagenMin = imagenMin.replace("Principal", "Miniatura");
						jsonNota.put("imagen_miniatura", imagenMin);

						jsonNota.put("id_categoria", nota.getIdCategoria());
						jsonNota.put("id_seccion", nota.getIdSeccion());
						jsonNota.put("desc_categoria", nota.getCategoriaDescripcion());
						jsonNota.put("desc_seccion", nota.getSeccionDescripcion());

						/*
						 * List<ITagNota> listaTags =
						 * iTagNotaCallWS.findByIdContenido(nota.getIdContenido(),
						 * parametros.getURL_WS_BASE()); JSONArray tagsArray = new JSONArray ();
						 * 
						 * 
						 * for (ITagNota iTagNota : listaTags) {
						 * 
						 * tagsArray.put(iTagNota.getFcIdTag()); }
						 * 
						 * jsonNota.put("tags", tagsArray);
						 */

						jsonNota.put("url_nota", nota.getFriendlyUrl());
						jsonItemNotas.put(jsonNota);

					}

					jsonHome.put("listaNotas", jsonItemNotas);
					jsonHome.put("mensaje", "OK");
					jsonHome.put("codigo", "0");
					jsonHome.put("causa_error", "");

					String name = "/ultimas-" + categoria.getFcIdCategoria();
					String basePath = parametros.getPathFiles() + "json/categoria/" + categoria.getFcIdCategoria()
							+ "/";

					logger.info(" basePath  : " + basePath + "");
					logger.info(" name  : " + name + "");

					if (utils.createFolders(basePath)) {

						utils.writeJson(basePath + name + ".json", jsonHome.toString());
						logger.info("-Se genero el archivo json");
					}
				}
			}

		} catch (Exception e) {
			logger.error(" --- ¡ Error Exception crearJsonNota [ JsonBO ]  ! : " + e.getMessage() + "---- ");
			throw new BOException(e.getMessage());
		}
	}

	public void crearJsonPorDeporte(List<Deporte> listaDeportes, ParametrosDTO parametros) throws BOException {
		logger.debug(" --- crearJsonPorDeporte [ JsonBO ]---- ");
		logger.debug(" --- listaDeportes: " + listaDeportes.size() + "---- ");
		List<Nota> lista = null;
		try {

			for (Deporte deporte : listaDeportes) {
				logger.debug(" --- deporte : " + deporte.getFcIdDeporte() + " [ JsonBO ] ---- ");
				lista = nNotaCallWS._getNotesByIdDeporte(deporte.getFcIdDeporte(), 21, "", "", parametros);
				logger.debug(" --- lista : " + lista.size() + " [ JsonBO ] ---- ");

				JSONObject jsonHome = new JSONObject();
				JSONArray jsonItemNotas = new JSONArray();

				if (lista != null && lista.size() > 0) {
					for (Nota nota : lista) {

						JSONObject jsonNota = new JSONObject();

						jsonNota.put("id_contenido", nota.getIdContenido());
						jsonNota.put("titulo", nota.getTitulo());
						jsonNota.put("descripcion", nota.getDescripcion());

						Date date = formatter.parse(nota.getFechaPublicacion());
						jsonNota.put("fecha_publicacion", df.format(date));

						// jsonNota.put("tipo_nota", tipoNotaClass(nota.getTipoNota()));
						jsonNota.put("tipo_nota", nota.getTipoNota());
						jsonNota.put("imagen_principal", nota.getImagenPrincipal());

						String imagenMin = nota.getImagenPrincipal();
						imagenMin = imagenMin.replace("Principal", "Miniatura");
						jsonNota.put("imagen_miniatura", imagenMin);

						jsonNota.put("id_categoria", nota.getIdCategoria());
						jsonNota.put("id_seccion", nota.getIdSeccion());
						jsonNota.put("desc_categoria", nota.getCategoriaDescripcion());
						jsonNota.put("desc_seccion", nota.getSeccionDescripcion());

						/*
						 * List<ITagNota> listaTags =
						 * iTagNotaCallWS.findByIdContenido(nota.getIdContenido(),
						 * parametros.getURL_WS_BASE()); JSONArray tagsArray = new JSONArray ();
						 * 
						 * 
						 * for (ITagNota iTagNota : listaTags) {
						 * 
						 * tagsArray.put(iTagNota.getFcIdTag()); }
						 * 
						 * jsonNota.put("tags", tagsArray);
						 */

						jsonNota.put("url_nota", nota.getFriendlyUrl());
						jsonItemNotas.put(jsonNota);

					}

					jsonHome.put("listaNotas", jsonItemNotas);
					jsonHome.put("mensaje", "OK");
					jsonHome.put("codigo", "0");
					jsonHome.put("causa_error", "");

					String name = "/ultimas-" + deporte.getFcIdDeporte();
					String basePath = parametros.getPathFiles() + "json/deporte/" + deporte.getFcIdDeporte() + "/";

					logger.info(" basePath  : " + basePath + "");
					logger.info(" name  : " + name + "");

					if (utils.createFolders(basePath)) {

						utils.writeJson(basePath + name + ".json", jsonHome.toString());
						logger.info("-Se genero el archivo json");
					}

				}

			}

		} catch (Exception e) {
			logger.error(" --- ¡ Error Exception crearJsonNota [ JsonBO ]  ! : " + e.getMessage() + "---- ");
			throw new BOException(e.getMessage());
		}
	}

	public void crearJsonPorTipoVideo(List<TipoVideo> listaTipoVideo, ParametrosDTO parametros) throws BOException {
		logger.debug(" --- crearJsonPorTipoVideo [ JsonBO ]---- ");
		logger.debug(" --- listaTipoVideo : " + listaTipoVideo.size() + "---- ");
		List<Nota> lista = null;
		try {

			for (TipoVideo tipoVideo : listaTipoVideo) {

				logger.debug(" --- tipoVideo : " + tipoVideo.getFcIdTipoVideo() + " [ JsonBO ] ---- ");

				lista = nNotaCallWS._getNotesByIdTipoVideo(tipoVideo.getFcIdTipoVideo(), 21, "", "", parametros);
				logger.debug(" --- lista : " + lista.size() + " [ JsonBO ] ---- ");

				if (lista != null && lista.size() > 0) {
					JSONObject jsonHome = new JSONObject();
					JSONArray jsonItemNotas = new JSONArray();

					for (Nota nota : lista) {

						JSONObject jsonNota = new JSONObject();

						jsonNota.put("id_contenido", nota.getIdContenido());
						jsonNota.put("titulo", nota.getTitulo());
						jsonNota.put("descripcion", nota.getDescripcion());

						Date date = formatter.parse(nota.getFechaPublicacion());
						jsonNota.put("fecha_publicacion", df.format(date));

						// jsonNota.put("tipo_nota", tipoNotaClass(nota.getTipoNota()));
						jsonNota.put("tipo_nota", nota.getTipoNota());
						jsonNota.put("imagen_principal", nota.getImagenPrincipal());

						String imagenMin = nota.getImagenPrincipal();
						imagenMin = imagenMin.replace("Principal", "Miniatura");
						jsonNota.put("imagen_miniatura", imagenMin);

						jsonNota.put("id_categoria", nota.getIdCategoria());
						jsonNota.put("id_seccion", nota.getIdSeccion());
						jsonNota.put("desc_categoria", nota.getCategoriaDescripcion());
						jsonNota.put("desc_seccion", nota.getSeccionDescripcion());

						/*
						 * List<ITagNota> listaTags =
						 * iTagNotaCallWS.findByIdContenido(nota.getIdContenido(),
						 * parametros.getURL_WS_BASE()); JSONArray tagsArray = new JSONArray ();
						 * 
						 * 
						 * for (ITagNota iTagNota : listaTags) {
						 * 
						 * tagsArray.put(iTagNota.getFcIdTag()); }
						 * 
						 * jsonNota.put("tags", tagsArray);
						 */

						jsonNota.put("url_nota", nota.getFriendlyUrl());
						jsonItemNotas.put(jsonNota);

					}

					jsonHome.put("listaNotas", jsonItemNotas);
					jsonHome.put("mensaje", "OK");
					jsonHome.put("codigo", "0");
					jsonHome.put("causa_error", "");

					String name = "/ultimas-" + tipoVideo.getFcIdTipoVideo();
					String basePath = parametros.getPathFiles() + "json/tipo-video/" + tipoVideo.getFcIdTipoVideo()
							+ "/";

					logger.info(" basePath  : " + basePath + "");
					logger.info(" name  : " + name + "");

					if (utils.createFolders(basePath)) {

						utils.writeJson(basePath + name + ".json", jsonHome.toString());
						logger.info("-Se genero el archivo json");
					}

				}

			}

		} catch (Exception e) {
			logger.error(" --- ¡ Error Exception crearJsonNota [ JsonBO ]  ! : " + e.getMessage() + "---- ");
			throw new BOException(e.getMessage());
		}
	}

}
