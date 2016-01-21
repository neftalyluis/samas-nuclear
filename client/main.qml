import QtQuick 2.5
import QtQuick.Controls 1.4
import QtQuick.Extras 1.4
import QtQuick.Layouts 1.2
import QtQuick.Window 2.2
import QtQuick.Dialogs 1.2


ApplicationWindow {
    visible: true
    width: 1024
    height: 768
    title: qsTr("SAMAS")

    Loader { id: pageLoader }

    MessageDialog {
        id: aboutDialog
        icon: StandardIcon.Information
        title: qsTr("Acerca de")
        text: "SAMAS"
        informativeText: qsTr("Holi :)")
    }

    menuBar: MenuBar {
        Menu {
            title: qsTr("Portafolios")
            MenuItem {
                text: qsTr("Crear")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Ver")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Editar")
                onTriggered: console.log("Open action triggered");
            }
        }

        Menu {
            title: qsTr("Activos")
            MenuItem {
                text: qsTr("Crear")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Ver")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Editar")
                onTriggered: console.log("Open action triggered");
            }
        }

        Menu {
            title: qsTr("Clientes")
            MenuItem {
                text: qsTr("Crear")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Ver")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Editar")
                onTriggered: console.log("Open action triggered");
            }
        }

        Menu {
            title: qsTr("Ayuda")
            MenuItem {
                text: qsTr("Acerca de")
                onTriggered: aboutDialog.open();
            }
        }
    }

    toolBar:ToolBar {
            RowLayout {
                anchors.fill: parent
                ToolButton {
                    text: "Boton inutil"
                }
                ToolButton {
                    text: "Otro Boton inutil"
                }
                Item { Layout.fillWidth: true }

                BusyIndicator {
                    running: false
                }
            }
        }

    MainForm {
        id: mainForm

        anchors.fill: parent

        Layout.minimumWidth: 710
        Layout.minimumHeight: 480
        Layout.preferredWidth: 710
        Layout.preferredHeight: 480

        tableView.model: CustomerModel

        Component.onCompleted: CustomerModel.selection = tableView.selection
    }
}
