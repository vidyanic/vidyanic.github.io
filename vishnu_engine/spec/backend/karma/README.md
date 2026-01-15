# ⚖️ KARMA — Action-Consequence System

> **"कर्मण्येवाधिकारस्ते मा फलेषु कदाचन"**  
> "You have the right to action alone, never to its fruits."  
> — Bhagavad Gita 2.47

---

## 📁 Contents

| File | Purpose |
|------|---------|
| [RINA_DEBT_SYSTEM.md](./RINA_DEBT_SYSTEM.md) | Karmic debt tracking — The 6 types of debt |
| [SANKALPA_BACKPROP.md](./SANKALPA_BACKPROP.md) | Intention as backpropagation — How sankalpa works |
| [FAILURE_MODES.md](./FAILURE_MODES.md) | What happens when karma fails to process |

---

## 🎯 Key Concepts

### Karma System Overview

```mermaid
flowchart TD
    subgraph Warehouse["📦 KARMA WAREHOUSE"]
        SA["💼 SANCHITA संचित<br/>Total Accumulated<br/>All Past Lives"]
    end
    
    subgraph Life["⚡ CURRENT LIFE"]
        PR["📋 PRARABDHA प्रारब्ध<br/>Active Portion<br/>This Life Only"]
        AG["🌱 AGAMI आगामी<br/>Being Created NOW<br/>Current Actions"]
    end
    
    subgraph Storage["💾 DEEP STORAGE"]
        VA["🌊 VASANA वासना<br/>Latent Impressions<br/>Drive tendencies"]
        SM["🔄 SAMSKARA संस्कार<br/>Deep Patterns<br/>Repeated grooves"]
        RI["⚖️ RINA ऋण<br/>Karmic Debt<br/>Most binding"]
    end
    
    SA -->|"Portion selected<br/>at birth"| PR
    PR -->|"Manifests as<br/>experiences"| EXP["🎭 Life Events"]
    EXP -->|"Your choices"| AG
    AG -->|"Adds to total"| SA
    
    AG --> VA --> SM --> RI
    RI -->|"Must be repaid"| SA
    
    style SA fill:#F3E5F5,stroke:#7B1FA2,stroke-width:2px
    style PR fill:#FFF3E0,stroke:#FF9800,stroke-width:2px
    style AG fill:#E8F5E9,stroke:#4CAF50,stroke-width:2px
    style RI fill:#FFEBEE,stroke:#F44336,stroke-width:2px
```

### Karma Types (कर्म-विभाग)

| Type | Sanskrit | Description |
|------|----------|-------------|
| **Sanchita** | संचित | Accumulated — Total backlog from all lives |
| **Prarabdha** | प्रारब्ध | Active — Current life's allocated load |
| **Agami** | आगामी | Future — Being created now by current actions |
| **Vasana** | वासना | Latent — Impressions that drive tendencies |
| **Samskara** | संस्कार | Patterns — Deep grooves from repeated actions |
| **Rina** | ऋण | Debt — Obligations to others (most binding) |

### The Formula

```
F_karma = Gx × (M_tamas_1 × M_tamas_2) / r²

Where:
Gx = Cosmic karma constant
M_tamas = Accumulated tamas (karma mass) of each party
r = Relationship distance (closer = stronger effect)
```

### Karma Resolution

```mermaid
flowchart LR
    K["⚖️ KARMA<br/>To be resolved"]
    
    K --> B["1️⃣ BHOGA भोग<br/>Experience it<br/>Natural consequence"]
    K --> P["2️⃣ PRAYASCHITTA प्रायश्चित्त<br/>Remedial action<br/>Active correction"]
    K --> S["3️⃣ SANKALPA संकल्प<br/>Counter-intention<br/>New positive karma"]
    K --> J["4️⃣ JNANA ज्ञान<br/>Understanding<br/>Dissolves (not deletes)"]
    K --> G["5️⃣ GRACE कृपा<br/>Divine intervention<br/>Rare blessing"]
    
    B --> R["✅ RESOLVED"]
    P --> R
    S --> R
    J --> R
    G --> R
    
    style K fill:#FFEBEE,stroke:#F44336,stroke-width:2px
    style R fill:#E8F5E9,stroke:#4CAF50,stroke-width:2px
```

---

## 📋 On Life's Journey

> **Vedic Understanding:**
> Each life carries allocated purpose (Prarabdha). The path forward
> is through, not around. Completing one's dharma enables proper
> transition. Challenges are teachers. Growth happens through the journey.

---

**[← Back to Backend](../README.md)** | **[← Back to Spec](../../README.md)**

---

## 🔗 Related Visual Diagrams

For visual understanding of concepts in this document, see:
- [Karma](../../../../site/diagrams/karma.md) — Action-consequence system
- [Rina](../../../../site/diagrams/rina.md) — Debt system
- [Sankalpa](../../../../site/diagrams/sankalpa.md) — Intention mechanism
- [Samskaras & Vasanas](../../../../site/diagrams/samskaras_vasanas.md) — Impressions
- [View All Diagrams](../../../../site/diagrams/README.md) — Complete library

---
