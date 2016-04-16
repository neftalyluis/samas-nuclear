TEMPLATE = app

QT += qml quick widgets

CONFIG += c++11

SOURCES += main.cpp \
    jsonlistmodel.cpp \
    httpclient.cpp

RESOURCES += qml.qrc

# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH =

# Default rules for deployment.
include(deployment.pri)

DISTFILES += \
    MenuForm.ui.qml \
    DataTable.ui.qml

HEADERS += \
    jsonlistmodel.h \
    httpclient.h
