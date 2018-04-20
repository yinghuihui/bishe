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
       data.remark = '20';
      if(props.title=="添加"){
        url = "/modules/manage/programa/save.htm";
        data=JSON.stringify(data);
      }else{
        url="/modules/manage/programa/update.htm";
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
          } else {
                    Modal.error({
                        title: result.msg
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
    var treeData = this.props.officeData?this.props.officeData:[];
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
                <FormItem  {...formItemLayout} label="名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('programaName',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="关键字：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('programaKeyword',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
               </Col>
               </Row>
               <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="父节点：">
                  <TreeSelect  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                  {...getFieldProps('parentId',{rules:[{required:true,message:'必填'}]})}
                  placeholder="请选择" treeDefaultExpandAll />
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
                <FormItem  {...formItemLayout} label="链接地址：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('href',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="链接类型：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('hrefType',{rules:[{required:true,message:'必填'}]})} type="text" />
                </FormItem> 
               </Col>
               </Row>
              <Row>
              <Col span="12">
                <FormItem  {...formItemLayout}  label="状态：">
                  <Select id="select" size="large" disabled={!props.canEdit} {...getFieldProps('state',{initialValue:"10",onChange:this.change})} >
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
AddMenu = createForm()(AddMenu);
export default AddMenu;