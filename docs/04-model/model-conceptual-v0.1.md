# Modelo conceptual v0.1 — FarmaStock 360

## 1. Objetivo
Este documento identifica los conceptos principales del dominio, sus atributos conceptuales, relaciones, cardinalidades y primeras reglas de integridad.

> **Nota metodológica:** este modelo conceptual fue reconstruido *a posteriori*, a partir del script SQL ya implementado (`FarmaStock 360`, motor PostgreSQL). Por tanto, las cardinalidades y reglas descritas reflejan lo que el esquema físico ya impone (claves foráneas, NOT NULL, UNIQUE), y no un análisis independiente de reglas de negocio previas. Se recomienda revisar la sección 8 (Dudas y decisiones) antes de darlo por definitivo.

## 2. Fuente analizada
- Proyecto asignado: FarmaStock 360 — Sistema de gestión de stock/farmacia
- Fuente: script DDL `CREATE TABLE` (PostgreSQL), 14 tablas
- Secciones cubiertas: gestión de usuarios y roles, catálogo de productos, compras a proveedores, control de lotes y vencimientos, movimientos de inventario (kardex), ventas, devoluciones y auditoría

## 3. Candidatos analizados

| Concepto | Clasificación | Justificación | Fuente |
|---|---|---|---|
| Rol | Entidad | Agrupa permisos/perfiles de acceso, tiene identidad propia (`id_rol`) | Tabla `roles` |
| Usuario | Entidad | Actor del sistema con identidad propia y atributos de autenticación | Tabla `usuarios` |
| Categoría | Entidad | Clasificación de productos, reutilizable por muchos productos | Tabla `categorias` |
| Proveedor | Entidad | Tercero externo con identidad fiscal (`nit_documento`) | Tabla `proveedores` |
| Producto | Entidad | Ítem del catálogo, con identidad propia (`codigo_barras`) | Tabla `productos` |
| Compra | Entidad (evento) | Transacción de recepción de mercadería, con folio propio | Tabla `compras` |
| Detalle de Compra | Entidad débil / asociativa | Depende de una Compra y un Producto; representa una línea | Tabla `detalle_compras` |
| Lote | Entidad | Unidad física de stock con vencimiento e identidad propia | Tabla `lotes` |
| Movimiento de Inventario | Entidad (evento) | Registro histórico (kardex) de cada cambio de stock de un lote | Tabla `movimientos_inventario` |
| Venta | Entidad (evento) | Transacción de venta al cliente final, con comprobante propio | Tabla `ventas` |
| Detalle de Venta | Entidad débil / asociativa | Depende de una Venta y un Lote; representa una línea vendida | Tabla `detalle_ventas` |
| Devolución | Entidad (evento) | Reverso total/parcial de una Venta | Tabla `devoluciones` |
| Detalle de Devolución | Entidad débil / asociativa | Depende de una Devolución y de un Detalle de Venta | Tabla `detalle_devoluciones` |
| Log de Auditoría | Entidad (evento) | Traza de acciones realizadas por un usuario sobre el sistema | Tabla `logs_auditoria` |
| Cliente | Duda | No existe tabla `clientes`; las ventas no registran comprador identificado | — |
| Caja / Turno de caja | Duda | No hay tabla de apertura/cierre de caja; solo se referencia `id_usuario_cajero` | Sección de ventas |

## 4. Entidades núcleo v0.1

### Rol
Responsabilidad: Definir los perfiles de acceso que puede tener un usuario del sistema (ej. administrador, cajero, encargado de almacén).
Atributos conceptuales: nombre, descripción, estado (activo/inactivo).
Identificador de negocio candidato: nombre del rol (único).

### Usuario
Responsabilidad: Representar a la persona que opera el sistema (autentica, registra compras, vende, autoriza devoluciones, genera auditoría).
Atributos conceptuales: nombre de usuario, credencial de acceso, nombre completo, estado, fecha de alta.
Identificador de negocio candidato: nombre de usuario (único).

### Categoría
Responsabilidad: Clasificar los productos del catálogo para su organización y búsqueda.
Atributos conceptuales: nombre, descripción, estado.
Identificador de negocio candidato: nombre de la categoría (único).

