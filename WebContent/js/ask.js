window.onload = function(){
	var btnAsk = document.getElementById('btnPublish');
	var	inputTitle = document.getElementById('txtTitle');
	var selectNull = document.getElementById('null');
	btnAsk.onclick = function(){
		if(inputTitle.value==null||inputTitle.value==""||selectNull.selected==true){
			alert("请将问题标题或类型填写完整！");
			return false;
		}
	}
}

		

