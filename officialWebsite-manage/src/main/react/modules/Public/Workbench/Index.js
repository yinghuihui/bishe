import React from 'react';
import { Row, Col } from 'antd';
import './style.css';
import Map from './Map';
import Bar1 from './Bar1';
import Bar2 from './Bar2';
import Pie1 from './Pie1';
import Pie2 from './Pie2';
import MenuDataStore from '../../frame/stores/MenuDataStore';
export default React.createClass({
    getInitialState() {
        return {
            menuData: [],
            assetsData: {},
            investmentData: {},
            loading: false,
            data: [],
            userMessage: {},
        }
    },
    fetch() {
        var me = this;
        this.setState({
            loading: true
        });      
        // Utils.ajaxData({
        //     url: '/modules/manage/count/homeInfo.htm',
        //     method: "get",
        //     callback: (result) => {
        //         me.setState({
        //             loading: false,
        //             data: result.data,
        //         });
        //     }
        // });
    },
    onWindowResize(){
        if(document.getElementById('box').offsetWidth < 1376){
            this.setState({
                change: false
            })
        }else{
            this.setState({
                change: true
            })
        }
        this.forceUpdate();
    },
    componentDidMount() {
        this.fetch();
        window.addEventListener('resize', this.onWindowResize);
    },
    componentWillUnmount() {
        window.removeEventListener('resize', this.onWindowResize)
    },
    render() {
        var { data,change } = this.state;
        
        if(typeof(change) == 'undefined' ){
            var box = document.getElementById('box')?document.getElementById('box'):{}
            if(box.offsetWidth < 1376){
                change = false;
            }else{
                change = true;
            }
        }
        return (
            <div id='box' >
                {/**<div className="block-panel">
                    <h2 className="navLine-title">今日数据</h2>
                    <div className='blk-top'>
                        <div className='blk-top-item'>
                            <div className='blk-title'>当天注册数</div>
                            <div className='blk-number'>{data.register}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>借款申请数</div>
                            <div className='blk-number'>{data.borrow}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>通过次数</div>
                            <div className='blk-number'>{data.borrowPass}</div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>通过率</div>
                            <div className='blk-number'>{data.passApr}<span style={{ fontSize: '12px' }}>% </span></div>
                        </div>
                        <div className='blk-top-item'>
                            <div className='blk-title'>放款量</div>
                            <div className='blk-number'>{data.borrowLoan}</div>
                        </div>
                        <div className='blk-top-item blk-top-item-last' >
                            <div className='blk-title'>还款量</div>
                            <div className='blk-number'>{data.borrowRepay}</div>
                        </div>
                    </div>
                </div>
                <div>
                    <div className="data-panel">
                        <div className="block-panel">
                            <h2 className="navLine-title">累计数据</h2>
                            <Row>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon1'> </span>
                                        <span className='blk-title'>历史放款总量</span>
                                        <span className='blk-number'>{data.borrowLoanHistory}</span>笔
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon2'> </span>
                                        <span className='blk-title'>历史还款总量</span>
                                        <span className='blk-number'>{data.borrowRepayHistory}</span>笔
                                    </div>
                                </Col>
                            </Row>
                        </div>
                    </div>
                    <div className="data-panel">
                        <div className="block-panel">
                            <h2 className="navLine-title">实时数据</h2>
                     
                            <Row>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon3'> </span>
                                        <span className='blk-title'>待还款总金额</span>
                                        <span className='blk-number'>{data.needRepay}</span>元
                                    </div>
                                </Col>
                                <Col span='12'>
                                    <div className='blk-bottom'>
                                        <span className='workBench-icon icon4'> </span>
                                        <span className='blk-title'>逾期未还款总额</span>
                                        <span className='blk-number'>{data.overdueRepay}</span>元
                                    </div>
                                </Col>
                            </Row>
                        </div>
                    </div>
                </div>
                { change ? <div className="block-chart">
                    <div className='blk-top'>
                        <div className='blk-top-item'>
                            <Map data={data} />
                            <div className="border-top">
                                <Pie2 data={data} />
                            </div>
                        </div>
                        <div className='blk-top-item blk-top-item-last'>
                            <Bar1 data={data} />
                            <Bar2 data={data} />
                        </div>
                    </div>
                </div> : <div className="block-chart">
                    <div className='blk-top-change'>
                            <div>
                                <Map data={data} />
                            </div>
                            <div>
                                <Pie2 data={data} />
                            </div>
                            <div>
                                <Bar1 data={data} />
                                <Bar2 data={data} />
                            </div>
                    </div>
                </div>} */}
            </div>
        )
    }
});
