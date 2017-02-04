<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="${basepath}/static/js/html5.js"></script>
<![endif]-->

      
      
<!-- CSS -->
<link href="${basepath}/static/frame/theme/bootstrap/css/bootstrap.min.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/treeTable/themes/vsStyle/treeTable.min.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/bootstrap/css/bootstrap-responsive.min.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/assets/styles.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/uniform.default.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/chosen.min.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/wysiwyg/bootstrap-wysihtml5.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/jGrowl/jquery.jgrowl.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/morris/morris.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/css/style.css" rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/icheck-1.x/skins/all.css"  rel="stylesheet" media="screen">
<link href="${basepath}/static/frame/theme/vendors/fullcalendar/fullcalendar.css" rel="stylesheet" media="screen">
<!-- JS -->
<script src="${basepath}/static/frame/theme/vendors/jquery-validation/lib/jquery.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.extend.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-1.9.1.min.js"></script>
<script src="${basepath}/static/js/common.js"></script>
<script src="${basepath}/static/js/bootstrap-paginator.js"></script>
<script src="${basepath}/static/frame/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${basepath}/static/frame/treeTable/jquery.treeTable.min.js"></script>
<script src="${basepath}/static/frame/theme/bootstrap/js/bootstrap.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/easypiechart/jquery.easy-pie-chart.js"></script>
<script src="${basepath}/static/frame/theme/assets/scripts.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-ui-1.10.3.js"></script>
<script src="${basepath}/static/frame/theme/vendors/fullcalendar/fullcalendar.js"></script>
<script src="${basepath}/static/frame/theme/vendors/fullcalendar/gcal.js"></script>
<script src="${basepath}/static/frame/theme/vendors/bootstrap-wysihtml5/lib/js/wysihtml5-0.3.0.js"></script>
<script src="${basepath}/static/frame/theme/vendors/bootstrap-wysihtml5/src/bootstrap-wysihtml5.js"></script>
<script src="${basepath}/static/frame/theme/vendors/tinymce/js/tinymce/tinymce.min.js" type="text/javascript" ></script>
<script src="${basepath}/static/frame/theme/vendors/jquery.uniform.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/chosen.jquery.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/wysiwyg/wysihtml5-0.3.0.js"></script>
<script src="${basepath}/static/frame/theme/vendors/wysiwyg/bootstrap-wysihtml5.js"></script>
<script src="${basepath}/static/frame/theme/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="${basepath}/static/frame/theme/vendors/datatables/js/jquery.dataTables.min.js"></script>
<script src="${basepath}/static/frame/theme/assets/form-validation.js"></script>
<script src="${basepath}/static/frame/theme/assets/DT_bootstrap.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jGrowl/jquery.jgrowl.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery.knob.js"></script>
<script src="${basepath}/static/frame/theme/vendors/raphael-min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/morris/morris.min.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.categories.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.pie.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.time.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.stack.js"></script>
<script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.resize.js"></script>
<script src="${basepath}/static/frame/theme/vendors/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basepath}/ckeditor/ckeditor.js"></script>
<script src="${basepath}/ckfinder/ckfinder.js"></script>
<script src="${basepath}/static/frame/icheck-1.x/icheck.min.js"></script>


