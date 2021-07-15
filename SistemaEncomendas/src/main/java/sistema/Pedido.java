
package sistema;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Mara Lemos
 */
public interface Pedido {
    
    public void adicionarEncomenda(Date dataPedido, Date dataEntrega,String cor, File estampa);
    
    public void excluirEncomenda(int id);
    
    public void editarEncomenda(int id, Date dataPedido, Date dataEntrega,String cor, File estampa);
    
    public void visualizarEncomenda(int id);
    
    public void listarEncomendas();
    
    public void alterarStatusPedido(int id);
}