### Proveedor
Responsabilidad: Representar al tercero externo del que se adquieren los productos.
Atributos conceptuales: documento/NIT, razón social, teléfono, email, dirección, estado.
Identificador de negocio candidato: NIT o documento (único).

### Producto
Responsabilidad: Representar el ítem comercializable del catálogo, independiente de sus existencias físicas concretas.
Atributos conceptuales: código de barras, nombre, descripción corta, precio de venta base, stock mínimo, categoría, estado.
Identificador de negocio candidato: código de barras (único).

### Compra
Responsabilidad: Registrar el evento de recepción de mercadería enviada por un proveedor.
Atributos conceptuales: número de factura del proveedor, fecha de emisión, fecha de recepción, total, estado de recepción, proveedor, usuario que recibe.
Identificador de negocio candidato: número de factura del proveedor (no único globalmente por sí solo; se recomienda revisar en sección 8).

### Detalle de Compra
Responsabilidad: Representar cada línea de producto dentro de una compra, con la cantidad esperada versus la efectivamente recibida.
Atributos conceptuales: cantidad esperada, cantidad recibida, precio unitario de compra, subtotal.
Identificador de negocio candidato: no tiene identidad propia fuera del sistema; depende de Compra + Producto.

### Lote
Responsabilidad: Representar una partida física concreta de un producto, con su propio vencimiento y stock disponible.
Atributos conceptuales: código de lote del fabricante, fecha de vencimiento, stock actual, estado del lote, producto al que pertenece.
Identificador de negocio candidato: código de lote del fabricante (único por producto, no globalmente).

### Movimiento de Inventario
Responsabilidad: Dejar traza histórica (kardex) de cada entrada, salida o ajuste de stock sobre un lote.
Atributos conceptuales: tipo de movimiento, cantidad afectada, stock resultante, fecha, motivo/referencia, lote, usuario responsable.
Identificador de negocio candidato: ninguno; es puramente un registro de auditoría interna del stock.

### Venta
Responsabilidad: Registrar el evento de venta de uno o más productos a un cliente (no identificado en el modelo actual).
Atributos conceptuales: número de comprobante, fecha, total, estado, usuario cajero.
Identificador de negocio candidato: número de comprobante (único).

### Detalle de Venta
Responsabilidad: Representar cada línea vendida dentro de una venta, asociada a un lote específico (no solo al producto genérico).
Atributos conceptuales: cantidad vendida, precio unitario de venta, subtotal.
Identificador de negocio candidato: no tiene identidad propia; depende de Venta + Lote.

### Devolución
Responsabilidad: Registrar el evento de reverso (total o parcial) de una venta.
Atributos conceptuales: fecha, motivo general, total reembolsado, venta asociada, usuario que autoriza.
Identificador de negocio candidato: ninguno propio; se identifica por su vínculo a una Venta.

### Detalle de Devolución
Responsabilidad: Representar cada línea devuelta, vinculada a la línea de venta original.
Atributos conceptuales: cantidad devuelta, estado del producto devuelto (ej. reintegrable a stock, dado de baja).
Identificador de negocio candidato: ninguno propio; depende de Devolución + Detalle de Venta.

### Log de Auditoría
Responsabilidad: Registrar toda acción relevante ejecutada por un usuario sobre cualquier tabla del sistema, con fines de trazabilidad.
Atributos conceptuales: acción realizada, tabla afectada, registro afectado, descripción del cambio, fecha/hora, IP de origen, usuario.
Identificador de negocio candidato: ninguno; es un log técnico.

## 5. Relaciones

