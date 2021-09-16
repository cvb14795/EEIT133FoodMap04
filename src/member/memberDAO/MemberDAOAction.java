package member.memberDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import member.memberBean.Member;

public class MemberDAOAction {

	// 總共新增幾筆資料
	private static int insertedDataCount = 0;
	// 共用SC
	private static Scanner sc = new Scanner(System.in);
	private static MemberDAO mDAO = MemberDAOFactory.getMemberDAO();
	// 程式提供的4種功能(4組鍵對)
	// 1.新增, 2.查詢, 3.修改, 4.刪除
	private static Map<Integer, String> progFuncMap = new HashMap<Integer, String>();
	private static String errMsg;
	private static Member mResult = null;

	public static void main(String[] args) {

		// 拆成幾個不同方法
		try {
			
			/* ========== 開啟連線 ========== */
			mDAO.createConn();
			
			mDAO.closeConn();
			/* ========== 關閉連線 ========== */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("===== Something Wrong!! =====");
			e.printStackTrace();
		} finally {
			System.out.println("===== 本程式執行完畢 =====");
		}

	}
	
	private static void checkUserInput(int userChoice) {
		boolean isVaild = false;
		while (!isVaild) { 
			for (int key : progFuncMap.keySet()) {
				//"0, 1, 2, 3, 4其中之一"
				if (key == userChoice) {
					isVaild = true;
					break;
				}
			}				
			if (userChoice != -1 && isVaild) {
				System.out.println("您選擇了: " + userChoice);
				break;
			}else {
				errMsg = " 無此功能 輸入不正確 !";
				userChoice = getUserInputInt(getWelcomeMsg(), errMsg);
			}
		}
	}
	
	
	private static int getUserInputInt(String hintMsg, String errMsg) {
		boolean isInputVaild = false;
		int intInput = 0;

		do {
			System.out.println(hintMsg);

			if (sc.hasNextInt()) {
				intInput = sc.nextInt();
				isInputVaild = true;
				break;
			} else {
				System.out.println("test");
				// 讓游標切換到下一行 否則會讀不到input
				// 跑完這裡後會直接回到迴圈開頭
				sc.nextLine();
				System.out.println(errMsg);
			}
		} while (!isInputVaild);

		return intInput;
	}

	private static String getUserInput(String hintMsg, String errMsg) {
		String userInput = "";

		System.out.println(hintMsg);

		if (sc.hasNext()) {
			userInput = sc.next();
		} else {
			// 讓游標切換到下一行 否則會讀不到input
			// 跑完這裡後會直接回到迴圈開頭
			sc.nextLine();
			System.out.println(errMsg);
		}
		return userInput;
	}

	private static String getWelcomeMsg() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n==========口罩地圖查詢系統==========\n");
		sb.append("請選擇以下功能: 0.載入CSV檔案資料至資料庫, 1.新增, 2.查詢ID並展示地圖, 3.修改, 4.刪除, 或輸入-1離開程式\n");
		sb.append("等待使用者輸入中..");
		return sb.toString();
	}

	private static Member findSqlDataByUserInput(MemberDAO mDAO) throws SQLException {
		// 接收查詢ID的mPos回傳結果
		Member mResult = null;
		int id = getUserInputInt("\n請輸入要查詢的ID:", "請輸入正確的ID格式(僅能輸入非負整數且不可為0)!");
		/* =====舊方法===== */
//		// 預設回傳第一筆資料
//		int id = 0;
//		boolean checkOK = false;
//		
//		do {
//			System.out.println("\n請輸入要查詢的ID:");
//			// id若=0則不給查詢 因為資料庫的ID從1開始
//			if (sc.hasNextInt()) {
//				id = sc.nextInt();
//				checkOK = true;
//			}else {
//				// 讓游標切換到下一行 否則會讀不到input
//				// 跑完這裡後會直接回到迴圈開頭
//				sc.nextLine();
//				System.out.println("請輸入正確的ID格式(僅能輸入非負整數且不可為0)!");					
//			}
//		}while (!checkOK);
		/* =====舊方法===== */

		// 印出查詢到的資料之所有欄位資訊
//		mResult = mDAO.findById(id);
		System.out.println("\n查詢完畢，結果為:");

		return mResult;
	}
}
