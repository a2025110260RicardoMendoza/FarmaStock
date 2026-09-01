\# FarmaStock 360 — Modelo relacional v0.1



\## 1. Fuente

\- Proyecto oficial: FarmaStock 360 — Sistema de gestion de stock/farmacia

\- Modelo conceptual base: model-conceptual-v0.1.md

\- Flujo critico utilizado: Un Proveedor entrega mercaderia mediante una Compra (con sus Detalles de Compra); esa mercaderia genera Lotes de un Producto con vencimiento y stock propio; los Lotes se venden a traves de una Venta (con sus Detalles de Venta) atendida por un Usuario cajero.



\## 2. Criterios de transformacion

\- Relaciones 1:N: FK en el lado N.

\- Relaciones N:M: tabla puente.

\- Optionalidad registrada antes de decidir NULL/NOT NULL.

\- Claves naturales relevantes registradas como candidatas a UNIQUE.



\## 3. Tablas candidatas nucleo



\### usuario

Proposito: persona que opera el sistema (recibe compras, gestiona lotes, vende, aparece en el kardex).

Atributos iniciales:

\- usuario\_id \[PK propuesta]

\- nombre\_usuario (clave natural, UNIQUE)

\- credencial\_acceso

\- nombre\_completo

\- estado

\- fecha\_alta

Reglas relacionadas: RI-01 (rol\_id se deja fuera del nucleo v0.1, ver seccion 9)



\### producto

Proposito: item comercializable del catalogo, independiente de sus existencias fisicas concretas.

Atributos iniciales:

\- producto\_id \[PK propuesta]

\- codigo\_barras (clave natural, UNIQUE)

\- nombre

\- descripcion\_corta

\- precio\_venta\_base

\- stock\_minimo

\- estado

Reglas relacionadas: RI-02, RI-03 (categoria\_id se deja fuera del nucleo v0.1, ver seccion 9)



\### proveedor

Proposito: tercero externo del que se adquieren los productos.

Atributos iniciales:

\- proveedor\_id \[PK propuesta]

\- nit\_documento (clave natural, UNIQUE)

\- razon\_social

\- telefono

\- email

\- direccion

\- estado

Reglas relacionadas: RI-04



\### compra

Proposito: evento de recepcion de mercaderia enviada por un proveedor.

Atributos iniciales:

\- compra\_id \[PK propuesta]

\- nro\_factura\_proveedor

\- fecha\_emision

\- fecha\_recepcion

\- total

\- estado\_recepcion

\- proveedor\_id \[FK -> proveedor.proveedor\_id]

\- usuario\_id \[FK -> usuario.usuario\_id]

Reglas relacionadas: RI-05, D-03



\### detalle\_compra

Proposito: linea de producto dentro de una compra (cantidad esperada vs recibida).

Atributos iniciales:

\- detalle\_compra\_id \[PK propuesta]

\- compra\_id \[FK -> compra.compra\_id]

\- producto\_id \[FK -> producto.producto\_id]

\- cantidad\_esperada

\- cantidad\_recibida

\- precio\_unitario\_compra

\- subtotal

Reglas relacionadas: RI-06



\### lote

Proposito: partida fisica concreta de un producto, con vencimiento y stock propio.

Atributos iniciales:

\- lote\_id \[PK propuesta]

\- codigo\_lote\_fabricante (clave natural, unico por producto)

\- producto\_id \[FK -> producto.producto\_id]

\- detalle\_compra\_id \[FK -> detalle\_compra.detalle\_compra\_id, opcional]

\- fecha\_vencimiento

\- stock\_actual

\- estado\_lote

Reglas relacionadas: RI-07, RI-08



\### venta

Proposito: evento de venta de uno o mas productos (consumidor final no identificado en v0.1).

Atributos iniciales:

\- venta\_id \[PK propuesta]

\- nro\_comprobante (clave natural, UNIQUE)

\- fecha

\- total

\- estado

\- usuario\_id \[FK -> usuario.usuario\_id]

Reglas relacionadas: RI-09, D-01



\### detalle\_venta

Proposito: linea vendida dentro de una venta, asociada a un lote especifico.

Atributos iniciales:

\- detalle\_venta\_id \[PK propuesta]

\- venta\_id \[FK -> venta.venta\_id]

\- lote\_id \[FK -> lote.lote\_id]

\- cantidad\_vendida

\- precio\_unitario\_venta

\- subtotal

Reglas relacionadas: RI-10



\## 4. Relaciones

\- proveedor 1:N compra - justificacion: RI-05

\- usuario 1:N compra - justificacion: RI-05 (usuario que recibe)

\- compra 1:N detalle\_compra - justificacion: RI-05

\- producto 1:N detalle\_compra - justificacion: trazabilidad de catalogo en la compra

\- producto 1:N lote - justificacion: RI-07