- Un Rol es asignado a muchos Usuarios; cada Usuario tiene exactamente un Rol.
- Una Categoría clasifica a muchos Productos; cada Producto pertenece a exactamente una Categoría.
- Un Proveedor realiza muchas Compras; cada Compra corresponde a exactamente un Proveedor.
- Un Usuario (rol almacén) registra muchas Compras; cada Compra es recibida por exactamente un Usuario.
- Una Compra contiene muchos Detalles de Compra; cada Detalle de Compra pertenece a exactamente una Compra.
- Un Producto aparece en muchos Detalles de Compra; cada Detalle de Compra referencia exactamente un Producto.
- Un Detalle de Compra puede originar como máximo un Lote (relación opcional); un Lote puede no provenir de ningún Detalle de Compra registrado (por ejemplo, cargas iniciales de stock).
- Un Producto tiene muchos Lotes; cada Lote pertenece a exactamente un Producto.
- Un Lote tiene muchos Movimientos de Inventario; cada Movimiento de Inventario afecta a exactamente un Lote.
- Un Usuario genera muchos Movimientos de Inventario; cada Movimiento de Inventario es generado por exactamente un Usuario.
- Un Usuario (rol cajero) registra muchas Ventas; cada Venta es atendida por exactamente un Usuario.
- Una Venta contiene muchos Detalles de Venta; cada Detalle de Venta pertenece a exactamente una Venta.
- Un Lote es vendido en muchos Detalles de Venta; cada Detalle de Venta referencia exactamente un Lote.
- Una Venta puede tener muchas Devoluciones (por ejemplo, devoluciones parciales en distintas fechas); cada Devolución corresponde a exactamente una Venta.
- Un Usuario autoriza muchas Devoluciones; cada Devolución es autorizada por exactamente un Usuario.
- Una Devolución contiene muchos Detalles de Devolución; cada Detalle de Devolución pertenece a exactamente una Devolución.
- Un Detalle de Venta puede estar referenciado en muchos Detalles de Devolución; cada Detalle de Devolución referencia exactamente un Detalle de Venta.
- Un Usuario genera muchos Logs de Auditoría; cada Log de Auditoría es generado por exactamente un Usuario.

## 6. Cardinalidades

| Relación | Cardinalidad | Justificación |
|---|---|---|
| Rol — Usuario | 1:N | `usuarios.id_rol` es FK NOT NULL hacia `roles`; un rol admite múltiples usuarios |
| Categoría — Producto | 1:N | `productos.id_categoria` es FK NOT NULL; una categoría agrupa múltiples productos |
| Proveedor — Compra | 1:N | `compras.id_proveedor` es FK NOT NULL |
| Usuario — Compra | 1:N | `compras.id_usuario_almacen` es FK NOT NULL |
| Compra — Detalle de Compra | 1:N | `detalle_compras.id_compra` es FK NOT NULL |
| Producto — Detalle de Compra | 1:N | `detalle_compras.id_producto` es FK NOT NULL |
| Detalle de Compra — Lote | 1:0..1 | `lotes.id_detalle_compra` es FK nullable; el lote puede existir sin ese origen |
| Producto — Lote | 1:N | `lotes.id_producto` es FK NOT NULL |
| Lote — Movimiento de Inventario | 1:N | `movimientos_inventario.id_lote` es FK NOT NULL |
| Usuario — Movimiento de Inventario | 1:N | `movimientos_inventario.id_usuario` es FK NOT NULL |
| Usuario — Venta | 1:N | `ventas.id_usuario_cajero` es FK NOT NULL |
| Venta — Detalle de Venta | 1:N | `detalle_ventas.id_venta` es FK NOT NULL |
| Lote — Detalle de Venta | 1:N | `detalle_ventas.id_lote` es FK NOT NULL |
| Venta — Devolución | 1:N | `devoluciones.id_venta` es FK NOT NULL; el esquema permite más de una devolución por venta |
| Usuario — Devolución | 1:N | `devoluciones.id_usuario_autoriza` es FK NOT NULL |
| Devolución — Detalle de Devolución | 1:N | `detalle_devoluciones.id_devolucion` es FK NOT NULL |
| Detalle de Venta — Detalle de Devolución | 1:N | `detalle_devoluciones.id_detalle_venta` es FK NOT NULL; el esquema no impone unicidad, por lo que en teoría admite múltiples devoluciones sobre la misma línea vendida |
| Usuario — Log de Auditoría | 1:N | `logs_auditoria.id_usuario` es FK NOT NULL |

## 7. Reglas iniciales de integridad

