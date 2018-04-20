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
  Radio,
  message,
  DatePicker,
  
} from 'antd';

const createForm = Form.create;
const FormItem = Form.Item;
var confirm = Modal.confirm;
const Option = Select.Option;
const objectAssign = require('object-assign');
var AddWin = React.createClass({
  getInitialState() {
    return {
       checked: true,
       disabled: false,
       data:"",
       record:"",
    };
  },
  handleCancel() {
    this.setState({
      first: true,
    })
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk(){
    var me = this;
      this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        //console.log('Errors in form!!!');
        return;
      }
     var tips = "您是否确定提交";
     var data = values;
     var url = '';
     if(me.props.title == '编辑'){
         url = '/modules/manage/messageTpl/update.htm';
         data.id = me.props.record.id
     }else{
         url = '/modules/manage/messageTpl/save.htm'
     }
     confirm({
        title: tips,
        onOk: function() {
            Utils.ajaxData({
                url:  url,
                data: data,
                method: 'post',
                callback: (result) => {
                    if(result.code==200){
                        Modal.success({
                            title: result.msg,
                        });     
                    }else{
                        Modal.error({
                            title:  result.msg,
                        });
                    }
                    me.handleCancel();
                }
            });
          },
        onCancel: function() {}
     });
      
      })
  },
  componentWillReceiveProps(nextProps) {
       this.setState({
         record:nextProps.record
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
        span: 6
      },
      wrapperCol: {
        span: 16
      },
    };
     var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="400" footer={modalBtns} maskClosable={false} >
         <Form horizontal  form={this.props.form}>
          <Row>
              <Col span="24">
                  <FormItem  {...formItemLayout} label="模板名称:">
                      <Input disabled={!props.canEdit}  {...getFieldProps('typeName', { rules: [{ required: true, message: '必填' }] }) } type="text" />
                  </FormItem>
              </Col>
          </Row> 
          <Row>
              <Col span="24">
                  <FormItem  {...formItemLayout} label="模板类型:">
                      <Input disabled={props.title=='编辑'?true:false}  {...getFieldProps('type', { rules: [{ required: true, message: '必填' }] }) } type="text" />
                  </FormItem>
              </Col>
          </Row> 
          <Row>
              <Col span="24">
                  <FormItem  {...formItemLayout} label="模板内容:">
                      <Input disabled={!props.canEdit}  {...getFieldProps('tpl', { rules: [{ required: true, message: '必填' }] }) } type="text" />
                  </FormItem>
              </Col>
          </Row> 
          <Row>
            <Col span="24">
              <FormItem  {...formItemLayout} label="状态:">
                  <Select {...getFieldProps('state',{initialValue: '',rules: [{ required: true, message: '必填' }]})} >
                    <Option value='10'>启用</Option>
                    <Option value='20'>禁用</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          </Form>
        </Modal>
    );
  }
});
AddWin = createForm()(AddWin);
export default AddWin;