package impacta.modelo;

import java.time.LocalDateTime;

public class OficinaEcologica extends AcaoSocioambiental {

    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, boolean kitMaterial, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.kitMaterial = kitMaterial;
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        int pontuacaoBase = 3 * duracaoHoras;
        if (this.kitMaterial) {
            return pontuacaoBase + 10;
        }
        return pontuacaoBase;
    }
}
