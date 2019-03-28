/**
 * Created by linxn on 2018/4/24.
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
    var userid = getCookie("userid")

    var commMess = {mType : 23, mFromId : userid, mContent : ""};


    function getTreeholeList() {
        $.ajax({
            type: "POST",
            url: "/chat/treehole/treeholeList",
            data: {mType : 23, mFromId : userid},
            success: function (data) {
                var mess = JSON.parse(data);
                //console.log(mess.mContent);
                var th_list = mess.mContent;
                var obj = JSON.parse(th_list);
                var ap = "";
                for(var i = 0; i < obj.length; i++){
                /*<div class="TH-container">
                        <div class="TH-text">
                        <span class="if-do-like" style="display: none;">0</span>
                        <span class="th-num" style="display: none;">1</span>
                        <span>我的愿望是快高长大~~</span>
                    <div class="do-likes"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i>&nbsp;<span class="do-likes-num">100</span></div>
                    </div>
                    </div>
                    <br />*/

                    ap = ap + '<div class="TH-container"> <div class="TH-text"> <span class="th-num" style="display: none;">'+
                            + obj[i].tId
                            + '</span> <span class="if-do-like" style="display: none;">0</span> <span>'
                            + obj[i].tContent
                            +'</span> <div class="do-likes"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i>&nbsp;<span class="do-likes-num">'
                            + obj[i].tLikesCount
                            +'</span></div> </div> </div> <br />';
                };
                $(".none-box").children().remove();
                $(".none-box").append(ap);

                $(".do-likes").click(function(){
                    if($(this).siblings(".if-do-like").text() == '0'){
                        //send ajax request
                        $(this).css("color","gold");
                        doLikeNum = Number($(this).children(".do-likes-num").text()) + 1;
                        $(this).children(".do-likes-num").text(doLikeNum);
                        $(this).siblings(".if-do-like").text("1");
                        tId = $(this).siblings(".th-num").text();
                        doLikeAjax(tId, doLikeNum);

                    }else {
                        //send ajax request
                        $(this).css("color","inherit");
                        doLikeNum = Number($(this).children(".do-likes-num").text()) - 1;
                        $(this).children(".do-likes-num").text(doLikeNum);
                        $(this).siblings(".if-do-like").text("0");
                        tId = $(this).siblings(".th-num").text();
                        doLikeAjax(tId, doLikeNum);
                    }
                });
            }
        });//ajax over
    };

    getTreeholeList();

    $(".reflash-btn").click(function (){
        getTreeholeList();
    });

    function doLikeAjax(tId, doLikeNum) {
        commMess.mType = 24;
        commMess.mToId = tId;
        commMess.mContent = doLikeNum;
        $.ajax({
            type: "POST",
            url: "/chat/treehole/updateDoLike",
            data: commMess
        });//ajax over
    }

    $(".public-th-btn").click(function(){
        myth = $(".th-text-field").val();
        if(myth == ''){
            alert("内容不能为空");
            return;
        }
        commMess.mType = 25;
        commMess.mContent = myth;
        $.ajax({
            type: "POST",
            url: "/chat/treehole/publicTreehole",
            data: commMess,
        });//ajax over
        alert("发布成功！");
    });
});



