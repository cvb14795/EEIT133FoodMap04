package cf.cvb14795.Food.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MapData")
public class MapDataBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mapname;
	private String mapku;
	private String mapnb;
	private String mapxy;
	private String mapcheck;
	private Blob img;
	private Integer FK_CompanyBean_Id;
	private String FK_CompanyBean_Ck;
	private String fileName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Blob getImg() {
		return img;
	}
	public void setImg(Blob img) {
		this.img = img;
	}
	public Integer getFK_CompanyBean_Id() {
		return FK_CompanyBean_Id;
	}
	public void setFK_CompanyBean_Id(Integer fK_CompanyBean_Id) {
		FK_CompanyBean_Id = fK_CompanyBean_Id;
	}
	public String getFK_CompanyBean_Ck() {
		return FK_CompanyBean_Ck;
	}
	public void setFK_CompanyBean_Ck(String fK_CompanyBean_Ck) {
		FK_CompanyBean_Ck = fK_CompanyBean_Ck;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
