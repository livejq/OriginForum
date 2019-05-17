$(document).ready(function(){
	var oBlock = document.querySelectorAll('#stay-time a');
	var oStay = document.querySelector('.stay');
	var index = 0;var lastindex = 0;
	function stayHere(){
		var i=0;
		for(;i<oBlock.length;i++){
			(function(i){
				oBlock[i].onclick = function(){
					index = i;
					oBlock[lastindex].className ='';
					oBlock[index].className = 'stay';
					lastindex = index;
				}
			})(i);
			
		}
	}
})

