/*
CC Video Player
v1.0.0
*/

// Self-executing function to prevent global vars and help with minification
(function (window, undefined) {
	var document = window.document;
	
	// Singleton, to prevent multiple initialization
	if(window.cc_js_Player){
		window.cc_js_Player.showPlayer();
		return;
	}
	
	// player
	function Player(){
	};
	
	// Use prototype to init function
	Player.prototype = {
		videoInfo: new Array(),
		videoAd:'',
		videoSrc: '',
		picSrc:'',
		adTime:'',
		adId:'',
		materialId:'',
		canskip:'',
		paramsObj:{},
		startPlayed: false,
		isQQ: function () {
			return navigator.userAgent.indexOf('MQQBrowser') > -1;
		},
		isWeixin: function () {
			return navigator.userAgent.indexOf('MicroMessenger') > -1;
		},
		isIE: function () {
			return !!window.ActiveXObject || "ActiveXObject" in window;
		},
		isIPad: function () {
			return navigator.userAgent.match(/iPad/i) != null;
		},
		isIPhone: function () {
			return navigator.userAgent.match(/iPhone/i) != null;
		},
		isAndroid: function () {
			return navigator.userAgent.match(/Android/i) != null;
		},
		isAndroid2: function () {
			return navigator.userAgent.match(/Android 2/i) != null;
		},
		isSymbianOS: function () {
			return navigator.userAgent.match(/SymbianOS/i) != null;
		},
		isWindowsPhoneOS: function () {
			return navigator.userAgent.match(/Windows Phone/i) != null;
		},
		showPlayer: function () {
			var scripts = document.getElementsByTagName("script");
			for(var i = 0;i < scripts.length;i=i+1){
				var script = scripts[i];
				if(script.src.indexOf("http://union.bokecc.com/player") == -1 && script.src.indexOf("http://p.bokecc.com/player") == -1
					&& script.src.indexOf("https://union.bokecc.com/player") == -1 && script.src.indexOf("https://p.bokecc.com/player") == -1
					){
					continue;
				}
				var src = script.src;
				script.src="";//
				var params = this.getParam(src.split("?")[1]);
				var video = document.createElement("div");
				var randomid = Math.ceil(Math.random() * 10000000);
				video.id = "cc_video_" + params.vid + "_" + randomid;
				params.divid = video.id;
				video.style.width = params.width;
				video.style.height = params.height;
				if(this.isMoble()){
					video.style.position = "relative";
				}
				video.innerHTML = "";
				// save video params
				this.videoInfo.push(params);
				script.parentNode.replaceChild(video, script);
				var t=new Date().getTime()+'_'+randomid;
				cc_js_Player.paramsObj[t]=params;
				this.jsonp("https://imedia.bokecc.com/servlet/mobile/adloader?uid=" + params.siteid + "&vid=" + params.vid + "&type=1&t="+t, "cc_js_Player.videoLoad", function(){
				    param=params;
					var authCode='';
					if(typeof window.get_cc_verification_code != 'undefined'){
				        authCode=encodeURIComponent(get_cc_verification_code(param.vid));
				    }
					if(cc_js_Player.isAndroid() && !cc_js_Player.isAndroid2()){
						cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
								+ param.vid + "&siteid=" + param.siteid + "&divid="
								+ param.divid + "&width=" + encodeURIComponent(window.screen.width, "UTF-8") + "&useragent="
								+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+ authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
					} else if(cc_js_Player.isIPhone() || cc_js_Player.isIPad()){
						cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
								+ param.vid + "&siteid=" + param.siteid + "&divid="
								+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
								+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+ authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
					} else {
						cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
								+ param.vid + "&siteid=" + param.siteid + "&divid="
								+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
								+ param.userAgent + "&version=20140214" + "&hlssupport=0&vc="+ authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
					}
				});
			}
		},
		showPlayerDirect:function(initParams){
			var params = initParams;
			params.userAgent = this.getUserAgent();
			var randomid = Math.ceil(Math.random() * 10000000);
			this.addVideoElement(document.body,params,randomid);
		    this.playAd(params,randomid); 
		},
		playAd:function(params,randomid){
			var t=new Date().getTime()+'_'+randomid;
			cc_js_Player.paramsObj[t]=params;
			this.jsonp("https://imedia.bokecc.com/servlet/mobile/adloader?uid=" + params.siteid + "&vid=" + params.vid + "&type=1&t="+t, "cc_js_Player.videoLoad", function(){
			    param=params;
				var authCode='';
				if(typeof window.get_cc_verification_code != 'undefined'){
			        authCode=encodeURIComponent(get_cc_verification_code(param.vid));
			    }
				if(cc_js_Player.isAndroid() && !cc_js_Player.isAndroid2()){
					cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
							+ param.vid + "&siteid=" + param.siteid + "&divid="
							+ param.divid + "&width=" + encodeURIComponent(window.screen.width, "UTF-8") + "&useragent="
							+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
				} else if(cc_js_Player.isIPhone() || cc_js_Player.isIPad()){
					cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
							+ param.vid + "&siteid=" + param.siteid + "&divid="
							+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
							+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
				} else {
					cc_js_Player.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
							+ param.vid + "&siteid=" + param.siteid + "&divid="
							+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
							+ param.userAgent + "&version=20140214" + "&hlssupport=0&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
				}
			});	
		},
		addVideoElement:function(parent,params,randomid){
			var video = document.createElement("div");
			video.id = "cc_video_" + params.vid + "_" + randomid;
			params.divid = video.id;
			video.style.width = params.width;
			video.style.height = params.height;
			if(this.isMoble()){
				video.style.position = "relative";
			}
			video.innerHTML = "";
			// save video params
			this.videoInfo.push(params);
			parent.appendChild(video);
		},
		videoLoad:function(data){
			var ts=data.response.t;
			param=cc_js_Player.paramsObj[ts];
			var authCode='';
			if(typeof window.get_cc_verification_code != 'undefined'){
		        authCode=encodeURIComponent(get_cc_verification_code(param.vid));
		    }
			if(this.isAndroid() && !this.isAndroid2()){
				this.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
						+ param.vid + "&siteid=" + param.siteid + "&divid="
						+ param.divid + "&width=" + encodeURIComponent(window.screen.width, "UTF-8") + "&useragent="
						+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
			} else if(this.isIPhone() || this.isIPad()){
				this.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
						+ param.vid + "&siteid=" + param.siteid + "&divid="
						+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
						+ param.userAgent + "&version=20140214" + "&hlssupport=1&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
			} else {
				this.jsonp("https://p.bokecc.com/servlet/getvideofile?vid="
						+ param.vid + "&siteid=" + param.siteid + "&divid="
						+ param.divid + "&width=" + encodeURIComponent(param.width, "UTF-8") + "&useragent="
						+ param.userAgent + "&version=20140214" + "&hlssupport=0&vc="+authCode + "&mediatype=" + param.mediatype,"cc_js_Player.showPlayerView");
			}

			if(typeof window.is_skip_ad === 'function'){
				var ad = encodeURIComponent(is_skip_ad());
				if(ad != 1){
					cc_js_Player.adLoader(data);
				}
			} else {
				cc_js_Player.adLoader(data);
			}
		},
		adLoader: function(j){
			if(j.response.result == 1){
				var str=j.response.ad[0].material;
				var pos = str.lastIndexOf(".");
				var lastname = str.substring(pos,str.length);
				if(lastname.toLowerCase() == '.mp4'){
					this.videoSrc = j.response.ad[0].material;
				} else {
					this.picSrc = j.response.ad[0].material;
				}
				this.adUrl = j.response.ad[0].material;
				this.canClick = j.response.canclick;
				this.videoAd = j.response.result;
				this.adTime = j.response.time;
				this.skipTime = j.response.skiptime;
				this.canskip = j.response.canskip;
				this.materialUrl = j.response.ad;
				this.adId = j.response.adid;
				this.materialId = j.response.ad[0].materialid;
				this.clickurl = j.response.ad[0].clickurl;
			}
		},
		getAdSrc: function(){
			return"https://imedia.bokecc.com/servlet/mobile/clickstats?adid="
						+ this.adId + "&clickurl=" + encodeURIComponent(this.clickurl) + "&materialid="+ this.materialId;
		},
		getParam: function(queryString){
			var params = queryString.split("&");
			var result = {mediatype:1};
			for(var i = 0;i < params.length;i=i+1){
				var key_value = params[i].split("=");
				var key = (key_value[0] + "").replace(/(^\s*)|(\s*$)/g, "");// trim
				result[key] = key_value[1];
			}
			result.userAgent = this.getUserAgent();
			return result;
		},
		getUserAgent: function(){
			var userAgent = navigator.userAgent.match(/MSIE|Firefox|iPad|iPhone|Android|SymbianOS/);
			
			var winPhoneUA = navigator.userAgent.match(/Windows Phone/);
			if (winPhoneUA == "Windows Phone"){
				userAgent = winPhoneUA;
			}
			
			if(userAgent){
				return userAgent;
			} else {
				return "other";
			}
		},
		isMoble: function(){
			var userAgent = navigator.userAgent.match(/iPhone|Android|SymbianOS|Windows Phone/);
			if(userAgent){
				return true;
			} else {
				return false;
			}
		},
		getVideoCode: function (params, video) {
			var videoCode = "";
			var _this = this;
			function getFileExt (num1, num2) {
                var filename = _this.getDefaultCopy(video).playurl;
                var index1 = filename.lastIndexOf(".");
                var index2 = filename.length;
                var postf = filename.substring(index1, index2);
                return postf.substring(num1,num2)
			};
            function checkPcm () {
                return video.status == 1 && getFileExt(0,4) == '.pcm';
            };
            function checkBrowser () {
                var m3u8 = navigator.userAgent.indexOf('Firefox') >= 0 && getFileExt(0,5) == '.m3u8' && _this.isAndroid();
                var qq = _this.isAndroid() && _this.isQQ() && !_this.isWeixin() && video.disableQQ == 1;
                return qq || m3u8;
            }
            function checkFlash () {
                var hasFlash = true;
                if(_this.isIE()) {
                    try{
                        var objFlash = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                    } catch(e) {
                        hasFlash = false;
                    }
                } else {
                    if(!navigator.plugins["Shockwave Flash"]) {
                        hasFlash = false;
                    }
                }
                return hasFlash;
            };
			function isCanPlayDomain() {
				if (typeof video.h5domain === 'undefined') {
					return true;
				}
				var oUrl = window.location.host;
				var blackURL = video.h5domain.blacklist;
				var whiteURL = video.h5domain.whitelist;

				if (!!blackURL && blackURL.length > 0) {
					for (key in blackURL) {
						if (String(oUrl).length > blackURL[key].length) {
							if (String(oUrl).endsWith('.' + blackURL[key].toLowerCase())) {
								return false;
							}
						} else if (String(oUrl).length == blackURL[key].length) {
							if(String(oUrl) == blackURL[key].toLowerCase()){
								return false;
							}
						}
					}
					return true;
				} else if (!!whiteURL && whiteURL.length > 0) {
					for (key in whiteURL) {
						if (String(oUrl).length > whiteURL[key].length) {
							if (String(oUrl).endsWith('.' + whiteURL[key].toLowerCase())) {
								return true;
							}
						} else if (String(oUrl).length == whiteURL[key].length) {
							if(String(oUrl) == whiteURL[key].toLowerCase()){
								return true;
							}
						}
					}
					return false;
				}
				return true;
			};
			// noraml player
			if (this.isSymbianOS()) {
				videoCode = this.createMobileView(params, video);
			} else if (video.playtype == 1 && this.isIE()) {
				if (!checkFlash()) {
                    videoCode = this.createFlashDiv(params);
                } else {
                	videoCode = this.createFlashView(params);
                }
			} else if (this.isAndroid() || video.playtype == 1 || this.isIPad() || this.isWindowsPhoneOS() || this.isIPhone()) {
				if(!isCanPlayDomain()){
					videoCode = this.createTips(params, '当前域名未获得播放授权');
				} else if (checkPcm()) {
					videoCode = this.createTips(params, '此视频已加密，请在其他设备上观看');
				} else if (checkBrowser()) {
					videoCode = this.createTips(params, '请切换其他浏览器观看视频');
				} else {
					videoCode = this.createHTML5VideoView(params, video);
				}
			} else {
                if (!checkFlash()) {
                    videoCode = this.createFlashDiv(params);
                } else {
                	videoCode = this.createFlashView(params);
                }
			}
			return videoCode;
		},
		getDefaultCopy: function (video) {
			for (var c in video.copies) {
				if (video.defaultquality == video.copies[c].quality) {
					return video.copies[c];
				}
			}
			return video.copies[0];
		},
		isRealtimePlay: function(video){
			return video.originalplay == 1 && video.status == 2;
		},
		getSrc: function(params, video){
			var src;
			var ua = window.navigator.userAgent.toLowerCase();
			if(this.videoAd == 1 && (this.isAndroid() || this.isIPad() || this.isIPhone())){
				src = this.videoSrc;
				if(ua.match(/MicroMessenger/i) == 'micromessenger' && this.isAndroid() && video.uid == '238125'){
					src = this.getDefaultCopy(video).playurl;
				}
			} else {
				if(this.isRealtimePlay(video)){
					src = 'https://express.play.bokecc.com/' + params.siteid + '/' + params.vid + '.mp4';
				} else if (typeof window.get_custom_id === 'function') {
					var flow = encodeURIComponent(get_custom_id());
					src = this.getDefaultCopy(video).playurl + '&custom_id=' +flow;
				} else {
					src = this.getDefaultCopy(video).playurl;
				}
			}
			return src;
		},
		skipSrc: function(params, video){
			var src;
			if(this.isRealtimePlay(video)){
				src = 'https://express.play.bokecc.com/' + params.siteid + '/' + params.vid + '.mp4';
			} else if (typeof window.get_custom_id === 'function') {
				var flow = encodeURIComponent(get_custom_id());
				src = this.getDefaultCopy(video).playurl + '&custom_id=' +flow;
			} else {
				src = this.getDefaultCopy(video).playurl;
			}
			return src;
		},
		getVttTrack: function(params, video){
			var vttSrc;
			var vttTrack;
			if(typeof video.vtt !='undefined' && video.vtt){
				vttSrc = video.vtt.vttUrl;
				vttTrack = "<track src='" + vttSrc + "' srclang='zh-cn' label='简体中文' kind='subtitles' default>";
			} else {
				vttTrack = '';
			}
			return vttTrack;
		},
		getCross: function(params, video){
			var cross;
			if((typeof video.vtt !='undefined' && video.vtt) || video.vrmode == 1){
				cross = 'crossorigin';
			} else {
				cross = '';
			}
			return cross;
		},
		getImgSrc: function(params, video){
			var imgSrc;
			if(this.isRealtimePlay(video)){
				imgSrc = 'https://express.play.bokecc.com/'+ params.siteid +'/' + params.vid + '.jpg';
			}else{
				imgSrc = video.img;
			}
			return imgSrc;
		},
		getCopy: function(params, video){
			var str;
			if(this.isRealtimePlay(video)){
				str = '';
			} else {
				str = this.getDefaultCopy(video).desp;
			}
			return str;
		},
		createMobileView: function(params, video){
				var twidth = params.width;
				var theight = params.height;
				if(!isNaN(params.width) || !isNaN(params.height)){
				   twidth = params.width + 'px';
				   theight = params.height + 'px';
				}
				if(video.status == 1){
					// var buttonwidth = 70;
					// var buttonheight = 50;
					// var top = (params.height - buttonheight) / 2;
					// var left = (params.width - buttonwidth) / 2;
					return "<table width='"+params.width+"' height='"+params.height+"' style='position:relative;'><tr><td width='"+params.width+"' height='"+params.height+"' style='position:relative;'><img src='" 
					+ video.img + "' width='"+params.width+"' height='"+params.height+"' style='width:" + twidth + "; height:" + theight + ";' />"
					+ "<a href='" +  this.getDefaultCopy(video).playurl
					+ "'><img src='https://p.bokecc.com/images/01.png' style='position:absolute; top:50%; left:50%; margin:-25px 0 0 -35px; width:70px; height:50px;' /></a></td></tr></table>";
				}else if(video.status == 0) {
					return  "<table><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，视频已删除，请联系网站管理员。</td></tr></table>";
				} else if(video.status == 2) {
					return  "<table><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>视频处理中……</td></tr></table>";
				} else if(video.status == 4) {
					return  "<table><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，网络连接失败，请刷新重试或联系网站管理员。</td></tr></table>";
				} else {
					return  "<table><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，暂不支持本设备，请选择其他设备观看。</td></tr></table>";
				}
		},
		createHTML5VideoView: function(params, video){
			var twidth = params.width;
			var theight = params.height;
			if(!isNaN(params.width) || !isNaN(params.height)){
			   twidth = params.width + 'px';
			   theight = params.height + 'px';
			}
			if(video.status == 1){
				return this.creatH5player(params, video);
			}else if(video.status == 0) {
				return  "<table style='border:none; padding:0; margin:0; width:" + twidth + "; height:" + theight + ";'><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; border:none; padding:0; margin:0; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，视频已删除，请联系网站管理员。</td></tr></table>";
			} else if(video.status == 2) {
				if(this.isRealtimePlay(video)){
					return this.creatH5player(params, video);
				} else {
					return  "<table style='border:none; padding:0; margin:0; width:" + twidth + "; height:" + theight + ";'><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; border:none; padding:0; margin:0; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>视频处理中……</td></tr></table>";
				}
			} else if(video.status == 4) {
				return  "<table style='border:none; padding:0; margin:0; width:" + twidth + "; height:" + theight + ";'><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; border:none; padding:0; margin:0; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，网络连接失败，请刷新重试或联系网站管理员。</td></tr></table>";
			} else {
				return  "<table style='border:none; padding:0; margin:0; width:" + twidth + "; height:" + theight + ";'><tr><td align='center' width='" + params.width + "' height='"
						+ params.height + "' style='background:#000; border:none; padding:0; margin:0; color:#FFF; font-size:30px; -webkit-text-size-adjust:none;'>抱歉，暂不支持本设备，请选择其他设备观看。</td></tr></table>";
			}
		},
		creatH5player: function(params, video){
			return "<div class='ccH5playerBox'>"
				+"  <div class='picAd' style='display:none;'>"
				+"      <a class='picBtn' href='' ></a>"
				+"  	<div class='x-advert-info'>"
				+"	  		<div class='x-advert-skip'>"
				+"		 		<div class='x-advert-txt closePicAd'><span class='closePicTime'><i class='skipPicNum'></i>s后可</span>关闭广告</div>"
				+"		 		<div class='x-mask'></div>"
				+"	  		</div>"
				+"	  		<div class='x-advert-countdown'>"
				+"		 		<div class='x-advert-txt'><span class='adSec'></span>s</div>"
				+"		 		<div class='x-mask'></div>"
				+"	  		</div>"
				+"  	</div>"
				+"		<div class='pictab'><img onload='oPlayer.picAdStyle()' class='pSrc' src=''/></div>"
				+"  	<div class='x-advert-detail'>"
				+"	  		<div class='x-advert-txt'><a class='picTxth' href='' target='_blank'>了解详情</a></div>"
				+"	  		<div class='x-mask'></div>"
				+"  	</div>"
				+"	</div>"
				+"  <div class='x-advert' style='display:none;'>"
        		+"		<a class='skipGg' href='' ></a>"
				+"  	<div class='x-advert-info'>"
				+"	  		<div class='x-advert-skip'>"
				+"		 		<div class='x-advert-txt closeAd'><span class='closeTime'><i class='skipNum'></i>s后可</span>关闭广告</div>"
				+"		 		<div class='x-mask'></div>"
				+"	  		</div>"
				+"	  		<div class='x-advert-countdown'>"
				+"		 		<div class='x-advert-txt'><span class='x-advert-sec'></span>s</div>"
				+"		 		<div class='x-mask'></div>"
				+"	  		</div>"
				+"  	</div>"
				+"  	<div class='x-advert-detail'>"
				+"	  		<div class='x-advert-txt'><a class='vadSrc' href=''  target='_blank'>了解详情</a></div>"
				+"	  		<div class='x-mask'></div>"
				+"  	</div>"
				+"  </div>"
				+"	<div class='ccH5Info'></div>"
				+"	<div class='ccH5Loading'></div>"
				+"	<div class='ccH5Poster'><img src='" + this.getImgSrc(params, video) + "' width='100%' height='100%' /></div>"
				+"	<video id='cc_"+ params.vid +"' x-webkit-airplay='allow' webkit-playsinline playsinline='true' " + this.getCross(params, video) + " width='" + params.width + "' height='" + params.height + "' src='" + this.getSrc(params, video) + "'>" + this.getVttTrack(params, video) + "</video>"
				+"	<div id='replaybtn' class='ccH5PlayBtn'></div>"
				+"	<div class='ccH5PlayBtn2'></div>"
				+"	<div class='ccH5AudioBg'><img src='http://p.bokecc.com/images/html5player/ccH5AudioBg.png' ></div>"
				+"	<section class='ccH5FadeOut'>"
				+"		<div class='ccH5ProgressBar'>"
				+"			<div class='ccH5LoadBar'>"
				+"				<div class='ccH5CurrentPro'>"
				+"					<span class='ccH5DragBtn'></span>"
				+"				</div>"
				+"			</div>"
				+"		</div>"
				+"		<span class='ccH5TogglePlay'></span>"
				+"		<div class='ccH5Time'>"
				+"			<em class='ccH5TimeCurrent'>00:00</em><span>/</span><em class='ccH5TimeTotal'>00:00</em>"
				+"		</div>"
				+"		<span class='ccH5vm'>T</span>"
				+"		<div class='ccH5vmdiv'>"
				+"			<div class='ccH5vmbar'>"
				+"				<div class='ccH5vmbarPro'>"
				+"					<span class='ccH5DragVmBtn'></span>"
				+"				</div>"
				+"			</div>"
				+"		</div>"
				+"		<span class='ccH5sp'>" + decodeURIComponent('%E5%B8%B8%E9%80%9F') + "</span>"
				+"		<ul class='ccH5spul'>"
				+"			<li>2" + decodeURIComponent('%E5%80%8D') + "</li>"
				+"			<li>1.5" + decodeURIComponent('%E5%80%8D') + "</li>"
				+"			<li class='selected'>" + decodeURIComponent('%E5%B8%B8%E9%80%9F') + "</li>"
				+"			<li>0.8" + decodeURIComponent('%E5%80%8D') + "</li>"
				+"		</ul>"
				+"		<span class='ccH5hd'>" + this.getCopy(params, video) + "</span>"
				+"		<ul class='ccH5hdul'>"
				+"		</ul>"
				+"		<em class='ccH5FullsBtn'>B</em>"
				+"		<em class='ccH5ExitFullsBtn'>B</em>"
				+"	</section>"
				+"</div>";
		},
		createFlashDiv: function(params){
            return"<div style='width: 100%; background: #000; color: #fff; text-align: center; font-size: 18px;'>"
                   +"   <div style='line-height:" + params.height +"px;'>"
                   +"   <span style='font-size:18px'>"+decodeURIComponent('%E6%82%A8%E8%BF%98%E6%B2%A1%E6%9C%89%E5%AE%89%E8%A3%85flash%E6%92%AD%E6%94%BE%E5%99%A8%EF%BC%8C%E8%AF%B7%E7%82%B9%E5%87%BB')+"<a style='color:#06a7e1' href='http://www.adobe.com/go/getflash' target='_blank'>"+decodeURIComponent('%E8%BF%99%E9%87%8C')+"</a>"+decodeURIComponent('%E5%AE%89%E8%A3%85')+"</span>"
                   +"   </div>"
                   +"</div>";
        },
		createTips: function(params, text){
			var theight = params.height;
			if(!isNaN(params.height)){
				theight = params.height + 'px';
			}
			return"<div style='width: 100%; height:" + theight + "; background: #000;color: #fff; text-align: center; font-size: 18px; display:table;'>"
				+"   <div style='display:table-cell; vertical-align: middle;'>"
				+"   <span id='pcmTips' style='font-size:18px'>"+ text +"</span>"
				+"   </div>"
				+"</div>";
		},
		createFlashView: function(params){
			return "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' "
						+ "codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash." 
						+ "cab#version=7,0,0,0' width='" + params.width + "' height='" + params.height 
						+ "' id='cc_" + params.vid + "'>"
						+ "<param name='movie' value='https://p.bokecc.com/flash/player.swf?vid="
						+ params.vid + "&siteid=" + params.siteid + "&playerid=" + params.playerid
						+ "&playertype=" + params.playertype + "&autoStart=" + params.autoStart + "&mediatype=" + param.mediatype + "' />"
						+ "<param value='transparent' name='wmode' />"
						+ "<param name='allowFullScreen' value='true' />"
						+ "<param name='allowScriptAccess' value='always' />"
						+ "<embed src='https://p.bokecc.com/flash/player.swf?vid="
						+ params.vid + "&siteid=" + params.siteid + "&playerid=" + params.playerid
						+ "&playertype=" + params.playertype
						+ "&autoStart=" + params.autoStart + "&mediatype=" + param.mediatype + "' width='" + params.width + "' height='" + params.height 
						+ "' name='cc_" + params.vid + "' wmode='transparent' allowFullScreen='true' allowScriptAccess='always'"
						+ " pluginspage='http://www.macromedia.com/go/getflashplayer' "
						+ "type='application/x-shockwave-flash'/></object>";
		},
		jsonp: function(url, callback, errorCallback){
			// use setTimeout to handle multiple requests problem, force them in
			// a queue
			var t1 = new Date().getTime();
			setTimeout(function(){
				var head = document.getElementsByTagName("head")[0] || document.documentElement;
				var script = document.createElement("script");
				// add the param callback to url, and avoid cache
				script.src = url + "&callback=" + callback + "&r=" + Math.random() * 10000000;			
				// Use insertBefore instead of appendChild to circumvent an IE6
				// bug.
				// This arises when a base node is used.
				head.insertBefore( script, head.firstChild );
				var hasCallbacked=false;
				script.onload = script.onreadystatechange = function(){
					// use /loaded|complete/.test( script.readyState ) to test
					// IE6 ready,!this.readyState to test FF
					if (!this.readyState || /loaded|complete/.test( script.readyState )) {
						hasCallbacked=true;
						// Handle memory leak in IE
						script.onload = script.onreadystatechange = null;
						if ( head && script.parentNode ) {
							head.removeChild( script );
						}
					}
                    var t2 = new Date().getTime();
                    window.playApiTime = Math.floor(t2 - t1);
				};
				script.onerror =function(){
					hasCallbacked=true;
					if(errorCallback){
						errorCallback();
					}
				};
				setTimeout(function(){
					if(!hasCallbacked){
						if(errorCallback){
							errorCallback();
						}
					}
					if ( head && script.parentNode ) {
							head.removeChild( script );
					}
				},5000);
			},0);
		},
		html5PlayerSkin: function(params, video, ts){
			(function(){
				// player skin
				var css=document.createElement('link'); 
				css.href='https://p.bokecc.com/css/html5player/skin_pc.css?v6.1';  
				if(ts.isIPad() || ts.isWindowsPhoneOS()){
					css.href='https://p.bokecc.com/css/html5player/skin2.css?v6.0';
					if(navigator.userAgent.match('8')){
						css.href='https://p.bokecc.com/css/html5player/skin2_8.0.css?v6.0';
					}
				}
				if(ts.isAndroid() || ts.isIPhone()){
					css.href='https://p.bokecc.com/css/html5player/skin_Android.css?v6.0';
					if(video.uid == "231986"){
						css.href='https://p.bokecc.com/css/html5player/canon_Android.css?v6.1';
					}
					//中意人寿皮肤定制
					if(video.uid == "240837"){
						css.href='https://p.bokecc.com/css/html5player/zyrs_Android.css?v6.1';
					}
				}
				if(ts.isIPad() && video.uid == "231986"){
					css.href='https://p.bokecc.com/css/html5player/canon_ipad.css?v6.0';
				}
				css.rel="stylesheet"; 
				css.type="text/css";
				document.head.appendChild(css);
				
			})();
			function CCHtml5Player(){};
			CCHtml5Player.prototype = {
				addListener: function(element, type, handler){
					element.addEventListener(type, handler, false);
				},
				init: function(id){
					this.oDiv = document.getElementById(id);
					this.oVideo = this.oDiv.getElementsByTagName("video")[0];
					this.adSrc = this.oDiv.getElementsByClassName("skipGg")[0];
					this.adcloseTime = this.oDiv.getElementsByClassName("closeTime")[0];
					this.imgCloseTime = this.oDiv.getElementsByClassName("closePicTime")[0];
					this.picAdSrc = this.oDiv.getElementsByClassName("picBtn")[0];
					this.vadSrc = this.oDiv.getElementsByClassName("vadSrc")[0];
					this.playBtn = this.oDiv.getElementsByClassName("ccH5PlayBtn")[0];
					this.playBtn2 = this.oDiv.getElementsByClassName("ccH5PlayBtn2")[0];
					this.videoBox = this.oDiv.getElementsByClassName("ccH5playerBox")[0];
					this.oLoading = this.oDiv.getElementsByClassName("ccH5Loading")[0];
					this.oInfo = this.oDiv.getElementsByClassName("ccH5Info")[0];
					this.poster = this.oDiv.getElementsByClassName("ccH5Poster")[0]; 
					this.ctrlBar = this.oDiv.getElementsByTagName("section")[0];
					this.loadBar = this.oDiv.getElementsByClassName("ccH5LoadBar")[0];
					this.toggleBtn = this.oDiv.getElementsByClassName("ccH5TogglePlay")[0];
					this.progressBar = this.oDiv.getElementsByClassName("ccH5ProgressBar")[0];
					this.dragBtn = this.oDiv.getElementsByClassName("ccH5DragBtn")[0];
					this.timeCurrent = this.oDiv.getElementsByClassName("ccH5TimeCurrent")[0];
					this.timeTotal = this.oDiv.getElementsByClassName("ccH5TimeTotal")[0];
					this.proCurrent = this.oDiv.getElementsByClassName("ccH5CurrentPro")[0];
					this.fullsBtn = this.oDiv.getElementsByClassName("ccH5FullsBtn")[0];
					this.exitFullsBtn = this.oDiv.getElementsByClassName("ccH5ExitFullsBtn")[0];
					this.hdBtn = this.oDiv.getElementsByClassName("ccH5hd")[0];
					this.hdUL = this.oDiv.getElementsByClassName("ccH5hdul")[0];
					this.spBtn = this.oDiv.getElementsByClassName("ccH5sp")[0];
					this.spUL = this.oDiv.getElementsByClassName("ccH5spul")[0];
					this.spLi = this.spUL.getElementsByTagName("li");
					this.vmBtn = this.oDiv.getElementsByClassName("ccH5vm")[0];
					this.vmDiv = this.oDiv.getElementsByClassName("ccH5vmdiv")[0];
					this.vmDragBtn = this.oDiv.getElementsByClassName("ccH5DragVmBtn")[0];
					this.vmBar = this.oDiv.getElementsByClassName("ccH5vmbar")[0];
					this.vmPro = this.oDiv.getElementsByClassName("ccH5vmbarPro")[0];
					this.advert = this.oDiv.getElementsByClassName("x-advert")[0];
					this.skipAdBtn = this.oDiv.getElementsByClassName("closeAd")[0];
					this.skipPicAd = this.oDiv.getElementsByClassName("closePicAd")[0];
					this.picAd = this.oDiv.getElementsByClassName("picAd")[0];
					this.xAdSec = this.oDiv.getElementsByClassName("x-advert-sec")[0];
					this.waitTime = this.oDiv.getElementsByClassName("skipNum")[0];
					this.waitPicTime = this.oDiv.getElementsByClassName("skipPicNum")[0];
					this.adSec = this.oDiv.getElementsByClassName("adSec")[0];
					this.pSrc = this.oDiv.getElementsByClassName("pSrc")[0];
					this.picTxth = this.oDiv.getElementsByClassName("picTxth")[0];
					this.audioBg = this.oDiv.getElementsByClassName("ccH5AudioBg")[0];
					this.videoMute = false;

					(function(i){ 
						if(!isNaN(params.width) || !isNaN(params.height)){
						   i.videoBox.style.width = params.width + 'px';
						   i.videoBox.style.height = params.height + 'px';
						   if(video.vrmode == 1){
						   		i.videoBox.style.width = window.innerWidth + 'px';
								i.videoBox.style.height = window.innerHeight + 'px';
						   		i.oVideo.style.width = window.innerWidth + 'px';
								i.oVideo.style.height = window.innerHeight + 'px';
						   }
						}
						else{
							i.videoBox.style.width = params.width;
							i.videoBox.style.height = params.height;
						}
					})(this);
					if(ts.isAndroid() || ts.isIPhone()){
						this.ctrlWidth = this.videoBox.clientWidth - 218;
						//中意人寿皮肤定制
						if(video.uid == "240837"){
							this.ctrlWidth = this.videoBox.clientWidth - 214;
						}
					} else if (ts.isIPad()){
						this.ctrlWidth = this.videoBox.clientWidth - 372;
					} else if (ts.isIPad() && video.uid == "231986"){
						//佳能ipad皮肤定制
						this.ctrlWidth = this.videoBox.clientWidth - 220;
					} else {
						this.ctrlWidth = this.videoBox.clientWidth;// - 378;
					}
					
					var _this = this;
					this.addListener(this.playBtn, 'click', function(){_this.play();});
					this.addListener(this.playBtn2, 'click', function(){_this.play2();});
					this.addListener(this.toggleBtn, 'click', function(){_this.togglePlay();});
					this.addListener(this.skipAdBtn, 'click', function(){_this.skipAd();});
					this.addListener(this.skipPicAd, 'click', function(){_this.skipAd();});
					this.addListener(this.oVideo, 'timeupdate', function(){_this.timeupdate();});
					this.addListener(this.fullsBtn, 'click', function(){_this.fullScreen(_this.videoBox);});
					this.addListener(this.exitFullsBtn, 'click', function(){_this.exitFullScreen();});
					this.addListener(this.oVideo, 'pause', function(){_this.pause();});
					this.addListener(this.oVideo, 'playing', function(){_this.playing();});
					this.addListener(this.oVideo, 'progress', function(){_this.progress();});
					this.addListener(this.oVideo, 'ended', function(){_this.ended();});
					this.addListener(this.oVideo, 'webkitendfullscreen', function(){_this.endFullscreen();});
					this.addListener(window, 'orientationchange', function(){_this.autoProgressBar();});
					this.addListener(this.oVideo, 'seeking', function(){_this.seeking();});
					this.addListener(this.oVideo, 'seeked', function(){_this.seeked();});
					this.addListener(this.oVideo, 'waiting', function(){_this.waiting();});
					this.addListener(this.oVideo, 'canplay', function(){_this.canplay();});
					this.addListener(this.oVideo, 'gesturechange', function(e){
						if(e.scale > 1){
							_this.fullScreen();
						}
					});
					this.addListener(this.oVideo, 'touchstart', function(e){
						if(e.touches.length == 2){
							_this.ctrlBar.className = "ccH5FadeIn";
							_this.oVideo.ontouchend = function(){
								_this.togglePlay();
								_this.oVideo.ontouchend = null;
							};
						}
					});
					this.addListener(this.oVideo, 'touchend', function(e){
						if(e.changedTouches.length == 1){
							_this.toggleCtrlBar();
						}
					});
					

					if(ts.isRealtimePlay(video)){
						this.hdBtn.style.display = "none";
					}

					// HD
					this.addListener(this.hdBtn, 'mouseover', function(){_this.toggleHd();});
					this.addListener(this.hdBtn, 'mouseout', function(){_this.toggleHdHide();});
					this.addListener(this.hdUL, 'mouseover', function(){_this.toggleHd();});
					this.addListener(this.hdUL, 'mouseout', function(){_this.toggleHdHide();});
					
					// playbackRate
					this.addListener(this.spBtn, 'mouseover', function(){_this.toggleSp();});
					this.addListener(this.spBtn, 'mouseout', function(){_this.toggleSpHide();});
					this.addListener(this.spUL, 'mouseover', function(){_this.toggleSp();});
					this.addListener(this.spUL, 'mouseout', function(){_this.toggleSpHide();});

					//mute
					this.addListener(this.vmBtn, 'click', function(){_this.mute();});

					//changefullscreen
					this.fullscreenchange();

					var s1 = 1;
					for(i=0; i<4; i++){
						var _this = this;
						this.spLi[i].index = i;
						this.addListener(this.spLi[i], 'click', function(){
							var s = [2, 1.5, 1, 0.8];
							for(j=0; j<4; j++){
								_this.spLi[j].className = "";
							}
							this.className = "selected";

							if(ts.isIPad()){
								_this.oVideo.pause();
							}
							_this.oVideo.playbackRate = s[this.index];

							var url = _this.oVideo.src;

							if (typeof window.changeSpeed === 'function') {
								window.changeSpeed(s1, _this.oVideo.playbackRate, url);
							}
							s1 = _this.oVideo.playbackRate;

							_this.spUL.style.display = 'none';
							_this.spBtn.style.background = 'rgba(51,51,51,0.5)';
							_this.spBtn.innerHTML = this.innerHTML;
							if(ts.isIPad()){
								setTimeout(function(){
									_this.oVideo.play();
								}, 300)
							}
						});
					};
					
					this.setQuality(video);
					
					// vm
					var timer = null;
					function vmShow(){
						_this.vmDiv.style.display = 'block';
						clearTimeout(timer);
					};
					function vmHide(){
						timer = setTimeout(function(){
							_this.vmDiv.style.display = 'none';
						}, 300);
					};
					
					this.addListener(this.vmBtn, 'mouseover', vmShow);
					this.addListener(this.vmBtn, 'mouseout', vmHide);
					this.addListener(this.vmDiv, 'mouseover', vmShow);
					this.addListener(this.vmDiv, 'mouseout', vmHide);
					
					this.dragVm(this.vmDragBtn);

					if(typeof video.vtt !='undefined' && video.vtt){
						var css = 'video::cue(c:past){' +video.vtt.cssStyle+ '}'
						this.addCssByStyle(css);
					}
					if(params.mediatype == 2){
						if(ts.videoAd == 1 && (ts.isIPad() || ts.isIPhone() || ts.isAndroid())){
							this.audioBg.style.display = 'none';
						} else {
							this.audioBg.style.display = 'block';
						}
					}
					if(params.autoStart == 'true' && !(ts.isIPad() || ts.isIPhone() || ts.isAndroid())){
						this.play();
					}
					window.onresize = function(){
						var boxWidth = _this.videoBox.style.width;
						var boxPos = _this.videoBox.style.position;
						var boxIndex = _this.videoBox.style.zIndex;
						var boxTextIndent = _this.videoBox.style.textIndent;
						var boxBackground = _this.videoBox.style.backgroundColor;
						if(boxWidth == '100%' && boxPos == 'fixed' && boxIndex == '9999' && boxTextIndent == '0px' && boxBackground == 'rgb(0, 0, 0)'){
							_this.videoBox.innerHTML = '视频不支持录屏模式下播放'; 
							_this.videoBox.style.color = '#ffffff';
							_this.videoBox.style.textAlign = 'center';
							_this.videoBox.style.lineHeight = '490px';
						}
					}
				},
				autoProgressBar: function(){
					var _this = this;
					setTimeout(function(){
				        if(ts.isAndroid() || ts.isIPhone()){
							_this.ctrlWidth = _this.videoBox.clientWidth - 218;
						} else if (ts.isIPad()){
							_this.ctrlWidth = _this.videoBox.clientWidth - 372;
						} else {
							_this.ctrlWidth = _this.videoBox.clientWidth;
						}
						_this.progress();
						_this.timeupdate();
					},250);
				},
    			openBarrage: function(size,col,dur){
    				ts.CCStyle={"size":20 , "col":0xffffff , "dur":10000};
                    if(typeof size != 'undefined' && size != null){
                        ts.CCStyle.size = size;
                    }
                    if(typeof col != 'undefined' && col != null){
						if(typeof col == 'string' && col.charAt(0) == '#'){
							col = '0x'+col.substring(1);
							col = parseInt(col,'0x');
						}
                        ts.CCStyle.col = col;
                    }
                    if(typeof dur != 'undefined' && dur != null){
                          ts.CCStyle.dur = dur;
                    }
                    if(ts.hasOpenBarrage){
    					return;
    				}
    				ts.hasOpenBarrage = true;
    				var barragebox = document.createElement('div');
					barragebox.innerHTML = this.createBarrageBox();
					this.videoBox.insertBefore(barragebox,this.oVideo);
					ts.loadScript("http://p.bokecc.com/js/player/CommentCoreLibrary.js",function(){
						ts.CM = new CommentManager(document.getElementById('ccBarrage'));
						ts.CM.init(); // 初始化
						ts.CM.start();
					});	
    			},
				sendBarrage: function(mtext,size,col,dur){
					if(!ts.hasOpenBarrage){
						this.openBarrage();
						return;
					}
					if(!ts.CM){
						return;
					}
					if(typeof mtext == "undefined"){
						return;
					}
					if(typeof col == 'string' && col.charAt(0) == '#'){
						col = '0x'+col.substring(1);
						col = parseInt(col,'0x');
					}
                	ts.CM.send({
					    "mode": 1,
					    "text": mtext,
					    "size": size || ts.CCStyle.size,
					    "color": col || ts.CCStyle.col,
					    "dur": dur || ts.CCStyle.dur
					});
                },
                showBarrage: function(){
                	if(!ts.hasOpenBarrage){
						this.openBarrage();
						return;
					}
					document.getElementsByClassName("abp")[0].style.visibility = "visible";
                },
                hideBarrage: function(){
                	if(!ts.hasOpenBarrage){
						return;
					}
					document.getElementsByClassName("abp")[0].style.visibility = "hidden";
                },
                createBarrageBox: function(){
                	return'<div class="abp" style="position:absolute; width:100%; height:100%; z-index:1;"><div id="ccBarrage" class="container" style="perspective: 227.217px;"></div></div>';
                },
				fullscreenchange: function(){
					var _this = this;
					var full = false;
					var list = ['fullscreenchange', 'mozfullscreenchange', 'webkitfullscreenchange', 'msfullscreenchange'];
					for(i in list){
						var type = list[i];
						document.addEventListener(type, function(e) {
					  		full = !full;
						  	if(full == false){
								_this.exitFullsBtn.click();
						  	}
						});
					}
				},
				addCssByStyle: function(cssString){
					var doc=document;
					var style=doc.createElement("style");
					style.setAttribute("type", "text/css");
					var cssText = doc.createTextNode(cssString);
					style.appendChild(cssText);
					var heads = doc.getElementsByTagName("head");
					if(heads.length){
					    heads[0].appendChild(style);
					} else {
					    doc.documentElement.appendChild(style);  
					}
				},
				toggleHd: function(){
					this.hdUL.style.display = 'block';
					this.hdBtn.style.background = 'rgba(255,146,10,0.6)';
					this.spUL.style.display = 'none';
					this.spBtn.style.background = 'rgba(51,51,51,0.5)';
					clearTimeout(window.hdtimer);
				},
				toggleHdHide: function(){
					var _this = this;
					hdtimer = setTimeout(function(){
						_this.hdUL.style.display = 'none';
						_this.hdBtn.style.background = 'rgba(51,51,51,0.5)';
					},200);
				},
				toggleSp: function(){
					this.spUL.style.display = 'block';
					this.spBtn.style.background = 'rgba(255,146,10,0.6)';
					this.hdUL.style.display = 'none';
					this.hdBtn.style.background = 'rgba(51,51,51,0.5)';
					clearTimeout(window.sptimer);
				},
				toggleSpHide: function(){
					var _this = this;
					sptimer = setTimeout(function(){
						_this.spUL.style.display = 'none';
						_this.spBtn.style.background = 'rgba(51,51,51,0.5)';
					},200);
				},
				setQuality: function(video){
					for(i=0; i < video.copies.length; i++){
						var li =document.createElement("li");
						var liText =document.createTextNode(video.copies[i].desp);
						li.appendChild(liText);
						this.hdUL.appendChild(li);
						this.hdUL.style.top = -video.copies.length * 30 + 'px';
					}
					this.switchQuality();
				},
				switchQuality: function(){
					var hdLi = this.hdUL.getElementsByTagName("li");
					for(i=0; i < hdLi.length; i++){
						var _this = this;
						hdLi[i].index = i;
						if(hdLi[i].innerHTML ==  this.hdBtn.innerHTML){
							hdLi[i].className = 'selected';
						}
						if(hdLi.length > 1){
							this.addListener(hdLi[i], 'click', function(){
								for(i=0; i < hdLi.length; i++){
									hdLi[i].className = "";
								}
								this.className = 'selected';
								_this.hdBtn.innerHTML = video.copies[this.index].desp;
								_this.hdUL.style.display = 'none';
								_this.hdBtn.style.background = 'rgba(51,51,51,0.5)';	
								
								var t = _this.oVideo.currentTime;
								var u1= _this.oVideo.src;

								_this.oVideo.src = video.copies[this.index].playurl;
								if (typeof window.get_custom_id === 'function') {
									var flow = encodeURIComponent(get_custom_id());
									_this.oVideo.src = video.copies[this.index].playurl + '&custom_id=' + flow;
								}
								if (typeof window.changeQuality === 'function') {
									window.changeQuality(u1, _this.oVideo.src);
								}

								_this.spBtn.innerHTML = _this.spLi[2].innerHTML;
								for(i=0; i<4; i++){
									_this.spLi[i].className = "";
								}
								_this.spLi[2].className = "selected";
								
								var h = function(){
									_this.oVideo.removeEventListener('canplay', h, false);
									
									_this.setDura(t);
									
									_this.pause();
									setTimeout(function(){
										_this.play();
									}, 300);
								}
								
								_this.addListener(_this.oVideo, 'canplay', h);
							});
						}
					}
				},
				picAdStyle: function(){
					var _this = this;
					var picW = this.pSrc.width;
					var picH = this.pSrc.height;
					var vidW = this.oVideo.offsetWidth;
					var vidH = this.oVideo.offsetHeight;
					var picHeight = vidW*picH/picW;
					var padH = (vidH-picHeight)/2 + 'px';
					if((picW/picH) <= (vidW/vidH)){
						_this.pSrc.style.height = '100%';
					} else {
						_this.pSrc.style.width = '100%';
						_this.pSrc.style.paddingTop = padH;
					}
				},
				videoTime: function(){
					this.oVideo.play();
					var _this = this;
					var s = cc_js_Player.adTime;
					this.advert.style.display = 'block';
				    window.aaa=function(){
				    	s--;
				    	_this.xAdSec.innerHTML=s;
				    	if(s==0){
			    		    _this.skipAdBtn.click();
				    	}
				    }
				    this.addListener(this.oVideo, 'playing', function(){
				    	if(typeof timer === 'undefined'){
                        	timer = setInterval('aaa()',1000);
				    	}
				    	
				    });
				},
				videoTime2: function(){
					this.oVideo.play();
					var _this = this;
					var s = this.xAdSec.innerHTML;
					this.advert.style.display = 'block';
				    window.ccc=function(){
				    	s--;
				    	_this.xAdSec.innerHTML=s;
				    	if(s==0){
			    		    _this.skipAdBtn.click();
				    	}
				    }
			    	timer = setInterval('ccc()',1000);
				},
				picAdTime: function(){
					var s = cc_js_Player.adTime;
					var _this = this;
					this.picAd.style.display = 'block';
					window.bbb=function(){
						s--;
						_this.adSec.innerHTML=s;
						if(s==0){
			    		    _this.skipAdBtn.click();
						}
					}
					timer = setInterval('bbb()',1000);
				},
				waitCloseBtn: function(){
					var s = cc_js_Player.skipTime;
					var _this = this;
					this.adcloseTime.style.display = 'inline-block';
					this.skipAdBtn.style.pointerEvents = 'none';
					window.ddd=function(){
						s--;
						_this.waitTime.innerHTML=s;
						if(s==0){
							clearInterval(timer2);
			    		    _this.adcloseTime.style.display = 'none';
			    		    _this.skipAdBtn.style.pointerEvents = 'auto';
						}
					}
					timer2 = setInterval('ddd()',1000);
				},
				waitPicClose: function(){
					var s = cc_js_Player.skipTime;
					var _this = this;
					this.imgCloseTime.style.display = 'inline-block';
					this.skipPicAd.style.pointerEvents = 'none';
					window.eee=function(){
						s--;
						_this.waitPicTime.innerHTML=s;
						if(s==0){
							clearInterval(timer2);
			    		    _this.imgCloseTime.style.display = 'none';
			    		    _this.skipPicAd.style.pointerEvents = 'auto';
						}
					}
					timer2 = setInterval('eee()',1000);
				},
				play: function(){
					if(typeof timer == 'undefined' && cc_js_Player.videoAd == 1 && (cc_js_Player.isAndroid() || cc_js_Player.isIPad() || cc_js_Player.isIPhone())){
						var format = cc_js_Player.adUrl;
						var pos = format.lastIndexOf('.');
						var lastN = format.substring(pos, format.length);
						if(lastN.toLowerCase() == '.jpg' || lastN.toLowerCase() == '.png' || lastN.toLowerCase() == '.gif'){
							this.adSec.innerHTML = cc_js_Player.adTime;
							this.pSrc.src = cc_js_Player.picSrc;
							this.picAdSrc.href = cc_js_Player.getAdSrc();
							this.picTxth.href = cc_js_Player.getAdSrc();
							if(cc_js_Player.clickurl == ''){
								this.picAdSrc.style.display = 'none';
								this.picTxth.style.display = 'none';
							}
							this.picAdTime();
							if(cc_js_Player.skipTime != 0){
								this.waitPicTime.innerHTML = cc_js_Player.skipTime;
								this.waitPicClose();
							}
						}else{
							this.xAdSec.innerHTML= cc_js_Player.adTime;
							this.adSrc.href = cc_js_Player.getAdSrc();
							this.vadSrc.href = cc_js_Player.getAdSrc();
							if(cc_js_Player.clickurl == ''){
								this.vadSrc.style.display = 'none';
								this.adSrc.style.display = 'none';
							}
							this.videoTime();
							if(cc_js_Player.skipTime != 0){
								this.waitTime.innerHTML = cc_js_Player.skipTime;
								this.waitCloseBtn();
							}
						}
					}
					if(cc_js_Player.canskip == 1){
						this.skipPicAd.style.display = 'block';
						this.skipAdBtn.style.display = 'block';
					}else{
						this.skipPicAd.style.display = 'none';
						this.skipAdBtn.style.display = 'none';
					}
					if(cc_js_Player.canClick == 1 && cc_js_Player.clickurl != ''){
						this.adSrc.style.display = 'block';
						this.picAdSrc.style.display = 'block';
					} else {
						this.adSrc.style.display = 'none';
						this.picAdSrc.style.display = 'none';
					}
					this.oVideo.play();
					this.playBtn.style.display = 'none';
					this.poster.style.display = 'none';
					this.toggleBtn.className = "ccH5TogglePause";
					if(this.oVideo.src == ts.skipSrc(params, video)){
						if(!(ts.isAndroid() || ts.isIPhone() || ts.isIPad())){
							this.toggleFade();
						}
					}
					this.drag(this.dragBtn);
					//this.singleTouch(this.oVideo);
					var _this = this;
					this.progressBar.ontouchstart = this.progressBar.onclick = function(e){
						_this.posDuration(e);
					};
					if(video.authenable == 0 && video.freetime == 0){
						var text = video.authmessage;
	                	if(text == ''){
							text = decodeURIComponent('%E4%B8%8D%E5%85%81%E8%AE%B8%E8%A7%82%E7%9C%8B%E6%88%96%E8%AF%95%E7%9C%8B%E6%97%B6%E9%97%B4%E7%94%A8%E5%B0%BD');
	                	}
						this.videoBox.innerHTML = cc_js_Player.createTips(params, text);
					}
				},
				play2: function(){
					this.playBtn2.style.display = 'none';
					this.videoTime2();
				},
				pause: function(){
					this.oVideo.pause();
					//this.playBtn.show();
					this.toggleBtn.className = "ccH5TogglePlay";
				},
				endFullscreen: function(){
			        if(this.oVideo.clientHeight != screen.height && this.oVideo.src != cc_js_Player.getDefaultCopy(video).playurl && cc_js_Player.isIPhone()){
			            this.playBtn2.style.display = 'block';
			            clearInterval(timer);
			        }
				},
				togglePlay: function(){
					if(this.oVideo.paused){
						this.play();
					} else {
						this.pause();
					}
				},
				playing: function(){
					this.playBtn.style.display = 'none';
					this.poster.style.display = 'none';
					this.toggleBtn.className = "ccH5TogglePause";
				},
				ended: function(){
					if(cc_js_Player.videoAd == 1 && this.oVideo.src != cc_js_Player.getDefaultCopy(video).playurl){
						var adarr=cc_js_Player.materialUrl;
						for(var i=0;i<adarr.length;i++){
							if(adarr[i].material == this.oVideo.src){
								if(i == (adarr.length-1)){
                                    this.oVideo.src = adarr[0].material;
                                    this.adSrc.href = "https://imedia.bokecc.com/servlet/mobile/clickstats?adid="
						+ cc_js_Player.adId + "&clickurl=" + encodeURIComponent(adarr[0].clickurl) + "&materialid="+ adarr[0].materialid;
                                    this.vadSrc.href = "https://imedia.bokecc.com/servlet/mobile/clickstats?adid="
						+ cc_js_Player.adId + "&clickurl=" + encodeURIComponent(adarr[0].clickurl) + "&materialid="+ adarr[0].materialid;
								}else{
                                    this.oVideo.src = adarr[i+1].material;
                                    this.adSrc.href = "https://imedia.bokecc.com/servlet/mobile/clickstats?adid="
						+ cc_js_Player.adId + "&clickurl=" + encodeURIComponent(adarr[i+1].clickurl) + "&materialid="+ adarr[i+1].materialid;
                                    this.vadSrc.href = "https://imedia.bokecc.com/servlet/mobile/clickstats?adid="
						+ cc_js_Player.adId + "&clickurl=" + encodeURIComponent(adarr[i+1].clickurl) + "&materialid="+ adarr[i+1].materialid;
								}
								break;
							}

						}
						this.playBtn.className = "";
						this.oVideo.play();
					} else {
						this.exitFullsBtn.click();
						this.toggleBtn.className = "ccH5TogglePlay";
						this.playBtn.className = "adrPlayBtn";
						this.playBtn.style.display = 'block';
					}
				},
				skipAd: function(){
					clearInterval(timer);
					this.oVideo.src = cc_js_Player.skipSrc(params, video);
					this.oVideo.play();
					this.playBtn2.style.display = 'none';
					this.advert.style.display = 'none';
					this.picAd.style.display = 'none';
					if(video.vrmode == 1){
						window.on_vr_init();
					}
					if(params.mediatype == 2){
						this.audioBg.style.display = 'block';
					}
				},
				seeking: function(){
					this.oLoading.style.display = 'block';
				},
				seeked: function(){
					this.oLoading.style.display = 'none';
				},
				waiting: function(){
					// this.oLoading.show();
				},
				canplay: function(){
					this.oLoading.style.display = 'none';
					if(!this.startPlayed && !(ts.isIPad() || ts.isIPhone() || ts.isAndroid())){
						this.startPlayed = true;
						this.hightlightsInit();
					}
				},
				hightlightsInit: function(){
					if(!!video.videomarks){
						for(var i=0; i<video.videomarks.length; i++){
							var oTime = video.videomarks[i].marktime;
							var oText = video.videomarks[i].markdesc;
							this.createHightlights(oTime, oText);
							var oPos = parseInt(oTime/this.oVideo.duration * this.ctrlWidth);
							if(oPos < 60){
								document.getElementsByClassName('ccHightlightsTips')[i].classList.add('hov');
							}
							if(oPos > (this.ctrlWidth - 60)){
								document.getElementsByClassName('ccHightlightsTips')[i].classList.add('lasthov');
							}
						}
					}
				},
				createHightlights: function(time, text){
					var oSpan = document.createElement('span');
					var videoTime = parseInt(this.oVideo.duration);
					oSpan.className = 'ccHightlights';
					oSpan.style.left = time / videoTime * 100 + '%';
					oSpan.innerHTML = "<span class='ccHightlightsTips'>" + text + "</span>";
					this.progressBar.appendChild(oSpan);
				},
				ctrlFadeIn: function(){
					clearTimeout(window.hide_timer);
					var _this = this;
					this.ctrlBar.className = "ccH5FadeIn";
					window.hide_timer = setTimeout(function(){
						_this.ctrlBar.className = "ccH5FadeOut";
					},4000);
				},
				ctrlFadeOut: function(){
					clearTimeout(window.hide_timer);
					this.ctrlBar.className = "ccH5FadeOut";
				},
				toggleFade: function(){
					var _this = this;
					this.addListener(this.videoBox, 'mouseover', function(){_this.ctrlFadeIn();});
					this.addListener(this.videoBox, 'mousemove', function(){_this.ctrlFadeIn();});
					this.addListener(this.videoBox, 'mouseout', function(){_this.ctrlFadeOut();});
				},
				toggleCtrlBar: function(){
					if(this.ctrlBar.className == "ccH5FadeOut"){
						this.ctrlFadeIn();
					} else {
						this.ctrlFadeOut();
					}
				},
				timeFormat: function(time){
					var t = parseInt(time),
					h,i,s;
					h = Math.floor(t/3600);
					h = h ? (h + ':') : '';
					i = h? Math.floor(t%3600/60) : Math.floor(t/60);
					s = Math.floor(t%60);
					i = i > 9 ? i : '0'+i;
					s = s > 9 ? s : '0'+s;
					return (h + i + ':' + s);	
				},
				timeupdate: function(){
					if(this.oVideo.currentTime > 0.1){
						this.timeTotal.innerHTML = this.timeFormat(this.oVideo.duration);
					}
					this.timeCurrent.innerHTML = this.timeFormat(this.oVideo.currentTime);
					this.proCurrent.style.width = this.oVideo.currentTime/this.oVideo.duration * this.ctrlWidth + 'px';
					if(video.authenable == 0 && this.oVideo.currentTime >= video.freetime){
						var text = video.authmessage;
	                	if(text == ''){
							text = decodeURIComponent('%E4%B8%8D%E5%85%81%E8%AE%B8%E8%A7%82%E7%9C%8B%E6%88%96%E8%AF%95%E7%9C%8B%E6%97%B6%E9%97%B4%E7%94%A8%E5%B0%BD');
	                	}
						this.videoBox.innerHTML = cc_js_Player.createTips(params, text);
					}
				},
				progress: function(){
					if (this.oVideo.buffered && this.oVideo.buffered.length > 0) {
						var progressWidth = Math.round(this.oVideo.buffered.end(0) / this.oVideo.duration * this.ctrlWidth);
						this.loadBar.style.width= progressWidth + "px";
						//中意人寿皮肤定制
						if(video.uid == "240837"){
							this.loadBar.style.width= progressWidth + 12 + "px";
						}
					}
				},
				setDura: function(v){
					this.oVideo.currentTime = v;
				},
				getPos: function(obj){
					if(obj.getBoundingClientRect){
						var pos = obj.getBoundingClientRect();
						return{
							left : pos.left,
							top : pos.top
						};
					}
					var x = e.offsetLeft, y = e.offsetTop;  
					while(e=e.offsetParent){
						x += e.offsetLeft;  
						y += e.offsetTop;
					}
					return{
						left : x,
						top : y
					};
				},
				getMousePos: function(ev){
					var SupportsTouches = ("createTouch" in document);
					var x = y = 0,
					doc = document.documentElement,
					body = document.body;
					if(!ev) ev=window.event;
					if (window.pageYoffset){
						x = window.pageXOffset;
						y = window.pageYOffset;
					}else{
						x = (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc && doc.clientLeft || body && body.clientLeft || 0);
						y = (doc && doc.scrollTop  || body && body.scrollTop  || 0) - (doc && doc.clientTop  || body && body.clientTop  || 0);
					}
					if(SupportsTouches && (ts.isIPad() || ts.isAndroid() || ts.isIPhone())){
						var evt = ev.touches.item(0);
						x=evt.pageX;
						y=evt.pageY;
					}else{
						x += ev.clientX;
						y += ev.clientY;
					}
					return{'x' : x, 'y' : y};
				},
				drag: function(el){
					var _this = this;
					el.ontouchstart = el.onmousedown = function(e){
						e.preventDefault();
						document.ontouchmove = document.onmousemove = function(ev){
							ev.preventDefault();
							_this.pause();
							_this.playBtn.style.display = 'none';
							_this.posDuration(ev);
							_this.oInfo.innerHTML = _this.timeFormat(_this.oVideo.currentTime);
							_this.oInfo.style.display = 'block';
						};
						document.ontouchend = document.onmouseup = function(){
							document.ontouchend = document.onmouseup = document.ontouchmove = document.onmousemove = null;
							_this.oInfo.innerHTML = '';
							_this.oInfo.style.display = 'none';
							setTimeout(function(){
								_this.play();
							}, 100);
						};
					};
				},
				dragVm: function(el){
					var _this = this;
					el.ontouchstart = el.onmousedown = function(e){
						e.preventDefault();
						document.ontouchmove = document.onmousemove = function(ev){
							ev.preventDefault();
							_this.posVm(ev);
						};
						document.ontouchend = document.onmouseup = function(){
							document.ontouchend = document.onmouseup = document.ontouchmove = document.onmousemove = null;
							
						};
					};
				},
				infoFadeIn: function(){
					var _this = this;
					this.oInfo.style.display = 'block';
					clearInterval(timer);
					var timer = setTimeout(function(){
						_this.oInfo.style.display = 'none';
					}, 200);
				},
				pre: function(){
					this.oInfo.innerHTML = "<span class='ccH5playerPre'>L</span>5S";
				},
				next: function(){
					this.oInfo.innerHTML = "<span class='ccH5playerNext'>R</span>5S";
				},
				singleTouch: function(e){
					var _this = this;
					e.ontouchstart = function(e){
						e.preventDefault();
						if(e.changedTouches.length == 1){
							var startX = e.changedTouches[0].pageX;
							document.ontouchend = function(){
								document.ontouchend = document.ontouchmove = null;
								if(e.touches.length == 1){
									var endX = e.changedTouches[0].pageX;
									var offset = endX - startX;
									if(_this.oVideo.currentTime != _this.oVideo.duration){
										if(offset < -50) {
											_this.ctrlFadeIn();
											_this.pre();
											_this.infoFadeIn();
											_this.setDura(_this.oVideo.currentTime - 5);
										} else if(offset > 50) {
											_this.ctrlFadeIn();
											_this.next();
											_this.infoFadeIn();
											_this.setDura(_this.oVideo.currentTime + 5);
										}
									}
								}
							};
						}
					};
				},
				posDuration: function(e){
					var mousePos = this.getMousePos(e),
						probarPos = this.getPos(this.progressBar),
						proCurrent = mousePos.x - probarPos.left,
						time = proCurrent/this.ctrlWidth * this.oVideo.duration;
					this.setDura(time);
				},
				mute: function(){
					this.videoMute = !this.videoMute;
					if (this.videoMute && this.oVideo.volume != 0){
						this.vmPro.style.top = 80 + 'px';
						this.vmPro.style.height = 0 + 'px';
						this.oVideo.volume = 0;
						this.vmBtn.style.backgroundPosition = 'right center';
					} else {
						this.vmPro.style.top = 20 + 'px';
						this.vmPro.style.height = 60 + 'px';
						this.oVideo.volume = 0.8;
						this.vmBtn.style.backgroundPosition = 'left center';
					}
				},
				setVol: function(v){
					this.oVideo.volume = v;
				},
				posVm: function(e){
					var mousePos = this.getMousePos(e).y - window.pageYOffset,
						vmbarPos = this.getPos(this.vmBar).top;
					var h = mousePos - vmbarPos;
					if(h < 80 && h > 0){
						var t = this.vmPro.style.top = h + 'px';
						var vh = this.vmPro.style.height = 80 - h + 'px';
						vol = ((80 - h) / 80).toFixed(1);
						this.setVol(vol);
						if(vol == 0){
							this.vmBtn.style.backgroundPosition = 'right center';
							this.videoMute = true;
						} else {
							this.vmBtn.style.backgroundPosition = 'left center';
							this.videoMute = false;
						}
					}
				},
				fullScreen: function(el) {
					if(ts.isAndroid() || ts.isIPhone() || ts.isIPad() || ts.isWindowsPhoneOS()){
						if (this.oVideo.requestFullscreen) {
							this.oVideo.requestFullscreen();
						} else if (this.oVideo.msRequestFullscreen) {
							this.oVideo.msRequestFullscreen();
						} else if (this.oVideo.mozRequestFullScreen) {
							this.oVideo.mozRequestFullScreen();
						} else if (this.oVideo.webkitSupportsFullscreen) {
							this.oVideo.webkitEnterFullscreen();
						}
					} else {
						var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;
						if (typeof rfs != "undefined" && rfs) {
							rfs.call(el);
						}
						this.fullsBtn.style.display = "none";
						this.exitFullsBtn.style.display = "block";
						this.videoBox.classList.add('ccH5PlayerBoxFixed');
						this.ctrlWidth = this.videoBox.clientWidth;
						this.progress();
						this.timeupdate();
					}
				},
				exitFullScreen: function(el) {
					if(ts.isAndroid() || ts.isIPhone() || ts.isIPad() || ts.isWindowsPhoneOS()){
						if (this.oVideo.exitFullscreen) {
					        this.oVideo.exitFullscreen();
					    } else if (this.oVideo.msExitFullscreen) {
					    	this.oVideo.msExitFullscreen();
					    } else if (this.oVideo.mozExitFullScreen) {  
					        this.oVideo.mozExitFullScreen();  
					    } else if (this.oVideo.webkitExitFullscreen) {  
					        this.oVideo.webkitExitFullscreen();  
					    }
					} else {
						var el = document,
							cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen;
						if (typeof cfs != "undefined" && cfs) {
							cfs.call(el);
						}
						this.fullsBtn.style.display = "block";
						this.exitFullsBtn.style.display = "none";
						this.videoBox.classList.remove('ccH5PlayerBoxFixed');
						this.ctrlWidth = this.videoBox.clientWidth;
						this.progress();
						this.timeupdate();
					}
				}
			};
			window.oPlayer = new CCHtml5Player();
			oPlayer.init(params.divid);
		},
		getScript: function (url, success) {
			var _this = this;
            var readyState = false,
            script = document.createElement('script');
            script.src = url;
            
            script.onload = script.onreadystatechange = function () {
                if (!readyState && (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
                    readyState = true;
                    success && success();
                    _this.loadJsArr[url]=1;
                }
            };
            document.body.appendChild(script);
        },
        loadScript: function (res, callback) {
            this.loadJsArr = {}; 
            if (typeof res === 'string') {
                var _res = res;
                res = [];
                res.push(_res);
                
            }
            for(var i=0;i<res.length;i++ ){
                this.loadJsArr[res[i]]=0;
            }
            
            var _this = this,
            queue = function (fs, cb) {
                _this.getScript(fs.shift(), function () {
                    fs.length ? queue(fs, cb) : cb && cb();
                });
            };

            queue(res, callback);
        },
		showPlayerView: function(video){
			if(video.vrmode == 1){
				this.loadScript([
					"http://p.bokecc.com/js/player/three.min.js",
	                "http://p.bokecc.com/js/player/DeviceOrientationControls.js",
	                "http://p.bokecc.com/js/player/ccvr.js"
				],function(){
					var isover = true;
                    for(var n in this.loadJsArr){
                       if(_this.loadJsArr[n] == 0){
                           isover = false;
                       }
                    }
                    if(isover && cc_js_Player.videoAd != 1 ){
                       window.on_vr_init();
                    }
				});
			}
			// callback to show video
			var video_div = document.getElementById(video.divid);
			for(var i = 0;i < this.videoInfo.length;i++){
				var params = this.videoInfo[i];
				if(params.divid == video.divid){
					// show video
					video_div.innerHTML = this.getVideoCode(params, video);

					//on_cc_h5player_init
					if (typeof window.on_cc_h5player_init === 'function') {
						window.on_cc_h5player_init();
					}

					if(this.isAndroid() || this.isIPhone() || this.isIPad() || this.isWindowsPhoneOS()) {
						// play statistic
						var vm = document.createElement("script");
						window.upid = video.UPID;
						vm.src = "https://p.bokecc.com/js/player/statistic.js?v20161219";
						document.head.appendChild(vm);
						vm.onload = function () {
							var videoMonitor = new VideoMonitor({
								uid: params.siteid,
								vid: params.vid,
								video: 'cc_' + params.vid
							});
							videoMonitor.start();

							if (typeof window.readyComplete === 'function') {
								window.readyComplete();
							}
						};
					}

					if(this.isAndroid() || video.playtype == 1 || this.isIPad() || this.isWindowsPhoneOS() || this.isIPhone()){
						if(video.status == 1 || this.isRealtimePlay(video)){
							var ts = this;
							this.html5PlayerSkin(params, video, ts);
						}
					}
					
					return;
				}
			}
		}
	};
	// Expose to global
	window.cc_js_Player = new Player();
// End self-executing function
})(window);