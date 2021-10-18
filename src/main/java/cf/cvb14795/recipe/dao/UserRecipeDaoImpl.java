package cf.cvb14795.recipe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.recipe.model.UserRecipeBean;



@Repository
public class UserRecipeDaoImpl implements IUserRecipeDao {

	SessionFactory factory;

	@Autowired
	public UserRecipeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void insert(UserRecipeBean uRecipe) {
		Session session = factory.getCurrentSession();
		session.save(uRecipe);
	}

	@Override
	public List<UserRecipeBean> findMembersRecipe() {
		Session session = factory.getCurrentSession();
		String hql = "From UserRecipeBean";
		List<UserRecipeBean> list = session.createQuery(hql,UserRecipeBean.class).getResultList();
		
		return list;
	}

}
