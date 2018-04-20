import reqwest from 'reqwest';
import React from 'react';
import antd from 'antd';
import {
  Modal,
} from 'antd';
var Select = antd.Select;
var Option = Select.Option;
const ComboData = {
  dic: {},
  /**
   * 初始化字典
   * @returns {void}
   */
  initDic() {
    Utils.ajaxData({
      url: '/modules/manage/system/dict/list.htm',
      callback: (result) => { 
          if (result.code == 200) {
            this.dic = result.data; 
          } else {
            Modal.error({
              title: "字典查询错误"
            });
          }
      }
    }); 
  },
  // CHINA_REGION:{},
  // initTic() {
  //   reqwest({
  //     url: '/modules/common/PlAreaAction/all.htm',
  //     success: (response) => {
  //       if (response.code == 200) {
  //         this.CHINA_REGION=response.data
  //       } else {
  //         Modal.error({
  //           title: "省市区字典查询错误"
  //         });
  //       }
  //     }
  //   });
  // },
  /**
   * 获取dicName对应字典，返回字典对象
   * @param {String} dicName 字典名字
   * @returns {Object} 字典key-value
   */
  getDic(dicName) {
    return this.dic[dicName];
  },
  /**
   * 获取dicName对应字典，返回字典对象
   * @param [String] dicName 字典名字
   * @returns [Object] 字典key-value
   */
  getDicArray(dicName) {
    let need = this.dic[dicName];
    let dicArray = [];
    for (var dicValue in need) {
      if (need.hasOwnProperty(dicValue)) {
        var element = need[dicValue];
        var data={};
        data.text = element;
        data.id = dicValue;
        dicArray.push(data)
      }
    }
    return dicArray;
  },
  /**
   * 获取dicName对应的下拉列表，返回React Option数组对象
   * @param {String} dicName 字典名字
   * @returns {Array} combo React Option数组对象
   */
  getCombo(dicName) {
    let need = this.dic[dicName];
    let combo = [];
    for (var dicValue in need) {
      if (need.hasOwnProperty(dicValue)) {
        var element = need[dicValue];
        combo.push(<Option key={dicValue} value={dicValue}>{element}</Option>)
      }
    }
    return combo;
  },
  /**
   * 获取dicName对应的Integer型下拉列表，返回React Option数组对象
   * @param {String} dicName 字典名字
   * @returns {Array} combo React Option数组对象
   */
  getIntCombo(dicName) {
    let need = this.dic[dicName];
    let combo = [];
    for (var dicValue in need) {
      if (need.hasOwnProperty(dicValue)) {
        if (!isNaN(dicValue)) {
          dicValue = parseInt(dicValue);
        }
        var element = need[dicValue];
        combo.push(<Option key={dicValue} value={dicValue}>{element}</Option>)
      }
    }
    return combo;
  }
}
export default ComboData;