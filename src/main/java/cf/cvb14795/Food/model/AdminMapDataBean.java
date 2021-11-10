package cf.cvb14795.Food.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MapData")
public class AdminMapDataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "MAPNAME")
	private String mapname;
	
	@Column(name = "MAPKU")
	private String mapku;
	
	@Column(name = "MAPNB")
	private String mapnb;
	
	@Column(name = "MAPXY")
	private String mapxy;
	
	@Column(name = "MAPCHECK")
	private String mapcheck;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "FILENAME")
	private byte[] filename;
	
	@Transient
	private String base64String;
	
	@Override
	public String toString() {
		return "MapDataBean [id=" + id + ", mapname=" + mapname + ", mapku=" + mapku + ", mapnb=" + mapnb + ", mapxy="
				+ mapxy + ", mapcheck=" + mapcheck + ", category=" + category +",  filename=" + Arrays.toString(filename) 
				+ "]";
	}
	
	public AdminMapDataBean() {
	}
	
	public AdminMapDataBean(String mapname, String mapku, String mapnb, String mapxy, String mapcheck, String category
							,byte[] filename, String base64String) {
		this.mapname = mapname;
		this.mapku = mapku;
		this.mapnb = mapnb;
		this.mapxy = mapxy;
		this.mapcheck = mapcheck;
		this.category = category;
		this.filename = filename;
		this.base64String = base64String;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getFilename() {
		return filename;
	}

	public void setFilename(byte[] filename) {
		this.filename = filename;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
}
