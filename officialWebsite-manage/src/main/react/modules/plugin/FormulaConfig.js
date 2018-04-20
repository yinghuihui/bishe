/**
 * 公式配置
 */
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Icon,
    Tag,
    Popover,
    Select,
    Cascader,
    Dropdown,
    Row,
    Col
} from 'antd';

var FormulaConfig = React.createClass({
    getInitialState() {
        return {
            value: "[]"
        }
    },
    handleDelete(key) {
        var value = JSON.parse(this.state.value);
        let newValue = [...value].filter((tag, index) => (index !== key) && tag);
        this.index--;
        this.props.onChange(newValue);
    },
    setIndex(index) {
        this.index = index;
    },
    checkKey(index, event) {
        var event = (event) ? event : window.event;//处理IE和FF浏览器的兼容性问题
        if (event.keyCode == 8) {
            //backspace键！
            this.handleDelete(index - 1);
        }
        if (event.keyCode == 37) {
            document.getElementById(`ipt${index - 1}`).focus();
        }
        if (event.keyCode == 39) {
            //right键！
            document.getElementById(`ipt${index + 1}`).focus();
        }
    },
    onChange(selectedOption, type) {
        var index = this.index = isNaN(this.index) ? 0 : this.index;
        var newValue = JSON.parse(this.state.value);
        var tag = selectedOption;
        if (type == '运算符' || type == '数字') {
            tag.isOperator = 1;
        } else tag.isOperator = 0;
        // if (index == 'last') {
        //     newValue.push(tag);
        // }
        // else
        newValue.splice(index, 0, tag);
        this.index++;
        this.props.onChange(newValue);
    },
    componentWillReceiveProps(nextProp) {
        var selectRecord = nextProp.selectRecord;
        if (typeof nextProp.value == "undefined") {
            this.setState({
                value: "[]"
            });
            return
        }
        if (nextProp.value) {
            this.setState({
                value: typeof nextProp.value != "string" ? JSON.stringify(nextProp.value) : nextProp.value
            });
        }
    },
    componentDidMount() {
        var props = this.props; 
        var selectRecord = props.selectRecord;

        if (selectRecord) {
            this.setState({
                value: typeof props.value != "string" ? JSON.stringify(props.value) : props.value
            });
        }
    },
    onfocusMyTag(e) {
        var value = JSON.parse(this.state.value);
        var tagLength = value.length;
        if (e.target == e.currentTarget) {

            this.refs.input.click();
        }
    },
    reset() {
        // this.setState({
        //     value: "[]"
        // }) 
        this.props.onChange([]);
    },
    render() {
        var props = this.props;
        var oldValue = this.state.value?this.state.value:'[]';//解决当this.state.value=undefined时JSON.parse()方法报错
        var value = JSON.parse(oldValue);
        var tagLength = value.length;
        var opts = this.props.tagOptions.map((c, index) => {
            if (c.children.length) {
                return <div key={c.value}><span style={{ width: 50, display: 'inline-block', textAlign: 'right' }}>{c.label}</span>:{c.children.map((item, i) => {
                    return <Button type="primary" key={item.value} onClick={this.onChange.bind(this, item, c.label)} style={{ marginLeft: 5, marginBottom: 5 }}>{item.label}</Button>
                })}</div>
            }
        });
        var keyboard = <div className="keyboard">{opts}</div>;
        return (<div>
            <div className="myTag x-tag" id='myTag' >
                {
                    value.length ? value.map((tag, index) => {
                        return props.canEdit ?
                            (<span key={`${tag.value}${index}`}>
                                <input id={`ipt${index}`} value="" className="ant-select-search__field" style={{ width: 5, border: 0 }} onClick={this.setIndex.bind(this, index)} onKeyDown={this.checkKey.bind(this, index)} />
                                <Popover content={<a onClick={this.handleDelete.bind(this, index)}>删除</a>} title="" trigger="hover">
                                    <span >{tag.label}</span>
                                </Popover>
                            </span>
                            ) : (<span key={`${tag.value}${index}`}>{tag.label}</span>)
                    }) : null
                }
                {
                    props.canEdit ?
                        <input ref='input' id={`ipt${tagLength}`} value="" className="ant-select-search__field" style={{ width: 5, border: 0 }} onClick={this.setIndex.bind(this, tagLength)} onKeyDown={this.checkKey.bind(this, tagLength)} />
                        : null
                }
                {
                    props.canEdit ? <Button onClick={this.reset} type="ghost" shape="circle" icon="cross" className='resetFelBtn' /> : null
                }
                <input value={this.state.value} type="hidden" />
            </div>
            {
                props.canEdit ? <div>{keyboard}</div> : null
            }
        </div>
        );
    },

});



module.exports = FormulaConfig;
