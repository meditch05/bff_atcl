<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,com.mw.mwportal.bff.*"%>
<%	request.setCharacterEncoding("UTF-8");	%>

  <header class="main-header"  style="z-index:2030;">

    <!-- Logo -->
    <a href="check_account" class="logo">
      <span class="logo-mini"><b>M</b>P</span>			<!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-lg"><b>MW</b> Portal</span>	<!-- logo for regular state and mobile devices -->
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">      
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">		<!-- Sidebar toggle button-->
        <span class="sr-only">Toggle navigation</span>
      </a>    
      
      
      <div class="collapse navbar-collapse pull-right" id="navbar-collapse">      
	      <!-- Navbar Right Menu -->
	      <div class="navbar-custom-menu ">
	        <ul class="nav navbar-nav">
	          <!-- User Account Menu -->
	          <li class="dropdown user user-menu">
	            <!-- Menu Toggle Button -->
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	              <!-- The user image in the navbar-->
	              <img src="../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
	              <!-- hidden-xs hides the username on small devices so only the image appears. -->
	              <span class="hidden-xs"><%=(session.getAttribute("userId")) %></span>
	            </a>
	            <ul class="dropdown-menu">
	              <!-- Menu Footer-->
	              <li class="user-footer">
	                <div class="pull-right">
	                  <a href="/mwportal/logout" class="btn btn-default btn-flat">Sign out</a>
	                </div>
	              </li>
	            </ul>
	          </li>
	        </ul>
	      </div>
	      
      </div>
    </nav>
  </header> 
