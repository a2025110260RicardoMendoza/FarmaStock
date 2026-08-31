\# FarmaStock 360

Sistema académico full-stack para la gestión operativa de farmacia, inventario por lotes y ventas\[cite: 1].



\## 1. Problema

Una farmacia de tamaño mediano necesita reemplazar registros dispersos en hojas de cálculo y cuadernos por una plataforma centralizada\[cite: 1]. Esta plataforma debe controlar productos, lotes, fechas de vencimiento, proveedores, compras, ventas, devoluciones y alertas de stock\[cite: 1]. Se requiere que el sistema sea estrictamente administrativo y comercial, ya que no debe diagnosticar, prescribir ni recomendar tratamientos médicos\[cite: 1].



\## 2. Objetivo del MVP

Construir un MVP web y móvil que permita operar el inventario farmacéutico por lote y registrar tanto el abastecimiento como la venta\[cite: 1]. Debe mantener la trazabilidad de los movimientos y advertir oportunamente sobre vencimientos y niveles mínimos de stock\[cite: 1]. Además, debe contar con roles diferenciados para administración, almacén y caja\[cite: 1].



\## 3. Actores principales

\* \*Administrador:\* Configura usuarios, catálogos, parámetros, proveedores y consulta indicadores\[cite: 1].

\* \*Encargado de almacén:\* Registra recepciones, lotes, ajustes y controla vencimientos/stock\[cite: 1].

\* \*Cajero:\* Busca productos disponibles y registra ventas o devoluciones permitidas\[cite: 1].

\* \*Supervisor:\* Revisa cierres, movimientos excepcionales y alertas\[cite: 1].

\* \*Cliente:\* Actor externo de la venta que no requiere autenticación en el MVP\[cite: 1].



\## 4. Alcance inicial

\* Usuarios y roles\[cite: 1].

\* Productos y categorías\[cite: 1].

\* Proveedores\[cite: 1].

\* Compras y recepción\[cite: 1].

\* Lotes y vencimientos\[cite: 1].

\* Inventario y movimientos\[cite: 1].

\* Ventas y detalle\[cite: 1].

\* Devoluciones controladas\[cite: 1].

\* Alertas y tablero\[cite: 1].

\* Auditoría básica\[cite: 1].



\## 5. Fuera de alcance

\* Emisión de recomendaciones médicas o sustitución de recetas\[cite: 1].

\* Diagnósticos o prescripción de tratamientos\[cite: 1].

\* Recomendación de medicamentos mediante inteligencia artificial\[cite: 1].



\## 6. Stack objetivo del semestre

\* \*Backend:\* Java 21 + Spring Boot\[cite: 1].

\* \*Base de datos:\* PostgreSQL con soporte para migraciones\[cite: 1].

\* \*Web:\* React + TypeScript\[cite: 1].

\* \*Móvil:\* React Native + TypeScript\[cite: 1].

\* \*Contenedores:\* Docker / Docker Compose\[cite: 1].

\* \*Versionado e Integración:\* Git + GitHub Actions (CI para build y tests automáticos)\[cite: 1].

\* \*IA:\* Spring AI, limitado únicamente a resumir alertas operativas o generar explicaciones textuales del estado de inventario\[cite: 1].



\## 7. Estado actual

Clase 01: comprensión del problema, alcance, lenguaje inicial del dominio y backlog v0.1. Todavía no existe código de aplicación.



\## 8. Documentación

\* docs/01-vision/vision-v0.1.md

\* docs/01-vision/glossary-v0.1.md

\* docs/02-requirements/backlog-v0.1.md

\* docs/03-decisions/README.md

\* docs/04-model/model-conceptual-v0.1.md


\## 9. Regla de trabajo

Cada cambio importante debe ser comprensible, trazable y defendible. El repositorio es la fuente de verdad del proyecto.

