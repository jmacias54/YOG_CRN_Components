package mx.com.amx.yog.components.crn.model;

import java.io.Serializable;


/**
 * The persistent class for the yog_ba_c_categoria database table.
 * 
 */

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String fcIdCategoria;
	private String fcDescripcion;
	private String fcDfp;
	private String fcFriendlyUrl;
	private int fiEstatus;

	public Categoria() {
	}

	public String getFcIdCategoria() {
		return this.fcIdCategoria;
	}

	public void setFcIdCategoria(String fcIdCategoria) {
		this.fcIdCategoria = fcIdCategoria;
	}

	public String getFcDescripcion() {
		return this.fcDescripcion;
	}

	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	public String getFcDfp() {
		return this.fcDfp;
	}

	public void setFcDfp(String fcDfp) {
		this.fcDfp = fcDfp;
	}

	public String getFcFriendlyUrl() {
		return this.fcFriendlyUrl;
	}

	public void setFcFriendlyUrl(String fcFriendlyUrl) {
		this.fcFriendlyUrl = fcFriendlyUrl;
	}

	public int getFiEstatus() {
		return this.fiEstatus;
	}

	public void setFiEstatus(int fiEstatus) {
		this.fiEstatus = fiEstatus;
	}

}