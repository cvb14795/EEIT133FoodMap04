package cf.cvb14795.recipe.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRecipeBeanDao implements IUserRecipeBeanDao {

	private Session session;

	public UserRecipeBeanDao(Session session) {
		this.session = session;
	}

	@Override
	public UserRecipeBean insert(UserRecipeBean uRecipe) {
		UserRecipeBean urBean = new UserRecipeBean();
		urBean.setUserName(uRecipe.getUserName());
		urBean.setFoodName(uRecipe.getCategory());
		urBean.setCategory(uRecipe.getFoodName());
		urBean.setFood1(uRecipe.getFood1());
		urBean.setFood2(uRecipe.getFood2());
		urBean.setFood3(uRecipe.getFood3());
		urBean.setFood4(uRecipe.getFood4());
		urBean.setSauce1(uRecipe.getSauce1());
		urBean.setSauce2(uRecipe.getSauce2());
		urBean.setSauce3(uRecipe.getSauce3());
		urBean.setPhoto(uRecipe.getPhoto());
		session.save(urBean);
		
		return uRecipe;
	}

	@Override
	public List<UserRecipeBean> findAll() {
		Query<UserRecipeBean> query = session.createQuery("From UserRecipeBean",UserRecipeBean.class);
		return query.list();
	}
	
}
