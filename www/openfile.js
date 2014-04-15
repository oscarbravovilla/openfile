var openfile = {
    showPdf: function(successCallback,errorCallback,url) {
        cordova.exec(
            successCallback,
            errorCallback, 
            'OpenFile', 
            'showPdf', 
            [url]
        ); 
    }
}
module.exports = openfile;
