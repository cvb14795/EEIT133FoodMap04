package recipe.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RecipeBeanDao implements IRecipeBeanDao {
	private Session session;
	
	public RecipeBeanDao(Session session) {
		this.session = session;
	}

	@Override
	public RecipeBean insert(RecipeBean recipe) {
		
		RecipeBean rBean = new RecipeBean();
		rBean.setName(recipe.getName());
		rBean.setCategory(recipe.getCategory());
		rBean.setFood1(recipe.getFood1());
		rBean.setFood2(recipe.getFood2());
		rBean.setFood3(recipe.getFood3());
		rBean.setFood4(recipe.getFood4());
		rBean.setSauce1(recipe.getSauce1());
		rBean.setSauce2(recipe.getSauce2());
		rBean.setSauce3(recipe.getSauce3());
		rBean.setFile1(recipe.getPhoto());
		session.save(rBean);
		
		return recipe;
		
	}

	@Override
	public List<RecipeBean> selectAll() {
		Query<RecipeBean> query = session.createQuery("from RecipeBean",RecipeBean.class);
		return query.list();
	}

	@Override
	public RecipeBean update(int id, String name, String category, String food1, String food2, String food3,
			String food4, String sauce1, String sauce2, String sauce3) {
		
		RecipeBean resultBean = session.get(RecipeBean.class, id);
		
		if (resultBean != null) {
			resultBean.setName(name);
			resultBean.setCategory(category);
			resultBean.setFood1(food1);
			resultBean.setFood2(food2);
			resultBean.setFood3(food3);
			resultBean.setFood4(food4);
			resultBean.setSauce1(sauce1);
			resultBean.setSauce2(sauce2);
			resultBean.setSauce3(sauce3);
		}
		return resultBean;
		
	}

	@Override
	public boolean deleteById(int id) {
		RecipeBean resultBean = session.get(RecipeBean.class, id);
		
		if (resultBean != null) {
			session.delete(resultBean);
			return true;
		}
		
		return false;
	}

}
