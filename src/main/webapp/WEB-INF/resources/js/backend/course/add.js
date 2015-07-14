/**
 * 
 */
var uri = "/LearningCommunity/manage/category/direction/"

$(function(){
	var category_select = $("#category-select");
	var direction_select = $("#direction-select");
	loadCategory(direction_select.val(),category_select);
	direction_select.change(function(){
		loadCategory(direction_select.val(),category_select);
	});
});

function loadCategory(id , selector){
	var url = "http://" + host + uri + id + ".json";
	$.getJSON(url , function(data){
		selector.empty();
		$.each(data,function(i, category){
			var option = "<option value='" + category.categoryId + "'>" + category.categoryName + "</option>";
			selector.append(option);
		});
	});
}