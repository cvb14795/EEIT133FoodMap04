function contentInit(){
	$(".aboutUs").on("click", function(){
	    var $contact = $("#contact");
        console.log("aboutUs");
        $contact.addClass("active");
        $contact.removeClass("close");
		$contact.show();
	})
	
	$("#close").on('click', function() {
		// 去除已點擊過的ID(contant)
		location.hash = '';
		$("#contact").hide();
	});
}