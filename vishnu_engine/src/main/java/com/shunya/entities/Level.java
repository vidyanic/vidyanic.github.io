package com.shunya.entities;

/**
 * Level (स्तर) — Fractal Hierarchy Levels from N-6 to N+6
 * 
 * Vedic: The 14 Lokas mapped to fractal levels
 * Simulation: Each level operates at different scales of space and time
 * Physics: Scale from Planck length to cosmic structures
 * 
 * N = Human level (reference point)
 * N-6 = Quantum level (Planck scale)
 * N+6 = Brahman level (infinite)
 * 
 * Shruti Pramana:
 * > "यथा पिण्डे तथा ब्रह्माण्डे"
 * > "Yatha pinde tatha brahmande"
 * > "As in the microcosm, so in the macrocosm"
 * > — Yoga Vasishtha
 */
public enum Level {
    
    // ═══════════════════════════════════════════════════════════════════
    // MICROCOSM (N-levels)
    // ═══════════════════════════════════════════════════════════════════
    
    N_MINUS_6("N-6", "Quantum", "क्वांटम",
        1.616e-35,     // Planck length (meters)
        5.391e-44,     // Planck time (seconds)
        1e-35          // Clarity at this scale
    ),
    
    N_MINUS_5("N-5", "Subatomic", "उप-परमाणु",
        1e-18,         // Attometer scale
        1e-24,         // Attosecond scale
        1e-18
    ),
    
    N_MINUS_4("N-4", "Atom", "परमाणु",
        1e-10,         // Angstrom scale
        1e-15,         // Femtosecond scale
        1e-10
    ),
    
    N_MINUS_3("N-3", "Molecule", "अणु",
        1e-9,          // Nanometer scale
        1e-12,         // Picosecond scale
        1e-6
    ),
    
    N_MINUS_2("N-2", "Cell", "कोश",
        1e-5,          // 10 micrometers
        1e-3,          // Millisecond scale
        0.001
    ),
    
    N_MINUS_1("N-1", "Organ", "अंग",
        0.1,           // 10 centimeters
        1.0,           // Second scale
        0.1
    ),
    
    // ═══════════════════════════════════════════════════════════════════
    // HUMAN LEVEL (Reference)
    // ═══════════════════════════════════════════════════════════════════
    
    HUMAN("N", "Human", "मनुष्य",
        1.0,           // 1 meter (reference)
        1.0,           // 1 second (reference)
        1.0            // Full clarity
    ),
    
    // ═══════════════════════════════════════════════════════════════════
    // MACROCOSM (N+levels)
    // ═══════════════════════════════════════════════════════════════════
    
    N_PLUS_1("N+1", "Ecosystem", "पारिस्थितिकी",
        1e4,           // 10 kilometers
        3600.0,        // Hour scale
        0.8
    ),
    
    N_PLUS_2("N+2", "Planet", "ग्रह",
        1e7,           // 10,000 km
        86400.0,       // Day scale
        0.5
    ),
    
    N_PLUS_3("N+3", "Solar System", "सौर मण्डल",
        1.5e11,        // AU scale
        3.156e7,       // Year scale
        0.3
    ),
    
    N_PLUS_4("N+4", "Galaxy", "आकाशगंगा",
        1e21,          // 100,000 light-years
        1e10 * 3.156e7, // Galactic year (~250 MY)
        0.1
    ),
    
    N_PLUS_5("N+5", "Universe", "ब्रह्माण्ड",
        1e26,          // Observable universe
        4.32e9 * 3.156e7, // Yuga scale
        0.01
    ),
    
    N_PLUS_6("N+6", "Brahman", "ब्रह्मन्",
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        0.0  // Brahman cannot be "seen" from N
    );
    
    // ═══════════════════════════════════════════════════════════════════
    // PROPERTIES
    // ═══════════════════════════════════════════════════════════════════
    
    private final String code;
    private final String name;
    private final String sanskrit;
    private final double scaleMeters;      // Characteristic size in meters
    private final double tickSeconds;      // Characteristic time in seconds
    private final double clarityFromHuman; // How clearly N can perceive this level
    
    Level(String code, String name, String sanskrit, 
          double scaleMeters, double tickSeconds, double clarityFromHuman) {
        this.code = code;
        this.name = name;
        this.sanskrit = sanskrit;
        this.scaleMeters = scaleMeters;
        this.tickSeconds = tickSeconds;
        this.clarityFromHuman = clarityFromHuman;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getSanskrit() { return sanskrit; }
    public double getScaleMeters() { return scaleMeters; }
    public double getTickSeconds() { return tickSeconds; }
    public double getClarityFromHuman() { return clarityFromHuman; }
    
    // ═══════════════════════════════════════════════════════════════════
    // CALCULATIONS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Get clarity when observing another level
     * Clarity decreases exponentially with level distance
     */
    public double getClarityFor(Level target) {
        int distance = Math.abs(this.ordinal() - target.ordinal());
        return Math.exp(-distance * 0.5);
    }
    
    /**
     * Convert ticks at this level to human seconds
     */
    public double ticksToHumanSeconds(long ticks) {
        return ticks * this.tickSeconds;
    }
    
    /**
     * Convert human seconds to ticks at this level
     */
    public long humanSecondsToTicks(double seconds) {
        if (this.tickSeconds <= 0 || Double.isInfinite(this.tickSeconds)) {
            return 0;
        }
        return (long) (seconds / this.tickSeconds);
    }
    
    /**
     * Get the level offset by n steps
     */
    public Level getOffsetLevel(int offset) {
        int targetOrdinal = this.ordinal() + offset;
        Level[] levels = Level.values();
        
        if (targetOrdinal < 0) return levels[0];
        if (targetOrdinal >= levels.length) return levels[levels.length - 1];
        
        return levels[targetOrdinal];
    }
    
    /**
     * Get all parent levels (higher than this)
     */
    public Level[] getParentLevels() {
        int count = Level.values().length - 1 - this.ordinal();
        if (count <= 0) return new Level[0];
        
        Level[] parents = new Level[count];
        for (int i = 0; i < count; i++) {
            parents[i] = Level.values()[this.ordinal() + 1 + i];
        }
        return parents;
    }
    
    /**
     * Get all child levels (lower than this)
     */
    public Level[] getChildLevels() {
        int count = this.ordinal();
        if (count <= 0) return new Level[0];
        
        Level[] children = new Level[count];
        for (int i = 0; i < count; i++) {
            children[i] = Level.values()[i];
        }
        return children;
    }
    
    /**
     * Get the numeric offset from Human level
     */
    public int getOffset() {
        return this.ordinal() - HUMAN.ordinal();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // TO STRING
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public String toString() {
        return String.format("%s (%s / %s)", code, name, sanskrit);
    }
    
    public String toDetailedString() {
        return String.format(
            "Level %s (%s / %s)\n" +
            "  Scale: %.2e meters\n" +
            "  Tick: %.2e seconds\n" +
            "  Clarity from N: %.2f",
            code, name, sanskrit, scaleMeters, tickSeconds, clarityFromHuman
        );
    }
}
