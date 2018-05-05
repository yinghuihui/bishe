import React from 'react';
import {
  Table,
} from 'antd';
const objectAssign = require('object-assign');
var Tab7 = React.createClass({
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
    if(nextProps.activekey == '7'){
      this.setState({
        data: []
      },() => {
        this.fetch();
      })
    }
  },
  scrolling(){
    var scrollTop = document.getElementById('scrolling').scrollTop;
    var height = document.getElementById('scrolling').scrollHeight;
    var allCurrent = Math.ceil(this.state.pagination.total / 10);
    if( 300 + scrollTop >= height && this.state.pagination.current != allCurrent ){
      var params = {};
      params = {
        pageSize: 10,
        current: this.state.pagination.current + 1,
        borrowId: this.props.record.id,
      }
      this.setState({
        pagination: {
          current: this.state.pagination.current + 1,
          total: this.state.pagination.total,
          pageSize: this.state.pagination.pageSize
        }
      })
      this.fetch(params);
    }
  },
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {
        pageSize: 10,
        current: 1,
        borrowId: this.props.record.id,
      }
    }
    var data = this.state.data;
    Utils.ajaxData({
      url: '/modules/manage/tongdunFraudHitRule/getDetails.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        // pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        if(data[0]){
          data = data.concat(result.data.rules);
        }else{
          data = result.data.rules;
        }
        this.setState({
          loading: false,
          data: data,
          finalData:result.data,
          pagination
        });
      }
    });
  },
  render() {
    var columns = [{
      title: '规则信息',
      dataIndex: "name",
    }, {
      title: '该条规则决策结果',
      dataIndex: "decision",
    }, {
      title: '规则分数',
      dataIndex: "score",
    }];
    var finalData = this.state.finalData;
    return (<div className="block-panel">
              <div>
                  <span style={{ marginRight: 15 }}>风险分数：{finalData?finalData.finalScore:""}</span>
                  <span >决策结果：{finalData?finalData.finalDecision:""}</span>
                </div>
              <Table columns={columns} rowKey={this.rowKey}  
                      dataSource={this.state.data}
                      pagination={false}
                      loading={this.state.loading}
                      onChange={this.handleTableChange}  />
          </div>
    );
  }
});
export default Tab7;