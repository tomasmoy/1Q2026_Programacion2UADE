package graphModule.EscapeRoom;

public enum TipoTerreno {
    NORMAL('N', 1, "\u001B[37m"),
    LODO('L', 3, "\u001B[33m"),
    TORRE('T', 1, "\u001B[31m"),
    PARED('P', 999, "\u001B[30m"),
    START('S', 0, "\u001B[34m"),
    EXIT('E', 0, "\u001B[32m");

    private final char simbolo;
    private final int costoBase;
    private final String ansiColor;

    TipoTerreno(char simbolo, int costoBase, String ansiColor) {
        this.simbolo = simbolo;
        this.costoBase = costoBase;
        this.ansiColor = ansiColor;
    }

    public char getSimbolo() { return simbolo; }
    public int getCostoBase() { return costoBase; }
    public String getAnsiColor() { return ansiColor; }

    public static TipoTerreno desdeSimbolo(char c) {
        for (TipoTerreno t : values()) {
            if (t.simbolo == c) return t;
        }
        return NORMAL;
    }
}