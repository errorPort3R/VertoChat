var socket;

function start() {
    var ws = new SockJS("/socket")
    socket = Stomp.over(ws)

    socket.connect({}, onSocketConnected)
}

function onSocketConnected() {
    socket.subscribe("/topic/chat", onReceiveMessage)
}

function onReceiveMessage(mess) {
    data = JSON.parse(mess.body);
    $('#divlist').append(data.time + " - " + data.msg +  "<br />" + "<br />");
    if (mess === undefined)
    {
        return;
    }
}

function sendMessage() {
    var t = timeNow(t);
    var s = JSON.stringify({msg: $('#msg').val(), time: t});
    socket.send("/topic/chat", {}, s);
}

function timeNow(i) {
  var d = new Date(),
      h = d.getHours(),
      m = d.getMinutes();
  return h + ':' + m;
}

start();