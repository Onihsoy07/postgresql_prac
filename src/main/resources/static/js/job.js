<script type="text/javascript">

	var compMailUrl = "http://61.38.160.205:80/mail2/";

	$(document).ready(function() {

		//메뉴 더보기로 자동 복사
		freeb_menu();

		//통합검색창 input 더미 태그 삭제 함수
		delDummyInputTag();

		//더보기 클릭시
		$(document).mouseup(function (e){
			if($(e.target).attr("moreBtn")  == null){
				$(".freeb_more").removeClass("on");
				$(".freeb_more_box").addClass("close");
				$(".freeb_more_box").removeClass("open");
			}
			else{
				 if (!$(".freeb_more").hasClass("on"))
				 {
					$(".freeb_more").addClass("on");
					$(".freeb_more_box").addClass("open");
					$(".freeb_more_box").removeClass("close");

				 } else {

				   $(".freeb_more").removeClass("on");
					$(".freeb_more_box").addClass("close");
					$(".freeb_more_box").removeClass("open");
				 }
				var fmiCnt = $(".freeb_more_box .more_box_in > div").size();
				var fmihei= Math.round(fmiCnt/2)*77
				 $(".freeb_more_box.open .more_box_in").css("height",fmihei);
			}
		});

		//더보기 박스에서 마우스가 떠났을시 닫힘
		$(".more_box_in").mouseleave(function(){
			$(".freeb_more").removeClass("on");
				$(".freeb_more_box").addClass("close");
				$(".freeb_more_box").removeClass("open");
		});






		setAuthBtn();

	});

	function setAuthBtn(){

		if("USER" == "USER" && "main" != "mailex"){
			if("" == "true"){
				$("#btnAdmin").show();
			}
			if("" == "true"){
				$("#btnMaster").show();
			}
		}


		if("USER" == "ADMIN" && "main" != "mailex"){
			$("#btnUser").show();
			if("" == "true"){
				$("#btnMaster").show();
			}
		}

		if("USER" == "MASTER" && "main" != "mailex"){
			$("#btnUser").show();
			if("" == "true"){
				$("#btnAdmin").show();
			}
		}
	}

	function helpDeskAdminAlertPop(){

	}




	//메뉴 더보기 박스로 복사
	function freeb_menu(){

	  var td_size = $(".freeb_m_ta table td").size();
	  var num = 8;
		if (td_size >10)
		 {
			$(".freeb_m_ta table td").eq(num).nextAll("td").hide();
			var menuCln = $(".freeb_m_ta table td").eq(num).nextAll("td").children(".fm_div").clone();
			$(".more_box_in").html(menuCln);
			$(".freeb_m_ta table").css("marginRight","183px");

			$(".freeb_more").show();
		  }else{
			  $(".freeb_more").hide();
		  }

		$(".freeb_menu_in").show();
	}


	var manualUserSe = "USER";
	var orgnztId = "287";
	var parentOptionValue = "0";
	var optionValueLogoutTime = "";
	var optionValueLogoutType = "";
	var limitYn = 1;		// 제한 여부
	var logonTime = "";
	var empDeptFlag = false;
	var alertFlag = true;
	var logoutFlag = true;
	var mentionUseYnFlag = "Y";

	// console.log(logonTime);
	// 로그인 통제 옵션 사용 시 (상위 옵션)
	if(parentOptionValue == "1") {
		// 자동 로그아웃 시간
		optionValueLogoutTime = "";
		optionValueLogoutType = "";

		localStorage.setItem("limitYn", 1);

		// 강제 로그아웃 시간 값이 존재할때
		if(optionValueLogoutTime > 0) {

			// 동작 이벤트 (시간 초기화)
			$(window).bind("click keyup", function () { resetTimer(); });

			// 사용 기록이 없을 때
			if(localStorage.getItem("LATime") == "" || localStorage.getItem("LATime") == null) {
				resetTimer();
			} else if (logonTime != null && logonTime != "") {
	            resetTimer();
	        }

			// 강제 로그 아웃 되었을 때
			if ((parseInt(localStorage.getItem("LATime")) + (60000 * optionValueLogoutTime) < (+new Date()))) {
	            document.location.href = 'uat/uia/actionLogout.do';
	            alert("보안설정으로 인해 자동 로그아웃 되었습니다");
	        } else {
	            resetTimer();
	            setInterval(function () { idleCheck(); }, 10000);
	        }
		}
	}


	function idleCheck() {

		if(!logoutFlag){
			fnAutoLogout();
			return;

		}else if (logoutFlag && limitYn == "1" && (parseInt(localStorage.getItem("LATime")) + (60000 * optionValueLogoutTime) - (+new Date())) < 0) {

			//PC Sleep으로 인해 스크립트 미동작 후 1분이상 지연 처리
			if((parseInt(localStorage.getItem("LATime")) + (60000 * optionValueLogoutTime) - (+new Date())) < -60000){
				fnAutoLogout();
				logoutFlag = false;
				return;
			}

			localStorage.setItem("limitYn", 0);

	        $("#idleCheckDiv, .idleCheckDivLock").show();
	        limitYn = 0;
	        countDown();
	    }
	}

	function countDown() {
		var sec = parseInt($("#limitSec").html());

		if(limitYn == "0"){
			if (sec > 0) {
		        setTimeout("countDown()", 1000);
		        $("#limitSec").html(sec -1);
		    } else {
		    	fnAutoLogout();
		    }
		}
	}

	function fnAutoLogout(){

		localStorage.setItem("forceLogoutYn", "Y");

        $("#secuText").html("보안설정으로 인해 자동 로그아웃 되었습니다.");
        $('[name=inSession]').hide();
        $("#limitSec").hide();

        if(optionValueLogoutType != "1"){
	         $("#logonPw").find('input').val("");
	         $("#logonPw").show();
	         $('[name=outSession]').show();

	         fnRemoveSession();

        }else{

        	setTimeout(function() {
        		document.location.href = 'uat/uia/actionLogout.do';
                if(logoutFlag){
                	alert("보안설정으로 인해 자동 로그아웃 되었습니다");
                	fnRemoveSession();
                }
        	}, 500);

        }

	}

	// 로그아웃 세션 삭제
	var fnRemoveSessionField = false;

	function fnRemoveSession() {

	 	if(fnRemoveSessionField){
			return;
		}

		$.ajax({
			type: "POST"
			, url: '/gw/uat/uia/removeSession.do'
			, success: function(result) {
				fnRemoveSessionField = true;
			}
			, error: function(result) {
				$("#idleCheckDiv, .idleCheckDivLock").show();
		        $("#secuText").html("보안설정으로 인해 자동 로그아웃 되었습니다.");
		        $('[name=inSession]').remove();
                $('[name=outSession]').remove();
                $("#limitSec").remove();
                $("#logonPw").remove();

                $('[name=forceSession]').show();

			}
		});
	}

	// 동작 이벤트 발생 시 시간 초기화
	function resetTimer() {
		if(limitYn == "1") {
			localStorage.setItem("LATime", (+new Date()));
		}
	}


	function loginButton() {
	    if (event.keyCode == 13) {
	        reLogon();
	    }
	}


	function reLogon() {
		if ($("#logonPw").find('input').val() == "") {
	        alert("패스워드를 입력해 주십시오");
	        return;
	    }

		var param = {};
		param.userSe = "USER";
		param.groupSeq = "daeryuk";
		param.compSeq = "15";
		param.deptSeq = "287";
		param.empSeq = "503";
		param.type = "def";
		param.secuStrBase = btoa(unescape(encodeURIComponent($("#logonPw").find('input').val())));
		$.ajax({
			type: "POST"
			, data: param
			, url: '/gw/loginPwCheck.do'
			, success: function(result) {
				if(result.result > 0) {

					localStorage.removeItem("forceLogoutYn");
					logoutFlag = true;
	                localStorage.setItem("limitYn", 1);
	                limitYn = 1;
	                resetTimer();

	                //세션이 없을경우 처리
	                if(result.result == 2){
	        			document.reLogonProcForm.action = "systemx/reLogonProc.do";
	        			document.reLogonProcForm.encPasswdOld.value = param.encPasswdOld;
	        			document.reLogonProcForm.langCode.value = result.langCode;
	        			document.reLogonProcForm.submit();
	                }else{
	    				$('#idleCheckDiv, .idleCheckDivLock').hide();
	                    $('#limitSec').html('60');
	                    $("#limitSec").show();
	                    $('[name=inSession]').show();
	                    $('[name=outSession]').hide();
	                    $("#logonPw").hide();
	                }
				} else {
					alert('패스워드가 일치하지 않습니다');
					$("#logonPw").focus();
				}
			}
			, error: function(result) {
				alert("실패");
			}
		});
	}

		var topType = 'main';
		var popType = 0; // 0:모두,1:프로필팝업, 2:알림팝업, 3:나의메뉴
		var clickCnt = 0;
		var chkInOut = false;

		function getTopMenu() {
			return menu.topMenuInfo;
		}

		function getLeftMenuList() {
			return menu.leftMenuList;
		}

		function onclickTopMenu(no, name, url, urlGubun) {

			$(".profile_comp_box").css("display", "none");

			if(topType == "mailex")
				$("#_content").attr("src","/"+urlGubun + "/cmm/systemx/myInfoManage.do");

			else{
				if (topType == 'tsearch' || topType == 'main' || topType == 'mainex' || topType == 'timeline' || topType == 'mail' || urlGubun == 'mail' || urlGubun == 'adminMail') {

					$("#no").val(no);
					$("#name").val(name);
					$("#url").val(url);
					$("#urlGubun").val(urlGubun);

					if (urlGubun == 'mail' || urlGubun == 'adminMail') {
						form.action="bizboxMail.do";
					}else if(urlGubun == 'mailex'){ // 메일전용 계정 추가
						form.action="bizboxMailEx.do";
					}else if(urlGubun == 'noticeMail') {
						openWindow2(url, name, 980, 680, 1, 1);
						return;
					}else {
						form.action="bizbox.do";
					}

					form.submit();

					//return mainmenu.clickTopBtn(no, name, url, urlGubun);
				} else {
					return menu.clickTopBtn(no, name, url, urlGubun);
				}
			}
		}

		function onclickTopCustomMenu(no, name, url, urlGubun, lnbMenuNo, ssoYn) {

			var param = {};
			param.menuNo = no;
			param.menuNm = name;

			$.ajax({
				type:"post",
				url: _g_contextPath_ + "/cmm/system/menuUseHistory.do",
				datatype:"json",
				data: param,
				success:function(data) {
					if(data != null && data.sessionYn == null){
						location.href = "/gw/forwardIndex.do?maxSessionOut=Y";
					}else if(data != null && data.sessionYn == "N"){
						menu.fnRedirectLogon();
						return;
					}else{
						clearTimeout(menuSetTimeID);
						detailUrl = "";

						if(urlGubun == "link_pop"/*  || urlGubun == "link_iframe" */){
							menu.go(urlGubun, url, no, name, ssoYn);
							return;
						}

						$("#topMenuList").find("a[name=topMenu]").each(function(){
							$(this).removeClass("on");
						});
						$("#topMenu" + no).addClass("on");

						if(urlGubun == "WEHAGO") {
							$.ajax({
								type:"POST",
								url: '/gw/linkWehagoSsoService.do',
								data: "",
								async: false,
								datatype:"json",
								success:function(data){
									if(data.resultCode == "200") {
										var resultData = data.resultData;
										// 초록우산(childfund) 운영서버 t_co_group-gw_url 값이 실 도메인과 달라서 하드코딩 처리
										var callbackUrl = resultData.groupSeq === 'childfund' ? "https://we.childfund.or.kr" : resultData.domain
										// 초록우산(childfund) 운영서버 위하고 도메인 하드코딩 처리
										var wehagoDomain = resultData.groupSeq === 'childfund' ? "https://data.childfund.or.kr/#/sso/service" : resultData.mode === 'DEV' ? 'http://dev.wehago.com/#/sso/service' : 'https://wehago.com/#/sso/service';
										var serviceDomainUrl = wehagoDomain + '?security_param=' + resultData.securityParamEnc + '&software_name=' + 'bizbox' + '&domain=' + btoa(callbackUrl);
										console.log(serviceDomainUrl);
										window.open(serviceDomainUrl, '위하고 서비스');
									}
									else if (data.resultCode == "300") {
										var resultData = data.resultData;

										var pop = window.open("", "wehagoLogin", "width=500,height=200,scrollbars=no");
										wehagoLoginPop.target = "wehagoLogin";
										wehagoLoginPop.method = "post";
										wehagoLoginPop.action = "/gw/systemx/wehagoLogin.do";
										wehagoLoginPop.submit();
										pop.focus();
									}
									else {
										Pudd.puddDialog({
											width: '400',
											height: '100',
											message: {
												type: 'error',
												content: 'WEHAGO와 연동되지 않았습니다.'
											}
										})
									}
								}
							});
						}else if(urlGubun == "one_contents"){
							$("#no").val(no);
							$("#name").val(name);
							$("#url").val(url);
							$("#urlGubun").val(urlGubun);
							$("#gnbMenuNo").val(no);
							$("#mainForward").val(ssoYn);
							form.action="oneContents.do";
							form.submit();
						}else if(urlGubun != "mail" && lnbMenuNo != ""){
							$("#no").val(no);
							$("#name").val(name);
							$("#url").val(url);
							$("#urlGubun").val(urlGubun);
							$("#mainForward").val('mainForward');
							$("#gnbMenuNo").val(no);
							$("#lnbName").val('');
							$("#lnbMenuNo").val(lnbMenuNo);
							$("#portletType").val('APPROVAL');
							$("#userSe").val('USER');
							form.action="bizbox.do";
							form.submit();
						}else{
							onclickTopMenu(no, name, url, urlGubun);
						}

					}
				},
				error: function(xhr) {
			      console.log('FAIL : ', xhr);
			    }
			});

		}

		function changeMode(userSe) {
			if(doubleClickEventStat){
				doubleClickEventStat = false;
				/* 함수가 잘 못 실행된 경우 상태값을 스스로 복원할 수 있도로 예외처리 적용 */
				/* 함수가 한번 수행 후 오류가 날 경우 doubleClickEventStat 값을 보완 하는 기능이 없기 때문에 별도 interval 처리 */
				/* 2018-01-10 김상겸 */
				var interval = setInterval(function(){
					doubleClickEventStat = true;
					clearInterval(interval);
				}, 1000);
				location.href = "changeUserSe.do?userSe="+userSe;
			}
		}


		function forwardPageByAlert(url, alertId, eventType, eventSubType, senderSeq, e){

			if(eventType == "TALK" || eventType == "MESSAGE"){

				$("#alertId").val(alertId);

		    	var pop_title = "mentionDetailPop.do" ;
		    	openWindow2("",  "mentionDetailPop.do", 500, 390, 1, 1) ;
		        var frmData = document.detailPopForm ;
		        frmData.target = pop_title ;
		        frmData.action = "/gw/mentionDetailPop.do";
		        frmData.submit();
			}
			else{

				var data = $(e).attr("data");
				data = JSON.parse(data);

				/*
				if(eventSubType == "PR001"){
					projectMove('project-1', data.prjSeq);
				}else if(eventSubType == "PR011"){
					projectMove('project-2', data.prjSeq);
				}else if(eventSubType == "PR013"){
					projectMove('project-3', data.prjSeq);
				}else
				*/

				if(eventSubType == "SC001"){
					scheduleMove(data.schSeq, data.schSeq);
				}else if(eventSubType == "RP001"){
					reportDetail(data.reportSeq, '1', data.kind);
				}else if(eventSubType == "RP002"){
					reportDetail(data.reportSeq, '1', data.kind);
				}else if(eventSubType == "RP003"){
					reportDetail(data.reportSeq, '1', data.kind);
				}else if(eventSubType == "EA101"){
					eapPop(url);
				}else if(eventSubType == "ED001"){
					edmsMove(data.dir_cd, data.artNo);
				}else if(eventSubType == "BO001"){
					boardMove('board-1', data.boardNo, data.artNo);
				}else if(eventSubType == "BO002" || eventSubType == "BO004" || eventSubType == "BO007" || eventSubType == "BO009"){
					var pop = openWindow2("/edms/home" + url,  "alertInfoPop", 1000, 800, 1, 1) ;
	 		    	pop.focus();
				}else if(eventType == "ONEFFICE"){
					var pop = openWindow2(data.doc_url, "alertInfoPop", 1000, 800, 1, 1) ;
	 		    	pop.focus();
				}else{
					if(url != ""){
		 				var urlGubun = "";

		 				if(eventType == "MAIL"){

		 				}else if(eventType == "PROJECT"){
		 					urlGubun = "project";
		 				}else if(eventType == "RESOURCE"){
		 					urlGubun = "schedule";
		 				}else if(eventType == "REPORT"){
		 					urlGubun = "schedule";
		 				}else if(eventType == "EAPPROVAL"){
		 					urlGubun = "eap";
		 				}else if(eventType == "BOARD"){
		 					urlGubun = "edms/board";
		 				}else if(eventType == "EDMS"){
		 					urlGubun = "edms/doc";
		 				}else if(eventType == "ATTEND"){
		 					if(eventSubType == "AT001" || eventSubType == "AT002" || eventSubType == "AT003")
		 						urlGubun = "attend";
		 					else
		 						urlGubun = "attend";
		 				}else if(eventType == "SCHEDULE"){
		 					urlGubun = "schedule";
		 				}

		 				var popUrl = (urlGubun == "") ? url : ("/" + urlGubun + "/" + url);

		 		    	var pop = openWindow2(popUrl,  "alertInfoPop", 1000, 800, 1, 1) ;
		 		    	pop.focus();
		 			}
				}

			}

			//해당알림 읽음처리
			fnAlertRead(alertId);
		}


		function reportDetail(reportSeq, type, kind){
			var url = "/schedule/Views/Common/report/reportInfoPop.do?reportSeq="+reportSeq+"&type="+type+"&kind="+kind;
	  		openWindow2(url,  "pop", 1020, 600, 1, 1) ;
		}



		function eapPop(urlPath) {
	  		var url = "/eap" + urlPath;
	  		openWindow2(url,  "pop", 1000, 711, 1, 1) ;
	  	}

		function scheduleMove(pkSeq1, pkSeq2) {
	  		var url = "/schedule/Views/Common/mCalendar/detail?seq="+pkSeq2;
	  		openWindow2(url,  "pop", 833, 711,"yes", 1);
	  	}

		function boardMove(jobType, catSeq, artSeq) {
	  		if(jobType == "board-1" || jobType == "board-3"){
	  			var url = "/edms/board/viewPost.do?boardNo="+catSeq+"&artNo="+artSeq;
	  			openWindow2(url,  "pop", 1200, 711,"yes", 1) ;
	  		}else if(jobType == "board-2"){
	  			var url = "/edms/board/viewPost.do?boardNo="+catSeq+"&artNo="+artSeq;
	  			openWindow2(url,  "pop", 1200, 711,"yes", 1) ;
	  		}else if(jobType == "board-4"){
	  			var url = "/edms/board/viewBoard.do?boardNo="+catSeq+"&artNo="+artSeq;
	  			openWindow2(url,  "pop", 1200, 711,"yes", 1) ;
	  		}
	  	}


		function edmsMove(wDirCd, artSeqNo) {

	  		var url = "/edms/doc/viewPost.do?dir_cd="+wDirCd+"&dir_lvl=3&dir_type=W&currentPage=1&artNo="+artSeqNo+"&dirMngYn=N&hasRead=Y&hasWrite=Y&searchField=&searchValue=&startDate=&endDate=";
	  		openWindow2(url,  "pop", 1000, 711, "yes", 1) ;

	  	}


		/*
		function projectMove(jobType, pkSeq) {

	  		var projectType = "";
	  		var projectSeq = "";
	  		var workSeq = "";
	  		var jobSeq = "";
	  		var menuNo = "";
	  		var type = "";

	  		if(jobType == "project-1"){
	  			projectSeq = pkSeq;
	  			menuNo = "401020000";
	  			type = "P";
	  		}else if(jobType == "project-2"){
	  			workSeq = pkSeq;
	  			menuNo = "401030000";
	  			type = "W";
	  		}else if(jobType == "project-3"){
	  			jobSeq = pkSeq;
	  			menuNo = "401040000";
	  			type = "J";
	  		}

	  		var url = "/project/Views/Common/project/projectView.do?pSeq="+projectSeq+"&wSeq="+workSeq+"&jSeq="+jobSeq+"&type="+type;
	  		openWindow2(url,  "pop", 1100, 911, 1) ;
	  	}*/

		function fnAlertRead(alertId){
			var tblParam = {};
			tblParam.alertId = alertId;

			$.ajax({
				type:"POST",
				url: '/gw/alertRead.do',
				data: tblParam,
				async: false,
				datatype:"json",
				success:function(data){
					setTimeout("alertCntReflesh()", 500);
				}
			});
		}


		function alertCntReflesh(){
			$.ajax({
				type:"post",
				url: _g_contextPath_ + "/alertUnreadCnt.do",
				datatype:"text",
				async: true,
				success:function(data){
					var alertNotifyYn = data.alertNotifyYn;
					if(data.alertMentionCnt > 0){
						$("#alertCnt").html("@");
						$("#newMentionIcon").addClass("new");
					}
					else if(alertNotifyYn == "Y"){
						$("#alertCnt").html("N");
					}
					else{
						$("#alertCnt").html("");
						$("#alertCnt").attr("class","");
					}

					if(data.alertMentionCnt < 1){
						$("#newMentionIcon").removeClass("new");
					}

//	 				$("." + alertId).removeClass("unread");
				},
				error: function(xhr) {
			      console.log('FAIL : ', xhr);
			    }
			});
		}


		function forwardPage(alertSeq, menuGugun, gnbMenuNo, lnbMenuNo, url, urlGubun, seq, name) {

			if(lnbMenuNo == "934000001"){
			   	var left = (screen.width-958)/2;
			   	var top = (screen.height-753)/2;

			   	var pop = window.open("/gw" + url, "certRequestPop", "width=690,height=370,left="+left+" top="+top);
			   	pop.focus();

				return;
			}

			menu.hideGbnPopup(this.Event);

			if (topType == 'timeline' || topType == 'mail' || urlGubun == 'mail') {
				if (urlGubun == 'ea' || urlGubun == 'eap' || (urlGubun == 'schedule' && url.search("report") > 0)) {
					window.open("../"+urlGubun+url);
					//window.close();
				}
				else {
					menu.moveAndReadCheck2(alertSeq, topType, menuGugun, gnbMenuNo, lnbMenuNo, url, urlGubun, seq, name);
				}
			}
			else {
				if (urlGubun == 'ea' || urlGubun == 'eap' || (urlGubun == 'schedule' && url.search("report") > 0)) {
					window.open("../"+urlGubun+url);
					//window.close();
				}
				else {
					menu.moveAndReadCheck(alertSeq, topType, menuGugun, gnbMenuNo, lnbMenuNo, url, urlGubun, seq, name);
				}
			}
		}

		function changeMainContent(target) {
			/*
			if (target == "timeline") {
				form.action="timeline.do";
			}
			else {
				form.action="userMain.do";
			}

			form.submit();
			*/

			if(target == "portal"){
				$("#pTap").addClass("on");
				$("#tTap").removeClass("on");
			}else{
				$("#pTap").removeClass("on");
				$("#tTap").addClass("on");
			}

			menu.timeline(target);
		}

		function changeTotalSearch() {
			if($("#tsearch").val() == "" || $.trim($("#tsearch").val()) == ""){
				alert("검색어를 입력해주세요!");
				$("#tsearch").focus();
				return;
			}
			var timestamp = new Date().getTime();
			$("#formSearch").attr('action', 'totalSearch.do?t=' + timestamp.toString());
			menu.totalsearch();
		}

		$(document).ready(function() {

			//forceLogoutCheck
			if(localStorage.getItem("forceLogoutYn") == "Y"){

				if("" == "N"){
					localStorage.removeItem("forceLogoutYn");
				}else{
					$("body").remove();
		            document.location.href = 'uat/uia/actionLogout.do';
		            alert("보안설정으로 인해 자동 로그아웃 되었습니다");
		            localStorage.removeItem("forceLogoutYn");
		            return;
				}
			}

			var img = new Image();
			img.onload=function(){
				var innerHTML = "";
				if("USER" == "USER")
					innerHTML = "<img src=\"/upload/img/logo/daeryuk/15/IMG_COMP_LOGO_15.png\" onerror=\"this.src='/gw/Images/freeb/gnb_logo01_pkg.png'\" />";
				else
					innerHTML = "<img src=\"/upload/img/logo/daeryuk/15/IMG_COMP_LOGO_15.png\" onerror=\"this.src='/gw/Images/freeb/gnb_logo01_pkg.png'\" />";
				$("#logoImgHyperLink").html(innerHTML);
			}

			img.onerror=function(){
				var innerHTML = "";
				if("USER" == "USER")
					innerHTML = "<img src=\"/gw/Images/freeb/gnb_logo01_pkg.png\" onerror=\"this.src='/gw/Images/freeb/gnb_logo01_pkg.png'\" />";
				else
					innerHTML = "<img src=\"/upload/img/logo/daeryuk/15/IMG_COMP_LOGO_15.png\" onerror=\"this.src='/gw/Images/freeb/gnb_logo01_pkg.png'\" />";
				$("#logoImgHyperLink").html(innerHTML);
			}
			img.src='/upload/img/logo/daeryuk/15/IMG_COMP_LOGO_15.png';

			if("61.38.160.205:80" == "61.38.160.205:80"){
				if("JOA Group" != "")
					document.title = "JOA Group";
				else if("JOA Group" != "")
					document.title = "JOA Group";
			}
			else{
				if("61.38.160.205:80" != "" && "JOA Group" != "")
					document.title = "JOA Group";
			}

			if("Y" == "N"){
				$(".alert_box").focusout(function(e){
					if(!chkInOut){
						$(".alert_box").css("display", "none");
						alertBtn_Click();
					}
				});
			}


			if("Y" == "Y"){
				$(".mention_alert_box").focusout(function(e){
					if(!chkInOut){
						$("body").off("click.mention_alert_box");
						$(".mention_alert_box").css("display", "none");
						alertBtn_Click();
					}
				});
			}else{
				$(".alert_box").focusout(function(e){
					if(!chkInOut){
						$(".alert_box").css("display", "none");
						alertBtn_Click();
					}
				});
			}

			$(".profile_comp_box").focusout(function(e){
				if(!chkInOut)
					$(".profile_comp_box").css("display", "none");
			});

			$(".mymenu_box").focusout(function(e){
				if(!chkInOut)
					$(".mymenu_box").css("display", "none");
			});

			if("Y" == "N"){
				$(".alert_box").on('mouseenter', function(){
					chkInOut = true;
				});

		 		$(".alert_box").on('mouseleave', function(){
					chkInOut = false;
				});
			}else{
				$(".mention_alert_box").on('mouseenter', function(){
					chkInOut = true;
				});
				$(".mention_alert_box").on('mouseleave', function(){
					chkInOut = false;
				});
			}





			$(".profile_comp_box").on('mouseenter', function(){
				chkInOut = true;
			});
			$(".profile_comp_box").on('mouseleave', function(){
				chkInOut = false;
			});

			$(".mymenu_box").on('mouseenter', function(){
				chkInOut = true;
			});
			$(".mymenu_box").on('mouseleave', function(){
				chkInOut = false;
			});

			if(topType == "timeline") {
				$("#tTap").addClass('on');
			}
			else {
				$("#pTap").addClass('on');
			}


			// 알림 카운트 조회
			if("Y" == "N"){
				menu.alertCnt();
				menu.alertPolling();	// 주기적으로 호출
			}else{
				menu.alertUnreadCnt();
				menu.alertUnreadPolling();	// 주기적으로 호출
			}


			// edms는 서버가 다를수 있음.
			menu.edmsDomain = 'http://61.38.160.205:80';


			/*

			*/



			// 검색
			$(".search_btn").click(function(e){
				//readyInfo();
				changeTotalSearch();
			});

//	 		// 열린 메뉴 다른 영역 클릭시 닫기
//	 		$('.header_wrap').on("click", function(e){
//	 			console.log("header_wrap click");
//	 			//e.stopPropagation();
//	 			if (popType != 1 && popType != 2) {
//	 				menu.hideGbnPopup(0);
//	 			}
//	 		});
//	 		$('.main_wrap').on("click", function(e){
//	 			console.log("main_wrap click");
//	 			//e.stopPropagation();
//	 			if (popType != 1 && popType != 2) {
//	 				menu.hideGbnPopup(0);
//	 			}
//	 		});

			$('iframe').on("click", function(e){
//	 			console.log("iframe click");
				//e.stopPropagation();
				menu.hideGbnPopup(0);
			});

			//사진등록임시
			$(".phogo_add_btn").on("click",function(){
				$(".hidden_file_add").click();
			})

			$(".hidden_file_add").on("change",function (){

				/* IE 10 버젼 이하에서 작동이 안될 수 있음. 추후 다른 방법으로 변경 예정. */
				var formData = new FormData();
				var pic = $("#userPic")[0];

				formData.append("file", pic.files[0]);
				formData.append("pathSeq", "910");	//이미지 폴더
				formData.append("relativePath", ""); // 상대 경로

		        menu.userImgUpload(formData, "userImg");

		    });

			//프로필팝업 온오프
			$(".divi_txt").on("mousedown",function(e){
//	 			console.log("divi_txt click");
				popType = 1;
				menu.hideGbnPopup(popType);
				$(".profile_comp_box").toggle();
				$(".h_pop_box").focus();

				//chkInOut = true;
				e.preventDefault();
				setPositionList();
			})

//	 	   //알림팝업 온오프
		   $(".alert_btn").on("mousedown",function(e){
	 		   console.log("alert_btn click");
			   popType = 2;
			   menu.hideGbnPopup(popType);
			   if("Y" == "N"){
		 			$(".alert_box").toggle();
			   }else{
					$(".mention_alert_box").toggle();

					if($("#_content").length > 0) {

						$("body").css("overflow", "hidden");

						$("#_content").contents().find("body").click(function(e) {
							//console.log(!$(e.target).hasClass("alert_btn"), !$(".mention_alert_box").is(e.target), $(".mention_alert_box").has(e.target).length);
							if(!$(e.target).hasClass("alert_btn") && !$(".mention_alert_box").is(e.target) && $(".mention_alert_box").has(e.target).length == 0) {
								if($(".mention_alert_box").css("display") == "block") {
									chkInOut = false;
									$(".mention_alert_box").blur();
								}
							}
						});
					}

					// 알림 팝업 생성시 바디태그에 클릭이벤트 추가한다.
					// 알림 팝업 태그의 focusout 이벤트가 정상 작동하지 않아 로직 추가
					$("body").on("click.mention_alert_box", function(e) {
						//console.log(!$(e.target).hasClass("alert_btn"), !$(".mention_alert_box").is(e.target), $(".mention_alert_box").has(e.target).length);
						if(!$(e.target).hasClass("alert_btn") && !$(".mention_alert_box").is(e.target) && $(".mention_alert_box").has(e.target).length == 0) {
							if($(".mention_alert_box").css("display") == "block") {
								chkInOut = false;
								$(".mention_alert_box").blur();
							}
						}
					});
			   }
				$(".h_pop_box").focus();
				chkInOut = true;

			   e.preventDefault();
			   //e.stopPropagation();
			   alertBtn_Click();
			})


			// 	   //알림팝업 온오프
		   $("#alertCnt").on("mousedown",function(e){
	 		   //console.log("alert_btn click");
			   popType = 2;
			   menu.hideGbnPopup(popType);
			   if("Y" == "N"){
		 			$(".alert_box").toggle();
			   }else{
					$(".mention_alert_box").toggle();
			   }
				$(".h_pop_box").focus();
				//chkInOut = true;

			   e.preventDefault();
			   alertBtn_Click();
			})

			//나의메뉴팝업 온오프
		   $(".mymenu_btn").on("mousedown",function(e){
//	 		   console.log("mymenu_btn click");
			   popType = 3;
			   menu.hideGbnPopup(popType);

			  var mymenu_box = $(".mymenu_box").css("display");

			  // 나의메뉴 데이터 호출
			  if (mymenu_box == 'none') {
				myMenu();
			  }

				$(".mymenu_box").toggle();
				$(".mymenu_box").focus();
				chkInOut = true;
			   e.stopPropagation();
			});




			//컨텐츠를 클릭할때(컨텐츠에 타이틀도 포함)
			$(".list_con").on("click",function(){
				$(this).parent().removeClass("unread");
			});

			//접고 펼치기
			$(".toggle_btn").on("click",function(){
				$(this).toggleClass("down");
				$(this).parent().parent().find(".sub_detail").removeClass("animated1s fadeIn").toggleClass("animated1s fadeIn").toggle();

				//멘션 접고 펼치기
				if($(this).hasClass("down")){
					$(this).parent().parent().find(".mention_detail").removeClass("ellipsis").toggleClass("animated1s fadeIn");
				}else{
					$(this).parent().parent().find(".mention_detail").removeClass("animated1s fadeIn").toggleClass("ellipsis");
				}
			});

		});

		function myMenu() {
			 menu.myMenu();
		}



		function alertBtn_Click(){
			if("Y" == "N"){
				// 알림 버튼 클릭시 알림 리스트 조회
				var alert_box = $(".alert_box").css("display");
				if (alert_box == 'block' || alert_box == '') {
				    menu.alertInfo();
				}
			}else{
				// 알림 버튼 클릭시 알림 리스트 조회
//		 		var alert_box = $(".alert_box").css("display");
				var mention_alert_box = $(".mention_alert_box").css("display");
				if (mention_alert_box == 'block' || mention_alert_box == '') {
				    menu.alertInfo();
				}
			}
		}

		function setAlertRemoveNew(){
			$("#allAlertRead").hide();
			$("li[name=li_item]").attr("class","");
			$("li[name=li_item]").find("span.ico_new").remove();
			$("#alertCnt").attr("class", "");
			$("#alertCnt").html("");
		}

		// 비밀번호 변경 팝업 호출
		function fn_pwdPop(type){
				var url = "/gw/cmm/systemx/myinfoPwdModPop.do?type="+type;
		    	var pop = window.open(url, "myinfoPwdModPop", "width=500,height=390,scrollbars=yes");
		}

		function fn_masterAuthPage(){
			mainmenu.mainToLnbMenu('1900000000', '시스템설정', '', 'gw', '', 'main', '1900000000', '1903050000', '마스터권한설정', 'main');
		}


		/* double click 방지 */
		var doubleClickEventStat = true;

		function changeEmpPosition(target){
			if(doubleClickEventStat){
				doubleClickEventStat = false;

				/* 함수가 잘 못 실행된 경우 상태값을 스스로 복원할 수 있도로 예외처리 적용 */
				/* 함수가 한번 수행 후 오류가 날 경우 doubleClickEventStat 값을 보완 하는 기능이 없기 때문에 별도 interval 처리 */
				/* 2018-01-10 김상겸 */
				var interval = setInterval(function(){
					doubleClickEventStat = true;
					clearInterval(interval);
				}, 1000);
				var curTr = "15" + "287";
				if(curTr != target.id){
					document.changeUserPositionProcForm.action = "systemx/changeUserPositionProc.do";
					document.changeUserPositionProcForm.seq.value = $("#"+target.id).attr("seq");
					document.changeUserPositionProcForm.submit();
					mailSessionReset();
				}
			}
		}

		function mailSessionReset(){
			if("http://61.38.160.205:80/mail2/" != ""){
				$.ajax({
					type: "POST",
			        url: "http://61.38.160.205:80/mail2/sessionInit.do",
			        async: true,
			        dataType: "json",
			        success: function (result) {
			        },
			        error:function (e) {
			        }
				});
			}
		}


		function setPositionList(){
			if("USER" == "USER" || "USER" == "ADMIN"){

				if(!empDeptFlag){
					empDeptFlag = true;
					$.ajax({
						type: "POST"
						, url: '/gw/getPositionList.do'
						, success: function(result) {
							var innerHTML = "";

							if(result != null && result.positionList == null){
								location.href = "/gw/forwardIndex.do?maxSessionOut=Y";
							}else{
								for(var i=0;i<result.positionList.length;i++){
									var positionData = result.positionList[i];
									var boldTag = "";

									//주회사인 경우 볼드처리
									if(positionData.mainCompYn == "Y"){
										boldTag = " fwb";
									}

									if("main" == "mailex"){
										innerHTML += "<tr class='positionList" + boldTag + "' id='" + positionData.empCompSeq + positionData.deptSeq + "' onclick='changeEmpPosition(this);' deptSeq='" + positionData.deptSeq + "' compSeq='" + positionData.empCompSeq + "' seq='" + positionData.seq + "'>";
										innerHTML += "<td>" + positionData.compName + "</td>";
										innerHTML += "</tr>";
									}else if("eap" == "ea"){
										innerHTML += "<tr class='positionList" + boldTag + "' id='" + positionData.empCompSeq + positionData.deptSeq + "' onclick='changeEmpPosition(this);' deptSeq='" + positionData.deptSeq + "' compSeq='" + positionData.empCompSeq + "' seq='" + positionData.seq + "'>";
										innerHTML += "<td>" + positionData.compName + "</td>";
										innerHTML += "<td>" + positionData.deptName + "</td>";
										innerHTML += "</tr>";
									}else{
										innerHTML += "<tr class='positionList' id='" + positionData.empCompSeq + positionData.deptSeq + "' onclick='changeEmpPosition(this);' deptSeq='" + positionData.deptSeq + "' compSeq='" + positionData.empCompSeq + "' seq='" + positionData.seq + "'>";
										innerHTML += "<td class='" + boldTag + "'>" + positionData.compName + "</td>";
										innerHTML += "<td>" + positionData.eapproval + "</td>";
										innerHTML += "<td>" + positionData.eapprovalRef + "</td>";
										innerHTML += "</tr>";
									}
								}
								$("#empPositionInfo").append(innerHTML);
								$("#positionSpinImg").remove();

								if("USER" != "MASTER"){
								    var table = document.getElementById("empPositionInfo");
									var tr = table.getElementsByTagName("tr");
									for(var i=0; i<tr.length; i++)
										tr[i].style.backgroundColor = "white";
									var curTr = document.getElementById("15" + "287");
									curTr.style.backgroundColor = "#E6F4FF";
							   }
							}
						}
						, error: function(result) {
							//alert("실패");
						}
					});
				}
			}
		}


		function fnMailMove(mailUid,recvEmail,url,alertId) {

			var targetUrl = url.replace("{0}", "muid=" + mailUid);
			targetUrl = targetUrl.replace("{1}", "email=" + recvEmail);

			if(targetUrl.indexOf("/mail2/") != -1){
				targetUrl = compMailUrl + targetUrl.substring(targetUrl.indexOf("/mail2/")+7);
			}

			var test = targetUrl + "&seen=0&userSe=USER";
			var gwDomain = window.location.host + (window.location.port == "" ? "" : (":" + window.location.port));
			var pop = openWindow2(targetUrl + "&seen=0&userSe=USER&gwDomain=" + gwDomain,"readMailPopApi",1020,700,1,1);
			pop.focus();

	    	//해당알림 읽음처리
			fnAlertRead(alertId);
		}


		function tabClick(target){
			if(target == "A"){
				$("#allReadBtn").css("display","");
				if($(".alertDiv").length == 0){
					$("#noDate").css("display","");
				}else{
					$("#noDate").css("display","none");
				}
			}
			else if(target == "M"){
				$("#allReadBtn").css("display","none");
				if($(".mentionDiv").length == 0){
					$("#noDate").css("display","");
				}else{
					$("#noDate").css("display","none");
				}
			}
		}

		function cloudLogout(){
			//로그아웃 프로세스 변경 필
			$("html").css("background","black");

			$("body").addClass("animated1s clipTvOut");
			setTimeout(function(){
				localStorage.setItem("empAttCheckDate", null);
				location.href = "uat/uia/actionLogout.do";
			},1000);
		}


		function delDummyInputTag() {
			setTimeout(function(){
				$("#dummyTag").remove();
			}, 3000);
		}

		function getEmpPathNm() {
			if("(주)대륙" === "") {
				return '';
			} else {
				return "(주)대륙-";
			}
		}

</script>