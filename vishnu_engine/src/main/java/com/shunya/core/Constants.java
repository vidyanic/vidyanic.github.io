package com.shunya.core;

/**
 * Constants (स्थिरांक) — Universal Constants for the Simulation
 * 
 * Vedic: The unchanging parameters that define the simulation's architecture
 * Simulation: Hardcoded values that remain constant across all levels
 * Physics: Planck units, Yuga durations, and fundamental ratios
 * 
 * NOTE: These are BACKEND constants. At higher levels (N+1 to N+6),
 * some become VARIABLES. See: Constant Relativity principle.
 * 
 * Shruti Pramana:
 * > "ऋतं च सत्यं चाभीद्धात् तपसोऽध्यजायत"
 * > "Rtam cha satyam cha abhiddhat tapaso adhyajayata"
 * > "From Tapas was born Rta (cosmic order) and Satya (truth)"
 * > — Rig Veda 10.190.1
 */
public final class Constants {
    
    // Private constructor — no instantiation
    private Constants() {}
    
    // ═══════════════════════════════════════════════════════════════════
    // PLANCK UNITS (Simulation's Pixel/Tick Limits)
    // ═══════════════════════════════════════════════════════════════════
    
    /** Planck length in meters — minimum "pixel" size */
    public static final double PLANCK_LENGTH = 1.616255e-35;
    
    /** Planck time in seconds — minimum "tick" duration */
    public static final double PLANCK_TIME = 5.391247e-44;
    
    /** Speed of light — c = 1 pixel / 1 tick (architecture ratio) */
    public static final double SPEED_OF_LIGHT = PLANCK_LENGTH / PLANCK_TIME;
    
    /** Planck mass in kg */
    public static final double PLANCK_MASS = 2.176434e-8;
    
    /** Planck energy in joules */
    public static final double PLANCK_ENERGY = 1.956e9;
    
    // ═══════════════════════════════════════════════════════════════════
    // YUGA DURATIONS (in human years)
    // ═══════════════════════════════════════════════════════════════════
    
    /** Satya Yuga — full Dharma (4/4) */
    public static final long SATYA_YUGA_YEARS = 1_728_000L;
    
    /** Treta Yuga — 3/4 Dharma */
    public static final long TRETA_YUGA_YEARS = 1_296_000L;
    
    /** Dvapara Yuga — 1/2 Dharma */
    public static final long DVAPARA_YUGA_YEARS = 864_000L;
    
    /** Kali Yuga — 1/4 Dharma */
    public static final long KALI_YUGA_YEARS = 432_000L;
    
    /** One Maha Yuga (complete cycle of 4 Yugas) */
    public static final long MAHA_YUGA_YEARS = SATYA_YUGA_YEARS + TRETA_YUGA_YEARS + 
                                                DVAPARA_YUGA_YEARS + KALI_YUGA_YEARS;
    
    /** One Kalpa (1000 Maha Yugas = Day of Brahma) */
    public static final long KALPA_YEARS = 1000 * MAHA_YUGA_YEARS;
    
    /** Brahma's Lifespan (100 Brahma years) */
    public static final double BRAHMA_LIFESPAN_YEARS = 100.0 * 360.0 * KALPA_YEARS * 2.0;
    
    // ═══════════════════════════════════════════════════════════════════
    // DHARMA UNITS
    // ═══════════════════════════════════════════════════════════════════
    
    /** Dharma unit for Satya Yuga */
    public static final int DHARMA_SATYA = 4;
    
    /** Dharma unit for Treta Yuga */
    public static final int DHARMA_TRETA = 3;
    
    /** Dharma unit for Dvapara Yuga */
    public static final int DHARMA_DVAPARA = 2;
    
    /** Dharma unit for Kali Yuga */
    public static final int DHARMA_KALI = 1;
    
    // ═══════════════════════════════════════════════════════════════════
    // LIFESPAN BY YUGA (in human years)
    // ═══════════════════════════════════════════════════════════════════
    
    /** Average lifespan in Satya Yuga */
    public static final int SATYA_LIFESPAN = 100_000;
    
    /** Average lifespan in Treta Yuga */
    public static final int TRETA_LIFESPAN = 10_000;
    
    /** Average lifespan in Dvapara Yuga */
    public static final int DVAPARA_LIFESPAN = 1_000;
    
    /** Average lifespan in Kali Yuga */
    public static final int KALI_LIFESPAN = 100;
    
    // ═══════════════════════════════════════════════════════════════════
    // GUNA DEFAULTS
    // ═══════════════════════════════════════════════════════════════════
    
    /** Default Sattva */
    public static final double DEFAULT_SATTVA = 1.0 / 3.0;
    
    /** Default Rajas */
    public static final double DEFAULT_RAJAS = 1.0 / 3.0;
    
    /** Default Tamas */
    public static final double DEFAULT_TAMAS = 1.0 / 3.0;
    
    // ═══════════════════════════════════════════════════════════════════
    // LEARNING RATES BY YUGA
    // ═══════════════════════════════════════════════════════════════════
    
    /** Learning rate in Satya Yuga (fastest learning) */
    public static final double LR_SATYA = 1.0;
    
    /** Learning rate in Treta Yuga */
    public static final double LR_TRETA = 0.75;
    
    /** Learning rate in Dvapara Yuga */
    public static final double LR_DVAPARA = 0.50;
    
