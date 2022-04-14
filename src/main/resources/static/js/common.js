//var CodeXu_url = "http://127.0.0.1:8081";
var CodeXu_url = "http://101.35.17.233:8081";
var tianxin_url = "http://114.86.224.5:7777";


function commonAjax(type,url,methodName,data){

    if(type != "get" || type != "post"){
        alert("请求方式有误！请选择 get 或者 post ！");
        return;
    }

    $.ajax({
        type:type,
        dateType: 'json',
        contentType: 'text/json,charset=utf-8',
        url: url + methodName,
        data: JSON.stringify(data),
        success :function (result){
            console.log(result);
        },
        fail: function (result){
            console.log("请求失败！"+result);
        }
    });
}




