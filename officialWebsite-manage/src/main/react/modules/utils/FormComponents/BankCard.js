// bankCard每4位用空格分隔
import React from 'react';
import antd from 'antd';
import {
    Input,
} from 'antd';
var BankCardIpt = React.createClass({

    getInitialState() {
        return {
            bankCardValue: '',
        }
    },
    componentWillReceiveProps(nextProps) {
        if (typeof nextProps.value == 'undefined' || nextProps.value == 'NaN') {
            this.setState({
                bankCardValue: ''
            })
            return
        }
        var value = String(nextProps.value);
        value = value.replace(/\D/g, '');
        var bankCardValue = value.replace(/\D/g, '').replace(/(....)(?=.)/g, '$1 ');
        this.setState({
            bankCardValue
        })

    },
    componentDidMount() {
        var props = this.props;
        if (typeof props.value == 'undefined' || props.value == 'NaN') {
            this.setState({
                bankCardValue: ''
            })
            return
        }
        var value = String(props.value);
        value = value.replace(/\D/g, '');
        var bankCardValue = value.replace(/\D/g, '').replace(/(....)(?=.)/g, '$1 ');
        this.setState({
            bankCardValue
        })
    },
    changeValue(e) {
        var bankCardValue = e.target?e.target.value:e;
        var value = bankCardValue.split(' ').join('');
        this.props.onChange(value);
    },
    render() {
        var props = this.props;
        return <Input {...props} value={this.state.bankCardValue} onChange={this.changeValue} type="text" autoComplete="off"  />
    },
});
module.exports = BankCardIpt;