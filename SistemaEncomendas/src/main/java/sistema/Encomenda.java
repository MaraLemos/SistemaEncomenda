
package sistema;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Mara Lemos
 */
public class Encomenda {
    private static int geraId = 1;
    private final int id;
    private Date dataPedido;
    private Date dataEntrega;
    private String status;
    private float valor;
    private String cor;
    private File estampa;
    
    public int getId() {
        return id;
    }

    public Encomenda(Date dataPedido, Date dataEntrega, String cor, File estampa) {
        this.id = geraId++;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.status = "Solicitada";
        this.cor = cor;
        this.estampa = estampa;
        this.valor = calculaValor();
    }


    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public File getEstampa() {
        return estampa;
    }

    public void setEstampa(File estampa) {
        this.estampa = estampa;
    }
    
    public float getValor(){
        return valor;
    }
    public float calculaValor(){
        if(!"Branco".equals(this.cor)){
            return 40;
        }else{
            return 30;
        }
    }
}
