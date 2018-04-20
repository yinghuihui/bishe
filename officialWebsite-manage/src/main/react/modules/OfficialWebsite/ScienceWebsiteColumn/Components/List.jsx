import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddMenu from './AddMenu'
var confirm = Modal.confirm;
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 10,
      },
      expandedRowKeys: [],
      canEdit: true,
      visible: false,
    };
  },
  componentDidMount() {
    this.fetch();
  },
  hideModal() {
    this.setState({
      visible: false,
      detailWin: false,
      selectedRowKeys: [],
    });

    this.fetch();
  },
  //详情弹窗
  showDtail(title, record, canEdit) {
    this.setState({
      canEdit: canEdit,
      detailWin: true,
      title: title,
      record: record
    });
  },
  rowKey(record) {
    return record.value;
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    const pager = this.state.pagination;
    pager.current = pagination.current;
    pager.pageSize = pagination.pageSize;

    this.fetch({
      pageSize: pagination.pageSize,
      current: pagination.current
    });
  },
  fetch(params = {
    pageSize: this.state.pagination.pageSize,
    current: 1
  }) {
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
    if (!params.searchParams) {
      params.searchParams = JSON.stringify({remark:'20'})
    }
    Utils.ajaxData({
      url: '/modules/manage/programa/list.htm',
      data: params,
      method:'get',
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.total = 1;
        if (!pagination.current) {
          pagination.current = 1
        };
        var data = result.data;
        let root = [{
          programaName:'栏目目录',
          label:'栏目目录',
          value:"0",
          id:'0',
          //type:10,
          children:data
        }];
        this.setState({
          loading: false,
          data: root,
          pagination,
          selectedRowKeys: []
        }); 
      }
    });
  }, 
  refreshList() {
    this.fetch();
  },

  showAddModal(title, canEdit) { 
    var me = this;
    var record = me.state.selectedRows[0];
    record.text = record.label;
    //console.log(record)
    if (title == '修改') {
      record.parentId=record.parentId+'';
      record.id=record.value;
      record.state=String(record.state);
      record.programaName = record.label;
      this.refs.AddMenu.setFieldsValue(record);
    } else if (title == '添加') {
      this.refs.AddMenu.setFieldsValue({
        parentId: record.value
      });
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title
    });
  },

  getExpandeRowKeys(data) {
    var me = this;
    var expandedRowKeys = [];

    data.length && data.forEach(function(record, i) {
      if (record.children) {
        expandedRowKeys.push(me.rowKey(record));
        expandedRowKeys = expandedRowKeys.concat(me.getExpandeRowKeys(record.children));
      }
    });

    return expandedRowKeys;
  },
  expandedAll() {
    var data = this.state.data;
    var expandedRowKeys = this.getExpandeRowKeys(data);

    function sortNumber(a, b) {
      return a - b
    }
    this.setState({
      expandedRowKeys: expandedRowKeys.sort(sortNumber).reverse()
    });
    //console.log(expandedRowKeys.sort(sortNumber).reverse());
  },
  collapseAll() {
    this.setState({
      expandedRowKeys: []
    });
  },
  onExpandedRowsChange(expandedRowKeys) {
    this.setState({
      expandedRowKeys: expandedRowKeys
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
            trueurl = "/modules/manage/programa/enable.htm"
        } else if (title == '禁用') {
            msg = '禁用成功';
            status = "20";
            tips = '您是否禁用';
            trueurl = "/modules/manage/programa/disable.htm"
        }
        confirm({
            title: tips,
            onOk: function() {
                Utils.ajaxData({
                    url: trueurl,
                    data: {
                        id: record.value,
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

  onRowClick(record) { 
    this.setState({
      selectedRowKeys: [record.value],
      selectedRows:[record]
    });
  },
  render() {
    var me = this;
    var state = this.state;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {  
      selectedRowKeys
    };
    const hasSelected = selectedRowKeys.length > 0;
    let openEdit = true;
    if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
       openEdit =false;
    }
    var columns = [{
      title: '栏目名字',
      dataIndex: 'label'
    }, {
      title: '关键字',
      dataIndex: 'programaKeyword'
    }, {
      title: '链接地址',
      dataIndex: 'href'
    }, {
        title: '外部',
        dataIndex: 'hrefType'
    }, {
      title: '排序',
      dataIndex: "sort"
    }, {
      title: '备注',
      dataIndex: "remark"
    }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
                  if(record.state =='10'){
                    return <a href="#" onClick={me.changeStatus.bind(me ,record,'禁用')}>禁用</a>
                  }else if(record.state =='20'){
                    return <a href="#" onClick={me.changeStatus.bind(me ,record,'启用')}>启用</a> 
                  }    
      }
    }];
    return (
      <div className="block-panel"> 
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <button className="ant-btn" onClick={this.expandedAll.bind(this,'展开所有',false)}>
                 展开所有
               </button>
               <button className="ant-btn" onClick={this.collapseAll.bind(this,'收缩所有',false)}>
                 收缩所有
               </button>
               <button className="ant-btn" onClick={this.refreshList.bind(this,'刷新',false)}>
                 刷新
               </button>
               <button className="ant-btn" disabled={openEdit} onClick={this.showAddModal.bind(this,'修改',true)}>
                 修改
               </button>
               <button className="ant-btn" disabled={!hasSelected} onClick={this.showAddModal.bind(this,'添加',true)}>
                 添加                   
               </button> 
             
           </div>   
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  ref="table" 
             rowSelection={rowSelection}
             onRowClick={this.onRowClick}
             dataSource={this.state.data} 
             expandedRowKeys={this.state.expandedRowKeys} 
             onExpandedRowsChange={this.onExpandedRowsChange}
             loading={this.state.loading} pagination={false}  />
            <AddMenu ref="AddMenu"  visible={state.visible}  officeData={this.state.data}  title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  }
})
