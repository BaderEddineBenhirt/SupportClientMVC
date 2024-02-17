
    /*<![CDATA[*/
    var stompClient = null;

    function connect() {


        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/publicChatRoom', function(chatMessage) {
                var message = JSON.parse(chatMessage.body);
                var isSender = message.sender === currentUser;
                showMessage(message.content, isSender);
            });
        });
    }


    function disconnect() {
    if (stompClient !== null) {
    stompClient.disconnect();
}
    console.log("Disconnected");
}

    function sendMessage() {
    var messageContent = document.getElementById('messageArea').value.trim();
    if(messageContent && stompClient) {
    var chatMessage = {
    sender: currentUser, // Replace with actual username
    content: messageContent,
    type: 'CHAT'
};
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    console.log("pop it")
    document.querySelector('#messageArea').value = '';

}
}

    function showMessage(message, isSender) {
        var chatLog = document.querySelector('#chatLog');
        var messageElement = document.createElement('div');
        var messageContent = document.createElement('div');

        // Apply different styles based on whether the message is sent or received
        messageElement.className = isSender ? 'flex justify-end' : 'flex justify-start';
        messageContent.className = isSender ?
            'bg-blue-500 text-white text-sm rounded-lg px-4 py-2 max-w-xs lg:max-w-md m-2' :
            'bg-blue-200 text-black-200 text-sm rounded-lg px-4 py-2 max-w-xs lg:max-w-md m-2';

        messageContent.innerText = message;
        messageElement.appendChild(messageContent);
        chatLog.appendChild(messageElement);
        chatLog.scrollTop = chatLog.scrollHeight;
    }

    connect();
    /*]]>*/
