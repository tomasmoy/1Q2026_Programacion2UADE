package graphModule.EscapeRoom;

public class Celda {
    private final int x;
    private final int y;
    private final TipoTerreno tipo;
    private int costoAgregado;

    public Celda(int x, int y, TipoTerreno tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.costoAgregado = 0;
    }

    public int getCostoTotal() {
        return this.tipo.getCostoBase() + this.costoAgregado;
    }

    public String getAnsiColor() {
        return this.tipo.getAnsiColor();
    }

    public TipoTerreno getTipo() {
        return tipo;
    }

    public void setCostoAgregado(int costoAgregado) {
        this.costoAgregado = costoAgregado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Celda otra = (Celda) obj;
        return this.x == otra.x && this.y == otra.y;
    }

    public String coordenadasToString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}