import React from 'react';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Row,
  Col,
  Select,
  Checkbox,
  Radio,
  message,
  DatePicker,
  Table

} from 'antd';
import './border.css'

const CheckboxGroup = Checkbox.Group
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Tab10 = React.createClass({
  getInitialState() {
    return {
      data: {
        tradeCount: [],
        addresses: [],
        historyAddresses: []
      }
    };
  },
  componentDidMount() {
    this.fetch();
  },
  fetch(){
    Utils.ajaxData({
      url: '/modules/manage/ecommerce/list.htm',
      data: {
        userId: this.props.record.userId,
      },
      callback: (result) => {
        if(result.code == 200){
          if(!!result.data.taobaoOrders){
            result.data.taobaoOrders.forEach(item => {
              if(!!item.subOrders){
                item.subOrders = JSON.parse(item.subOrders)
              }
              if(!!item.address){
                item.address = JSON.parse(item.address)
              }
              if(!!item.logistics){
                item.logistics = JSON.parse(item.logistics)
              }
            });
          }
          this.setState({
            data: result.data
          }, () => {          
            if(!!result.data.baseInfo){
              this.props.form.setFieldsValue(result.data.baseInfo);
            }
          })
        }
      }
    });
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;


    const formItemLayout = {
      labelCol: {
        span: 9
      },
      wrapperCol: {
        span: 15
      },
    };
    const columns = [{
        title: '月份',
        dataIndex: 'yearMonth'
    }, {
        title: '消费笔数',
        dataIndex: 'count'
    }, {
        title: '消费金融',
        dataIndex: "amount"
    }]
    const columns1 = [{
        title: '姓名',
        dataIndex: 'receiveName'
    }, {
        title: '地址',
        dataIndex: 'address'
    }, {
        title: '邮编',
        dataIndex: "postCode"
    }, {
        title: '手机号',
        dataIndex: "telNumber"
    }]
    const columns2 = [{
        title: '姓名',
        dataIndex: 'name'
    }, {
        title: '地址',
        dataIndex: 'address'
    }, {
        title: '手机号1111',
        dataIndex: "phone"
    }, {
        title: '总消费金额',
        dataIndex: "amount"
    }, {
        title: '最近送货时间',
        dataIndex: 'tradeTime'
    }, {
        title: '地址出现时间',
        dataIndex: 'tradeTime'
    }]
    const columns3 = [{
        title: '商品id',
        dataIndex: 'itemId'
    }, {
        title: '商品名称',
        dataIndex: 'itemTitle'
    }, {
        title: '商品单价',
        dataIndex: "actual"
    }, {
        title: '商品数量',
        dataIndex: "quantity"
    }]
    return (
        <Form className='special' horizontal form={this.props.form} style={{border: '1px solid #ccc', marginTop:20,display:this.props.visibleShow?"none":"block", marginBottom:20}}>
          <div className='headText'>基本信息</div>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="真实姓名:">
                <Input disabled={true} type="text" {...getFieldProps('name', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="登录邮箱:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('email', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="身份证号:">
                <Input disabled={true} type="text" {...getFieldProps('identityCard', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="绑定手机:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('mobile', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="用户名:">
                <Input disabled={true} type="text" {...getFieldProps('taobaoAccount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="会员等级:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('coordinate', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="昵称:">
                <Input disabled={true} type="text" {...getFieldProps('primaryAmount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="成长值:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('taoScore', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="性别:">
                <Input disabled={true} type="text" {...getFieldProps('fee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="信用积分:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('creditLevelAsBuyer', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="出生日期:">
                <Input disabled={true} type="text" {...getFieldProps('infoAuthFee', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="好评率:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('interest', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem {...formItemLayout} label="认证渠道:">
                <Input disabled={true} type="text" {...getFieldProps('realAmount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="安全等级:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('createTime', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="是否实名认证:">
                <Input disabled={true} type="text" {...getFieldProps('isVerifiedStr', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="消失" className='noText'>
                <Input disabled={true} type="text" />
              </FormItem>
            </Col>
          </Row>
          <div className='headText needTop'>绑定支付宝信息</div>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="支付宝账号:">
                <Input disabled={true} type="text" {...getFieldProps('alipayAccount', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实名认证姓名:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('name', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="邮箱:">
                <Input disabled={true} type="text" {...getFieldProps('email', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实名认证身份证号:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('identityCard', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="绑定手机:">
                <Input disabled={true} type="text" {...getFieldProps('mobile', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="实名认证状态:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('isVerifiedStatus', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="账户余额:">
                <Input disabled={true} type="text" {...getFieldProps('alipayBalance', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="余额宝余额:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('yuebaoBalance', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="花呗当前可用余额:">
                <Input disabled={true} type="text" {...getFieldProps('huabeiBalance', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem {...formItemLayout} label="花呗总额度:"  className='noRight'>
                <Input disabled={true} type="text" {...getFieldProps('huabeiAmount', { initialValue: '' }) } />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="余额宝历史累计收益:">
                <Input disabled={true} type="text" {...getFieldProps('yuebaoIncome', { initialValue: '' }) } />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="消失" className='noText'>
                <Input disabled={true} type="text" />
              </FormItem>
            </Col>
          </Row>
          <div className='headText needTop'>按月消费数据统计</div>
          <Table   columns={columns} ref="table"
                    dataSource={this.state.data.tradeCount}
                    pagination={false}
                    onChange={this.handleTableChange}
          />
          <div className='headText'>收货地址</div>
          <Table   columns={columns1} ref="table1"
                    dataSource={this.state.data.addresses}
                    pagination={false}
                    onChange={this.handleTableChange}
          />
          <div className='headText'>历史地址</div>
          <Table   columns={columns2} ref="table2"
                    dataSource={this.state.data.historyAddresses}
                    pagination={false}
                    onChange={this.handleTableChange}
          />
          <div className='headText'>订单详情</div>
          {
            !!state.data.taobaoOrders ? state.data.taobaoOrders.map((item, index) => {
              return (
                <div className='text'>
                  <div className='orderTitle title'>{++index}订单号:{item.orderNumber}</div>
                  <div  className='clear'>
                    <div className='four'>订单金额:{item.actualFee}</div>
                    <div className='six'>订单时间:{item.createTime}</div>
                  </div>
                  <div className='clear'>
                    <div className='four'>订单状态:{item.tradeStatusName}</div>
                    <div className='six'>收货人信息:{!!item.address ? item.address.receiveName + ' ' + item.address.telNumber + ' ' + item.address.region + ' ' + item.address.address : ''}</div>
                  </div>
                  <div className='orderTitle title'>购买商品信息</div>
                  <Table   columns={columns3} ref="table3"
                    dataSource={item.subOrders}
                    pagination={false}
                    onChange={this.handleTableChange}
                  />
                  <div className='title'>物流信息</div>
                  <div className='ul'>
                    <ul>
                      <li>运送方式</li>
                      <li>物流公司</li>
                      <li className='noBorder'>送货单号</li>
                    </ul>
                    <ul>
                      <li></li>
                      <li>{!!item.logistics ? item.logistics.companyName : ''}</li>
                      <li className='noBorder'>{!!item.logistics ? item.logistics.postManPhone : ''}</li>
                    </ul>
                  </div>
                </div>
              )
            }) : ''
          }
        </Form>
    );
  }
});
Tab10 = createForm()(Tab10);
export default Tab10;