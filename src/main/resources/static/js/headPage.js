$(function () {
    $.ajax({
        url: "/getNotice",
        type: 'post',
        dataType: 'json',
        success: function (cs) {
            console.log(cs)
            let data=cs.data;
            let t=document.getElementById("noticeTitle");
            let c=document.getElementById("noticeContent")
            let ct=document.getElementById("noticeCreateTime")
            t.innerText=data.title;
            c.innerText=data.content;
            ct.innerText=data.createTime;
            }
    })
})
var st;
function sign_in() {
    let d=new Date();
    let t=document.getElementById("sign_in");
    t.innerText=d.getHours().toString()+":";
    st=t.innerText;
    if (d.getMinutes()<10){
        st+="0"+d.getMinutes().toString();
    }else {
        st+=d.getMinutes().toString();
    }
    t.innerText=st;
    document.getElementById("sign_in").disabled = true
    let sin=st;
    // $.ajax({
    //     url: "/sign_in",
    //     type: 'post',
    //     data: {
    //         sin:sin
    //     },
    //     dataType: 'json',
    //     success: function (cs) {
    //         console.log(cs)
    //         }
    // })
}
function sign_out() {
    let d=new Date();
    let t=document.getElementById("sign_out");
    t.innerText=d.getHours().toString()+":";
    let sout=t.innerText;
    if (d.getMinutes()<10){
        sout+="0"+d.getMinutes().toString();
    }else {
        sout+=d.getMinutes().toString()
    }
    t.innerText=sout;
    document.getElementById("sign_out").disabled = true

    $.ajax({
        url: "/getLoginInfo",
        dataType: 'json',
        success: function (cs) {
            $.ajax({
                url: "/sign_out",
                type: 'post',
                data: {
                    uid:(cs.data.account),
                    sout:sout,
                    st:st
                },
                dataType: 'json',
                success: function (cs) {
                    console.log(cs)
                }
            })
        }
    })

}
