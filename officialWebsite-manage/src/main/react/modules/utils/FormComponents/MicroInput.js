//数字千分位加, 
import React from 'react';
import antd from 'antd';
import {
    Input,
} from 'antd';
var MicroInput = React.createClass({

    getInitialState() {
        return {
            microValue: '',
            isOperate:true
        }
    },
    componentWillReceiveProps(nextProps) { 
        if(this.state.isOperate){
            if (typeof nextProps.value == 'undefined' || nextProps.value == 'NaN' || nextProps.value==null || nextProps.value=="null" || nextProps.value ==='') { 
                this.setState({
                    microValue: ''
                })
                return
            } 
            var value = String(nextProps.value);
            var arr = value.split('.');
            var isFloat = arr.length == 2;
            if (isFloat) {
                var floatSection = arr[1];
                value = arr[0];//整数部分
            }


            var microValue = value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function (s) {
                return s + ',';
            });
            if (isFloat) {
                microValue = microValue + '.' + floatSection
            }
            this.setState({
                microValue,
                isOperate:false
            })
        }
    },
    componentDidMount() {
        var props = this.props; 
        if (typeof props.value == 'undefined' || props.value == 'NaN'|| props.value==null||props.value=="null"||props.value==='') { 
            this.setState({
                microValue: ''
            })
            return
        } 
        var value = String(props.value);
        var arr = value.split('.');
        var isFloat = arr.length == 2;
        if (isFloat) {
            var floatSection = arr[1];
            value = arr[0];//整数部分
        }


        var microValue = value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function (s) {
            return s + ',';
        });
        if (isFloat) {
            microValue = microValue + '.' + floatSection
        }
        this.setState({
            microValue
        })
    },
    changeValue(e) {//3位加逗号
        var props = this.props;
        var value = e.target?e.target.value:e;
        var arr = value.split('.');
        var isFloat = arr.length == 2;
        if (isFloat) {
            var floatSection = arr[1];
            value = arr[0];//整数部分
        }
        var microValue=value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function (s) {
            return s + ',';
        });;
        var microValueOld = value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function (s) {
            return s + ',';
        });
        if (isFloat) {
            microValue = microValueOld + '.' + floatSection
        }
        if (isFloat&&floatSection=="") {
            microValue = microValueOld
        }
        this.setState({
            microValue
        }) 
    },
    changeValue2(e){//数据处理
        var props = this.props;
        var value = e.target?e.target.value:e;
        value = value.trim();
        var max=(props.max||props.max==0)?props.max:9999999999.99;
        var min=(props.min||props.min==0)?props.min:0;
        var num = (props.digit||props.digit==0)?props.digit:2;
        
        var arr = value.split('.');
        var a;
        var decimal = arr[1]; 
        if(isNaN(arr[1])){
          decimal = parseInt(arr[1]);  
          if(String(decimal)=='NaN'){
            decimal=""
          }
        }    
        var value = parseInt(arr[0].split(',').join(''));
        if (arr.length == 2) {
            if (arr[1] === '') {
                value = String(value) + '.';
            }
            else{
                value = value + '.' + String(decimal).substr(0,num)
            } 
        }

        if(Number(value)>max){
            value = max
        };
        if(Number(value)<min){
            value = min
        };
        if (String(value) == 'NaN') {
            value = '';
        }
        if (String(value) == 'NaN.') {
            value = '';
        }
        this.props.onChange(Number(value)); 
        this.setState({
            microValue:value
        })
    },
    changeValue3(e){
        var props = this.props;
        var value = e.target?e.target.value:e;
        var num = (props.digit||props.digit==0)?props.digit:2;
        if(value){
            var arr = value.split('.');
            var a;
            var decimal = arr[1]; 
            if(isNaN(arr[1])){
              decimal = parseInt(arr[1]);  
              if(String(decimal)=='NaN'){
                decimal=""
              }
            }    
            var value = parseInt(arr[0].split(',').join(''));
            if (arr.length == 2) {
                if (arr[1] === '') {
                    value = String(value) + '.';
                }
                else{
                    value = value + '.' + String(decimal).substr(0,num)
                } 
            }
            this.setState({
                microValue:value
            })
        }
    },
    render() {
        var props = this.props; 
        return <Input {...props} value={this.state.microValue} onChange={this.changeValue2} onFocus={this.changeValue3} onBlur={this.changeValue} type="text" autoComplete="off"  />
    },
});
module.exports = MicroInput;