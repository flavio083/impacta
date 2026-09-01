package impacta.modelo;

public class Voluntario  {

    private String nome;
    private String email;
    private String matricula;
    private int pontuacaoAcumulada = 0;
    private int quantidadeAcoes = 0;

    public String getEmail() {
        return email;
    }

    public Voluntario(String email, String nome, String matricula) {
        this.email = email;
        this.nome = nome;
        this.matricula = matricula;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPontuacaoAcumulada() {
        return pontuacaoAcumulada;
    }

    public void setPontuacaoAcumulada(int pontuacaoAcumulada) {
        this.pontuacaoAcumulada = pontuacaoAcumulada;
    }

    public int getQuantidadeAcoes() {
        return quantidadeAcoes;
    }

    public void setQuantidadeAcoes(int quantidadeAcoes) {
        this.quantidadeAcoes = quantidadeAcoes;
    }

    public void adicionarPontuacaoAcumulada(int pontosGanhos) {
        this.pontuacaoAcumulada += pontosGanhos;
    }
}

