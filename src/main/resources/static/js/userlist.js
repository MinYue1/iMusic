onload = function () {
    //
    var message = $("#message").val();
    console.log(message)

    if (message != '') {
        initMessagebox(message);
        $("#message0").html(null);
    }
}