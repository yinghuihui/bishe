<script type="text/javascript">
/**
 * 拒绝、取消原因
 */
$.ajax({
    url: "/modules/common/action/ComboAction/queryDic.htm?typeCode=REFUSE_TYPE",
    type: "get",
    dataType: "json",
    // async:false,
    success: function(result) {
        var len = result.data.length;
        for(var i=0; i<len;i++){
            $(".cancelReasonSelect").append('<option value="'+result.data[i].value+'">'+result.data[i].text+'</option>')
        }

    }
})
// console.log(000)
</script>
<!-- 订单信息-弹窗-start -->
<div class="orderInforWarp" id="orderInforWarp">
    <!-- <div class='titleWarp'>
        <span class="tipTitle">持票方</span>
        <span class="hTitle">北京京东世纪贸易有限公司</span>
    </div>
    <div class="lf-tab">
        <ul class="lf-tab-title">
            <li class="lf-this">交易信息</li>
            <li>电票详情</li>
        </ul>
        <div class="lf-tab-content">
            <div class="lf-tab-item lf-show">
                <div class="lfTab-item-left">
                    <ul>
                        <li>
                            <label>汇款类型</label><span>某某类</span></li>
                        <li>
                            <label>票号</label><span>11111111</span></li>
                        <li>
                            <label>票据签发日</label><span>2016-12-10</span></li>
                        <li>
                            <label>票据到期日</label><span>2016-12-30</span></li>
                        <li>
                            <label>票面金额</label><span>1,000,000.00元</span></li>
                        <li>
                            <label>贴现金额</label><span>1000.00元</span></li>
                        <li>
                            <label>贴现率</label><span>10%</span></li>
                        <li>
                            <label>购买方</label><span>某某机构</span></li>
                        <li>
                            <label>服务费</label><span>10元</span></li>
                    </ul>
                </div>
                <div class="lfTab-item-right">
                    <ul>
                        <li class="active">
                            <p><span class='sort'>1</span>17:00 协议达成，待资金方确定<span class='icon'></span></p>
                            <div class='silderDiv'>议价已达成，待资金方确认
                                <div class='time'><span class='alarm'></span>09:50</div>
                            </div>
                        </li>
                        <li>
                            <p><span class='sort'>2</span>资金方已确认，待资金方付款<span class='icon'></span></p>
                            <div class='silderDiv'>已确认</div>
                        </li>
                        <li>
                            <p><span class='sort'>3</span>资金已代收，请将电银背书转交至交易中心<span class='icon'></span></p>
                            <div class='silderDiv'>已代收</div>
                        </li>
                        <li>
                            <p><span class='sort'>4</span>待交易中心确定签收电银<span class='icon'></span></p>
                            <div class='silderDiv'>待交易中心确定</div>
                        </li>
                        <li>
                            <p><span class='sort'>5</span>交易中心已签收电银，资金已到账，请查收<span class='icon'></span></p>
                            <div class='silderDiv'>已到账</div>
                        </li>
                        <li class='bottom'>
                            <p><span class='sort'>6</span>交易已完成，请评价本次交易<span class='icon'></span></p>
                            <div class='silderDiv bottom'>已完成</div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="lf-tab-item">
                <div class='ticket'>
                    <p>电票正面</p>
                    <img src="/resources/img/seller/nophoto.jpg">
                </div>
                <div class='ticket'>
                    <p>电票背面</p>
                    <img src="/resources/img/seller/nophoto.jpg">
                </div>
            </div>
        </div>
    </div> -->
</div>
<!-- 订单信息-弹窗-end -->


<!-- 发起交易-弹窗-start -->
<div class="startTradeWarp">
    <div class='titleWarp'>
        <span class="hTitle">结算凭证信息</span>
    </div>
    <div class="startTrade-lis">
        <form id="layerSeller">
        </form>
    </div>
</div>
<!-- 发起交易-弹窗-end -->


