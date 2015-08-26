var app = require('express')()
  , server = require('http').createServer(app)
  , io = require('socket.io').listen(server);
var express = require('express');
var path = require('path');
var process = require('child_process');
var fs = require('fs');
 
app.use("/public", express.static(__dirname + '/public'));
 
server.listen(8003);
 
app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});
 
io.sockets.on('connection', function (socket) {
  socket.on('DATA', function (data) {
    var path = __dirname + '/' + getPath(data.data);
    var folder_path = __dirname + '/' + 'classes/';
    

    fs.writeFile(path + ".py", data.data, function (err) {
        if (err) throw err;
        var packet = {
          cmd: 'save'
        }
        socket.emit('DATA',packet);
        process.exec('python ' + path + ".py",
          function (error, stdout, stderr) {
            if (error != null) {
              console.log('exec error: ' + error);
              var packet = { cmd : 'stdout',  stderr: true, data : { stderr: stderr }}
              socket.emit('DATA',packet);
            }else{
              var packet = {
                cmd: 'stdout',
                data: stdout
              }
              socket.emit('DATA',packet);
            }
          });   
        });
    });
});

function getPath(data){
    var file = randomString(16);
    return 'classes/' + file;    
  }

function randomString(len) {
　　len = len || 32;
　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    
　　var maxPos = $chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
　　}
　　return pwd;
}

