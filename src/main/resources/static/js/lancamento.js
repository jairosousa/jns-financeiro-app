$(document).ready(function () {
    // $("input[type='radio']").attr("checked")
    $("label.radiobox").removeClass("btn-secondary").addClass("btn-primary");

    $("label.radiobox").on('click',(function(){
        // $(this).removeClass(function () {
        //     $(this).is(":checked") ? 'btn-secondary' : 'btn-primary'
        // }).addClass(function () {
        //     $(this).is(":checked") ? 'btn-primary' : 'btn-secondary'
        // })

        console.log("acessou")
    }))

})