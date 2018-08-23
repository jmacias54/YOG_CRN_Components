/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.ws;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.TipoVideo;
import mx.com.amx.yog.components.crn.utils.PropertiesUtils;
import mx.com.amx.yog.components.crn.ws.exception.CallWSException;



/**
 * @author Jesus Armando Macias Benitez
 *
 */
public class TipoVideoCallWS {

	private static Logger logger = Logger.getLogger(TipoVideoCallWS.class);

	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public TipoVideoCallWS() {
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

		try {
			props.load(this.getClass().getResourceAsStream("/general.properties"));
		} catch (Exception e) {
			logger.error("[ConsumeWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}

	}

	public List<TipoVideo> findAll() throws CallWSException {
		logger.debug("--- findAll --- [ CatalogsCallWS ] --- ");

		PropertiesUtils properties = new PropertiesUtils();
		ParametrosDTO parametros = null;
		List<TipoVideo> lista = null;

		try {

			parametros = properties.obtenerPropiedades();
			String URL_WS = parametros.getUrl() +parametros.getTipoVideoController();

			logger.debug("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			lista = Arrays.asList(restTemplate.postForObject(URL_WS, entity, TipoVideo[].class));

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException findAll [ TipoVideoCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findAll [ TipoVideoCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findAll  [ TipoVideoCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}
		
		
		logger.debug(" lista  size "+lista.size());
		return lista;

	}

	public TipoVideo findById(String idTipoVideo) throws CallWSException {
		logger.debug("--- findByIdTipoVideo --- [ CatalogsCallWS ] --- ");
		logger.debug("--- idTipoVideo : " + idTipoVideo + " --- ");

		PropertiesUtils properties = new PropertiesUtils();
		ParametrosDTO parametros = null;
		

		try {

			parametros = properties.obtenerPropiedades();
			String URL_WS = parametros.getUrl() + parametros.getTipoVideoController() + idTipoVideo + "/";
			logger.debug("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			return restTemplate.postForObject(URL_WS, entity, TipoVideo.class);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException findByIdTipoVideo [ TipoVideoCallWS ]: "
					+ rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findByIdTipoVideo [ TipoVideoCallWS ]: ", rre);
			throw new CallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findByIdTipoVideo  [ TipoVideoCallWS ]: ", e);
			throw new CallWSException(e.getMessage());
		}

	}

}
