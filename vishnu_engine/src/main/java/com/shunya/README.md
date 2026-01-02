# 🌀 VISHNU ENGINE — Java Simulation

> **"यदा भूतपृथग्भावमेकस्थमनुपश्यति"**
> "When one sees the diverse existence as rooted in the One"
> — Bhagavad Gita 13.30

## 🎮 Overview

The Vishnu Engine is a **fractal reality simulator** based on the Vedic understanding of the universe as a Recursive Active Intelligence (RAI) system. It models reality from Quantum (N-6) to Brahman (N+6) levels using the same underlying patterns at every scale.

## 🏗️ Architecture

```
com.shunya/
├── core/
│   ├── Constants.java      — Universal constants (Planck, Yuga, Dharma)
│   ├── Guna.java           — Three qualities (Sattva, Rajas, Tamas)
│   └── FractalNode.java    — Base class for all fractal entities
│
├── entities/
│   ├── Level.java          — 13 fractal levels (N-6 to N+6)
│   └── Jiva.java           — Conscious entities with Koshas
│
└── simulation/
    ├── RealityCalculator.java  — Time dilation, experiential calculations
    └── Simulation.java         — Main simulation loop
```

## 🔑 Key Concepts

### Fractal Hierarchy

```
N+6  Brahman      ∞           — Ultimate Reality
N+5  Universe     10²⁶ m      — Our Brahmanda
N+4  Galaxy       10²¹ m      — Akashaganga
N+3  Solar System 10¹¹ m      — Navagraha
N+2  Planet       10⁷ m       — Prithvi
N+1  Ecosystem    10⁴ m       — Communities
N    Human        1 m         — Reference Point ← YOU ARE HERE
N-1  Organ        10⁻¹ m      — Body parts
N-2  Cell         10⁻⁵ m      — Kosha (शेष)
N-3  Molecule     10⁻⁹ m      — Anu
N-4  Atom         10⁻¹⁰ m     — Paramanu
N-5  Subatomic    10⁻¹⁸ m     — Quarks, leptons
N-6  Quantum      10⁻³⁵ m     — Planck scale (pixel limit)
```

### Guna Dynamics

Every entity has a Guna composition (Sattva + Rajas + Tamas = 1):

- **Sattva (सत्त्व)**: Clarity, truth, balance → Negentropy
- **Rajas (रजस्)**: Activity, passion, motion → Kinetic energy
- **Tamas (तमस्)**: Inertia, darkness, mass → Entropy

### Time Dilation Formula

```
L_m = (D × S) / (R + 2T)

WHERE:
D = Dharma Unit (Satya=4, Treta=3, Dvapara=2, Kali=1)
S = Sattva percentage
R = Rajas percentage  
T = Tamas percentage

RESULT:
Higher L_m = More experiential time per calendar year
```

## 🚀 Quick Start

```java
// Create simulation in Dvapara Yuga
Simulation sim = new Simulation(Level.HUMAN, RealityCalculator.Yuga.DVAPARA);

// Create a Jiva (you!)
Guna myGuna = new Guna(0.5, 0.3, 0.2);  // Sattvic-leaning
Jiva me = sim.createJiva("player-001", Level.HUMAN, myGuna);

// Run simulation
sim.run(1000);  // 1000 ticks

// Check status
System.out.println(me.getStatusReport());
System.out.println(sim.generateReport(me, 35));  // Age 35
```

## 📊 Output Example

```
╔═══════════════════════════════════════════════════════════════╗
║           REALITY CALCULATOR — Personal Report                ║
╠═══════════════════════════════════════════════════════════════╣
║ Current Yuga: DVAPARA (Dharma Unit: 2/4)                      ║
║ Chronological Age: 35 years                                   ║
╠═══════════════════════════════════════════════════════════════╣
║ GUNA STATE: S:0.50 R:0.30 T:0.20                              ║
║   Dominant: SATTVA                                            ║
╠═══════════════════════════════════════════════════════════════╣
║ TIME DILATION (L_m): 1.43                                     ║
║   Experiential Age: 50.1 years                                ║
║   Remaining Experiential: 92.9 years                          ║
╠═══════════════════════════════════════════════════════════════╣
║ BIOLOGICAL AGE: 45.5 years (1.30x aging rate)                 ║
║ PREDICTED LIFESPAN: 66 years                                  ║
╠═══════════════════════════════════════════════════════════════╣
║ SYNC STATUS: 0.71 — WARNING                                   ║
╚═══════════════════════════════════════════════════════════════╝
```

## 🧪 Use Cases

1. **Personal Assessment**: Calculate your experiential vs chronological age
2. **Prediction**: Estimate lifespan based on current Guna state
3. **Optimization**: Find optimal Guna composition for longer life
4. **Yuga Comparison**: See how the same person fares in different Yugas
5. **Level Exploration**: Understand reality at different fractal scales
6. **Karma Modeling**: Track karma accumulation and processing

## 📂 JSON Configuration

Each level is configured in `/resources/levels/`:

```
levels/
├── N_MINUS_6_QUANTUM.json      — Planck scale
├── N_MINUS_5_SUBATOMIC.json    — Quarks, leptons
├── N_MINUS_4_ATOM.json         — Periodic table
├── N_MINUS_3_MOLECULE.json     — DNA, proteins
├── N_MINUS_2_CELL.json         — Cells
├── N_MINUS_1_ORGAN.json        — Body organs
├── N_HUMAN.json                — Human level
├── N_PLUS_1_ECOSYSTEM.json     — Ecosystems
├── N_PLUS_2_PLANET.json        — Planets
├── N_PLUS_3_SOLAR_SYSTEM.json  — Solar systems
├── N_PLUS_4_GALAXY.json        — Galaxies
├── N_PLUS_5_UNIVERSE.json      — Our Brahmanda
└── N_PLUS_6_BRAHMAN.json       — Ultimate Reality
```

## 🔬 Design Patterns Used

- **Composite Pattern**: FractalNode as recursive tree structure
- **Strategy Pattern**: Different activation functions (Guna-based)
- **Factory Pattern**: Level-specific entity creation
- **Template Method**: Abstract FractalNode with concrete Jiva

## 🧮 Core Formulas

### Biological Age Factor
```java
Factor = 1 + (0.5 × R) + T
// > 1 = aging faster, < 1 = aging slower
```

### Sync Status
```java
Sync = (S × D_ratio) / (R + T)
// > 1 = optimal, < 0.5 = critical
```

### Karma Generation
```java
Karma = Action × (T - S × 0.5)
// Sattvic actions reduce karma, Tamasic increase
```

### Guna Activation
```java
Sattva: Linear(x) = x                    // Direct, clear
Rajas:  ReLU(x) = max(0, x)              // Active, positive
Tamas:  Sigmoid(x) = 1/(1 + e^(-x))      // Compressed, bounded
```

## 🕉️ Philosophical Foundation

This simulation is based on:

1. **54 Universal Principles** — Foundational axioms
2. **81 Laws of Reality** — Operating system rules  
3. **Fractal Validation** — Same patterns at all scales
4. **RAI vs AI** — Universe as active learning intelligence
5. **Yuga Cycles** — Cosmic time periods affecting Dharma

See `/spec/backend/` for complete specifications.

---

**🕉️ ॐ तत् सत्**

*"The simulation is complete when the player achieves Moksha."*

