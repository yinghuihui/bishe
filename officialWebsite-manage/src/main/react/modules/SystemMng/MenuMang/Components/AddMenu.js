import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Select,
  Tree,
  TreeSelect,
  Row,
  Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const TreeNode = Tree.TreeNode;
const Option = Select.Option;

let treeData = [];
Utils.ajaxData({
  url: '/modules/manage/system/menu/list.htm',
  method: 'get',
  type: 'json',
  callback: (result) => {
    let data = result.data;
    treeData = data;
  }
});

var AddMenu = React.createClass({
  getInitialState() {
    return {
      visible: false,
      status: {

      },
      formData: {},
      value:""
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk(e) {
    e.preventDefault();
    var me = this;
    var props = me.props;
    var record = props.record;
    var title = props.title;
    //console.log(record)

    var url = "";
   
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
       var data=values;
      if(props.title=="添加"){
        url = "/modules/manage/system/menu/save.htm";
        data=JSON.stringify(data);
      }else{
        url="/modules/manage/system/menu/update.htm";
        data=JSON.stringify(data);
      }
      // let status = "create";
      // if (title == "修改") {
      //   status = "update";
      // }
      Utils.sendAjax({
        url: url,
        data: data,
        callback: (result) => {
          if (result.code == 200) {
            Modal.success({
              title: result.msg,
              onOk: () => {
                me.props.form.resetFields();
                props.hideModal();
              }
            });
          }
        }
      });
    });
  },
  componentDidMount() {
    var record = {};
    if (this.props.record) {
      var record = this.props.record;
    }
    this.props.form.setFieldsValue(record);
  },
  change(value){
    //console.log("00000",value);

    this.setState({
        value:value
    })

  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    var formData = state.formData;
    treeData = this.props.officeData;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          确 定
      </button>
    ];
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 15
      },
    };
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
          <Form horizontal  form={this.props.form}>
            <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="脚本名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('scriptid',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="菜单名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('name',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
               </Col>
               </Row>
               <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="父节点：">
                <div  className="clearfix">
                  <TreeSelect  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                  {...getFieldProps('parentId')}
                  placeholder="请选择" treeDefaultExpandAll />
                  </div>
                </FormItem> 
              </Col>                
              <Col span="12">
                <FormItem  {...formItemLayout} label="图标：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('iconCls')} type="text"  />
                </FormItem> 
              </Col> 
              </Row>
              <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="菜单说明：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('remark',{rules:[{required:true,message:'必填'}]})} type="text" /> 
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="排序：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('sort',{rules:[{required:true,message:'必填'}]})} type="text"/> 
                </FormItem>
              </Col>
              </Row>
              <Row>
              <Col span="12">
                <FormItem  {...formItemLayout}  label="是否删除：">
                  <Select id="select" size="large"  disabled={!props.canEdit} {...getFieldProps('isDelete',{initialValue:0})} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select> 
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout}  label="类型：">
                  <Select id="select" size="large" disabled={!props.canEdit} {...getFieldProps('type',{initialValue:"10",onChange:this.change})} >
                        <Option value={"10"}>菜单</Option> 
                        <Option value={"20"}>权限</Option>
                  </Select> 
                </FormItem>
              </Col> 
              </Row>
            
              {this.state.value=="20"?(
                <Col span="12">
                <FormItem  {...formItemLayout} label="URL：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('url')} type="text" /> 
                </FormItem>
               </Col>):""}  
           
          </Form>
      </Modal>
    );
  }
});
AddMenu = createForm()(AddMenu);
export default AddMenu;