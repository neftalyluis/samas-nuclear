import QtQuick 2.0

Rectangle {
    id: button

    property alias text : buttonText.text
    signal clicked

    color: "#00796B"

    Text {
        id: buttonText
        text: ""
        anchors.centerIn: parent
        font.pixelSize: parent.height*0.2
        color: "white"
    }

    MouseArea {
        id: mouseArea
        anchors.fill: parent
        onClicked: parent.clicked()
    }
}
