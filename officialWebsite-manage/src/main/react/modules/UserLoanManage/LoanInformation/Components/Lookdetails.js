import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,

} from 'antd';

const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Lookdetails = React.createClass({
  getInitialState() {
    return {
      checked: true,
      disabled: false,
      data: "",
      timeString: "",
      record: ""
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  componentWillReceiveProps(nextProps) {
    if (nextProps.visible) {
      this.setState({
        record: nextProps.record
      })
    }
  },
  componentDidMount() {
    //this.props.form.resetFields();
      let state = this.props.dataRecord.state;
      if ( state != "27" && state != '20' && state != '21' && state != '22') {
          this.props.dataRecord.state = "26";
      }
    this.props.form.setFieldsValue(this.props.dataRecord);
  },
  onChange(time, timeString) {

    //console.log(time, timeString);
    this.setState({
      timeString: timeString,
    })
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;


    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayoutone = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const formItemLayouttwo = {
      labelCol: {
        span: 6
      },
      wrapperCol: {
        span: 18
      },
    };
    return (
        <Form horizontal form={this.props.form} style={{marginTop:20}}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="订单号:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('orderNo', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款人姓名:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('realName', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="手机号码:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('phone', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款期限(月):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('timeLimit', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="借款金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('amount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款利息(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('interest', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>  
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际到账金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('realAmount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="借款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('createTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="放款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('loanTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="逾期天数(天):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('penaltyDay', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="逾期金额(元):">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('penaltyAmout', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实际还款时间:">
                <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
          <Col span="12">
            <FormItem {...formItemLayout} label="应还总额(元):">
              <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayTotal', { initialValue: '' }) } />
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem {...formItemLayout} label="已还总额(元):">
              <Input disabled={!props.canEdit} type="text" {...getFieldProps('repayYesTotal', { initialValue: '' }) } />
            </FormItem>
          </Col>
        </Row>
        <Row>
              <Col span="18">
              <FormItem  {...formItemLayout} label="审批意见:">
                  <Select  {...getFieldProps('state', { initialValue: "27" }) } disabled={!props.canEdit}>
                      <Option value="20">自动审核通过</Option>
                      <Option value="21">自动审核不通过</Option>
                      <Option value="22">自动审核未决待人工复审</Option>
                      <Option value="27">人工复审拒绝</Option>
                      <Option value="26">人工复审通过</Option>
                  </Select>
              </FormItem>
              </Col>
          </Row>
          <Row>
              <Col span="18">
              <FormItem  {...formItemLayout} label="备注说明:">
                  <Input disabled={!props.canEdit} type="textarea" placeholder="" rows={4} style={{ width: "500px", height: "40px" }}   {...getFieldProps('remark', { initialValue: '',rules:[{ max: 50,message: '不能超过50个字符' }] }) } />
              </FormItem>
              </Col>
          </Row>
        </Form>
    );
  }
});
Lookdetails = createForm()(Lookdetails);
export default Lookdetails;