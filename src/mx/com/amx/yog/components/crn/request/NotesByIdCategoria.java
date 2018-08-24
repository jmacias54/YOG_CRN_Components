/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.yog.components.crn.request;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class NotesByIdCategoria {
	
	private  String idCategoria; 
	private int limit ;
	private String fechaIni ; 
	private String fechaFin;
	
	
	
	
	
	public NotesByIdCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
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
