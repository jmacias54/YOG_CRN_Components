/**
 * 
 */
package mx.com.amx.yog.components.crn.bo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Categoria;
import mx.com.amx.yog.components.crn.model.Magazine;
import mx.com.amx.yog.components.crn.model.Nota;
import mx.com.amx.yog.components.crn.ws.NNotaCallWS;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class ComponentesBO {
	
	private final Logger logger = Logger.getLogger(ComponentesBO.class);

	
	@Autowired
	private NNotaCallWS nNotaCallWS;
	@Autowired
	private Templates templates;


	
	public void creaPropuestas(ParametrosDTO parametros) {
		logger.debug(" --- creaPropuestas [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {

			lista = nNotaCallWS.lastNotesFindByIdCategoriaNotInIMagazineByIdMagazineLimit(
					Constants.CATEGORIA_PROPUESTAS, 0, Constants.MAGAZINE_PROPUESTAS, parametros.getURL_WS_BASE());

			if (lista.size() > 0) {
				templates.crearNotasElecciones(lista, Constants.CATEGORIA_PROPUESTAS, parametros);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaPropuestas  [ GenerarComponentesBO ] !" + e.getMessage());
		}

	}
	
	public void creaDebates(ParametrosDTO parametros) {
		logger.debug(" --- creaDebates [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {

			List<Nota> listaPosdebates = nNotaCallWS.findByIdMagazine(Constants.MAGAZINE_POSDEBATES, 0, parametros.getURL_WS_BASE());
			lista = nNotaCallWS.findByIdMagazine(Constants.MAGAZINE_DEBATES, 0, parametros.getURL_WS_BASE());

			if (lista.size() > 0) {
				templates.crearNotasElecciones(lista, Constants.MAGAZINE_DEBATES, parametros);
				templates.crearNotasElecciones(listaPosdebates, Constants.MAGAZINE_POSDEBATES, parametros);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaDebates  [ GenerarComponentesBO ] !" + e.getMessage());
		}

	}
	
	public void creaEncuestas(ParametrosDTO parametros) {
		logger.debug(" ===================================================");
		logger.debug(" --- creaEncuestas [ GenerarComponentesBO ] ---- ");
		logger.debug(" ===================================================");
		
		
		List<Nota> lista = null;
		try {

			lista = nNotaCallWS.lastNotesFindByIdCategoriaLimit(Constants.CATEGORIA_ENCUESTAS, 0, parametros.getURL_WS_BASE());

			if (lista.size() > 0) {
				templates.crearNotasElecciones(lista, Constants.CATEGORIA_ENCUESTAS, parametros);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaEncuestas  [ GenerarComponentesBO ] !" + e.getMessage());
		}

	}

	public void creaNotasTags(ParametrosDTO parametros) {
		logger.debug(" --- creaNotasTags [ GenerarComponentesBO ] ---- ");
		
		
		List<Nota> lista = null;
		
		
		
		try {

			String[] magazineTag = parametros.getMagazinetag().split(";");

			for (int index = 0; index < magazineTag.length; index++) {
				String[] componentArray = magazineTag[index].split("\\|");

				String fileMagazine = componentArray[0];
				String tags = componentArray[1];

				String[] fileMagazineArray = fileMagazine.split(",");

				String file = fileMagazineArray[0];
				String idMagazine = fileMagazineArray[1];

				/* notas tag manuales */
				lista = nNotaCallWS.findByTagsNotInIMagazineByIdMagazine(tags, parametros.getLimit(),idMagazine, parametros.getURL_WS_BASE());
				templates.crearNotasTags(lista, file, parametros);


			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaTags  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}
	
	
	public void creaNotasTipoSeccionTags(ParametrosDTO parametros) {
		logger.debug("================================================================== ");
		logger.debug(" --- creaNotasTipoSeccionTags [ GenerarComponentesBO ] ---- ");
		logger.debug("================================================================== ");
		
		
		List<Nota> lista = null;
		
		try {

			String[] tipoSeccionTagMagazine = parametros.getTipoSeccionTagMagazine().split("\\|");

			for (int index = 0; index < tipoSeccionTagMagazine.length; index++) {
				
				String[] seccionTagArray = tipoSeccionTagMagazine[index].split(",");

				String idTipoSeccion = seccionTagArray[0];
				String tag = seccionTagArray[1];
				String magazine = seccionTagArray[2];
				String name = idTipoSeccion+"-"+tag;
				

				/* notas tipo seccion  tag manuales */
				lista = nNotaCallWS.findByIdTagAndIdTipoSeccionNotInIMagazineByIdMagazine(tag, idTipoSeccion,magazine, parametros.getLimit(), parametros.getURL_WS_BASE());
				
				logger.debug("================================================================== ");
				logger.debug(" --- lista : "+lista.size()+" ---- ");
				logger.debug("================================================================== ");
				
				if (lista.size() > 0) {
					
					templates.crearNotas(lista, Constants.TYPE_TIPO_SECCION_TAG_MAGAZINE, false,name, parametros);
				}
			}
			

		} catch (Exception e) {
			logger.error(" ¡ Error  creaNotasTipoSeccionTags  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}
	
	public void creaNotasSeccioTags(ParametrosDTO parametros) {
		logger.debug(" --- creaNotasSeccioTags [ GenerarComponentesBO ] ---- ");
		
		
		List<Nota> lista = null;
		
		
		try {

			String[] seccionTag = parametros.getSeccionTag().split("\\|");

			for (int index = 0; index < seccionTag.length; index++) {
			
				String[] seccionTagArray = seccionTag[index].split(",");

				String seccion = seccionTagArray[0];
				String tags = seccionTagArray[1];
				String name = seccion+"-"+tags;
				

				/* notas tag manuales */
				
				lista = nNotaCallWS.findByIdTagAndIdSeccion(tags, seccion, parametros.getLimit(), parametros.getURL_WS_BASE());
				
				
				if (lista.size() > 0) {
					
					templates.crearMagazine(lista, Constants.TYPE_SECCION_TAG, name, parametros);
					templates.crearNotas(lista, Constants.TYPE_SECCION_TAG, false,name, parametros);
				}
			
				


			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaNotasSeccioTags  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}

	public void creaHome(String idMagazine) {
		logger.debug(" --- creaHome [ GenerarComponentesBO ] ---- ");
		
		List<Nota> lista = null;
		
		
		try {

			
			CargaProperties props = new CargaProperties();
			ParametrosDTO parametros = new ParametrosDTO();
			parametros = props.obtenerPropiedades(Constants.PROPERTIES);
			
			
			
			lista = nNotaCallWS.findByIdMagazine(idMagazine, 0 , parametros.getURL_WS_BASE());
			templates.crearMagazine(lista, Constants.TYPE_HOME, idMagazine , parametros);
			logger.debug(" --- {creaHome}  listaNotas  total : "+lista.size()+" ---- ");
			
			
			List<Nota> listFinal = new ArrayList<Nota>();
			listFinal.addAll(lista);
			listFinal.addAll(nNotaCallWS.lastNotesFindByInfinitoHomeNotInIMagazineByIdMagazine(parametros.getHome(),parametros.getLimit(), parametros.getURL_WS_BASE()));
			logger.debug(" ---{creaHome}  listFinal  total : "+listFinal.size()+" ---- ");
			
			templates.crearNotas(listFinal, Constants.TYPE_HOME, false,null, parametros);
			
			
			
		} catch (Exception e) {
			logger.error(
					" ¡ Error  creaHome  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}
	
	public void creaMagazineYNotasAutomticasPorCategoria(List<Categoria> listaCatgeoria,ParametrosDTO parametros) {
		logger.debug(" --- creaMagazineYNotasAutomticasPorCategoria [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {
			for (Categoria categoria : listaCatgeoria) {
				logger.debug(" --- se obtienen las notas por la categoria : " + categoria.getFcIdCategoria() + "---- ");
				logger.debug(" --- Limit : " + parametros.getLimit() + "---- ");

				lista = nNotaCallWS.lastNotesFindByIdCategoriaLimit(categoria.getFcIdCategoria(),
						parametros.getLimit(), parametros.getURL_WS_BASE());

				if (lista.size() > 0) {
					logger.debug(" --- [ crear magazine-auto por categoria]  ---- ");
					templates.crearMagazine(lista, Constants.TYPE_AUTO, "" , parametros);
					logger.debug(" --- [ crear notas-auto  por categoria ]  ---- ");
					templates.crearNotas(lista, Constants.TYPE_AUTO, false,null, parametros);
				}

			}
		} catch (Exception e) {
			logger.error(
					" ¡ Error  creaMagazineYNotasAutomticasPorCategoria  [ GenerarComponentesBO ] !" + e.getMessage());
		}

	}
	
	
	
	public void creaMagazine(List<Magazine> listaMagazine , ParametrosDTO parametros) {
		logger.debug(" --- creaMagazine [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {
			for (Magazine magazine : listaMagazine) {
			

				lista =  nNotaCallWS.findByIdMagazine(magazine.getFcIdMagazine(), 0, parametros.getURL_WS_BASE());

				if (lista.size() > 0) {
					templates.crearMagazine(lista, Constants.TYPE_MANU, magazine.getFcIdMagazine() , parametros);
				}

			}
		} catch (Exception e) {
			logger.error(
					" ¡ Error  creaMagazine  [ GenerarComponentesBO ] !" + e.getMessage());
		}

	}

	public void creaNotasCategoria(ParametrosDTO parametros) {
		logger.debug(" --- creaNotasCategoria [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;

		try {
			String[] magazineCategoria = parametros.getMagazineCategoria().split("\\|");

			for (int index = 0; index < magazineCategoria.length; index++) {
				String[] magazine = magazineCategoria[index].split(",");


				lista = nNotaCallWS.lastNotesFindByIdCategoriaNotInIMagazineByIdMagazineLimit(magazine[1],
						parametros.getLimit(), magazine[0], parametros.getURL_WS_BASE());
				if (lista.size() > 0) {
					logger.debug(" --- [ crear notas-manu por categoria ] ---- ");
					templates.crearNotas(lista, Constants.TYPE_MANU, false,null, parametros);

				}
				
				

			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaNotasCategoria  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}

	public void creaNotasSeccion(ParametrosDTO parametros) {
		logger.debug(" --- creaNotasSeccion [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {

			
			String[] magazineSeccion = parametros.getMagazineSeccion().split("\\|");
			for (int index = 0; index < magazineSeccion.length; index++) {
				String[] magazine = magazineSeccion[index].split(",");
				lista = nNotaCallWS.lastNotesFindByIdSeccionNotInIMagazineByIdMagazineLimit(magazine[1],
						parametros.getLimit(), magazine[0], parametros.getURL_WS_BASE());
				if (lista.size() > 0) {
					logger.debug(" --- [ crear notas-manu por seccion]  ---- ");
					templates.crearNotas(lista, Constants.TYPE_MANU, true , magazine[1], parametros);

				}
				
		

			}

		} catch (Exception e) {
			logger.error(" ¡ Error  creaNotasSeccion  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}

	public void creaMagazinesVertical(ParametrosDTO parametros) {
		logger.debug(" --- creaMagazinesVertical [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {
			logger.debug(" --- [ Magazine Vertical ] ---- ");
			String[] magazines = parametros.getMagazines().split("\\|");

			for (int index = 0; index < magazines.length; index++) {
			
				String[] magazine = magazines[index].split(",");

				
				lista = nNotaCallWS.findByIdMagazine(magazine[0], 0, parametros.getURL_WS_BASE());
				logger.debug(" --- total notas Magazine Vertical : " + lista.size() + "---- ");

				if (lista.size() > 0) {
					logger.debug(" --- [ crear magazine-vertical ]  ---- ");
					templates.crearMagazineVertical(lista, magazine[1], magazine[0], parametros);
				}

			}
		} catch (Exception e) {
			logger.error(" ¡ Error  creaMagazinesVertical  [ GenerarComponentesBO ] !" + e.getMessage());
		}
	}
}
