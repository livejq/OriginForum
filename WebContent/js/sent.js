$(document).ready(function(){
	var btnSent = document.getElementById('btnSent');
	var sentNull = document.getElementById('sentNull');
	var uploadtk = document.getElementById('uploadtk');
	uploadtk.onchange = function(){
			var oP = document.getElementById('fileName');
			var len = uploadtk.files.length;
			var temp = "";
			for(var i = 0; i < len; i++){
				if(len>1){
					if(len==i+1)
						temp += uploadtk.files[i].name;
					else{
						temp += uploadtk.files[i].name+"、";
					}
				}else{
					temp += uploadtk.files[i].name;
				}
			}
			oP.innerHTML = temp;
			oP.style.display = "block";
		}
		
		btnSent.onclick = function(){
			if(uploadtk.files[0]==null){
				alert("您还没有选择文件！");
				return false;
			}
			if(sentNull.selected==true){
				alert("您还没有确定作业码！");
				return false;
			}
			var size = uploadtk.files[0].size/(1024*1024);
			if(size>100){
	    		alert("您的文件大小已超过100MB!");
	    		return false;
	    	}
		}
		uploadtk.addEventListener('change',function(){
			var size = uploadtk.files[0].size/(1024*1024);
	    	if(size>100){
	    		alert("您的文件大小已超过100MB!");
	    	}
	    },false);
})