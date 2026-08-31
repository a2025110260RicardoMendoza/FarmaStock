# Visión v0.1 — FarmaStock 360

## 1. Contexto
Una farmacia de tamaño mediano necesita reemplazar registros dispersos en hojas de cálculo y cuadernos por una plataforma centralizada[cite: 1]. 

## 2. Problema
La operación actual con registros manuales dificulta el control preciso de productos, lotes y fechas de vencimiento, además de generar falta de trazabilidad entre los proveedores, las compras, las ventas y las devoluciones[cite: 1]. La falta de alertas automatizadas puede ocasionar pérdida de productos por caducidad o quiebres de stock.

## 3. Objetivo
Construir un MVP web y móvil que permita operar el inventario farmacéutico por lote, registrar el abastecimiento y la venta, mantener la trazabilidad de los movimientos y advertir oportunamente sobre vencimientos y niveles mínimos[cite: 1]. 

## 4. Actores
### Administrador
Configura usuarios, catálogos, parámetros, proveedores y consulta indicadores[cite: 1].

### Encargado de almacén
Registra recepciones, lotes, ajustes y controla vencimientos/stock[cite: 1].

### Cajero
Busca productos disponibles y registra ventas/devoluciones permitidas[cite: 1].

### Supervisor
Revisa cierres, movimientos excepcionales y alertas[cite: 1].

### Cliente
Actor externo de la venta; no requiere autenticación en el MVP[cite: 1].

## 5. Alcance del MVP
1. Usuarios y roles[cite: 1].
2. Productos y categorías[cite: 1].
3. Proveedores[cite: 1].
4. Compras y recepción[cite: 1].
5. Lotes y vencimientos[cite: 1].
6. Inventario y movimientos[cite: 1].
7. Ventas y detalle[cite: 1].
8. Devoluciones controladas[cite: 1].
9. Alertas y tablero[cite: 1].
10. Auditoría básica[cite: 1].

## 6. Exclusiones
No incluye el diagnóstico de pacientes, prescripción médica, ni recomendación de tratamientos[cite: 1]. El sistema no debe emitir recomendaciones médicas ni sustituir una receta médica, y la inteligencia artificial no se usará para recetar o recomendar medicamentos[cite: 1].

## 7. Éxito inicial del proyecto
El proyecto será exitoso cuando pueda demostrar un flujo de extremo a extremo: configuración de productos, registro de compra por lotes, actualización de stock, y confirmación de una venta que descuente inventario aplicando la regla FEFO[cite: 1]. Todo esto debe ser validado por un supervisor, consumido por interfaces web y móvil sobre el mismo backend y con datos reales persistidos[cite: 1].