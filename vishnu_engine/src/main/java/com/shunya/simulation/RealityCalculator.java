package com.shunya.simulation;

import com.shunya.core.Constants;
import com.shunya.core.Guna;
import com.shunya.entities.Level;

/**
 * RealityCalculator (यथार्थ-गणक) — Frontend Rendering Calculator
 * 
 * Takes backend inputs (Yuga, Guna state, Level) and calculates
 * frontend rendering outputs (experiential time, biological age, etc.)
 * 
 * Vedic: "यथा दृश्यं तथा सृष्टिः" — "As the seeing, so the creation"
 * Simulation: Prediction engine based on Universal Laws
 * 
 * Use Cases:
 * 1. Calculate experiential time (how much life you experience)
 * 2. Predict biological age (vs chronological age)
 * 3. Calculate sync status (alignment with cosmic frequency)
 * 4. Estimate remaining experiential years
 * 5. Model Guna transitions and their effects
 * 
 * Shruti Pramana:
 * > "कालः पचति भूतानि कालः संहरते प्रजाः"
 * > "Kalah pachati bhutani kalah samharate prajah"
 * > "Time devours all beings, Time destroys all creatures"
 * > — Mahabharata, Vana Parva 313.117
 */
public class RealityCalculator {
    
    // ═══════════════════════════════════════════════════════════════════
    // YUGA CONFIGURATION
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Yuga enum with Dharma units and default Gunas
     */
    public enum Yuga {
        SATYA(4, 0.9, 0.08, 0.02, 1728000L),
        TRETA(3, 0.7, 0.2, 0.1, 1296000L),
        DVAPARA(2, 0.5, 0.3, 0.2, 864000L),
        KALI(1, 0.1, 0.4, 0.5, 432000L);
        
        private final int dharmaUnit;
        private final Guna defaultGuna;
        private final long durationYears;
        
        Yuga(int dharmaUnit, double sattva, double rajas, double tamas, long durationYears) {
            this.dharmaUnit = dharmaUnit;
            this.defaultGuna = new Guna(sattva, rajas, tamas);
            this.durationYears = durationYears;
        }
        
        public int getDharmaUnit() { return dharmaUnit; }
        public Guna getDefaultGuna() { return defaultGuna; }
        public long getDurationYears() { return durationYears; }
        
        public double getDharmaRatio() {
            return dharmaUnit / 4.0;
        }
    }
    
    // Current Yuga setting (default to Dvapara transition)
    private Yuga currentYuga = Yuga.DVAPARA;
    private double sandhyaProgress = 0.5; // 0-1 within sandhya period
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════════════
    
    public RealityCalculator() {
        // Default constructor uses Dvapara Sandhya
    }
    
