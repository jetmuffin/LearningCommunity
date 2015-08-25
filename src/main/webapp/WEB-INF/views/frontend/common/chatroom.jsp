<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <div class="chat-box" id="chatroom">
        <div class="chat-header">
            在线讨论 <span id="room"></span> ( <span id="status"></span>)
            <span id="close"> × </span>
        </div>
        <div class="chat-body">
            <div class="chat-friend-list">
                <ul id="friend-list">
                </ul>
            </div>
            <div class="chat-content">
                <div id="chat-board" class="chat-board">

                </div>
                <div class="chat-control">
                    <a id="emoji" class="control-icon"></a>
                    <div id="emojiWrapper"></div>
                    <label for="sendImage" class="imageLable" title="send image">
                        <a title="send image" class="sendImage control-icon"></a>
                        <input id="sendImage" type="file" value="image"/>
                    </label>
                    <a id="clearBtn" title="clear screen" class="control-icon"></a> 
                    <div class="to">
                        发送给:
                        <select name="" id="sendTo">
                            <option value="all">所有人</option>
                        </select>
                    </div>
                    <div style="height:8px"></div>
                    <textarea id="msg-textarea"></textarea>
                    <button class="btn btn-blue" id="sendBtn">发送</button>
                    <div id="send-notice">按Ctrl+Enter发送</div>                    
                </div>
            </div>
        </div>
    </div>