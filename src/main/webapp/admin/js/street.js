$(function () {
    $('#dg').datagrid({
        url:'getStreet',
        pagination:true,
        pageSize:10,
        pageList:[10,20,30,40],
        columns:[[
            {field:'id',title:'编号',width:100,align:'center',checkbox:true},
            {field:'name',title:'姓名',width:100},
            {field:'districtId',title:'区域编号',width:100},
            {field:'option',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteStreet("+row.id+")'>删除</a>|<a href='javascript:updateDetail("+row.id+")'>编辑</a>"
                }
            }
        ]]
    });
    $('#dg').datagrid({
        toolbar:'#ToolBar' //指定工具栏
    });
})
function searchUser() {
    //追加条件在请求中
    var streetname=$("#streetname").val();
    var districtId=$("#districtId").val();
    $('#dg').datagrid({
        queryParams: {
            //streetname是查询条件实体中的属性，对应放值
            streetname: streetname,
            districtId: districtId
        }
    });


}
function deleteMoreStreet() {
    //获取选中的所有复选框
    var selections=$('#dg').datagrid("getSelections");
    //判断用户是否有选中至少一条数据
    if(selections.length>0){
        //提示用户，r代表确定按钮
        $.messager.confirm("系统提示", "是否确认删除",function (r) {
            if(r){
                //创建字符串，拼接字符串
                var str='';
                for (var i = 0; i < selections.length; i++) {
                    //每个字符串后面都要添加个逗号拼接
                    str=str+selections[i].id+",";
                }
                //去掉末尾的字符串，注意是去掉字符串末尾的
                str=str.substring(0,str.length-1);
                //测试
                // alert(str);
                $.post("deleteMoreStreet",{ids:str},function (data) {
                    $("#ToolBar").append("<span style='color: red;'>成功删除了"+data.result+"数据</span>")
                },"json");
                //删除成功后刷新页面
                $('#dg').datagrid('reload');
            }
        });
    }else{
        $.messager.alert("系统提示", "请选择至少一条数据");
    }
}
function deleteStreet(id) {
    $.messager.confirm("系统提示", "是否确认删除",function (r) {
        if(r){
            $.post("deleteStreet",{id:id},function (data) {
                if (data.result>0){
                    $('#dg').datagrid('reload');
                    $("#updateDialog").dialog("close");
                } else {
                    $.messager.alert("系统提示", "删除成功");
                    $('#dg').datagrid('reload');
                }
            },"json")
        }
    });
}
function updateDetail(id) {
    $("#updateDialog").dialog("open").dialog('setTitle',"编辑区域");
    // $("#AddDialog").form("load",selections[0]);
    $.post("listStreet",{id:id},function (data) {
        $("#updateDialog").form("load",data);
    },"json")
}
function updateDialog() {
    $('#UpdateDialogForm').form({
        url:"updateStreet",
        onSubmit: function(){
            // do some check
            // return false to prevent submit;
        },
        success:function(data){
            var result=$.parseJSON(data);
            if (result.result>0){
                $('#dg').datagrid('reload');
                $("#updateDialog").dialog("close");
            } else {
                $.messager.alert("系统提示", "更新失败");
                $('#dg').datagrid('reload');
            }
        }
    });
    $('#UpdateDialogForm').submit();
}
function goadd(){
    //打开对话框
    //$("#AddDialog").dialog("open");
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}
function CloseDialog(id) {
    $("#" + id).dialog(close);
    // $("#dg").dialog("unselectRow"+id);
}
function SaveDialog() {
    $('#ModiyDialogForm').form({
        url:"addStreet",
        onSubmit: function(){
            // do some check
            // return false to prevent submit;
        },
        success:function(data){
            var result=$.parseJSON(data);
            if (result.result>0){
                $('#dg').datagrid('reload');
                $("#AddDialog").dialog("close");
                $("#bname").val("");
            } else {
                $.messager.alert("系统提示", "添加失败");
                $('#dg').datagrid('reload');
            }
        }
    });
// submit the form
    $('#ModiyDialogForm').submit();
}
function ModifyBySelect() {
    var selections=$('#dg').datagrid("getSelections");
    if(selections.length==1){
        $("#updateDialog").dialog("open").dialog('setTitle',"编辑区域");
        // $("#AddDialog").form("load",selections[0]);
        $.post("listStreet",{id:selections[0].id},function (data) {
            $("#updateDialog").form("load",data);
        },"json")
    }else{
        $.messager.alert("系统提示", "没选择或者选中多条");
    }
}