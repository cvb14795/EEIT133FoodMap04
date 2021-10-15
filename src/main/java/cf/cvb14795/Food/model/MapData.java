package cf.cvb14795.Food.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "MapData")
public class MapData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mapDataId;
	
	@Column(name = "mapname")
	private String mapname;
	
	@Column(name = "mapku")
	private String mapku;
	
	@Column(name = "mapnb")
	private String mapnb;
	
	@Column(name = "mapxy")
	private String mapxy;
	
	@Column(name = "mapcheck")
	private String mapcheck;
	
	@Column(name = "img")
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
