#ifndef JSONLISTMODEL_H
#define JSONLISTMODEL_H

#include <QtCore>
#include <QtGui>


class JSONListModel : public QAbstractListModel
{
    //Esta macro agrega la interfaz
    //que se le implementa a cualquier Q_OBJECT
    Q_OBJECT

    //Lo mismo aqui, se adecua para cumplir que hereda a un QObject
public:
    explicit JSONListModel(QObject *parent = 0);
    ~JSONListModel();

public: //Interfaces para adecuarnos a la interfaz QAbstractItemModel
    virtual int rowCount(const QModelIndex &parent) const;
    virtual QVariant data(const QModelIndex &index, int role) const;

private:
    //Una lista de Strings
    QList<QString> m_data;
    //Una lista de mapas de diferentes tipos, aha! (;
    QList<QVariantMap> JSONListMap;
};

#endif // JSONLISTMODEL_H
