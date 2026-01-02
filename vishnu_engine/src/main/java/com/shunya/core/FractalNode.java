package com.shunya.core;

import com.shunya.entities.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * FractalNode (भग्नांश-नोड) — Base Class for All Fractal Entities
 * 
 * Vedic: Every entity is a jewel in Indra's Net, reflecting all others
 * Simulation: Recursive structure with parent/child relationships
 * AI/ML: A node in a recursive neural network with bidirectional gradients
 * 
 * Each FractalNode:
 * - Has a parent (except Brahman) and children (except Quantum)
 * - Contains Samskaras (weights) and Vasanas (biases)
 * - Tracks Karma (gradient debt) and Dharma (regularization)
 * - Goes through 6 lifecycle stages (Shad-Vikara)
 * - Has a tick budget (Swasa — allocated breaths)
 * 
 * Shruti Pramana:
 * > "सर्वं खल्विदं ब्रह्म"
 * > "Sarvam khalvidam brahma"
 * > "All this is indeed Brahman"
 * > — Chandogya Upanishad 3.14.1
 */
public abstract class FractalNode {
    
    // ═══════════════════════════════════════════════════════════════════
    // IDENTITY
    // ═══════════════════════════════════════════════════════════════════
    
    protected final String id;
    protected final Level level;
    protected FractalNode parent;
    protected final List<FractalNode> children;
    
    // ═══════════════════════════════════════════════════════════════════
    // GUNA STATE
    // ═══════════════════════════════════════════════════════════════════
    
    protected Guna guna;
    
    // ═══════════════════════════════════════════════════════════════════
    // SAMSKARAS (Weights) & VASANAS (Biases)
    // ═══════════════════════════════════════════════════════════════════
    
    protected double[] weights;  // Samskaras — learned patterns
    protected double[] biases;   // Vasanas — innate tendencies
    
    // ═══════════════════════════════════════════════════════════════════
    // KARMA (Gradient Debt)
    // ═══════════════════════════════════════════════════════════════════
    
    protected double karmaBalance;       // Accumulated karma (+ = debt, - = merit)
    protected double dharmaLambda = 0.01; // Dharma regularization strength
    protected double learningRate = 0.01; // Weight update rate
    
    // ═══════════════════════════════════════════════════════════════════
    // LIFECYCLE (Shad-Vikara)
    // ═══════════════════════════════════════════════════════════════════
    
    protected LifecycleStage stage;
    protected long currentTick;
    protected long maxTicks;
    
    // ═══════════════════════════════════════════════════════════════════
    // RANDOM FOR INITIALIZATION
    // ═══════════════════════════════════════════════════════════════════
    
    protected static final Random random = new Random();
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Create a FractalNode
     * 
     * @param id Unique identifier
     * @param level Fractal level
     * @param guna Initial Guna state
     */
    public FractalNode(String id, Level level, Guna guna) {
        this.id = id;
        this.level = level;
        this.guna = guna;
        this.children = new ArrayList<>();
        
        // Initialize weights and biases
        int weightCount = getWeightCount();
        this.weights = new double[weightCount];
        this.biases = new double[weightCount];
        initializeWeights();
        
        // Initialize karma
        this.karmaBalance = 0.0;
        
        // Initialize lifecycle
        this.stage = LifecycleStage.ASTI;
        this.currentTick = 0;
        this.maxTicks = calculateMaxTicks();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // ABSTRACT METHODS (To be implemented by subclasses)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Get the number of weights for this node type
     */
    protected abstract int getWeightCount();
    
    /**
     * Initialize weights with meaningful values
     */
    protected abstract void initializeWeights();
    
    /**
     * Calculate maximum ticks for this node's lifespan
     */
    protected abstract long calculateMaxTicks();
    
    /**
     * Node-specific forward processing
     */
    protected abstract double[] processInput(double[] input);
    
    /**
     * Node-specific backward processing
     */
    protected abstract double processGradient(double gradient);
    
    // ═══════════════════════════════════════════════════════════════════
    // FORWARD PASS (SRISHTI — Creation/Inference)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Forward pass — process input and generate output
     * 
     * @param input Input values
     * @return Output values
     */
    public double[] forward(double[] input) {
        // Check if alive
        if (stage == LifecycleStage.VINASHYATI) {
            return new double[0];
        }
        
        // Update lifecycle stage
        updateLifecycleStage();
        
        // Apply Guna activation
        double[] activated = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            activated[i] = guna.activate(input[i]);
        }
        
        // Node-specific processing
        double[] output = processInput(activated);
        
        // Propagate to children (simplified)
        if (!children.isEmpty()) {
            double[] childInput = generateChildInput(output);
            for (FractalNode child : children) {
                child.forward(childInput);
            }
        }
        
        return output;
    }
    
