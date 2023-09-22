$(function () {
    editById();
})
function editById(){
    $.ajax({
        url:"/getInfo",
        type:"post",
        data:{
        },
        dataType:"json",
        success:function (ecs) {
            console.log(ecs);
                let data=ecs.data;
                $('#name').val(data.name);
                $('#account').val(data.account);
                $('#tel').val(data.tel);
                $('#password').val(data.password);
                $('#account').prop('disabled',true);
                $('#name').prop('disabled',true);
                $('#tel').prop('disabled',true);
                $('#password').prop('disabled',true);

        }
    })
}
function nameClick1() {
$("#nameBtn1").css('display','none');
$("#nameBtn2").css('display','inline-block');
$("#nameBtn3").css('display','inline-block');
    $('#name').prop('disabled',false);
}
function nameClick2(){
    $("#nameBtn1").css('display','inline-block');
    $("#nameBtn2").css('display','none');
    $("#nameBtn3").css('display','none');
    $('#name').prop('disabled',true);

}
function nameClick3(){
    let n=document.getElementById("name").value;
    let a=document.getElementById("account").value;
    $.ajax({
        url:"/nameEdit",
        type:"post",
        data: {
            account:a,
            name:n
        },
        dataType:"json",
        success:function (ecs) {

        }
    })
}
function telClick3(){
    let n=document.getElementById("tel").value;
    let a=document.getElementById("account").value;
    $.ajax({
        url:"/telEdit",
        type:"post",
        data: {
            account:a,
            tel:n
        },
        dataType:"json",
        success:function (ecs) {

        }
    })
}
function passwordClick3(){
    let n=document.getElementById("password").value;
    let a=document.getElementById("account").value;
    $.ajax({
        url:"/passwordEdit",
        type:"post",
        data: {
            account:a,
            password:n
        },
        dataType:"json",
        success:function (ecs) {

        }
    })
}
function telClick1() {
    $("#telBtn1").css('display','none');
    $("#telBtn2").css('display','inline-block');
    $("#telBtn3").css('display','inline-block');
    $('#tel').prop('disabled',false);
}
function telClick2(){
    $("#telBtn1").css('display','inline-block');
    $("#telBtn2").css('display','none');
    $("#telBtn3").css('display','none');
    $('#tel').prop('disabled',true);

}
function passwordClick1() {
    $("#passwordBtn1").css('display','none');
    $("#passwordBtn2").css('display','inline-block');
    $("#passwordBtn3").css('display','inline-block');
    $('#password').prop('disabled',false);
}
function passwordClick2(){
    $("#passwordBtn1").css('display','inline-block');
    $("#passwordBtn2").css('display','none');
    $("#passwordBtn3").css('display','none');
    $('#password').prop('disabled',true);
}


