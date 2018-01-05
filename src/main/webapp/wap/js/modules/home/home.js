// 广播内容
var _broadcast_index = 0;
var _broadcast_index_cur = 0;
function an() {
    $('#bc_show').fadeOut('slow', function () {
        $('#bc_show').text(_broadcastsJson[_broadcast_index].title);
        $('#bc_show').fadeIn('slow');
        _broadcast_index_cur = _broadcast_index;
        _broadcast_index++;
        if(_broadcast_index >= _broadcastsJson.length){
            _broadcast_index = 0;
            $.ajax({
                url: ctx + '/index/broadcastList',
                type: 'GET',
                data:{},
                success: function(data){
                    _broadcastsJson = data;
                }
            });
        }
    });
    setTimeout(function () {
        an();
    }, 7000);
}

// Highcharts全局配置
Highcharts.setOptions({
    global: {
        useUTC: true,
        timezoneOffset: -8*60
    },
    lang: {
        contextButtonTitle: "Chart context menu",
        decimalPoint: ".",
        downloadJPEG: "",
        downloadPDF: "",
        downloadPNG: "",
        downloadSVG: "",
        invalidDate: "",
        loading: "Loading...",
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        numericSymbols: [ "k" , "M" , "G" , "T" , "P" , "E"],
        printChart: "Print chart",
        rangeSelectorFrom: "From",
        rangeSelectorTo: "To",
        rangeSelectorZoom: "Zoom",
        resetZoom: "Reset zoom",
        resetZoomTitle: "Reset zoom level 1:1",
        shortMonths:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        shortWeekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
        thousandsSep: "",
        weekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"]
    }
});

function showBroadCast() {
    if(_broadcast_index_cur >=0 && _broadcast_index_cur < _broadcastsJson.length){
        window.location.href = ctxWap+'/index/broadcastDetail/'+_broadcastsJson[_broadcast_index_cur].id;
    }
}