<!-- 取消交易-弹窗-start -->
<div class='cancelWarp'>
<form id="cancelFrom">
    <div class='cancelWin'>
        <div class='tpips-div'><span class='cancelTip'></span>你确定要取消交割？取消交割将影响您的交割信用，请谨慎远择！</div>
        <div class='cancelReason'>
            <label><span class='required'>*</span>取消原因</label>
            <select class='borrowFormTxt J_input cancelReasonSelect' autocomplete="off" name="cancelReason" id="cancelReason">
                <!-- <option value="1">原因A</option>
                <option value="2">原因C</option>
                <option value="3">原因B</option> -->
            </select>
        </div>
        <div class='cancelRemark'>
            <label><span class='required'>*</span>备注说明</label>
            <textarea name="cancelRemark"></textarea>
        </div>
    </div>
</form>
</div>
<!-- 取消交易-弹窗-end -->

<!-- 拒绝购买-弹窗-start -->
<div class='refuseWarp'>
<form id="refuseFrom">
    <div class='cancelWin'>
        <div class='tpips-div'><span class='cancelTip'></span>你确定要拒绝购买？请说明原因。</div>
        <div class='cancelReason'>
            <label><span class='required'>*</span>拒绝原因</label>
            <select class='borrowFormTxt J_input cancelReasonSelect' autocomplete="off" name="cancelReason">
                <!-- <option value="1">原因A</option>
                <option value="2">原因C</option>
                <option value="3">原因B</option> -->
            </select>
        </div>
        <div class='cancelRemark'>
            <label><span class='required'>*</span>备注说明</label>
            <textarea name="cancelRemark"></textarea>
        </div>
    </div>
</form>
</div>
<!-- 拒绝购买-弹窗-end -->
<!-- 设置接受票源规则-弹窗-start -->
<div class='setRuleWrap'>
<form id="setRuleFrom">
    <input type="hidden" name="id" id="setRule" />
    <div class='cancelWin'>
        <div class='cancelReason settleType-fa'>
            <label>结算类型</label>
            <input class='dateInput' name="receiptTypeId" id="PjsReceiptType" autocomplete="off" readonly="readonly" />
            <!-- <input type="text" class="dateInput" name="PjsReceiptType" id="PjsReceiptType" value=""  readonly="readonly" />
            <input type="hidden" name="receiptTypeId" id="receiptTypeId"> -->
           <ul class="settleType clearfix" id="settleType">
                    <!-- <li><input type="checkbox" value="" />苹果 </li> -->
            </ul>
            <!-- <div id="menuContent" class="menuContent" style="">
                <ul id="treeDemo" class="ztree"></ul>
            </div> -->

        </div>
        <div class='cancelReason'>
            <div class='cancelReason' id="banckWrap">
                <label>承兑银行</label>
                <input name="accBank" id="bankName" class="dateInput" type="text" readonly="readonly" />
                <ul class="bankNameList clearfix" id="bankNameList">
                    <!-- <li><input type="checkbox" value="" />苹果 </li> -->
                </ul>
                <!-- <select class='borrowFormTxt J_input' name="accBank" id="bankName" autocomplete="off">
                </select> -->
            </div>
        </div>
        <div class='cancelReason'>
            <label>到期期限</label>
            <!-- <input type="text" class="dateInput" name="dueDate" id="dueDate" value=""  readonly="readonly" /> -->
            <select class='borrowFormTxt J_input' name="expires" id="dueDate" autocomplete="off">
                <option value="0">不限</option>
                <option value="1">足月:180天</option>
                <option value="2">足月:360天</option>
                <option value="3">不足月:1-180天</option>
                <option value="4">不足月:181-360天</option>
            </select>
        </div>
        <div class='cancelReason'>
            <label><span class='required'>*</span>金额阀值</label>
            <input name="billAmtMax" id="billAmtMax" class="dateInput" type="text" />
        </div>
        <div class='cancelReason'>
            <label><span class='required'>*</span>贴现率(%)</label>
            <input name="discountRate" id="discountRate" class="dateInput" type="text" />
        </div>
        <!-- <div class='cancelReason'>
            <label>自动轮换时长</label>
            <select class='borrowFormTxt J_input' autocomplete="off">
                <option>原因A</option>
                <option>原因C</option>
                <option>原因B</option>
            </select>
        </div> -->
    </div>
