package puerto;

public record Parada(String codigo, String nombre, String zona, int abordajes) {
    public Parada {
        if (codigo == null || !codigo.matches("[A-Z][A-Z0-9]*"))
            throw new IllegalArgumentException("Código inválido. Use letras A-Z y, opcionalmente, dígitos después de la inicial.");
        if (nombre == null || nombre.isBlank() || zona == null || zona.isBlank())
            throw new IllegalArgumentException("Se requieren el nombre de la parada y el de la zona.");
        if (abordajes < 0)
            throw new IllegalArgumentException("Los abordajes deben ser cero o más.");
        nombre = nombre.strip();
        zona = zona.strip();
    }
}
