
package sistema;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mara Lemos
 */
public class Sistema{
    
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
            System.out.println("Identificador: "+id);
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
    
    public boolean novaOp() {
        int alternativa;
        System.out.println("Deseja realizar nova operação?\n1-Sim\t2-Não");
        
        try {
            Scanner teclado = new Scanner(System.in);
            alternativa = teclado.nextInt();
            
            if (alternativa == 1) {
                return true;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("ERRO! Você digitou um caractere inválido, digite uma das opções.");
            novaOp();
        }
        
        return false;
    }
    
    public void menuClientes(Sistema s) throws ParseException{
        System.out.println("1 - Adicionar Clientes");
        System.out.println("2 - Voltar ao menu inicial");
        
        int alternativa;
        
        try {
            Scanner teclado = new Scanner(System.in);
            alternativa = teclado.nextInt();
            teclado.nextLine();
            
            switch(alternativa){
                case 1:
                    String nome;
                    System.out.println("Digite o nome:");
                    nome = teclado.nextLine();
                    
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String dataNasc;
                    System.out.println("Digite a data de nascimento:");
                    dataNasc = teclado.nextLine();
                    
                    String telefoneContato;
                    System.out.println("Digite o telefone para contato:");
                    telefoneContato = teclado.nextLine();
                    
                    addCliente(nome,formato.parse(dataNasc),telefoneContato);
                    
                    if(novaOp())
                        menuClientes(s);
                    
                    break;
     
                case 2:
                    menu(s);
                    break;
                default:
                    System.out.println("ERRO! Caractere Inválido");
                    System.out.println("Digite uma das opções");
                    menuClientes(s);
                    break;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("ERRO! Caractere Inválido");
            System.out.println("Digite uma das opções");
            menuClientes(s);
        }
        
    }
    
    public void menu(Sistema s) throws ParseException{
        System.out.println("\tSeja Bem Vindo");
        System.out.println("1 - Clientes");
        System.out.println("2 - Encomendas");
        System.out.println("3 - Listar clientes e suas encomendas");
        
        int alternativa;
        
        try {
            Scanner teclado = new Scanner(System.in);
            alternativa = teclado.nextInt();
            
            switch(alternativa){
                case 1:
                    menuClientes(s);
                    break;
                case 2:
                    menuEncomendas(s);
                    break;
                case 3:
                    s.listarClientes();
                    if(novaOp())
                        menu(s);
                    break;
                default:
                    System.out.println("ERRO! Caractere Inválido");
                    System.out.println("Digite uma das opções");
                    menu(s);
                    break;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("ERRO! Caractere Inválido");
            System.out.println("Digite uma das opções");
            menu(s);
        }
        
    }
    
    
    public void menuEncomendas(Sistema s) throws ParseException{
        System.out.println("1 - Adicionar Encomenda");
        System.out.println("2 - Alterar Status Encomenda");
        System.out.println("3 - Voltar ao menu inicial");
        
        int alternativa;
        int id;
        
        try {
            Scanner teclado = new Scanner(System.in);
            alternativa = teclado.nextInt();
            
            switch(alternativa){
                case 1:
                    System.out.println("Digite o identificador do cliente que deseja adicionar a encomenda:");
                   
                    try{
                        id = teclado.nextInt();
                        teclado.nextLine();
                        
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        String dataPedido;
                        System.out.println("Digite a data do pedido:");
                        dataPedido = teclado.nextLine();

                        String dataEntrega;
                        System.out.println("Digite a data da entrega:");
                        dataEntrega = teclado.nextLine();

                        String cor;
                        System.out.println("Digite a cor da camiseta:");
                        cor = teclado.nextLine();

                        System.out.println("Selecione o arquivo da estampa:");

                        JFileChooser fileChooser = new JFileChooser();
                        int returnVal = fileChooser.showOpenDialog(null);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            s.addEncomendaParaCliente(id,formato.parse(dataPedido),formato.parse(dataEntrega),cor,file);
                        } else {
                            JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado");
                            System.out.println("Erro ao adicionar encomenda.");
                            menuEncomendas(s);
                        }
                        
                    }catch (InputMismatchException e){
                        System.out.println("ERRO! Inválido");
                    }
                    if(novaOp())
                        menuEncomendas(s);
                    break;
                case 2:
                    System.out.println("Digite o identificador do cliente que deseja alterar o status de uma encomenda:");
                   
                    try{
                        id = teclado.nextInt();
                        
                        System.out.println("Informe o id da encomenda: ");
                        int idEncomenda = teclado.nextInt();
                        
                        s.alterarStatusDeEncomendaDeCliente(id,idEncomenda);
                        
                    }catch (InputMismatchException e){
                        System.out.println("ERRO! Inválido");
                    }
                    if(novaOp())
                        menuEncomendas(s);
                    break;
                    
                case 3:
                    menu(s);
                    break;
                default:
                    System.out.println("ERRO! Caractere Inválido");
                    System.out.println("Digite uma das opções");
                    menuEncomendas(s);
                    break;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("ERRO! Caractere Inválido");
            System.out.println("Digite uma das opções");
            menuEncomendas(s);
        }
        
    }
    
    public static void main(String args[]) throws ParseException{
        Sistema s = new Sistema();
        
        s.menu(s);
        
        /*
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
        */
    }
}
