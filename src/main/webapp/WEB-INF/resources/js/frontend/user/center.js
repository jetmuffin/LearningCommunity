/**
 * 
 */

$(function(){
	
	$.getJSON('/LearningCommunity/user/record/'+uid,function(data){
		console.log(data);
		var calendar = new Calendar();
		calendar.init(data);
	});
});

var Calendar = function(){
	this.container = $('#calendar');
}

Calendar.prototype = {
	init: function(data){
		var that = this;
		console.log(this.container);
		for(var i = 0; i < data.length; i++){
			console.log(data[i]);
			var li = document.createElement("li");
			var data_title = "+"+data[i].integral+"积分<br>" + data[i].date;
			$(li).addClass('rect').attr("data-title",data_title);
			if(data[i].integral > 0)
				$(li).addClass('bg-blue').css("opacity",data[i].integral/10);
			else
				$(li).addClass('bg-gray');
			$(li).appendTo(this.container);					
		}
		this.container.children('li').bind({
			'mouseover':function(){
				var data_title = $(this).attr("data-title");
				var top = $(this).position().top;
				var left = $(this).position().left;
				console.log(top,left);
				that._createTooltip(data_title, top, left);
			},
			'mouseout':function(){
				that._destoryTooltip();
			}
		});
	},

	_createTooltip:function(msg, top, left){
		var tooltip = $('<div class="tooltip fade bottom in" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>');
		tooltip.children('.tooltip-inner').html(msg);
		tooltip.css("left",left-30+"px");
		tooltip.css("top",top+15+"px");
		tooltip.appendTo($('body'));
	},

	_destoryTooltip:function(){
		$('.tooltip').remove();
	}
}