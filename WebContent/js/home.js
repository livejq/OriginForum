window.onload=function(){
	/*鼠标停留用户头像延时显示下拉列表*/
	var oUser = document.querySelector(".user");
	var oUmsg = document.getElementById("user-msg");
	var timer = null;

	oUser.onmouseover=oUmsg.onmouseover=function (){
		clearInterval(timer);
		setTimeout(function (){
			oUmsg.style.display='block';
		}, 300);
	};
	oUser.onmouseout=oUmsg.onmouseout=function (){
		clearInterval(timer);
		timer=setTimeout(function (){
			oUmsg.style.display='none';
		}, 300);
	};
	
	/*adv定时切换*/
	var oRbtn = document.querySelector(".btn-right");
	var oLbtn = document.querySelector(".btn-left");
	var oTabs = document.querySelectorAll(".tab li");
	var oImages =  document.querySelectorAll(".ban-img img");
	var index = 0;var lastindex = 0;var advSpeed = 6000;

	oLbtn.onclick = function(){
		oImages[lastindex].className = "";
		oTabs[lastindex].className = "";
		if(index==0)index=oImages.length-1;
		else index--;
		oImages[index].className = "on";
		oTabs[index].className = "on";
		lastindex = index;
	}
	var scrollForward = function(){
		oImages[lastindex].className = "";
		oTabs[lastindex].className = "";
		index++;
		index %= oImages.length;
		oImages[index].className = "on";
		oTabs[index].className = "on";
		lastindex = index;
	}
	oRbtn.onclick = scrollForward;
	setInterval(scrollForward,advSpeed);
	/*选项卡式下划线切换*/
	var oImgs = document.querySelectorAll(".card img");
	var oLine = document.querySelector(".line");
	var oFirst = document.getElementById("showFirst");
	var oBoxs = document.querySelectorAll(".box-content div");
	oLine.style.left = oFirst.offsetLeft-10+"px";
	var pointer = 0;var lastpointer = 0;
	var array;
	
	function doMove(){
		var i=0;
		for(;i<oImgs.length;i++){
			(function(i){
				oImgs[i].onmouseover = function(){
					oLine.style.left = oImgs[i].offsetLeft-10+"px";
					lastpointer = pointer;
					pointer = i;
					oImgs[lastpointer].className = "";
					oBoxs[lastpointer].className = "";
					oBoxs[lastpointer].style.zIndex = -1;
					oImgs[pointer].className = "active";
					oBoxs[pointer].className = "alive";
					oBoxs[pointer].style.zIndex = 100;
				}
			})(i);
		}
	}
	doMove();
	function getPosition(){
		var oActive = document.querySelector("#content .active");
		oLine.style.left = oActive.offsetLeft-10+"px";
	}
	setInterval(getPosition,700);
	/*回到顶部*/
	var oBtn = document.getElementById('returnTop');
	var bSys = true;
	var timer = null;
	var scrollTop = 0;
	oBtn.style.display = "none";
	/*如何检测用户拖动了滚动条*/
	window.onscroll=function (){
		if(!bSys){
			clearInterval(timer);
		}
		bSys=false;
		scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
		if(scrollTop>=600){
			oBtn.style.display = "block";
		}else if(scrollTop<=100){
			oBtn.style.display = "none";
		}
	};

	oBtn.onclick=function (){
		timer=setInterval(function (){
			scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
			var iSpeed=Math.floor(-scrollTop/6);
			
			if(scrollTop==0)
			{
				clearInterval(timer);
				oBtn.style.display = "none";
			}
			
			bSys=true;
			document.documentElement.scrollTop=document.body.scrollTop=scrollTop+iSpeed;
		}, 30);
		
	};

}
