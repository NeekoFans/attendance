function register() {
    let account=$("#account").val();
    let password=$("#password").val();
    let password2=$("#password2").val();
    if (account == null||account=="") {
        alert("账户名不可为空");
        return;
    }
    if (!/^.{0,15}$/.test(account)){
        alert("账号长度不能超过15个字符")
        return;
    }
    if (password == null||password=="") {
        alert("密码不可为空");
        return;
    }
    if (!/^.{0,8}$/.test(password)){
        alert("密码长度不能超过8个字符")
        return;
    }
    if (password!=password2){
        alert("两次输入的密码不一致")
        return;
    }
    $.ajax({
        url:"/register",
        type:'post',
        data: {
            account:account,
            password:password,
        },
        dataType:'json',
        success:function (cs) {
            if (cs.code==0){
                alert(cs.msg)
            }else {//不添加else框起来的话alert框会先输出上面的，点击确定后显示下面的
                alert(cs.msg)
            }
        },
        error:function (ev) {
            console.log(ev)
        }
    })
}