<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        var websocket;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/webSocketServer/zhangsan");
        } else {
            console.log("不支持websocket");
        }
        window.onbeforeunload = function (ev) {
            console.log("准备关闭连接")
            websocket.close();
        }
        websocket.onmessage = function (ev) {
            console.log("收到服务器消息: " + ev.data)
        }
        websocket.onerror = function (ev) {
            console.log("出现异常")
        }
        websocket.onopen = function (ev) {
            console.log("连接建立成功")
        }
    </script>
</head>
<body>
<input type="text" name="">
<button id="clickEndpoint">发送消息</button>
</body>

<script>
    var v = document.getElementById("clickEndpoint");
    v.onclick = function (msg) {
        websocket.send("xixihaha");
    }
</script>

</html>