    public RealityCalculator(Yuga yuga) {
        this.currentYuga = yuga;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // TIME DILATION CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate Time Dilation Factor (L_m)
     * 
     * Formula: L_m = (D × S) / (R + 2T)
     * 
     * @param guna Current Guna state
     * @return Time dilation multiplier (higher = more experiential time per chronological year)
     */
    public double calculateTimeDilation(Guna guna) {
        return guna.calculateTimeDilation(currentYuga.getDharmaUnit());
    }
    
    /**
     * Calculate Effective Dharma Unit (for Sandhya periods)
     * During transition, D interpolates between Yugas
     */
    public double getEffectiveDharmaUnit() {
        // During Sandhya, blend between old and new Yuga
        Yuga nextYuga = getNextYuga(currentYuga);
        if (nextYuga == null) return currentYuga.getDharmaUnit();
        
        double current = currentYuga.getDharmaUnit();
        double next = nextYuga.getDharmaUnit();
        
        return current + (next - current) * sandhyaProgress;
    }
    
    private Yuga getNextYuga(Yuga current) {
        switch (current) {
            case KALI: return Yuga.DVAPARA;  // Ascending
            case DVAPARA: return Yuga.TRETA;
            case TRETA: return Yuga.SATYA;
            case SATYA: return null;  // Peak
            default: return null;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // EXPERIENTIAL TIME CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate experiential years from chronological years
     * 
     * @param chronologicalYears Calendar years
     * @param guna Current Guna state
     * @return Experiential years (quality-adjusted)
     */
    public double calculateExperientialYears(double chronologicalYears, Guna guna) {
        double timeDilation = calculateTimeDilation(guna);
        return chronologicalYears * timeDilation;
    }
    
    /**
     * Calculate how many chronological years feel like target experiential years
     * 
     * @param targetExperientialYears Desired experiential years
     * @param guna Current Guna state
     * @return Chronological years needed
     */
    public double yearsToAchieve(double targetExperientialYears, Guna guna) {
        double timeDilation = calculateTimeDilation(guna);
        if (timeDilation <= 0) return Double.POSITIVE_INFINITY;
        return targetExperientialYears / timeDilation;
    }
    
    /**
     * Calculate remaining experiential life
     * 
     * @param currentAge Current chronological age
     * @param expectedLifespan Expected chronological lifespan
     * @param guna Current Guna state
     * @return Remaining experiential years
     */
    public double remainingExperientialLife(int currentAge, int expectedLifespan, Guna guna) {
        int remainingYears = expectedLifespan - currentAge;
        return calculateExperientialYears(remainingYears, guna);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // BIOLOGICAL AGE CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate biological age from chronological age
     * 
     * Formula: Bio_Age = Chrono_Age × (1 + 0.5×R + T)
     * 
     * @param chronologicalAge Calendar age
     * @param guna Current Guna state
     * @return Biological age (may be higher or lower than chronological)
     */
    public double calculateBiologicalAge(int chronologicalAge, Guna guna) {
        double ageFactor = guna.biologicalAgeFactor();
        return chronologicalAge * ageFactor;
    }
    
    /**
     * Calculate biological age acceleration/deceleration
     * 
     * @param guna Current Guna state
     * @return Factor (>1 = aging faster, <1 = aging slower)
     */
    public double getAgingRate(Guna guna) {
        return guna.biologicalAgeFactor();
    }
    
    /**
     * Predict chronological lifespan based on current Guna trajectory
     * 
     * @param currentAge Current chronological age
     * @param currentGuna Current Guna state
     * @param targetBioAge Target biological age at death (typically ~85-90)
     * @return Predicted chronological age at death
     */
    public int predictLifespan(int currentAge, Guna currentGuna, double targetBioAge) {
        double currentBioAge = calculateBiologicalAge(currentAge, currentGuna);
        double remainingBioYears = targetBioAge - currentBioAge;
        
        if (remainingBioYears <= 0) return currentAge;
        
        double agingRate = getAgingRate(currentGuna);
        int remainingChronoYears = (int) (remainingBioYears / agingRate);
        
        return currentAge + remainingChronoYears;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SYNC STATUS CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate sync status with cosmic frequency
     * 
     * Formula: Sync = (S × D_yuga) / (R + T)
     * 
     * @param guna Current Guna state
     * @return Sync status (>1 = optimal, <0.5 = critical)
     */
    public double calculateSyncStatus(Guna guna) {
        return guna.syncStatus(currentYuga.getDharmaRatio());
    }
    
    /**
     * Get sync status category
     */
    public SyncCategory getSyncCategory(Guna guna) {
        double sync = calculateSyncStatus(guna);
        
        if (sync >= 1.0) return SyncCategory.OPTIMAL;
        if (sync >= 0.5) return SyncCategory.WARNING;
        return SyncCategory.CRITICAL;
    }
    
    public enum SyncCategory {
        OPTIMAL("✅ Synchronized with cosmic frequency"),
        WARNING("⚠️ Gradual decline - correction recommended"),
        CRITICAL("🚨 CRITICAL - Immediate Guna correction needed");
        
        private final String message;
        SyncCategory(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GUNA OPTIMIZATION
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate optimal Guna state for current Yuga
     * 
     * @return Optimal Guna configuration
     */
    public Guna getOptimalGuna() {
        // High Sattva, low Rajas/Tamas
        return new Guna(0.8, 0.15, 0.05);
    }
    
    /**
     * Calculate Guna adjustment needed
     * 
     * @param current Current Guna state
     * @return Adjustment vector (positive = increase, negative = decrease)
     */
    public double[] getGunaAdjustment(Guna current) {
        Guna optimal = getOptimalGuna();
        return new double[] {
            optimal.getSattva() - current.getSattva(),
            optimal.getRajas() - current.getRajas(),
            optimal.getTamas() - current.getTamas()
        };
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // LEVEL CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate clarity when observing another level
     * 
     * @param observerLevel The level of the observer
     * @param targetLevel The level being observed
     * @return Clarity (0.0 to 1.0)
     */
    public double calculateClarity(Level observerLevel, Level targetLevel) {
        return observerLevel.getClarityFor(targetLevel);
    }
    
    /**
     * Convert time between levels
     * 
     * @param duration Duration value
     * @param sourceLevel Level of the duration
     * @param targetLevel Level to convert to
     * @return Duration in target level's time units
     */
    public double convertTime(double duration, Level sourceLevel, Level targetLevel) {
        double sourceSeconds = sourceLevel.ticksToHumanSeconds((long) duration);
        return targetLevel.humanSecondsToTicks(sourceSeconds);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // KARMA CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Calculate karmic debt from actions
     * 
     * @param actionMagnitude Intensity of action
     * @param guna Guna state during action
     * @return Karma generated (positive = debt, negative = merit)
     */
    public double calculateKarmaGenerated(double actionMagnitude, Guna guna) {
        // Sattvic actions reduce karma, Tamasic increase
        double karmaFactor = guna.getTamas() - guna.getSattva();
        return actionMagnitude * karmaFactor;
    }
    
    /**
     * Estimate time to exhaust karma
     * 
     * @param karmaLoad Current karma load
     * @param guna Current Guna state
     * @return Years to exhaust karma at current rate
     */
    public double estimateKarmaExhaustionTime(double karmaLoad, Guna guna) {
        // Higher Sattva = faster karma processing
        double processingRate = guna.getSattva() * 0.1;
        if (processingRate <= 0) return Double.POSITIVE_INFINITY;
        return karmaLoad / processingRate;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // COMPREHENSIVE REPORT
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Generate comprehensive reality report
     * 
     * @param chronologicalAge Current age
     * @param guna Current Guna state
     * @return Formatted report string
     */
    public String generateReport(int chronologicalAge, Guna guna) {
        int expectedLifespan = Constants.KALI_LIFESPAN;
        
        StringBuilder report = new StringBuilder();
        report.append("╔═══════════════════════════════════════════════════════════════╗\n");
        report.append("║           REALITY CALCULATOR — Personal Report                ║\n");
        report.append("╠═══════════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║ Current Yuga: %s (Dharma Unit: %d/4)                          ║\n", 
            currentYuga.name(), currentYuga.getDharmaUnit()));
        report.append(String.format("║ Chronological Age: %d years                                   ║\n", 
            chronologicalAge));
        report.append("╠═══════════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║ GUNA STATE: %s                                ║\n", guna));
        report.append(String.format("║   Dominant: %s                                               ║\n", 
            guna.getDominant().name()));
        report.append("╠═══════════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║ TIME DILATION (L_m): %.2f                                    ║\n", 
            calculateTimeDilation(guna)));
        report.append(String.format("║   Experiential Age: %.1f years                               ║\n", 
            calculateExperientialYears(chronologicalAge, guna)));
        report.append(String.format("║   Remaining Experiential: %.1f years                         ║\n", 
            remainingExperientialLife(chronologicalAge, expectedLifespan, guna)));
        report.append("╠═══════════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║ BIOLOGICAL AGE: %.1f years (%.1fx aging rate)                ║\n", 
            calculateBiologicalAge(chronologicalAge, guna), getAgingRate(guna)));
        report.append(String.format("║ PREDICTED LIFESPAN: %d years                                 ║\n", 
            predictLifespan(chronologicalAge, guna, 85)));
        report.append("╠═══════════════════════════════════════════════════════════════╣\n");
        SyncCategory sync = getSyncCategory(guna);
        report.append(String.format("║ SYNC STATUS: %.2f — %s          ║\n", 
            calculateSyncStatus(guna), sync.name()));
        report.append("╚═══════════════════════════════════════════════════════════════╝\n");
        
        return report.toString();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // SETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public void setCurrentYuga(Yuga yuga) {
        this.currentYuga = yuga;
    }
    
    public void setSandhyaProgress(double progress) {
        this.sandhyaProgress = Math.max(0, Math.min(1, progress));
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public Yuga getCurrentYuga() {
        return currentYuga;
    }
    
    public double getSandhyaProgress() {
        return sandhyaProgress;
    }
}

