package impacta.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AcaoSocioambiental {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;

    private List<String> emailsInscritos;

    public AcaoSocioambiental(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.emailsInscritos = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }


    public abstract int calcularPontuacao();


    public List<String> getEmailsInscritos() {
        return emailsInscritos;
    }
}
