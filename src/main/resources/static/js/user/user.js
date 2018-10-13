$("#save_btn").on("click", function() {
    userinfo_form_submit()
})

function userinfo_form_submit() {
    $.ajax({
        cache: !1,
        type: "POST",
        url: "/sbus/user/updateUserInfo",
        data: $("#userinfo").serialize(),//form表单中的所有属性
        beforeSend: function() {
            $("#save_btn").html("保存中..."),
            $("#save_btn").attr("disabled", "disabled")
        },
        success: function(data) {
        	if(data.successed == true){
        		var url = "/sbus/user/getUserInfo?userid="+data.datas.id;
        		location.href = url;
        	}else{
        		alert("保存失败!");
        	}
        },
        complete: function() {
            $("#save_btn").html("保存"),
            $("#save_btn").removeAttr("disabled")
        }
    })
}