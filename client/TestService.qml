import QtQuick 2.0
import QtQuick.Controls 1.4
import "GenericService.js" as Service

Button {
    text: 'Delete Last Color'
    onClicked: {
        Service.request(name)
    }
}
