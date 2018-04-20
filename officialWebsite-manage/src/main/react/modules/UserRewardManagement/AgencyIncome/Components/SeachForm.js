import React from 'react';
import {Button, Form, Input, Select,Message,DatePicker } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const RangePicker = DatePicker.RangePicker;
let SeachForm = React.createClass({
    getInitialState() {
        return {
            roleList: []
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        var data = {endTime:'',startTime:'',phone:params.phone,name:params.name};
        if(params.Time){
            data.startTime = (DateFormat.formatDate(params.Time[0]));
            data.endTime = (DateFormat.formatDate(params.Time[1]));
        }else{
            data.startTime = DateFormat.formatCountDate(-10);
            data.endTime = DateFormat.formatDate(new Date());
        }
        this.props.passParams({
            search: JSON.stringify(data),
            current: 1,
        });
    },
    handleReset() {
        this.props.form.resetFields();
        var search = {};
        search.endTime = DateFormat.formatDate(new Date());
        search.startTime = DateFormat.formatCountDate(-10);
        this.props.passParams({
            search: JSON.stringify(search),
            current: 1,
        });
    },

    disabledDate(startValue) {
        var today = new Date();
        return startValue.getTime() > today.getTime();
    },
    render() {

        const {getFieldProps} = this.props.form;

        return (
            <Form inline>
                <FormItem label="姓名:">
                    <Input  {...getFieldProps('name')} />
                </FormItem>
                <FormItem label="手机号码:">
                    <Input  {...getFieldProps('phone')} />
                </FormItem>
                <FormItem label="时间:">
                    <RangePicker disabledDate={this.disabledDate} {...getFieldProps('Time') } />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;