#include <QApplication>
#include <QQmlApplicationEngine>
#include <QtGui>
#include <QtQml>
#include <jsonlistmodel.h>

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    /**
     * Registramos nuestro tipo
     * bajo la URL "mx.samas" en version 1.0
     * Bajo el nombre JSONListModel
     *
     * Como en Java :D!
     */
    qmlRegisterType<JSONListModel>("mx.samas",1,0,"JSONListModel");

    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:/main.qml")));

    return app.exec();
}
