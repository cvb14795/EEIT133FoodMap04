package cf.cvb14795.recipe.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cf.cvb14795.member.bean.Member;

@Entity
@Table(name="myFavorites")
public class MyFavoritesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int favoriteId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userAccount")
	private Member userAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private AdminRecipeBean aRecipeId;
	
	public MyFavoritesBean() {
	}

	public MyFavoritesBean(Member userAccount, AdminRecipeBean aRecipeId) {
		this.userAccount = userAccount;
		this.aRecipeId = aRecipeId;
	}

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Member getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Member userAccount) {
		this.userAccount = userAccount;
	}

	public AdminRecipeBean getaRecipeId() {
		return aRecipeId;
	}

	public void setaRecipeId(AdminRecipeBean aRecipeId) {
		this.aRecipeId = aRecipeId;
	}

	@Override
	public String toString() {
		return "MyFavoritesBean [favoriteId=" + favoriteId + ", userAccount=" + userAccount + ", aRecipeId=" + aRecipeId.getId()
				+ "]";
	}

	
	
}
