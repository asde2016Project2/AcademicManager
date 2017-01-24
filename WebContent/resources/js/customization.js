
$(document).ready(function () {

     $('table.datatable').dataTable({
          keys: true,
          responsive: true,
          fixedHeader: true
     });
     
     $('.date-picker').daterangepicker({
          
          locale:{
            format: 'DD-MM-YYYY',  
          },          
          singleDatePicker: true,
          calender_style: "picker_3"          
        }, function(start, end, label) {          
     });
});