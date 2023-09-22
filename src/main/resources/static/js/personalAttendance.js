$(function () {
    let tableObj = TableInit();//获取表格初始化对象
    tableObj.Init();//初始化表格
    getTableData();
    initDateTimePick()
})
//数据表格初始化
var TableInit = function () {
    var oTableInit = new Object();
    // 初始化Table
    oTableInit.Init = function () {
        $('#table').bootstrapTable({
            url: '/getAttendanceData', // 请求后台的URL（*）
            method: 'post', // 请求方式（*）
            contentType: "application/x-www-form-urlencoded",//post 必须制定类型，否则不能正常传值
            toolbar: '#toolbar', // 工具按钮用哪个容器
            striped: true, // 是否显示行间隔色
            cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, // 是否显示分页（*）
            sortName: "id",//默认排序列
            sortable: true, // 是否启用排序
            sortOrder: "asc", // 排序方式
            queryParams: oTableInit.queryParams,// 传递参数（*）
            sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, // 初始化加载第一页，默认第一页
            pageSize: 10, // 每页的记录行数（*）
            pageList: [10, 15, 20], // 可供选择的每页的行数（*）
            search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true, // 是否显示所有的列
            showRefresh: true, // 是否显示刷新按钮
            minimumCountColumns: 2, // 最少允许的列数
            clickToSelect: true, // 是否启用点击选中行
            singleSelect: false,//开启单选，默认为多选
            height: 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id", // 每一行的唯一标识，一般为主键列
            showToggle: false, // 是否显示详细视图和列表视图的切换按钮
            cardView: false, // 是否显示详细视图
            detailView: false, // 是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'signIn',
                title: '上午签到',
            },  {
                field: 'signOut',
                title: '下午签到',

            }, {
                field: 'createTime',
                title: '日期',
            },{
                field: 'op',
                title: '操作',
                formatter:function (value,rows,index) {
                    let html='<a class="glyphicon-trash"  href="javascript:void(0)" >详情</a>';//直接复制图标下面的代码
                    return html;
                },
                events:{
                    //事件名   选择器           回调函数
                    'click .glyphicon-trash':function (ev,value,rows,index) {
                        ev.stopPropagation();//阻止事件冒泡
                        showById(rows.id);//根据id删除记录
                    },
                },
            }]
        });
    };
    // 得到查询的参数
    oTableInit.queryParams = function (params) {
        var page = (params.offset / params.limit) + 1;
        var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            size: params.limit, // 页面大小
            page: page, // 第几页
            keyWords:$("#search_id").val(),
        };
        return temp;
    };
    oTableInit.refresh=function () {
        $('#table').bootstrapTable('refresh');
    }
    return oTableInit;
};
function getTableData() {
    $.ajax({
        url:"/getAttendanceData",
        type:"post",
        data:{},
        dataType:"json",
        success:function (ecs) {
            console.log(ecs)
            if (ecs.code==1){
                let data=ecs.rows;
                let total=ecs.total;
            }else {
                console.log(ecs)
            }

        }
    })

}
function showById(id){
    $.ajax({
        url:"/showById",
        type:"post",
        data: {
            id: id,
        },
        dataType:"json",
        success:function (ecs) {
            if (ecs.code==1){
                let data=ecs.data;
                console.log(data)
                $("#modalTitle").val(data.title)
                $("#modalContent").val(data.content)
                $('#myModal').modal('show')

            }
        }
    })
}

// function findaway() {
//     let keyWords=document.getElementById("search_id").value;
//     $.ajax({
//         url:"/getkeyData",
//         type:"post",
//         data:{
//             keyWords:keyWords,
//         },
//         dataType:"json",
//         success:function (ecs) {
//             console.log(ecs)
//             if (ecs.code==1){
//                 let data=ecs.rows;
//                 let total=ecs.total;
//                 $('#table').bootstrapTable('refresh',{pageNumber: 1});
//             }else {
//                 console.log(ecs)
//             }
//
//         }
//     })
// }
function findaway() {
    $('#table').bootstrapTable('refresh',{pageNumber: 1});
}
function initDateTimePick(){
    console.log(333)
    $("#search_id").datetimepicker({
        format: 'yyyy-mm',
        language:'zh-CN',
        autoclose:true,
        startView:3,
        minView:3//可选择月份中的每一天
    }).on('hide',function (ev) {
        ev.stopPropagation()//阻止'关闭日期选择器'的关闭事件冒泡
    })
}