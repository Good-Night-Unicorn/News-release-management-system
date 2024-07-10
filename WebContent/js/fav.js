$(function() {

$("#usersid").blur(
		function() {
			$("#usersid_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#usersid").after("<span id='usersid_msg' style='color: red'>用户不能为空</span>");
			}
	});
$("#articleid").blur(
		function() {
			$("#articleid_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#articleid").after("<span id='articleid_msg' style='color: red'>新闻不能为空</span>");
			}
	});






$('#sub').click(function(){
var usersid = $("#usersid").val();
var articleid = $("#articleid").val();
$("#usersid_msg").empty();
$("#articleid_msg").empty();
if (usersid == "" || usersid == null) {
				$("#usersid").after("<span id='usersid_msg' style='color: red'>用户不能为空</span>");
	return false;
}
if (articleid == "" || articleid == null) {
				$("#articleid").after("<span id='articleid_msg' style='color: red'>新闻不能为空</span>");
	return false;
}
});
$('#res').click(function() {
$("#usersid_msg").empty();
$("#articleid_msg").empty();
});

});
