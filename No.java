package Arvores;
/**
 * @author Yann Le Hyaric Almeida 
 */

public class No {

    private int chave;
    private No Fesq;
    private No pai;
    private boolean Cor;
    private No Fdir;
    private int altura;

    public No() {
    }

    public No(int chave) {
        this.chave = chave;
        Cor=false;//false=vermelho
        altura=0;
    }
    public void setAltura(int a){
        altura=a;
    }
    
    public int getAltura(){
        return altura;
    }
    
    public No getFilhoDir() {
        return Fdir;
    }
    

    public void setFilhoDir(No Fdir) {
        this.Fdir = Fdir;
    }
    
     public No getFilhoEsq() {
        return Fesq;
        
    }
    public void setFilhoEsq(No Fesq) {
        this.Fesq = Fesq;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public boolean getCor() {
        return Cor;
    }

    public void setCor(boolean Cor) {
        this.Cor = Cor;
    }

    
    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
   
}
 

