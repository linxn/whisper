/**
 * Created by linxn on 2018/4/13.
 */
$(document).ready(function(){

    //Cookie操作
    function setCookie(cname,cvalue,exdays)
    {
        var d = new Date();
        d.setTime(d.getTime()+(exdays*24*60*60*1000));
        var expires = "expires="+d.toGMTString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }

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
    $("#inputUserName").val(getCookie("defaultUsername"));
    $("#inputPassword_l").val(getCookie("defaultUserPwd"));

    //登录ajax
    $("#loginBtn").click(function(){

        var username = $("#inputUserName").val();
        var userpwd = $("#inputPassword_l").val();
        var loginMess = {mType : 3, mContent : '{uUsername : "' + username + '", uUserpwd : "' + userpwd + '"}'};
        var result = $.ajax({
            type: "POST",
            url: "/chat/user/checkUser",
            data: loginMess,
            success : function (mess) {
                var messObj = JSON.parse(mess);
                if(messObj.mType == 1){
                    var userM = messObj.mContent;
                    var user = JSON.parse(userM);
                    if(userM != '0'){
                        if ($('.remember-checkbox').is(':checked')) {
                            setCookie("defaultUsername", username, 7);
                            setCookie("defaultUserPwd", userpwd, 7);
                        }else{
                            document.cookie = "defaultUsername= ";
                            document.cookie = "defaultUserPwd= ";
                        }
                        document.cookie = "username="+username;
                        document.cookie = "userid="+user.uId;
                        document.cookie = "uHeadPhoto="+user.uHeadPhoto;
                        window.location.replace("/chat/html/homePage.html");
                    }else{
                        alert("用户名或密码错误");
                        $(".form-control").val('');
                    }
                };
            }
        });
    });

    //注册ajax
    $("#registerBtn").click(function(){

        var username = $("#inputUserName").val();
        var userpwd = $("#inputPassword_r").val();
        var checkpwd = $("#checkPassword").val();
        if(userpwd != checkpwd){
            alert("两次输入密码不一致！");
            return;
        }
        var registerMess = {mType : 4, mContent : '{uUsername : "' + username + '", uUserpwd : "' + userpwd + '"}'};

        var result = $.ajax({
            type: "POST",
            url: "/chat/user/register",
            data: registerMess,
            success : function (mess) {
                var messObj = JSON.parse(mess);
                if(messObj.mType == 1){
                    if(messObj.mContent == 1){
                        alert("注册成功！");
                        window.location.replace("/chat/html/login.html");
                    }else{
                        alert("用户名已存在");
                    }
                };
            }
        });
    });


})