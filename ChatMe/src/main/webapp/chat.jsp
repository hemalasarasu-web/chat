<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Simple Chatting System</title>

    <style>

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f2f2f2;
        }

        .chat-box {
            width: 600px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        #messages {
            height: 350px;
            overflow-y: auto;
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 15px;
            background: #fafafa;
        }

        .message {
            background: #e8f5e9;
            padding: 10px;
            margin: 8px 0;
            border-radius: 6px;
        }

        .time {
            color: gray;
            font-size: 12px;
        }

        .input-area {
            display: flex;
            gap: 10px;
        }

        #message {
            flex: 1;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 15px;
        }

        button {
            padding: 12px 20px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background: #45a049;
        }

    </style>

</head>

<body>

<div class="chat-box">

    <h2>💬 Simple Chatting System</h2>

    <div id="messages">
        Loading messages...
    </div>

    <div class="input-area">

        <input type="text"
               id="message"
               placeholder="Type your message..."
               autocomplete="off">

        <button onclick="sendMessage()">Send</button>

    </div>

</div>


<script>

function sendMessage() {

    let message =
        document.getElementById("message").value;

    if (message.trim() === "") {

        alert("Please enter a message");

        return;
    }

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "MessageServlet", true);

    xhr.setRequestHeader(
        "Content-Type",
        "application/x-www-form-urlencoded"
    );

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4) {

            if (xhr.status === 200) {

                document.getElementById("message").value = "";

                loadMessages();

            } else {

                alert("Message sending failed");

            }
        }
    };

    xhr.send(
        "message=" + encodeURIComponent(message)
    );
}


function loadMessages() {

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "MessageServlet", true);

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4) {

            if (xhr.status === 200) {

                document.getElementById("messages").innerHTML =
                    xhr.responseText;

            } else {

                document.getElementById("messages").innerHTML =
                    "Unable to load messages.";

            }
        }
    };

    xhr.send();

}


// Load messages when page opens
loadMessages();


// Automatically refresh messages every 2 seconds
setInterval(loadMessages, 2000);

</script>

</body>
</html>