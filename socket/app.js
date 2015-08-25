var app = require('express')()
  , server = require('http').createServer(app)
  , io = require('socket.io').listen(server);
var express = require('express');
var path = require('path');
var process = require('child_process');
var fs = require('fs');
 
app.use("/public", express.static(__dirname + '/public'));
 
server.listen(8002);
 
app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});
 
io.sockets.on('connection', function (socket) {
  socket.on('DATA', function (data) {
    var path = __dirname + '/' + getPath(data.data);
    var folder_path = __dirname + '/' + 'classes/';
    

    fs.writeFile(path, data.data, function (err) {
        if (err) throw err;
        process.exec('javac ' + path,
          function (error, stdout, stderr) {
            if (error != null) {
              console.log('exec error: ' + error);
              var packet = { cmd : 'build',  stderr: true, data : { stderr: stderr }}
              socket.emit('DATA',packet);
            }else{
              var packet = {
                cmd: 'build'
              }
              socket.emit('DATA',packet);
              var class_name = getClass(data.data)[1];
              process.exec('cd ' + folder_path + ';java ' + class_name ,
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
            }
          }); 
        });
    });
});

function getClass(data){
  var res = data.match('class\\s+([\\w\\d$_]+)s*');
  return res;
}
function getPath(data){
  
  var res = getClass(data);

  if(res == null){
    var file = randomString(16);
    return 'classes/' + file + ".java";    
    // return 'classes/' + folder + '/' + file + ".java";    
  } else{
    var name = res[1];
    console.log(name);
    return 'classes/' + name + ".java";
    // return 'classes/' + folder + '/' + name + ".java";
  }
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

