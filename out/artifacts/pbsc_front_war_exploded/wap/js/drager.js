/**
 *
 */
(function (window) {
    var _all_opt = {};
    window.drag = function (domId, opt) {
        _all_opt[domId] = opt || {};
        if(typeof _all_opt[domId].drayX === 'undefined'){
            _all_opt[domId].drayX = true;
        }
        if(typeof _all_opt[domId].drayY === 'undefined'){
            _all_opt[domId].drayY = true;
        }

        var it = document.getElementById(domId);

        // 添加触摸事件
        if(it.attachEvent){
            it.attachEvent('ontouchstart', function (event) {
                event = event || window.event;
                _dragStart(event, it);
            });
            it.attachEvent('ontouchmove', function (event) {
                event = event || window.event;
                _dragMove(event, it);
                event.preventDefault();
            });
            it.attachEvent('ontouchend', function (event) {
                event = event || window.event;
                _dragEnd(event, it);
            });
        }else {
            it.addEventListener('touchstart', function (event) {
                _dragStart(event, it);
            });
            it.addEventListener('touchmove', function (event) {
                _dragMove(event, it);
                event.preventDefault();
            });
            it.addEventListener('touchend', function (event) {
                _dragEnd(event, it);
            });
        }
    };

    var _dragFlag = false;
    var startPos = null, isScrolling = 0, endPos = null, offsetTop = null, offsetLeft = null;
    function _dragStart(event, it) {
        var touch = event.targetTouches[0]; //touches数组对象获得屏幕上所有的touch，取第一个touch
        startPos = {x:touch.pageX,y:touch.pageY,time:+new Date}; //取第一个touch的坐标值

        offsetTop = it.offsetTop;
        offsetLeft = it.offsetLeft;
    }

    function _dragMove(event, it) {
        if(event.targetTouches.length > 1 || event.scale && event.scale !== 1) return;
        _dragFlag = true;
        var touch = event.targetTouches[0];

        endPos = {x:touch.pageX - startPos.x,y:touch.pageY - startPos.y};

        if(_all_opt[it.id].drayY){
            it.style.marginTop = '0px';
            it.style.top = offsetTop + endPos.y + 'px';
        }
        if(_all_opt[it.id].drayX){
            it.style.marginLeft = '0px';
            it.style.left = offsetLeft + endPos.x + 'px';
        }
    }

    function _dragEnd(event, it) {
        if(!_dragFlag){
            return;
        }
    }

})(window);
