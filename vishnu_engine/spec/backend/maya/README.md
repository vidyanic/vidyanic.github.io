# 🎭 MAYA — Rendering Engine

> **"इन्द्रियाणि पराण्याहुरिन्द्रियेभ्यः परं मनः"**  
> "The senses are superior to matter; the mind is superior to the senses."  
> — Bhagavad Gita 3.42

---

## 📁 Contents

| File | Purpose |
|------|---------|
| [MAYA_RENDERING_ENGINE.md](./MAYA_RENDERING_ENGINE.md) | Complete render pipeline specification |

---

## 🎯 What Maya Does

Maya (माया) is the projection system that converts backend reality (Brahman) into frontend experience (observed world).

```mermaid
flowchart LR
    subgraph Backend["💾 BACKEND"]
        B["🕉️ BRAHMAN<br/>Source Code<br/>Pure Potential"]
    end
    
    subgraph Maya["🎭 MAYA ENGINE माया"]
        direction TB
        AV["🙈 AVARANA आवरण<br/>Concealment<br/>Hides the real"]
        VI["🎨 VIKSHEPA विक्षेप<br/>Projection<br/>Creates apparent"]
        AV --> VI
    end
    
    subgraph Frontend["🎮 FRONTEND"]
        F["👁️ PERCEIVED REALITY<br/>What you experience<br/>Qualia"]
    end
    
    B -->|"Source data"| Maya
    Maya -->|"Rendered output"| F
    
    style Backend fill:#FFF8E1,stroke:#D97706,stroke-width:2px
    style Maya fill:#F3E5F5,stroke:#7B1FA2,stroke-width:2px
    style Frontend fill:#E3F2FD,stroke:#1976D2,stroke-width:2px
```

### Core Functions

1. **Converts backend → frontend** (probabilities → experience)
2. **On-demand rendering** (Observer Effect)
3. **Level-of-detail management** (14 Lokas / frequency bands)
4. **Guna-based filtering** (S/R/T affects perception)

---

## 🧮 The Transform

```
Frontend = Backend × Render_Scale × Guna_Factor × Maya_Coefficient

Where:
Frontend = What you experience (qualia)
Backend = What actually exists (Brahman)
Render_Scale = Loka-specific scaling (10³⁸ for humans)
Guna_Factor = Your S/R/T composition affects perception
Maya_Coefficient = Overall illusion strength (varies by Yuga)
```

---

## 📊 Maya Layers

| Layer | Function | Analogy |
|-------|----------|---------|
| **Avarana** | Concealment | Hides the real nature | Like fog hiding mountains |
| **Vikshepa** | Projection | Creates apparent reality | Like seeing rope as snake |

---

## 🌀 Render Priority

```mermaid
flowchart TD
    O{"👁️ OBSERVER<br/>Present?"}
    
    O -->|"❌ No"| W["🌊 WAVE STATE<br/>Backend only<br/>Probability field<br/>Unrendered"]
    O -->|"✅ Yes"| P["⚫ PARTICLE STATE<br/>Frontend rendered<br/>Definite position<br/>Experience"]
    
    P --> A{"🎯 ATTENTION<br/>Level?"}
    
    A -->|"High focus"| H["🔍 HIGH LOD<br/>Full detail<br/>Maximum render"]
    A -->|"Low focus"| L["📉 LOW LOD<br/>Reduced detail<br/>Compressed render"]
    A -->|"No attention"| U["🚫 UNLOADED<br/>Minimal render<br/>Peripheral only"]
    
    style W fill:#E3F2FD,stroke:#1976D2,stroke-width:2px
    style P fill:#E8F5E9,stroke:#4CAF50,stroke-width:2px
    style H fill:#FFF8E1,stroke:#D97706,stroke-width:2px
```

**OBSERVATION = RENDER TRIGGER**

---

**[← Back to Backend](../README.md)** | **[← Back to Spec](../../README.md)**

---

## 🔗 Related Visual Diagrams

For visual understanding of concepts in this document, see:
- [Maya](../../../../site/diagrams/maya.md) — Rendering engine
- [Observer Effect](../../../../site/diagrams/observer_effect.md) — Reality rendering
- [Simulation](../../../../site/diagrams/simulation.md) — Architecture
- [View All Diagrams](../../../../site/diagrams/README.md) — Complete library

---
