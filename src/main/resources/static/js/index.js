$(function () {

    getLoginInfo();
    perInfoClick();
    var headPage=document.getElementById('headPage')
    headPage.click()
})
function getLoginInfo() {
    $.ajax({
        url: "/getLoginInfo",
        dataType: 'json',
        success: function (cs) {

            $('#ZHao').text(cs.data)
        }
    })
}

function backLogin() {
    window.location.href="/User/Login"
}
function perInfoClick() {
$('.First_td').on('click','.left_info',function () {
    let l1=document.getElementById('iframe1');
    l1.style.display='inline-block'
})
}
function t1() {
    let l1=document.getElementById('iframe1');
    l1.style.display='inline-block'
}
