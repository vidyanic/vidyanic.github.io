package com.shunya.core;

/**
 * Guna (गुण) — The Three Fundamental Qualities of Prakriti
 * 
 * Vedic: Every manifestation is a combination of Sattva, Rajas, and Tamas
 * Simulation: Determines quality, activity, and inertia of any entity
 * Physics: Maps to negentropy, kinetic energy, and entropy/mass
 * 
 * CONSTRAINT: S + R + T = 1.0 (Always normalized)
 * 
 * Shruti Pramana:
 * > "सत्त्वं रजस्तम इति गुणाः प्रकृतिसम्भवाः"
 * > "Sattvam rajas tama iti gunah prakriti-sambhavah"
 * > "Sattva, Rajas, and Tamas — these qualities are born of Prakriti"
 * > — Bhagavad Gita 14.5
 */
public class Guna {
    
    private double sattva;  // Clarity, truth, balance (negentropy)
    private double rajas;   // Activity, passion, motion (kinetic energy)
    private double tamas;   // Inertia, darkness, mass (entropy)
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTORS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Create a normalized Guna composition
     * Values are automatically normalized to sum to 1.0
     */
    public Guna(double sattva, double rajas, double tamas) {
        double total = sattva + rajas + tamas;
        if (total <= 0) {
            // Fallback to balanced if all zero
            this.sattva = 1.0 / 3.0;
            this.rajas = 1.0 / 3.0;
            this.tamas = 1.0 / 3.0;
        } else {
            this.sattva = sattva / total;
            this.rajas = rajas / total;
            this.tamas = tamas / total;
        }
    }
    
    /**
     * Create a balanced Guna (1/3 each)
     */
    public static Guna balanced() {
        return new Guna(1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0);
    }
    
    /**
     * Create a Sattvic Guna (default for Satya Yuga)
     */
    public static Guna sattvic() {
        return new Guna(0.9, 0.08, 0.02);
    }
    
    /**
     * Create a Rajasic Guna (high activity)
     */
    public static Guna rajasic() {
        return new Guna(0.2, 0.6, 0.2);
    }
    
    /**
     * Create a Tamasic Guna (default for Kali Yuga)
     */
    public static Guna tamasic() {
        return new Guna(0.1, 0.4, 0.5);
    }
    
