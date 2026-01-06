# 🌀 Shunya-0 Simulation Engine — Java Implementation

> **"यथा पिण्डे तथा ब्रह्माण्डे"**
> "As in the microcosm, so in the macrocosm."
> — Yajur Veda

---

## 📁 Package Structure

```
com.shunya/
+-- core/                    # Core abstractions (this package)
|   +-- FractalNode.java     # Base class for ALL nodes
|   +-- Guna.java            # Sattva/Rajas/Tamas state
|   +-- Kosha.java           # 5 sheaths abstraction
|   +-- Constants.java       # Universal constants
|
+-- entities/                # Entity implementations
|   +-- Level.java           # Enum for N-6 to N+6
|   +-- Jiva.java            # Conscious entities
|   +-- Jada.java            # Non-conscious entities
|
+-- karma/                   # Karma system
|   +-- KarmaEngine.java     # Backpropagation
|   +-- Rina.java            # Debt accumulation
|   +-- SankalpaHandler.java # Intent processing
|
+-- maya/                    # Rendering engine
|   +-- RenderingEngine.java # Maya renderer
|   +-- PixelTick.java       # Space-time units
|   +-- LokaRenderer.java    # 14 frequency bands
|
+-- time/                    # Time calculations
|   +-- YugaCalculator.java  # Yuga time dilation
|   +-- SwasaTracker.java    # Tick budget
|
+-- config/                  # JSON configurations
|   +-- LevelConfig.java     # Level loader
|   +-- WorldBuilder.java    # World instantiation
|
+-- simulation/              # Main simulation
    +-- Simulation.java      # Entry point
    +-- RealityCalculator.java # Prediction engine
```

---

## 🎯 Design Patterns Used

| Pattern | Application | Why |
|---------|-------------|-----|
| **Composite** | FractalNode | Every node contains child nodes |
| **Factory** | LevelFactory | Creates N±6 level instances |
| **Builder** | WorldBuilder | Constructs worlds from JSON |
| **Observer** | KarmaEngine | Action->Consequence notification |
| **State** | Guna | S/R/T state transitions |
| **Flyweight** | Constants | Shared immutable values |
| **Template Method** | Kosha | 5 sheaths same pattern |

---

## 🔢 Key Formulas Implemented

### Time Dilation
```java
double L_m = (dharmaUnit * sattva) / (rajas + 2 * tamas);
```

### Karma Resolution
```java
double karmaEffect = Gx * (tamas1 * tamas2) / (distance * distance);
```

### Guna Normalization
```java
double total = sattva + rajas + tamas;
sattva /= total; rajas /= total; tamas /= total;
// Always: sattva + rajas + tamas = 1.0
```

---

## ✨ Quick Start

```java
// 1. Load configuration for Human level (N)
LevelConfig config = LevelConfig.load("levels/N_HUMAN.json");

// 2. Build the world
World world = WorldBuilder.fromConfig(config);

// 3. Run simulation
Simulation sim = new Simulation(world);
sim.run();

// 4. Calculate predictions
RealityCalculator calc = new RealityCalculator(world);
double experientialYears = calc.calculateExperientialTime(80, gunaState);
```

---

## 📊 Level Range: N-6 to N+6

| Level | Name | Scale | JSON Config |
|-------|------|-------|-------------|
| N-6 | Quantum | 10⁻³⁵ m | `levels/N-6_QUANTUM.json` |
| N-5 | Subatomic | 10⁻¹⁸ m | `levels/N-5_SUBATOMIC.json` |
| N-4 | Atom | 10⁻¹⁰ m | `levels/N-4_ATOM.json` |
| N-3 | Molecule | 10⁻⁹ m | `levels/N-3_MOLECULE.json` |
| N-2 | Organelle | 10⁻⁶ m | `levels/N-2_ORGANELLE.json` |
| N-1 | Cell | 10⁻⁵ m | `levels/N-1_CELL.json` |
| **N** | **Human** | 10⁰ m | `levels/N_HUMAN.json` |
| N+1 | Ecosystem | 10³ m | `levels/N+1_ECOSYSTEM.json` |
| N+2 | Planet | 10⁷ m | `levels/N+2_PLANET.json` |
| N+3 | Solar System | 10¹³ m | `levels/N+3_SOLAR.json` |
| N+4 | Galaxy | 10²¹ m | `levels/N+4_GALAXY.json` |
| N+5 | Universe | 10²⁶ m | `levels/N+5_UNIVERSE.json` |
| N+6 | Brahman | ∞ | `levels/N+6_BRAHMAN.json` |

---

## 🧮 Use Cases

### 1. Calculate Experiential Time
```java
// How many experiential years in 50 chronological years?
Guna state = new Guna(0.3, 0.4, 0.3);  // S, R, T
double experiential = calc.experientialYears(50, state, Yuga.DVAPARA);
// Result: 50 * L_m
```

### 2. Predict Biological Age
```java
// What's biological age given lifestyle?
double bioAge = calc.biologicalAge(chronologicalAge, gunaHistory);
```

### 3. Karma Debt Calculation
```java
// Calculate accumulated karma debt
double rinaBalance = karmaEngine.calculateRina(actions, time);
```

### 4. Render Reality at Level
```java
// What does reality look like at quantum level?
RenderOutput output = maya.render(quantumState, observer);
```

---

## 🏗️ Architecture Principles

1. **Fractal First**: Every class follows same pattern at all levels
2. **Config-Driven**: All constants from JSON, no hardcoding
3. **Guna-Normalized**: All states maintain S+R+T=1
4. **Tick-Bounded**: All entities have finite lifespan (Swasa)
5. **Bidirectional**: Karma flows up AND down hierarchy

---

**ॐ तत् सत्**

