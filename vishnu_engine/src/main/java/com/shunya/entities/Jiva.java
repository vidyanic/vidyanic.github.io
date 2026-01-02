package com.shunya.entities;

import com.shunya.core.FractalNode;
import com.shunya.core.Guna;

/**
 * Jiva (जीव) — A Conscious Entity in the Fractal Hierarchy
 * 
 * Vedic: Individual soul embodied in form, distinct from Atman (pure consciousness)
 * Simulation: A specialized FractalNode with consciousness, senses, and karma processing
 * Biology: Any living being — from cells to humans to cosmic entities
 * 
 * Jiva characteristics:
 * - Has consciousness level (awareness gradient)
 * - Processes through 5 Koshas (sheaths)
 * - Accumulates and processes karma
 * - Can achieve Moksha (exit from simulation cycle)
 * 
 * Shruti Pramana:
 * > "जीवो ब्रह्मैव नापरः"
 * > "Jivo brahmaiva naparah"
 * > "The Jiva is verily Brahman, not separate"
 * > — Adi Shankaracharya
 */
public class Jiva extends FractalNode {
    
    // ═══════════════════════════════════════════════════════════════════
    // JIVA-SPECIFIC PROPERTIES
    // ═══════════════════════════════════════════════════════════════════
    
    private double consciousnessLevel;  // 0.0 (dormant) to 1.0 (fully awakened)
    private double[] koshaHealth;       // Health of 5 Koshas (0.0-1.0 each)
    private double pranaLevel;          // Life force (0.0-1.0)
    private boolean mokshaPending;      // Flag for liberation
    
    // Kosha indices
    private static final int ANNAMAYA = 0;    // Physical
    private static final int PRANAMAYA = 1;   // Vital/Energy
    private static final int MANOMAYA = 2;    // Mental
    private static final int VIJNANAMAYA = 3; // Wisdom
    private static final int ANANDAMAYA = 4;  // Bliss
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTORS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Create a Jiva with default consciousness
     */
    public Jiva(String id, Level level, Guna guna) {
        this(id, level, guna, 0.5); // Default consciousness
    }
    
    /**
     * Create a Jiva with specified consciousness level
     */
    public Jiva(String id, Level level, Guna guna, double consciousnessLevel) {
        super(id, level, guna);
        
        this.consciousnessLevel = Math.max(0, Math.min(1, consciousnessLevel));
        this.pranaLevel = 1.0;
        this.mokshaPending = false;
        
        // Initialize Koshas (healthy at start)
        this.koshaHealth = new double[5];
        for (int i = 0; i < 5; i++) {
            koshaHealth[i] = 1.0;
        }
        
        System.out.printf("🕉️ Jiva '%s' created at %s (Consciousness: %.2f)%n",
            id, level.getName(), consciousnessLevel);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // ABSTRACT METHOD IMPLEMENTATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    protected int getWeightCount() {
        return 10; // Samskaras for various life aspects
    }
    
    @Override
    protected void initializeWeights() {
        // Initialize Samskaras (karmic patterns)
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextGaussian() * 0.1;  // Small random values
        }
        
