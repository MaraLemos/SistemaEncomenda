
package sistema;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mara Lemos
 */
public abstract class Pessoa {
    protected String nome;
    protected Date dataNasc;
    protected String telefoneContato;
    
    public String getNome(){
        return nome;
    }
    public String getDataNasc(){
        String dataNascimento;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dataNascimento = formato.format(dataNasc);
        return dataNascimento;
    }
    
    public String getTelefoneContato(){
        return telefoneContato;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setDataNasc(Date dataNasc){
        this.dataNasc = dataNasc;
    }
    public void setTelefoneContato(String telefoneContato){
        this.telefoneContato = telefoneContato;
    }
    
    public abstract int getIdade();
}
