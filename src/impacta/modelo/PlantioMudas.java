package impacta.modelo;

import java.time.LocalDateTime;

public class PlantioMudas extends AcaoSocioambiental {

    private int quantidadeMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int quantidadeMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.quantidadeMudas = quantidadeMudas;
    }

    @Override
    public int calcularPontuacao() {
        return 5 +(2 * quantidadeMudas);
    }

    public int getQuantidadeMudas() {
        return quantidadeMudas;
    }

    public void setQuantidadeMudas(int quantidadeMudas) {
        this.quantidadeMudas = quantidadeMudas;
    }
}
