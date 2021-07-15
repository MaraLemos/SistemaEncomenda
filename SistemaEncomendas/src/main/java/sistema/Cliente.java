
package sistema;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.GregorianCalendar;


/**
 *
 * @author Mara Lemos
 */
public class Cliente extends Pessoa implements Pedido{
    
    private final List<Encomenda> encomendas;
    private int qtdEncomendas;
    
    public Cliente(String nome,Date dataNasc,String telefoneContato){
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.telefoneContato = telefoneContato;
        this.encomendas = new ArrayList<>();
        this.qtdEncomendas = 0;
    }
    
    @Override
    public int getIdade(){
        
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(this.dataNasc);

        // Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();

        // Obtém a idade baseado no ano
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

        if (today.before(dateOfBirth))
            age--;

        return age; 
    }
    
    @Override
    public void adicionarEncomenda(Date dataPedido, Date dataEntrega,String cor, File estampa){
        Encomenda enc = new Encomenda(dataPedido,dataEntrega,cor,estampa);
        encomendas.add(enc);
        this.qtdEncomendas++;
        JOptionPane.showConfirmDialog(null, "Encomenda adicionada com sucesso", " SUCESSO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void excluirEncomenda(int id){
        if (id >= 0 && id < this.encomendas.size()) {
            if("Em produção".equals(encomendas.get(id).getStatus())){
                JOptionPane.showConfirmDialog(null, " Encomenda em produção. Impossível excluir. ", " ERRO !", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }else{
                encomendas.remove(id);
                this.qtdEncomendas--;
                JOptionPane.showConfirmDialog(null, " Encomenda removida com sucesso ! ", " SUCESSO !", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showConfirmDialog(null, " Encomenda não encontrada.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void editarEncomenda(int id, Date dataPedido, Date dataEntrega,String cor, File estampa){
        if (id >= 0 && id < this.encomendas.size()) {
            if("Em produção".equals(encomendas.get(id).getStatus())){
                JOptionPane.showConfirmDialog(null, " Encomenda em produção. Impossível editar. ", " ERRO !", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }else{
                encomendas.get(id).setDataPedido(dataPedido);
                encomendas.get(id).setDataEntrega(dataEntrega);
                encomendas.get(id).setCor(cor);
                encomendas.get(id).setEstampa(estampa);
                encomendas.get(id).calculaValor();
                encomendas.get(id).setStatus("Solicitada");
            
                JOptionPane.showConfirmDialog(null, " Encomenda editada com sucesso ! ", " SUCESSO !", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else{
            JOptionPane.showConfirmDialog(null, " Encomenda não encontrada.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void visualizarEncomenda(int id){
        if (id >= 0 && id < this.encomendas.size()) {
            String data;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("********************************************");
            System.out.println("Identificador: "+encomendas.get(id).getId());
            data = formato.format(encomendas.get(id).getDataPedido());
            System.out.println("Data do pedido: "+data);
            data = formato.format(encomendas.get(id).getDataEntrega());
            System.out.println("Data da Entrega: "+data);
            System.out.println("Status: "+encomendas.get(id).getStatus());
            System.out.println("Cor: "+encomendas.get(id).getCor());
            System.out.println("Estampa: "+encomendas.get(id).getEstampa());
            System.out.println("Valor: "+encomendas.get(id).getValor());
            System.out.println("********************************************");
        }else{
            JOptionPane.showConfirmDialog(null, " Encomenda não encontrada.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void listarEncomendas(){
        if(qtdEncomendas == 0){
            System.out.println("Não há encomendas para este cliente");
        }else{
            for(int i=0;i < qtdEncomendas; i++)
                visualizarEncomenda(i);
        }
        
    }
    
    @Override
    public void alterarStatusPedido(int id){
        if (id >= 0 && id < this.encomendas.size()) {
            
            if("Solicitada".equals(encomendas.get(id).getStatus()))
                encomendas.get(id).setStatus("Em produção");
            
            else if("Em produção".equals(encomendas.get(id).getStatus()))
                encomendas.get(id).setStatus("Entregue");
            
            else if("Entregue".equals(encomendas.get(id).getStatus()))
                JOptionPane.showConfirmDialog(null, " Encomenda entregue", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                
        }else{
            JOptionPane.showConfirmDialog(null, " Encomenda não encontrada.", " ERRO !" ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