- RI-01: Todo Usuario debe tener asociado exactamente un Rol vigente (`id_rol` NOT NULL).
- RI-02: Todo Producto debe pertenecer a exactamente una Categoría (`id_categoria` NOT NULL).
- RI-03: El `codigo_barras` de un Producto es único en todo el sistema.
- RI-04: El `nit_documento` de un Proveedor es único en todo el sistema.
- RI-05: Toda Compra debe estar asociada a un Proveedor y a un Usuario que la recibe.
- RI-06: La `cantidad_recibida` de un Detalle de Compra puede diferir de la `cantidad_esperada` (el esquema no fuerza igualdad), lo cual habilita el registro de faltantes o sobrantes en la recepción.
- RI-07: Un Lote siempre pertenece a un Producto; su vínculo con un Detalle de Compra es opcional.
- RI-08: El `stock_actual` de un Lote se ve reflejado y debe ser consistente con el histórico de `movimientos_inventario` de ese lote (regla de negocio no impuesta por el esquema, se sugiere validar con trigger o a nivel de aplicación).
- RI-09: Toda Venta debe estar asociada a un Usuario cajero; el `nro_comprobante` es único en todo el sistema.
- RI-10: Un Detalle de Venta se vincula a un Lote específico (no solo al Producto), lo que permite trazabilidad de qué lote exacto fue vendido.
- RI-11: Toda Devolución debe referenciar una Venta existente y un Usuario que la autoriza.
- RI-12: Un Detalle de Devolución debe referenciar un Detalle de Venta existente; la cantidad devuelta debería ser menor o igual a la cantidad originalmente vendida en esa línea (regla de negocio no impuesta por el esquema).
- RI-13: Todo registro en Logs de Auditoría debe estar asociado a un Usuario que ejecutó la acción.

## 8. Dudas y decisiones

- D-01: El esquema no incluye una entidad Cliente. Se asume que las ventas son a consumidor final no identificado. **Pendiente confirmar** si el negocio requiere trazabilidad del comprador (ej. ventas con receta médica, crédito, fidelización).
- D-02: No existe una entidad de Caja/Turno de caja ni de Sesión de trabajo; solo se registra qué usuario cajero hizo la venta. **Pendiente confirmar** si se requiere apertura/cierre de caja con arqueo.
- D-03: `compras.nro_factura_proveedor` no tiene restricción UNIQUE en el esquema. **Pendiente decidir** si debería ser único por proveedor (para evitar cargar la misma factura dos veces).
- D-04: La relación entre Detalle de Venta y Detalle de Devolución no restringe (a nivel de esquema) que la suma de cantidades devueltas no supere la cantidad vendida. **Pendiente definir** si esta validación se resuelve por trigger, aplicación, o vista de control.
- D-05: No hay una entidad explícita de "Ajuste de inventario" distinta de `movimientos_inventario.tipo_movimiento`; se asume que ese campo (tipo texto libre `VARCHAR(50)`) codifica valores como entrada, salida, ajuste, merma, etc. **Pendiente definir** el catálogo cerrado de valores permitidos.
- D-06: `detalle_devoluciones.estado_producto_devuelto` es también texto libre. **Pendiente definir** catálogo de estados (ej. reintegrable a stock, dado de baja, en cuarentena).

## 9. Trazabilidad inicial

| Concepto/relación | RN/RF asociado |
|---|---|
| Rol — Usuario | RF de gestión de accesos y permisos |
| Categoría — Producto | RF de gestión de catálogo |
| Proveedor — Compra — Detalle de Compra | RF de recepción de mercadería / abastecimiento |
| Detalle de Compra — Lote | RN de trazabilidad de origen de stock |
| Producto — Lote | RN de control de vencimientos por lote |
| Lote — Movimiento de Inventario | RF de kardex / control de stock |
| Usuario — Venta — Detalle de Venta — Lote | RF de punto de venta con trazabilidad de lote vendido |
| Venta — Devolución — Detalle de Devolución | RF de gestión de devoluciones |
| Usuario — Log de Auditoría | RN de trazabilidad y auditoría del sistema |

