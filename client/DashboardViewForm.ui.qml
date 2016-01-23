import QtQuick 2.4
import QtQuick.Layouts 1.2
import QtQuick.Controls 1.4



RowLayout {
    spacing: 0

    TableView {
        Layout.fillHeight: true
        Layout.minimumWidth: parent.width/2
        Layout.maximumWidth: parent.width/2

        TableViewColumn {
            role: "fecha"
            title: "Fecha"
            width: 100
        }
        TableViewColumn {
            role: "cuenta"
            title: "Cuenta"
            width: 200
        }

        TableViewColumn {
            role: "estrategia"
            title: "Estrategia"
            width: 200
        }
    }

    ContractForm {

        Layout.fillHeight: true
        Layout.minimumWidth: parent.width/2
        Layout.maximumWidth: parent.width/2
        anchors.right: parent.right

    }
}
