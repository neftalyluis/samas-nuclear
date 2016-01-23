import QtQuick 2.4
import QtQuick.Layouts 1.2
import QtQuick.Controls 1.4
import "controls" as Awesome


    Row{

        id: dashParent

        width: 1024
        height: 700

        ColumnLayout {

            id: menuDash

            width: 70
            height: 700

            spacing: 0

            Rectangle { color: "red"; width: menuDash.width ; height: menuDash.height/5 }
            Rectangle { color: "blue"; width: menuDash.width ; height: menuDash.height/5 }
            Rectangle { color: "green"; width: menuDash.width ; height: menuDash.height/5 }
            Rectangle { color: "black"; width: menuDash.width ; height: menuDash.height/5 }
            Rectangle { color: "gray"; width: menuDash.width ; height: menuDash.height/5 }

        }

        DashboardViewForm {
            id: dashView
            width: 954
            height: 700
        }
    }


