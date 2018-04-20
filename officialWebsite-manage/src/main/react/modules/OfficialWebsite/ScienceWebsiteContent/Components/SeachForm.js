import React from 'react';
import {
    Button,
    Form,
    Input,
    Select,
    DatePicker,
    Radio,
    TreeSelect,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
let SeachForm = React.createClass({
    getInitialState() {
        return {
        }
    },
    handleQuery() {
        var params = this.props.form.getFieldsValue();
        params.remark = '20';
        this.props.passParams({
            searchParams:JSON.stringify(params),
            current: 1
        });
    },
    handleReset() {
        this.props.form.resetFields();
        this.props.passParams({
            current: 1
        });
    },

    componentDidMount() {
        let treeData = [];
        var data = {};
        data.remark = '20';
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
    render() {
        const {
            getFieldProps
        } = this.props.form;
        const { treeData } = this.state;
        return (
            <Form inline>
                <FormItem  label="栏目名称：">
                        <TreeSelect  style={{width:200}} dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                        {...getFieldProps('programaId',{rules:[{ required: true, message: '必填'}]})}
                        placeholder="请选择" treeDefaultExpandAll />
                </FormItem> 
                <FormItem label="标题:">
                    <Input type="text" placeholder='请输入标题' {...getFieldProps('title') } />
                </FormItem>
                <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
                <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
            </Form>
        );
    }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;/**
 * Created by WIN10 on 2016/10/12.
 */
