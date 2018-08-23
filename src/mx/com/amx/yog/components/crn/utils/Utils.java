/**
 * 
 */
package mx.com.amx.yog.components.crn.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

import mx.com.amx.yog.components.crn.model.NNota;





/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Utils {

	private final static Logger logger = Logger.getLogger(Utils.class);

	
	public  String getMediaContent(NNota nota ,String typeTemplate) {
		String template = "";
		
		if( !nota.getFcVideoYoutube().trim().equals("") || !nota.getFcIdVideoContent().trim().equals("")) {
			
			template= getVideo(nota,typeTemplate);
		}else {
			template = getImagen(nota,typeTemplate);
		}
		
		return template;
	}
	
	
	/**
	 * 
	 * */
	public void writeJson(String parRuta, String json)
	{		
		
		logger.debug(" --- writeJson [ Utils ]--- ");
		logger.debug("Inicia writeJson");
		try {							
			Writer wt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(parRuta),"UTF-8"));
			try {
				wt.write(json);
			} finally {
				wt.close();
			}						
		} catch (Exception e) {
			logger.error("Exception en writeJson [ Utils ] : ",e);
		}
	}


	
	
	public  boolean writeHTML(String rutaHMTL, String HTML) {
		logger.debug(" --- writeHTML [ Utils ]--- ");
		logger.debug(" --- rutaHMTL : "+rutaHMTL+"--- ");
	
		boolean success = false;
		try {
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				fichero = new FileWriter(rutaHMTL);
				pw = new PrintWriter(fichero);
				pw.println(HTML);
				pw.close();
				success = true;
			} catch (Exception e) {
				logger.error("Error al obtener la plantilla " + rutaHMTL + ": ", e);
				success = false;
			} finally {
				try {
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					success = false;
					logger.error("Error al cerrar el file: ", e2);
				}
			}
		} catch (Exception e) {
			success = false;
			logger.error("Fallo al crear la plantilla: ", e);
		}
		return success;
	}

	public  boolean createFolders(String carpetaContenido) {
		logger.debug(" --- createFolders [ Utils ]--- ");
		logger.debug(" --- carpetaContenido : "+carpetaContenido+"--- ");
		boolean success = false;
		try {
			File carpetas = new File(carpetaContenido);
			if (!carpetas.exists()) {
				success = carpetas.mkdirs();
			} else
				success = true;
		} catch (Exception e) {
			success = false;
			logger.error("Ocurrio error al crear las carpetas: ", e);
			logger.debug("Ocurrio error al crear las carpetas: ", e);
		}
		return success;
	}

	
	/**
	  * Clase para la codificacion de Caracteres
	  * @param String, Texto a codificar
	  * @return String, Texto codificado
	  * */
	public  String cambiaCaracteres(String texto) {
			texto = texto.replaceAll("�", "&#225;");
	        texto = texto.replaceAll("�", "&#233;");
	        texto = texto.replaceAll("�", "&#237;");
	        texto = texto.replaceAll("�", "&#243;");
	        texto = texto.replaceAll("�", "&#250;");  
	        texto = texto.replaceAll("�", "&#193;");
	        texto = texto.replaceAll("�", "&#201;");
	        texto = texto.replaceAll("�", "&#205;");
	        texto = texto.replaceAll("�", "&#211;");
	        texto = texto.replaceAll("�", "&#218;");
	        texto = texto.replaceAll("�", "&#209;");
	        texto = texto.replaceAll("�", "&#241;");        
	        texto = texto.replaceAll("�", "&#170;");          
	        texto = texto.replaceAll("�", "&#228;");
	        texto = texto.replaceAll("�", "&#235;");
	        texto = texto.replaceAll("�", "&#239;");
	        texto = texto.replaceAll("�", "&#246;");
	        texto = texto.replaceAll("�", "&#252;");    
	        texto = texto.replaceAll("�", "&#196;");
	        texto = texto.replaceAll("�", "&#203;");
	        texto = texto.replaceAll("�", "&#207;");
	        texto = texto.replaceAll("�", "&#214;");
	        texto = texto.replaceAll("�", "&#220;");
	        texto = texto.replaceAll("�", "&#191;");
	        texto = texto.replaceAll("�", "&#8220;");        
	        texto = texto.replaceAll("�", "&#8221;");
	        texto = texto.replaceAll("�", "&#8216;");
	        texto = texto.replaceAll("�", "&#8217;");
	        texto = texto.replaceAll("�", "...");
	        texto = texto.replaceAll("�", "&#161;");
	        texto = texto.replaceAll("�", "&#191;");
	        texto = texto.replaceAll("�", "&#176;");
	        
	        texto = texto.replaceAll("�", "&#221;");
	        texto = texto.replaceAll("�", "&#253;");
	        
	        texto = texto.replaceAll("�", "&#8211;");
	        texto = texto.replaceAll("�", "&#8212;");
	        //texto = texto.replaceAll("\"", "&#34;");
			return texto;
		}
	

	public  String getVideo(NNota dto,String typeTemplate) {

		StringBuffer mediaContent = new StringBuffer();

		mediaContent.append("<div class=\"thumb\"> <i class=\"fas fa-play\"></i><img src=\"" + dto.getFcImagenPrincipal() + "\"> </div> ");
		
		
		
		return mediaContent.toString();
	}
	
	/**
	 * Se lleva a cabo el reemplazo del Media Content de la nota, de tipo imagen, se
	 * valida si la nota es de tipo galería, infografia o default
	 * 
	 * @param ContentDTO
	 *            Instancia con la información necesaria para reemplazar
	 * @return String Se devuelve una cadena con el Media Content de tipo imagen
	 * @author jesus
	 */
	public  String getImagen(NNota dto,String typeTemplate) {
		
		StringBuffer mediaImage = new StringBuffer("");
		String imgPrincipal = dto.getFcImagenPrincipal() == null ? "" : dto.getFcImagenPrincipal();

		String galeria = dto.getClGaleriaImagenes() == null ? "" : dto.getClGaleriaImagenes();

		if (!galeria.trim().equals("")) {
			mediaImage.append("<img src='" + cambiaCaracteres(galeria) + "'>");
		} else {

			mediaImage.append("<img src='" + cambiaCaracteres(imgPrincipal) + "'>");
		}
	
		
		return mediaImage.toString();
	}
}
