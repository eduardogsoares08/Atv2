
/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    conectaDAO conexao;
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES" + "(?,?,?)";
        try {
            conexao = new conectaDAO();
            conn = conexao.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Podcast: " + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos(int id) {

        return listagem;
    }

}
