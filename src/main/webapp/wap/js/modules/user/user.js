
function myMessages() {
    window.location.replace(ctxWap + '/user/userMessageList?' + Math.random());
}

function modifyChnName(){
    layer.open({
        content: '<input id="chnName" autofocus type="text" style="line-height: 0.6rem; font-size: 0.6rem;" placeholder="请输入中文姓名" value=""/>',
        btn: '确定',
        yes: function(){
            var chnName = $('#chnName').val();
            $.ajax({
                url:ctx + '/users/modifyChnName',
                type: 'put',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    chnName: chnName
                }),
                success: function(){
                    layer.msg('修改成功！');
                    $('#chnName_p').text(chnName);
                }
            });

        }
    });
}

function modifyMobile(){
    window.location.replace(ctxWap + '/user/modifyMobile?' + Math.random());
}

function modifyTradePwd() {
    window.location.replace(ctxWap + '/user/modifyTradePwd?' + Math.random());
}