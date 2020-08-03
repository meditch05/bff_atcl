<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<!-- <script src="../bower_components/jquery/dist/jquery.min.js"></script> -->

<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
<!-- <script src="../bower_components/bootstrap/dist/js/bootstrap.4.3.1.js"></script> -->
<!-- <script src="../bower_components/bootstrap/dist/js/bootstrap3.3.7.min.js"></script> -->

<!-- Slimscroll -->
<script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>

<!-- FastClick -->
<script src="../bower_components/fastclick/lib/fastclick.js"></script>

<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>

<!-- AdminLTE for demo purposes -->
<!-- <script src="../dist/js/demo.js"></script> -->

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->

<script type="text/javascript" src="../resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../resources/js/dataTables.scroller.js"></script>
     
<script type="text/javascript" src="../bower_components/select2/dist/js/select2.full.min.js"></script>     

<script src="../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

<script src="../resources/js/dataTables.buttons.min.js"></script>
<script src="../resources/js/buttons.bootstrap.min.js"></script>
<script src="../resources/js/jszip.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script> -->
<script src="../resources/js/buttons.html5.min.js"></script>
<script src="../resources/js/buttons.print.min.js"></script>
<script src="../resources/js/buttons.colVis.min.js"></script>
     
 <script type="text/javascript">
 	var email = '<%=(session.getAttribute("email")) %>';
 	var pemail = '<%=(session.getAttribute("pemail")) %>';
 	
	//Initialize Select2 Elements
 	$('.select2').select2({theme: "classic"});
 
	 var url = window.location.href;
	 // for sidebar menu entirely but not cover treeview
	 $('ul.sidebar-menu a').filter(function() {
	 return this.href == url;
	 }).parent().addClass('active');
	 // for treeview
	 $('ul.treeview-menu a').filter(function() {
	 return this.href == url;
	 }).closest('.treeview').addClass('active');
	 
	 function makeBizHtml(title, elementId){
		 var html_contents = document.getElementById(elementId).innerHTML;
		 html_contents = html_contents.replace("class=\"thead-light\"", "style='font-size:12px;color: #4950.8;background-color: #e9ecef;border-color: #dee2e6;'").replace(/height: 0px;/gi, "height: 20px;")
		 html_contents = html_contents.replace(/<td>/gi, "<td><font facce='맑은 고딕'>")
		 html_contents = html_contents.replace(/<\/td>/gi, "</font></td>")
		 html_contents = html_contents.replace(/<td/gi, "<td style='font-size:12px;'")
		 html_contents = "<html><body><h4>" + title + "</h4><table border='1' style='border:1px solid #A7EEFF;border-collapse:collapse;'>" + html_contents + "</table></body></html>";
		 return html_contents;
	 }
	 
// 	 .replace(/a/gi,"b")
	 
	 
// 	 .thead-light
// 	 style="font-size:12px;"
// 	 color: #4950.8;
// 		background-color: #e9ecef;
// 		border-color: #dee2e6
		
// 		td{font-size:12px;}
// 		table.table-bordered > tbody > tr > td{
// 		    border:1px solid #A7EEFF;
// 		    vertical-align: middle
// 		}
 </script>
 
