import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Row,
  Col,
  Select,
  Tabs,
} from 'antd';

import RuleReport from './RuleReport';
import ManualReviewForm from './ManualReviewForm'
import Tab1 from './Tab1';
import Tab2 from './Tab2';
import Tab3 from './Tab3';
import Tab4 from './Tab4';
import Tab5 from './Tab5';
import Tab7 from './Tab7';
import Tab11 from './Tab11';

const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const TabPane = Tabs.TabPane;
var confirm = Modal.confirm;
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      activekey: '1'
    };
  },
  handleCancel() {
     this.changeTabState();
    // this.refs.Tab1.resetFields();
    this.props.hideModal();
    
  },

  changeTabState() {
      this.setState({
          activekey: '1',
      })
  },
  handleTabClick(key) {
      this.setState({
          activekey: key
      })
  },
  handleOk() {

    let me = this;
    let params = this.refs.ManualReviewForm.getFieldsValue();
    let record = this.props.record;
    console.log("record",record)
    this.refs.ManualReviewForm.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
      var tips = '是否确定提交';
      confirm({
        title: tips,
        onOk: function () {
          Utils.ajaxData({
            url: '/modules/manage/borrow/verifyBorrow.htm',
            data: { borrowId: record.id, state: params.state1 == "0"?"":params.state1,type:params.state1 == "0"?"1":"", remark: params.remark },
            callback: (result) => {
              if (result.code == 200) {
                me.handleCancel();
              };
              let resType = result.code == 200 ? 'success' : 'warning';
              Modal[resType]({
                title: result.msg,
              });
            }
          });
        },
        onCancel: function () { }
      })

    })

  },
  render() {
    // const {
    //   getFieldProps
    // } = this.props.form;
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>,
      <button key="sure" className="ant-btn ant-btn-primary" onClick={this.handleOk}>确定</button>
    ];
    var modalBtnstwo = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>,
    ];
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 12
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 4
      },
      wrapperCol: {
        span: 20
      },
    };
    

    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1200" footer={props.title == "查看" ? [modalBtnstwo] : [modalBtns]} maskClosable={false} >

        <Tabs onTabClick={this.handleTabClick}  activekey={this.state.activekey}  >
          <TabPane tab="基本信息" key="1">
            <Tab1  ref="Tab1" record={props.record} dataForm={props.dataForm} canEdit={props.canEdit} visible={props.visible} recordSoure={props.recordSoure} activekey={this.state.activekey}/>
          </TabPane>
           {/* <TabPane tab="规则报告" key='2'>
            <RuleReport  record={this.props.record} visible={props.visible} activekey={this.state.activekey}/>
          </TabPane>
        <TabPane tab="同盾决策引擎审核结果" key="7">
            <Tab7 ref="Tab7" record={props.record}  canEdit={props.canEdit} visible={props.visible} activekey={this.state.activekey}/>
          </TabPane>*/}
        { /* <TabPane tab="通讯录" key="3">
            <Tab2 ref="Tab2" record={props.record}  canEdit={props.canEdit} visible={props.visible} activekey={this.state.activekey}/>
          </TabPane>
          <TabPane tab="通话记录ssss" key="4">
            <Tab3 ref="Tab3" record={props.record}  canEdit={props.canEdit} visible={props.visible} activekey={this.state.activekey}/>
          </TabPane>
            <TabPane tab="淘宝信息" key="12">
            <Tab11  ref="Tab11" record={props.record} canEdit={props.canEdit} visible={props.visible} activekey={this.state.activekey}/>
        </TabPane>*/}
        </Tabs>
        <ManualReviewForm ref="ManualReviewForm" canEdit={props.canEdit}/>
      </Modal>
    );
  }
});
export default Lookdetails;