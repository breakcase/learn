// 原来 $(function(){…}); 也可以写作 jQuery(function($) {…}); ，
// 其实就是$(document).ready(funcrtion{...}); 的简写
/*
注释是配套index2.html 
$(".globalLogin").on("click", function() {
    login_form_submit()
})

function login_form_submit() {
    $.ajax({
        cache: !1,
        type: "POST",
        url: "/sbus/loginVerify",
        data: $("#login_form").serialize(),//form表单中的所有属性
        beforeSend: function() {
            $("#login_btn").html("登录中..."),
            $("#login_btn").attr("disabled", "disabled")
        },
        success: function(data) {
        	if(data.successed == true){
        		alert("登录成功！");
        		//将原来的‘登录’替换成当前登录用户名
        		//TODO
        	}else{
        		$("#login-form-tips").html("账号或者密码错误，请重新输入").show(500)
        	}
        },
        complete: function() {
            $("#login_btn").html("登录"),
            $("#login_btn").removeAttr("disabled")
        }
    })
}*/

$("#login_btn").on("click", function() {
    login_form_submit()
})

function login_form_submit() {
    $.ajax({
        cache: !1,
        type: "POST",
        url: "/sbus/nologin/loginVerify",
        data: $("#login_form").serialize(),//form表单中的所有属性
        beforeSend: function() {
            $("#login_btn").html("登录中..."),
            $("#login_btn").attr("disabled", "disabled")
        },
        success: function(data) {
        	if(data.successed == true){
        		var url = "/sbus/";
        		location.href = url;
        	}else{
        		$("#login-form-tips").html("账号或者密码错误，请重新输入").show(500)
        	}
        },
        complete: function() {
            $("#login_btn").html("登录"),
            $("#login_btn").removeAttr("disabled")
        }
    })
}
