
function loadscript(){

    $.ajax({
        type: "POST",
        url: "/message",
        data: $("#message-text").val(),
        success: function(){
            loadMessages();
        },
        dataType: "json",
        contentType: "application/json; charset=utf-8"
    });
}

function printMessages(data){
    $.each(data, function(index, message){
        $("#messagethingy").append("<br> " + message.text)
    })
}

function loadMessages(){
    $.ajax({
        type: "GET",
        url: "/message",
        data: null,
        success: function (data) {
            printMessages(data);
        },
        dataType: "json",
        contentType: "application/json; charset=utf-8"
    });
    $( "#messagethingy" ).empty();
}

window.onload = function () {
        loadMessages();
    }








