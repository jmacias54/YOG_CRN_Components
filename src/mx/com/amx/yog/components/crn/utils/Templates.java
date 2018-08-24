/**
 * 
 */
package mx.com.amx.yog.components.crn.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Templates {
	
	private final static Logger logger = Logger.getLogger(Templates.class);
	
	@Autowired
	private Utils utils;

	@Autowired
	private UtilsTemplates utilsTemplates;


	
	/*
	public void crearMagazineVertical(List<Nota> lista , String title , String idMagazine , ParametrosDTO parametros) {
		logger.debug(" --- crearMagazineVertical [ Templates ] ---- ");
		if (lista != null)
			logger.debug(" --- total de registros : " + lista.size() + " ---- ");

		String templateMagazineVertical;
		StringBuffer bufferMagazineVertical;
		boolean success = false;
		String folder =  "";

		try {

			bufferMagazineVertical = new StringBuffer();
			
			if (idMagazine.equals(Constants.MAGAZINE_DEBATES)) {
				folder = parametros.getFolderElecciones();
				
			}else {
				folder=parametros.getFolderMagazineVertical();
			}
			
			
			
			templateMagazineVertical = parametros.getVerticalTemplate();

			logger.debug(" --- Contenido Magazine Vertical --- ");

			for (Nota nota : lista) {


				bufferMagazineVertical.append(utilsTemplates.crearItem(nota,Constants.TYPE_NOTA));

			}

			templateMagazineVertical = templateMagazineVertical.toString().replace(parametros.getConstantContent(),bufferMagazineVertical.toString());
			templateMagazineVertical = templateMagazineVertical.toString().replace(parametros.getConstantTituloVertical(), utils.cambiaCaracteres(title));
			
			
			
			String path = parametros.getRutaArchivos() + folder;
			
			
			
			if (utils.createFolders(path)) {

				String file = path + idMagazine + parametros.getHtmlFileExtension();
				
				
				success = utils.writeHTML(file, templateMagazineVertical);
				logger.info("Genero HTML : " + file + ": status :" + success);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearMagazineVertical ![ Templates ]: ", e);
		}

		logger.debug(" --- FIN crearMagazineVertical [ Templates ] ---- ");
	}
	

	public void crearMagazine(List<Nota> lista , String type , String html_name , ParametrosDTO parametros ) {
		logger.debug(" --- crearMagazine [ Templates ] ---- ");
		if (lista != null)
			logger.debug(" --- total de registros : " + lista.size() + " ---- ");

		String templateMagazine;
		StringBuffer bufferMagazine;
		boolean success = false;
		int limit = 0;

		try {

			bufferMagazine = new StringBuffer();
			
			
			if(type.equals(Constants.TYPE_HOME) ) {
				templateMagazine = parametros.getMagazineHome();
			}else {
				templateMagazine = parametros.getMagazineAutoTemplate();
			}

			logger.debug(" --- Contenido Magazine  --- ");

			if (type.equals(Constants.TYPE_HOME) 
					|| type.equals(Constants.TYPE_AUTO) 
					|| type.equals(Constants.TYPE_SECCION_TAG)) {

				limit = parametros.getTotalMagazineAuto();
			} else {

				limit = lista.size();
			}
			
			
			for (int i = 0; i < limit; i++) {

				if (type.equals(Constants.TYPE_HOME)) {
					bufferMagazine.append(utilsTemplates.crearItemMagazineHome(lista.get(i), parametros));
				} else {
					bufferMagazine.append(utilsTemplates.crearItem(lista.get(i), Constants.TYPE_MAGAZINE));
				}

			}

			templateMagazine = templateMagazine.toString().replace(parametros.getConstantContent(),bufferMagazine.toString());

			String folder = "";
			String name = "";
			
			
			if (html_name.equals("")) {
				name = lista.get(0).getIdCategoria();
			} else {

				name = html_name;
			}

			
			
			
			
			if (type.equals(Constants.TYPE_AUTO)) {
				folder = parametros.getFolderMagazineAuto();
			} else if (type.equals(Constants.TYPE_MANU)) {
				folder = parametros.getFolderMagazineManu();
			} else if (type.equals(Constants.TYPE_HOME)) {
				folder = parametros.getFolderHome();
				name = Constants.TYPE_MAGAZINE+"-"+Constants.TYPE_HOME ;
			}else if (type.equals(Constants.TYPE_SECCION_TAG) ) {
				folder = parametros.getFolderElecciones();
				name = html_name ;
			}
			
			String path = parametros.getRutaArchivos() + folder;
			
			
			if (utils.createFolders(path)) {
				
				String file = path + name + parametros.getHtmlFileExtension();
				
				success = utils.writeHTML(file, templateMagazine);
				logger.info("Genero HTML : " + file + ": status :" + success);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearMagazine ![ Templates ]: ", e);
		}

		logger.debug(" --- FIN crearMagazine [ Templates ] ---- ");
	}
	
	
	public void crearNotasElecciones(List<Nota> lista, String html_name , ParametrosDTO parametros) {
		logger.debug(" --- crearNotasElecciones [ Templates ] ---- ");
		if (lista != null)
			logger.debug(" --- total de registros : " + lista.size() + " ---- ");

		String template;
		StringBuffer buffer;
		boolean success = false;

		String folder = "";
		String name = "";

		try {

			buffer = new StringBuffer();
			
			if(html_name.equals(Constants.CATEGORIA_PROPUESTAS) 
					|| html_name.equals(Constants.MAGAZINE_DEBATES)
					|| html_name.equals(Constants.CATEGORIA_ENCUESTAS)
					|| html_name.equals(Constants.MAGAZINE_POSDEBATES)) {
				template = Constants.TEMPLATE_PROPUESTAS;
			}else {
				template = parametros.getNotasAutoTemplate();
			}
		

			logger.debug(" --- Contenido Notas --- ");

			for (Nota nota : lista) {

				if (html_name.equals(Constants.CATEGORIA_ENCUESTAS)) {
					buffer.append(utilsTemplates.crearItemEncuestas(nota, parametros));
				} else {

					buffer.append(utilsTemplates.crearItem(nota, Constants.TYPE_NOTA));
				}

			}

			template = template.toString().replace(parametros.getConstantContent(), buffer.toString());

			folder = parametros.getFolderElecciones();
			name = html_name  + parametros.getHtmlFileExtension();
			String path = parametros.getRutaArchivos() + folder;

			if (utils.createFolders(path)) {

				String file = path + name;

				success = utils.writeHTML(file, template);
				logger.info("Genero HTML : " + file + ": status :" + success);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearNotasElecciones ![ Templates ]: ", e);
		}

		logger.debug(" --- FIN crearNotasElecciones [ Templates ] ---- ");
	}
	
	
	public void crearNotas(List<Nota> lista , String type , Boolean  seccion ,String html_name , ParametrosDTO parametros) {
		logger.debug(" --- crearNotas [ Templates ] ---- ");
		if (lista != null)
			logger.debug(" --- total de registros : " + lista.size() + " ---- ");

		String templateMagazine;
		StringBuffer bufferMagazine;
		boolean success = false;

		try {

			bufferMagazine = new StringBuffer();
			
			if(type.equals(Constants.TYPE_HOME) ) {
				templateMagazine = parametros.getNotasHome();
			}else {
				templateMagazine = parametros.getNotasAutoTemplate();
			}
		

			logger.debug(" --- Contenido Notas --- ");

			int i = 0;

			if (type.equals(Constants.TYPE_HOME) 
					|| type.equals(Constants.TYPE_AUTO)
					|| type.equals(Constants.TYPE_SECCION_TAG)) {

				i = parametros.getTotalMagazineAuto();
			}
			
			for (; i < lista.size(); i++) {

				if (type.equals(Constants.TYPE_HOME)) {
					bufferMagazine.append(utilsTemplates.crearItemNotaHome(lista.get(i), parametros));
				} else {
					bufferMagazine.append(utilsTemplates.crearItem(lista.get(i), Constants.TYPE_NOTA));
				}

			}
			
			
			 


			templateMagazine = templateMagazine.toString().replace(parametros.getConstantContent(),bufferMagazine.toString());

			String folder = "";
			String name = "";
			
			if(seccion) {
				
				name = html_name + "-sec"+ parametros.getHtmlFileExtension();
			}else {
				
				name = lista.get(0).getIdCategoria() + parametros.getHtmlFileExtension();
			}
			
			
			
			if(type.equals(Constants.TYPE_AUTO)) {
				folder=parametros.getFolderNotasAuto();
			}else if(type.equals(Constants.TYPE_MANU)) {
				folder=parametros.getFolderNotasManu();
			}else if(type.equals(Constants.TYPE_HOME)) {
				folder=parametros.getFolderHome();
				name = Constants.TYPE_NOTA+"-"+Constants.TYPE_HOME + parametros.getHtmlFileExtension();
			}else if (type.equals(Constants.TYPE_SECCION_TAG) || type.equals(Constants.TYPE_TIPO_SECCION_TAG_MAGAZINE) ) {
				folder = parametros.getFolderElecciones();
				name = html_name  + "-notas" + parametros.getHtmlFileExtension();
			}
			
			String path = parametros.getRutaArchivos() + folder;
			
			if (utils.createFolders(path)) {

				
			
				
				String file = path+ name;
				
				success = utils.writeHTML(file, templateMagazine);
				logger.info("Genero HTML : " + file + ": status :" + success);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearNotas ![ Templates ]: ", e);
		}

		logger.debug(" --- FIN crearNotas [ Templates ] ---- ");
	}

	public void crearNotasTags(List<Nota> lista, String name , ParametrosDTO parametros) {
		logger.debug(" --- crearNotasTags [ Templates ] ---- ");
		logger.debug(" --- name : "+name+"---- ");
		if (lista != null)
			logger.debug(" --- total de registros : " + lista.size() + " ---- ");

		String template;
		StringBuffer buffer;
		boolean success = false;

		try {

			buffer = new StringBuffer();
			template = parametros.getNotasAutoTemplate();

			logger.debug(" --- Contenido Notas --- ");

			for (Nota nota : lista) {

				buffer.append(utilsTemplates.crearItem(nota, Constants.TYPE_NOTA));

			}

			template = template.toString().replace(parametros.getConstantContent(), buffer.toString());
			String path = parametros.getRutaArchivos() + parametros.getFolderNotasTags();

			if (utils.createFolders(path)) {

				String file = path + name;
				success = utils.writeHTML(file, template);
				logger.info("Genero HTML : " + file + ": status :" + success);
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearNotasTags ![ Templates ]: ", e);
		}

		logger.debug(" --- FIN crearNotasTags [ Templates ] ---- ");
	}

	*/
}
