package impacta.modelo;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends AcaoSocioambiental {

    private int duracaoHoras;


    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }
    
    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        return 4 *  duracaoHoras;
    }
}