    /** Learning rate in Kali Yuga (slowest learning) */
    public static final double LR_KALI = 0.25;
    
    // ═══════════════════════════════════════════════════════════════════
    // DHARMA REGULARIZATION
    // ═══════════════════════════════════════════════════════════════════
    
    /** Default Dharma regularization strength (L2) */
    public static final double DHARMA_LAMBDA = 0.01;
    
    /** Gradient clipping threshold */
    public static final double KARMA_CLIP_THRESHOLD = 10.0;
    
    /** Weight pruning threshold */
    public static final double WEIGHT_PRUNE_THRESHOLD = 0.001;
    
    // ═══════════════════════════════════════════════════════════════════
    // TICK BUDGET (Swasa - Breath Allocation)
    // ═══════════════════════════════════════════════════════════════════
    
    /** Breaths per day (average human) */
    public static final int BREATHS_PER_DAY = 21_600;
    
    /** Total breaths for human lifespan (~100 years) */
    public static final long HUMAN_TOTAL_BREATHS = 778_000_000L;
    
    /** Tortoise breath rate (very low — long life) */
    public static final int TORTOISE_BREATHS_PER_MIN = 4;
    
    /** Human breath rate (normal) */
    public static final int HUMAN_BREATHS_PER_MIN = 15;
    
    /** Mouse breath rate (very high — short life) */
    public static final int MOUSE_BREATHS_PER_MIN = 150;
    
    // ═══════════════════════════════════════════════════════════════════
    // 81-GRID ARCHITECTURE
    // ═══════════════════════════════════════════════════════════════════
    
    /** Total nodes in the grid */
    public static final int GRID_TOTAL_NODES = 81;
    
    /** Brahma Sthan (kernel) nodes */
    public static final int GRID_BRAHMA_STHAN = 9;
    
    /** Daivika Ring (system forces) nodes */
    public static final int GRID_DAIVIKA_RING = 16;
    
    /** Manusha Ring (interface) nodes */
    public static final int GRID_MANUSHA_RING = 24;
    
    /** Paisacha Ring (firewall) nodes */
    public static final int GRID_PAISACHA_RING = 32;
    
    /** The 15th axis (Sushumna/Center) */
    public static final int FIFTEENTH_AXIS = 15;
    
    // ═══════════════════════════════════════════════════════════════════
    // KOSHA INDICES
    // ═══════════════════════════════════════════════════════════════════
    
    /** Annamaya Kosha index (Physical) */
    public static final int ANNAMAYA = 0;
    
    /** Pranamaya Kosha index (Energy) */
    public static final int PRANAMAYA = 1;
    
    /** Manomaya Kosha index (Mental) */
    public static final int MANOMAYA = 2;
    
    /** Vijnanamaya Kosha index (Wisdom) */
    public static final int VIJNANAMAYA = 3;
    
    /** Anandamaya Kosha index (Bliss) */
    public static final int ANANDAMAYA = 4;
    
    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT INDICES (Pancha Mahabhuta)
    // ═══════════════════════════════════════════════════════════════════
    
    /** Prithvi (Earth) */
    public static final int PRITHVI = 0;
    
    /** Jala (Water) */
    public static final int JALA = 1;
    
    /** Agni (Fire) */
    public static final int AGNI = 2;
    
    /** Vayu (Air) */
    public static final int VAYU = 3;
    
    /** Akasha (Space) */
    public static final int AKASHA = 4;
    
    // ═══════════════════════════════════════════════════════════════════
    // MATHEMATICAL CONSTANTS
    // ═══════════════════════════════════════════════════════════════════
    
    /** Golden ratio (Phi) */
    public static final double PHI = 1.618033988749895;
    
    /** Square root of 2 */
    public static final double SQRT_2 = 1.4142135623730951;
    
    /** Pi */
    public static final double PI = Math.PI;
    
    /** Euler's number */
    public static final double E = Math.E;
    
    // ═══════════════════════════════════════════════════════════════════
    // UTILITY METHODS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Get lifespan for a given Yuga name
     */
    public static int getLifespanForYuga(String yugaName) {
        switch (yugaName.toUpperCase()) {
            case "SATYA": return SATYA_LIFESPAN;
            case "TRETA": return TRETA_LIFESPAN;
            case "DVAPARA": return DVAPARA_LIFESPAN;
            case "KALI": return KALI_LIFESPAN;
            default: return KALI_LIFESPAN;
        }
    }
    
    /**
     * Get Dharma unit for a given Yuga name
     */
    public static int getDharmaUnitForYuga(String yugaName) {
        switch (yugaName.toUpperCase()) {
            case "SATYA": return DHARMA_SATYA;
            case "TRETA": return DHARMA_TRETA;
            case "DVAPARA": return DHARMA_DVAPARA;
            case "KALI": return DHARMA_KALI;
            default: return DHARMA_KALI;
        }
    }
    
    /**
     * Get learning rate for a given Yuga name
     */
    public static double getLearningRateForYuga(String yugaName) {
        switch (yugaName.toUpperCase()) {
            case "SATYA": return LR_SATYA;
            case "TRETA": return LR_TRETA;
            case "DVAPARA": return LR_DVAPARA;
            case "KALI": return LR_KALI;
            default: return LR_KALI;
        }
    }
}
