/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.request;

/**
 * @author Jesus Armando Macias Benitez
 *
 */
public class NotesByIdDeporte {

	private String idDeporte;
	private int limit;
	private String fechaIni;
	private String fechaFin;

	public NotesByIdDeporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(String idDeporte) {
		this.idDeporte = idDeporte;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
