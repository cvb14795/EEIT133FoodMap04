package model;

import org.hibernate.Session;

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

}
