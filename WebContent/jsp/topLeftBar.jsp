<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
   
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="메뉴"></div>
              </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>N-BBANG</b></a>
            <!--logo end-->
            <div class="nav notify-row " id="top_menu" >
                <!--  notification start -->
                <ul class="nav  top-menu">
                    <!-- inbox dropdown start-->
                    <li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                            <i class="fa fa-envelope-o"></i>
                            <span class="badge bg-theme">N</span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">받은 메세지 목록</p>
                            </li>
                              <li id="listTable"></li>
                            <li>
                                <a href="meeting.jsp">See all messages</a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="jsp/logout.jsp">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->

       <script type="text/javascript">
    $(document).ready(function() {
    	var table="";
    		$.ajax({
						url : "selectMessage.do",
						type : "post",
						dataType : "json", //결과데이터타입
						success : 
							function(data) {
							$(data.list).each(
									function(index, item) {
										table += "<a href='index.html#'> <span class='photo'><img alt='avatar' src='assets/img/ui-zac.jpg'></span> <span id='listTable' class='subject'>"+
										"<span class='subject'> <span class='from'>"+item.memname+"</span>"
												+"<span class='time'>"+item.alramtime+"</span></span>"
												+"<span class='message'>"+item.content+"</span></span></a>";
									});
							$("#listTable").html(table);
						} ,
						error : function(err) {//실패했을때
							alert(err + " : 메세지 보기 실패");
						}
					});
    		
    });
    
	  </script>
  <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
             
              	  <p class="centered"><a href="profile.html"><img src="assets/img/friends/fr-01.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">${sessionScope.dto.memname }</h5>
              	
                  <li class="mt">
                      <a href="#" data-toggle="modal" data-target="#myModal">
                          <i class="fa fa-dashboard"></i>
                          <span>회비 추가 & 친구 선택</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-desktop"></i>
                          <span>회비 관리</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="general.html">General</a></li>
                          <li><a  href="buttons.html">Buttons</a></li>
                          <li><a  href="panels.html">Panels</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>Components</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="calendar.html">Calendar</a></li>
                          <li><a  href="gallery.html">Gallery</a></li>
                          <li><a  href="todo_list.html">Todo List</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-book"></i>
                          <span>Extra Pages</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="blank.html">Blank Page</a></li>
                          <li><a  href="login.html">Login</a></li>
                          <li><a  href="lock_screen.html">Lock Screen</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-tasks"></i>
                          <span>Forms</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="form_component.html">Form Components</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-th"></i>
                          <span>Data Tables</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="basic_table.html">Basic Table</a></li>
                          <li><a  href="responsive_table.html">Responsive Table</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class=" fa fa-bar-chart-o"></i>
                          <span>Charts</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="morris.html">Morris</a></li>
                          <li><a  href="chartjs.html">Chartjs</a></li>
                      </ul>
                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->