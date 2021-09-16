package Coupon.model;

public class QuestionnaireDAOFactor {
	private static final QuestionnaireDAO QUESTIONNAIRE_DAO = createQuestionnaireDAO();

	private static QuestionnaireDAO createQuestionnaireDAO() {
		QuestionnaireDAO QuestionnaireDAO = new QuestionnaireDAO();
		return QuestionnaireDAO;

	}

	public static QuestionnaireDAO getQuestionnaireDAO() {
		return QUESTIONNAIRE_DAO;
	}

}
