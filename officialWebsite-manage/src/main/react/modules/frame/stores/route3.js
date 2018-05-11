var OfficialWebsiteColumn = require('../../OfficialWebsite/OfficialWebsiteColumn/index');//袋鼠钱包-网站栏目
var OfficialWebsiteContent = require('../../OfficialWebsite/OfficialWebsiteContent/index');//袋鼠钱包-内容列表
var ScienceWebsiteColumn = require('../../OfficialWebsite/ScienceWebsiteColumn/index');//袋鼠科技-网站栏目
var ScienceWebsiteContent = require('../../OfficialWebsite/ScienceWebsiteContent/index');//袋鼠科技-内容列表
var StagesWebsiteColumn = require('../../OfficialWebsite/StagesWebsiteColumn/index');//袋鼠分期-网站栏目
var StagesWebsiteContent = require('../../OfficialWebsite/StagesWebsiteContent/index');//袋鼠分期-内容列表
var RiskExamine = require('../../RiskController/RiskExamine/index');//人工审核
var TimedTaskList = require('../../TimedTaskList/index');//定时任务
var TimedTaskLog = require('../../TimedTaskLog/index');//定时任务
var OverdueList = require('../../UserLoanManage/OverdueList/index');//贷前管理-逾期列表
var RepaymentList = require('../../UserLoanManage/RepaymentList/index');//还款列表
var BadDebtsList = require('../../UserLoanManage/BadDebtsList/index');//已坏账列表
var LoanList = require('../../UserLoanManage/LoanList/index');//贷前管理-放款列表
var LoanInformation = require('../../UserLoanManage/LoanInformation/index');//借款列表
var LoanSchedule = require('../../LoanSchedule/index');//贷中管理-借款进度
module.exports = {
  OfficialWebsiteColumn,
  OfficialWebsiteContent,
  ScienceWebsiteColumn,
	ScienceWebsiteContent,
	StagesWebsiteColumn,
	StagesWebsiteContent,
	RiskExamine,
	TimedTaskList,
	TimedTaskLog,
	OverdueList,
	LoanList,
	LoanInformation,
	LoanSchedule,
	RepaymentList,
	BadDebtsList,
} 
