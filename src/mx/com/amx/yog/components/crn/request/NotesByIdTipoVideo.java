/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.request;

/**
 * @author Jesus Armando Macias Benitez
 *
 */
public class NotesByIdTipoVideo {

	private String idTipoVideo;
	private int limit;
	private String fechaIni;
	private String fechaFin;

	public String getIdTipoVideo() {
		return idTipoVideo;
	}

	public void setIdTipoVideo(String idTipoVideo) {
		this.idTipoVideo = idTipoVideo;
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

	public NotesByIdTipoVideo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
