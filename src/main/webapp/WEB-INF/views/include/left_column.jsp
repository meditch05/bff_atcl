<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,com.mw.mwportal.bff.*"%>
<% request.setCharacterEncoding("UTF-8"); %>

<aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu" data-widget="tree">

			<!-- Optionally, you can add icons to the links -->
			<li class="treeview">
				<a href="#"><i class="fa fa-laptop"></i>서비스 점검
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/ondemand/check_dashboard"><i class="fa fa-fw fa-dashboard"></i>점검 Dashboard</a></li>
					<li><a href="/mwportal/ondemand/check_service_opmate"><i class="fa fa-fw fa-database"></i>서비스별 점검(OPMATE)</a></li>
					<li><a href="/mwportal/ondemand/check_service"><i class="fa fa-fw fa-database"></i>서비스별 점검(ANSIBLE)</a></li>					
					<li><a href="/mwportal/ondemand/check_account"><i class="fa fa-fw fa-bomb"></i>계정별 점검</a></li>
					<li><a href="/mwportal/ondemand/swing_timeout"><i class="fa fa-fw fa-hourglass-2"></i>Swing 타임아웃</a></li>
					<li><a href="/mwportal/ondemand/check_service_mgmt"><i class="fa fa-fw fa-gavel"></i>점검 대상 관리</a></li>
				</ul></li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-sort-amount-asc"></i>표준화
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/standard/mw_std_script"><i class="fa fa-fw fa-pencil"></i>표준 Script List</a></li>
					<li><a href="/mwportal/standard/mw_ver_home_instcnt"><i class="fa fa-fw fa-sort-numeric-asc"></i>버전 List</a></li>
					<li><a href="/mwportal/standard/mw_hioms_mon_list"><i class="fa fa-fw fa-bell-o"></i>Hi-OMS 등록 List</a></li>
				</ul>
			</li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-file-text"></i>변경 작업 관리
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/work/todo"><i class="fa fa-fw fa-wrench"></i>변경작업 (TBD)</a></li>
				</ul>
			</li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-file-word-o"></i>업무 자동화
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/job_automation/html_mw_daily_check">일일점검 List</a></li>
					<li><a href="/mwportal/job_automation/html_mw_std_script_progress">표준 Script 진척률</a></li>
					<li><a href="/mwportal/job_automation/html_mw_get_crontab_progress">Crontab 수집 진척률</a></li>
					<li><a href="/mwportal/job_automation/html_mw_get_profile_progress">Profile 수집 진척률</a></li>
					<li><a href="/mwportal/job_automation/html_mw_hioms_not_registered">Hi-OMS 누락 List</a></li>
				</ul>
			</li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-rotate-left"></i> 설정 자동화
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/conf_info/xvarm">XVARM</a></li>
					<li><a href="/mwportal/conf_info/wmq">WMQ</a></li>
					<li><a href="/mwportal/conf_info/apache">Apache</a></li>
					<li><a href="/mwportal/conf_info/tomcat">Tomcat</a></li>
					<li><a href="/mwportal/conf_info/webtob">WEBTOB</a></li>
					<li><a href="/mwportal/conf_info/jeus">Jeus</a></li>
					<li><a href="/mwportal/mw_config_iplanet">Iplanet(TBD)</a></li>
					<li><a href="/mwportal/mw_config_weblogic">WebLogic(TBD)</a></li>
					<li><a href="/mwportal/mw_config_anylink">AnyLink(TBD)</a></li>
					<li><a href="/mwportal/mw_config_tmax">TMAX(TBD)</a></li>
				</ul>
			</li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-pie-chart"></i>운영 정보 및 통계
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/statics_info/statics">인스턴스, 제품별 통계 (TBD)</a></li>
					<li><a href="/mwportal/statics_info/xxx">운영정보 (TBD)</a></li>
					<li><a href="/mwportal/statics_info/xxx">운영자 페이지 (TBD)</a></li>
					<li><a href="/mwportal/statics_info/mw_xvarm_mig">XVARM 일별 Mig. 이력</a></li>
					<li><a href="/mwportal/statics_info/mw_mq_object_history">MQ Object 생성이력</a></li>
				</ul>
			</li>

			<li class="treeview"><a href="#"><i
					class="fa fa-fw fa-bar-chart"></i>성능, 용량 <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/perform/report">보고서 자료 추출</a></li>
					<li><a href="/mwportal/perform/enpharos_tps_res">Swing
							Daily TPS_RES</a></li>
					<!-- 					<li><a href="/mwportal/perform/xxx">MobileT TPS (TBD)</a></li> -->
				</ul></li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-phone"></i>SMS이력
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/sms_hist/emevent_tqms_list">TQMS발송내역(팀)</a></li>
					<li><a href="/mwportal/sms_hist/emevent_statics">SMS 팀 발송통계</a></li>
					<li><a href="/mwportal/sms_hist/emevent_list">SMS 도메인별 발송통계</a></li>
					<li><a href="/mwportal/sms_hist/emevent_individual">SMS 개인별 발송통계</a></li>
				</ul>
			</li>

			<li class="treeview">
				<a href="#"><i class="fa fa-fw fa-cog"></i>설정
					<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<li><a href="/mwportal/main_mgmt/user_ip_mgmt"><i class="fa fa-fw fa-user-plus"></i>사용자, IP 관리</a></li>
					<li><a href="/mwportal/main_mgmt/check_mgmt"><i class="fa fa-fw fa-folder-open-o"></i>서비스 점검 관련 설정</a></li>
				</ul>
			</li>

		</ul>		
		<!-- /.sidebar-menu -->
		
	</section>	
	<!-- /.sidebar -->
	
</aside>
