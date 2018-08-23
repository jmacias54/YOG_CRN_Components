package mx.com.amx.yog.components.crn.model;

import java.io.Serializable;


/**
 * The persistent class for the yog_ba_c_tipo_video database table.
 * 
 */

public class TipoVideo implements Serializable {
	private static final long serialVersionUID = 1L;


	private String fcIdTipoVideo;
	private String fcDescripcion;
	private int fiEstatus;

	public TipoVideo() {
	}

	public String getFcIdTipoVideo() {
		return this.fcIdTipoVideo;
	}

	public void setFcIdTipoVideo(String fcIdTipoVideo) {
		this.fcIdTipoVideo = fcIdTipoVideo;
	}

	public String getFcDescripcion() {
		return this.fcDescripcion;
	}

	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	public int getFiEstatus() {
		return this.fiEstatus;
	}

	public void setFiEstatus(int fiEstatus) {
		this.fiEstatus = fiEstatus;
	}

}