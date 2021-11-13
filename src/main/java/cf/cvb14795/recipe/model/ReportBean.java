package cf.cvb14795.recipe.model;

import java.io.Serializable;

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
@Table(name = "report")
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reportId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private UserRecipeBean userRecipe;

//	@GenericGenerator(
//		name = "generator", strategy = "foreign",
//		parameters = @Parameter(name = "property", value = "member")
//	)
//	@GeneratedValue(generator = "generator")
//	@Column
//	private String memberAccount;
	
	public ReportBean() {
	}

	public ReportBean(Member member, UserRecipeBean userRecipe) {
		this.member = member;
		this.userRecipe = userRecipe;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public UserRecipeBean getUserRecipe() {
		return userRecipe;
	}

	public void setUserRecipe(UserRecipeBean userRecipe) {
		this.userRecipe = userRecipe;
	}

	@Override
	public String toString() {
		return "ReportBean [reportId=" + reportId + ", member=" + member + ", userRecipe=" + userRecipe.getId() + "]";
	}

}
