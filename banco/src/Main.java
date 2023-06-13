import jdbc.Conexao;
import service.ServicoService;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        var conexao = new Conexao();
        Locale brasil = new Locale("PT", "BR");
        Connection conn = conexao.conectar();
        DateFormat data = DateFormat.getDateInstance(DateFormat.FULL, brasil);

        var servicoService = new ServicoService();

        servicoService.iniciaProjeto();
    }
}