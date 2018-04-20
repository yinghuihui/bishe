import React from 'react';
const options = {
  //工具栏
  toolbars: [[
    'fullscreen', 'source', '|', 'undo', 'redo', '|',
    'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch',
    '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
    'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
    'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
    'directionalityltr', 'directionalityrtl', 'indent', '|',
    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
    'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
    'simpleupload',
    'horizontal', 'date', 'time',
  ]]
  , lang: "zh-cn"
  //字体
  , 'fontfamily': [
    { label: '', name: 'songti', val: '宋体,SimSun' },
    { label: '', name: 'kaiti', val: '楷体,楷体_GB2312, SimKai' },
    { label: '', name: 'yahei', val: '微软雅黑,Microsoft YaHei' },
    { label: '', name: 'heiti', val: '黑体, SimHei' },
    { label: '', name: 'lishu', val: '隶书, SimLi' },
    { label: '', name: 'andaleMono', val: 'andale mono' },
    { label: '', name: 'arial', val: 'arial, helvetica,sans-serif' },
    { label: '', name: 'arialBlack', val: 'arial black,avant garde' },
    { label: '', name: 'comicSansMs', val: 'comic sans ms' },
    { label: '', name: 'impact', val: 'impact,chicago' },
    { label: '', name: 'timesNewRoman', val: 'times new roman' }
  ]
  //字号
  , 'fontsize': [10, 11, 12, 14, 16, 18, 20, 24, 36]
  , enableAutoSave: false
  , autoHeightEnabled: false
  , initialFrameWidth: '100%'
}
class Ueditor extends React.Component {
  constructor(props) {
    super(props);
    this.editor = {}
  }
  componentWillReceiveProps(nextProps) {

    /*if (!nextProps.record) {//新增 
      var value = '';console.log(111)
      editor.setContent(value);
      return;
    }*/

    if (nextProps.record != this.props.record) {
      console.log(11)
      var value = nextProps.value ? nextProps.value : '';
      this.editor.setContent(value);
    }

  }
  componentDidUpdate() {
    if (!this.props.disabled) {
      this.editor.setEnabled();
    }
    else {
      this.editor.setDisabled();
    }
  }

  componentWillUnmount() {
    
    // 组件卸载后，清除放入库的id
    UE.delEditor(this.props.id);
    // UE.getEditor(this.props.id).destroy()
    var dom = document.getElementById('content');
　　if (dom) {
　　　　dom.parentNode.removeChild(dom);
　　　　}
  }

  componentDidMount() {
    options.readonly = this.props.disabled;
    let editorOptions = options;
    UE.delEditor(this.props.id);
    this.editor = UE.getEditor(this.props.id, editorOptions);
    var me = this;
    this.editor.ready(function (ueditor) {
      var value = me.props.value ? me.props.value : '';
      me.editor.setContent(value);
      me.editor.setOpt('readonly', me.props.disabled)
    });
  }
  render() {
    return (
      <script id={this.props.id} name="content" type="text/plain" >

      </script>
    )
  }
}
export default Ueditor; 