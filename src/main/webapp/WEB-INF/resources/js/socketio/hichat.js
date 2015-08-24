window.onload = function() {
    //实例并初始化我们的hichat程序
    var hichat = new HiChat();
    hichat.init();
};

//定义我们的hichat类
var HiChat = function() {
    this.socket = null;
};

//向原型添加业务方法
HiChat.prototype = {
    init: function() {//此方法初始化程序
        var that = this;
        this._initialEmoji();//初始化表情
        //建立到服务器的socket连接
        this.socket = io.connect("localhost:8000");
        //监听socket的connect事件，此事件表示连接已经建立
        this.socket.on('connect', function() {
            // //连接到服务器后，显示昵称输入框

            //昵称设置的确定按钮

        });

        this.socket.on('nickExisted', function() {
            document.getElementById('info').textContent = '!nickname is taken, choose another pls'; //显示昵称被占用的提示
        });

        this.socket.on('loginSuccess', function() {
            document.title = 'hichat | ' + document.getElementById('nicknameInput').value;
            document.getElementById('loginWrapper').style.display = 'none';//隐藏遮罩层显聊天界面
            document.getElementById('msg-textarea').focus();//让消息输入框获得焦点
        });

        this.socket.on('system', function(nickName, userMap, type) {
            var msg = (type == 'login' ? ' 进入讨论房间' : '离开讨论房间');
            //指定系统消息显示为红色
            that._displayNewMsg(nickName, msg, 2);
            document.getElementById('room').textContent = that.room_id;
            document.getElementById('status').textContent = userMap.size + ' 位用户在线';
            if(type == 'login'){
                if(nickName == that.user)
                    that._loadUser(userMap);
                else
                    that._addUser(nickName, userMap);
            }else{
                that._removeUser(nickName, userMap);
            }
        });     

        this.socket.on('receive', function(user, msg, type) {
            var type = type == 'all' ? 1 : 0;
            that._displayNewMsg(user, msg, type);
        });

        this.socket.on('receiveImage', function(user, img, type) {
            var type = type == 'all' ? 1:0;
            that._displayImage(user, img, type);
         });

        document.getElementById('clearBtn').addEventListener('click', function() {
            var container = document.getElementById('chat-board');
            container.innerHTML = ''; 
        }, false);

        document.getElementById('sendBtn').addEventListener('click', function() {
            var messageInput = document.getElementById('msg-textarea'),
                msg = messageInput.value,
                sendTo = document.getElementById('sendTo').value;
            messageInput.value = '';
            messageInput.focus();
            if (msg.trim().length != 0) {
                if(sendTo == 'all'){
                    that.socket.emit('send:all', msg); //把消息发送到服务器
                    that._displayNewMsg('我', msg , 1); //把自己的消息显示到自己的窗口中                    
                }else{
                    that.socket.emit('send:user', msg, sendTo); 
                    that._displayNewMsg('我', msg , 0);
                }
            };
        }, false);

        document.getElementById('sendImage').addEventListener('change', function() {
            var sendTo = document.getElementById('sendTo').value;
            //检查是否有文件被选中
             if (this.files.length != 0) {
                //获取文件并用FileReader进行读取
                 var file = this.files[0],
                     reader = new FileReader();
                 if (!reader) {
                     that._displayNewMsg('system', '!your browser doesn\'t support fileReader', 'red');
                     this.value = '';
                     return;
                 };
                 reader.onload = function(e) {
                    //读取成功，显示到页面并发送到服务器
                    this.value = '';
                    if(sendTo == 'all'){
                        that.socket.emit('img:all', e.target.result);
                        that._displayImage('我', e.target.result, 1);                 
                    }else{
                        that.socket.emit('img:user', e.target.result, sendTo);
                        that._displayImage('我', e.target.result, 0);  
                    }                     
                 };
                 reader.readAsDataURL(file);
             };
         }, false);         

          document.getElementById('msg-textarea').addEventListener('keyup', function(e) {
                var messageInput = document.getElementById('msg-textarea'),
                    msg = messageInput.value,
                    sendTo = document.getElementById('sendTo').value;
                if (e.ctrlKey && e.keyCode == 13 && msg.trim().length != 0) {
                    messageInput.value = '';
                    if(sendTo == 'all'){
                        that.socket.emit('send:all', msg); //把消息发送到服务器
                        that._displayNewMsg('我', msg , 1); //把自己的消息显示到自己的窗口中                    
                    }else{
                        that.socket.emit('send:user', msg, sendTo); 
                        that._displayNewMsg('我', msg , 0);
                    }
                };
          }, false);

         document.getElementById('emoji').addEventListener('click', function(e) {
             var emojiwrapper = document.getElementById('emojiWrapper');
             emojiwrapper.style.display = 'block';
             e.stopPropagation();
         }, false);

         document.body.addEventListener('click', function(e) {
             var emojiwrapper = document.getElementById('emojiWrapper');
             if (e.target != emojiwrapper) {
                 emojiwrapper.style.display = 'none';
             };
         });  

        document.getElementById('emojiWrapper').addEventListener('click', function(e) {
            //获取被点击的表情
            var target = e.target;
            if (target.nodeName.toLowerCase() == 'img') {
                var messageInput = document.getElementById('msg-textarea');
                messageInput.focus();
                messageInput.value = messageInput.value + '[emoji:' + target.title + ']';
            };
        }, false);     

        document.getElementById('friend-list').addEventListener('click',function(e){
            var target = e.target,
                userName,
                optionNode = document.createElement('option'),
                sendToNode = document.getElementById('sendTo'),
                whisperNode = document.getElementById('whisper');
            if(target.getAttribute('class') == 'friend'){
                userName = target.id;
                var reg = /user-(.*)/g;
                userName = reg.exec(userName)[1];
            }else{
                userName = target.innerHTML;
            }
            if(userName != that.user){
                if(whisperNode){
                    sendToNode.removeChild(whisperNode);
                }
                optionNode.value = userName;
                optionNode.id = 'whisper';
                optionNode.innerHTML = userName;
                optionNode.selected = true;
                sendToNode.appendChild(optionNode);                
            }
        }, false);
    },

    _displayNewMsg: function(user, msg, type) {
        var container = document.getElementById('chat-board'),
            msgToDisplay = document.createElement('p'),
            date = new Date().toTimeString().substr(0, 8),
            whisper = type == 0 ? '（悄悄话）' : ''
            //将消息中的表情转换为图片
            msg = this._showEmoji(msg);
        if(user == '我')
            msgToDisplay.innerHTML = '<div class="msg-right"><div class="avatar"><img src="../images/avatar.png" alt=""></div><div class="arrow"></div><div class="msg"><div class="name">'+ user + whisper +' <span class="time">(' + date + '): </span></div><div>'+msg+'</div></div><div class="clear"></div></div>'
            // msgToDisplay.innerHTML = user + whisper + '<span class="timespan">(' + date + '): </span>' + msg;
        else if(type == 2)
            msgToDisplay.innerHTML = '<div class="msg-sys"><div class="msg">系统消息：<span class="name">'+user+'</span> '+msg+'</div></div>'
        else
            msgToDisplay.innerHTML = '<div class="msg-left"><div class="avatar"><img src="../images/avatar.png" alt=""></div><div class="arrow"></div><div class="msg"><div class="name">'+user+whisper+'<span class="time">('+date+')</span></div><div>'+msg+'</div></div><div class="clear"></div></div>'
        container.appendChild(msgToDisplay);
        container.scrollTop = container.scrollHeight;
    },

    _displayImage: function(user, imgData, type) {
        var container = document.getElementById('chat-board'),
            msgToDisplay = document.createElement('p'),
            date = new Date().toTimeString().substr(0, 8),
            whisper = type == 0 ? '（悄悄话）' : '';
        if(user == '我')
            msgToDisplay.innerHTML = '<div class="msg-right"><div class="avatar"><img src="../images/avatar.png" alt=""></div><div class="arrow"></div><div class="msg"><div class="name">'+ user + whisper +'<span class="timespan">(' + date + '): </span> <br/>' + '<a href="' + imgData + '" target="_blank"><img src="' + imgData + '" class="img-thumb"/></a>'+'</div></div><div class="clear"></div></div>'
        else
            msgToDisplay.innerHTML = '<div class="msg-left"><div class="avatar"><img src="../images/avatar.png" alt=""></div><div class="arrow"></div><div class="msg"><div class="name">'+ user + whisper +'<span class="timespan">(' + date + '): </span> <br/>' + '<a href="' + imgData + '" target="_blank"><img src="' + imgData + '" class="img-thumb"/></a>'+'</div></div><div class="clear"></div></div>'
        container.appendChild(msgToDisplay);
        container.scrollTop = container.scrollHeight;
    },   

    _addUser: function(user, userMap) {
        var container = document.getElementById('friend-list'),
            userItem = document.createElement('li');
        userItem.id = 'user-' + user; 
        userItem.setAttribute("class", "friend");
        userItem.innerHTML = '<div class="friend-avatar"><img src="../images/avatar.png" alt=""></div><div class="friend-name">'+user+'</div>';
        container.appendChild(userItem);
    },

    _loadUser: function(userMap) {
        var container = document.getElementById('friend-list');
        for(var user in userMap.map){
            userItem = document.createElement('li');
            userItem.id = 'user-' + user; 
            userItem.setAttribute("class", "friend");
            userItem.innerHTML = '<div class="friend-avatar"><img src="../images/avatar.png" alt=""></div><div class="friend-name">'+user+'</div>';
            container.appendChild(userItem);            
        }
    },

    _removeUser: function(user, userMap){
        var container = document.getElementById('friend-list'),
            userItem = document.getElementById('user-'+user);
        container.removeChild(userItem);
    },

    _initialEmoji: function() {
        var emojiContainer = document.getElementById('emojiWrapper'),
            docFragment = document.createDocumentFragment();
        for (var i = 30; i > 0; i--) {
            var emojiItem = document.createElement('img');
            emojiItem.src = '/LearningCommunity/resources/images/emoji/tuzki/' + i + '.gif';
            emojiItem.title = i;
            docFragment.appendChild(emojiItem);
        };
        emojiContainer.appendChild(docFragment);
    },    

    _showEmoji: function(msg) {
        var match, result = msg,
            reg = /\[emoji:\d+\]/g,
            emojiIndex,
            totalEmojiNum = document.getElementById('emojiWrapper').children.length;
        while (match = reg.exec(msg)) {
            emojiIndex = match[0].slice(7, -1);
            if (emojiIndex > totalEmojiNum) {
                result = result.replace(match[0], '[X]');
            } else {
                result = result.replace(match[0], '<img class="emoji" src="../content/tuzki/' + emojiIndex + '.gif" />');
            };
        };
        return result;
    }     
};

