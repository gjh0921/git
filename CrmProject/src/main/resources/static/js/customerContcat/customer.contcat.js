layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //订单列表展示
    var  tableIns = table.render({
        elem: '#cusContcatList',
        url : ctx+'/customerContcat/list?cusId='+$("input[name='id']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-100",
        limits : [5, 10, 15, 20, 25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "cusContcatList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'contactTime', title: '交往时间',align:"center"},
            {field: 'address', title: '交往地址',align:"center"},
            {field: 'overview', title: '交往事件',align:"center"},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#cusContcatBar"}
        ]]
    });


    /**
     * 行监听
     */
    table.on("tool(cusContcat)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            // alert(obj.data.id)
            openAddOrUpdateCusContcatDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前数据?', {icon: 3, title: "联系人管理"}, function (index) {
                $.post(ctx+"/customerContcat/delete",{ids:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }

    });

    //头部工具栏事件
    table.on('toolbar(cusContcat)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateCusContcatDialog();
                break;
            case "del":
                delUser(checkStatus.data);
                break;
        }
    });

    // 打开添加交往记录数据页面
    function openAddOrUpdateCusContcatDialog(id)  {
        var url  =  ctx+"/customerContcat/addOrUpdateCusContcatPage?cid="+$("input[name='id']").val();
        var title="交往记录管理-添加交往记录";
        if(id){
            url = url+"&id="+id;
            title="交往记录管理-更新交往记录";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["700px","400px"],
            maxmin:true,
            content : url
        });
    }

    /**
     * 批量删除
     * @param datas
     */
    function delUser(datas) {
        if(datas.length==0){
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }
        layer.confirm('确定删除选中的用户记录？', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close(index);
            var ids= "ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else {
                    ids=ids+datas[i].id
                }
            }
            $.ajax({
                type:"post",
                url:ctx+"/customerContcat/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                }
            })
        });
    }

});