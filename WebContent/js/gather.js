$(document).ready(function(){

	/*10行星星*/
	var oPf = document.querySelectorAll('.score');
	var aLi = new Array(oPf.length);
	
	function getScore(){
		var i=0;
		for(;i<oPf.length;i++){
			(function(i){
				aLi[i] = oPf[i].getElementsByTagName('li');
				for(var k=0;k<aLi[i].length;k++){
					aLi[i][k].index=k;
					aLi[i][k].onmouseover=function(){
						for(k=0;k<aLi[i].length;k++){
							if(k<=this.index)
							{
								
								aLi[i][k].style.background="url(../images/star.gif) no-repeat 0 -29px";
							}
							else
							{
								aLi[i][k].style.background="url(../images/star.gif) no-repeat 0 0";
							}
						}
					};
					
					aLi[i][k].onmousedown=function ()
					{
						window.open('teacher.jsp','_self');
					};
				}
			})(i);
			
		}
	}
	getScore();
	/*全选*/
	var oTable = document.getElementById('send-list');
	var oBtnSelectAll = oTable.getElementsByTagName('input')[0];
	oBtnSelectAll.onclick=function (){
		selectAll();
	}
	var btndownloadG = document.getElementById('downloadG');
	var tdInput = document.querySelector('.one-line');
	var check = tdInput.getElementsByTagName('input');
	
	btndownloadG.onclick = function(){
		var i=0;var count = 0;
		for(;i<check.length;i++){
			(function(i){
				if(check[i].checked==false){
					count++;
				}
			})(i);
		}
		if(count==check.length){
			alert("您未选中任何下载内容！")
			return false;
		}else{
			return true;
		}
	}
	/*下拉列表*/
	var oDiv=document.getElementById('drop');
	var oH5=oDiv.getElementsByTagName('h5');
	var oUl=oDiv.getElementsByTagName('ul');
	
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
})

function selectAll(){
	var oTable = document.getElementById('send-list');
	var oBtnSelectAll = oTable.getElementsByTagName('input')[0];
	var aInputs = oTable.tBodies[0].getElementsByTagName('input');
	
	var i=0;
	
	for(i=0;i<aInputs.length;i++)
	{
		if(aInputs[i].type=='checkbox')
		{
			aInputs[i].checked=oBtnSelectAll.checked;
		}
	}
}

function openPage(i, tkcodes, status) {
	window.location.href='gather.jsp?page_count=' + i + '&tkcodes=' + tkcodes + '&status=' + status;
}




