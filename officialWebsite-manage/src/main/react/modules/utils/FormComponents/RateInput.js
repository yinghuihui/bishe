//百分号显示，默认两位小数
import React from 'react';
import antd from 'antd';
import { 
    Input, 
} from 'antd';
const RateInput = React.createClass({     
    
    getInitialState() {
        return { 
            rateValue:''
        }
    },
    componentWillReceiveProps (nextProps) {
        if(typeof nextProps.value == 'undefined'||nextProps.value=='NaN'||nextProps.value==null||nextProps.value=='null'||nextProps.value===''){
            this.setState({
                rateValue: '',
            })
            return
        }
        var isFloat = false;
        var value = nextProps.value ;
        var num = nextProps.digit?nextProps.digit:2;
        var rateValue = Number((parseFloat(nextProps.value)*10000/100).toFixed(num));
        if(typeof value =='string'){ 
            rateValue = value
        }
        this.setState({
            rateValue:rateValue,
        })
        
    },
    changeValue(e){
        var props = this.props;
        var  rateValue = e.target?e.target.value:e; 
        if (rateValue === "") {
            props.onChange("");
            return
        }
        var max=props.max?props.max:9.99;
        var min=props.min?props.min:0;
        var num = props.digit?props.digit:2;
        var value = Number((parseFloat(rateValue)*100/10000).toFixed(num+2));
        
        if(String(value) == 'NaN'){
            value =''
        } 
        else if(typeof value=='number'&&Number(value) > max){
            value =max
        } 
        else if(Number(value) < min){
            value =min
        }
        this.props.onChange(Number(value));     
    },
    /*componentDidMount() {
    },*/
    render() {
        var props = this.props;
        return  <Input {...props} value={this.state.rateValue} onBlur={this.changeValue} autoComplete="off" /> 
    },
});
export default RateInput ;