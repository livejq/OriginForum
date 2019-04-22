window.onload = function(){
	/*下拉列表*/
	var oDshow = document.getElementById('drop-in-show');
	var oH5 = oDshow.getElementsByTagName('h5');
	var oUl = oDshow.getElementsByTagName('ul');
	
	for(var i=0;i<oH5.length;i++){
		oH5[i].index = i;
		oH5[i].onclick = function(){
			if(oUl[this.index].style.display==='none'){
				oUl[this.index].style.display='block';
			}else{
				oUl[this.index].style.display='none';
			}
		}
	}
	
}
