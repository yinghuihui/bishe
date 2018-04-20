import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col,
    DatePicker 
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
import Ueditor from '../../../plugin/Ueditor';
const file1 ={
    position: 'absolute',
    width: '120px',
    left: '4px',
    top: '-1px',
    'borderColor': '#f50',
    'boxShadow': '0 0 0 2px rgba(255, 85, 0, 0.2)',
     opacity: '0'
};
const textSty ={
    position: 'absolute',
    left: '-68px',
    color: '#f50',
     opacity: '1'
};
const textSty1={
    position: 'absolute',
    top: '26px',
    left: '9px',
    color: '#f50',
    opacity: '0'
}
var AddWin = React.createClass({
    getInitialState() {
        return {
            loading: false,
            record:"",
            data:"",
            canEdit:true,
            dataRecord:[],
            newsContent:"",
            fileList: '',
        };
    },
  handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
          this.setState({
            canEdit:true,
            dataRecord:"",  
            fileList: '',
        })  
    },
    componentWillReceiveProps(nextProps, nextState) {
        this.setState({
            dataRecord:nextProps.dataRecord
        })
  },

    handleOk() {
        var me = this;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                //console.log('Errors in form!!!');
                return;
            }
        let filea = new FormData();
        let fileList = me.state.fileList;
        if(this.props.title=="新增"){       
                var url= '/modules/manage/content/save.htm';
                if(me.state.fileList.length==0  ){
                    var Input1 = document.getElementById('file1');  
                    var span1 = document.getElementById('text1');
                    var span = document.getElementById('text');
                    Input1.style.opacity="1";  
                    span1.style.opacity="1"; 
                    span.style.opacity="1"; 
                    return;
                }else{
                    var Input1 = document.getElementById('file1');  
                    var span1 = document.getElementById('text1');
                    var span = document.getElementById('text');
                    Input1.style.opacity="0";  
                    span1.style.opacity="0"; 
                    span.style.opacity="1"; }
        }   
        if(this.props.title=="编辑"){
            var url= '/modules/manage/content/update.htm';
            filea.append('id', me.props.record.id);
        }  
        filea.append('thumbnail', fileList[0]);
        filea.append('content', UE.getEditor('content').getContent());
        filea.append('description', values.description);
        filea.append('isStick', values.isStick);
        filea.append('keyword', values.keyword);
        filea.append('programaId', values.programaId);
        filea.append('remark', "10");
        filea.append('resource', values.resource);
        filea.append('state', values.state);
        filea.append('title', values.title);
        filea.append('writer', values.writer);
        filea.append('sort', values.sort?values.sort:"0");
        // params.content = UE.getEditor('content').getContent();
        let req = new XMLHttpRequest();
        req.open('POST', url, true);
        req.onload = function (event) {
            let result = JSON.parse(req.responseText);
            if (req.status === 200) {
                if (result.code == 200) {
                    Modal.success({
                        title:result.msg
                    })
                    me.handleCancel();
                } else {
                    Modal.error({
                    title: result.msg
                    })
                }
            } else {
                Modal.error({
                    title: '请求失败'
                })
            }
        };
            req.send(filea);
        })
    },

    componentDidMount() {
        let treeData = [];
        var data = {};
        data.remark = '10';
        Utils.ajaxData({
            url: '/modules/manage/programa/list.htm',
            data:{
                searchParams:JSON.stringify(data),
            },
            method: 'get',
            type: 'json',
            callback: (result) => {
                this.setState({
                    treeData:result.data
                })
            }
        });
    },

    handleFileChange(files) {
        if(this.props.title=='新增'){
            var Input1 = document.getElementById('file1');  
            var span1 = document.getElementById('text1');
            var span = document.getElementById('text');
            Input1.style.opacity="0";  
            span1.style.opacity="0"; 
            span.style.opacity="1"; 
        }
        let fileList = document.getElementById('file').files;

        var r= new FileReader();
        var f=document.getElementById('file').files[0];
        r.readAsDataURL(f);
        r.onload=function  (e) {
            document.getElementById('show').src=this.result;
            document.getElementById('show').style.width = '80px';
            document.getElementById('show').style.height = '80px';
        };
        this.setState({
            fileList : document.getElementById('file').files
        })
    },

    render() {
        const {
            getFieldProps
        } = this.props.form;
        const { record,treeData } = this.state;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 14
            },
        };
        const formItemLayout1 = {
            labelCol: {
                span: 4
            },
            wrapperCol: {
                span: 19
            },
        };

        var formatItem = props.title == '新增'?(
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="缩略图:">
                                    <span id='text' style={textSty}>*</span>
                                    <Input id="file1" style={file1} />
                                    <span id='text1' style={textSty1}>必填</span>
                                    <a className="a-upload">
                                        <Input id="file"  onChange={this.handleFileChange} type="file" />点击这里上传图片
                                    </a>
                                </FormItem>
                            </Col>
                         ):(
                            <Col span="12">
                                <FormItem  {...formItemLayout} label="缩略图:">
                                    <span id='text' style={textSty}>*</span>
                                    <a className="a-upload">
                                        <Input id="file" onChange={this.handleFileChange} type="file"/>点击这里修改图片
                                    </a>
                                </FormItem>
                            </Col>)
        
        let newsContent = state.newsContent? state.newsContent:props.record.content;
        return (
             <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="760" footer={modalBtns} maskClosable={false} >
                <Form horizontal form={this.props.form} style={{ marginTop: "20px" }}>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="标题:">
                                <Input type="text" placeholder="请输入标题" disabled={!props.canEdit} {...getFieldProps('title',{rules:[{required: true, message: '必填'}]})} />                                 
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="作者:">
                                <Input type="text" placeholder="请输入作者" disabled={!props.canEdit} {...getFieldProps('writer',{rules:[{ required: true, message: '必填' }]})} />                                 
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="文章来源:">
                                <Input type="text" placeholder="请输入文章来源" disabled={!props.canEdit} {...getFieldProps('resource',{rules:[{ required: true, message: '必填'}]})} />                                 
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="栏目名称：">
                                    <TreeSelect  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                                    {...getFieldProps('programaId',{rules:[{ required: true, message: '必填'}]})}
                                    placeholder="请选择" treeDefaultExpandAll />
                            </FormItem> 
                        </Col>   
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="关键字:">
                                <Input type="text" placeholder="请输入关键字" disabled={!props.canEdit} {...getFieldProps('keyword',{rules:[{required: true, message: '必填'}]})} />                                 
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout}  label="是否置顶：">
                                <Select id="select" size="large" disabled={!props.canEdit} {...getFieldProps('isStick',{initialValue:"10",onChange:this.change})} >
                                        <Option value={"10"}>置顶</Option> 
                                        <Option value={"20"}>不置顶</Option>
                                </Select> 
                            </FormItem>
                         </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="描述:">
                                <Input type="text" placeholder="请输入描述" disabled={!props.canEdit} {...getFieldProps('description',{rules:[{ required: true, message: '必填' }]})} />                                 
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout}  label="状态：">
                                <Select id="select" size="large" disabled={!props.canEdit} {...getFieldProps('state',{initialValue:"10",onChange:this.change})} >
                                        <Option value={"10"}>启用</Option> 
                                        <Option value={"20"}>禁用</Option>
                                </Select> 
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="排序:">
                                <Input type="text" placeholder="请输入排序" disabled={!props.canEdit} {...getFieldProps('sort',{rules:[{validator:validatorFn.isNumber,message:'请输入整数'}]})} />                                 
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        {formatItem}
                        <Col span="12">
                            <FormItem  {...formItemLayout}>
                                {props.title == '新增' && state.fileList == '' ?null:<img style={{width:80,height:80}} src={props.record.thumbnail} id='show' />  }
                            </FormItem>
                        </Col>
                    </Row>
                    {props.visible?<Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout1}  label="内容：">
                                <Ueditor value={newsContent} id="content" height="200" record={record} disabled={!props.canEdit}/> 
                            </FormItem> 
                        </Col>
                    </Row>:''}
                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
