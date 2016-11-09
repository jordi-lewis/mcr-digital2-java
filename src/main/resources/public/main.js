// $.ajax({
//     type: "POST",
//     url: url,
//     data: data,
//     success: success,
//     dataType: dataType
// });

var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8080/user",
    "method": "POST",
    "headers": {
        "content-type": "application/json",
        "cache-control": "no-cache"
    },
    "processData": false,
    "data": "username: $('#user-input')"
}

$("#login").click(function() {
    var userInput = $("#user-input").value();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user",
        data: "username: $userInput",
        success: null,
        dataType:"json"
    });
});







