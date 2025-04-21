
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

    
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    private Connection conn;
    private conectaDAO conexao;
    
    public ProdutosDAO(){
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES" + "(?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Podcast: " + e.getMessage());
        }

    }

    public ProdutosDTO listarProdutoPorId(int id) {
        
        ProdutosDTO produto = null;
        
        String sql = "SELECT * FROM produtos WHERE id = ?";
        
        try{ 
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
               produto = new ProdutosDTO();
               produto.setId(rs.getInt("id"));
               produto.setNome(rs.getString("nome"));
               produto.setValor(rs.getInt("valor"));
               produto.setStatus(rs.getString("status"));
            }
        } catch (Exception e){
            System.out.println("Erro ao listar produto por id: "+e.getMessage());
            e.printStackTrace();
        }
        
        return produto;
    }
    
    
    public ArrayList<ProdutosDTO> listarTodosProdutos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    String sql = "SELECT * FROM produtos";

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            lista.add(produto);
        }

        stmt.close();
        conn.close();
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }

    return lista;
}


}
