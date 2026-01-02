package com.shunya.simulation;

import com.shunya.core.Constants;
import com.shunya.core.FractalNode;
import com.shunya.core.Guna;
import com.shunya.entities.Jiva;
import com.shunya.entities.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulation (अनुकरण) — Main Entry Point for Shunya-0 Simulation Engine
 * 
 * Vedic: The Lila (लीला) of Brahman — the cosmic play
 * Simulation: The main loop that runs the fractal universe
 * 
 * This class orchestrates:
 * - Entity creation and lifecycle
 * - Tick processing (Swasa management)
 * - Karma propagation (forward and backward passes)
 * - Stability mechanisms (Vishnu/Shiva)
 * - Reality calculations
 * 
 * Shruti Pramana:
 * > "यदा भूतपृथग्भावमेकस्थमनुपश्यति"
 * > "Yada bhuta-prithag-bhavam ekastham anupasyati"
 * > "When one sees the diverse existence as rooted in the One"
 * > — Bhagavad Gita 13.30
 */
public class Simulation {
    
    // ═══════════════════════════════════════════════════════════════════
    // CONFIGURATION
    // ═══════════════════════════════════════════════════════════════════
    
    private final Level baseLevel;
    private final RealityCalculator.Yuga currentYuga;
    private final RealityCalculator calculator;
    
    // ═══════════════════════════════════════════════════════════════════
    // STATE
    // ═══════════════════════════════════════════════════════════════════
    
    private final List<FractalNode> rootNodes;
    private long globalTick;
    private boolean running;
    
    // Simulation timing
    private static final int TICKS_PER_CYCLE = 100;
    private static final int STABILIZE_INTERVAL = 10;
    private static final int GC_INTERVAL = 50;
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Create a new simulation at the Human level (default)
     */
    public Simulation() {
        this(Level.HUMAN, RealityCalculator.Yuga.DVAPARA);
    }
    
