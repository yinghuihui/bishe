import React from 'react'; 
import { Tree,Modal } from 'antd';
const TreeNode = Tree.TreeNode; 
export default React.createClass({
  mixins: [   
    ], 
  getInitialState() {
    return { 
      treeData:[],
      checkedKeys:[],
      expandedKeys:[], 
      allCheckedKey:[],
      allExpandedKeys:[],
    };
  },   
  handleOk() {
    var me = this;
    var selectRecord= this.props.selectRecord;
    var roleId = selectRecord.id;
    var checkedKeys =  this.state.checkedKeys;
    var firstChecked = this.state.checkedKeys;
      this.state.treeData.map((item)=>{
        var secordKids = item.children?item.children.map((its)=>{
          if(its.checked == 1 && firstChecked.indexOf(item.value) == -1){
              firstChecked.push(item.value)
            }
          var threeKids = its.children?its.children.map((it)=>{
            if(it.checked == 1 && firstChecked.indexOf(its.value) == -1){
              firstChecked.push(its.value)
              if(firstChecked.indexOf(item.value) == -1){
                firstChecked.push(item.value)
              }
              
            }
          }):null;
          
        }):null;
      })

    checkedKeys = firstChecked.map((v,i)=>{
          return Number(v);
      });
         Utils.ajaxData({
                url: '/modules/manage/system/roleMenu/update.htm',
                method: 'get', 
                type: 'json',
                data:{
                  roleId:roleId,
                  menus:JSON.stringify(checkedKeys)
                },
                callback: (result) => { 
                   if(result.msg=="操作成功")
                   {
                     
                     Modal.success({
                       title: result.msg
                     }); 
                    
                     me.props.hideModal();
                   
                   }
                   else  
                   {
                     Modal.error({
                       title: result.msg
                     });  
                   }
                }
              });  
  },
  handleCancel() {
    this.props.hideModal();
  }, 
  getCheckedKeys(data){
    var checkedKeys = [];
    data.map((item,index)=>{
      
      if(item.checked == "1"){
        checkedKeys.push(item.value);
      }
      if(item.children){
        checkedKeys=checkedKeys.concat(this.getCheckedKeys(item.children));
      }
    })
    return checkedKeys;
  },
  getExpandedKeys(data){
    var expandedKeys = [];
    data.map((item,index)=>{
      if(item.expanded){
        expandedKeys.push(item.value);
      }
      if(item.children){
        expandedKeys=expandedKeys.concat(this.getExpandedKeys(item.children));
      }
    })
    return expandedKeys;
  },
  componentWillReceiveProps(nextProp){
    if(nextProp.selectRecord){
      this.queryTreeData(nextProp.selectRecord);
    }
  }, 
  queryTreeData(selectRecord){
    var selectRecord= selectRecord;
    var roleId = selectRecord.id;
    var me = this;
    Utils.ajaxData({
            url: '/modules/manage/system/role/menu/list.htm',
            method: 'get', 
            type: 'json',
            data:{
              roleId:roleId,
              node:'root'
            },
            callback: (result) => {
               var checkedKeys = me.getCheckedKeys(result.data);
               var expandedKeys = me.getExpandedKeys(result.data); 
               var allCheckedKey = me.getAllCheckedKeys(result.data);
               var allExpandedKeys = me.getAllExpandedKeys(result.data);
               result.data.forEach(item => {
                 if (item.type == '10') {
                   item.name += ' (菜单)';
                 } else {
                   item.name += ' (权限)';
                 }
                 if (item.children) {
                   item.children.forEach(item => {
                     if (item.type == '10') {
                       item.name += ' (菜单)';
                     } else {
                       item.name += ' (权限)';
                     }
                     if (item.children) {
                       item.children.forEach(item => {
                         if (item.type == '10') {
                           item.name += ' (菜单)';
                         } else {
                           item.name += ' (权限)';
                         }
                       })
                     }
                   })
                 }
               })
               me.setState({
                 treeData :result.data,
                 checkedKeys:checkedKeys,
                 expandedKeys:expandedKeys, 
                 allCheckedKey:allCheckedKey,
                 allExpandedKeys:allExpandedKeys,
               })
            }
          });
  }, 
  handleCheck (checkedKeys){
    this.setState({
          checkedKeys:checkedKeys
        });  
  },
  getAllCheckedKeys(data){
    var checkedKeys = [];
      data.map((item,index)=>{ 
        checkedKeys.push(item.value); 
        if(item.children){
          checkedKeys=checkedKeys.concat(this.getAllCheckedKeys(item.children));
        }
    })
    return checkedKeys; 
  },
  getAllExpandedKeys(data){
    var expandedKeys = [];
      data.map((item,index)=>{ 
        if(item.children){ 
          if(item.expanded){
            expandedKeys.push(item.value);
          }
          expandedKeys=expandedKeys.concat(this.getAllExpandedKeys(item.children));
        }
    })
    return expandedKeys; 
  },
  checkedAll (){
      var allCheckedKey = this.state.allCheckedKey; 
       this.setState({
          checkedKeys:allCheckedKey
        });
  },
  uncheckedAll (){
      this.setState({
          checkedKeys:[]
        });
  },
  expandedAll (){
      var allExpandedKeys = this.state.allExpandedKeys; 
      this.setState({
        expandedKeys:allExpandedKeys
      });
  },
  collapseAll (){ 
      this.setState({
        expandedKeys:[]
      });
  },
  handleExpand(expandedKeys){
     this.setState({
        expandedKeys:expandedKeys
      });
  },
  render() { 
    var state = this. state;  
    var status = state.status;
    var props = this.props;
    const loop = data => data.map((item) => {
      if (item.children) {
        return <TreeNode title={item.name} key={item.value}>{loop(item.children)}</TreeNode>;
      }
      return <TreeNode title={item.name} key={item.value} isLeaf={item.leaf} />;
    });
    const treeNodes = loop(this.state.treeData); 
    return(
    <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800"
      footer={[
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            确 定
          </button>,
          <button key="back" className="ant-btn" onClick={this.handleCancel}>取 消</button> 
        ]} >  
        <div className="actionBtns">
           <button className="ant-btn" onClick={this.checkedAll}> 
             全选
           </button>
           <button className="ant-btn" onClick={this.uncheckedAll}>  
             全不选
           </button>
           <button className="ant-btn" onClick={this.expandedAll}> 
             展开所有
           </button>
           <button className="ant-btn" onClick={this.collapseAll}> 
             收缩所有
           </button> 
        </div>
        <div style={{height:'450px',overflowY: 'scroll'}} >
            <Tree  multiple checkable  defaultExpandAll  onCheck={this.handleCheck} checkedKeys={this.state.checkedKeys} onExpand={this.handleExpand}   expandedKeys={this.state.expandedKeys}
             > 
                  {treeNodes}
            </Tree>
        </div> 
    </Modal> 
    )
  }
});