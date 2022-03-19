function setLineOAuthUrl() {
	var url = "https://access.line.me/oauth2/v2.1/authorize?"
	url += "response_type=code"
	url += "&client_id=1656572191"
//	const redirect_uri = encodeURI("https://eeit133-foodmap04.herokuapp.com/FoodMap04/api/oauth2callback/line");
	const baseUrl = document.location.origin;
	const redirect_uri = encodeURI(baseUrl+"/FoodMap04/api/oauth2callback/line");
	url += "&redirect_uri="+redirect_uri
	url += "&state=12345abcde"
	url += "&scope=profile%20openid"
	// 檢查是否加入官方帳號好友
	url += "&bot_prompt=aggressive"
	console.log(url);
    var href = document.getElementById("lineLoginHref");
	if (href) {
		href.setAttribute("href", url);	
	}
}