    /**
     * Create Guna for a specific Yuga
     */
    public static Guna forYuga(String yuga) {
        switch (yuga.toUpperCase()) {
            case "SATYA": return new Guna(0.9, 0.08, 0.02);
            case "TRETA": return new Guna(0.7, 0.2, 0.1);
            case "DVAPARA": return new Guna(0.5, 0.3, 0.2);
            case "KALI": return new Guna(0.1, 0.4, 0.5);
            default: return balanced();
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // TIME DILATION
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate Time Dilation Factor (L_m)
     * 
     * Formula: L_m = (D × S) / (R + 2T)
     * 
     * WHERE:
     * D = Dharma Unit of Yuga (4=Satya, 3=Treta, 2=Dvapara, 1=Kali)
     * S = Sattva percentage
     * R = Rajas percentage
     * T = Tamas percentage
     * 
     * INTERPRETATION:
     * - Higher L_m = More experiential time per chronological year
     * - A person with L_m=2.0 experiences 2 years of life per 1 calendar year
     * - High Sattva and low Tamas = time "expands"
     * - High Tamas = time "compresses" (life feels shorter)
     * 
     * @param dharmaUnit The Dharma unit (1-4)
     * @return Time dilation factor
     */
    public double calculateTimeDilation(int dharmaUnit) {
        double denominator = rajas + 2 * tamas;
        if (denominator <= 0) denominator = 0.01; // Prevent division by zero
        return (dharmaUnit * sattva) / denominator;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // BIOLOGICAL AGE
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate biological age factor
     * 
     * Formula: Factor = 1 + (0.5 × R) + T
     * 
     * - If Factor > 1: Aging faster than chronological
     * - If Factor < 1: Aging slower
     * - If Factor = 1: Chronological = Biological
     * 
     * @return Biological age multiplier
     */
    public double biologicalAgeFactor() {
        return 1.0 + (0.5 * rajas) + tamas;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SYNC STATUS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate sync status with cosmic frequency
     * 
     * Formula: Sync = (S × D_ratio) / (R + T)
     * 
     * @param dharmaRatio Current Yuga's Dharma ratio (0.25-1.0)
     * @return Sync value (>1 = good, <0.5 = critical)
     */
    public double syncStatus(double dharmaRatio) {
        double denominator = rajas + tamas;
        if (denominator <= 0) denominator = 0.01;
        return (sattva * dharmaRatio) / denominator;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // ACTIVATION FUNCTIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Apply Guna-weighted activation
     * 
     * Sattva = Linear (direct, clear)
     * Rajas = ReLU (active, positive bias)
     * Tamas = Sigmoid (compressed, bounded)
     * 
     * @param x Input value
     * @return Activated value
     */
    public double activate(double x) {
        double sattvaOut = x * sattva;                           // Linear
        double rajasOut = Math.max(0, x) * rajas;                // ReLU
        double tamasOut = (1.0 / (1.0 + Math.exp(-x))) * tamas;  // Sigmoid
        return sattvaOut + rajasOut + tamasOut;
    }
    
    /**
     * Apply inverse activation (for backward pass)
     */
    public double deactivate(double y) {
        // Simplified inverse for gradient computation
        return y / (sattva + rajas + tamas);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GUNA ANALYSIS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Get dominant Guna
     */
    public Type getDominant() {
        if (sattva >= rajas && sattva >= tamas) return Type.SATTVA;
        if (rajas >= sattva && rajas >= tamas) return Type.RAJAS;
        return Type.TAMAS;
    }
    
    /**
     * Check if balanced (all within 10% of each other)
     */
    public boolean isBalanced() {
        double avg = (sattva + rajas + tamas) / 3.0;
        double threshold = 0.1;
        return Math.abs(sattva - avg) < threshold &&
               Math.abs(rajas - avg) < threshold &&
               Math.abs(tamas - avg) < threshold;
    }
    
    /**
     * Blend with another Guna state
     * 
     * @param other The other Guna
     * @param weight Blend weight (0=this, 1=other)
     * @return New blended Guna
     */
    public Guna blend(Guna other, double weight) {
        weight = Math.max(0, Math.min(1, weight));
        return new Guna(
            sattva * (1 - weight) + other.sattva * weight,
            rajas * (1 - weight) + other.rajas * weight,
            tamas * (1 - weight) + other.tamas * weight
        );
    }
    
    /**
     * Shift towards Sattva
     */
    public Guna shiftSattvic(double amount) {
        return new Guna(
            sattva + amount,
            rajas - amount * 0.5,
            tamas - amount * 0.5
        );
    }
    
    /**
     * Shift towards Tamas
     */
    public Guna shiftTamasic(double amount) {
        return new Guna(
            sattva - amount * 0.5,
            rajas - amount * 0.5,
            tamas + amount
        );
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SETTERS (with normalization)
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Set Sattva and normalize
     */
    public void setSattva(double newSattva) {
        double ratio = (rajas + tamas) > 0 ? rajas / (rajas + tamas) : 0.5;
        double remaining = 1.0 - newSattva;
        this.sattva = newSattva;
        this.rajas = remaining * ratio;
        this.tamas = remaining * (1 - ratio);
    }
    
    /**
     * Set all values and normalize
     */
    public void set(double s, double r, double t) {
        double total = s + r + t;
        if (total <= 0) {
            this.sattva = 1.0 / 3.0;
            this.rajas = 1.0 / 3.0;
            this.tamas = 1.0 / 3.0;
        } else {
            this.sattva = s / total;
            this.rajas = r / total;
            this.tamas = t / total;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public double getSattva() { return sattva; }
    public double getRajas() { return rajas; }
    public double getTamas() { return tamas; }
    
    // ═══════════════════════════════════════════════════════════════════
    // TO STRING
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public String toString() {
        return String.format("S:%.2f R:%.2f T:%.2f", sattva, rajas, tamas);
    }
    
    /**
     * Get detailed string with analysis
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Guna Composition [S:%.2f R:%.2f T:%.2f]\n", sattva, rajas, tamas));
        sb.append(String.format("  Dominant: %s\n", getDominant()));
        sb.append(String.format("  Balanced: %s\n", isBalanced() ? "Yes" : "No"));
        sb.append(String.format("  Bio Age Factor: %.2fx\n", biologicalAgeFactor()));
        return sb.toString();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GUNA TYPE ENUM
    // ═══════════════════════════════════════════════════════════════════
    
    public enum Type {
        SATTVA("सत्त्व", "Clarity, truth, balance"),
        RAJAS("रजस्", "Activity, passion, motion"),
        TAMAS("तमस्", "Inertia, darkness, mass");
        
        private final String sanskrit;
        private final String description;
        
        Type(String sanskrit, String description) {
            this.sanskrit = sanskrit;
            this.description = description;
        }
        
        public String getSanskrit() { return sanskrit; }
        public String getDescription() { return description; }
    }
}
