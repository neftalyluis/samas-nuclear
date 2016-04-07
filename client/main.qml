import QtQuick 2.6
import QtQuick.Controls 1.5
import QtQuick.Dialogs 1.2
import QtWebSockets 1.0
ApplicationWindow{

    visible: true
    Rectangle {
        width: 360
        height: 360
        color: '#000'

        ChatView {
            id: box
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.top: parent.top
            anchors.bottom: input.top
        }

        ChatInput {
            id: input
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.bottom: parent.bottom
            focus: true
            onAccepted: {
                print('send message: ' + text)
                socket.sendTextMessage(text)
                box.append('>', text)
                text = ''
            }
        }

        WebSocket {
            id: socket

            url: "ws://localhost:8080/web-alpha/notifications"
            active: true
            onTextMessageReceived: {
                box.append('<', message)
            }
            onStatusChanged: {
                if (socket.status == WebSocket.Error) {
                    box.append('#', 'socket error ' + socket.errorString)
                } else if (socket.status == WebSocket.Open) {
                    box.append('#', 'socket open')
                } else if (socket.status == WebSocket.Closed) {
                    box.append('#', 'socket closed')
                }
            }
        }

    }
}
