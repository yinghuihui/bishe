import React from 'react'
import { Table, Modal, Icon, Button } from 'antd';
const objectAssign = require('object-assign');
import AddWin from "./AddWin";
var confirm = Modal.confirm;
export default React.createClass({
    getInitialState() {
        return {
            selectedRowKeys: [], // 这里配置默认勾选列
            loading: false,
            data: [],
            pagination: {
                pageSize: 10,
                current: 1
            },
            canEdit: true,
            visible: false,
            rowRecord: [],
            dataRecord: '',
            record: "",

        };
    },

    componentWillReceiveProps(nextProps, nextState) {
        var pagination = this.state.pagination;
        nextProps.params.pageSize = pagination.pageSize;
        this.fetch(nextProps.params);
    },

    componentDidMount() {
        this.fetch();
        []
    },

    fetch(params = {}) {
        this.setState({
            loading: true
        });
        if (!params.pageSize) {
            var params = {};
            params = {
                pageSize: this.state.pagination.pageSize,
                current: 1
            }
        }
        Utils.ajaxData({
            url: '/modules/manage/messageTpl/messageTplList.htm',
            method: "post",
            data: params,
            callback: (result) => {
                const pagination = this.state.pagination;
                pagination.current = params.current;
                pagination.pageSize = params.pageSize;
                pagination.total = result.page.total;
                if (!pagination.current) {
                    pagination.current = 1
                }
                ;
                this.setState({
                    loading: false,
                    data: result.data,
                    pagination
                });
            }
        });
    },

    //新增
    showModal(title, record, canEdit) {
        var record = record;
       var me = this;
        this.setState({
            visible: true,
            canEdit: true,
            record: record,
            title: title
        }, () => {
        if(title == '编辑'){
            me.refs.AddWin.setFieldsValue(me.state.record);
        }
        })
    },
    //隐藏弹窗
    hideModal() {
        this.setState({
            visible: false,
            selectedIndex: '',
            selectedRowKeys: [],
            dataRecord: ''
        });
        this.refreshList();
    },
    rowKey(record) {
        return record.id;
    },

    //选择
    onSelectChange(selectedRowKeys) {
        this.setState({
            selectedRowKeys
        });
    },

    //分页
    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        pager.pageSize = pagination.pageSize;
        this.setState({
            pagination: pager,
        });
        this.refreshList();
    },

    refreshList() {
        var pagination = this.state.pagination;
        var params = objectAssign({}, this.props.params, {
            current: pagination.current,
            pageSize: pagination.pageSize
        });
        this.fetch(params);
    },

    //行点击事件
    onRowClick(record) {
        //console.log(record)
        this.setState({
            selectedRowKeys: [record.id],
            selectedRow: record,
            rowRecord: record
        }, () => {
            this
        });
    },

    // 列表添加选中行样式
    rowClassName(record) {
        let selected = this.state.selectedIndex;
        //console.log('selected', this.state.selectedIndex)
        return (record.id == selected && selected !== '') ? 'selectRow' : '';

    },

    //选择
    onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
        if (selected) {
            this.setState({
                selectedRowKeys
            })
        } else {
            this.setState({
                selectedRowKeys: []
            })
        }
    },

    changeStatus(record,title) {
        var record=record;
        var me = this;
        var status = record.state == '10'?'20':'10';
        var tips = record.state == '10'?'您是否禁用':'您是否启用';
        confirm({
            title: tips,
            onOk: function() {
                Utils.ajaxData({
                    url: '/modules/manage/messageTpl/updateSelective.htm',
                    data: {
                        id: record.id,
                        state:status
                    },
                    method: 'post',
                    callback: (result) => {
                        if (result.code == 200) {
                            Modal.success({
                                title: result.msg,
                            });
                            me.refreshList();
                        } else {
                           Modal.error({
                                title: result.msg,
                            });
                        }
                        
                    }
                });
            },
            onCancel: function() { }
        });
    },

    render() {
        const {
            loading,
            selectedRowKeys
            } = this.state;
        const rowSelection = {
            type: 'checkbox',
            selectedRowKeys,
            onSelectAll: this.onSelectAll,
        };
        let me = this;
        const hasSelected = selectedRowKeys.length > 0;
        let openEdit = true;
        if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
            openEdit = false;
        }
        var columns = [{
            title: '模板名称',
            dataIndex: 'typeName'
        }, {
            title: '模板类型',
            dataIndex: 'type'
        }, {
            title: '模板内容',
            dataIndex: 'tpl'
        }, {
            title: '模板状态',
            dataIndex: 'state',
            render:(text,record)=>{
                return text == '10'?'启用':'禁用'
            }
        }, {
            title: '操作',
            dataIndex: "",
            render: (value, record) => {
                return (
                  <div style={{ textAlign: "left" }}>
                        <a href="#" onClick={me.showModal.bind(me, '编辑',record, true)}>编辑</a>
                        <span className="ant-divider"></span>    
                         {record.state=="20"?<a href="#" onClick={me.changeStatus.bind(me ,record,'启用')}>启用</a>:<a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>}  
                   </div>
                )
            }
        }];

        var state = this.state;
        return (
            <div className="block-panel">
                <div className="actionBtns" style={{ marginBottom: 16 }}>
                    <Button onClick={me.showModal.bind(me, '新增')}>
                        新增
                    </Button>
                </div>
                <Table columns={columns} rowKey={this.rowKey} ref="table"
                    onRowClick={this.onRowClick}
                    dataSource={this.state.data}
                    rowClassName={this.rowClassName}
                    pagination={this.state.pagination}
                    onChange={this.handleTableChange}
                />
                <AddWin ref="AddWin" dataRecord={state.dataRecord} visible={state.visible} hideModal={me.hideModal} title={state.title} canEdit={state.canEdit} record={state.record} />
            </div>
        );
    }
})
