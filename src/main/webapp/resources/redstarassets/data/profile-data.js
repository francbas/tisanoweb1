/**
 *  Document   : profile.js
 *  Author     : redstar
 *  Description: profile page related scripts
 *
 **/

var Profile = function () {
    return {
        init: function () {
            Profile.initMiniCharts()
        },
        initMiniCharts: function () {
            App.isIE8() && !Function.prototype.bind && (Function.prototype.bind = function (t) {
                if ("function" != typeof this) throw new TypeError("Function.prototype.bind - what is trying to be bound is not callable");
                var i = Array.prototype.slice.call(arguments, 1),
                    r = this,
                    n = function () {
                    },
                    o = function () {
                        return r.apply(this instanceof n && t ? this : t, i.concat(Array.prototype.slice.call(arguments)))
                    };
                return n.prototype = this.prototype, o.prototype = new n, o
            }), $("#sparkline_bar").sparkline([8, 9, 10, 11, 10, 10, 12, 10, 10, 11, 9, 12, 11], {
                type: "bar",
                width: "100",
                barWidth: 6,
                height: "45",
                barColor: "#F36A5B",
                negBarColor: "#e02222"
            }), $("#sparkline_bar2").sparkline([9, 11, 12, 13, 12, 13, 10, 14, 13, 11, 11, 12, 11], {
                type: "bar",
                width: "100",
                barWidth: 6,
                height: "45",
                barColor: "#5C9BD1",
                negBarColor: "#e02222"
            })
        }
    }
}();
jQuery(document).ready(function () {
    'use strict';
    Profile.init()
});