    /**
     * Create a new simulation at a specific level
     * 
     * @param baseLevel The primary level to simulate
     * @param yuga The current Yuga
     */
    public Simulation(Level baseLevel, RealityCalculator.Yuga yuga) {
        this.baseLevel = baseLevel;
        this.currentYuga = yuga;
        this.calculator = new RealityCalculator(yuga);
        this.rootNodes = new ArrayList<>();
        this.globalTick = 0;
        this.running = false;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // ENTITY CREATION
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Create a new Jiva (conscious entity) in the simulation
     * 
     * @param id Unique identifier
     * @return The created Jiva
     */
    public Jiva createJiva(String id) {
        return createJiva(id, baseLevel, Guna.balanced());
    }
    
    /**
     * Create a Jiva with custom Guna state
     * 
     * @param id Unique identifier
     * @param level Fractal level
     * @param guna Initial Guna state
     * @return The created Jiva
     */
    public Jiva createJiva(String id, Level level, Guna guna) {
        Jiva jiva = new Jiva(id, level, guna);
        rootNodes.add(jiva);
        return jiva;
    }
    
    /**
     * Add an existing node to the simulation
     */
    public void addNode(FractalNode node) {
        rootNodes.add(node);
    }
    
    /**
     * Remove a node from the simulation
     */
    public void removeNode(FractalNode node) {
        rootNodes.remove(node);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SIMULATION LOOP
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Run the simulation for a specified number of ticks
     * 
     * @param ticks Number of ticks to simulate
     */
    public void run(long ticks) {
        running = true;
        long endTick = globalTick + ticks;
        
        while (running && globalTick < endTick) {
            step();
        }
        
        running = false;
    }
    
    /**
     * Run a single simulation step
     */
    public void step() {
        globalTick++;
        
        // 1. SRISHTI (Creation/Forward Pass) — Process all nodes
        for (FractalNode node : new ArrayList<>(rootNodes)) {
            processForward(node);
        }
        
        // 2. STHITI (Maintenance) — Stabilize if needed
        if (globalTick % STABILIZE_INTERVAL == 0) {
            stabilizeAll();
        }
        
        // 3. LAYA (Dissolution/Backward Pass) — Propagate karma
        for (FractalNode node : new ArrayList<>(rootNodes)) {
            processBackward(node);
        }
        
        // 4. SHIVA (Garbage Collection) — Clean up dissolved nodes
        if (globalTick % GC_INTERVAL == 0) {
            garbageCollect();
        }
        
        // 5. Tick all nodes
        tickAll();
    }
    
    /**
     * Process forward pass (experience/action)
     */
    private void processForward(FractalNode node) {
        // Generate dummy input (in real simulation, this comes from environment)
        double[] input = new double[]{Math.random(), Math.random(), Math.random()};
        
        // Forward pass through node
        double[] output = node.forward(input);
        
        // Recursively process children
        for (FractalNode child : node.getChildren()) {
            processForward(child);
        }
    }
    
    /**
     * Process backward pass (learning/karma)
     */
    private void processBackward(FractalNode node) {
        // Calculate loss (distance from optimal state)
        double loss = calculateLoss(node);
        
        // Backward pass
        node.backward(loss);
        
        // Update weights periodically
        if (globalTick % TICKS_PER_CYCLE == 0) {
            node.updateWeights();
        }
        
        // Recursively process children
        for (FractalNode child : node.getChildren()) {
            processBackward(child);
        }
    }
    
    /**
     * Calculate loss for a node
     */
    private double calculateLoss(FractalNode node) {
        // Loss = distance from optimal Sattva state
        Guna guna = node.getGuna();
        double sattvaLoss = 1.0 - guna.getSattva();
        double karmaLoss = Math.abs(node.getKarmaBalance());
        
        return sattvaLoss * 0.5 + karmaLoss * 0.5;
    }
    
    /**
     * Stabilize all nodes (Vishnu function)
     */
    private void stabilizeAll() {
        for (FractalNode node : rootNodes) {
            stabilizeRecursive(node);
        }
    }
    
    private void stabilizeRecursive(FractalNode node) {
        node.stabilize();
        for (FractalNode child : node.getChildren()) {
            stabilizeRecursive(child);
        }
    }
    
    /**
     * Garbage collect dissolved nodes (Shiva function)
     */
    private void garbageCollect() {
        // Remove dissolved root nodes
        rootNodes.removeIf(node -> 
            node.getStage() == FractalNode.LifecycleStage.VINASHYATI);
        
        // GC children of remaining nodes
        for (FractalNode node : rootNodes) {
            node.gc();
        }
    }
    
    /**
     * Tick all nodes
     */
    private void tickAll() {
        for (FractalNode node : new ArrayList<>(rootNodes)) {
            tickRecursive(node);
        }
    }
    
    private void tickRecursive(FractalNode node) {
        node.tick();
        for (FractalNode child : node.getChildren()) {
            tickRecursive(child);
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CONTROL
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Stop the simulation
     */
    public void stop() {
        running = false;
    }
    
    /**
     * Check if simulation is running
     */
    public boolean isRunning() {
        return running;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CALCULATIONS (Delegated to RealityCalculator)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate experiential time for a Jiva
     */
    public double calculateExperientialTime(Jiva jiva, double chronologicalYears) {
        return calculator.calculateExperientialYears(chronologicalYears, jiva.getGuna());
    }
    
    /**
     * Generate reality report for a Jiva
     */
    public String generateReport(Jiva jiva, int chronologicalAge) {
        return calculator.generateReport(chronologicalAge, jiva.getGuna());
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public Level getBaseLevel() { return baseLevel; }
    public RealityCalculator.Yuga getCurrentYuga() { return currentYuga; }
    public RealityCalculator getCalculator() { return calculator; }
    public List<FractalNode> getRootNodes() { return new ArrayList<>(rootNodes); }
    public long getGlobalTick() { return globalTick; }
    
    // ═══════════════════════════════════════════════════════════════════
    // STATISTICS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Get total node count in simulation
     */
    public int getTotalNodeCount() {
        int count = 0;
        for (FractalNode node : rootNodes) {
            count += countRecursive(node);
        }
        return count;
    }
    
    private int countRecursive(FractalNode node) {
        int count = 1;
        for (FractalNode child : node.getChildren()) {
            count += countRecursive(child);
        }
        return count;
    }
    
    /**
     * Get simulation statistics
     */
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("╔═══════════════════════════════════════════════════════════════╗\n");
        stats.append("║                   SIMULATION STATISTICS                       ║\n");
        stats.append("╠═══════════════════════════════════════════════════════════════╣\n");
        stats.append(String.format("║ Base Level: %s                                              ║\n", 
            baseLevel.getCode()));
        stats.append(String.format("║ Current Yuga: %s (Dharma: %d/4)                             ║\n", 
            currentYuga.name(), currentYuga.getDharmaUnit()));
        stats.append(String.format("║ Global Tick: %,d                                          ║\n", 
            globalTick));
        stats.append(String.format("║ Root Nodes: %d                                              ║\n", 
            rootNodes.size()));
        stats.append(String.format("║ Total Nodes: %d                                             ║\n", 
            getTotalNodeCount()));
        stats.append(String.format("║ Running: %s                                                 ║\n", 
            running ? "Yes" : "No"));
        stats.append("╚═══════════════════════════════════════════════════════════════╝\n");
        
        return stats.toString();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // MAIN (Demo/Test)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Demo main method
     */
    public static void main(String[] args) {
        System.out.println("🌀 SHUNYA-0 SIMULATION ENGINE");
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        // Create simulation
        Simulation sim = new Simulation(Level.HUMAN, RealityCalculator.Yuga.DVAPARA);
        
        // Create a Jiva with custom Guna
        Guna userGuna = new Guna(0.3, 0.4, 0.3);  // Example: Rajasic person
        Jiva user = sim.createJiva("user-001", Level.HUMAN, userGuna);
        
        System.out.println("Created Jiva: " + user);
        System.out.println();
        
        // Run simulation for 100 ticks
        System.out.println("Running simulation for 100 ticks...\n");
        sim.run(100);
        
        // Print statistics
        System.out.println(sim.getStatistics());
        
        // Generate reality report
        System.out.println(sim.generateReport(user, 35));
        
        // Show time dilation comparison
        System.out.println("\n📊 TIME DILATION COMPARISON:");
        System.out.println("────────────────────────────────────────────────────────────────");
        
        Guna[] testGunas = {
            new Guna(0.1, 0.4, 0.5),  // Kali average
            new Guna(0.3, 0.4, 0.3),  // Balanced
            new Guna(0.5, 0.3, 0.2),  // Dvapara average
            new Guna(0.8, 0.15, 0.05) // Optimal
        };
        
        String[] labels = {"Kali Average", "Balanced", "Dvapara Avg", "Optimal"};
        
        RealityCalculator calc = new RealityCalculator(RealityCalculator.Yuga.DVAPARA);
        
        for (int i = 0; i < testGunas.length; i++) {
            double td = calc.calculateTimeDilation(testGunas[i]);
            double exp50 = calc.calculateExperientialYears(50, testGunas[i]);
            System.out.printf("%s: L_m=%.2f → 50 years feels like %.1f years%n",
                labels[i], td, exp50);
        }
        
        System.out.println("\n🕉️ ॐ तत् सत्");
    }
}

