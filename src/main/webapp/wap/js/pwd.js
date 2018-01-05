/**
 * Created by Administrator on 2016/10/19.
 */
uname.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("用户名不能为空！");
    }else if(this.validity.tooShort){
        this.setCustomValidity("密码长度不能小于6位！");
    }else{
        this.setCustomValidity("");
    }
}
pwd.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("密码不能为空！");
    }else if(this.validity.tooShort){
        this.setCustomValidity("密码长度不能小于6位！");
    }else{
        this.setCustomValidity("");
    }
}
pwd2.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("确认不能为空！");
    }else if(pwd.value!=pwd2.value){
        this.setCustomValidity("两次输入的密码数据不一致！");
    }else{
        this.setCustomValidity("");
    }
}
tcash.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("提现金额不能为空！");
    }else if(this.validity.tooShort){
        this.setCustomValidity("提现金额不能小于两位数字！");
    }else{
        this.setCustomValidity("");
    }
}
card.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("银行卡号不能为空！");
    }else if(this.validity.tooShort){
        this.setCustomValidity("请填写正确的银行卡号！");
    }else{
        this.setCustomValidity("");
    }
}
bank.onblur=function(){
    if(this.validity.valueMissing){
        this.setCustomValidity("银行卡不能为空！");
    }else if(this.validity.tooShort){
        this.setCustomValidity("请填写正确的银行卡名称！");
    }else{
        this.setCustomValidity("");
    }
}
