import React from 'react'
import {
  Table,
  Modal,
  Select
} from 'antd';
import AddWin from './AddWin'
var confirm = Modal.confirm;
const Option = Select.Option;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 10,
      },
      canEdit: true,
      visible: false,
      value: ''
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.clearSelectedList();
    var pagination = this.state.pagination;
    nextProps.params.pageSize = pagination.pageSize;
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      value: '',
      title: '',
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record,
    },()=>{
      if (title =="编辑" || title =="查看") {
        this.refs.AddWin.setFieldsValue(record);
      }
    });
  },
  rowKey(record) {
    return record.id;
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
      url: '/modules/manage/phoneLocation/page.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current || result.page.current == 1) {
          pagination.current = 1
        } else {
          pagination.current = result.page.current
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination
        });
      }
    });
  },
  clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
      selectedrecord: null
    });
  },
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      current: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },

  componentDidMount() {
    this.fetch();
  },

  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedrecord: record
    });
  },

  delete(record) {
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/manage/phoneLocation/delete.htm",
          data: {
            id: Number(record.id),
          },
          method: 'post',
          callback: (result) => {
            Modal.success({
              title: result.msg,
            });
            me.refreshList();
          }
        });
      },
    });
  },

  changeStatus(record,title) {
        var record=record;
        var me = this;
        var status;
        var msg = "";
        var tips = "";
        var trueurl = "";
        if (title == '启用') {
            msg = '启用成功';
            status = "10";
            tips = '您是否启用';
        } else if (title == '禁用') {
            msg = '禁用成功';
            status = "20";
            tips = '您是否禁用';
        }
        confirm({
            title: tips,
            onOk: function() {
                Utils.ajaxData({
                    url: '/modules/manage/phoneLocation/updateState.htm',
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
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,

    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [ {
      title: '号段',
      dataIndex: 'phoneSectionNo'
    }, {
      title: '运营商',
      dataIndex: 'operator'
    }, {
      title: '省份',
      dataIndex: 'province'
    }, {
      title: '城市',
      dataIndex: 'city'
    }, {
      title: '状态',
      dataIndex: 'stateStr'
    }, {
      title: '创建时间',
      dataIndex: 'createTime'
    }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
        return <div >
                  <a href="#" onClick={me.showModal.bind(me, '查看', record, false)}>查看 </a>&nbsp;
                  <span className="ant-divider"></span>&nbsp;
                  <a href="#" onClick={me.showModal.bind(me, '编辑', record, true)}>编辑</a>&nbsp; 
                  <span className="ant-divider"></span>&nbsp;
                  <a href="#" onClick={me.delete.bind(me, record)}>删除 </a>&nbsp;   
                  <span className="ant-divider"></span>&nbsp;
                  {record.state=="20"?
                      <a href="#" onClick={me.changeStatus.bind(me ,record,'启用')}>启用</a>:
                      <a href="#" onClick={me.changeStatus.bind(me,record,'禁用')}>禁用</a>} 
                </div>
      }
    }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" onClick={this.showModal.bind(this, '新增')}>
             新增
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}
          onRowClick={this.onRowClick} params={this.props.params}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange} />

        <AddWin ref="AddWin" value={state.value} visible={state.visible} title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
          canEdit={state.canEdit} />
      </div>
    );
  }
})
