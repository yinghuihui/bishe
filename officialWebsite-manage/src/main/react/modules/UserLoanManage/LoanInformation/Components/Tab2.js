import React from 'react';
import {
  Table,
  Form,
  Input,
  Row,
  Col,
  Button,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const objectAssign = require('object-assign');
var Tab2 = React.createClass({
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
    if(nextProps.activeKey1 == '4' && nextProps.tabClick){
      this.props.tabClickChange();
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
        userId: this.props.record.userId,
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
        userId: this.props.record.userId,
      }
    }
    var data = this.state.data;
    Utils.ajaxData({
      url: '/modules/manage/msg/listContacts.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.current;
        pagination.pageSize = params.pageSize;
        pagination.total = result.page.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        if(data[0]){
          data = data.concat(result.data.list);
        }else{
          data = result.data.list;
        }
        this.setState({
          loading: false,
          data: data,
          pagination
        });
      }
    });
  },

  handleQuery(){
    var params = this.props.form.getFieldsValue();
    if (!params.pageSize) {
      params.pageSize = 10;
      params.current = 1;
    }
    Utils.ajaxData({
      url: '/modules/manage/msg/listContacts.htm',
      data: params,
      callback: (result) => {
        this.setState({
          loading: false,
          data: result.data?result.data.list:[],
        });
      }
    });
  },

  handleReset(){
    this.props.form.resetFields();
    this.fetch();
  },

  render() {
    var props = this.props;
    var state = this.state;
    const {
        getFieldProps
    } = this.props.form;
    const formItemLayout = {
            labelCol: {
                span: 9
            },
            wrapperCol: {
                span: 14
            },
        };
    var columns = [{
      title: '姓名',
      dataIndex: "name",
    }, {
      title: '手机号码',
      dataIndex: "phone",
    }, {
      title: '通话次数',
      dataIndex: "totalCount",
    }, {
      title: '通话总时长（秒）',
      dataIndex: "sumDuration",
    }];
    return (<div className="block-panel">
              <div>
                <Input {...getFieldProps('userId', { initialValue: this.props.record.userId }) } type="hidden" />
                  <Row>
                    <Col span="8">
                      <FormItem {...formItemLayout} label="姓名">
                        <Input {...getFieldProps('name', { initialValue: '' }) }  />
                      </FormItem>
                    </Col>
                    <Col span="8">
                      <FormItem {...formItemLayout} label="手机号码：">
                        <Input {...getFieldProps('phone', { initialValue: '' }) }  />
                      </FormItem>
                    </Col>
                    <Col {...formItemLayout} span="8">
                      <FormItem>
                          <Button type="primary" onClick={this.handleQuery} style={{marginRight:'10'}}>查询</Button>
                          <Button type="reset" onClick={this.handleReset} >重置</Button>
                      </FormItem>
                    </Col>
                  </Row>
                </div>
              <div id='scrolling' onScroll={this.scrolling} style={{height: 300,  overflow: 'scroll'}}>
                <Table columns={columns} rowKey={this.rowKey}  
                dataSource={this.state.data}
                pagination={false}
                loading={this.state.loading} />
              </div>
          </div>
    );
  }
});
Tab2 = createForm()(Tab2);
export default Tab2;