var situationBoolean = false;


onload = function (){
    //登录点击后提示
    var message0 = $("#message0").val();
    console.log(message0)
    if(message0 != "" && message0 != undefined){
        initMessagebox(message0);
        $("#message0").html(null);
    }

    //注册点击后提示
    var message1 = $("#message1").val();
    // console.log(message1)
    if(message1 == "true"){
        initMessagebox("注册成功，请登入～");
        $("#message1").html(null);
    }

    //是否填写验证码
    var codetrue = $("#codetrue").val();
    console.log('sfadf:::'+codetrue)
    if(codetrue == "true"){
        initMessagebox("多次登录失败，请填写验证码！");
        $("#code_text").css("display", "block");
        $("#code_div").css("display", "block");
        document.getElementById("img_code").src = "verifyCode?id="+Math.random();

        $("#message1").html(null);
    }
}

function UserName(u){
    var v = $(u).val();
    console.log(v)
    $.ajax({
        type:"post",
        url:"registerName",
        data:"username="+v,
        success:function (msg){
            situationBoolean = msg;
            if(msg){
                $("#error1").html("✅");
            }else{
                $("#error1").html("❌");
                if(!(typeof(v)=="undefined"||v == ''||v == null))initMessagebox("该用户名已注册");
            }
            //清空消息提示
            clearMsg(v,1);
        }
    })
}
var passwrod;
var p1 = false;
function UserPass1(u){
    var v = $(u).val();
    passwrod =v;
    // 验证密码强度S
    // 有小写字母，数字，其他字符，级别+1
    var strengthLevel = document.getElementById("strengthLevel");
    // 获取用户输入内容
    // 记录安全级别
    var level = 0;
    // 正则表达式判断输入的东西
    // 小写字母，级别+1
    if (/[a-z]/.test(v)) {
        level++;
    }
    // 数字+1
    if (/[0-9]/.test(v)) {
        level++;
    }
    // 其他+1
    if (/[^a-z0-9]/.test(v)) {
        level++;
    }
    if(v != '')$("#error3").html("❌");
    else $("#error3").html("");
    if(level < 2 && v != ''){
        initMessagebox("密码太简单啦")
        $("#error2").html("❌");
        p1 = false;
    }else{
        $("#error2").html("✅");
        p1 = true;
    }
    //清空消息提示
    clearMsg(v,2);
    strengthLevel.className = "strengthLv" + level;


}

var p2 = false;
function UserPass2(u){
    var v = $(u).val();
    if(v != passwrod){
        p2 = false;
        $("#error3").html("❌");
        if(!(typeof(v)=="undefined"||v == ''||v == null))initMessagebox("两次输入的密码不一致");
    }else{
        p2 = true;
        $("#error3").html("✅");
    }
    clearMsg(v,3);
}

//消息清空
function clearMsg(v,index){
    //清空消息提示
    if (typeof(v)=="undefined"||v == ''||v == null) {
        $("#error"+index).html(null);
    }
}

//验证输入文本框是否为空,是否能提交
function FromTested(index){
    switch (index){
        case 1:{
            //注册
            return IfNull($("#R_user").val()) && IfNull($("#R_password1").val()) && IfNull($("#R_password2").val()) && situationBoolean && p1 && p2;
        }
        case 2:{
            //登录
            var boolean1 = false;
            if($("#L_user").val() == '' || $("#L_password").val() == ''){
                initMessagebox("嘿You、用户名或密码没填呢！");
                return false;
            }else{
                return true;
            }

        }
        default: return false;
    }

}
//判断字符串 是否为空
function IfNull(str){
    if(str != '' && str != null && str != undefined){
        return true;
    }
    else{
        initMessagebox("嘿You、还没填完呢！");
        return false;
    }
}
//切换登录或注册页面时，清除输入框消息提示
 var clean1 = document.getElementById("cleanAllMesg1");
 var clean = document.getElementById("cleanAllMesg");

 clean1.onfocus = function(){
    cleanAll()
}
 clean.onfocus = function(){
     cleanAll();
 }
function cleanAll(){
    //All
    //输入框 提示 消失
    for(let i = 0;i < 5;i++){
        $("#error"+i).html(null);
    }
    //密码强度条 消失
    document.getElementById("strengthLevel").className= "strengthLv0";

}