import QtQuick 2.4
import QtQuick.Layouts 1.3

ColumnLayout{
    id: bar
    spacing: 0

    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {
            var component = Qt.createComponent("Child.qml")
            var window    = component.createObject(bar)
            window.show()
        }
    }

    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Login"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {
            var component = Qt.createComponent("Login.qml")
            var window    = component.createObject(bar)
            window.show()
        }
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
    ButtonMenu {
        Layout.alignment: Qt.AlignCenter
        color: "#00796B"
        text: "Terminal"
        Layout.preferredHeight: bar.height*0.1
        Layout.preferredWidth: bar.width
        onClicked: {print("holi")}
    }
}
