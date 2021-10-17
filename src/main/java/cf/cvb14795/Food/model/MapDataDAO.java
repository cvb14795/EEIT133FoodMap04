package cf.cvb14795.Food.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class MapDataDAO {

	private Session session;

	public MapDataDAO(Session session) {
		this.session = session;
	}

	// 新增資料

	public MapData addMapData(MapData m) {
		MapData mapData = session.get(MapData.class, m.getMapDataId());

		if (mapData == null) {
			session.save(m);
			return m;
		}
		return null;
	}

	// 修改資料 (依靠名稱)
	public List<MapData> updateName(String mapname, MapData m) {
		Query<MapData> query = session.createQuery("from MapData as a where a.mapname like " + " '%" + mapname + "%'",
				MapData.class);
		final List<MapData> list = query.list();

		if (list.size() > 0) {

			for (MapData mapData : list) {
				mapData.setMapname(m.getMapname());
				mapData.setMapku(m.getMapku());
				mapData.setMapnb(m.getMapnb());
				mapData.setMapxy(m.getMapxy());
				mapData.setMapcheck(m.getMapcheck());
				mapData.setImgName(m.getImgName());

			}
		} else {
			System.out.println("修改失敗");
		}
		return list;
	}

	// 查詢資料
	public List<MapData> findByName(String mapname) {
		Query<MapData> query = session.createQuery("from MapData as a where a.mapname like " + " '%" + mapname + "%'",
				MapData.class);
		final List<MapData> list = query.list();
		return list;
	}

	// 透過 mapname 刪除該筆資料
	public boolean deleteMapDataByname(String mapname) {
		Query<MapData> query = session.createQuery("from MapData as a where a.mapname like " + " '%" + mapname + "%'",
				MapData.class);
		final List<MapData> list = query.list();

		if (list.size() > 0) {
			for (MapData mapData : list) {
				session.delete(mapData);
			}
			return true;
		}
		return false;
	}
}
