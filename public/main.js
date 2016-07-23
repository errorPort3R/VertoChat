var socket;

function start() {
    var ws = new SockJS("/socket")
    socket = Stomp.over(ws)

    socket.connect({}, onSocketConnected)
}

function onSocketConnected() {
    socket.subscribe("/chat", onReceiveMessage)
}

function onReceiveMessage() {

}

function sendMessage() {
    var s = JSON.stringify({message: $('#msg').val()});
    socket.send("/topic/chat", {}, s);
}

start();