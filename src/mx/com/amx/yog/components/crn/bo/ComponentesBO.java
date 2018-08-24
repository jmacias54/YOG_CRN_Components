/**
 * 
 */
package mx.com.amx.yog.components.crn.bo;

import org.apache.log4j.Logger;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class ComponentesBO {
	
	private final Logger logger = Logger.getLogger(ComponentesBO.class);

	/*
	@Autowired
	private NNotaCallWS nNotaCallWS;
	@Autowired
	private Templates templates;



	


	public void crearJsonPorCategoria(List<Categoria> listaCatgeoria,ParametrosDTO parametros) {
		logger.debug(" --- creaMagazineYNotasAutomticasPorCategoria [ GenerarComponentesBO ] ---- ");
		List<Nota> lista = null;
		try {
			for (Categoria categoria : listaCatgeoria) {
				logger.debug(" --- se obtienen las notas por la categoria : " + categoria.getFcIdCategoria() + "---- ");
				

				// lista = nNotaCallWS.lastNotesFindByIdCategoriaLimit(categoria.getFcIdCategoria(),parametros.getLimit(), parametros.getUrl());
				
				lista = nNotaCallWS._getNotesByIdCategoria(categoria.getFcIdCategoria(), 21, "", "", parametros);

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
	
	
	
	*/
}
