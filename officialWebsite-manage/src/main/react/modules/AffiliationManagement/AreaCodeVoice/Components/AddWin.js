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
    Col,
    Radio
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const objectAssign = require('object-assign');
import Region from '../../../plugin/Region';
import chinaRegion from '../../../plugin/chinaRegion';


var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
            province: '',
            city: '',
            provinceName: '',
            cityName: '',
            area:'',
            areaCodeName: [],
        };
    },

    changeAreaId (areaId) {
        var areaCodeName = String(areaId);
        var province = areaCodeName.substr(0, 2) + '0000';
        var city = areaCodeName.substr(0, 4) + '00';
        this.setState({
        province: chinaRegion[province][0],
        city: chinaRegion[city][0],
        area:  chinaRegion[areaCodeName][0],
        });
    },

    componentWillReceiveProps(nextProps, nextState) {   
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
        this.setState({
            province:'',
            city: '',
            area:'',
            areaCodeName: [],
        });
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var state = me.state;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        var url;
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            var data = {};
            if (title == "新增") {
                url = "/modules/manage/voiceLocation/save.htm";
            } else if (title == "编辑") {
                url = "/modules/manage/voiceLocation/update.htm";
                data.id = values.id;
            }
            data.voice = values.voice;
            data.areaCode = values.areaCode;
            data.province = state.province?state.province:props.record.province;
            data.city = state.city?state.city:props.record.city;
            data.area = state.area?state.area:props.record.area;
            Utils.ajaxData({
                url: url,
                data: data,
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                me.handleCancel();
                            }
                        });
                    } else {
                        Modal.error({
                            title: result.msg
                        });
                    }
                }
            });
        })
    },

    componentDidMount() {
    },

    areaCodeLength(rule, value, callback) {
           if (!value || value.length == 6) {
               callback();
           } else {
               callback([new Error('需6个字符')]);
           }
    },

    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="button" className="ant-btn ant-btn-primary" onClick={this.handleOk}>
                确定
            </button>,
            <button key="back" className="ant-btn" onClick={this.handleCancel}>取消</button>
        ];
        var modalBtnsLook = [
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 18
            },
        };
        var canEditCard = props.title == '新增'?false:true; 
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="600" footer={props.title =='查看'?modalBtnsLook:modalBtns} maskClosable={false}>
                <Form horizontal form={this.props.form}>
                    <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="身份证代码:">
                                <Input style={{width:'200'}} disabled={!props.canEdit}  {...getFieldProps('areaCode', { rules: [{ required: true, message: '必填' },{validator:this.areaCodeLength,message:'长度需为6个字符'},,{validator:validatorFn.isNumber,message:'请输入整数'}] }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row> 
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="主流语音:">
                                <Input style={{width:'200'}} disabled={!props.canEdit}  {...getFieldProps('voice', { rules: [{ required: true, message: '必填' },{ validator: validatorFn.checkVioceLength, message: '长度需小于32个字符'}] }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row> 
                    <Row>
                        <Col span="24">
                            <FormItem {...formItemLayout} label="地址">
                                <Region disabled={!props.canEdit} {...getFieldProps('areaCodeName', {
                                initailValue: [],rules:[{required:true,message:'必填'}],
                                onChange: this.changeAreaId,
                                }) } />
                            </FormItem>
                        </Col>
                    </Row> 
                </Form>
            </Modal >
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
