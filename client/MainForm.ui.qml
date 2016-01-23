import QtQuick 2.2
import QtQuick.Layouts 1.1
import QtQuick.Dialogs 1.1
import QtQuick.Controls 1.2

Item {
    width: 1024
    height: 710
    property alias tableView: tableView

    SplitView {
        id: splitView
        anchors.fill: parent

        TableView {
            id: tableView

            property int columnWidth: width / 2
            Layout.minimumWidth: splitView.width / 2

            TableViewColumn {
                role: "customerId"
                title: qsTr("Nombre")
                width: tableView.columnWidth
            }

            TableViewColumn {
                role: "firstName"
                title: qsTr("Ticker")
                width: tableView.columnWidth
            }

        }

        TabView {
            id: tabView

            Layout.minimumWidth: 480

            Tab {
                title: qsTr("Detalles")
                source: "AssetDetails.qml"
            }
            Tab {
                title: qsTr("Precios")
                source: "qml/Settings.qml"
            }
            Tab {
                title: qsTr("Propiedades")
                source: "qml/Settings.qml"
            }
        }
    }
}
