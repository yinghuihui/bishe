import React from 'react'
import {  Button, Form, Input, Row , Col, Select  } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
let FromBox = React.createClass({
    render() {
        let { getFieldProps } = this.props.form;
        let props = this.props;
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 12
            },
        };
        
        return (
           <Form horizontal form={this.props.form}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden" />
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="审批意见:">
                        {props.title != "查看" ? (
                        <Select  {...getFieldProps('state1', { initialValue: "27" }) } disabled={!props.canEdit}>
                            <Option value="27">人工复审拒绝</Option>
                            <Option value="26">人工复审通过</Option>
                        </Select>) : (<Input type="text" disabled={!props.canEdit} {...getFieldProps('stateStr') } />)}
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="24">
                    <FormItem  {...formItemLayout} label="备注说明:">
                        <Input disabled={!props.canEdit} type="textarea" placeholder="" rows={4} style={{ width: "500px", height: "40px" }}   {...getFieldProps('remark', { initialValue: '',rules:[{ max: 50,message: '不能超过50个字符' }] }) } />
                    </FormItem>
                    </Col>
                </Row>
            </Form> 
        )
    }
})
FromBox = createForm()(FromBox);
export default FromBox