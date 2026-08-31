# Glosario v0.1 — FarmaStock 360

| Término | Significado inicial |
|---|---|
| Producto | Artículo farmacéutico gestionado en el sistema, que puede estar activo o inactivo[cite: 1]. |
| Categoría | Clasificación lógica que agrupa los productos[cite: 1]. |
| Lote | Conjunto de existencias de un producto asociado obligatoriamente a una fecha de vencimiento cuando corresponda[cite: 1]. |
| Stock / Inventario | Cantidad actual disponible de un producto o lote que puede ser vendida[cite: 1]. |
| Cliente | Actor externo de la venta; no requiere autenticación en el MVP[cite: 1]. |
| Proveedor | Entidad a la cual se le registran compras para el abastecimiento de la farmacia[cite: 1]. |
| Compra / Recepción | Evento que registra el ingreso de inventario, creando lotes y existencias[cite: 1]. |
| Venta | Operación que descuenta stock automáticamente, priorizando la salida de los lotes según su vencimiento[cite: 1]. |
| Devolución | Operación vinculada estrictamente a una venta previa, la cual no puede superar la cantidad vendida[cite: 1]. |
| Ajuste | Modificación manual del inventario que requiere un motivo y un usuario responsable[cite: 1]. |
| Bloqueo | Estado aplicado automáticamente a un lote vencido, impidiendo su venta[cite: 1]. |
| FEFO | Regla de negocio (First Expired, First Out) que prioriza la salida de los lotes con vencimiento más próximo[cite: 1]. |
| Kardex | Registro histórico simplificado de todos los movimientos de un producto[cite: 1]. |
| Alerta | Notificación generada por el sistema cuando se alcanza el stock mínimo o existen lotes próximos a vencer[cite: 1]. |

> Este glosario es v0.1. Los significados pueden refinarse cuando aparezcan nuevas reglas, pero los cambios deberán quedar documentados.