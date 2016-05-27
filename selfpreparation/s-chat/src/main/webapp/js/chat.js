$(document).ready(function() {
    get();
    $("#xml").on("click", get);
    $("#clc").on("click",sex);
    $("#apnd").on("click",concatenate);

    function get() {
        $.ajax({
            type: 'GET',
            url: '/xml/xml.php',
            cache: false,
            dataType: "xml",
            success : function (result) {
                $("#result").html('Load - OK!');
                var list = $('dl');
                list.empty();
                $(result).find('message').each(function (index, element) {

                    var message = $(element);
                    // get the values we want
                    var nickname = message.find('nickname').text();
                    var mtext = message.find('mtext').text();
                    var id = message.find('id').text();
                    // and append some html in the <dl> element we stored previously
                    list.append('<dt id="'+id+'">' + nickname + ': </dt>')
                        .append('<dd id="'+id+'">' + mtext + '</dd>');
                });
                var d = $('#chat');
                d.animate({scrollTop: d.prop("scrollHeight")}, 50);
            },
            error : function () {
                $("#result").html('Load - NOT OK!');
            }
        });
    }

    function concatenate() {
        $.ajax({
            type: 'GET',
            url: '/xml/xml.php?lastmsg=1',
            cache: false,
            dataType: "xml",
            success : function (result) {
                var list = $('dl');
                $(result).find('message').each(function (index, element) {
                    var message = $(element);
                    // get the values we want
                    var nickname = message.find('nickname').text();
                    var mtext = message.find('mtext').text();
                    var id = message.find('id').text();
                    // and append some html in the <dl> element we stored previously
                    list.append('<dt id="'+id+'">' + nickname + ': </dt>')
                        .append('<dd id="'+id+'">' + mtext + '</dd>');
                });
            },
            error : function () {
                $("#result").html('Load - NOT OK!');
            }
        });
        var d = $('#chat');
        d.animate({scrollTop: d.prop("scrollHeight")}, 250);
    }

    function sex() {
        $('dl').empty();
    }

});


function getRequest() {
    $.ajax({
        type: 'GET',
        url: '/',
        cache: false,
        dataType: "xml",
        success : function (result) {

        },
        error : function () {
            $("#result").html('Load - NOT OK!');
        }
    });
}
