$("#regist_btn").on("click", function() {
    regist_form_submit()
})

function regist_form_submit() {
    $.ajax({
        cache: !1,
        type: "POST",
        url: "/sbus/nologin/regist",
        data: $("#regist_form").serialize(),//form表单中的所有属性
        beforeSend: function() {
            $("#regist_btn").html("注册中..."),
            $("#regist_btn").attr("disabled", "disabled")
        },
        success: function(data) {
        	if(data.successed == true){
        		if(confirm("注册成功！马上登陆？"))
        		 {
        			var url = "/sbus/nologin/login";
            		location.href = url;
        		 }
        	}else{
        		$("#login-form-tips").html("注册失败，请稍后重试！").show(500)
        	}
        },
        complete: function() {
            $("#login_btn").html("注册"),
            $("#login_btn").removeAttr("disabled")
        }
    })
}
