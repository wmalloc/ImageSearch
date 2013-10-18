package net.crimsonresearch.imagesearch;

import java.io.Serializable;

public class SearchSettings implements Serializable {
	private static final long serialVersionUID = 3251179796912699242L;
	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String siteFilter;
	
	public SearchSettings () {
		super();
		imageSize = "";
		colorFilter = "";
		imageType = "";
		siteFilter = "";
	}
	
	public String getImageSize() {
		return imageSize;
	}
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	public String getColorFilter() {
		return colorFilter;
	}
	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}
	
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getSiteFilter() {
		return siteFilter;
	}
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}
}
