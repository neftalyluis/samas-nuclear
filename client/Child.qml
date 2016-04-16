import QtQuick 2.5
import QtQuick.Controls 1.4

ApplicationWindow {
    id: root
    width: 100; height: 100

    Text {
        anchors.centerIn: parent
        text: qsTr("Hello World.")
    }
}
