var Reflux = require('reflux');

var AppActions = Reflux.createActions([
	"initStore",
	"setTabActiveKey",
	"setTabActiveKeyBytabId",
	"selectRecord",
	"removeTab"
]);

module.exports = AppActions;