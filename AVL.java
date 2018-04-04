
package Arvores;

/**
 * @author Yann Le Hyaric Almeida 
 */
public class AVL {
    private No raiz;
    
    public AVL(){
        raiz=null;
    }
    public void insereNo(No x){
        if(raiz==null){
            raiz=x;
        }
        else{
            No p=busca(x.getChave());
            if(p.getChave()<x.getChave()){
                p.setFilhoEsq(x);
                
            }
            else{
                p.setFilhoDir(x);
            }
            x.setPai(p);
        }
        verificaFB(raiz);
    }
    
    public void verificaFB(No x){
        if(x.getFilhoEsq()!=null)
            verificaFB(x.getFilhoEsq());
        if(x.getFilhoDir()!=null)
            verificaFB(x.getFilhoDir());       
        if(x.getFilhoDir().getAltura()-x.getFilhoEsq().getAltura()>Math.abs(1)){
            rotacao(x);
            setAlturas(x);
        }
    }
    public void setAlturas(No x){
        if(x.getFilhoEsq()!=null)
            setAlturas(x.getFilhoEsq());
        if(x.getFilhoDir()!=null)
            setAlturas(x.getFilhoDir());
        if(x.getFilhoEsq()==null && x.getFilhoDir()==null)
            x.setAltura(0);
        else{
            if(x.getFilhoDir().getAltura()>x.getFilhoEsq().getAltura())
                x.setAltura(x.getFilhoDir().getAltura()+1);
            else
                x.setAltura(x.getFilhoEsq().getAltura()+1);
        }
    }
    public No busca(int chave){
        return auxbusca(chave,raiz);
    }
            
    private No auxbusca(int Chave, No y){
        if(y.getFilhoDir()!=null){
            if (y.getFilhoDir().getChave()<Chave||  y.getFilhoDir().getChave()>Chave && y.getChave()<Chave )
                y=auxbusca(Chave,y.getFilhoDir());   
            else if(y.getFilhoDir().getChave()==Chave)
                return y.getFilhoDir();                   
        }
        if(y.getFilhoEsq()!=null){
            if(y.getFilhoEsq().getChave()>Chave||  y.getFilhoEsq().getChave()<Chave && y.getChave()>Chave)
                y=auxbusca(Chave,y.getFilhoEsq());
        
            else if(y.getFilhoEsq().getChave()==Chave)
                 return y.getFilhoEsq();
        }
        return y;
    }
    
    public void rotacao(No x){   
        No y=x.getPai();
        if(x.getChave()<y.getChave()){
            if(y==raiz){
                raiz=x;
                y.setFilhoEsq(x.getFilhoDir());
                x.setFilhoDir(y);                
            }
            else{
                if(y.getChave()<y.getPai().getChave()){y.getPai().setFilhoEsq(x);}
                else {y.getPai().setFilhoDir(x);}
                y.setFilhoEsq(x.getFilhoDir());
                x.setFilhoDir(y);   
            }
        }
        else{
            if(y==raiz){
                raiz=x;
                y.setFilhoDir(x.getFilhoEsq());
                x.setFilhoEsq(y);                
            }
            else{
                if(y.getChave()<y.getPai().getChave()){y.getPai().setFilhoDir(x);}
                else {y.getPai().setFilhoEsq(x);}
                y.setFilhoDir(x.getFilhoEsq());
                x.setFilhoEsq(y);
            }
        }
    } 
    public void remove(No x){
        if(x.getFilhoDir()!=null&&x.getFilhoEsq()!=null){
            No a=auxbusca(x.getChave(),x.getFilhoDir()),b=auxbusca(x.getChave(),x.getFilhoEsq());
            if(Math.abs(x.getChave()-b.getChave())<=x.getChave()-a.getChave()){
                if(x==raiz)
                    raiz=b;
                else if(x.getPai().getFilhoDir()==x)
                    x.getPai().setFilhoDir(b);
                else
                    x.getPai().setFilhoEsq(b);
                b.setFilhoEsq(x.getFilhoEsq());
                b.setFilhoDir(x.getFilhoDir());
                verificaFB(b);
            }
            else if (Math.abs(x.getChave()-b.getChave())>x.getChave()-a.getChave()){
                if(x==raiz)
                    raiz=a;
                else if(x.getPai().getFilhoDir()==x)
                    x.getPai().setFilhoDir(a);
                else
                    x.getPai().setFilhoEsq(a);
                a.setFilhoEsq(x.getFilhoEsq());
                a.setFilhoDir(x.getFilhoDir());
                verificaFB(a);
            }
        }
        else if(x.getFilhoDir()==null&&x.getFilhoEsq()!=null){
            No a=auxbusca(x.getChave(),x.getFilhoEsq());
            if(x==raiz)
                raiz=a;
            else if(x.getPai().getFilhoEsq()==x)
                    x.getPai().setFilhoEsq(a);
            else
                    x.getPai().setFilhoDir(a);
            a.setFilhoEsq(x.getFilhoEsq());
            verificaFB(a);
        }
        else if(x.getFilhoDir()!=null&&x.getFilhoEsq()==null){
            No a=auxbusca(x.getChave(),x.getFilhoDir());
            if(x==raiz)
                raiz=a;
            else if(x.getPai().getFilhoEsq()==x)
                    x.getPai().setFilhoEsq(a);
            else
                    x.getPai().setFilhoDir(a);
            a.setFilhoDir(x.getFilhoDir());
            verificaFB(a);
        }
        else{
            if(x!=raiz){
                No a=x.getPai();
                verificaFB(a);
            }
            else
                raiz=null;            
        }
    }
}