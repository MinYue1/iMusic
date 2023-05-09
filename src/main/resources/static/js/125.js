// 获取要操作的元素
let login_title=document.querySelector('.login-title');
let register_title=document.querySelector('.register-title');
let login_box=document.querySelector('.login-box');
let register_box=document.querySelector('.register-box');

// 绑定标题点击事件
login_title.addEventListener('click',()=>{
    // 判断是否收起，收起才可以点击
    if(login_box.classList.contains('slide-up')){
        register_box.classList.add('slide-up');
        login_box.classList.remove('slide-up');
    }
})
register_title.addEventListener('click',()=>{
    if(register_box.classList.contains('slide-up')){
        login_box.classList.add('slide-up');
        register_box.classList.remove('slide-up');
    }
})

/*提示信息框动态效果*/
var messageboxT;
var top1 = 10;
var opacity = 0;
//初始化数据
function initMessagebox(info) {
    top1 = 10;
    opacity = 0;
    opacity1 = 1;
    $("#messagebox").html(info);
    $("#messagebox").css({ 'display': 'block' });
    appearMessagebox();
}
//出现信息框
function appearMessagebox() {
    top1 += 1;
    opacity += 0.05;
    if (opacity < 1) {
        messageboxT = setTimeout("appearMessagebox()", 15);
    }
    else {
        disappearMessagebox();
    }
    $("#messagebox").css({ 'top': String(top1) + 'px' });
    $("#messagebox").css({ 'opacity': String(opacity) });
}
//隐藏信息框
function disappearMessagebox() {
    opacity -= 0.05;
    if (opacity > 0.05) {
        messageboxT = setTimeout("disappearMessagebox()", 160);
    }
    else {
        opacity = 0;
        $("#messagebox").css({ 'display': 'none' });
    }
    $("#messagebox").css({ 'opacity': String(opacity) });
}


