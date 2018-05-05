import React from "react";
import { Button, Modal } from "antd";
const str = ['livingImg','frontImg','backImg']
var Show = React.createClass({
  getInitialState() {
    return {
        first: true,
        tranZ: 0
    };
  },
  handleCancel() {
    //console.log(11111111111)
    this.setState({
      first: true
    })
    this.props.hidden();
  },
   componentWillReceiveProps(nextProps) {
    if (nextProps.visible && this.state.first) {
      this.setState({
        first: false,
        num: nextProps.num
      })
    }
  },
  change(){
    var num = this.state.num;
    num = num == 2 ? 0 : num + 1;
    this.setState({
      num: num
    })
  },
  scrolling(e){
    var tranZ = this.state.tranZ;
    e = e || window.event;
    if (e.deltaY < 0) {
      //当滑轮向上滚动时
      tranZ = tranZ >=900 ? tranZ : tranZ+50;
    }else {
      //当滑轮向下滚动时
      tranZ = tranZ <= -200 ? tranZ : tranZ-50;
    }
    this.setState({
      tranZ
    })
  },
  render() {
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <Button key="back" className="ant-btn" onClick={this.handleCancel}>
        返 回
      </Button>
    ];
    const formItemLayout = {
      labelCol: {
        span: 5
      },
      wrapperCol: {
        span: 19
      }
    };
    return (
      <Modal
        title={props.title}
        visible={props.visible}
        onCancel={this.handleCancel}
        width="800"
        footer={modalBtns}
        maskClosable={false}
      >

        <div id='div' onWheel={this.scrolling} style={{ position: "relative", width: 500, height: 500, margin: '0 auto', overflow: 'hidden',perspective: 1000 }}>
              {props.recordSoure ? <img id='img' onClick={this.change} src={props.recordSoure.userbase[str[state.num]]} style={{ position: "absolute",transition: 'all 0.5s',width: 200,left:0,right:0,top:0,bottom:0, margin: 'auto',transform: 'translateZ('+this.state.tranZ+'px)' }} /> : null}
        </div>
      </Modal>
    );
  }
});
export default Show;
