
function reg(){
    if(!$('#reg-valid').valid()){
        return false;
    }
    if($('#readUserPro:checked').length == 0){
        layer.msg("请阅读协议，然后勾选同意！");
        return false;
    }
    return true;
}