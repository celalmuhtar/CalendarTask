/*
  angular-momentjs - v0.2.2 
  2015-07-10
*/

!function(a, b) {
	"use strict";b.module("angular-moment", [ "gdi2290.moment" ]), b.module("angular-momentjs", [ "gdi2290.moment" ]), b.module("ngMoment", [ "gdi2290.moment" ]), b.module("ngMomentjs", [ "gdi2290.moment" ]), b.module("ngMomentJS", [ "gdi2290.moment" ]), b.module("gdi2290.amTimeAgo", []), b.module("gdi2290.amDateFormat", []), b.module("gdi2290.moment-service", []), b.module("gdi2290.moment", [ "gdi2290.moment-service", "gdi2290.amDateFormat", "gdi2290.amTimeAgo" ]), b.module("gdi2290.amDateFormat").filter("amDateFormat", [ "$moment", function(a) {
		return function(b, c) {
			return "undefined" == typeof b || null === b ? "" : (!isNaN(parseFloat(b)) && isFinite(b) && (b = new Date(parseInt(b, 10))), a.then ? void a().then(function(a) {
				return a(b).format(c)
			}) : a(b).format(c))
		}
	} ]), b.module("gdi2290.amTimeAgo").directive("amTimeAgo", [ "$moment", "$timeout", function(a, c) {
		function d(a) {
			return "undefined" == typeof a || null === a || "" === a
		}
		return function(e, f, g) {
			function h() {
				m && (c.cancel(m), m = null)
			}
			function i(b) {
				f.text(b.fromNow());var d;
				a.then ? a().then(function(a) {
					d = a().diff(b, "minute")
				}) : d = a().diff(b, "minute");var e = 3600;
				1 > d ? e = 1 : 60 > d ? e = 30 : 180 > d && (e = 300), m = c(function() {
					i(b)
				}, 1e3 * e, !1)
			}
			function j() {
				h(), a().then ? a.then(function(a) {
					i(a(k, l))
				}) : i(a(k, l))
			}
			var k,
				l,
				m = null;
			e.$watch(g.amTimeAgo, function(a) {
				return d(a) ? (h(), void (k && (f.text(""), k = null))) : (b.isNumber(a) && (a = new Date(a)), k = a, void j())
			}), g.$observe("amFormat", function(a) {
				l = a, k && j()
			}), e.$on("$destroy", function() {
				h()
			})
		}
	} ]), b.module("gdi2290.moment-service").provider("$moment", function() {
		function a(a) {
			if (document) {
				var b = document.createElement("script");
				b.type = "text/javascript", b.async = !0, b.src = d, b.onreadystatechange = function() {
					"complete" === this.readyState && a()
				}, b.onload = a;
				var c = document.getElementsByTagName("head")[0];
				c.appendChild(b)
			}
		}
		var b,
			c = !1,
			d = "//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js",
			e = {};
		this.asyncLoading = function(a) {
			return c = a || c, this
		}, this.scriptUrl = function(a) {
			return d = a || d, this
		}, this.locale = function(a, c) {
			return b = a || b, e = c || e, this
		}, this.$get = [ "$timeout", "$q", "$window", function(d, f, g) {
			var h = f.defer(),
				i = g.moment;
			if (c) {
				var j = function() {
					d(function() {
						b && (g.moment.lang ? g.moment.lang(b, e) : g.moment.locale(b, e)), h.resolve(g.moment)
					})
				};
				a(j)
			} else b && (g.moment.lang ? g.moment.lang(b, e) : g.moment.locale(b, e));
			return c ? h.promise : i
		} ]
	})
}(this, this.angular, void 0);
//# sourceMappingURL=angular-momentjs.min.js.map