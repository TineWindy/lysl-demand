(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-news-index"],{1321:function(e,t,n){"use strict";n.r(t);var r=n("8299"),i=n("4652");for(var a in i)"default"!==a&&function(e){n.d(t,e,function(){return i[e]})}(a);n("7f87");var s,o=n("f0c5"),c=Object(o["a"])(i["default"],r["b"],r["c"],!1,null,"68e07d63",null,!1,r["a"],s);t["default"]=c.exports},"1e49":function(e,t,n){t=e.exports=n("2350")(!1),t.push([e.i,"uni-page-body[data-v-68e07d63]{height:100%}.body[data-v-68e07d63]{height:100%;display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column}.body-content[data-v-68e07d63]{-webkit-box-flex:1;-webkit-flex:1;flex:1;display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column;-webkit-box-align:center;-webkit-align-items:center;align-items:center;-webkit-box-pack:center;-webkit-justify-content:center;justify-content:center;background-color:#fff}",""])},4652:function(e,t,n){"use strict";n.r(t);var r=n("6391"),i=n.n(r);for(var a in r)"default"!==a&&function(e){n.d(t,e,function(){return r[e]})}(a);t["default"]=i.a},6391:function(e,t,n){"use strict";(function(e){var r=n("e54b"),i=n("288e");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a=i(n("75fc"));n("96cf");var s=i(n("3b8d"));n("386d");var o=r(n("d255")),c=n("02d9"),u={data:function(){return{showList:[],scope:{origin:{list:[],hasMore:!1,query:{pageNo:0,pageSize:10}},search:{list:[],hasMore:!1,query:{pageNo:0,pageSize:10,title:""}}}}},onLoad:function(){this.getList()},methods:{timestampToTime:c.timestampToTime,getScope:function(){return this.scope.search.query.title?this.scope.search:this.scope.origin},getList:function(){var e=(0,s.default)(regeneratorRuntime.mark(function e(){var t,n,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return t=this.getScope(),n=this.scope.search.query.title?o.getNewsSearch:o.getNews,e.next=4,n(t.query);case 4:r=e.sent,t.list=[].concat((0,a.default)(t.list),(0,a.default)(r)),this.showList=t.list,t.hasMore=10===r.length;case 8:case"end":return e.stop()}},e,this)}));function t(){return e.apply(this,arguments)}return t}(),scrolltolower:function(){var e=this.getScope();e.hasMore&&(e.query.pageNo+=1,this.getList())},onSearch:(0,c.debounce)((0,s.default)(regeneratorRuntime.mark(function t(){return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:if(e.log(this),""!==this.scope.search.query.title){t.next=7;break}return this.scope.search={list:[],hasMore:!1,query:{pageNo:0,pageSize:10,title:""}},t.next=5,this.getList();case 5:t.next=10;break;case 7:return this.scope.search={list:[],hasMore:!1,query:{pageNo:0,pageSize:10,title:this.scope.search.query.title}},t.next=10,this.getList();case 10:case"end":return t.stop()}},t,this)})),500,!0),goToDetail:function(e){var t=e.url;(0,c.toPath)("./newsDetail?pageUrl="+t)}}};t.default=u}).call(this,n("5a52")["default"])},"7f87":function(e,t,n){"use strict";var r=n("a84d"),i=n.n(r);i.a},8299:function(e,t,n){"use strict";var r,i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("v-uni-view",{staticClass:"body"},[n("v-uni-view",{staticClass:"cu-bar search bg-white"},[n("v-uni-view",{staticClass:"search-form round"},[n("v-uni-text",{staticClass:"cuIcon-search"}),n("v-uni-input",{attrs:{type:"text",placeholder:"请输入新闻名称"},on:{change:function(t){arguments[0]=t=e.$handleEvent(t),e.onSearch.apply(void 0,arguments)}},model:{value:e.scope.search.query.title,callback:function(t){e.$set(e.scope.search.query,"title",t)},expression:"scope.search.query.title"}})],1),n("v-uni-view",{staticClass:"action"},[n("v-uni-button",{staticClass:"cu-btn bg-blue shadow-blur round",on:{click:function(t){arguments[0]=t=e.$handleEvent(t),e.onSearch.apply(void 0,arguments)}}},[e._v("搜索")])],1)],1),n("v-uni-scroll-view",{staticClass:"body-content",attrs:{"scroll-y":!0,"lower-threshold":10},on:{scrolltolower:function(t){arguments[0]=t=e.$handleEvent(t),e.scrolltolower.apply(void 0,arguments)}}},[n("v-uni-view",{staticClass:"cu-list menu margin-top"},e._l(e.showList,function(t){return n("v-uni-view",{key:t.id,staticClass:"cu-item padding text-left",attrs:{target:"_self"},on:{click:function(n){arguments[0]=n=e.$handleEvent(n),e.goToDetail(t)}}},[n("v-uni-view",{staticClass:"content"},[n("p",{staticClass:"text-bold"},[e._v(e._s(t.title))]),n("p",{staticClass:"text-gray text-sm"},[e._v(e._s(e.timestampToTime(t.publishDatetime))+" 来源："+e._s(t.origin))])])],1)}),1),!e.getScope.hasMore&&e.showList.length<=0?n("v-uni-view",{staticStyle:{"text-align":"center",color:"#c3c3c3"}},[e._v("暂无信息。。。")]):e.getScope.hasMore?e._e():n("v-uni-view",{staticStyle:{"text-align":"center",color:"#c3c3c3"}},[e._v("没有更多信息了。。。")])],1)],1)},a=[];n.d(t,"b",function(){return i}),n.d(t,"c",function(){return a}),n.d(t,"a",function(){return r})},a84d:function(e,t,n){var r=n("1e49");"string"===typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);var i=n("4f06").default;i("4232f72c",r,!0,{sourceMap:!1,shadowMode:!1})},d255:function(e,t,n){"use strict";var r=n("288e");Object.defineProperty(t,"__esModule",{value:!0}),t.searchInstitutionNames=s,t.createDonation=c,t.getTransportationList=l,t.getNews=f,t.getNewsSearch=d,n("96cf");var i=r(n("3b8d")),a=n("02d9");function s(e){return o.apply(this,arguments)}function o(){return o=(0,i.default)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,a.fetchGet)("inst/queryByPartOfName",{name:t});case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}},e,this)})),o.apply(this,arguments)}function c(e){return u.apply(this,arguments)}function u(){return u=(0,i.default)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,a.fetchPost)("/donationOrder/addDonationOrder",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}},e,this)})),u.apply(this,arguments)}function l(e){return p.apply(this,arguments)}function p(){return p=(0,i.default)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,a.fetchGet)("/transportation/getTransportationListApproved",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}},e,this)})),p.apply(this,arguments)}function f(e){return h.apply(this,arguments)}function h(){return h=(0,i.default)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,a.fetchGet)("/news/getNewsList",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}},e,this)})),h.apply(this,arguments)}function d(e){return w.apply(this,arguments)}function w(){return w=(0,i.default)(regeneratorRuntime.mark(function e(t){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,a.fetchGet)("/news/getNewsListByPartionOfTitle",t);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}},e,this)})),w.apply(this,arguments)}}}]);