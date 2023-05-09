onload = function (){
    $(".right-minbox").load("gxtj");
}
function gxtj(){
    $(".right-minbox").load("gxtj");
}
function zsdz(){
    $(".right-minbox").load("zsdz");
}
function upRequest(){
    $.ajax({
        url: "upRequest",
        type: "get",
        success:function(data){
            console.log("==="+data)
            if(data == true){
                //已登录跳转
                $(".right-minbox").load("upMusiclists");
            }
            else{
                //未登录
                initMessagebox("未登录，无法访问！");
            }

        }
    })
    // document.getElementById("tableData1").innerHTML = tabletr;

}

//异步提交mp3文件
function upfile(){
    var form_data = new FormData($("#upMp3file")[0]);
    console.log(111);
    $.ajax({
        url: "upfile",
        data: form_data,
        type: "post",
        async: false,
        cache: false,
        contentType: false,//发送数据的类型
        processData: false,
        beforeSend:function(){
            initMessagebox("正在处理...");
            return true;
        },
        success:function(data){
            console.log("请求成功返回数据============="+data);
            if(data == true){
                initMessagebox("上传文件成功！");
                //刷新数据
                $(".right-minbox").load("upMusiclists");
            }else{
                initMessagebox("上传失败，请重试");
            }

        },
        error:function(){
            initMessagebox("请求出错!");
        },

    })
}
