<!doctype html>
<!--
COPYRIGHT by HighHay/Mivfx
Before using this theme, you should accept themeforest licenses terms.
http://themeforest.net/licenses
-->

<html class="no-js" lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
	<#assign staticPath=''>
        <!-- Page Title Here -->
        <title></title>
		
        <!-- Page Description Here -->
		<meta name="description" content="A responsive coming soon template, un template HTML pour une page en cours de construction">

        <!-- Disable screen scaling-->
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, user-scalable=0">

        <!-- Place favicon.ico and apple-touch-icon(s) in the root directory -->
        
        <!-- Initializer -->
        <link rel="stylesheet" href="${staticPath}/arrnging/css/normalize.css">
        
        <!-- Web fonts and Web Icons -->
        <link rel="stylesheet" href="${staticPath}/arrnging/css/pageloader.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/fonts/opensans/stylesheet.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/fonts/asap/stylesheet.css">
        <link rel="stylesheet" href=" https://cdn.bootcss.com/ionicons/1.5.2/css/ionicons.min.css">

        <!-- Vendor CSS style -->
        <link rel="stylesheet" href="${staticPath}/arrnging/css/foundation.min.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/css/jquery.fullPage.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/css/vegas.min.css">

		<!-- Main CSS files -->
        <link rel="stylesheet" href="${staticPath}/arrnging/css/main.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/css/main_responsive.css">
        <link rel="stylesheet" href="${staticPath}/arrnging/css/style-font1.css">
		<link rel="stylesheet" type="text/css" href="${staticPath}/arrnging/css/styles.css" />
        <script src="${staticPath}/arrnging/js/modernizr-2.7.1.min.js"></script>
    </head>
    <body id="menu" class="alt-bg">
        <!--[if lt IE 8]>
            <p class="browsehappy">你是用的是 <strong>低版本</strong> 浏览器. 请 <a href="http://browsehappy.com/">请升级你的浏览器</a> 以改善你的体验。</p>
        <![endif]-->

        <!-- Site config : put launching date here
             data-date="01/31/2015 23:00:00"        Launching date
             data-date-timezone="+0"                Lanching date time zone
-->



        <!-- Page Loader -->
        <div class="page-loader" id="page-loader">
            <div><i class="ion ion-loading-d"></i><p>loading</p></div>
        </div>
        
        <!-- BEGIN OF site header Menu -->
		<!-- Add "material" class for a material design style -->
		<header class="header-top">
