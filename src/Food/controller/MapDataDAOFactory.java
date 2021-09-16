package Food.controller;

public class MapDataDAOFactory {
	
	// singleton + factory design pattern (單例 + 工廠模式)
	
	private static final MapDataDAO MAPDATA_DAO = createMapDataDAO();
	
	private static MapDataDAO createMapDataDAO() {
		MapDataDAO mDAO = new MapDataDAO();
		return mDAO;
	}
	
	public static MapDataDAO getMapDataDAO() {
		return MAPDATA_DAO;
	}
	

}
