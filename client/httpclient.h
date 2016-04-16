#ifndef HTTPCLIENT_H
#define HTTPCLIENT_H

#include <QObject>

class HTTPClient : public QObject
{
    Q_OBJECT
    Q_PROPERTY(QString baseURL READ baseURL WRITE setBaseURL NOTIFY baseURLChanged)
    Q_PROPERTY(QList<QVariant> headers READ headers WRITE setHeaders NOTIFY headersChanged)
    Q_PROPERTY(QString method READ method WRITE setMethod NOTIFY methodChanged)

public:
    explicit HTTPClient(QObject *parent = 0);

signals:

public slots:
};

#endif // HTTPCLIENT_H
