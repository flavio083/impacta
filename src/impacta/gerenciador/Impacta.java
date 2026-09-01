package impacta.gerenciador;

import impacta.excecoes.AcaoLotadaException;
import impacta.excecoes.EmailDuplicadoException;
import impacta.excecoes.InscricaoDuplicadaException;
import impacta.modelo.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Impacta {

    private Map<String, Voluntario> voluntarios;

    private Map<Integer, AcaoSocioambiental> acoes;

    private int geradorIdAcoes = 1;

    public Impacta() {
        this.voluntarios = new HashMap<>();
        this.acoes = new HashMap<>();
    }
    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        if (this.voluntarios.containsKey(email)) {
            throw new EmailDuplicadoException("Este e-mail já está cadastrado.");
        }

        Voluntario novoVoluntario = new Voluntario(email, nome, matricula);
        this.voluntarios.put(email, novoVoluntario);
        return true;
    }
    public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
        LocalDateTime dataFormatada = LocalDateTime.parse(data);

        PlantioMudas plantio = new PlantioMudas(geradorIdAcoes, titulo, descricao, dataFormatada, maxParticipantes, qtdMudas);
        this.acoes.put(geradorIdAcoes, plantio);

        int idGerado = geradorIdAcoes;
        geradorIdAcoes++;
        return idGerado;
    }
    public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
        LocalDateTime dataFormatada = LocalDateTime.parse(data);

        MutiraoReciclagem mutirao = new MutiraoReciclagem(geradorIdAcoes, titulo, descricao, dataFormatada, maxParticipantes, duracaoHoras);
        this.acoes.put(geradorIdAcoes, mutirao);

        int idGerado = geradorIdAcoes;
        geradorIdAcoes++;
        return idGerado;
    }
    public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        LocalDateTime dataFormatada = LocalDateTime.parse(data);

        OficinaEcologica oficina = new OficinaEcologica(geradorIdAcoes, titulo, descricao, dataFormatada, maxParticipantes, kitMaterial, duracaoHoras);
        this.acoes.put(geradorIdAcoes, oficina);

        int idGerado = geradorIdAcoes;
        geradorIdAcoes++;
        return idGerado;
    }
    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {

        AcaoSocioambiental acao = this.acoes.get(idAcao);
        Voluntario voluntario = this.voluntarios.get(emailVoluntario);

        if (acao == null || voluntario == null) {
            return false;
        }

        if (acao.getEmailsInscritos().contains(emailVoluntario)) {
            throw new InscricaoDuplicadaException("O voluntário já está inscrito nesta ação.");
        }

        if (acao.getEmailsInscritos().size() >= acao.getMaxParticipantes()) {
            throw new AcaoLotadaException("A ação já atingiu o limite máximo de participantes.");
        }

        acao.getEmailsInscritos().add(emailVoluntario);

        voluntario.setQuantidadeAcoes(voluntario.getQuantidadeAcoes() + 1);

        int pontosGanhos = acao.calcularPontuacao();
        voluntario.adicionarPontuacaoAcumulada(pontosGanhos);

        return true;
    }
    public String exibirVoluntario(String email) {
        Voluntario voluntario = this.voluntarios.get(email);

        if (voluntario == null) {
            return "Voluntário não encontrado.";
        }

        return "Nome: " + voluntario.getNome() +
                " | Ações participadas: " + voluntario.getQuantidadeAcoes() +
                " | Pontuação acumulada: " + voluntario.getPontuacaoAcumulada();
    }

    public String exibirDetalhesAcao(int idAcao) {
        AcaoSocioambiental acao = this.acoes.get(idAcao);

        if (acao == null) {
            return "Ação não encontrada.";
        }

        String detalhes = "Título: " + acao.getTitulo() +
                " | Descrição: " + acao.getDescricao() +
                " | Data: " + acao.getData() +
                " | Pontuação Calculada: " + acao.calcularPontuacao() +
                " | Inscritos: " + acao.getEmailsInscritos().size() + "/" + acao.getMaxParticipantes() +
                " | Voluntários: " + acao.getEmailsInscritos();

        if (acao instanceof PlantioMudas) {
            PlantioMudas plantio = (PlantioMudas) acao;
            detalhes += " | Quantidade de mudas: " + plantio.getQuantidadeMudas();

        } else if (acao instanceof MutiraoReciclagem) {
            MutiraoReciclagem mutirao = (MutiraoReciclagem) acao;
            detalhes += " | Duração em horas: " + mutirao.getDuracaoHoras();

        } else if (acao instanceof OficinaEcologica) {
            OficinaEcologica oficina = (OficinaEcologica) acao;
            detalhes += " | Duração em horas: " + oficina.getDuracaoHoras() +
                    " | Kit de material: " + oficina.isKitMaterial();
        }

        return detalhes;
    }

    public String[] listarVoluntarios() {

        Voluntario[] arrayVoluntarios = this.voluntarios.values().toArray(new Voluntario[0]);

        for (int i = 0; i < arrayVoluntarios.length; i++) {
            for (int j = i + 1; j < arrayVoluntarios.length; j++) {

                boolean precisaTrocar = false;

                if (arrayVoluntarios[j].getPontuacaoAcumulada() > arrayVoluntarios[i].getPontuacaoAcumulada()) {
                    precisaTrocar = true;
                }
                else if (arrayVoluntarios[j].getPontuacaoAcumulada() == arrayVoluntarios[i].getPontuacaoAcumulada()) {

                    if (arrayVoluntarios[j].getNome().compareToIgnoreCase(arrayVoluntarios[i].getNome()) < 0) {
                        precisaTrocar = true;
                    }
                }

                if (precisaTrocar) {
                    Voluntario auxiliar = arrayVoluntarios[i];
                    arrayVoluntarios[i] = arrayVoluntarios[j];
                    arrayVoluntarios[j] = auxiliar;
                }
            }
        }

        String[] ranking = new String[arrayVoluntarios.length];

        for (int i = 0; i < arrayVoluntarios.length; i++) {
            Voluntario v = arrayVoluntarios[i];
            ranking[i] = v.getNome() + " - " + v.getPontuacaoAcumulada() + " pontos";
        }

        return ranking;
    }
}