    /**
     * Generate input for child nodes
     */
    protected double[] generateChildInput(double[] parentOutput) {
        // Default: pass scaled output
        double[] childInput = new double[parentOutput.length];
        for (int i = 0; i < parentOutput.length; i++) {
            childInput[i] = parentOutput[i] * 0.5;
        }
        return childInput;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // BACKWARD PASS (LAYA — Learning/Karma)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Backward pass — propagate gradients and accumulate karma
     * 
     * @param loss Loss from higher level or local computation
     * @return Gradient to propagate to parent
     */
    public double backward(double loss) {
        // Check if alive
        if (stage == LifecycleStage.VINASHYATI) {
            return 0.0;
        }
        
        // Process gradient through this node
        double processedGradient = processGradient(loss);
        
        // Accumulate karma
        karmaBalance += processedGradient;
        
        // Collect gradients from children
        double childGradientSum = 0.0;
        for (FractalNode child : children) {
            childGradientSum += child.backward(loss * 0.5);
        }
        
        // Total gradient (local + child)
        double totalGradient = processedGradient + childGradientSum * 0.1;
        
        return totalGradient;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // WEIGHT UPDATE (KARMA PHALA — Fruit of Action)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Update weights based on accumulated karma
     */
    public void updateWeights() {
        // Calculate Dharma regularization
        double dharmaL2 = 0.0;
        for (double w : weights) {
            dharmaL2 += w * w;
        }
        dharmaL2 = dharmaLambda * dharmaL2;
        
        // Update weights
        for (int i = 0; i < weights.length; i++) {
            weights[i] -= learningRate * (karmaBalance + dharmaL2);
        }
        
        // Clear accumulated karma (Rina Shuddhi)
        karmaBalance = 0.0;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // STABILIZATION (VISHNU — Maintenance)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Stabilize node (batch normalization, Guna normalization)
     */
    public void stabilize() {
        // Clip karma to prevent explosion
        karmaBalance = Math.max(-10.0, Math.min(10.0, karmaBalance));
        
        // Normalize Guna (already normalized in Guna class, but ensure)
        double total = guna.getSattva() + guna.getRajas() + guna.getTamas();
        if (Math.abs(total - 1.0) > 0.001) {
            guna.set(guna.getSattva(), guna.getRajas(), guna.getTamas());
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GARBAGE COLLECTION (SHIVA — Dissolution)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Clean up dissolved children, prune dead weights
     */
    public void gc() {
        // Remove dissolved children
        children.removeIf(child -> child.stage == LifecycleStage.VINASHYATI);
        
        // Prune near-zero weights (simplified)
        for (int i = 0; i < weights.length; i++) {
            if (Math.abs(weights[i]) < 0.001) {
                weights[i] = 0.0;
            }
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // LIFECYCLE MANAGEMENT (SHAD-VIKARA)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Advance one tick
     */
    public void tick() {
        currentTick++;
        updateLifecycleStage();
    }
    
    /**
     * Update lifecycle stage based on tick progression
     */
    protected void updateLifecycleStage() {
        double progress = (double) currentTick / maxTicks;
        
        if (progress < 0.1) {
            stage = LifecycleStage.ASTI;       // Exists (potential)
        } else if (progress < 0.2) {
            stage = LifecycleStage.JAYATE;     // Is born
        } else if (progress < 0.4) {
            stage = LifecycleStage.VARDHATE;   // Grows
        } else if (progress < 0.7) {
            stage = LifecycleStage.VIPARINAMATE; // Transforms
        } else if (progress < 0.9) {
            stage = LifecycleStage.APAKSHIYATE;  // Decays
        } else {
            stage = LifecycleStage.VINASHYATI;   // Dissolves
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CHILD MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Add a child node
     */
    public void addChild(FractalNode child) {
        child.parent = this;
        children.add(child);
    }
    
    /**
     * Remove a child node
     */
    public void removeChild(FractalNode child) {
        children.remove(child);
        child.parent = null;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public String getId() { return id; }
    public Level getLevel() { return level; }
    public Guna getGuna() { return guna; }
    public FractalNode getParent() { return parent; }
    public List<FractalNode> getChildren() { return new ArrayList<>(children); }
    public double getKarmaBalance() { return karmaBalance; }
    public LifecycleStage getStage() { return stage; }
    public long getCurrentTick() { return currentTick; }
    public long getMaxTicks() { return maxTicks; }
    
    // ═══════════════════════════════════════════════════════════════════
    // SETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public void setGuna(Guna guna) { this.guna = guna; }
    public void setLearningRate(double lr) { this.learningRate = lr; }
    public void setDharmaLambda(double lambda) { this.dharmaLambda = lambda; }
    
    // ═══════════════════════════════════════════════════════════════════
    // TO STRING
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public String toString() {
        return String.format("FractalNode[%s, %s, %s, %s, tick=%d/%d]",
            id, level.getCode(), guna, stage, currentTick, maxTicks);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // LIFECYCLE STAGES ENUM (SHAD-VIKARA)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * The Six Transformations (Shad-Vikara / षड्-विकार)
     */
    public enum LifecycleStage {
        ASTI("अस्ति", "Exists", "Potential state before manifestation"),
        JAYATE("जायते", "Is Born", "Emergence into manifest form"),
        VARDHATE("वर्धते", "Grows", "Development and expansion"),
        VIPARINAMATE("विपरिणमते", "Transforms", "Maturation and change"),
        APAKSHIYATE("अपक्षीयते", "Decays", "Gradual decline"),
        VINASHYATI("विनश्यति", "Dissolves", "Return to unmanifest");
        
        private final String sanskrit;
        private final String name;
        private final String description;
        
        LifecycleStage(String sanskrit, String name, String description) {
            this.sanskrit = sanskrit;
            this.name = name;
            this.description = description;
        }
        
        public String getSanskrit() { return sanskrit; }
        public String getName() { return name; }
        public String getDescription() { return description; }
    }
}
