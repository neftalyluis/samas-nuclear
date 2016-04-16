import QtQuick 2.6
import QtQuick.Controls 1.5
import QtQuick.Layouts 1.3
import QtQuick.Controls.Styles 1.4


ApplicationWindow {
    id: window

    Layout.preferredHeight: 600
    Layout.preferredWidth: 800
    Layout.minimumHeight: 600
    Layout.minimumWidth: 800
    Layout.maximumHeight: 600
    Layout.maximumWidth: 800

    style: ApplicationWindowStyle{
        background: Rectangle{
            color: "black"
        }
    }

    ColumnLayout {
        id: layout
        spacing: 0
        anchors.fill: parent

        Item {
            id: loginLabel
            width: 200
            height: 200
            Layout.fillHeight: true
            Layout.fillWidth: true
            Layout.preferredHeight: 100
            Layout.preferredWidth: 800
            Layout.minimumHeight: 100
            Layout.minimumWidth: 800
            Layout.maximumHeight: 100
            Layout.maximumWidth: 800
            Text {
                id: loginText
                color: "#ffffff"
                text: qsTr("Login")
                style: Text.Normal
                font.pixelSize: 70
                verticalAlignment: Text.AlignVCenter
                horizontalAlignment: Text.AlignHCenter
                anchors.rightMargin: 0
                anchors.leftMargin: 0
                anchors.bottomMargin: 0
                anchors.topMargin: 0
                anchors.fill: parent
            }
        }

        GridLayout {
            id: loginForm
            width: 200
            height: 200
            Layout.columnSpan: 0
            columnSpacing: 0
            rowSpacing: 0
            Layout.minimumHeight: 400
            Layout.maximumHeight: 400
            Layout.preferredHeight: 400
            Layout.maximumWidth: 800
            Layout.minimumWidth: 800
            Layout.preferredWidth: 800
            Layout.rowSpan: 0
            columns: 2
            rows: 3

            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                Label {
                    id: userLabel
                    color: "#ffffff"
                    text: qsTr("User")
                }
            }

            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                TextField {
                    id: userInput

                    placeholderText: qsTr("Email")
                }
            }
            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                Label {
                    id: passwordLabel
                    color: "#ffffff"

                    text: qsTr("Password")
                }
            }
            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                TextField {
                    id: passwordInput
                    placeholderText: qsTr("Password")
                    echoMode: TextInput.Password
                }
            }
            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                Label {
                    id: label3
                    color: "#ffffff"

                    text: qsTr("State:")
                }
            }
            Item {

                Layout.fillHeight: true
                Layout.fillWidth: true

                Button {
                    id: button1
                    text: qsTr("Send")
                }
            }
        }

        Item {
            id: loginSpacer
            width: 200
            height: 200
            Layout.maximumHeight: 100
            Layout.minimumHeight: 100
            Layout.maximumWidth: 800
            Layout.minimumWidth: 800
            Layout.preferredHeight: 100
            Layout.preferredWidth: 800
            Layout.fillHeight: true
            Layout.fillWidth: true
        }

    }

}
