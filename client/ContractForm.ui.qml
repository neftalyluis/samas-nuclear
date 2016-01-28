import QtQuick 2.4
import QtQuick.Layouts 1.1
import QtQuick.Controls 1.2

Item {
    Layout.minimumWidth: parent.width
    Layout.maximumWidth: parent.width

    GridLayout{
        id: formularioContratos

        anchors.right: parent.right
        anchors.left: parent.left
        anchors.top: parent.top
        anchors.rightMargin: 12
        anchors.leftMargin: 12
        anchors.topMargin: 12
        columnSpacing: 8
        rowSpacing: 8
        rows: 8
        columns: 7
        enabled: true

        Label {
            id: label1
            text: qsTr("Cuenta")
            Layout.columnSpan: 2
        }

        Label {
            id: label2
            text: qsTr("Estatus")
            Layout.columnSpan: 2
        }

        Item {
            id: spacer10
            Layout.preferredHeight: 14
            Layout.preferredWidth: 14
        }

        Label {
            id: label3
            text: qsTr("Last Name")
        }

        Item {
            id: spacer15
            Layout.preferredHeight: 14
            Layout.preferredWidth: 14
        }

        ComboBox {
            id: numeroCuenta
            Layout.columnSpan: 2
            Layout.fillWidth: true
            model: ["GJK8546", "GFDS76543"]
        }

        ComboBox {
            id: estadoCuenta
            Layout.minimumWidth: 140
            Layout.fillWidth: true
            Layout.columnSpan: 3
            model: ["LIQUIDANDO", "OPERANDO"]
        }

        ComboBox {
            id: estrategia
            Layout.minimumWidth: 140
            Layout.fillWidth: true
            Layout.columnSpan: 2
            model: ["RIESGO", "DEUDA", "BALANCEADO"]

        }

        Label {
            id: label4
            text: qsTr("Clientes")
            Layout.columnSpan: 5
        }

        Button{
            id: accionAgregarCliente
            text: "Agregar"
            Layout.columnSpan: 6
        }

        TableView{
            id: tablaClientes
            width: 608
            height: 150
            Layout.columnSpan: 5
            property int columnWidth: width / 3
            Layout.minimumWidth: formularioContratos.width / 3



            TableViewColumn {
                role: "clienteId"
                title: qsTr("Customer Id")
                width: tablaClientes.columnWidth
            }

            TableViewColumn {
                role: "nombre"
                title: qsTr("Nombre")
                width: tablaClientes.columnWidth
            }
            TableViewColumn {
                role: "apellido"
                title: qsTr("Apellido")
                width: tablaClientes.columnWidth
            }
        }

    }

    RowLayout {
        anchors.topMargin: 12
        anchors.right: parent.right
        anchors.rightMargin: 12
        anchors.top: formularioContratos.bottom

        Button {
            id: guardar
            text: qsTr("Guardar")
        }

        Button {
            id: cancelar
            text: qsTr("Cancelar")
        }
    }


}
