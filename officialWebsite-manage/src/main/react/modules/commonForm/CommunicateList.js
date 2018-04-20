//通讯录
import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var CommunicateList = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {},
    };
  },
  rowKey(record) {
    return record.id;
  },
  componentWillReceiveProps(nextProps){
    if(nextProps.visible){
      this.fetch();
    }
  },
  componentDidMount(){
    this.fetch();
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        userId: this.props.record.id,
      }
    }
    Utils.ajaxData({
      url: '/modules/manage/msg/listContacts.htm',
      data: params,
      callback: (result) => {
        var resultData = {};
        resultData = result.data?result.data:{};
        this.setState({
          loading: false,
          data: resultData.contacts?JSON.parse(resultData.contacts):[],
        });
      }
    });
  },
  render() {
    var columns = [{
      title: '姓名',
      dataIndex: "name",
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '通话次数',
      dataIndex: "callCount",
    }];
    return (<div className="block-panel">
              <Table columns={columns} rowKey={this.rowKey}  
              dataSource={this.state.data}
              pagination={{ pageSize: 20 }}
              loading={this.state.loading}  />
          </div>
    );
  }
});
export default CommunicateList;