<!--		<header class="header-top material">-->
			<div class="logo">
				<a href="#home">
					<img src="/arrnging/img/lmlogo.png" alt="北京市力迈中美学校">
					<span>北京力迈中美学校</span>
				</a>
			</div>

		</header>
        <!-- END OF site header Menu-->
        
        <!-- BEGIN OF Quick nav icons at left -->
		<!--<nav class="quick-link count-6 nav-left">
			<ul id="qmenu">
				<li data-menuanchor="home">
					<a href="#home" class=""><i class="icon ion ion-home"></i></a>
					<span class="title">主页</span>
				</li>
				<li data-menuanchor="when">
					<a href="#when" class=""><i class="icon ion ion-bookmark"></i></a>
					<span class="title">基本资料</span>
				</li>

				<li data-menuanchor="class">
					<a href="#class"><i class="icon ion ion-calendar"></i></a>
					<span class="title">课程表</span>
				</li>
				<li data-menuanchor="honor">
					<a href="#honor"><i class="icon ion ion-star"></i></a>
					<span class="title">荣誉表彰</span>
				</li>
				<li data-menuanchor="register">
					<a href="#register"><i class="icon ion ion-shopping-cart">¥</i></a>
					<span class="title">缴费项目</span>
				</li>
				<li data-menuanchor="talk">
					<a href="#talk"><i class="icon ion ion-compose"></i></a>
					<span class="title">教师点评</span>
				</li>
				<li data-menuanchor="progress">
					<a href="#progress"><i class="icon ion ion-android-information"></i></a>
					<span class="title">教学计划</span>
				</li>
				<li data-menuanchor="entrance">
					<a href="#entrance"><i class="icon ion-leaf"></i></a>
					<span class="title">升学指导</span>
				</li>

				<li data-menuanchor="contact">
					<a href="#contact/message"><i class="icon ion-email"></i></a>
					<span class="title">写信</span>
				</li>
			</ul>
		</nav>-->
        <!-- END OF Quick nav icons at left -->
        


        <!-- BEGIN OF site cover -->
        <div class="page-cover" id="home">
            <!-- Cover Background -->
            <div class="cover-bg pos-abs full-size bg-img" data-image-src="img/bg-default.jpg"></div>
			
            <!-- BEGIN OF Slideshow Background -->
            <div class="cover-bg pos-abs full-size slide-show">
				<img class='img' data-src='/arrnging/img/slide.jpg'>
				<img class='img' data-src='/arrnging/img/bg-slide1.jpg'>
				<img class='img' data-src='/arrnging/img/bg-slide3.jpg'>
			<!--	<i class='img' data-src='./img/bg-slide1.jpg'></i>-->

			</div>
			<!-- Solid color as filter or as background -->
            <div class="cover-bg pos-abs full-size bg-color" data-bgcolor="rgba(51, 2, 48, 0.12)"></div>
            
        </div>
        <!--END OF site Cover -->
        
        <!-- BEGIN OF site main content content here -->
        <main class="page-main" id="mainpage">
			<!-- Begin of home page主页 -->
			<div class="section page-home page page-cent" id="s-home">
				<!-- Logo -->
				<div class="logo-container">
					<img class="h-logo" src="/arrnging/img/icon.jpg" alt="name">
					<h3>Hello ! <b>Audrey Hepburn</b></h3>
				</div>
				<!-- Content -->
				<section class="content">
					
					<header class="header">
						<div class="h-left">
							<h2>关于 <strong>梦想</strong></h2>
						</div>
						<div class="h-right">
							<h3>我&nbsp;&nbsp;&nbsp;想 <br>大&nbsp;声&nbsp;说</h3>
						</div>

						<h4 class="subhead">梦想，是对未来的一种期望，指在现在想未来的事或是可以达到但必须努力才可以达到的情况，梦想就是一种让你感到坚持就是幸福的东西，甚至其可以视为一种信仰。</h4>

					</header>
				</section>
				
				<!-- Scroll down button -->
                <footer class="p-footer p-scrolldown">
                    <a href="#when">
                        <div class="arrow-d">
							<!--<div class="before"></div>
							<div class="after"></div>-->
							<div class="circle"></div>
						</div>
                    </a>                        
                </footer>
			</div>
            <!-- Begin of when page 个人资料-->
            <div class="section page-when page page-cent" id="s-when">
                <section class="content">
					<header class="p-title">
						<h3>Audrey Hepburn</h3>
					</header>
					<div class="left">
						<article class="text">
							<p>学号：lmf20170102</p>
							<p>性别：女</p>
							<p>国籍：中国</p>
							<p>身份信息：</p>
							<p>学生来源：网络</p>
							<p>家长邮箱：sds163.com</p>
							<p>家长电话：13345654565</p>

						</article>
					</div>
					<div class="right">
						<article class="text">
							<p>学部：费尔蒙特</p>
							<p>入学年级：一年级</p>
							<p>教学主管：瓦尔</p>
							<p>班主任：瓦尔</p>
							<p>入学情况：优秀</p>
							<p>招生老师：王文</p>
							<p>学籍所在地：</p>
						</article>
					</div>
                    <div class="clock clock-countdown hidden">
						<div class="site-config" data-date="10/31/2015 23:00:00" data-date-timezone="+0"></div>
                    </div>

                </section>                
                <footer class="p-footer p-scrolldown">
                    <a href="#results">
                        <div class="arrow-d">
							<div class="circle"></div>
						</div>
                    </a>                        
                </footer>
            </div>
			<!-- Begin of when page 获奖记录-->
			<div class="section page-when page page-cent " id="s-results">
				<section class="content">
					<header class="p-title">
						<h3>获奖经历</h3>
					</header>
				<div class="left">
					<article class="text">
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖荣获北京市《少儿英语大赛》一等奖荣获北京市《少儿英语大赛》一等奖荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
						<p>2018-08-05：荣获北京市《少儿英语大赛》一等奖</p>
					</article>
				</div>

					<div class="clock clock-countdown hidden">
						<div class="site-config" data-date="10/31/2015 23:00:00" data-date-timezone="+0"></div>
					</div>

				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#transcript">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!--成绩-->
			<div class="section page-class page page-cent " id="s-transcript">
				<section class="content">
					<header class="p-title">
						<h3>成绩表</h3>
					</header>
					<div class="table-re">
						<table class="table">
							<thead>

							<tr>
								<th></th>
								<th>语文</th>
								<th>数学</th>
								<th>英语</th>
								<th>化学</th>
								<th>物理</th>
								<th>政治</th>
								<th>历史</th>
							</tr>
							</thead>
							<tbody>

							<tr>
								<td rowspan="1">上学期</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
							</tr>
							<tr>
								<td>评语</td>
								<td colspan="7" align="left" class="text-left">
									我们永远都在崇拜着那些闪闪发亮的人。我们永远觉得他们像是神祗一样的存在。他们用强大而无可抗拒的魅力和力量征服着世界。但是我们永远不知道，他们用什么样的代价，去换来了闪亮的人生。
								</td>
							</tr>
							<tr>
								<td rowspan="1">下学期</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
							</tr>
							<tr>
								<td>评语</td>
								<td colspan="7"  align="left" class="text-left">
									我们永远都在崇拜着那些闪闪发亮的人。我们永远觉得他们像是神祗一样的存在。他们用强大而无可抗拒的魅力和力量征服着世界。但是我们永远不知道，他们用什么样的代价，去换来了闪亮的人生。
								</td>
							</tr>
							</tbody>
						</table>

					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#honor">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!-- Begin of teacher page 老师资料-->
			<div class="section page  page-cent page-teacher " id="s-teacher">

				<section class="content">
					<header class="p-title teacher-title">
						<h3>老师档案</h3>
					</header>
					<div class="v_out v_out_p">
						<div class="v_show">
							<div class="v_cont">
								<ul>
									<li index="0">
										<div class="cus-name">
											<span>姓名：王文</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们活在浩瀚的宇宙里，我们活在浩瀚的宇宙里我们活在浩瀚的宇宙里我们活在浩瀚的宇宙里我们活在浩瀚的宇宙里漫天漂浮的宇宙尘埃和星河光尘，我们是比这些还要渺小的存在。你并不知道生活在什么时候突然改变方向，陷入墨水一般浓稠的黑暗里去。你被失望拖进深渊，你被疾病拉进坟墓，你被挫折践踏的体无完肤，你被嘲笑、被讽刺、被讨厌、被怨恨、被放弃。但是我们却总在内心里保留着希望保留着不甘心放弃跳动的心。我们依然在大大的绝望里小小的努力着。这种不想放弃的心情，它们变成无边黑暗的小小星辰。我们都是小小的星辰。</p>
										</div>
									</li>
									<li index="1">
										<div class="cus-name">
											<span>姓名：王文</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们太年轻，以致都不知道以后的时光，竟然那么长，长得足够让我忘记你，足够让我重新喜欢一个人，就像当初喜欢你那样。</p>
										</div>
									</li>

									<li index="2">
										<div class="cus-name">
											<span>姓名：王文</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 当青春变成旧照片，当旧照片变成回忆，当我们终于站在分叉的路口，孤独，失望，彷徨，残忍，上帝打开了那扇窗，叫做成长的大门。</p>
										</div>
									</li>

									<li index="3">
										<div class="cus-name">
											<span>姓名：露芽</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们活在浩瀚的宇宙里，漫天漂浮的宇宙尘埃和星河光尘，我们是比这些还要渺小的存在。你并不知道生活在什么时候突然改变方向，陷入墨水一般浓稠的黑暗里去。你被失望拖进深渊，你被疾病拉进坟墓，你被挫折践踏的体无完肤，你被嘲笑、被讽刺、被讨厌、被怨恨、被放弃。但是我们却总在内心里保留着希望保留着不甘心放弃跳动的心。我们依然在大大的绝望里小小的努力着。这种不想放弃的心情，它们变成无边黑暗的小小星辰。我们都是小小的星辰。</p>
										</div>
									</li>

									<li index="4">
										<div class="cus-name">
											<span>姓名：伊娃</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们活在浩瀚的宇宙里，漫天漂浮的宇宙尘埃和星河光尘，我们是比这些还要渺小的存在。你并不知道生活在什么时候突然改变方向，陷入墨水一般浓稠的黑暗里去。你被失望拖进深渊，你被疾病拉进坟墓，你被挫折践踏的体无完肤，你被嘲笑、被讽刺、被讨厌、被怨恨、被放弃。但是我们却总在内心里保留着希望保留着不甘心放弃跳动的心。我们依然在大大的绝望里小小的努力着。这种不想放弃的心情，它们变成无边黑暗的小小星辰。我们都是小小的星辰。</p>
										</div>
									</li>
									<li index="5">
										<div class="cus-name">
											<span>姓名：伊娃</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们活在浩瀚的宇宙里，漫天漂浮的宇宙尘埃和星河光尘，我们是比这些还要渺小的存在。你并不知道生活在什么时候突然改变方向，陷入墨水一般浓稠的黑暗里去。你被失望拖进深渊，你被疾病拉进坟墓，你被挫折践踏的体无完肤，你被嘲笑、被讽刺、被讨厌、被怨恨、被放弃。但是我们却总在内心里保留着希望保留着不甘心放弃跳动的心。我们依然在大大的绝望里小小的努力着。这种不想放弃的心情，它们变成无边黑暗的小小星辰。我们都是小小的星辰。</p>
										</div>
									</li>
									<li index="6">
										<div class="cus-name">
											<span>姓名：伊娃</span>|
											<span class="ml">科目：语文老师</span>|
											<span class="ml">电话：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> 我们活在浩瀚的宇宙里，漫天漂浮的宇宙尘埃和星河光尘，我们是比这些还要渺小的存在。你并不知道生活在什么时候突然改变方向，陷入墨水一般浓稠的黑暗里去。你被失望拖进深渊，你被疾病拉进坟墓，你被挫折践踏的体无完肤，你被嘲笑、被讽刺、被讨厌、被怨恨、被放弃。但是我们却总在内心里保留着希望保留着不甘心放弃跳动的心。我们依然在大大的绝望里小小的努力着。这种不想放弃的心情，它们变成无边黑暗的小小星辰。我们都是小小的星辰。</p>
										</div>
									</li>

								</ul>
							</div>
						</div>

						<ul class="circle">
							<li class="circle-cur"><img src="${staticPath}/arrnging/img/1.jpg"></li>
							<li><img src="${staticPath}/arrnging/img/2.jpg" /></li>
							<li><img src="${staticPath}/arrnging/img/3.jpg"></li>
							<li><img src="${staticPath}/arrnging/img/4.jpg"></li>
							<li><img src="${staticPath}/arrnging/img/5.jpg"></li>
							<li><img src="${staticPath}/arrnging/img/6.jpg"></li>

						</ul>

					</div>


				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#class">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>

			<!--class  课程表-->
			<div class="section page-class page page-cent " id="s-class">
				<section class="content">
					<header class="p-title">
						<h3>课程表</h3>
					</header>
					<div class="table-re">
						<table class="table">
							<thead>
							<tr class="colspan">
								<th align="center" colspan="9">本学期课程表</th>
							</tr>
							<tr>
								<th></th>
								<th>课节</th>
								<th>周一</th>
								<th>周二</th>
								<th>周三</th>
								<th>周四</th>
								<th>周五</th>
								<th>周六</th>
								<th>周日</th>
							</tr>
							</thead>
							<tbody>

							<tr>
								<td rowspan="2">上午</td>
								<td>第一节</td>
								<td>数学</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
							</tr>
							<tr>

								<td>第二节</td>
								<td>数学</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
							</tr>
							<tr>
								<td rowspan="3">下午</td>
								<td>第三节</td>
								<td>数学</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
							</tr>
							<tr>

								<td>第四节</td>
								<td>数学</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
							</tr>
							<tr>

								<td>第五节</td>
								<td>数学</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
								<td>外语</td>
							</tr>
							<tr class="colspan">
								<td align="center" colspan="9">个性化选修课</td>

							</tr>
							<tr>

								<td></td>
								<td>音乐</td>
								<td></td>
								<td>舞蹈</td>
								<td></td>
								<td></td>
								<td></td>
								<td>美术</td>
								<td>美术</td>
							</tr>
							<tr>

								<td></td>
								<td>音乐</td>
								<td></td>
								<td>舞蹈</td>
								<td></td>
								<td></td>
								<td></td>
								<td>美术</td>
								<td>美术</td>
							</tr>
							</tbody>
						</table>

					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#honor">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!--荣誉表彰honor-->
			<div class="section page-class page page-cent " id="s-honor">
				<section class="content-">
					<header class="p-title">
						<h3>获得荣誉</h3>
					</header>
					<div class="main-timeline">
						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">高尔夫环球赛<span class="hide-time">时间：08-04</span></h3>

								<p class="description">
									优秀的业绩不是一个人所能铸造的，

									而是一群人，

									我们称之为团

									队。集团的各项荣誉离不开一支支团结奋进、爱岗敬业、无私奉献的

									项目团队。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">高尔夫环球赛<span class="hide-time">时间：08-04</span></h3>
								<p class="description">
									优秀的业绩不是一个人所能铸造的，

									而是一群人，

									我们称之为团

									队。集团的各项荣誉离不开一支支团结奋进、爱岗敬业、无私奉献的

									项目团队。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">高尔夫环球赛<span class="hide-time">时间：08-04</span></h3>
								<p class="description">
									优秀的业绩不是一个人所能铸造的，

									而是一群人，

									我们称之为团

									队。集团的各项荣誉离不开一支支团结奋进、爱岗敬业、无私奉献的

									项目团队。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">高尔夫环球赛<span class="hide-time">时间：08-04</span></h3>
								<p class="description">
									优秀的业绩不是一个人所能铸造的，

									而是一群人，

									我们称之为团

									队。集团的各项荣誉离不开一支支团结奋进、爱岗敬业、无私奉献的

									项目团队。
								</p>
							</div>
						</div>
					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#club">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!--俱乐部club-->
			<div class="section page-class page page-cent " id="s-club">
				<section class="content">
					<header class="p-title">
						<h3>我的俱乐部</h3>
					</header>
					<div class="content_photo">
						<img class="img01" src="${staticPath}/arrnging/img/1.jpg">
						<img class="img02" src="${staticPath}/arrnging/img/2.jpg">
						<img class="img03" src="${staticPath}/arrnging/img/3.jpg">
						<img class="img04" src="${staticPath}/arrnging/img/4.jpg">
						<img class="img05" src="${staticPath}/arrnging/img/5.jpg">
						<img class="img06" src="${staticPath}/arrnging/img/6.jpg">
						<img class="img07" src="${staticPath}/arrnging/img/7.jpg">
						<img class="img08" src="${staticPath}/arrnging/img/8.jpg">
						<img class="img09" src="${staticPath}/arrnging/img/9.jpg">
					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#register">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!-- Begin of register page 财务缴费状况-->
            <div class="section page-register page page-cent " id="s-register">
                <section class="content">

					<div class="clock clock-countdown">

						<header class="header">
							缴费<strong>项目</strong>
						</header>
						<div class="elem-over">
							<div class="digit">¥2500000</div>

						</div>
						<div class="elem-left">
							<div class="digit">课外活动:¥500</div>

						</div>
						<div class="elem-center">
							<div class="digit">学费:¥15,0000</div>

						</div>
						<div class="elem-mid">
							<div class="digit">服装:¥500</div>

						</div>
						<div class="elem-right">
							<div class="digit">服装:¥500</div>

						</div>
						<div class="second">
							<div style="display:inline;width:400px;height:400px;"></div>
						</div>
					</div>

				</section>
                <footer class="p-footer p-scrolldown">
                    <a href="#talk">
                        <div class="arrow-d">
							<div class="circle"></div>
						</div>
                    </a>                        
                </footer>
            </div>
			<!--begin of talk page老师点评-->
			<div class="section page-talk page page-cent " id="s-talk">
				<section class="content">
					<header class="p-title">
						<h3>教师点评</h3>
					</header>
					<div id="timeline">

						<ul id="dates">

							<li><a href="#1">08-08</a></li>

							<li><a href="#2">08-09</a></li>

							<li><a href="#3">08-20</a></li>

							<li><a href="#4">08-21</a></li>

							<li><a href="#5">10-8</a></li>

							<li><a href="#6">12-1</a></li>

							<li><a href="#7">12-3</a></li>

						</ul>

						<ul id="issues">

							<li id="1">
								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="2">

								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="3">

								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="4">

								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="5">

								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="6">


								<h3>语文教师</h3>

								<p>“虚惊一场”这四个字是人世间最好的成语，比起什么兴高采烈，五彩缤纷，一帆风顺都要美好百倍。你可懂什么叫失去。</p>

							</li>

							<li id="7">


								<h1>12-3</h1>

								<p>Donec semper quam scelerisque tortor dictum gravida. In hac habitasse platea dictumst. Nam pulvinar, odio sed rhoncus suscipit, sem diam ultrices mauris, eu consequat purus metus eu velit. Proin metus odio, aliquam eget molestie nec, gravida ut sapien. Phasellus quis est sed turpis sollicitudin venenatis sed eu odio. Praesent eget neque eu eros interdum malesuada non vel leo. Sed fringilla porta ligula.</p>

							</li>

						</ul>

						<div id="grad_left"></div>

						<div id="grad_right"></div>

						<a href="#" id="next">+</a>

						<a href="#" id="prev">-</a>

					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#progress">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!--教学进度-->
			<div class="section page-progress page page-cent" id="s-progress">
				<section class="content">
					<header class="p-title">
						<h3>教学计划
						</h3>
						<!--	<h2>学业规划 <span class="bold">make</span> only <span class="bold">beautiful</span> things</h2>
                       --> </header>
					<div class="text">

						<div class="col-md-offset-3">
							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										数学
										<span class="progress_number">89%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #1abc9c; width: 89%;" data-value="89" data-percentage-value="89"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										数学
										<span class="progress_number">75%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #fdba04; width: 75%;" data-value="75" data-percentage-value="75"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										数学
										<span class="progress_number">65%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #049dff; width: 65%;" data-value="65" data-percentage-value="65"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										数学
										<span class="progress_number">95%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #ed687c; width: 95%;" data-value="95" data-percentage-value="95"></span>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="p-footer p-scrolldown">
					<a href="#entrance">
						<div class="arrow-d">

							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!-- Begin of entrance page 升学指导 -->
            <div class="section page-entrance page page-cent" id="s-entrance">
                <section class="content">
                    <header class="p-title">
                        <h3>升学指导
                        </h3>
					<!--	<h2>学业规划 <span class="bold">make</span> only <span class="bold">beautiful</span> things</h2>
                   --> </header>
                    <article class="text">
                        <p><strong>现状分析：</strong>如何让你遇见我，在我最美丽的时刻
							为这，我已在佛前求了五百年，求佛让我们结一段尘缘，佛於是把我化做一棵树，长在你必经的路旁，阳光下，慎重地开满了花，朵朵都是我前世的盼望，当你走近，请你细听，那颤抖的叶，是我等待的热情，而当你终於无视地走过，在你身後落了一地的，朋友啊，那不是花瓣，那是我凋零的心</p>
						<p><strong>学业规划：</strong>如何让你遇见我，在我最美丽的时刻
							为这，我已在佛前求了五百年，求佛让我们结一段尘缘，佛於是把我化做一棵树，长在你必经的路旁，阳光下，慎重地开满了花，朵朵都是我前世的盼望，当你走近，请你细听，那颤抖的叶，是我等待的热情，而当你终於无视地走过，在你身後落了一地的，朋友啊，那不是花瓣，那是我凋零的心</p>

                    </article>
                </section>
                <footer class="p-footer p-scrolldown">
                    <a href="#end">
                        <div class="arrow-d">

							<div class="circle"></div>
						</div>
                    </a>                        
                </footer>
            </div>

			<!-- Begin of end page主页 -->
			<div class="section page-home page page-cent" id="s-end">
				<!-- Logo -->
				<div class="logo-container">
					<img class="h-logo" src="${staticPath}/arrnging/img/icon.jpg" alt="name">
					<h3>Bye ! <b>Audrey Hepburn</b></h3>
				</div>
				<!-- Content -->
				<section class="content">

					<header class="header">
						<div class="h-left">
							<h2>家长 <strong>期望</strong></h2>
						</div>
						<!--<div class="h-right">
							<h3>&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;</h3>
						</div>-->
						<br>
						<br><br>

						<h4 class="subhead">我们永远都在崇拜着那些闪闪发亮的人。我们永远觉得他们像是神祗一样的存在。他们用强大而无可抗拒的魅力和力量征服着世界。但是我们永远不知道，他们用什么样的代价，去换来了闪亮的人生。</h4>

					</header>
				</section>

				<!-- Scroll down button -->
				<footer class="p-footer p-scrolldown">
					<a href="#contact">
						<div class="arrow-d">
							<div class="circle"></div>
						</div>
					</a>
				</footer>
			</div>
			<!-- Begin of Contact page  联系我们 -->
            <div class="section page-contact page page-cent  bg-color" data-bgcolor="rgba(95, 25, 208, 0.88)s" id="s-contact">
				<!-- Begin of contact information -->
				<div class="slide" id="information" data-anchor="information">
					<section class="content">
						<header class="p-title">
							<h3>Contact
							</h3>
							<ul class="buttons">
								<li class="show-for-medium-up">
									<a title="home" href="#home"><i class="ion ion-android-information"></i></a>
								</li>
								<!--<li>
									<a title="Contact" href="#contact/information"><i class="ion ion-location"></i></a>
								</li>-->
								<li>
									<a title="Message" href="#contact/message"><i class="ion ion-email"></i></a>
								</li>
							</ul>
						</header>
						<!-- Begin Of Page SubSction -->
						<div class="contact">
							<div class="row">
								<div class="medium-6 columns left">
									<ul>
										<li>
											<h4></h4>
											<p>电话：400-102-8081</p>
										</li>
										<li>
											<h4></h4>
											<p>地址：北京市朝阳区仰山路2号
											</p>
										</li>
										<li>
											<h4></h4>
											<p>学校邮箱：lmzmxx@163.com</p>
										</li>
										<li>	<p class="small">北京力迈中美学校. All right reserved 2018</p>
										</li>
									</ul>
								</div>
							<!--	<div class="medium-6 columns social-links right">
									<ul>

										&lt;!&ndash; legal notice &ndash;&gt;
										&lt;!&ndash;<li class="show-for-medium-up">
											<h4>英文</h4>
											<p>电话：</p>
										</li>
										<li  class="show-for-medium-up">
											<h4>英文</h4>
											<p>电话：</p>
											&lt;!&ndash; End of Social links &ndash;&gt;
										</li>&ndash;&gt;

										<li>
											<p><img src="img/lmlogo.png" alt="Logo" class="logo"></p>
										</li>
									</ul>

								</div>-->
							</div>
						</div>
						<!-- End of page SubSection -->
					</section>  
				</div>
				<!-- end of contact information -->
				
				<!-- begin of contact message --> 
				<div class="slide" id="message" data-anchor="message">
					<section class="content">
						<header class="p-title">
							<h3>写信给我们<i class="ion ion-email">
								</i>
							</h3>
							<ul class="buttons">
								<li class="show-for-medium-up">
									<a title="home" href="#home"><i class="ion ion-android-information"></i></a>
								</li>
								<li>
									<a title="Contact" href="#contact/information"><i class="ion ion-location"></i></a>
								</li>
								<!--<li>
									<a title="Message" href="#contact/message"><i class="ion ion-email"></i></a>
								</li>-->
							</ul>
						</header>
						<!-- Begin Of Page SubSction -->
						
						<div class="page-block c-right v-zoomIn">
							<div class="wrapper">
								<div>
									<form class="message form send_message_form" method="get" action="ajaxserver/serverfile.php">
										<div class="fields clearfix">
											<div class="input">
												<label for="mes-name">姓名 </label>
												<input id="mes-name" name="name" type="text" placeholder="你的姓名" required></div>
											<div class="buttons">
												<button id="submit-message" class="button email_b" name="submit_message">Ok</button>
											</div>
										</div>
										<div class="fields clearfix">
											<div class="input">
												<label for="mes-email">Email </label>
												<input id="mes-email" type="email" placeholder="Email Address" name="email" required>
											</div>
										</div>
										<div class="fields clearfix no-border">
											<label for="mes-text">内容 </label>
											<textarea id="mes-text" placeholder="在这里回复你要写的内容..." name="message" required></textarea>

											<div>
												<p class="message-ok invisible">在这里回复你要写的内容...</p>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- End Of Page SubSction -->
					</section>
						
				</div>
				<!-- End of contact message -->
            </div>


		</main>

        <!-- END OF site main content content here -->  
		
		<!-- Begin of site footer -->
		<footer class="page-footer">
			<span>Find us on &nbsp;&nbsp; 400-102-8081
			</span>
		</footer>
		<!-- End of site footer -->

        

        <!-- All Javascript plugins goes here -->
        <script src="${staticPath}/arrnging/js/jquery-1.11.2.min.js"></script>
		<!-- All vendor scripts -->
        <script src="${staticPath}/arrnging/js/all.js"></script>
		<!-- Downcount JS -->
        <script src="${staticPath}/arrnging/js/jquery.downCount.js"></script>
		
		<!-- Form script -->
        <script src="${staticPath}/arrnging/js/form_script.js"></script>
		<!-- Javascript main files -->
        <script src="${staticPath}/arrnging/js/main.js"></script>
		<script src="${staticPath}/arrnging/js/jquery.timelinr-0.9.4.js" type="text/javascript"></script>
		<script type="${staticPath}/arrnging/text/javascript" src="js/alpha.js"></script>
		<script type="text/javascript">

			$(function(){

				$().timelinr()

			});

		</script>



	</body>
</html>
