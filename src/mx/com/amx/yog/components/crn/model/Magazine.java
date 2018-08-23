package mx.com.amx.yog.components.crn.model;

import java.io.Serializable;



/**
 * The persistent class for the yog_ba_c_magazine database table.
 * 
 */

public class Magazine implements Serializable {
	private static final long serialVersionUID = 1L;


	private String fcIdMagazine;
	private String fcTitulo;
	private int fiEstatus;
	private int fiRegistros;

	public Magazine() {
	}

	public String getFcIdMagazine() {
		return this.fcIdMagazine;
	}

	public void setFcIdMagazine(String fcIdMagazine) {
		this.fcIdMagazine = fcIdMagazine;
	}

	public String getFcTitulo() {
		return this.fcTitulo;
	}

	public void setFcTitulo(String fcTitulo) {
		this.fcTitulo = fcTitulo;
	}

	public int getFiEstatus() {
		return this.fiEstatus;
	}

	public void setFiEstatus(int fiEstatus) {
		this.fiEstatus = fiEstatus;
	}

	public int getFiRegistros() {
		return this.fiRegistros;
	}

	public void setFiRegistros(int fiRegistros) {
		this.fiRegistros = fiRegistros;
	}

}