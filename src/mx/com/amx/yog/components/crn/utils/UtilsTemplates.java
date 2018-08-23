/**

 * 
 */
package mx.com.amx.yog.components.crn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.yog.components.crn.dto.ParametrosDTO;
import mx.com.amx.yog.components.crn.model.Nota;


/**
 * @author Jesus A. Macias Benitez
 *
 */
public class UtilsTemplates {
	private final Logger logger = Logger.getLogger(UtilsTemplates.class);

	// private ParametrosDTO parametros;
	private SimpleDateFormat formatter;
	private SimpleDateFormat df;
	//private String img="";
	
	@Autowired
	private Utils utils;

	private UtilsTemplates() {

	

		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		df = new SimpleDateFormat("dd/MM/yyyy");
		
	
		
		
	}
	
	public String crearItem(Nota nota, String type) {
		logger.debug(" --- crearItem [ UtilsTemplates ] ---- ");

		StringBuffer bufferItem = new StringBuffer();
		ParametrosDTO parametros = null;

		try {
			
			CargaProperties props = new CargaProperties();
			 
			parametros = new ParametrosDTO();
			parametros = props.obtenerPropiedades(Constants.PROPERTIES);
			
			

			bufferItem.append(" ");

			if (type.equals(Constants.TYPE_MAGAZINE)) {
				bufferItem.append(crearItemMagazine(nota , parametros));
			} else if (type.equals(Constants.TYPE_NOTA)) {
				bufferItem.append(crearItemNota(nota , parametros));
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearItem ![ UtilsTemplates ]: ", e);
		}

		return bufferItem.toString();

	}
	
	
	
	public String crearItemNotaCircle(Nota nota , ParametrosDTO parametros ) {
		logger.debug(" --- crearItemNotaCircle [ UtilsTemplates ] ---- ");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";
		String img="";
		
		try {
			
			
			if (parametros.getAmbiente().equals(Constants.AMBIENTE_DEV)) {

				img = Constants.IMG_LOAD_DEV;
			} else if (parametros.getAmbiente().equals(Constants.AMBIENTE_PROD)) {

				img = Constants.IMG_LOAD_PROD;
			}
			

			
			
			String base= parametros.getBASE_URL();
			
			
			
			String url_externa = ((nota.getUrlExterna() == null)?"":nota.getUrlExterna() );
			
			if (url_externa.trim().equals("")) {
				/* para poder obtener la friendly url con todo y base , */
				url = base+nota.getFriendlyUrl();
			} else {
				url = nota.getUrlExterna();
			}
			
			bufferItem.append(" ");
			
			bufferItem.append(" <a class=\"item-circle\" href=\""+url+"\" >");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota , parametros));
			bufferItem.append(" 		<img src=\""+img+"\" data-echo=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"'   /> ");						//bufferItem.append(" 		<img src=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"' > ");
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_ITEM_CONTENT+"\"> ");
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemNotaCircle ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	public String crearItemNotaHome(Nota nota, ParametrosDTO parametros ) {
		logger.debug(" --- crearItemNotaHome [ UtilsTemplates ] ---- ");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";
		String img="";
		
		try {
			
			
			Date date = formatter.parse(nota.getFechaPublicacion());
			String base= parametros.getBASE_URL();
			
			if (parametros.getAmbiente().equals(Constants.AMBIENTE_DEV)) {

				img = Constants.IMG_LOAD_DEV;
			} else if (parametros.getAmbiente().equals(Constants.AMBIENTE_PROD)) {

				img = Constants.IMG_LOAD_PROD;
			}
			

			
			String url_externa = ((nota.getUrlExterna() == null)?"":nota.getUrlExterna() );
			
			if (url_externa.trim().equals("")) {
				/* para poder obtener la friendly url con todo y base , */
				url = base+nota.getFriendlyUrl();
			} else {
				url = nota.getUrlExterna();
			}
			
			bufferItem.append(" ");
			
			
			bufferItem.append(" <a  class=\""+nota.getIdCategoria()+" item\" href=\""+url+"\" data-tb-region-item>");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota , parametros));
			bufferItem.append(" 		<img src=\""+img+"\" data-echo=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"'   /> ");			
			//bufferItem.append(" 		<img src=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"' > ");
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_ITEM_CONTENT+"\"> ");
			bufferItem.append(" <div class=\"meta\"> ");
			bufferItem.append(" 		 	<span class=\"tag "+nota.getIdCategoria()+"\">"+utils.cambiaCaracteres(nota.getCategoriaDescripcion())+" </span> ");
			bufferItem.append(" 		 		<span class=\"date\"> <i class=\"far fa-calendar\"></i>"+df.format(date)+" </span> "); 
			bufferItem.append("    		</div> "); // cierra div meta
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemNotaHome ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	
	
	private String crearItemNota(Nota nota , ParametrosDTO parametros ) {
		logger.debug(" --- crearItemNota [ UtilsTemplates ] ---- ");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";
		String img="";
		
		try {
			
			
			Date date = formatter.parse(nota.getFechaPublicacion());
			String base= parametros.getBASE_URL();
			
			if (parametros.getAmbiente().equals(Constants.AMBIENTE_DEV)) {

				img = Constants.IMG_LOAD_DEV;
			} else if (parametros.getAmbiente().equals(Constants.AMBIENTE_PROD)) {

				img = Constants.IMG_LOAD_PROD;
			}
			

			
			String url_externa = ((nota.getUrlExterna() == null)?"":nota.getUrlExterna() );
			
			if (url_externa.trim().equals("")) {
				/* para poder obtener la friendly url con todo y base , */
				url = base+nota.getFriendlyUrl();
			} else {
				url = nota.getUrlExterna();
			}
			
			bufferItem.append(" ");
			
			
			bufferItem.append(" <a class=\""+nota.getIdCategoria()+" item\" href=\""+url+"\" >");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota,parametros));
			bufferItem.append(" 		<img src=\""+img+"\" data-echo=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"'   /> ");			
			//bufferItem.append(" 		<img src=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"' > ");
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_ITEM_CONTENT+"\"> ");
			bufferItem.append(" <div class=\"meta\"> ");
			bufferItem.append(" 		 	<span class=\"tag "+nota.getIdCategoria()+"\">"+utils.cambiaCaracteres(nota.getCategoriaDescripcion())+" </span> ");
			bufferItem.append(" 		 		<span class=\"date\"> <i class=\"far fa-calendar\"></i>"+df.format(date)+" </span> "); 
			bufferItem.append("    		</div> "); // cierra div meta
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemNota ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	
	public String crearItemEncuestas(Nota nota ,ParametrosDTO parametros) {
		logger.debug(" ===================================================");
		logger.debug(" --- crearItemEncuestas [ UtilsTemplates ] ---- ");
		logger.debug(" ===================================================");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";
		String img="";
		
		try {
			
			
			
			Date date = formatter.parse(nota.getFechaPublicacion());
			String base= parametros.getBASE_URL();
			
			if (parametros.getAmbiente().equals(Constants.AMBIENTE_DEV)) {

				img = Constants.IMG_LOAD_DEV;
			} else if (parametros.getAmbiente().equals(Constants.AMBIENTE_PROD)) {

				img = Constants.IMG_LOAD_PROD;
			}
			

			
			String url_externa = ((nota.getUrlExterna() == null)?"":nota.getUrlExterna() );
			
			if (url_externa.trim().equals("")) {
				/* para poder obtener la friendly url con todo y base , */
				url = base+nota.getFriendlyUrl();
			} else {
				url = nota.getUrlExterna();
			}
			
			
			bufferItem.append(" ");
			
			
			bufferItem.append(" <a class=\"item\" href=\""+url+"\" >");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota , parametros));
			
			

			bufferItem.append(" 		<img src=\""+img+"\" data-echo=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"'   /> ");						
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_ITEM_CONTENT+"\"> ");
			bufferItem.append(" <div class=\"meta\"> ");
			bufferItem.append(" 		 	<span class=\"tag "+nota.getIdCategoria()+"\">"+utils.cambiaCaracteres(nota.getCategoriaDescripcion())+" </span> ");
			bufferItem.append(" 		 		<span class=\"date\"> <i class=\"far fa-calendar\"></i>"+df.format(date)+" </span> "); 
			bufferItem.append("    		</div> "); // cierra div meta
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemEncuestas ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	private String crearItemMagazine(Nota nota ,ParametrosDTO parametros) {
		logger.debug(" --- crearItemMagazine [ UtilsTemplates ] ---- ");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";

		
		try {
			
			
			Date date = formatter.parse(nota.getFechaPublicacion());
			String base= parametros.getBASE_URL();
			
			
			
			String url_externa = ((nota.getUrlExterna() == null) ? "" : nota.getUrlExterna());

			if (url_externa.trim().equals("")) {

				url = base + nota.getFriendlyUrl();
			} else {

				url = nota.getUrlExterna();
			}
			
			
			bufferItem.append(" ");
			bufferItem.append(" <a class=\"swiper-slide\" href=\""+url+"\"> ");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota , parametros));
			
			bufferItem.append(" 		<img src=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"' > ");
			
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_CONTAINER+"\"> ");

			bufferItem.append(" 		 	<span class=\"tag "+nota.getIdCategoria()+"\">"+utils.cambiaCaracteres(nota.getCategoriaDescripcion())+" </span> ");
			bufferItem.append(" 		 		<span class=\"date\"> <i class=\"far fa-calendar\"></i>"+df.format(date)+" </span> "); 
			
			
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemMagazine ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	public String crearItemMagazineHome(Nota nota ,ParametrosDTO parametros) {
		logger.debug(" --- crearItemMagazineHome [ UtilsTemplates ] ---- ");
		
		StringBuffer bufferItem = new StringBuffer();
		String url = "";

		
		try {
			
			
			Date date = formatter.parse(nota.getFechaPublicacion());
			String base= parametros.getBASE_URL();
			
			
			
			String url_externa = ((nota.getUrlExterna() == null) ? "" : nota.getUrlExterna());

			if (url_externa.trim().equals("")) {

				url = base + nota.getFriendlyUrl();
			} else {

				url = nota.getUrlExterna();
			}
			
			
			bufferItem.append(" ");
			bufferItem.append(" <a  class=\"swiper-slide\" href=\""+url+"\" data-tb-region-item> ");
			bufferItem.append(" 	 <div class=\"thumb\">");

			logger.debug(" --- [ TIPO_NOTA  : "+nota.getTipoNota()+" ] --- ");
			bufferItem.append(crearTagTipoNota(nota , parametros));
			
			bufferItem.append(" 		<img src=\""+nota.getImagenPrincipal()+"\"  alt='"+utils.cambiaCaracteres(nota.getTitulo())+"' > ");
			
			bufferItem.append("    		</div> "); // cierra div del thumb
			bufferItem.append(" 		 <div class=\""+Constants.DIV_CLASS_CONTAINER+"\"> ");

			bufferItem.append(" 		 	<span class=\"tag "+nota.getIdCategoria()+"\">"+utils.cambiaCaracteres(nota.getCategoriaDescripcion())+" </span> ");
			bufferItem.append(" 		 		<span class=\"date\"> <i class=\"far fa-calendar\"></i>"+df.format(date)+" </span> "); 
			
			
			bufferItem.append(" 	 			 <h2>"+ utils.cambiaCaracteres( nota.getTitulo() )+"</h2> ");
			bufferItem.append("    		</div> "); // cierra div del item-content
			bufferItem.append(" </a>");
		
			
		
		}catch (Exception e) {
			logger.error(" ¡ Error en crearItemMagazineHome ![ UtilsTemplates ]: ", e);
		}
		
		
		return bufferItem.toString();
		
	}
	
	
	
	
	private String crearTagTipoNota(Nota nota ,ParametrosDTO parametros) {
		logger.debug(" --- crearTagTipoNota [ UtilsTemplates ] ---- ");
		logger.debug(" --- NOTA : " + nota.getNombre() + " --- ");
		logger.debug(" --- CATEGORIA  : " + nota.getIdCategoria() + " --- ");
		StringBuffer bufferTipoNota = new StringBuffer();
		

		try {

			logger.debug(" --- [ TIPO_NOTA  : " + nota.getTipoNota() + " ] --- ");
			/* valida si el tipo de nota es video o multimedia */
			if (nota.getTipoNota().equals(Constants.TIPO_NOTA_VIDEO) || nota.getTipoNota().equals(Constants.TIPO_NOTA_MULTIMEDIA)) {
				logger.debug(" --- VIDEO y/o MULTIMEDIA --- ");
				
				bufferTipoNota.append("				 <i class=\"far fa-play\"></i> ");

				/* valida si el tipo de nota es galeria */
			} else if (nota.getTipoNota().equals(Constants.TIPO_NOTA_GALERIA)) {
				logger.debug(" --- GALERIA--- ");
				bufferTipoNota.append("				 <i class=\"far fa-images\"></i> ");

				/* valida si el tipo de nota es infografia */
			} else if (nota.getTipoNota().equals(Constants.TIPO_NOTA_INFOGRAFIA)) {
				logger.debug(" --- INFOGRAFIA --- ");
			
				bufferTipoNota.append("				 <i class=\"far fa-image\"></i> ");
			}

		} catch (Exception e) {
			logger.error(" ¡ Error en crearTagTipoNota ![ UtilsTemplates ]: ", e);
		}

		return bufferTipoNota.toString();
	}

}
