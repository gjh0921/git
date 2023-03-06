layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    //监听提交
    form.on('submit(saveBtn)', function (data) {
        $.ajax({
            type:"post",
            url:ctx+"/user/updatePassword",
            data:{
                oldPassword:data.field.old_password,
                newPassword:data.field.new_password,
                confirmPassword:data.field.again_password
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg("密码更新成功,3s后系统将自动退出,请重新登录!",function () {
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                        $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
                        setTimeout(function () {
                            window.parent.location.href=ctx+"/index";
                        },3000);
                    })
                }else{
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    });
});