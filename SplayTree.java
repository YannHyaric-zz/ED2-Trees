
package Arvores;
/**
 * @author Yann Le Hyaric Almeida 
 */
public class SplayTree {
    private 
        No raiz;
    public SplayTree(){
        raiz=null;
    }

    public void insereNo(No x){
        if(raiz==null){
            raiz=x;
        }
        else{
            No p=busca(x.getChave(),raiz);
            if(p.getChave()<x.getChave()){
                p.setFilhoDir(x);
            }
            else{
                p.setFilhoEsq(x);
            }
            x.setPai(p);
            rotacao(x);
        }
    }
    public No buscaChave(int chave){
        No x=busca(chave,raiz);
        rotacao(x);
        return x;
    }
    
    private No busca(int Chave, No y){
        if(y.getFilhoDir()!=null){
            if (y.getFilhoDir().getChave()<Chave||  y.getFilhoDir().getChave()>Chave && y.getChave()<Chave )
                y=busca(Chave,y.getFilhoDir());   
            else if(y.getFilhoDir().getChave()==Chave)
                return y.getFilhoDir();                   
        }
        if(y.getFilhoEsq()!=null){
            if(y.getFilhoEsq().getChave()>Chave||  y.getFilhoEsq().getChave()<Chave && y.getChave()>Chave)
                y=busca(Chave,y.getFilhoEsq());
        
            else if(y.getFilhoEsq().getChave()==Chave)
                 return y.getFilhoEsq();
        }
        return y;
    }
    
    public void rotacao(No x){
        while(x!=raiz){
            if(x.getPai()==raiz)
                rotacaoZig(x);
            else if(x.getPai().getPai().getFilhoDir()==x.getPai()&& x.getPai().getFilhoDir()==x || x.getPai().getPai().getFilhoEsq()==x.getPai()&& x.getPai().getFilhoEsq()==x)
                rotacaoZigZig(x);
            else if(x.getPai().getPai().getFilhoEsq()==x.getPai()&& x.getPai().getFilhoDir()==x || x.getPai().getPai().getFilhoEsq()==x.getPai()&& x.getPai().getFilhoDir()==x)
                rotacaoZigZag(x);
        }
    }
    
    public void rotacaoZig(No x){        
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
    public void rotacaoZigZag(No x){
        rotacaoZig(x);
        rotacaoZig(x);
    }  
    public void rotacaoZigZig(No x){
        rotacaoZig(x.getPai());
        rotacaoZig(x);
    }  
    public void remove(No x){
        if(x.getFilhoDir()!=null&&x.getFilhoEsq()!=null){
            No a=busca(x.getChave(),x.getFilhoDir()),b=busca(x.getChave(),x.getFilhoEsq());
            if(Math.abs(x.getChave()-b.getChave())<=x.getChave()-a.getChave()){
                if(x==raiz)
                    raiz=b;
                else if(x.getPai().getFilhoDir()==x)
                    x.getPai().setFilhoDir(b);
                else
                    x.getPai().setFilhoEsq(b);
                b.setFilhoEsq(x.getFilhoEsq());
                b.setFilhoDir(x.getFilhoDir());
                rotacao(b);
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
                rotacao(a);
            }
        }
        else if(x.getFilhoDir()==null&&x.getFilhoEsq()!=null){
            No a=busca(x.getChave(),x.getFilhoEsq());
            if(x==raiz)
                raiz=a;
            else if(x.getPai().getFilhoEsq()==x)
                    x.getPai().setFilhoEsq(a);
            else
                    x.getPai().setFilhoDir(a);
            a.setFilhoEsq(x.getFilhoEsq());
            rotacao(a);
        }
        else if(x.getFilhoDir()!=null&&x.getFilhoEsq()==null){
            No a=busca(x.getChave(),x.getFilhoDir());
            if(x==raiz)
                raiz=a;
            else if(x.getPai().getFilhoEsq()==x)
                    x.getPai().setFilhoEsq(a);
            else
                    x.getPai().setFilhoDir(a);
            a.setFilhoDir(x.getFilhoDir());
            rotacao(a);
        }
        else{
            if(x!=raiz){
                No a=x.getPai();
                rotacao(a);
            }
            else
                raiz=null;            
        }
        
    }
}

