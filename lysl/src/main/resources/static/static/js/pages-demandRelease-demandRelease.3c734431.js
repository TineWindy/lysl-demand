(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-demandRelease-demandRelease"],{"38dd":function(i,t,e){t=i.exports=e("2350")(!1),t.push([i.i,".body[data-v-5337300f]{background:#fff;padding:12px}.body .title[data-v-5337300f]{height:20px;text-align:center;font-size:16px;font-family:Alibaba-PuHuiTi-M,Alibaba-PuHuiTi;font-weight:400;margin-top:30px;margin-bottom:15px;color:#f24e4a;line-height:20px}.body .label[data-v-5337300f]{height:20px;font-size:16px;margin-top:15px;margin-bottom:8px;font-family:Alibaba-PuHuiTi-M,Alibaba-PuHuiTi;font-weight:400;color:#2c2d2e;line-height:20px}.body .cu-btn[data-v-5337300f]{margin-left:10px;margin-right:10px}.body .bg-white[data-v-5337300f]{border:1px solid #e0e0e0}.body .remarks[data-v-5337300f]{height:17px;font-size:14px;font-family:Alibaba-PuHuiTi-R,Alibaba-PuHuiTi;font-weight:400;margin-bottom:8px;color:#696d71;line-height:17px}.body .wz[data-v-5337300f]{position:relative;padding-bottom:10px;padding-top:10px;border-bottom:1px solid #ccc}.body .wz .my_item[data-v-5337300f]{width:85%;display:inline-block;margin-top:5px;margin-bottom:5px}.body .wz .del[data-v-5337300f]{width:15%;display:inline-block;text-align:center;position:relative;top:-55px}.body .wz .del uni-image[data-v-5337300f]{width:26px;height:26px}.body .border_bottom[data-v-5337300f]{border-bottom:none}.body .add[data-v-5337300f]{position:relative;border:1px dashed #e0e0e0;color:#ccc}.body .add uni-image[data-v-5337300f]{width:14px;height:14px;border:1px dashed #ccc;position:relative;left:-10px;top:-1px}.body .add[data-v-5337300f]:after{border:0}.body .my_item[data-v-5337300f]{min-height:32px;line-height:32px;position:relative;margin-top:10px;margin-bottom:10px}.body .my_item uni-label[data-v-5337300f]{position:absolute;top:0;font-size:14px;width:70px;color:#696d71;display:inline-block}.body .my_item .input_icon[data-v-5337300f]{width:22px;height:22px;position:absolute}.body .my_item .my_input[data-v-5337300f]{position:absolute;right:0;width:calc(100% - 70px);padding:5px;height:32px;border:1px solid #ccc;border-radius:5px;display:inline-block}.body .my_item .my_input uni-input[data-v-5337300f]{padding-left:27px}.body .my_item .my_textarea[data-v-5337300f]{width:100%;padding:5px;border:1px solid #ccc;display:inline-block}.body .my_item .my_input_l[data-v-5337300f]{width:100%;padding:5px;height:32px;border:1px solid #ccc;display:inline-block}.body .commit[data-v-5337300f]{margin-top:45px}.label.reuired[data-v-5337300f]{margin-left:15px}",""])},"4dc9":function(i,t,e){e("5737"),i.exports=e("584a").Number.isNaN},"565d":function(i,t,e){"use strict";e.r(t);var n=e("b9d3"),a=e("caab");for(var s in a)"default"!==s&&function(i){e.d(t,i,function(){return a[i]})}(s);e("7a5c");var o=e("f0c5"),l=Object(o["a"])(a["default"],n["a"],n["b"],!1,null,"5337300f",null);t["default"]=l.exports},5737:function(i,t,e){var n=e("63b6");n(n.S,"Number",{isNaN:function(i){return i!=i}})},"7a5c":function(i,t,e){"use strict";var n=e("e758"),a=e.n(n);a.a},a5b4:function(i,t,e){"use strict";var n=e("288e");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a=n(e("e814")),s=n(e("bfb3"));e("6b54");var o=e("68f7"),l=n(e("3bde")),c=n(e("2405")),u=n(e("e3a0")),r={components:{fileUpload:l.default,materialSelection:c.default,wPicker:u.default},data:function(){return{picker:null,pickerText:"",description:"",disabled:!1,institution:{type:"HOSPITAL",name:"",province:"",city:"",district:"",address:"",auth:[]},donee:{name:"",phone:"",wxNumber:""},materialList:[{name:"",value:""}],region:["","",""]}},onShow:function(){var i=this;(0,o.fetch)("region/getProvinceList",{},"GET","JSON",function(t){i.region[0]=t,(0,o.fetch)("/region/getCityListByProvinceId",{provinceId:t[0].provinceId},"GET","JSON",function(t){i.region[1]=t})})},methods:{openAddres:function(){this.$refs.wPicker.show()},confirm:function(i){this.picker=i,this.institution.province=i.checkArr[0],this.institution.city=i.checkArr[1],this.institution.district=i.checkArr[2],this.pickerText=i.checkArr.toString()},getMaterial:function(i,t){this.materialList[t].name=i.configValue.materialName,this.materialList[t].item=i},openMaterial:function(i){this.$refs.materialSelection.open(i)},add:function(){this.materialList.push({name:"",value:""})},del:function(i){this.materialList.length>1?this.materialList.splice(i,1):uni.showToast({title:"必须有一个物资"})},verific:function(){return this.institution.name?this.institution.province||this.institution.city||this.institution.district?this.institution.address?!this.institution.auth.length>0?(uni.showToast({icon:"none",title:"请上传图片"}),!1):this.description?this.donee.name?this.donee.phone?(0,s.default)((0,a.default)(this.donee.phone))?(uni.showToast({icon:"none",title:"联系方式只能输入数字"}),!1):this.donee.wxNumber?!this.materialList.length>0?(uni.showToast({icon:"none",title:"请选择捐赠物资"}),!1):this.materialList[0].name&&this.materialList[0].value?!(0,s.default)((0,a.default)(this.materialList[0].value))||(uni.showToast({icon:"none",title:"物资数量输入正整数"}),!1):(uni.showToast({icon:"none",title:"请输入物资名称/数量"}),!1):(uni.showToast({icon:"none",title:"请输入微信号"}),!1):(uni.showToast({icon:"none",title:"请输入联系方式"}),!1):(uni.showToast({icon:"none",title:"请输入联系人姓名"}),!1):(uni.showToast({icon:"none",title:"请输入备注"}),!1):(uni.showToast({icon:"none",title:"请输入详细地址"}),!1):(uni.showToast({icon:"none",title:"请选择城市"}),!1):(uni.showToast({icon:"none",title:"机构名称不可为空"}),!1)},commit:function(){if(this.disabled=!0,this.institution.auth=this.$refs.fileUpload.getImgs(),!this.verific())return this.disabled=!1,!1;var i=this.materialList.map(function(i){return{materialName:i.item.configValue.materialName,materialId:(0,a.default)(i.item.configValue.materialId),materialNum:(0,a.default)(i.value)}}),t={institution:this.institution,donee:this.donee,description:this.description,materials:i};(0,o.fetch)("/demand/insertDemand",t,"POST","JSON",function(i){uni.showToast({icon:"none",title:"需求发布成功"}),(0,o.back)()})}}};t.default=r},b9d3:function(i,t,e){"use strict";var n=function(){var i=this,t=i.$createElement,e=i._self._c||t;return e("v-uni-view",{staticClass:"body"},[e("v-uni-view",{staticClass:"p title"},[i._v("一、请填写机构相关信息")]),e("v-uni-view",{staticClass:"p label reuired"},[i._v("1.机构类型")]),e("v-uni-view",{staticClass:"margin-tb-sm text-center"},[e("v-uni-button",{staticClass:"cu-btn ",class:"HOSPITAL"===i.institution.type?"bg-blue":"bg-white",on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.institution.type="HOSPITAL"}}},[i._v("医院")]),e("v-uni-button",{staticClass:"cu-btn",class:"SCHOOL"===i.institution.type?"bg-blue":"bg-white",on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.institution.type="SCHOOL"}}},[i._v("学校")]),e("v-uni-button",{staticClass:"cu-btn",class:"COMMUNITY"===i.institution.type?"bg-blue":"bg-white",on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.institution.type="COMMUNITY"}}},[i._v("社区")]),e("v-uni-button",{staticClass:"cu-btn",class:"OTHER"===i.institution.type?"bg-blue":"bg-white",on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.institution.type="OTHER"}}},[i._v("其他")])],1),e("v-uni-view",{staticClass:"p label reuired"},[i._v("2.机构名称")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("请输入机构名称")]),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-input",{staticClass:"my_input_l",attrs:{placeholder:""},model:{value:i.institution.name,callback:function(t){i.$set(i.institution,"name",t)},expression:"institution.name"}})],1),e("v-uni-view",{staticClass:"p label reuired"},[i._v("3.机构地址")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("请输入机构详细地址，便于物资配送")]),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("省市区：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/area.svg"}}),e("v-uni-input",{attrs:{placeholder:""},on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.openAddres.apply(void 0,arguments)}},model:{value:i.pickerText,callback:function(t){i.pickerText=t},expression:"pickerText"}})],1)],1),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("详细地址：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/xxdz.svg"}}),e("v-uni-input",{attrs:{placeholder:""},model:{value:i.institution.address,callback:function(t){i.$set(i.institution,"address",t)},expression:"institution.address"}})],1)],1),e("v-uni-view",{staticClass:"p label reuired"},[i._v("4.机构证明")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("您可以上传可以证明需求真实可靠的照片或截图")]),e("v-uni-view",{staticClass:"p my_item"},[e("fileUpload",{ref:"fileUpload"})],1),e("v-uni-view",{staticClass:"p title"},[i._v("二、防疫物资需求信息")]),e("v-uni-view",{staticClass:"p label reuired"},[i._v("5.物资需求背景说明")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("请简要介绍你所在的机构，并描述物资需求的")]),e("v-uni-view",{staticClass:"my_item"},[e("v-uni-textarea",{staticClass:"my_textarea",attrs:{placeholder:""},model:{value:i.description,callback:function(t){i.description=t},expression:"description"}})],1),e("v-uni-view",{staticClass:"p label reuired"},[i._v("6.物资清单  【最少选择1项】")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("请选择需要的物资，在后面填写需要的物资数量")]),i._l(i.materialList,function(t,n){return e("v-uni-view",{key:n,staticClass:"wz",class:i.materialList.length-1===n?"border_bottom":""},[e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("物资名称：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/jzwz.svg"}}),e("v-uni-input",{attrs:{readonly:!0,placeholder:""},on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.openMaterial(n)}},model:{value:t.name,callback:function(e){i.$set(t,"name",e)},expression:"item.name"}})],1)],1),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("捐赠数量：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/number.svg"}}),e("v-uni-input",{attrs:{placeholder:""},model:{value:t.value,callback:function(e){i.$set(t,"value",e)},expression:"item.value"}})],1)],1),e("v-uni-view",{staticClass:"del"},[e("v-uni-image",{attrs:{src:"../../static/demandRelease/del.svg"},on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.del(n)}}})],1)],1)}),e("v-uni-button",{staticClass:"cu-btn block line-blue margin-tb-sm lg add",on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.add.apply(void 0,arguments)}}},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/add.svg"}}),i._v("添加一项")],1),e("v-uni-view",{staticClass:"p title"},[i._v("三、物资联系人信息")]),e("v-uni-view",{staticClass:"p label reuired"},[i._v("7.联系人")]),e("v-uni-view",{staticClass:"p remarks"},[i._v("请留下联系方式，便于志愿者进行协助与核对")]),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("联系人：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/dh.svg"}}),e("v-uni-input",{attrs:{placeholder:""},model:{value:i.donee.name,callback:function(t){i.$set(i.donee,"name",t)},expression:"donee.name"}})],1)],1),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("联系电话：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/lxr.svg"}}),e("v-uni-input",{attrs:{placeholder:""},model:{value:i.donee.phone,callback:function(t){i.$set(i.donee,"phone",t)},expression:"donee.phone"}})],1)],1),e("v-uni-view",{staticClass:"p my_item"},[e("v-uni-label",[i._v("微信号：")]),e("v-uni-view",{staticClass:"my_input"},[e("v-uni-image",{staticClass:"input_icon",attrs:{src:"../../static/demandRelease/wx.svg"}}),e("v-uni-input",{attrs:{placeholder:""},model:{value:i.donee.wxNumber,callback:function(t){i.$set(i.donee,"wxNumber",t)},expression:"donee.wxNumber"}})],1)],1),e("v-uni-view",[e("v-uni-button",{staticClass:"cu-btn block bg-blue margin-tb-sm lg commit",attrs:{disabled:i.disabled},on:{click:function(t){arguments[0]=t=i.$handleEvent(t),i.commit.apply(void 0,arguments)}}},[i._v("提交")])],1),e("materialSelection",{ref:"materialSelection",on:{getMaterial:function(t){arguments[0]=t=i.$handleEvent(t),i.getMaterial.apply(void 0,arguments)}}}),e("wPicker",{ref:"wPicker",attrs:{mode:"region"},on:{confirm:function(t){arguments[0]=t=i.$handleEvent(t),i.confirm.apply(void 0,arguments)}}})],2)},a=[];e.d(t,"a",function(){return n}),e.d(t,"b",function(){return a})},bfb3:function(i,t,e){i.exports=e("4dc9")},caab:function(i,t,e){"use strict";e.r(t);var n=e("a5b4"),a=e.n(n);for(var s in n)"default"!==s&&function(i){e.d(t,i,function(){return n[i]})}(s);t["default"]=a.a},e758:function(i,t,e){var n=e("38dd");"string"===typeof n&&(n=[[i.i,n,""]]),n.locals&&(i.exports=n.locals);var a=e("4f06").default;a("4926d56c",n,!0,{sourceMap:!1,shadowMode:!1})}}]);