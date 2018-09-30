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
            <p class="browsehappy">You are using it <strong>Low version</strong> Browser. 请 <a href="http://browsehappy.com/">Please upgrade your browser</a> To improve your experience。</p>
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
					<img src="/arrnging/img/lmlogo.png" alt="Beijing city of Li Mai Sino-American school">
					<span>Beijing Li Mai Sino-American school</span>
				</a>
			</div>

		</header>
        <!-- END OF site header Menu-->
        
        <!-- BEGIN OF Quick nav icons at left -->
		<!--<nav class="quick-link count-6 nav-left">
			<ul id="qmenu">
				<li data-menuanchor="home">
					<a href="#home" class=""><i class="icon ion ion-home"></i></a>
					<span class="title">homepage</span>
				</li>
				<li data-menuanchor="when">
					<a href="#when" class=""><i class="icon ion ion-bookmark"></i></a>
					<span class="title">Basic information</span>
				</li>

				<li data-menuanchor="class">
					<a href="#class"><i class="icon ion ion-calendar"></i></a>
					<span class="title">schedule</span>
				</li>
				<li data-menuanchor="honor">
					<a href="#honor"><i class="icon ion ion-star"></i></a>
					<span class="title">Honour commendation</span>
				</li>
				<li data-menuanchor="register">
					<a href="#register"><i class="icon ion ion-shopping-cart">¥</i></a>
					<span class="title">Payment item</span>
				</li>
				<li data-menuanchor="talk">
					<a href="#talk"><i class="icon ion ion-compose"></i></a>
					<span class="title">teacher commenting</span>
				</li>
				<li data-menuanchor="progress">
					<a href="#progress"><i class="icon ion ion-android-information"></i></a>
					<span class="title">Teaching plan</span>
				</li>
				<li data-menuanchor="entrance">
					<a href="#entrance"><i class="icon ion-leaf"></i></a>
					<span class="title">Study Instruction</span>
				</li>

				<li data-menuanchor="contact">
					<a href="#contact/message"><i class="icon ion-email"></i></a>
					<span class="title">Write letters</span>
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
			<!-- Begin of home pagehomepage -->
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
							<h2>about <strong>Dream</strong></h2>
						</div>
						<div class="h-right">
							<h3>我&nbsp;&nbsp;&nbsp;想 <br>大&nbsp;声&nbsp;说</h3>
						</div>

						<h4 class="subhead">Dream，Is a hope for the future，It refers to what we want to do in the future, or what we can achieve but we must work hard to achieve.，Dream is something that makes you feel persistent and happy.，Even it can be regarded as a belief。</h4>

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
            <!-- Begin of when page personal data-->
            <div class="section page-when page page-cent" id="s-when">
                <section class="content">
					<header class="p-title">
						<h3>Audrey Hepburn</h3>
					</header>
					<div class="left">
						<article class="text">
							<p>School number：lmf20170102</p>
							<p>Sex：女</p>
							<p>nationality：China</p>
							<p>Identity information：</p>
							<p>Student source：network</p>
							<p>Parents mailbox：sds163.com</p>
							<p>Parent phone：13345654565</p>

						</article>
					</div>
					<div class="right">
						<article class="text">
							<p>Faculty：Phil Monte</p>
							<p>School year：first grade</p>
							<p>Teaching Supervisor：Wahl</p>
							<p>Headmaster：Wahl</p>
							<p>Enrolment：excellent</p>
							<p>Admissions teacher：Wang Wen</p>
							<p>Place of school records：</p>
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
			<!-- Begin of when page Award-winning record-->
			<div class="section page-when page page-cent " id="s-results">
				<section class="content">
					<header class="p-title">
						<h3>Winning experience</h3>
					</header>
				<div class="left">
					<article class="text">
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize won the city of Beijing《Children's English Contest》The first prize won the city of Beijing《Children's English Contest》The first prize won the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
						<p>2018-08-05：Win the city of Beijing《Children's English Contest》The first prize</p>
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
			<!--achievement-->
			<div class="section page-class page page-cent " id="s-transcript">
				<section class="content">
					<header class="p-title">
						<h3>Achievement table</h3>
					</header>
					<div class="table-re">
						<table class="table">
							<thead>

							<tr>
								<th></th>
								<th>Chinese</th>
								<th>Mathematics</th>
								<th>English</th>
								<th>Chemistry</th>
								<th>Physics</th>
								<th>Politics</th>
								<th>History</th>
							</tr>
							</thead>
							<tbody>

							<tr>
								<td rowspan="1">Last semester</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
							</tr>
							<tr>
								<td>Comment</td>
								<td colspan="7" align="left" class="text-left">
									We will always adore those sparkling people.。We always feel that they are like gods.。They conquer the world with strong and irresistible charm and strength.。But we never know，What kind of price do they use，In exchange for a bright life。
								</td>
							</tr>
							<tr>
								<td rowspan="1">Next semester</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
								<td>92</td>
							</tr>
							<tr>
								<td>Comment</td>
								<td colspan="7"  align="left" class="text-left">
									We will always adore those sparkling people.。We always feel that they are like gods.。They conquer the world with strong and irresistible charm and strength.。But we never know，What kind of price do they use，In exchange for a bright life。
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
			<!-- Begin of teacher page Teachers' information-->
			<div class="section page  page-cent page-teacher " id="s-teacher">

				<section class="content">
					<header class="p-title teacher-title">
						<h3>Teacher's file</h3>
					</header>
					<div class="v_out v_out_p">
						<div class="v_show">
							<div class="v_cont">
								<ul>
									<li index="0">
										<div class="cus-name">
											<span>name：Wang Wen</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We live in the vast universe，We live in the vast universe. We live in the vast universe. We live in the vast universe. We live in the vast universe, the floating cosmic dust and the Star River dust in the vast universe.，We are even smaller than these.。You don't know when life suddenly changes direction.，Fall into the dark and thick darkness of ink。You were dragged into the abyss by disappointment，You were pulled into the grave by the disease，You have been trampled by setbacks，You are laughed at、Be satirize、Be disliked、Be resentful、Be given up。But we always keep hope in our hearts and keep the heart not willing to give up beating.。We are still struggling in great despair.。This kind of mood that does not want to give up，They become little dark stars。We are all small stars。</p>
										</div>
									</li>
									<li index="1">
										<div class="cus-name">
											<span>name：Wang Wen</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We are too young，So that we don't know the time later，It's so long，Long enough to let me forget you，Enough for me to re like a person，Just like you liked it。</p>
										</div>
									</li>

									<li index="2">
										<div class="cus-name">
											<span>name：Wang Wen</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> When youth becomes an old picture，When the old picture turns into a memory，When we finally stood at the fork of the road，lonely，Disappointment，Wandering，cruel，God opened the window，It's called the gate of growth。</p>
										</div>
									</li>

									<li index="3">
										<div class="cus-name">
											<span>name：Dew bud</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We live in the vast universe，The floating cosmic dust and the starlight dust of the Star River，We are even smaller than these.。You don't know when life suddenly changes direction.，Fall into the dark and thick darkness of ink。You were dragged into the abyss by disappointment，You were pulled into the grave by the disease，You have been trampled by setbacks，You are laughed at、Be satirize、Be disliked、Be resentful、Be given up。But we always keep hope in our hearts and keep the heart not willing to give up beating.。We are still struggling in great despair.。This kind of mood that does not want to give up，They become little dark stars。We are all small stars。</p>
										</div>
									</li>

									<li index="4">
										<div class="cus-name">
											<span>name：Eva</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We live in the vast universe，The floating cosmic dust and the starlight dust of the Star River，We are even smaller than these.。You don't know when life suddenly changes direction.，Fall into the dark and thick darkness of ink。You were dragged into the abyss by disappointment，You were pulled into the grave by the disease，You have been trampled by setbacks，You are laughed at、Be satirize、Be disliked、Be resentful、Be given up。But we always keep hope in our hearts and keep the heart not willing to give up beating.。We are still struggling in great despair.。This kind of mood that does not want to give up，They become little dark stars。We are all small stars。</p>
										</div>
									</li>
									<li index="5">
										<div class="cus-name">
											<span>name：Eva</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We live in the vast universe，The floating cosmic dust and the starlight dust of the Star River，We are even smaller than these.。You don't know when life suddenly changes direction.，Fall into the dark and thick darkness of ink。You were dragged into the abyss by disappointment，You were pulled into the grave by the disease，You have been trampled by setbacks，You are laughed at、Be satirize、Be disliked、Be resentful、Be given up。But we always keep hope in our hearts and keep the heart not willing to give up beating.。We are still struggling in great despair.。This kind of mood that does not want to give up，They become little dark stars。We are all small stars。</p>
										</div>
									</li>
									<li index="6">
										<div class="cus-name">
											<span>name：Eva</span>|
											<span class="ml">Subject：Chinese teacher</span>|
											<span class="ml">Telephone：12245455454</span>
										</div>
										<div class="testimonial-bubble">
											<p> We live in the vast universe，The floating cosmic dust and the starlight dust of the Star River，We are even smaller than these.。You don't know when life suddenly changes direction.，Fall into the dark and thick darkness of ink。You were dragged into the abyss by disappointment，You were pulled into the grave by the disease，You have been trampled by setbacks，You are laughed at、Be satirize、Be disliked、Be resentful、Be given up。But we always keep hope in our hearts and keep the heart not willing to give up beating.。We are still struggling in great despair.。This kind of mood that does not want to give up，They become little dark stars。We are all small stars。</p>
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

			<!--class  schedule-->
			<div class="section page-class page page-cent " id="s-class">
				<section class="content">
					<header class="p-title">
						<h3>schedule</h3>
					</header>
					<div class="table-re">
						<table class="table">
							<thead>
							<tr class="colspan">
								<th align="center" colspan="9">time table</th>
							</tr>
							<tr>
								<th></th>
								<th>Class Festival</th>
								<th>Monday</th>
								<th>Tuesday</th>
								<th>Wednesday</th>
								<th>Thursday</th>
								<th>Friday</th>
								<th>Saturday</th>
								<th>Sunday</th>
							</tr>
							</thead>
							<tbody>

							<tr>
								<td rowspan="2">morning</td>
								<td>Section 1</td>
								<td>Mathematics</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
							</tr>
							<tr>

								<td>Second section</td>
								<td>Mathematics</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
							</tr>
							<tr>
								<td rowspan="3">Afternoon</td>
								<td>Third section</td>
								<td>Mathematics</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
							</tr>
							<tr>

								<td>Fourth section</td>
								<td>Mathematics</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
							</tr>
							<tr>

								<td>Fifth section</td>
								<td>Mathematics</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
								<td>Foreign Languages</td>
							</tr>
							<tr class="colspan">
								<td align="center" colspan="9">Elective course</td>

							</tr>
							<tr>

								<td></td>
								<td>Music</td>
								<td></td>
								<td>Dance</td>
								<td></td>
								<td></td>
								<td></td>
								<td>Fine Arts</td>
								<td>Fine Arts</td>
							</tr>
							<tr>

								<td></td>
								<td>Music</td>
								<td></td>
								<td>Dance</td>
								<td></td>
								<td></td>
								<td></td>
								<td>Fine Arts</td>
								<td>Fine Arts</td>
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
			<!--Honour commendationhonor-->
			<div class="section page-class page page-cent " id="s-honor">
				<section class="content-">
					<header class="p-title">
						<h3>Gain honor</h3>
					</header>
					<div class="main-timeline">
						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">Global Golf Tournament<span class="hide-time">time：08-04</span></h3>

								<p class="description">
									Good performance is not what one can cast，

									It's a group of people，

									We call it a regiment

									队。The honor of the group can not be separated from a united advance.、Love the job and dedication、Selfless

									Project team。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">Global Golf Tournament<span class="hide-time">time：08-04</span></h3>
								<p class="description">
									Good performance is not what one can cast，

									It's a group of people，

									We call it a regiment

									队。The honor of the group can not be separated from a united advance.、Love the job and dedication、Selfless

									Project team。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">Global Golf Tournament<span class="hide-time">time：08-04</span></h3>
								<p class="description">
									Good performance is not what one can cast，

									It's a group of people，

									We call it a regiment

									队。The honor of the group can not be separated from a united advance.、Love the job and dedication、Selfless

									Project team。
								</p>
							</div>
						</div>

						<div class="timeline">
							<div class="timeline-icon"><span class="year">08/09</span></div>
							<div class="timeline-content">
								<h3 class="title">Global Golf Tournament<span class="hide-time">time：08-04</span></h3>
								<p class="description">
									Good performance is not what one can cast，

									It's a group of people，

									We call it a regiment

									队。The honor of the group can not be separated from a united advance.、Love the job and dedication、Selfless

									Project team。
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
			<!--Clubclub-->
			<div class="section page-class page page-cent " id="s-club">
				<section class="content">
					<header class="p-title">
						<h3>My club</h3>
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
			<!-- Begin of register page Financial contribution-->
            <div class="section page-register page page-cent " id="s-register">
                <section class="content">

					<div class="clock clock-countdown">

						<header class="header">
							Pay<strong>project</strong>
						</header>
						<div class="elem-over">
							<div class="digit">¥2500000</div>

						</div>
						<div class="elem-left">
							<div class="digit">Extracurricular activities:¥500</div>

						</div>
						<div class="elem-center">
							<div class="digit">Tuition:¥15,0000</div>

						</div>
						<div class="elem-mid">
							<div class="digit">clothing:¥500</div>

						</div>
						<div class="elem-right">
							<div class="digit">clothing:¥500</div>

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
			<!--begin of talk pageTeacher reviews-->
			<div class="section page-talk page page-cent " id="s-talk">
				<section class="content">
					<header class="p-title">
						<h3>teacher commenting</h3>
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
								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

							</li>

							<li id="2">

								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

							</li>

							<li id="3">

								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

							</li>

							<li id="4">

								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

							</li>

							<li id="5">

								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

							</li>

							<li id="6">


								<h3>Chinese teacher</h3>

								<p>“A false alarm”These four words are the best idioms in the world，Better than anything，Colourful，A smooth sailing should be a hundred times better。You can understand what is lost。</p>

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
			<!--Teaching progress-->
			<div class="section page-progress page page-cent" id="s-progress">
				<section class="content">
					<header class="p-title">
						<h3>Teaching plan
						</h3>
						<!--	<h2>planning <span class="bold">make</span> only <span class="bold">beautiful</span> things</h2>
                       --> </header>
					<div class="text">

						<div class="col-md-offset-3">
							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										Mathematics
										<span class="progress_number">89%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #1abc9c; width: 89%;" data-value="89" data-percentage-value="89"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										Mathematics
										<span class="progress_number">75%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #fdba04; width: 75%;" data-value="75" data-percentage-value="75"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										Mathematics
										<span class="progress_number">65%</span>
									</small>
									<span class="progress-bar-inner" style="background-color: #049dff; width: 65%;" data-value="65" data-percentage-value="65"></span>
								</div>
							</div>

							<div class="progress_bar">
								<div class="pro-bar">
									<small class="progress_bar_title">
										Mathematics
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
			<!-- Begin of entrance page Study Instruction -->
            <div class="section page-entrance page page-cent" id="s-entrance">
                <section class="content">
                    <header class="p-title">
                        <h3>Study Instruction
                        </h3>
					<!--	<h2>planning <span class="bold">make</span> only <span class="bold">beautiful</span> things</h2>
                   --> </header>
                    <article class="text">
                        <p><strong>Status analysis：</strong>How to let you meet me，At my most beautiful moment
							For this，I beseeched the Buddha for five hundred years，The quest for Buddhism has led us to an edge，Buddha let me become a tree，Growing on the roadside that you pass by every day，Under the sun，It carefully bloomed with flowers all over，Every bloom is my longing from a previous life，When you come near，Please listen carefully.，Those shivering leaves，It's the passion I've been waiting for，And when you end up in a blind way，Falling behind you，My friend，That's not a petal，Those are my withering heart</p>
						<p><strong>planning：</strong>How to let you meet me，At my most beautiful moment
							For this，I beseeched the Buddha for five hundred years，The quest for Buddhism has led us to an edge，Buddha let me become a tree，Growing on the roadside that you pass by every day，Under the sun，It carefully bloomed with flowers all over，Every bloom is my longing from a previous life，When you come near，Please listen carefully.，Those shivering leaves，It's the passion I've been waiting for，And when you end up in a blind way，Falling behind you，My friend，That's not a petal，Those are my withering heart</p>

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

			<!-- Begin of end pagehomepage -->
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
							<h2>Parent <strong>Expect</strong></h2>
						</div>
						<!--<div class="h-right">
							<h3>&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;</h3>
						</div>-->
						<br>
						<br><br>

						<h4 class="subhead">We will always adore those sparkling people.。We always feel that they are like gods.。They conquer the world with strong and irresistible charm and strength.。But we never know，What kind of price do they use，In exchange for a bright life。</h4>

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
			<!-- Begin of Contact page  Contact us -->
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
											<p>Telephone：400-102-8081</p>
										</li>
										<li>
											<h4></h4>
											<p>address：Beijing Chaoyang District Yang Shan Road2号
											</p>
										</li>
										<li>
											<h4></h4>
											<p>School mailbox：lmzmxx@163.com</p>
										</li>
										<li>	<p class="small">Beijing Li Mai Sino-American school. All right reserved 2018</p>
										</li>
									</ul>
								</div>
							<!--	<div class="medium-6 columns social-links right">
									<ul>

										&lt;!&ndash; legal notice &ndash;&gt;
										&lt;!&ndash;<li class="show-for-medium-up">
											<h4>English</h4>
											<p>Telephone：</p>
										</li>
										<li  class="show-for-medium-up">
											<h4>English</h4>
											<p>Telephone：</p>
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
							<h3>Write to us<i class="ion ion-email">
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
												<label for="mes-name">name </label>
												<input id="mes-name" name="name" type="text" placeholder="Your name" required></div>
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
											<label for="mes-text">content </label>
											<textarea id="mes-text" placeholder="Here's a reply to what you want to write..." name="message" required></textarea>

											<div>
												<p class="message-ok invisible">Here's a reply to what you want to write...</p>
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
