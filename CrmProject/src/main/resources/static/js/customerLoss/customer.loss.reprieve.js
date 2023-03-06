layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //订单列表展示
    var tableIns = table.render({
        elem: '#customerLossRepList',
        url: ctx + '/cus_reprieve/list?lossId=' + $("input[name='id']").val(),
        cellMinWidth: 95,
        page: true,
        height: "full-100",
        limits: [5, 10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "customerOrderListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'measure', title: '暂缓措施', align: "center"},
            {field: 'createDate', title: '创建时间', align: "center"},
            {field: 'updateDate', title: '更新时间', align: "center"},
            {title: '操作', fixed: "right", align: "center", minWidth: 150, templet: "#customerLossRepListBar"}
        ]]
    });


    /**
     * 行监听
     */
    table.on("tool(customerLossRep)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            // alert(obj.data.id)
            openAddOrUpdateCusRepDialog(obj.data.id);
        } else if (layEvent === "del") {
            layer.confirm('确定删除当前数据?', {icon: 3, title: "联系人管理"}, function (index) {
                $.post(ctx + "/cus_reprieve/delete", {id: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }

    });

    //头部工具栏事件
    table.on('toolbar(customerLossRep)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case "add":
                openAddOrUpdateCusRepDialog();
                break;
            case "confirm":
                updateCustomerLossState();
                break;
        }
    });

    function openAddOrUpdateCusRepDialog(id) {
        var url = ctx + "/cus_reprieve/addOrUpdateCusRep?lossId=" + $("input[name='id']").val();
        var title = "暂缓管理-添加暂缓";
        if (id) {
            url = url + "&id=" + id;
            title = "暂缓管理-更新暂缓";
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "400px"],
            maxmin: true,
            content: url
        });
    }

    /**
     * 更新流失客户的流失状态
     */
    function updateCustomerLossState() {
        // 弹出确认框，询问用户是否确认流失
        layer.confirm('确定标记当前客户为确认流失吗?', {icon: 3, title: "客户流失管理"}, function (index) {
            // 关闭确认框
            layer.close(index);

            // prompt层  输入框
            layer.prompt({title: '请输入流失原因', formType: 2}, function (text, index) {
                // 关闭输入框
                layer.close(index);

                /**
                 * 发送请求给后台，更新指定流失客户的流失状态
                 *  1. 指定流失客户   流失客户ID （隐藏域）
                 *  2. 流失原因      输入框的内容（text）
                 */
                $.ajax({
                    type: "post",
                    url: ctx + "/customer_loss/updateCustomerLossStateById",
                    data: {
                        id: $("[name='id']").val(), // 流失客户的ID
                        lossReason: text // 流失原因
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg('确认流失成功！', {icon: 6});
                            // 关闭窗口
                            layer.closeAll("iframe");
                            // 刷新父页面
                            parent.location.reload();
                        } else {
                            layer.msg(result.msg, {icon: 5});
                        }
                    }
                });
            });
        });
    }
});