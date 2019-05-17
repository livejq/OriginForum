$(document).ready(function(){
	 var oUl = document.getElementById('upload');
		oUl.onchange = function(){
			var oP = document.getElementById('fileName');
			var len = oUl.files.length;
			var temp = "";
			for(var i = 0; i < len; i++){
				if(len>1){
					if(len==i+1)
						temp += oUl.files[i].name;
					else{
						temp += oUl.files[i].name+"、";
					}
				}else{
					temp += oUl.files[i].name;
				}
			}
			oP.innerHTML = temp;
			oP.style.display = "block";
		}
		
	function removeAll(){
	     var oClass=document.getElementById('s-class');
	     oClass.options.length=1;
	}
		
	$.get('../json/major.json', function(data) {
		for (var i in data) {
			$("#s-major").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
		};
	});
	
	function updateClass(){
		var selectedMajor = $("#s-major").val();
		function getMajorID(majorName, cb) {
			var majorID = null;
			$.get('../json/major.json', function(data) {
				for (var i in data) {
					if(majorName == data[i].name){
						majorID = data[i].MajorID;
						cb(majorID);
					};
				};
			});
		}
		getMajorID(selectedMajor, function(majorID) {
			$.get('../json/class.json', function(data) {
				for (var i in data) {
					if(data[i].MajorID == majorID) {
						$('#s-class').append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
					}
				};
			});
		})
	}

	$('#s-major').change(function(event) {
		removeAll();
		updateClass();
		})

		
	var taskTitle = document.getElementById('taskTitle');
	var classNull = document.getElementById('classNull');
	var upload = document.getElementById('upload');
	var btnPublish = document.getElementById('btnPublish');
	var deadline = document.getElementById('deadline');
	btnPublish.onclick = function(){
		if(taskTitle.value==null||taskTitle.value==""||classNull.selected==true||deadline.value==""||deadline.value==null){
			alert("请将作业标题、班级或期限填写完整！");
			return false;
		}
		if(/^[0-9]+$/.test(deadline.value)){
			return true;
		}else{
			alert("期限必须是数字！");
			return false;
		}
		
		var size = upload.files[0].size/(1024*1024);
		if(size>100){
    		alert("您的文件大小已超过100MB!");
    		return false;
    	}
	}
	upload.addEventListener('change',function(){
		var size = upload.files[0].size/(1024*1024);
    	if(size>100){
    		alert("您的文件大小已超过100MB!");
    	}
    },false);

})
/**
 * 
 * function addFile(){
		var fragment = document.createDocumentFragment();
		var divNode = document.getElementById("container");
		 
		var newDiv = document.createElement("div");
		newDiv.setAttribute("id","file");
		fragment.appendChild(newDiv);
		 
		var newInput = document.createElement("input");
		newInput.setAttribute("type","file");
		newInput.setAttribute("name","upLoadFile");
		newDiv.appendChild(newInput);
		 
		var newInput=document.createElement("a");
		newInput.href = "javascript:delFile();";
		newInput.innerHTML = "移除";
		newDiv.appendChild(newInput);
		 
		divNode.appendChild(fragment);
		}
	function delFile(){
		var divNode=document.getElementById("container");
		divNode.removeChild(divNode.firstElementChild);
	}
 */


/*
 * 
 * var oUl = document.getElementById('upload');
	oUl.onchange = function(){
		var oP = document.getElementById('fileName');
		var len = oUl.files.length;
		var temp = "";
		for(var i = 0; i < len; i++){
			if(len>1){
				if(len==i+1)
					temp += oUl.files[i].name;
				else{
					temp += oUl.files[i].name+"、";
				}
			}else{
				temp += oUl.files[i].name;
			}
		}
		console.log(temp);
		oP.innerHTML = temp;
		oP.style.display = "block";
	}*/

/*点击下拉改变背景*/
/*    document.getElementById('select-btn').addEventListener('click',function(){
        selectView[2].selected = true;          
    });
    
    *
    *
    *var selectView1 = document.getElementById('s-major');
    for (var i = 0; i < 3; i++) {
        selectView1[i] = new Option('name' + i, 'value' + i);
    }
    *
    *
    *
    *
    *
    *
    *
    *var oSelectM = document.querySelector('.top-m select');
	var oOptionM = oSelectM.getElementsByTagName('option');
	var isup = true;
	oSelectM.onclick = function(){
		if(isup){
			oSelectM.style.background="url(images/down.gif) no-repeat 130px";
			isup = false;
		}
		else{
			oSelectM.style.background="url(images/up.gif) no-repeat 130px";
			isup = true;
		}
		
	}
	var oSelectC = document.querySelector('.top-c select');
	var oOptionC = oSelectM.getElementsByTagName('option');
	var isup2 = true;
	oSelectC.onclick = function(){
		if(isup2){
			oSelectC.style.background="url(images/down.gif) no-repeat 130px";
			isup2 = false;
		}
		else{
			oSelectC.style.background="url(images/up.gif) no-repeat 130px";
			isup2 = true;
		}
		
	}
    */
		

