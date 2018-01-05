(function (global, factory) {
    /* CommonJS */
    if (typeof require === 'function' && typeof module === 'object' && module && typeof exports === 'object' && exports)
        factory(require('./iSlider'));
    /* AMD */
    else if (typeof define === 'function' && define['amd'])
        define(['./iSlider'], function (iSlider) {
            factory(iSlider);
        });
    /* Global */
    else
        factory(global['iSlider']);

})(window ? window : this, function (iSlider) {
    'use strict';
    iSlider && iSlider.extend(iSlider._animateFuncs, {
        flow1: (function () {
            function flow(dom, axis, scale, i, offset, direct) {
                var absoluteOffset = Math.abs(offset);
                var rotateDirect = (axis === 'X') ? 'Y' : 'X';
                var directAmend = (axis === 'X') ? 1 : -1;
                var offsetRatio = Math.abs(offset / scale);
                var outer = dom.parentElement;

                iSlider.setStyle(outer, 'perspective', scale * 2);

                if (i === 1) {
                    dom.style.zIndex = scale - absoluteOffset;
                }
                else {
                    dom.style.zIndex = (offset > 0) ? (1 - i) * absoluteOffset : (i - 1) * absoluteOffset;
                }

                iSlider.setStyle(dom, 'transform', 'scale(0.6, 0.6) translateZ(' + (offsetRatio * 450 - 450) * Math.abs(i - 1) + 'px)'
                    + 'translate' + axis + '(' + (offset + scale * (i - 1)) + 'px)'
                    + 'rotate' + rotateDirect + '(' + 0 + 'deg)');
            }

            flow.effect = iSlider.styleProp('transform');
            return flow;
        })()
    });
});