\- detalle\_compra 1:0..1 lote - justificacion: RI-07 (ver optionalidad, seccion 7)

\- usuario 1:N venta - justificacion: RI-09

\- venta 1:N detalle\_venta - justificacion: RI-09

\- lote 1:N detalle\_venta - justificacion: RI-10



\## 5. Relaciones N:M

No se identifico una relacion N:M directa entre las 8 entidades nucleo de esta version. Las relaciones muchos-a-muchos aparentes (Producto-Compra, Producto/Lote-Venta) ya estan resueltas mediante las tablas puente detalle\_compra (Compra x Producto) y detalle\_venta (Venta x Lote), por eso se modelaron como entidades nucleo desde el Paso 2 en vez de listarse aparte aqui.



\## 6. Claves naturales / UNIQUE candidatas

\- producto.codigo\_barras - unico en todo el sistema (RI-03)

\- proveedor.nit\_documento - unico en todo el sistema (RI-04)

\- venta.nro\_comprobante - unico en todo el sistema (RI-09)

\- usuario.nombre\_usuario - unico, requerido por autenticacion

\- (lote.producto\_id, lote.codigo\_lote\_fabricante) - unico por producto, no global

\- compra.nro\_factura\_proveedor - actualmente SIN restriccion UNIQUE; decision pendiente (D-03) sobre si debe ser (proveedor\_id, nro\_factura\_proveedor) UNIQUE



\## 7. Optionalidad

\- compra.proveedor\_id - obligatoria - toda compra requiere proveedor (RI-05)

\- compra.usuario\_id - obligatoria - toda compra requiere usuario que la recibe (RI-05)

\- detalle\_compra.compra\_id - obligatoria - no existe un detalle sin su compra

\- detalle\_compra.producto\_id - obligatoria - todo detalle referencia un producto

\- lote.producto\_id - obligatoria - todo lote pertenece a un producto (RI-07)

\- lote.detalle\_compra\_id - OPCIONAL - un lote puede existir sin una compra registrada, por ejemplo cargas iniciales de stock (RI-07)

\- venta.usuario\_id - obligatoria - toda venta requiere un usuario cajero (RI-09)

\- detalle\_venta.venta\_id - obligatoria

\- detalle\_venta.lote\_id - obligatoria - cada linea de venta debe estar asociada a un lote especifico (RI-10)



\## 8. Reglas iniciales de integridad

\- RI-01: todo Usuario debe tener un Rol vigente (rol\_id NOT NULL en el esquema fisico; el nucleo v0.1 no modela Rol todavia).

\- RI-02/RI-03: todo Producto pertenece a una Categoria (fuera del nucleo v0.1) y su codigo\_barras es unico.

\- RI-04: el nit\_documento de Proveedor es unico.

\- RI-05: toda Compra requiere Proveedor y Usuario que la recibe.

\- RI-06: cantidad\_recibida puede diferir de cantidad\_esperada en detalle\_compra (permite registrar faltantes/sobrantes).

\- RI-07: un Lote siempre pertenece a un Producto; su vinculo con detalle\_compra es opcional.

\- RI-08: stock\_actual de un Lote debe ser consistente con su historial de movimientos (regla de negocio, no impuesta por el esquema; se sugiere validar por trigger o aplicacion).

\- RI-09: toda Venta requiere Usuario cajero; nro\_comprobante es unico.

\- RI-10: un Detalle de Venta se vincula a un Lote especifico, no solo al Producto generico.



\## 9. Decisiones pendientes

\- Rol no se modelo como tabla nucleo en v0.1; queda pendiente incorporar usuario.rol\_id como FK formal en la Clase 04.

\- Categoria tampoco se incluyo en el nucleo v0.1; producto.categoria\_id queda pendiente de formalizar.

\- D-01 (conceptual): no existe entidad Cliente; se asume venta a consumidor final no identificado. Pendiente confirmar si se requiere trazabilidad del comprador.

\- D-03 (conceptual): compra.nro\_factura\_proveedor no tiene UNIQUE; pendiente decidir si debe ser unico por proveedor.

\- Movimiento de Inventario, Devolucion/Detalle de Devolucion y Log de Auditoria quedan fuera del nucleo v0.1 y se completaran en la Clase 04.



\## 10. Revision de normalizacion basica

\- Listas multivaluadas detectadas/corregidas: no se detectaron columnas con listas separadas por comas en las 8 tablas nucleo.

\- Columnas repetitivas detectadas/corregidas: no se detectaron columnas duplicadas tipo telefono1/telefono2.

\- Datos redundantes detectados/corregidos: precio\_unitario se guarda por separado en detalle\_compra y detalle\_venta en vez de solo en producto; esto es intencional (no redundante), ya que el precio de un producto puede cambiar con el tiempo y cada detalle debe conservar el precio vigente al momento exacto de esa transaccion.

EOF

