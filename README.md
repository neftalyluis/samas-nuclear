SAMAS
=====

[![Build Status](https://travis-ci.com/neftalyluis/samas.svg?token=yVhq9gzNMCPefShFMz7P&branch=master)](https://travis-ci.com/neftalyluis/samas)
[![codecov](https://codecov.io/gh/neftalyluis/samas/branch/master/graph/badge.svg?token=yBp1PBTDnD)](https://codecov.io/gh/neftalyluis/samas)


# IMPORTANTE
Para poderle hacer deploy a SAMAS, necesitas tres cosas:

1. Una Base de Datos (MySQL, aunque es posible usar cualquier otra)
2. Un Cluster de ElasticSearch version 2.4.x, (Version 5.x no soportada por ahora)
3. [Instalar OpenBLAS](https://github.com/xianyi/OpenBLAS)

Para 1 y 2 necesitas poner sus respectivas propiedades en el application.properties que esta en el codigo
