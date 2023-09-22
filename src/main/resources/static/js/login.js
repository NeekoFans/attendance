$(function () {
    //cookie回填账户名密码
    // let account=$.cookie('Zh');
    // let password=$.cookie('Mm');
    // $("#account").val(account);
    // $("#password").val(password);
    // $("#account").val(localStorage.getItem("account"));
    // $("#password").val(localStorage.getItem("password"));
})
function Login(){
   let account=$("#account").val();
   let password=$("#password").val();
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
    $.ajax({
        url: "/login",
        type: 'post',
        data: {
            "account": account,
            "password": password,
        },
        dataType: 'json',
        success: function (cs) {
            if (cs.code==1){
                // $.cookie('Zh', 'account');
                // $.cookie('Mm', 'password');
                // localStorage.setItem("account",account);
                // localStorage.setItem("password",password);
                window.location.href="/User/index"
            }else {
                console.log(cs)
            }

        },
    })
}