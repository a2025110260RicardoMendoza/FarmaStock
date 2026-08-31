# Decisiones técnicas y de producto

Esta carpeta almacenará las decisiones relevantes del proyecto **FarmaStock 360 – Gestión Operativa de Farmacia, Inventario por Lotes y Ventas**, en forma de ADR (Architecture Decision Records) o notas equivalentes.

Cada decisión importante deberá documentarse indicando:

1. **Contexto** — situación y restricciones del proyecto en el momento de la decisión.
2. **Problema o decisión** — qué se necesita resolver o definir.
3. **Alternativas consideradas** — opciones evaluadas, con sus ventajas y desventajas.
4. **Decisión tomada** — la opción elegida y su justificación.
5. **Consecuencias conocidas** — impactos, riesgos o deuda técnica que resultan de la decisión.

---

## ADR-000 · Clase 01 — Estado inicial del proyecto

**Estado:** Sin decisión arquitectónica formal registrada.

**Contexto:**
El proyecto FarmaStock 360 busca reemplazar registros dispersos en hojas de cálculo por una plataforma que controle productos, lotes, fechas de vencimiento, proveedores, compras, ventas, devoluciones y alertas de stock. El sistema es estrictamente administrativo y comercial: no diagnostica, prescribe ni recomienda tratamientos. El equipo cuenta con el documento de contexto del cliente (situación, objetivo contractual, actores, alcance funcional, reglas de negocio, modelo de información mínimo, requisitos funcionales, requisitos de experiencia web/móvil, flujo crítico, API REST mínima esperada y criterios de aceptación), pero **aún no se ha iniciado el diseño técnico detallado**.

**Problema o decisión:**
No aplica todavía. En esta clase (Clase 01) el trabajo se limitó a comprender el encargo, no a tomar decisiones de arquitectura, modelado o tecnología.

**Alternativas consideradas:**
No aplica — no se evaluaron alternativas técnicas en esta etapa.

**Decisión tomada:**
No se registra ninguna decisión arquitectónica formal en la Clase 01. Esta carpeta queda preparada para documentar, a partir del diseño técnico detallado, decisiones como: modelo relacional (PostgreSQL), estructura del backend (Java 21 / Spring Boot, arquitectura por puertos y adaptadores), organización del frontend web (React + TypeScript) y móvil (React Native + TypeScript), estrategia de autenticación/autorización por roles, manejo de reglas de negocio (p. ej. FEFO, bloqueo de lotes vencidos), uso acotado de Spring AI, y estrategia de CI/CD (GitHub Actions, Docker/Compose).

**Consecuencias conocidas:**
- Al no existir decisiones formales, cualquier definición técnica tomada informalmente durante esta fase debe tratarse como provisional.
- Las próximas decisiones (a partir de la Parte I del proyecto: dominio, requisitos, datos y backend fundacional) deberán registrarse aquí como ADR-001, ADR-002, etc., siguiendo el mismo formato.
- El alcance funcional cerrado y las reglas de negocio obligatorias (RN-01 a RN-10) ya definidos por el cliente condicionarán fuertemente las decisiones de modelado que se tomen más adelante (p. ej. trazabilidad por lote, prioridad FEFO, auditoría mínima).

---

## Plantilla para futuras decisiones

```markdown
## ADR-XXX · [Título breve de la decisión]

**Estado:** [Propuesta | Aceptada | Reemplazada por ADR-YYY]

**Contexto:**
[Situación y restricciones relevantes]

**Problema o decisión:**
[Qué se necesita resolver]

**Alternativas consideradas:**
- Opción A: ...
- Opción B: ...

**Decisión tomada:**
[Opción elegida y justificación]

**Consecuencias conocidas:**
[Impactos, riesgos, deuda técnica]
```