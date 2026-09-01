package impacta.testes;

import java.time.LocalDateTime;
import org.junit.Test;

public class ImpactaTest {
    @Test
    public void testarCalculosDePontuacao(){

            java.time.LocalDateTime dataFalsa = java.time.LocalDateTime.now();

            impacta.modelo.PlantioMudas plantio = new impacta.modelo.PlantioMudas(1, "Plantio", "Desc", dataFalsa, 10, 10);
            org.junit.Assert.assertEquals(25, plantio.calcularPontuacao());


            impacta.modelo.MutiraoReciclagem mutirao = new impacta.modelo.MutiraoReciclagem(2, "Mutirao", "Desc", dataFalsa, 10, 3);
            org.junit.Assert.assertEquals(12, mutirao.calcularPontuacao());

            impacta.modelo.OficinaEcologica oficina = new impacta.modelo.OficinaEcologica(3, "Oficina", "Desc", dataFalsa, 10, true, 2);
            org.junit.Assert.assertEquals(16, oficina.calcularPontuacao());
    }
    @Test
    public void testarOrdenacaoDeVoluntarios() {

        impacta.gerenciador.Impacta sistema = new impacta.gerenciador.Impacta();

        sistema.cadastrarVoluntario("Carlos", "carlos@gmail.com", "111");
        sistema.cadastrarVoluntario("Ana", "ana@gmail.com", "222");
        sistema.cadastrarVoluntario("Bruno", "bruno@gmail.com", "333");

        int idAcao1 = sistema.cadastrarMutirao("Mutirão", "Desc", "2026-10-10T10:00:00", 10, 5);

        int idAcao2 = sistema.cadastrarPlantio("Plantio", "Desc", "2026-10-11T10:00:00", 10, 10);

        sistema.inscreverVoluntario("carlos@gmail.com", idAcao1);
        sistema.inscreverVoluntario("ana@gmail.com", idAcao2);
        sistema.inscreverVoluntario("bruno@gmail.com", idAcao2);

        String[] ranking = sistema.listarVoluntarios();

        org.junit.Assert.assertEquals("Ana - 25 pontos", ranking[0]);

        org.junit.Assert.assertEquals("Bruno - 25 pontos", ranking[1]);

        org.junit.Assert.assertEquals("Carlos - 20 pontos", ranking[2]);
    }

}