        // Initialize Vasanas (innate tendencies)
        for (int i = 0; i < biases.length; i++) {
            biases[i] = random.nextGaussian() * 0.05;
        }
    }
    
    @Override
    protected long calculateMaxTicks() {
        // Based on level
        switch (level) {
            case N_MINUS_2: return 1_000_000L;        // Cell: millions of cycles
            case N_MINUS_1: return 100_000_000L;     // Organ: part of body lifespan
            case HUMAN:     return 778_000_000L;    // Human: ~100 years in breaths
            case N_PLUS_1:  return 10_000_000_000L; // Ecosystem: very long
            default:        return 1_000_000_000L;  // Default: large
        }
    }
    
    @Override
    protected double[] processInput(double[] input) {
        // Process through 5 Koshas
        double[] output = new double[input.length];
        
        for (int i = 0; i < input.length; i++) {
            double processed = input[i];
            
            // Annamaya (Physical) — basic filtering
            processed *= koshaHealth[ANNAMAYA];
            
            // Pranamaya (Energy) — life force modulation
            processed *= pranaLevel * koshaHealth[PRANAMAYA];
            
            // Manomaya (Mental) — thought processing
            processed = guna.activate(processed) * koshaHealth[MANOMAYA];
            
            // Vijnanamaya (Wisdom) — discrimination
            processed *= (1.0 + consciousnessLevel * 0.5) * koshaHealth[VIJNANAMAYA];
            
            // Anandamaya (Bliss) — connection to Atman
            processed *= koshaHealth[ANANDAMAYA];
            
            output[i] = processed;
        }
        
        // Update internal state based on processing
        updateInternalState(input, output);
        
        return output;
    }
    
    @Override
    protected double processGradient(double gradient) {
        // Karma processing — how much karma is generated
        
        // Sattvic processing generates less karma
        double karmaMultiplier = guna.getTamas() - guna.getSattva() * 0.5;
        
        // Consciousness level affects karma processing
        karmaMultiplier *= (1.0 - consciousnessLevel * 0.3);
        
        // Apply through Koshas (outer to inner)
        double processedGradient = gradient * karmaMultiplier;
        
        // Update Kosha health based on karma load
        for (int i = 0; i < 5; i++) {
            koshaHealth[i] -= Math.abs(processedGradient) * 0.001;
            koshaHealth[i] = Math.max(0.1, koshaHealth[i]);
        }
        
        return processedGradient;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // INTERNAL STATE UPDATES
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Update internal state based on processing
     */
    private void updateInternalState(double[] input, double[] output) {
        // Calculate processing quality
        double processingQuality = 0.0;
        for (int i = 0; i < output.length; i++) {
            processingQuality += output[i];
        }
        processingQuality /= output.length;
        
        // Positive processing increases consciousness slightly
        if (processingQuality > 0.5) {
            consciousnessLevel = Math.min(1.0, consciousnessLevel + 0.0001);
            guna = guna.shiftSattvic(0.001);
        } else {
            guna = guna.shiftTamasic(0.001);
        }
        
        // Prana slowly decreases with ticks
        pranaLevel = Math.max(0, pranaLevel - 0.00001);
        
        // Check for Moksha conditions
        if (consciousnessLevel > 0.99 && guna.getSattva() > 0.9) {
            mokshaPending = true;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // JIVA-SPECIFIC METHODS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Practice Sadhana (spiritual practice)
     * Increases consciousness and shifts Gunas towards Sattva
     */
    public void doSadhana(double intensity) {
        intensity = Math.max(0, Math.min(1, intensity));
        
        // Increase consciousness
        consciousnessLevel = Math.min(1.0, consciousnessLevel + intensity * 0.01);
        
        // Shift towards Sattva
        guna = guna.shiftSattvic(intensity * 0.02);
        
        // Heal Koshas
        for (int i = 0; i < 5; i++) {
            koshaHealth[i] = Math.min(1.0, koshaHealth[i] + intensity * 0.01);
        }
        
        // Restore Prana
        pranaLevel = Math.min(1.0, pranaLevel + intensity * 0.005);
    }
    
    /**
     * Perform Pranayama (breath control)
     * Conserves ticks and restores Prana
     */
    public void doPranayama(double intensity) {
        intensity = Math.max(0, Math.min(1, intensity));
        
        // Restore Prana
        pranaLevel = Math.min(1.0, pranaLevel + intensity * 0.02);
        
        // Heal Pranamaya Kosha specifically
        koshaHealth[PRANAMAYA] = Math.min(1.0, koshaHealth[PRANAMAYA] + intensity * 0.03);
        
        // Slight consciousness increase
        consciousnessLevel = Math.min(1.0, consciousnessLevel + intensity * 0.001);
    }
    
    /**
     * Process Karma (exhaust accumulated karma)
     */
    public void processKarma(double amount) {
        // Reduce karma balance
        karmaBalance = Math.max(0, karmaBalance - amount);
        
        // Karmic processing may affect Gunas
        if (amount > 0.5) {
            // Heavy karma processing is temporarily disturbing
            guna = guna.shiftTamasic(0.01);
        }
    }
    
    /**
     * Check if ready for Moksha
     */
    public boolean isReadyForMoksha() {
        return mokshaPending || 
               (consciousnessLevel > 0.95 && 
                guna.getSattva() > 0.85 && 
                karmaBalance < 0.1);
    }
    
    /**
     * Achieve Moksha (exit simulation cycle)
     */
    public void achieveMoksha() {
        if (isReadyForMoksha()) {
            stage = LifecycleStage.VINASHYATI;
            consciousnessLevel = 1.0;
            mokshaPending = false;
            System.out.printf("🕉️ Jiva '%s' achieved MOKSHA! 🕉️%n", id);
        }
    }
    
    /**
     * Spawn a child Jiva (e.g., a cell within a human)
     */
    public Jiva spawnChild(String childId, Level childLevel) {
        if (childLevel.ordinal() >= this.level.ordinal()) {
            throw new IllegalArgumentException("Child level must be lower than parent");
        }
        
        // Child inherits Guna with some variation
        Guna childGuna = guna.blend(Guna.balanced(), 0.3);
        
        // Child gets fraction of parent's consciousness
        double childConsciousness = consciousnessLevel * 0.5;
        
        Jiva child = new Jiva(childId, childLevel, childGuna, childConsciousness);
        addChild(child);
        
        return child;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public double getConsciousnessLevel() { return consciousnessLevel; }
    public double getPranaLevel() { return pranaLevel; }
    public double[] getKoshaHealth() { return koshaHealth.clone(); }
    public boolean isMokshaPending() { return mokshaPending; }
    
    /**
     * Get overall health (average of all Koshas)
     */
    public double getOverallHealth() {
        double sum = 0;
        for (double health : koshaHealth) {
            sum += health;
        }
        return sum / koshaHealth.length;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public void setConsciousnessLevel(double level) {
        this.consciousnessLevel = Math.max(0, Math.min(1, level));
    }
    
    public void setPranaLevel(double level) {
        this.pranaLevel = Math.max(0, Math.min(1, level));
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // TO STRING
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public String toString() {
        return String.format(
            "Jiva[%s, %s, %s, consciousness=%.2f, prana=%.2f, karma=%.2f, stage=%s]",
            id, level.getCode(), guna, consciousnessLevel, pranaLevel, 
            karmaBalance, stage.getName()
        );
    }
    
    /**
     * Get detailed status report
     */
    public String getStatusReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("║ JIVA: %-54s ║\n", id));
        sb.append("╠═══════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ Level: %-53s ║\n", level));
        sb.append(String.format("║ Guna: %-54s ║\n", guna));
        sb.append(String.format("║ Stage: %-53s ║\n", stage.getName()));
        sb.append("╠═══════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ Consciousness: %.2f                                         ║\n", consciousnessLevel));
        sb.append(String.format("║ Prana: %.2f                                                 ║\n", pranaLevel));
        sb.append(String.format("║ Karma Balance: %.2f                                         ║\n", karmaBalance));
        sb.append("╠═══════════════════════════════════════════════════════════════╣\n");
        sb.append("║ KOSHA HEALTH:                                                 ║\n");
        sb.append(String.format("║   Annamaya (Physical):  %.2f                                ║\n", koshaHealth[ANNAMAYA]));
        sb.append(String.format("║   Pranamaya (Energy):   %.2f                                ║\n", koshaHealth[PRANAMAYA]));
        sb.append(String.format("║   Manomaya (Mental):    %.2f                                ║\n", koshaHealth[MANOMAYA]));
        sb.append(String.format("║   Vijnanamaya (Wisdom): %.2f                                ║\n", koshaHealth[VIJNANAMAYA]));
        sb.append(String.format("║   Anandamaya (Bliss):   %.2f                                ║\n", koshaHealth[ANANDAMAYA]));
        sb.append("╠═══════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ Moksha Ready: %-46s ║\n", isReadyForMoksha() ? "YES 🕉️" : "No"));
        sb.append(String.format("║ Tick: %,d / %,d                                    ║\n", currentTick, maxTicks));
        sb.append("╚═══════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}
