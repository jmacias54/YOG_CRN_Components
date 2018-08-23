package mx.com.amx.yog.components.crn.dto;

import java.io.Serializable;

public class ParametrosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dominio;
	private String ambiente;
	private String url;
	
	
	
	private String nnotaController;
	private String categoriaController;
	private String deportesController;
	private String magazinesController;
	private String tipoVideoController;
	
	private String pathFiles;

	
	
	
	
	public String getPathFiles() {
		return pathFiles;
	}
	public void setPathFiles(String pathFiles) {
		this.pathFiles = pathFiles;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNnotaController() {
		return nnotaController;
	}
	public void setNnotaController(String nnotaController) {
		this.nnotaController = nnotaController;
	}
	public String getCategoriaController() {
		return categoriaController;
	}
	public void setCategoriaController(String categoriaController) {
		this.categoriaController = categoriaController;
	}
	public String getDeportesController() {
		return deportesController;
	}
	public void setDeportesController(String deportesController) {
		this.deportesController = deportesController;
	}
	public String getMagazinesController() {
		return magazinesController;
	}
	public void setMagazinesController(String magazinesController) {
		this.magazinesController = magazinesController;
	}
	public String getTipoVideoController() {
		return tipoVideoController;
	}
	public void setTipoVideoController(String tipoVideoController) {
		this.tipoVideoController = tipoVideoController;
	}
	


	
	
	
	
	
	
	

}
