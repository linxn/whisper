/**
 * Created by linxn on 2018/4/16.
 */
$(document).ready(function(){

    function getCookie(cname)
    {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++)
        {
            var c = ca[i].trim();
            if (c.indexOf(name)==0) return c.substring(name.length,c.length);
        }
        return "";
    }

    //获取用户名
    var username = getCookie("username");
    var userid = getCookie("userid");
    var uHeadPhoto = getCookie("uHeadPhoto");
    var pageType = window.location.href;
    pageType = pageType.substr(pageType.indexOf("pageType=")+"pageType=".length, pageType.length - 1);


    //emoji
    $("#emoji_text_field").emoji({
        button: "#emoji-btn",
        showTab: false,
        animation: 'slide',
        icons: [{
            name: "QQ表情",
            path: "/chat/static/img/qq/",
            maxNum: 91,
            excludeNums: [41, 45, 54],
            file: ".gif",
            placeholder: "#qq_{alias}#"
        }]
    });

    function alterEmoji(str) {
        var re = str.replace(/#qq_\d+#/g, function(word){
                console.log("word: "+word);
                var str = '<img class="emoji_icon" src="/chat/static/img/qq/'+word.substring(4,word.length - 1)+'.gif">';
                return str;
            }
        );
        return re;
    }


    //websocket参数
    var changeOnlineStateUrl  = "/chat/user/changeState";
    var onlineParmMess = {mType : 5, mFromId : userid, mContent : '{uId : "' + userid + '", uOnline : "' + 1 + '"}'};
    var offlineParmMess = {mType : 5, mFromId : userid, mContent : '{uId : "' + userid + '", uOnline : "' + 0 + '"}'};
    var commMess = {mType : 2, mFromId : userid, mToId : -1, mContent : ""};
    $(".my-name").html(username);

    var to_num;
    var to_name;
    var to_head_photo;
    var desire_f_num = -1;
    var talk_to_friend_num = -1;

    //ajax获取陌生人用户列表
    function getStrangerList() {
        $.ajax({
            type: "POST",
            url: "/chat/user/onlineUser",
            data: {mType : 6, mFromId : userid},
            success: function (data) {
                console.log(data);
                obj = JSON.parse(data);
                var ap = "";
                for(var i = 0; i < obj.length; i++){
                    ap = ap + '<div class="friend-card"> <img src="/chat/static/img/head/'
                        + obj[i].uHeadPhoto
                        + '" height="40" /> &nbsp;&nbsp;&nbsp;&nbsp; <span class="friend-num" style="display:none">'
                        + obj[i].uId
                        + '</span><span class="friend-head-photo" style="display:none">'
                        + obj[i].uHeadPhoto
                        + '</span><span class="f-name">'
                        + obj[i].uUsername
                        + '</span> </div>';
                };
                $(".none-box").children().remove();
                $(".none-box").append(ap);

                $(".friend-card").click(function(){
                    if(confirm("是否邀请与其聊天？\n(当前聊天将清空不会保存且之前的聊天邀请作废)")){
                        $(".none-box3").children().remove();
                        to_num = $(this).children(".friend-num").text();
                        to_name = $(this).children(".f-name").text();
                        to_head_photo = $(this).children(".friend-head-photo").text();
                        commMess.mType = 7;
                        commMess.mToId = to_num;
                        var messJ = JSON.stringify(commMess);
                        websocket.send(messJ);
                        $(".talk-to-name").html("等待对方接受...");
                        $(".send-btn").attr({"disabled":"disabled"});
                        $(".add-friend-btn").attr({"disabled":"disabled"});
                        $(".disconnect-btn").attr({"disabled":"disabled"});
                        $(".none-box3").children().remove();
                        //移除请求聊天
                        $("#user-"+to_num).remove();
                        /*新增 未测试*/
                        $(".friend-card").css("background-color", "inherit");
                        $(this).css("background-color", "#586168");
                    }
                });
            }
        });//ajax over
    };

    //ajax获取好友列表
    function getFriendList() {
        $.ajax({
            type: "POST",
            url: "/chat/user/friendList",
            data: {mType : 19, mFromId : userid},
            success: function (data) {
                console.log(data);
                obj = JSON.parse(data);
                friendList = obj[0];
                console.log(friendList);
                messageList = obj[1];
                console.log(messageList);
                var ap = "";
                for(var i = 0; i < friendList.length; i++){
                    ap = ap + '<div class="friend-card" id="fCard-'
                        + friendList[i].uId
                        + '"> <img src="/chat/static/img/head/'
                        + friendList[i].uHeadPhoto
                        + '" height="40" /> &nbsp;&nbsp;&nbsp;&nbsp; <span class="friend-num" style="display:none">'
                        + friendList[i].uId
                        + '</span><span class="friend-head-photo" style="display:none">'
                        + friendList[i].uHeadPhoto
                        + '</span><span class="f-name">'
                        + friendList[i].uUsername
                        + '</span> </div>';
                    $(".none-box3-f-"+(i+1)).attr({"id":"nb3-f-"+friendList[i].uId});
                };
                $(".none-box").children().remove();
                $(".none-box").append(ap);
                ap = "";

                if(messageList.length > 0){
                    alert("您有未读的好友聊天！");
                    for(var i = 0; i < messageList.length; i++){
                        var chatHead = $("#fCard-"+messageList[i].mFromId).children(".friend-head-photo").text();
                        ap = '<div class="admin-group"> <img class="admin-img" src="/chat/static/img/head/'
                            + chatHead
                            +'" /> <div class="admin-msg"> <i class="triangle-admin"></i> <span class="admin-reply">'
                            + messageList[i].mContent
                            +'</span> </div> </div>';
                        var from_id = messageList[i].mFromId;
                        ap = alterEmoji(ap);
                        $("#nb3-f-"+from_id).append(ap);
                    }
                    ap = "";
                }

                $(".friend-card").click(function(){
                    $(".send-btn").removeAttr("disabled");
                    $(".del-friend-btn").removeAttr("disabled");
                    to_num = $(this).children(".friend-num").text();
                    to_name = $(this).children(".f-name").text();
                    to_head_photo = $(this).children(".friend-head-photo").text();
                    $(".talk-to-num").html(to_num);
                    $(".talk-to-name").html(to_name);
                    $(".none-box3").css("display","none");
                    $("#nb3-f-"+to_num).css("display","block");
                    console.log(to_num+" "+to_name);
                });
            }
        });//ajax over
    };

    //ajax获取心愿好友列表
    function getDesireFriendList() {
        $.ajax({
            type: "POST",
            url: "/chat/user/desireFriendList",
            data: {mType : 34, mFromId : userid},
            success: function (data) {
                console.log(data);
                obj = JSON.parse(data);
                friendList = obj[0];
                console.log(friendList);
                messageList = obj[1];
                console.log(messageList);
                var ap = "";
                for(var i = 0; i < friendList.length; i++){
                    ap = ap + '<div class="friend-card" id="fCard-'
                        + friendList[i].uId
                        + '"> <img src="/chat/static/img/head/'
                        + friendList[i].uHeadPhoto
                        + '" height="40" /> &nbsp;&nbsp;&nbsp;&nbsp; <span class="friend-num" style="display:none">'
                        + friendList[i].uId
                        + '</span><span class="friend-head-photo" style="display:none">'
                        + friendList[i].uHeadPhoto
                        + '</span><span class="f-name">'
                        + friendList[i].uUsername
                        + '</span> </div>';

                    //心愿好友nonebox从6开始
                    $(".none-box3-f-"+(i+6)).attr({"id":"nb3-f-"+friendList[i].uId});
                    desire_f_num = friendList[i].uId;
                };

                //TODO
                $(".request-connect").css("display","block");
                $(".none-box2").children().remove();
                $(".none-box2").append(ap);
                ap = "";

                if(messageList.length > 0){
                    alert("您有未读的心愿好友聊天！");
                    for(var i = 0; i < messageList.length; i++){
                        var chatHead = $("#fCard-"+messageList[i].mFromId).children(".friend-head-photo").text();
                        ap = '<div class="admin-group"> <img class="admin-img" src="../static/img/head/'
                            + chatHead
                            + '" /> <div class="admin-msg"> <i class="triangle-admin"></i> <span class="admin-reply">'
                            + messageList[i].mContent
                            +'</span> </div> </div>';
                        var from_id = messageList[i].mFromId;
                        ap = alterEmoji(ap);
                        $("#nb3-f-"+from_id).append(ap);
                    }
                    ap = "";
                }

                $(".friend-card").click(function(){
                    $(".send-btn").removeAttr("disabled");
                    $(".del-friend-btn").removeAttr("disabled");
                    to_num = $(this).children(".friend-num").text();
                    to_name = $(this).children(".f-name").text();
                    to_head_photo = $(this).children(".friend-head-photo").text();
                    $(".talk-to-num").html(to_num);
                    $(".talk-to-name").html(to_name);
                    $(".none-box3").css("display","none");
                    $("#nb3-f-"+to_num).css("display","block");
                    console.log(to_num+" "+to_name);
                });
            }
        });//ajax over
    };

    //getStrangerList();
    if(pageType == 'stranger'){
        $(".my-name").prepend('<img src="/chat/static/img/head/'+ uHeadPhoto +'" height="50" style="margin-right: 10px"/>');
        $(".stra-list").css("color","gray");
        $(".stra-list").css("disabled","true");
        getStrangerList();
        $(".request-connect").children().children(".f-name").text("邀请聊天");
        $(".frie-list").attr("href","chatPage.html?pageType=friend");
        $(".stra-list").css("cursor","default");
        $(".refrash-s").css("display","block");
        $(".request-connect").css("display","block");
        $(".none-box3").css("display","none");
        $(".stranger-none-box").css("display","block");
        $(".del-friend-btn").css("display","none");
    }else if(pageType == 'friend'){
        $(".my-name").prepend('<img src="/chat/static/img/head/'+ uHeadPhoto +'" height="50" style="margin-right: 10px"/>');
        $(".frie-list").css("color","gray");
        $(".frie-list").css("disabled","true");
        getFriendList();
        getDesireFriendList();
        $(".request-connect").children().children(".f-name").text("心愿好友");
        $(".stra-list").attr("href","chatPage.html?pageType=stranger");
        $(".frie-list").css("cursor","default");
        $(".refrash-s").css("display","none");
        $(".request-connect").css("display","none");
        $(".disconnect-btn").css("display","none");
        $(".add-friend-btn").css("display","none");
        $(".none-box3").css("display","none");
    }

    //websorcket
    //websocket初始化
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    function myWebSocket(){
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + document.location.host + "/chat/websocket/" + userid);
        } else {
            alert('当前浏览器 Not support websocket');
        }
    }


    //websocket业务逻辑
    //创建websocket
    myWebSocket();

    //连接发生错误的回调方法
    websocket.onerror = function() {
        //setMessageInnerHTML("WebSocket连接发生错误");
        console.log("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function() {
        //setMessageInnerHTML("WebSocket连接成功");
        console.log("WebSocket连接成功");
        //doPost(changeOnlineStateUrl,userOnlineParm);
        $.ajax({type:"POST",url:changeOnlineStateUrl,data:onlineParmMess});
    }

    //接收到消息的回调方法
    websocket.onmessage = function(event) {
        //setMessageInnerHTML(event.data);
        console.log(event.data);
        var messObj = JSON.parse(event.data);
        // mType == 7 发起聊天邀请
        if(messObj.mType == 7){
            alert("有人邀请您聊天！");
            console.log(messObj.mContent);
            var user = JSON.parse(messObj.mContent);
            console.log(user);
            var ap = '<div class="friend-card acc-friend-card" id="user-'
                + user.uId
                +'"> <img src="/chat/static/img/head/'
                + user.uHeadPhoto
                + ' " height="40" /> &nbsp;&nbsp;&nbsp;&nbsp; <span class="friend-num acc-num" style="display:none">'
                + user.uId
                + '</span><span class="friend-head-photo acc-head-photo" style="display:none">'
                + user.uHeadPhoto
                + '</span><span class="f-name acc-name">'
                + user.uUsername
                + '</span><div class="btn-group accept-btn-group" role="group" aria-label="Basic example"><button type="button" class="btn btn-secondary accept-btn">接受</button> <button type="button" class="btn btn-secondary refuse-btn">拒绝</button> </div></div>';
            $(".none-box2").append(ap);

            //TODO  待解决 多人同时请求聊天bug
            $(".accept-btn").click(function(){

                if(confirm("确认接受聊天么？\n(当前的聊天将清空不会保存)")){
                    $(".none-box3").children().remove();
                    commMess.mType = 8;
                    commMess.mToId = $(this).parent().siblings(".acc-num").text();
                    var messJ = JSON.stringify(commMess);
                    console.log(messJ);
                    websocket.send(messJ);
                    $(".friend-card").css("background-color", "inherit");
                    //$(".acc-friend-card").css("background-color", "#3a3f45");
                    $(this).parent().parent().css("background-color", "#586168");
                    //to_num = $(".acc-num").text();
                    to_num = $(this).parent().siblings(".acc-num").text();
                    //to_name = $(".acc-name").text();
                    to_name = $(this).parent().siblings(".acc-name").text();
                    to_head_photo = $(this).parent().siblings(".acc-head-photo").text();
                    $(".talk-to-num").html(to_num);
                    $(".talk-to-name").html(to_name);
                    alert("您已接受聊天邀请~");
                    //$(".accept-btn-group").css("display","none");
                    $(this).parent().css("display","none");
                    $(".send-btn").removeAttr("disabled");
                    $(".add-friend-btn").removeAttr("disabled");
                    $(".disconnect-btn").removeAttr("disabled");
                    var ap = '<div class="send-time">开始聊天吧：）</div>';
                    $(".none-box3").append(ap);
                }
            });

            $(".refuse-btn").click(function(){
                commMess.mType = 9;
                commMess.mToId = $(this).parent().siblings(".acc-num").text();
                var messJ = JSON.stringify(commMess);
                console.log(messJ);
                websocket.send(messJ);
                $(this).parent().parent().remove();
                alert("您已拒绝聊天");
                $(".send-btn").attr({"disabled":"disabled"});
                $(".add-friend-btn").attr({"disabled":"disabled"});
                $(".disconnect-btn").attr({"disabled":"disabled"});
            });
            //mType == 8 接受聊天邀请  9  拒绝聊天
        }else if(messObj.mType == 8 && to_num == messObj.mFromId){
            alert("对方已接受聊天邀请！");
            $(".talk-to-num").html(to_num);
            $(".talk-to-name").html(to_name);
            $(".send-btn").removeAttr("disabled");
            $(".add-friend-btn").removeAttr("disabled");
            $(".disconnect-btn").removeAttr("disabled");
            var ap = '<div class="send-time">开始聊天吧：）</div>';
            $(".none-box3").append(ap);
            /*$(".friend-card").css("background-color", "inherit");
            $("#user-"+messObj.mFromId).css("background-color", "#586168");*/
        }else if(messObj.mType == 9){
            alert("对方在忙：( \n再找其他人聊天呗");
            $(".send-btn").attr({"disabled":"disabled"});
            $(".add-friend-btn").attr({"disabled":"disabled"});
            $(".disconnect-btn").attr({"disabled":"disabled"});
        }else if(messObj.mType == 2){
            if(to_num == messObj.mFromId){
                var ap = '<div class="admin-group"> <img class="admin-img" src="/chat/static/img/head/'
                    + to_head_photo
                    + '" /> <div class="admin-msg"> <i class="triangle-admin"></i> <span class="admin-reply">'
                    + messObj.mContent
                    +'</span> </div> </div>';
                ap = alterEmoji(ap);
                $(".none-box3").append(ap);
            }else{
                //已断开连接
                commMess.mType = 10;
                commMess.mToId = messObj.mFromId;
                var messJ = JSON.stringify(commMess);
                websocket.send(messJ);
            }
        }else if(messObj.mType == 10){
            alert("对方已结束聊天：（ \n再找其他人聊天呗~");
            $(".send-btn").attr({"disabled":"disabled"});
            $(".add-friend-btn").attr({"disabled":"disabled"});
            $(".disconnect-btn").attr({"disabled":"disabled"});
            if(class_name == 'user-group'){
                $(".triangle-user:last").css("border-left", "12px solid #bbb");
                $(".user-reply:last").css("background-color", "#bbb");
            }
            $("#user-"+to_num).remove();
            /*$(".triangle-user:last").css("border-left", "12px solid #bbb");
            $(".user-reply:last").css("background-color", "#bbb");*/
            var ap = '<div class="send-time">--对方已结束聊天--</div>';
            $(".none-box3").append(ap);
        }else if(messObj.mType == 11){
            alert("对方已下线");
            $(".send-btn").attr({"disabled":"disabled"});
            $(".add-friend-btn").attr({"disabled":"disabled"});
            $(".disconnect-btn").attr({"disabled":"disabled"});
            var class_name = $(".none-box3").children(":last").attr("class");
            console.log(class_name);
            if(class_name == 'user-group'){
                $(".triangle-user:last").css("border-left", "12px solid #bbb");
                $(".user-reply:last").css("background-color", "#bbb");
            }
            var ap = '<div class="send-time">--对方已下线--</div>';
            $(".none-box3").append(ap);
            $("#user-"+to_num).remove();
        }else if(messObj.mType == 12){
            alert("对方请求添加好友");
            $(".add-friend-bar").css("display","block");
        }else if(messObj.mType == 13){
            alert("对方已同意添加好友！");
        }else if(messObj.mType == 14){
            alert("对方已拒绝添加好友！");
        }else if(messObj.mType == 15){
            alert("您的好友已满！");
        }else if(messObj.mType == 16){
            alert("对方好友已满！");
        }else if(messObj.mType == 17){
            alert("你们已经是好友");
        }else if(messObj.mType == 18){
            alert("已发送添加好友请求！");
        }else if(messObj.mType == 20){
            var chatHead = $("#fCard-"+messObj.mFromId).children(".friend-head-photo").text();
            var ap = '<div class="admin-group"> <img class="admin-img" src="/chat/static/img/head/'
                + chatHead
                + '" /> <div class="admin-msg"> <i class="triangle-admin"></i> <span class="admin-reply">'
                + messObj.mContent
                + '</span> </div> </div>';
            var from_id = messObj.mFromId;
            ap = alterEmoji(ap);
            $("#nb3-f-"+from_id).append(ap);
        }else if(messObj.mType == 22){
            alert("对方已将您删除好友！");
            $(".send-btn").attr({"disabled":"disabled"});
            $(".del-friend-btn").attr({"disabled":"disabled"});
            $("#nb3-f-"+to_num).append('<div class="send-time">--非好友关系--</div>');
        }
    }

    //连接关闭的回调方法
    websocket.onclose = function() {
        //setMessageInnerHTML("WebSocket连接关闭");
        //$.ajax({type:"POST",url:changeOnlineStateUrl,data:offlineParmMess});
        console.log("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
        //$.ajax({type:"POST",url:changeOnlineStateUrl,data:offlineParmMess});
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        //$.ajax({type:"POST",url:changeOnlineStateUrl,data:offlineParmMess});
        websocket.close();
    }

    //通过websocket发送消息
    $(".send-btn").click(function () {
            commMess.mType = ( pageType == 'friend' ? 20 : 2);
            commMess.mToId = $(".talk-to-num").html();
            commMess.mContent = $(".text_field").val();
            var messJ = JSON.stringify(commMess);

            $(".text_field").val('');
            var ap = '<div class="user-group"> <div class="user-msg"> <span class="user-reply">'
                + commMess.mContent
                + '</span> <i class="triangle-user"></i> </div> <img class="user-img" src="/chat/static/img/head/'
                + uHeadPhoto
                + '"/> </div>';
            if(pageType == 'friend'){
                if(to_num == desire_f_num){
                    commMess.mType = 33;
                }
                ap = alterEmoji(ap);
                $("#nb3-f-"+to_num).append(ap);
                console.log("正在发送");
                console.log(messJ);
            }else if(pageType == 'stranger'){
                ap = alterEmoji(ap);
                $(".none-box3").append(ap);
                console.log("正在发送");
                console.log(messJ);
            }
        websocket.send(messJ);
        }
    );//websocket over



    $(".refrash-s").click(function(){
        $(".none-box").children().remove();
        getStrangerList();
    });

    $(".disconnect-btn").click(function () {
        if(confirm("确定要结束聊天吗？")){
            $("#user-"+to_num).remove();
            commMess.mType = 10;
            commMess.mToId = to_num;
            var messJ = JSON.stringify(commMess);
            websocket.send(messJ);
            to_name = -1;
            to_num = -1;
            $(".talk-to-name").html("选择聊天人");
            $(".none-box3").children().remove();
            $(".send-btn").attr({"disabled":"disabled"});
            $(".add-friend-btn").attr({"disabled":"disabled"});
            $(".disconnect-btn").attr({"disabled":"disabled"});

        }
    });

    $(".add-friend-btn").click(function(){
        commMess.mType = 12;
        commMess.mToId = to_num;
        var messJ = JSON.stringify(commMess);
        websocket.send(messJ);
        //alert("已发送添加好友请求！");
    });

    $(".agree-add-friend").click(function(){
        commMess.mType = 13;
        commMess.mToId = to_num;
        var messJ = JSON.stringify(commMess);
        websocket.send(messJ);
        $(".add-friend-bar").css("display", "none");
        alert("你们已经是好友啦！");
    });

    $(".disaggree-add-friend").click(function(){
        commMess.mType = 14;
        commMess.mToId = to_num;
        var messJ = JSON.stringify(commMess);
        websocket.send(messJ);
        $(".add-friend-bar").css("display", "none");
        alert("您已拒绝添加好友请求");
    });

    $(".del-friend-btn").click(function(){
        if(confirm("确定删除好友吗？")){
            commMess.mType = 21;
            commMess.mToId = to_num;
            var messJ = JSON.stringify(commMess);
            websocket.send(messJ);
            $("#fCard-" + to_num).remove();
            $(".send-btn").attr({"disabled":"disabled"});
            $(".del-friend-btn").attr({"disabled":"disabled"});
            alert("已删除好友");
        }
    });


});
