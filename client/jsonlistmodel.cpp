#include "jsonlistmodel.h"

JSONListModel::JSONListModel(QObject *parent)
    :QAbstractListModel(parent)
{
    m_data = QColor::colorNames();
}

/**
 * Aqui van a existir dos contructores, uno donde reciba como parametro un callback de una request HTTP
 * mientras que el segundo va a ser una URL referida de un JSON local, en caso de que se necesita nutrirse de un archivo
 */

JSONListModel::~JSONListModel()
{

}


int JSONListModel::rowCount(const QModelIndex &parent) const
{
    Q_UNUSED(parent);
    return JSONListMap.count();
}

QVariant JSONListModel::data(const QModelIndex &index, int role) const
{
    int row = index.row();

    //Checamos que el indice que se nos pide exista en la Lista
    //Sino, enviamos vacio
    if(row<0|| row>= JSONListMap.count()){
        return QVariant();
    }

    // A model can return data for different roles.
    // The default role is the display role.
    // it can be accesses in QML with "model.display"
    switch(role){
    case Qt::DisplayRole:

        // Return the color name for the particular row
        // Qt automatically converts it to the QVariant type
        return JSONListMap.value(row);
    }

    // The view asked for other data, just return an empty QVariant
    return QVariant();
}
