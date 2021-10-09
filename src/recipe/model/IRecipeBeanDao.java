package recipe.model;

import java.util.List;


public interface IRecipeBeanDao {
	public RecipeBean insert(RecipeBean recipe);

	public List<RecipeBean> selectAll();

	public RecipeBean update(int id, String name, String category, String food1, String food2, String food3, String food4,
			String sauce1, String sauce2, String sauce3);
	
	public boolean deleteById(int id);
}
