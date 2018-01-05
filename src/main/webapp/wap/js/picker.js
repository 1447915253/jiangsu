/**
 *
 * demo:
 * >> html:
 * <li id="selectMoney"><span>100</span><b></b></li>
 * >> js:
 * pick('selectMoney', [10, 20, 50, 100, 200, 500, 800, 1000], 100, function (val) {
 *     $('#selectMoney').html('<span>'+val+'</span><b></b>');
 * });
 *
 */
(function () {
    var _opts = {};
    var _all_opts = {};
    var _all_val = {};
    var _current_domId = null;
    var _base_index = 1020;
    var dom_id_generate = 0;

    window.pick = function(domId, items, defaultVal, sureFun){
        _over();

        var isBind = true;
        if(domId == null){
            isBind = false;
            domId = 'auto_picker_' + (dom_id_generate++);
        }
        _all_opts[domId] = {};
        _opts = _all_opts[domId];
        _opts.items = !!!items || items.length == 0 ? ['空'] : items;
        defaultVal = defaultVal || _opts.items[0];
        for (var i = 0; i < _opts.items.length; i++){
            if(_opts.items[i] == defaultVal){
                _opts.defaultVal = i;
            }
        }
        _opts.sureFun = sureFun || function(){};
        _all_val[domId] = _opts.defaultVal;

        if(isBind){
            var it = document.getElementById(domId);
            if(it.attachEvent){
                it.attachEvent('onclick', function () {
                    _fire(domId);
                });
                it.attachEvent('ontouch', function () {
                    _fire(domId);
                });
            }else {
                it.addEventListener('click', function () {
                    _fire(domId);
                });
                it.addEventListener('touch', function () {
                    _fire(domId);
                });
            }
        }

        return {
            setVal : function (val) {
                for(var i = 0; i < _all_opts[domId].items.length; i++){
                    if(val == _all_opts[domId].items[i]){
                        _setVal(domId, i);
                        return;
                    }
                }
                _setVal(domId, 0);
            },
            show : function () {
                _fire(domId);
            }
        };
    };

    function _setVal(domId, witch) {
        _all_val[domId] = witch;
        _all_opts[domId].sureFun(_all_opts[domId].items[witch], witch);
    }

    var _win_width = null, _win_height =  null, _them_top =  null, _middle_top = null, _main_height = null;

    function init() {
        _win_width = window.document.body.clientWidth;
        _win_height = window.document.body.clientHeight;

        _them_top = _win_height*0.3/2;
        _middle_top = _win_height*0.3/2;
        _main_height = _win_height*0.3;
    }
    init();

    var _item = null;
    var _main = null;
    var _title_div = null;
    var _them = null;

    var _them_top_arr = null;
    var _li_height = null;
    var _witch = -1;
    var _pre_witch = -1;

    // 字体占高
    var lineHeight = 0.9;
    var _li_style_common = 'color: white; line-height: '+lineHeight+'rem; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;';
    var _li_style= _li_style_common + ' font-size: 0.4rem;';
    var _li_selected_style= _li_style_common + ' font-size: 0.5rem';

    function _fire(domId){
        _current_domId = domId;
        // 重置数据
        _them_top = _win_height*0.3/2;
        _them_top_arr = [];
        _opts = _all_opts[_current_domId];
        _witch = _all_val[_current_domId];
        _witch = _witch > _opts.items.length - 1 ? 0 : _witch;

        var item = document.createElement('div');
        _item = item;
        item.style.position = 'absolute';
        item.style.width = '100%';
        item.style.height = '100%';
        item.style.backgroundColor = 'black';
        item.style.filter = 'alpha(opacity=50)';
        item.style.mozOpacity = '0.50';
        item.style.opacity = '0.5';
        item.style.zIndex = _base_index;

        _bindEvent(item, 'click', function () {
            _over();
        });
        _bindEvent(item, 'touch', function () {
            _over();
        });

        document.body.appendChild(item);

        _main = document.createElement('div');
        _main.style.position = 'absolute';
        _main.style.width = _win_width+'px';
        _main.style.height = _main_height + 'px';
        _main.style.top = (_win_height - _main_height)+'px';
        _main.style.backgroundColor = '#AAAAAA';
        _main.style.overflow = 'hidden';
        _main.style.zIndex = _base_index;

        document.body.appendChild(_main);

        _them = document.createElement('ul');
        _them.style.position = 'absolute';
        _them.style.width = _win_width+'px';
        _them.style.textAlign = 'center';
        _them.style.zIndex = _base_index + 2;

        _them.style.marginTop = -lineHeight/2 + 'rem';

        var _arr = [];

        var _i = null;
        for(var i = 0; i < _opts.items.length; i++){
            _i = _opts.items[i];
            if(_witch == i){
                _arr.push('<li style="'+_li_selected_style+'">'+_i+'</li>');
            }else {
                _arr.push('<li style="'+_li_style+'">'+_i+'</li>');
            }
        }

        _them.innerHTML = _arr.join('');
        _main.appendChild(_them);

        // 获取li的高度
        _li_height = _them.children.length > 0 ? _them.children[0].clientHeight : 0;
        _them_top -= (_li_height * _witch);
        _them.style.top = _them_top + 'px';

        for(var i = 0; i < _them.children.length; i++){
            (function(a){
                _bindEvent(_them.children[a], 'click', function () {
                    _pre_witch = _witch;
                    _witch = a;
                    if(_pre_witch != -1){
                        setStyle(_them.children[_pre_witch], _li_style);
                    }
                    setStyle(_them.children[_witch], _li_selected_style);

                    _them.style.top = _middle_top - (_li_height*a) + 'px';
                });
            })(i);
        }

        // 中间选框
        var _middle_div = document.createElement('div');
        _middle_div.style.position = 'absolute';
        _middle_div.style.width = '100%';
        _middle_div.style.height = lineHeight + 'rem';
        _middle_div.style.top = _middle_top + 'px';
        _middle_div.style.marginTop = -lineHeight/2 + 'rem';
        _middle_div.style.borderTop = '1px solid gray';
        _middle_div.style.borderBottom = '1px solid gray';
        _middle_div.style.zIndex = _base_index + 1;
        _main.appendChild(_middle_div);

        // 标题头
        _title_div = document.createElement('div');
        _title_div.style.position = 'absolute';
        _title_div.style.width = '100%';
        _title_div.style.height = '1.0rem';
        _title_div.style.top = _win_height*0.7 + 'px';
        _title_div.style.marginTop = '-1.0rem';
        _title_div.style.borderTop = '1px solid gray';
        _title_div.style.borderBottom = '1px solid gray';
        _title_div.style.backgroundColor = 'gray';
        _title_div.style.textAlign = 'center';
        _title_div.style.zIndex = _base_index + 3;
        _title_div.innerHTML = '<span style="line-height: 1.0rem; font-size: 0.5rem; color: #333333; font-weight: bold;">确定</span>';

        _bindEvent(_title_div, ['click', 'touch'], function () {
            _sure();
        });
        document.body.appendChild(_title_div);

        // 添加触摸事件
        _bindEvent(_main, 'touchstart', function () {
            _dragStart(event);
        });
        _bindEvent(_main, 'touchmove', function () {
            _dragMove(event);
        });
        _bindEvent(_main, 'touchend', function () {
            _dragEnd(event);
        });

        // 渐隐效果
        var _good_div1 = document.createElement('div');
        _good_div1.style.position = 'absolute';
        _good_div1.style.width = '100%';
        _good_div1.style.height = (_main_height - _li_height)/2 + 'px';
        _good_div1.style.top =  -_li_height + 'px';
        _good_div1.style.background =  '-webkit-linear-gradient(bottom, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div1.style.background =  '-o-linear-gradient(bottom, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div1.style.background =  '-moz-linear-gradient(bottom, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div1.style.background =  'linear-gradient(to top, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div1.style.zIndex = _base_index + 3;
        _main.appendChild(_good_div1);

        var _good_div2 = document.createElement('div');
        _good_div2.style.position = 'absolute';
        _good_div2.style.width = '100%';
        _good_div2.style.height = (_main_height - _li_height)/2 + 'px';
        _good_div2.style.top =  (_main_height/2 + _li_height/2) + _li_height + 'px';
        _good_div2.style.background =  '-webkit-linear-gradient(top, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div2.style.background =  '-o-linear-gradient(top, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div2.style.background =  '-moz-linear-gradient(top, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div2.style.background =  'linear-gradient(to bottom, rgba(128,128,128,0), rgba(128,128,128,1))';
        _good_div2.style.zIndex = _base_index + 3;
        _main.appendChild(_good_div2);
    }

    function _over() {
        if(_current_domId){
            if(_item != null){
                document.body.removeChild(_item);
            }
            if(_main != null){
                document.body.removeChild(_main);
            }
            if(_title_div != null){
                document.body.removeChild(_title_div);
            }
            _current_domId = null;
        }
    }

    var _dragFlag = false;
    var startPos = null, isScrolling = 0, endPos = null;
    function _dragStart(event){
        var touch = event.targetTouches[0]; //touches数组对象获得屏幕上所有的touch，取第一个touch
        startPos = {x:touch.pageX,y:touch.pageY,time:+new Date}; //取第一个touch的坐标值
    }
    function _dragMove(event){
        if(event.targetTouches.length > 1 || event.scale && event.scale !== 1) return;
        _dragFlag = true;

        var touch = event.targetTouches[0];
        endPos = {x:touch.pageX - startPos.x,y:touch.pageY - startPos.y};
        isScrolling = Math.abs(endPos.x) < Math.abs(endPos.y) ? 1:0; //isScrolling为1时，表示纵向滑动，0为横向滑动
        if(isScrolling === 0){

        }else if(isScrolling === 1){
            _them.style.top = _checkMinMax(_them_top + endPos.y) + 'px';
        }
        event.preventDefault(); //阻止触摸事件的默认行为，即阻止滚屏
    }
    function _dragEnd(event){
        if(!_dragFlag){
            return;
        }
        _pre_witch = _witch;
        // 矫正位置
        _witch += _checkMinMax2(-Math.round(endPos.y / _li_height), -_opts.items.length+1, _opts.items.length-1);
        _witch = _checkMinMax2(_witch, 0, _opts.items.length-1);
        _them_top += _li_height * Math.round(endPos.y / _li_height);
        _them_top = _checkMinMax(_them_top);
        _them.style.top = _them_top + 'px';

        if(_pre_witch != -1){
            setStyle(_them.children[_pre_witch], _li_style);
        }
        setStyle(_them.children[_witch], _li_selected_style);

        _dragFlag = false;
    }

    //
    function _sure() {
        _setVal(_current_domId, _witch);

        _over();
    }

    function _checkMinMax(val) {
        return Math.max(Math.min(_middle_top, val), _middle_top - _li_height*(_opts.items.length-1));
    }

    function _checkMinMax2(val, min, max) {
        return Math.min(Math.max(min, val), max);
    }

    function _bindEvent(dom, type, fun){
        var arr = null;
        if(typeof type === 'object' && !!type.push){
            arr = type;
        }else {
            arr = [type];
        }
        for (var i = 0; i < arr.length; i++){
            if(dom.attachEvent){
                dom.attachEvent('on'.concat(arr[i]), function (event) {
                    fun(event);
                });
            }else {
                dom.addEventListener(arr[i], function (event) {
                    fun(event);
                });
            }
        }
    }

    function setStyle(el, cssText) {
        el.style = cssText;
        el.setAttribute('style', cssText);
        el.style.cssText = cssText;
    }

    _bindEvent(window, 'resize', function () {
        init();
        if(_current_domId != null){
            var _temp_domId = _current_domId;
            _over();
            _fire(_temp_domId);
        }
    });
})();