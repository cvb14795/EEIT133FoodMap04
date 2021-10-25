package cf.cvb14795.recipe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.recipe.model.AdminRecipeBean;


@Repository
public class AdminRecipeDaoImpl implements IAdminRecipeDao {
//	private Session session;
	SessionFactory factory;
	
	@Autowired
	public AdminRecipeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

//	public RecipeBeanDaoImpl(Session session) {
//		this.session = session;
//	}

	@Override
	public void insert(AdminRecipeBean recipe) {
		
		Session session = factory.getCurrentSession();
		session.save(recipe);
		
//		RecipeBean rBean = new RecipeBean();
//		rBean.setName(recipe.getName());
//		rBean.setCategory(recipe.getCategory());
//		rBean.setFood1(recipe.getFood1());
//		rBean.setFood2(recipe.getFood2());
//		rBean.setFood3(recipe.getFood3());
//		rBean.setFood4(recipe.getFood4());
//		rBean.setSauce1(recipe.getSauce1());
//		rBean.setSauce2(recipe.getSauce2());
//		rBean.setSauce3(recipe.getSauce3());
//		rBean.setFile1(recipe.getPhoto());
//		session.save(rBean);
	}

	@Override
	public List<AdminRecipeBean> selectAll() {
//		Query<RecipeBean> query = session.createQuery("from RecipeBean", RecipeBean.class);
//		return query.list();
		Session session = factory.getCurrentSession();
		String hql = "From AdminRecipeBean";
		List<AdminRecipeBean> list = session.createQuery(hql,AdminRecipeBean.class).getResultList();
		
		return list;
	}
	
	@Override
	public AdminRecipeBean getUpdateId(int id) {

//		RecipeBean resultBean = session.get(RecipeBean.class, id);
//
//		if (resultBean != null) {
//			resultBean.setName(name);
//			resultBean.setCategory(category);
//			resultBean.setFood1(food1);
//			resultBean.setFood2(food2);
//			resultBean.setFood3(food3);
//			resultBean.setFood4(food4);
//			resultBean.setSauce1(sauce1);
//			resultBean.setSauce2(sauce2);
//			resultBean.setSauce3(sauce3);
//		}
//		return resultBean;
		Session session = factory.getCurrentSession();
		AdminRecipeBean updateBean = session.get(AdminRecipeBean.class, id);
//		session.saveOrUpdate(id);
		return updateBean;
		

	}

	@Override
	public void saveRecipe(AdminRecipeBean adminRecipe) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(adminRecipe);
		
	}
	
	@Override
	public void deleteById(int id) {
//		RecipeBean resultBean = session.get(RecipeBean.class, id);
//
//		if (resultBean != null) {
//			session.delete(resultBean);
//			return true;
//		}
//
//		return false;
		Session session = factory.getCurrentSession();
		AdminRecipeBean rb = session.get(AdminRecipeBean.class, id);
		
		session.delete(rb);
		
	}

}
