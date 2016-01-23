import QtQuick 2.5
import QtQuick.Controls 1.4
import QtQuick.Extras 1.4
import QtQuick.Layouts 1.2
import QtQuick.Window 2.2
import QtQuick.Dialogs 1.2


ApplicationWindow {
    visible: true

    minimumHeight: 780
    maximumHeight: 780
    minimumWidth: 1024
    maximumWidth: 1024
    width: 1024
    height: 780

    title: qsTr("SAMAS")

    Loader { id: pageLoader }

    MessageDialog {
        id: aboutDialog
        icon: StandardIcon.Information
        title: qsTr("Acerca de")
        text: "SAMAS"
        informativeText: qsTr("Holi :)")
    }

    DashboardForm {
        id: dash
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


            }
        }



    statusBar: StatusBar {
        RowLayout {
            anchors.fill: parent
            Label{
                text: "test"
            }
            /*
            BusyIndicator {
                running: true
                anchors.right: parent.right
            }
            */
        }
    }


}
