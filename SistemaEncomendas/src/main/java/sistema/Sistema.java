
package sistema;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mara Lemos
 */
public class Sistema {
    
    private List<Cliente> clientes;
    private int qtdClientes;
    
    public Sistema(){
        clientes = new ArrayList<>();
        qtdClientes = 0;
    }
    
    public void addCliente(String nome,Date dataNasc,String telefoneContato){
        Cliente c = new Cliente(nome,dataNasc,telefoneContato);
        clientes.add(c);
        qtdClientes++;
    }
    
    public void visualizarCliente(int id){
        if (id >= 0 && id < this.clientes.size()) {
            System.out.println("********************************************");
            System.out.println("Nome: "+ clientes.get(id).getNome());
            System.out.println("Nome: "+ clientes.get(id).getDataNasc());
            System.out.println("Idade: "+clientes.get(id).getIdade());
            System.out.println("Telefone para Contato: "+clientes.get(id).getTelefoneContato());
            
            System.out.println("Encomendas:");
            clientes.get(id).listarEncomendas();
            
            System.out.println("********************************************");
        }else{
            JOptionPane.showConfirmDialog(null, " Cliente não encontrado.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void listarClientes(){
        if(qtdClientes == 0){
            System.out.println("Não há clientes cadastrados");
        }else{
            for(int i=0;i<qtdClientes;i++)
                visualizarCliente(i);
        }
    }
    
    public void addEncomendaParaCliente(int id, Date dataPedido, Date dataEntrega,String cor, File estampa){
        if (id >= 0 && id < this.clientes.size()) {
            clientes.get(id).adicionarEncomenda(dataPedido,dataEntrega,cor,estampa);
        }else{
            JOptionPane.showConfirmDialog(null, " Cliente não encontrado.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void alterarStatusDeEncomendaDeCliente(int id, int idPedido){
        if (id >= 0 && id < this.clientes.size()) {
            clientes.get(id).alterarStatusPedido(idPedido);
        }else{
            JOptionPane.showConfirmDialog(null, " Cliente não encontrado.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String args[]) throws ParseException{
        Sistema s = new Sistema();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        s.addCliente("Mara",formato.parse("05/04/2000"),"(32)999624380");
        s.addCliente("Gleiph",formato.parse("20/10/2000"),"(32)455866777");
        s.addCliente("Sara",formato.parse("03/12/1994"),"(32)555555555");
        
        s.listarClientes();
        
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            s.addEncomendaParaCliente(0,formato.parse("15/07/2001"),formato.parse("20/07/2001"),"Rosa",file);
        } else {
            JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado");
            System.exit(0);
        }
        
        JFileChooser fileChooser2 = new JFileChooser();
        int returnVal2 = fileChooser2.showOpenDialog(null);
        
        if (returnVal2 == JFileChooser.APPROVE_OPTION) {
            File file2 = fileChooser.getSelectedFile();
            s.addEncomendaParaCliente(0,formato.parse("15/07/2001"),formato.parse("20/07/2001"),"Branco",file2);
        } else {
            JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado");
            System.exit(0);
        }
        
        s.alterarStatusDeEncomendaDeCliente(0,1);
        
        s.listarClientes();
    }
}