</form>
</div>
<!-- 设置接受票源规则-弹窗-end -->

<!-- 查看票源规则列表-弹窗-start -->
<div class='showRuleWrap'>
    <ul class="showRuleTitle clearfix">
        <li class="showRuleId">编号</li>
        <li class="showRuleType">票据类型</li>
        <li class="showRuleBank">承兑银行</li>
        <li class="showRuleDate">到期期限</li>
        <li class="showRuleBil">金额阀值</li>
        <li class="showRuleRate">贴现率(%)</li>
        <li class="showRuleOpretion">操作</li>
    </ul>
    <div class="showRuleListWrap" id="showRuleListWrap">
        <!-- <ul class="showRuleList clearfix">
            <li class="showRuleId">1</li>
            <li class="showRuleType">票据类型</li>
            <li class="showRuleBank">承兑银行</li>
            <li class="showRuleDate">到期期限</li>
            <li class="showRuleBil">金额阀值</li>
            <li class="showRuleOpretion">
                <a href="javascript:;">编辑</a>
            </li>
        </ul>
        <ul class="showRuleList clearfix">
            <li class="showRuleId">1</li>
            <li class="showRuleType">票据类型</li>
            <li class="showRuleBank">承兑银行</li>
            <li class="showRuleDate">到期期限</li>
            <li class="showRuleBil">金额阀值</li>
            <li class="showRuleOpretion">
                <a href="javascript:;">编辑</a>
            </li>
        </ul>
        <ul class="showRuleList clearfix">
            <li class="showRuleId">1</li>
            <li class="showRuleType">票据类型</li>
            <li class="showRuleBank">承兑银行</li>
            <li class="showRuleDate">到期期限</li>
            <li class="showRuleBil">金额阀值</li>
            <li class="showRuleOpretion">
                <a href="javascript:;">编辑</a>
            </li>
        </ul>
        <ul class="showRuleList clearfix">
            <li class="showRuleId">1</li>
            <li class="showRuleType">票据类型</li>
            <li class="showRuleBank">承兑银行</li>
            <li class="showRuleDate">到期期限</li>
            <li class="showRuleBil">金额阀值</li>
            <li class="showRuleOpretion">
                <a href="javascript:;">编辑</a>
            </li>
        </ul> -->
    </div>
</div>
<!-- 查看票源规则列表-弹窗-start -->

<!-- 添加银行账户-弹窗-start -->
<div class="addBank" style="display: none;">
<form id="addBankForm">
    <div class='cancelWin'>
        <div class='cancelReason'>
            <label>开户银行</label>
            <select class='borrowFormTxt J_input' name="bankArea" id="bankListSelect" autocomplete="off">
            </select>
        </div>
        <div class='cancelReason'>
            <label>银行账号</label>
            <input name="bankCard" id="bankCard" class="dateInput" type="text" maxlength="30" />
        </div>
        <div class='cancelReason'>
            <label>开户地区</label>
            <select id="province" name="province" class="borrowFormTxt areaCls"> </select> <select id="city" name="city" class="borrowFormTxt areaCls"></select>
        </div>
        <div class='cancelReason'>
            <label>账户名称</label>
            <input name="accountName" id="bankAccountName" class="dateInput" type="text" maxlength="30" />
        </div>

    </div>
</form>
</div>
<!-- 添加银行账户-弹窗-start -->

<!-- 协议 start -->
<div class="borrowftl" style="display: none;">
    <div style="padding: 30px 15px">
        <#include "/contractfile/templates/borrow.ftl">
        <!-- <div><input id="xiyiCheck" type="checkbox" name="checkbox" value="" />我已阅读协议</div> -->
    </div>
</div>
<!-- 协议 End -->
