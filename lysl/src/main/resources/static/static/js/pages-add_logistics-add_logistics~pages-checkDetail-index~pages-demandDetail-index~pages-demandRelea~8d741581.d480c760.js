(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-add_logistics-add_logistics~pages-checkDetail-index~pages-demandDetail-index~pages-demandRelea~8d741581"], {
    "3b8d": function (t, e, n) {
        "use strict";
        n.r(e), n.d(e, "default", function () {
            return a
        });
        var r = n("795b"), o = n.n(r);

        function i(t, e, n, r, i, a, u) {
            try {
                var c = t[a](u), s = c.value
            } catch (h) {
                return void n(h)
            }
            c.done ? e(s) : o.a.resolve(s).then(r, i)
        }

        function a(t) {
            return function () {
                var e = this, n = arguments;
                return new o.a(function (r, o) {
                    var a = t.apply(e, n);

                    function u(t) {
                        i(a, r, o, u, c, "next", t)
                    }

                    function c(t) {
                        i(a, r, o, u, c, "throw", t)
                    }

                    u(void 0)
                })
            }
        }
    }, "68f7": function (t, e, n) {
        "use strict";
        (function (t) {
            var r = n("288e");
            Object.defineProperty(e, "__esModule", {value: !0}), e.toPath = a, e.back = c, e.getRandomAlphaNum = h, e.timestampToTime = l, e.call = f, e.fetch = p, e.test = g, e.debounce = v, e.fetchGet = w, e.fetchPost = b, e.fetchPut = E, e.fetchDelete = R, n("96cf");
            var o = r(n("795b"));
            n("6b54");
            var i = r(n("3b8d"));

            function a(t) {
                return u.apply(this, arguments)
            }

            function u() {
                return u = (0, i.default)(regeneratorRuntime.mark(function t(e) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                uni.navigateTo({url: e});
                            case 1:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), u.apply(this, arguments)
            }

            function c(t) {
                return s.apply(this, arguments)
            }

            function s() {
                return s = (0, i.default)(regeneratorRuntime.mark(function t(e) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                uni.navigateBack();
                            case 1:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), s.apply(this, arguments)
            }

            function h(t) {
                for (var e = ""; e.length < t; e += Math.random().toString(36).substr(2)) ;
                return e.substr(0, t)
            }

            function l(t) {
                var e = new Date(t), n = e.getFullYear() + "-",
                    r = (e.getMonth() + 1 < 10 ? "0" + (e.getMonth() + 1) : e.getMonth() + 1) + "-",
                    o = e.getDate() + " ", i = e.getHours() + ":", a = e.getMinutes() + ":", u = e.getSeconds();
                return n + r + o + i + a + u
            }

            function f(t) {
                uni.makePhoneCall({phoneNumber: t})
            }

            function p(t) {
                return d.apply(this, arguments)
            }

            function d() {
                return d = (0, i.default)(regeneratorRuntime.mark(function t(e) {
                    var n, r, o, i, a, u = arguments;
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                n = u.length > 1 && void 0 !== u[1] ? u[1] : {}, r = u.length > 2 && void 0 !== u[2] ? u[2] : "GET", o = u.length > 3 && void 0 !== u[3] ? u[3] : "JSON", i = u.length > 4 ? u[4] : void 0, uni.showLoading({title: "加载中"}), a = {}, "POST" === r && (a["content-type"] = "application/json"), a["X-Requested-With"] = "XMLHttpRequest", e = "http://47.113.115.120:8080/" + e, uni.request({
                                    url: e,
                                    header: a,
                                    method: r,
                                    data: n,
                                    dataType: o,
                                    success: function (t) {
                                        uni.hideLoading(), t.data.success ? i(t.data.resultObj) : t.data.success || uni.showToast({
                                            icon: "none",
                                            title: t.data.resultDesc,
                                            duration: 3e3
                                        })
                                    },
                                    fail: function (t) {
                                        uni.hideLoading(), uni.showToast({icon: "none", title: "系统错误", duration: 3e3})
                                    },
                                    complete: function (t) {
                                    }
                                });
                            case 10:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), d.apply(this, arguments)
            }

            function g() {
                return y.apply(this, arguments)
            }

            function y() {
                return y = (0, i.default)(regeneratorRuntime.mark(function e() {
                    return regeneratorRuntime.wrap(function (e) {
                        while (1) switch (e.prev = e.next) {
                            case 0:
                                t.log(uni);
                            case 1:
                            case"end":
                                return e.stop()
                        }
                    }, e, this)
                })), y.apply(this, arguments)
            }

            function v(t, e, n) {
                var r, o, i, a, u, c = function c() {
                    var s = new Date - a;
                    s < e && s >= 0 ? r = setTimeout(c, e - s) : (r = null, n || (u = t.apply(i, o), r || (i = o = null)))
                };
                return function () {
                    i = this, o = arguments, a = new Date;
                    var s = n && !r;
                    return r || (r = setTimeout(c, e)), s && (u = t.apply(i, o), i = o = null), u
                }
            }

            function m(t) {
                var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
                    n = arguments.length > 2 ? arguments[2] : void 0,
                    r = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : "JSON";
                return new o.default(function (o, i) {
                    p(t, e, n, r, o)
                })
            }

            function w(t, e) {
                return x.apply(this, arguments)
            }

            function x() {
                return x = (0, i.default)(regeneratorRuntime.mark(function t(e, n) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                return t.next = 2, m(e, n, "GET");
                            case 2:
                                return t.abrupt("return", t.sent);
                            case 3:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), x.apply(this, arguments)
            }

            function b(t, e) {
                return L.apply(this, arguments)
            }

            function L() {
                return L = (0, i.default)(regeneratorRuntime.mark(function t(e, n) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                return t.next = 2, m(e, n, "POST");
                            case 2:
                                return t.abrupt("return", t.sent);
                            case 3:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), L.apply(this, arguments)
            }

            function E(t, e) {
                return k.apply(this, arguments)
            }

            function k() {
                return k = (0, i.default)(regeneratorRuntime.mark(function t(e, n) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                return t.next = 2, m(e, n, "PUT");
                            case 2:
                                return t.abrupt("return", t.sent);
                            case 3:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), k.apply(this, arguments)
            }

            function R(t, e) {
                return T.apply(this, arguments)
            }

            function T() {
                return T = (0, i.default)(regeneratorRuntime.mark(function t(e, n) {
                    return regeneratorRuntime.wrap(function (t) {
                        while (1) switch (t.prev = t.next) {
                            case 0:
                                return t.next = 2, m(e, n, "DELETE");
                            case 2:
                                return t.abrupt("return", t.sent);
                            case 3:
                            case"end":
                                return t.stop()
                        }
                    }, t, this)
                })), T.apply(this, arguments)
            }
        }).call(this, n("5a52")["default"])
    }, "96cf": function (t, e) {
        !function (e) {
            "use strict";
            var n, r = Object.prototype, o = r.hasOwnProperty, i = "function" === typeof Symbol ? Symbol : {},
                a = i.iterator || "@@iterator", u = i.asyncIterator || "@@asyncIterator",
                c = i.toStringTag || "@@toStringTag", s = "object" === typeof t, h = e.regeneratorRuntime;
            if (h) s && (t.exports = h); else {
                h = e.regeneratorRuntime = s ? t.exports : {}, h.wrap = x;
                var l = "suspendedStart", f = "suspendedYield", p = "executing", d = "completed", g = {}, y = {};
                y[a] = function () {
                    return this
                };
                var v = Object.getPrototypeOf, m = v && v(v(S([])));
                m && m !== r && o.call(m, a) && (y = m);
                var w = k.prototype = L.prototype = Object.create(y);
                E.prototype = w.constructor = k, k.constructor = E, k[c] = E.displayName = "GeneratorFunction", h.isGeneratorFunction = function (t) {
                    var e = "function" === typeof t && t.constructor;
                    return !!e && (e === E || "GeneratorFunction" === (e.displayName || e.name))
                }, h.mark = function (t) {
                    return Object.setPrototypeOf ? Object.setPrototypeOf(t, k) : (t.__proto__ = k, c in t || (t[c] = "GeneratorFunction")), t.prototype = Object.create(w), t
                }, h.awrap = function (t) {
                    return {__await: t}
                }, R(T.prototype), T.prototype[u] = function () {
                    return this
                }, h.AsyncIterator = T, h.async = function (t, e, n, r) {
                    var o = new T(x(t, e, n, r));
                    return h.isGeneratorFunction(e) ? o : o.next().then(function (t) {
                        return t.done ? t.value : o.next()
                    })
                }, R(w), w[c] = "Generator", w[a] = function () {
                    return this
                }, w.toString = function () {
                    return "[object Generator]"
                }, h.keys = function (t) {
                    var e = [];
                    for (var n in t) e.push(n);
                    return e.reverse(), function n() {
                        while (e.length) {
                            var r = e.pop();
                            if (r in t) return n.value = r, n.done = !1, n
                        }
                        return n.done = !0, n
                    }
                }, h.values = S, N.prototype = {
                    constructor: N, reset: function (t) {
                        if (this.prev = 0, this.next = 0, this.sent = this._sent = n, this.done = !1, this.delegate = null, this.method = "next", this.arg = n, this.tryEntries.forEach(P), !t) for (var e in this) "t" === e.charAt(0) && o.call(this, e) && !isNaN(+e.slice(1)) && (this[e] = n)
                    }, stop: function () {
                        this.done = !0;
                        var t = this.tryEntries[0], e = t.completion;
                        if ("throw" === e.type) throw e.arg;
                        return this.rval
                    }, dispatchException: function (t) {
                        if (this.done) throw t;
                        var e = this;

                        function r(r, o) {
                            return u.type = "throw", u.arg = t, e.next = r, o && (e.method = "next", e.arg = n), !!o
                        }

                        for (var i = this.tryEntries.length - 1; i >= 0; --i) {
                            var a = this.tryEntries[i], u = a.completion;
                            if ("root" === a.tryLoc) return r("end");
                            if (a.tryLoc <= this.prev) {
                                var c = o.call(a, "catchLoc"), s = o.call(a, "finallyLoc");
                                if (c && s) {
                                    if (this.prev < a.catchLoc) return r(a.catchLoc, !0);
                                    if (this.prev < a.finallyLoc) return r(a.finallyLoc)
                                } else if (c) {
                                    if (this.prev < a.catchLoc) return r(a.catchLoc, !0)
                                } else {
                                    if (!s) throw new Error("try statement without catch or finally");
                                    if (this.prev < a.finallyLoc) return r(a.finallyLoc)
                                }
                            }
                        }
                    }, abrupt: function (t, e) {
                        for (var n = this.tryEntries.length - 1; n >= 0; --n) {
                            var r = this.tryEntries[n];
                            if (r.tryLoc <= this.prev && o.call(r, "finallyLoc") && this.prev < r.finallyLoc) {
                                var i = r;
                                break
                            }
                        }
                        i && ("break" === t || "continue" === t) && i.tryLoc <= e && e <= i.finallyLoc && (i = null);
                        var a = i ? i.completion : {};
                        return a.type = t, a.arg = e, i ? (this.method = "next", this.next = i.finallyLoc, g) : this.complete(a)
                    }, complete: function (t, e) {
                        if ("throw" === t.type) throw t.arg;
                        return "break" === t.type || "continue" === t.type ? this.next = t.arg : "return" === t.type ? (this.rval = this.arg = t.arg, this.method = "return", this.next = "end") : "normal" === t.type && e && (this.next = e), g
                    }, finish: function (t) {
                        for (var e = this.tryEntries.length - 1; e >= 0; --e) {
                            var n = this.tryEntries[e];
                            if (n.finallyLoc === t) return this.complete(n.completion, n.afterLoc), P(n), g
                        }
                    }, catch: function (t) {
                        for (var e = this.tryEntries.length - 1; e >= 0; --e) {
                            var n = this.tryEntries[e];
                            if (n.tryLoc === t) {
                                var r = n.completion;
                                if ("throw" === r.type) {
                                    var o = r.arg;
                                    P(n)
                                }
                                return o
                            }
                        }
                        throw new Error("illegal catch attempt")
                    }, delegateYield: function (t, e, r) {
                        return this.delegate = {
                            iterator: S(t),
                            resultName: e,
                            nextLoc: r
                        }, "next" === this.method && (this.arg = n), g
                    }
                }
            }

            function x(t, e, n, r) {
                var o = e && e.prototype instanceof L ? e : L, i = Object.create(o.prototype), a = new N(r || []);
                return i._invoke = _(t, n, a), i
            }

            function b(t, e, n) {
                try {
                    return {type: "normal", arg: t.call(e, n)}
                } catch (r) {
                    return {type: "throw", arg: r}
                }
            }

            function L() {
            }

            function E() {
            }

            function k() {
            }

            function R(t) {
                ["next", "throw", "return"].forEach(function (e) {
                    t[e] = function (t) {
                        return this._invoke(e, t)
                    }
                })
            }

            function T(t) {
                function e(n, r, i, a) {
                    var u = b(t[n], t, r);
                    if ("throw" !== u.type) {
                        var c = u.arg, s = c.value;
                        return s && "object" === typeof s && o.call(s, "__await") ? Promise.resolve(s.__await).then(function (t) {
                            e("next", t, i, a)
                        }, function (t) {
                            e("throw", t, i, a)
                        }) : Promise.resolve(s).then(function (t) {
                            c.value = t, i(c)
                        }, function (t) {
                            return e("throw", t, i, a)
                        })
                    }
                    a(u.arg)
                }

                var n;

                function r(t, r) {
                    function o() {
                        return new Promise(function (n, o) {
                            e(t, r, n, o)
                        })
                    }

                    return n = n ? n.then(o, o) : o()
                }

                this._invoke = r
            }

            function _(t, e, n) {
                var r = l;
                return function (o, i) {
                    if (r === p) throw new Error("Generator is already running");
                    if (r === d) {
                        if ("throw" === o) throw i;
                        return G()
                    }
                    n.method = o, n.arg = i;
                    while (1) {
                        var a = n.delegate;
                        if (a) {
                            var u = O(a, n);
                            if (u) {
                                if (u === g) continue;
                                return u
                            }
                        }
                        if ("next" === n.method) n.sent = n._sent = n.arg; else if ("throw" === n.method) {
                            if (r === l) throw r = d, n.arg;
                            n.dispatchException(n.arg)
                        } else "return" === n.method && n.abrupt("return", n.arg);
                        r = p;
                        var c = b(t, e, n);
                        if ("normal" === c.type) {
                            if (r = n.done ? d : f, c.arg === g) continue;
                            return {value: c.arg, done: n.done}
                        }
                        "throw" === c.type && (r = d, n.method = "throw", n.arg = c.arg)
                    }
                }
            }

            function O(t, e) {
                var r = t.iterator[e.method];
                if (r === n) {
                    if (e.delegate = null, "throw" === e.method) {
                        if (t.iterator.return && (e.method = "return", e.arg = n, O(t, e), "throw" === e.method)) return g;
                        e.method = "throw", e.arg = new TypeError("The iterator does not provide a 'throw' method")
                    }
                    return g
                }
                var o = b(r, t.iterator, e.arg);
                if ("throw" === o.type) return e.method = "throw", e.arg = o.arg, e.delegate = null, g;
                var i = o.arg;
                return i ? i.done ? (e[t.resultName] = i.value, e.next = t.nextLoc, "return" !== e.method && (e.method = "next", e.arg = n), e.delegate = null, g) : i : (e.method = "throw", e.arg = new TypeError("iterator result is not an object"), e.delegate = null, g)
            }

            function j(t) {
                var e = {tryLoc: t[0]};
                1 in t && (e.catchLoc = t[1]), 2 in t && (e.finallyLoc = t[2], e.afterLoc = t[3]), this.tryEntries.push(e)
            }

            function P(t) {
                var e = t.completion || {};
                e.type = "normal", delete e.arg, t.completion = e
            }

            function N(t) {
                this.tryEntries = [{tryLoc: "root"}], t.forEach(j, this), this.reset(!0)
            }

            function S(t) {
                if (t) {
                    var e = t[a];
                    if (e) return e.call(t);
                    if ("function" === typeof t.next) return t;
                    if (!isNaN(t.length)) {
                        var r = -1, i = function e() {
                            while (++r < t.length) if (o.call(t, r)) return e.value = t[r], e.done = !1, e;
                            return e.value = n, e.done = !0, e
                        };
                        return i.next = i
                    }
                }
                return {next: G}
            }

            function G() {
                return {value: n, done: !0}
            }
        }(function () {
            return this || "object" === typeof self && self
        }() || Function("return this")())
    }
}]);