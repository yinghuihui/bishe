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
import Region from '../../plugin/Region';
import chinaRegion from '../../plugin/chinaRegion';


var AddWin = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
            province: '',
            city: '',
            areaCode:'',
        };
    },

    changeAreaId (areaId) {
        var areaCode = String(areaId);
        var province = areaCode.substr(0, 2) + '0000';
        var city = areaCode.substr(0, 4) + '00';
        this.setState({
        province:chinaRegion[province][0],
        city: chinaRegion[city][0],
        areaCode: areaCode,
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
            areaCode: '',
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
            this.changeAreaId(values.areaCode)
            if (title == "新增") {
                url = "/modules/manage/phoneLocation/save.htm";
            } else if (title == "编辑") {
                url = "/modules/manage/phoneLocation/update.htm";
                data.id = values.id;
            }
            data.operator = values.operator;
            data.phoneSectionNo = values.phoneSectionNo;
            data.areaCode = values.areaCode;
            data.province = state.province?state.province:this.props.record.province;
            data.city = state.city?state.city:this.props.record.city;
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
        
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="500" footer={props.title =='查看'?modalBtnsLook:modalBtns} maskClosable={false}>
                <Form horizontal form={this.props.form}>
                    <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="运营商:">
                                <Input style={{width:'200'}} disabled={!props.canEdit}  {...getFieldProps('operator', { rules: [{ required: true, message: '必填' },
                                { validator: validatorFn.checkBusinessLength, message: '长度需小于9个字符'},{ validator: validatorFn.isChinese, message: '请输入中文名称'}] }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row> 
                    <Row>
                        <Col span="24">
                            <FormItem  {...formItemLayout} label="手机号段:">
                                <Input style={{width:'200'}} disabled={!props.canEdit}  {...getFieldProps('phoneSectionNo', { rules: [{ required: true, message: '必填' },{ validator: validatorFn.checkNoMobile, message: '手机段号格式错误'},{ validator: validatorFn.isNumber, message: '请输入数字'}] }) } type="text" />
                            </FormItem>
                        </Col>
                    </Row> 
                    <Row>
                        <Col span="24">
                            <FormItem {...formItemLayout} label="地址">
                                <Region disabled={!props.canEdit} {...getFieldProps('areaCode', {
                                initailValue: this.props.areaCode,
                                rules: [{ required: true, message: '必填' }],
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
