/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var spinner = "<center><div style='vertical-align: middle; height: 100px; padding-top: 60px;'><img src='/mfs/assets/spinner_1.gif'/></div></center>";
          
$(document).ready(function($) {
    
    $(function() {
        $( "#accordion" ).accordion();
    });    
   
    $(function() {
        $("#dialog").dialog({
            modal:      true,
            autoOpen:   false,
            resizable:  true,
            closeText:  " ",
            show:       {effect: "blind", duration: 800},
            hide:       {effect: "fade", duration: 800}
        });
        
       
        $("a[name=view_profile]").click(function(){
            var id          = $(this).attr('id');
            
            $( "#dialog" ).dialog( "option", "height", $(this).attr('height') );
            $( "#dialog" ).dialog( "option", "width", $(this).attr('width') );
            $("#dialog").dialog("option", "title", $(this).attr('caption'));
            $("#dialog").html("&nbsp;");
            $('#dialog').dialog('open');
            $('#dialog').html(spinner);
            
            if(id !== 'undefined') {
                $('#dialog').load("/mfs/" + $(this).attr('cont') + "/" + $(this).attr('act') + "/" + id);
            }
            else {
                $('#dialog').load("/mfs/" + $(this).attr('cont') + "/" + $(this).attr('act'));
            }
            
            $('#dialog').animate({scrollTop: 0}, 300);
        });        
    });    
});


