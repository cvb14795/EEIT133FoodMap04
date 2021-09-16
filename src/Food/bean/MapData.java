package Food.bean;

import java.io.Serializable;

public class MapData implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mapDataId;
	private String mapname;
	private String mapku;
	private String mapnb;
	private String mapxy;
	private String mapcheck;
	private String imgName;

	public MapData() {
	}

	public MapData(String mapname, String mapku, String mapnb, String mapxy, String mapcheck, String imgName) {
		super();
		this.mapname = mapname;
		this.mapku = mapku;
		this.mapnb = mapnb;
		this.mapxy = mapxy;
		this.mapcheck = mapcheck;
		this.imgName = imgName;
	}

	public int getMapDataId() {
		return mapDataId;
	}

	public void setMapDataId(int mapDataId) {
		this.mapDataId = mapDataId;
	}

	public String getMapname() {
		return mapname;
	}

	public void setMapname(String mapname) {
		this.mapname = mapname;
	}

	public String getMapku() {
		return mapku;
	}

	public void setMapku(String mapku) {
		this.mapku = mapku;
	}

	public String getMapnb() {
		return mapnb;
	}

	public void setMapnb(String mapnb) {
		this.mapnb = mapnb;
	}

	public String getMapxy() {
		return mapxy;
	}

	public void setMapxy(String mapxy) {
		this.mapxy = mapxy;
	}

	public String getMapcheck() {
		return mapcheck;
	}

	public void setMapcheck(String mapcheck) {
		this.mapcheck = mapcheck;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

}
