package mx.com.amx.yog.components.crn.model;

import java.io.Serializable;

/**
 * The persistent class for the yog_ba_c_deporte database table.
 * 
 */

public class Deporte implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fcIdDeporte;

	private String fcDescripcion;

	private int fiEstatus;

	public Deporte() {
	}

	public String getFcIdDeporte() {
		return this.fcIdDeporte;
	}

	public void setFcIdDeporte(String fcIdDeporte) {
		this.fcIdDeporte = fcIdDeporte;
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