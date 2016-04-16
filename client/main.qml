import QtQuick 2.6
import QtQuick.Controls 1.5
import QtQuick.Dialogs 1.2
import QtQuick.Layouts 1.3

ApplicationWindow{

    visible: true

    width: 1280
    height: 800

        ToolbarForm{
            id: toolbar

            width:parent.width
            height: parent.height*0.10
            anchors.top: parent.top
        }

        Item{
            id: space

            width: parent.width
            height: parent.height*0.90
            anchors.bottom: parent.bottom

            MenuForm{
                id: menu

                width: space.width*0.1
                height: space.height
                anchors.left:parent.left
            }

            Terminal{
                id: operation

                width: space.width*0.9
                height: space.height
                anchors.right: parent.right
            }


        }
}
