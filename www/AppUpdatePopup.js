var exec = require('cordova/exec');

module.exports.showPopUp = function (success, error) {
    exec(success, error, 'AppUpdatePopup', 'showPopUp', []);
};
module.exports.startUpdate = function (success, error){
    exec(success, error, 'AppUpdatePopup', 'startUpdate', []);
}