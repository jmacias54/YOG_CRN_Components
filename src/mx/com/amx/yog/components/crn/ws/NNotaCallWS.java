/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.ws;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Nota;
import mx.com.amx.yog.components.crn.request.NotesByIdCategoria;
import mx.com.amx.yog.components.crn.request.NotesByIdDeporte;
import mx.com.amx.yog.components.crn.request.NotesByIdTipoVideo;
import mx.com.amx.yog.components.crn.ws.exception.CallWSException;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class NNotaCallWS {
	
	
	private static Logger logger = Logger.getLogger(NNotaCallWS.class);

	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	

	public NNotaCallWS() {
		super();
		restTemplate = new RestTemplate();
		ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

		if (factory instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			((SimpleClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			System.out.println("Inicializando rest template 1");
		} else if (factory instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			System.out.println("Inicializando rest template 2");
		}

		restTemplate.setRequestFactory(factory);
		headers.setContentType(MediaType.APPLICATION_JSON);

	

	}
	
	
	
	public List<Nota> _getNotesByIdMagazine(String idMagazine,  int limit , ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesByIdMagazine --- [ NNotaCallWS ] --- ");

		List<Nota> lista = null;

		try {

			
			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesByIdMagazine";

			logger.debug("URL_WS: " + URL_WS);

			//HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idMagazine",idMagazine);
			parts.add("limit",limit);
			
			
			lista = Arrays.asList(restTemplate.postForObject(URL_WS, parts, Nota[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesByIdMagazine [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesByIdMagazine [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesByIdMagazine  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}

	
	public List<Nota> _getNotesNotInMagazineByIdCategoria(String idCategoria,  int limit , ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesNotInMagazineByIdCategoria --- [ NNotaCallWS ] --- ");

		List<Nota> lista = null;

		try {

			
			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesNotInMagazineByIdCategoria";

			logger.debug("URL_WS: " + URL_WS);

			//HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idCategoria",idCategoria);
			parts.add("limit",limit);
			
			lista = Arrays.asList(restTemplate.postForObject(URL_WS, parts, Nota[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesNotInMagazineByIdCategoria [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesNotInMagazineByIdCategoria [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesNotInMagazineByIdCategoria  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}

	public List<Nota> _getNotesByIdDeporte(String idDeporte, int limit, String fechaIni, String fechaFin,
			ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesByIdDeporte --- [ NNotaCallWS ] --- ");

		List<Nota> lista = null;

		try {

			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesByIdDeporte";
			logger.debug("URL_WS: " + URL_WS);

			
			
			NotesByIdDeporte req = new NotesByIdDeporte();
			req.setIdDeporte(idDeporte);
			req.setLimit(limit);
			req.setFechaIni(fechaIni);
			req.setFechaFin(fechaFin);
			HttpEntity<NotesByIdDeporte> entity = new HttpEntity<NotesByIdDeporte>(req);

			
			
			lista = Arrays.asList(restTemplate.postForObject(URL_WS, entity, Nota[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesByIdDeporte [ NNotaCallWS ]: "
					+ rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesByIdDeporte [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesByIdDeporte  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}

		logger.debug(" lista  size " + lista.size());
		return lista;

	}

	
	public List<Nota> _getNotesByIdCategoria(String idCategoria,  int limit , 
											  String fechaIni  ,  String fechaFin ,ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesByIdCategoria --- [ NNotaCallWS ] --- ");
		logger.debug("--- idCategoria : "+idCategoria+"--- ");
		logger.debug("--- limit : "+limit+"--- ");
		logger.debug("--- fechaIni : "+fechaIni+"--- ");
		logger.debug("--- fechaFin : "+fechaFin+"--- ");

		List<Nota> lista = null;

		try {

			
			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesByIdCategoria";
			logger.debug( "===========================================");
			logger.debug("URL_WS: " + URL_WS);
			logger.debug( "===========================================");

			NotesByIdCategoria req = new NotesByIdCategoria();
			req.setIdCategoria(idCategoria);
			req.setLimit(limit);
			req.setFechaIni(fechaIni);
			req.setFechaFin(fechaFin);
			
			HttpEntity<NotesByIdCategoria> entity = new HttpEntity<NotesByIdCategoria>(req);
			
			
			
			Nota[] array = restTemplate.postForObject(URL_WS, entity, Nota[].class);
			
			lista = Arrays.asList(array);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesByIdCategoria [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesByIdCategoria [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesByIdCategoria  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}
	
	
	
	
	public List<Nota> _getNotesByIdCategoriaAndIdDeporte(String idCategoria, String idDeporte, int limit ,  String fechaIni  ,  String fechaFin , ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesByIdCategoriaAndIdDeporte --- [ NNotaCallWS ] --- ");

		List<Nota> lista = null;

		try {

			
			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesByIdCategoriaAndIdDeporte";
				  //  URL_WS = URL_WS +idCategoria+"/"+idDeporte+"/"+limit+"/"+fechaIni+"/"+fechaFin+"/" ;
			logger.debug("URL_WS: " + URL_WS);

			// HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			MultiValueMap<String, Object> parts;
			parts = new LinkedMultiValueMap<String, Object>();
			parts.add("idCategoria",idCategoria);
			parts.add("idDeporte",idDeporte);
			parts.add("limit",limit);
			parts.add("fechaIni",fechaIni);
			parts.add("fechaFin",fechaFin);
			
			lista = Arrays.asList(restTemplate.postForObject(URL_WS, parts, Nota[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesByIdCategoriaAndIdDeporte [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesByIdCategoriaAndIdDeporte [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesByIdCategoriaAndIdDeporte  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}
	
	
	
	public List<Nota> _getNotesByIdTipoVideo(String idTipoVideo, int limit , String fechaIni  ,  String fechaFin ,  ParametrosDTO parametros) throws CallWSException {
		logger.debug("--- _getNotesByIdTipoVideo --- [ NNotaCallWS ] --- ");

		List<Nota> lista = null;

		try {

			
			String URL_WS = parametros.getUrl() + parametros.getNnotaController() + "getNotesByIdTipoVideo";
			logger.debug("URL_WS: " + URL_WS);

			NotesByIdTipoVideo req = new  NotesByIdTipoVideo();
			req.setIdTipoVideo(idTipoVideo);
			req.setLimit(limit);
			req.setFechaIni(fechaIni);
			req.setFechaFin(fechaFin);
			HttpEntity<NotesByIdTipoVideo> entity = new HttpEntity<NotesByIdTipoVideo>(req);

			lista = Arrays.asList(restTemplate.postForObject(URL_WS, entity, Nota[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException _getNotesByIdTipoVideo [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException _getNotesByIdTipoVideo [ NNotaCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception _getNotesByIdTipoVideo  [ NNotaCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}
	
	
}
