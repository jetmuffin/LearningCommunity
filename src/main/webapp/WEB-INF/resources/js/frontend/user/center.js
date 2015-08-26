/**
 * 
 */

var red = "#bf616a",
        blue = "#5B90BF",
        orange = "#d08770",
        yellow = "#ebcb8b",
        green = "#a3be8c",
        teal = "#96b5b4",
        pale_blue = "#8fa1b3",
        purple = "#b48ead",
        brown = "#ab7967";

        $id = function(id){
            return document.getElementById(id);
        };
        
$(function(){
	
	$.getJSON('/LearningCommunity/user/record/'+uid,function(data){
		var calendar = new Calendar();
		calendar.init(data);
	});
	
	var canvas = $id('modular-doughnut'),
    colours = {
        "Core": blue,
        "Line": orange,
        "Bar": teal,
        "Polar Area": purple,
        "Radar": brown,
        "Doughnut": green
    },
    helpers = Chart.helpers;


var data = [
    {
        value: 7.57,
        label: "Core"
    },

    {
        value: 1.63,
        label: "Bar"
    },

    {
        value: 1.09,
        label: "Doughnut"
    },

    {
        value: 1.71,
        label: "Radar"
    },

    {
        value: 1.64,
        label: "Line"
    },

    {
        value: 1.37,
        label: "Polar Area"
    }
]
var colors = getArrayItems(colours,data.length);
for(var i = 0; i < data.length; i++){
    data[i].color = colors[i];
    data[i].highlight = Colour(colors[i],10);
}

// 
var moduleDoughnut = new Chart(canvas.getContext('2d')).Doughnut(data, { tooltipTemplate : "<%if (label){%><%=label%>: <%}%><%= value %>kb", animation: false });
// 
var legendHolder = document.createElement('div');
legendHolder.innerHTML = moduleDoughnut.generateLegend();
helpers.each(legendHolder.firstChild.childNodes, function(legendNode, index){
    helpers.addEvent(legendNode, 'mouseover', function(){
        var activeSegment = moduleDoughnut.segments[index];
        activeSegment.save();
        activeSegment.fillColor = activeSegment.highlightColor;
        moduleDoughnut.showTooltip([activeSegment]);
        activeSegment.restore();
    });
});
helpers.addEvent(legendHolder.firstChild, 'mouseout', function(){
    moduleDoughnut.draw();
});
canvas.parentNode.parentNode.appendChild(legendHolder.firstChild);
});

var Calendar = function(){
	this.container = $('#calendar');
}

function Colour(col, amt) {
    var usePound = false;
    if (col[0] == "#") {
        col = col.slice(1);
        usePound = true;
    }
    var num = parseInt(col,16);
    var r = (num >> 16) + amt;
    if (r > 255) r = 255;
    else if  (r < 0) r = 0;
    var b = ((num >> 8) & 0x00FF) + amt;
    if (b > 255) b = 255;
    else if  (b < 0) b = 0;
    var g = (num & 0x0000FF) + amt;
    if (g > 255) g = 255;
    else if (g < 0) g = 0;
    return (usePound?"#":"") + (g | (b << 8) | (r << 16)).toString(16);

}

function getArrayItems(arr, num) {
    var temp_array = new Array();
    for (var index in arr) {
        temp_array.push(arr[index]);
    }
    var return_array = new Array();
    for (var i = 0; i<num; i++) {
        if (temp_array.length>0) {
            var arrIndex = Math.floor(Math.random()*temp_array.length);
            return_array[i] = temp_array[arrIndex];
            temp_array.splice(arrIndex, 1);
        } else {
            break;
        }
    }
    return return_array;
}

Calendar.prototype = {
	init: function(data){
		var that = this;
		for(var i = 0; i < data.length; i++){
			console.log(data[i]);
			var li = document.createElement("li");
			var data_title = "+"+data[i].integral+"积分<br>" + data[i].date;
			$(li).addClass('rect').attr("data-title",data_title);
			if(data[i].integral > 0)
				$(li).addClass('bg-blue').css("opacity",data[i].integral/30);
			else
				$(li).addClass('bg-gray');
			$(li).appendTo(this.container);					
		}
		this.container.children('li').bind({
			'mouseover':function(){
				var data_title = $(this).attr("data-title");
				var top = $(this).position().top;
				var left = $(this).position().left;
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