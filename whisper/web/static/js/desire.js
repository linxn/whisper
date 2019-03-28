/**
 * Created by linxn on 2018/5/7.
 */

$(document).ready(function () {
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

    var commMess = {mType : 26, mFromId : userid, mContent : ""};


    function getDesireList() {
        $.ajax({
            type: "POST",
            url: "/chat/desire/desireList",
            data: {mType : 26, mFromId : userid},
            success: function (data) {
                var mess = JSON.parse(data);
                var desire_list = mess.mContent;
                var obj = JSON.parse(desire_list);
                var ap = "";
                for(var i = 0; i < obj.length; i++){
                /*<div class="TH-container">
                    <div class="TH-text">
                        <span class="th-num" style="display: none;">1</span>
                        <span class="if-do-like" style="display: none;">0</span>     //是否已点赞
                        <span class="publisher-id" style="display: none;">24</span>  //发布者id
                        <span>我的愿望是快高长大~~</span>
                        <div class="do-likes do-likes-con"><i class="fa fa-user-plus fa-lg" aria-hidden="true"></i></div>
                        <div class="do-likes do-likes-btn"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i>&nbsp;<span class="do-likes-num">100</span></div>
                    </div>
                </div>
                <br />*/

                    ap = ap + '<div class="TH-container"> <div class="TH-text"> <span class="th-num" style="display: none;">'
                        + obj[i].dId
                        + '</span> <span class="if-do-like" style="display: none;">0</span> <span class="publisher-id" style="display: none;">'
                        + obj[i].dPublisherId
                        + '</span> <span class="if-realize" style="display: none;">'
                        + obj[i].dRealize
                        + '</span> <span>'
                        + obj[i].dContent
                        + '</span> <div class="do-likes do-likes-con"><i class="fa fa-user-plus fa-lg'
                        + (obj[i].dRealize == 1 ? ' do-likes-realize':'')
                        + '" aria-hidden="true"></i></div> <div class="do-likes do-likes-btn"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i>&nbsp;<span class="do-likes-num">'
                        + obj[i].dLikesCount
                        + '</span></div> </div> </div> <br />';
                };
                $(".none-box").children().remove();
                $(".none-box").append(ap);

                //点赞或取消赞
                $(".do-likes-btn").click(function(){
                    if($(this).siblings(".if-do-like").text() == '0'){
                        //send ajax request
                        $(this).css("color","gold");
                        doLikeNum = Number($(this).children(".do-likes-num").text()) + 1;
                        $(this).children(".do-likes-num").text(doLikeNum);
                        $(this).siblings(".if-do-like").text("1");
                        tId = $(this).siblings(".th-num").text();
                        doLikesAjax(tId, doLikeNum);

                    }else {
                        //send ajax request
                        $(this).css("color","inherit");
                        doLikeNum = Number($(this).children(".do-likes-num").text()) - 1;
                        $(this).children(".do-likes-num").text(doLikeNum);
                        $(this).siblings(".if-do-like").text("0");
                        tId = $(this).siblings(".th-num").text();
                        doLikesAjax(tId, doLikeNum);
                    }
                });

                $(".do-likes-con").click(function(){
                    if($(this).siblings(".if-realize").text() == '0'){
                        commMess.mType = 29;
                        commMess.mToId = $(this).siblings(".publisher-id").text();
                        commMess.mContent = $(this).siblings(".th-num").text();
                        if(commMess.mToId == userid){
                            alert("不能添加自己为心愿好友！");
                        }else{
                            $.ajax({
                                type: "POST",
                                url: "/chat/desire/addDesireFriend",
                                data: commMess,
                                success: function (data) {
                                    var mess = JSON.parse(data);
                                    if(mess.mType == 30){
                                        alert("您已有心愿好友");
                                    }else if(mess.mType == 31){
                                        alert("对方已有心愿好友");
                                    }else if(mess.mType == 17){
                                        alert("对方已是您的好友");
                                    }else if(mess.mType == 32){
                                        $(this).css("color","gold");
                                        $(this).addClass("do-likes-realize");
                                        alert("添加心愿好友成功！\n到好友界面聊天吧");
                                    }
                                }
                            });//ajax over
                        }
                    }
                });

            }
        });//ajax over
    };

    getDesireList();

    function doLikesAjax(){
        commMess.mType = 27;
        commMess.mToId = tId;
        commMess.mContent = doLikeNum;
        $.ajax({
            type: "POST",
            url: "/chat/desire/updateDoLike",
            data: commMess
        });//ajax over
    }

    function doLikesConnAjax(){
        commMess.mType = 27;
        commMess.mToId = tId;
        commMess.mContent = doLikeNum;
        $.ajax({
            type: "POST",
            url: "/chat/desire/updateDoLike",
            data: commMess
        });//ajax over
    }

    $(".reflash-btn").click(function (){
        getDesireList();
    });

    $(".public-desire-btn").click(function(){
        myDesire = $(".desire-text-field").val();
        if(myDesire == ''){
            alert("内容不能为空");
            return;
        }
        commMess.mType = 28;
        commMess.mContent = myDesire;
        $.ajax({
            type: "POST",
            url: "/chat/desire/publicDesire",
            data: commMess
        });//ajax over
        alert("发布成功！");
    });

})