package mx.com.amx.yog.components.crn.model;

import java.io.Serializable;

/**
 * The persistent class for the uno_mx_n_nota database table.
 * 
 */

public class NNota implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fcUrlExterna;
	private String fcIdContenido;

	private String clGaleriaImagenes;

	private String clRtfContenido;

	private String fcAlternateText;

	private String fcDescripcion;

	private String fcDuration;

	private String fcEscribio;

	private String fcFileSize;

	private String fcFuente;

	private String fcIdCategoria;

	private String fcIdTipoNota;

	private String fcIdVideoContent;

	private String fcIdVideoPlayer;

	private String fcImagenPie;

	private String fcImagenPrincipal;

	private String fcInfografia;

	private String fcKeywords;

	private String fcLugar;

	private String fcNombre;

	private String fcPaisRegistro;

	private String fcPatrocinioBackground;

	private String fcPatrocinioColorTexto;

	private String fcPcode;

	private String fcPlaceGallery;

	private String fcSourceVideo;

	private String fcTags;

	private String fcTitulo;

	private String fcVideoYoutube;

	private String fdFechaModificacion;

	private String fdFechaPublicacion;

	private int fiBanInfinitoHome;

	private int fiBanNoTeLoPierdas;

	private int fiBanPatrocinio;

	private int fiBanVideoViral;

	public NNota() {
	}

	public String getFcUrlExterna() {
		return fcUrlExterna;
	}

	public void setFcUrlExterna(String fcUrlExterna) {
		this.fcUrlExterna = fcUrlExterna;
	}

	public String getFcIdContenido() {
		return this.fcIdContenido;
	}

	public void setFcIdContenido(String fcIdContenido) {
		this.fcIdContenido = fcIdContenido;
	}

	public String getClGaleriaImagenes() {
		return this.clGaleriaImagenes;
	}

	public void setClGaleriaImagenes(String clGaleriaImagenes) {
		this.clGaleriaImagenes = clGaleriaImagenes;
	}

	public String getClRtfContenido() {
		return this.clRtfContenido;
	}

	public void setClRtfContenido(String clRtfContenido) {
		this.clRtfContenido = clRtfContenido;
	}

	public String getFcAlternateText() {
		return this.fcAlternateText;
	}

	public void setFcAlternateText(String fcAlternateText) {
		this.fcAlternateText = fcAlternateText;
	}

	public String getFcDescripcion() {
		return this.fcDescripcion;
	}

	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	public String getFcDuration() {
		return this.fcDuration;
	}

	public void setFcDuration(String fcDuration) {
		this.fcDuration = fcDuration;
	}

	public String getFcEscribio() {
		return this.fcEscribio;
	}

	public void setFcEscribio(String fcEscribio) {
		this.fcEscribio = fcEscribio;
	}

	public String getFcFileSize() {
		return this.fcFileSize;
	}

	public void setFcFileSize(String fcFileSize) {
		this.fcFileSize = fcFileSize;
	}

	public String getFcFuente() {
		return this.fcFuente;
	}

	public void setFcFuente(String fcFuente) {
		this.fcFuente = fcFuente;
	}

	public String getFcIdCategoria() {
		return this.fcIdCategoria;
	}

	public void setFcIdCategoria(String fcIdCategoria) {
		this.fcIdCategoria = fcIdCategoria;
	}

	public String getFcIdTipoNota() {
		return this.fcIdTipoNota;
	}

	public void setFcIdTipoNota(String fcIdTipoNota) {
		this.fcIdTipoNota = fcIdTipoNota;
	}

	public String getFcIdVideoContent() {
		return this.fcIdVideoContent;
	}

	public void setFcIdVideoContent(String fcIdVideoContent) {
		this.fcIdVideoContent = fcIdVideoContent;
	}

	public String getFcIdVideoPlayer() {
		return this.fcIdVideoPlayer;
	}

	public void setFcIdVideoPlayer(String fcIdVideoPlayer) {
		this.fcIdVideoPlayer = fcIdVideoPlayer;
	}

	public String getFcImagenPie() {
		return this.fcImagenPie;
	}

	public void setFcImagenPie(String fcImagenPie) {
		this.fcImagenPie = fcImagenPie;
	}

	public String getFcImagenPrincipal() {
		return this.fcImagenPrincipal;
	}

	public void setFcImagenPrincipal(String fcImagenPrincipal) {
		this.fcImagenPrincipal = fcImagenPrincipal;
	}

	public String getFcInfografia() {
		return this.fcInfografia;
	}

	public void setFcInfografia(String fcInfografia) {
		this.fcInfografia = fcInfografia;
	}

	public String getFcKeywords() {
		return this.fcKeywords;
	}

	public void setFcKeywords(String fcKeywords) {
		this.fcKeywords = fcKeywords;
	}

	public String getFcLugar() {
		return this.fcLugar;
	}

	public void setFcLugar(String fcLugar) {
		this.fcLugar = fcLugar;
	}

	public String getFcNombre() {
		return this.fcNombre;
	}

	public void setFcNombre(String fcNombre) {
		this.fcNombre = fcNombre;
	}

	public String getFcPaisRegistro() {
		return this.fcPaisRegistro;
	}

	public void setFcPaisRegistro(String fcPaisRegistro) {
		this.fcPaisRegistro = fcPaisRegistro;
	}

	public String getFcPatrocinioBackground() {
		return this.fcPatrocinioBackground;
	}

	public void setFcPatrocinioBackground(String fcPatrocinioBackground) {
		this.fcPatrocinioBackground = fcPatrocinioBackground;
	}

	public String getFcPatrocinioColorTexto() {
		return this.fcPatrocinioColorTexto;
	}

	public void setFcPatrocinioColorTexto(String fcPatrocinioColorTexto) {
		this.fcPatrocinioColorTexto = fcPatrocinioColorTexto;
	}

	public String getFcPcode() {
		return this.fcPcode;
	}

	public void setFcPcode(String fcPcode) {
		this.fcPcode = fcPcode;
	}

	public String getFcPlaceGallery() {
		return this.fcPlaceGallery;
	}

	public void setFcPlaceGallery(String fcPlaceGallery) {
		this.fcPlaceGallery = fcPlaceGallery;
	}

	public String getFcSourceVideo() {
		return this.fcSourceVideo;
	}

	public void setFcSourceVideo(String fcSourceVideo) {
		this.fcSourceVideo = fcSourceVideo;
	}

	public String getFcTags() {
		return this.fcTags;
	}

	public void setFcTags(String fcTags) {
		this.fcTags = fcTags;
	}

	public String getFcTitulo() {
		return this.fcTitulo;
	}

	public void setFcTitulo(String fcTitulo) {
		this.fcTitulo = fcTitulo;
	}

	public String getFcVideoYoutube() {
		return this.fcVideoYoutube;
	}

	public void setFcVideoYoutube(String fcVideoYoutube) {
		this.fcVideoYoutube = fcVideoYoutube;
	}

	public String getFdFechaModificacion() {
		return this.fdFechaModificacion;
	}

	public void setFdFechaModificacion(String fdFechaModificacion) {
		this.fdFechaModificacion = fdFechaModificacion;
	}

	public String getFdFechaPublicacion() {
		return this.fdFechaPublicacion;
	}

	public void setFdFechaPublicacion(String fdFechaPublicacion) {
		this.fdFechaPublicacion = fdFechaPublicacion;
	}

	public int getFiBanInfinitoHome() {
		return this.fiBanInfinitoHome;
	}

	public void setFiBanInfinitoHome(int fiBanInfinitoHome) {
		this.fiBanInfinitoHome = fiBanInfinitoHome;
	}

	public int getFiBanNoTeLoPierdas() {
		return this.fiBanNoTeLoPierdas;
	}

	public void setFiBanNoTeLoPierdas(int fiBanNoTeLoPierdas) {
		this.fiBanNoTeLoPierdas = fiBanNoTeLoPierdas;
	}

	public int getFiBanPatrocinio() {
		return this.fiBanPatrocinio;
	}

	public void setFiBanPatrocinio(int fiBanPatrocinio) {
		this.fiBanPatrocinio = fiBanPatrocinio;
	}

	public int getFiBanVideoViral() {
		return this.fiBanVideoViral;
	}

	public void setFiBanVideoViral(int fiBanVideoViral) {
		this.fiBanVideoViral = fiBanVideoViral;
	}

}