
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
