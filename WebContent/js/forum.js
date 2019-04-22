window.onload = function(){
	var oBlock = document.querySelectorAll('.c-blocks a');
	var oDiv = document.querySelectorAll('.c-blocks a div');
	function doMove(){
		var i=0;
		for(;i<oBlock.length;i++){
			(function(i){
				oBlock[i].onmouseover = function(){
					oDiv[i].style.top = oDiv[i].offsetTop-30+'px';
				}
				oBlock[i].onmouseout = function(){
					oDiv[i].style.top = oDiv[i].offsetTop+30+'px'; 
				}
			})(i);
			
		}
	}
	doMove();
}

