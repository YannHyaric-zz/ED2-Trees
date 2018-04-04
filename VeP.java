
package Arvores;
/**
 * @author Yann Le Hyaric Almeida 
 */
public class VeP {
    private No raiz;
    
    public VeP(){
    raiz=null;
    }
    public void insere(No x){
        if(raiz==null){
            raiz=x;
            x.setCor(true);
        }
        else{
            No p=busca(x.getChave());
            if(p.getChave()<x.getChave()){
                p.setFilhoEsq(x);
                x.setPai(p);
            }
            else{
                p.setFilhoEsq(x);
                x.setPai(p);
            }
            verificaCor(x);
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
    
    public void verificaCor(No x){
        if(x.getPai().getCor()==false){
            if(!x.getPai().getPai().getFilhoEsq().getCor() && !x.getPai().getPai().getFilhoDir().getCor()){
                x.getPai().getPai().setCor(false);
                x.getPai().getPai().getFilhoDir().setCor(true);
                x.getPai().getPai().getFilhoEsq().setCor(true);
                verificaCor(x.getPai().getPai());   
            }
            
            else if(x.getPai().getPai().getFilhoEsq()==x.getPai()&& x.getPai().getPai().getFilhoDir().getCor()){
                if(x.getPai().getFilhoEsq()==x){
                    rotacao(x);
                    x.getPai().setCor(true);
                    x.getPai().getFilhoDir().setCor(false);
                }
                else{
                    rotacao(x);
                    rotacao(x);
                    x.getFilhoDir().setCor(false);
                    x.getFilhoEsq().setCor(false);
                }           
            }
            else if(x.getPai().getPai().getFilhoDir()==x.getPai()&& x.getPai().getPai().getFilhoEsq().getCor()){
                if(x.getPai().getFilhoDir()==x){
                    rotacao(x);
                    x.getPai().setCor(true);
                    x.getPai().getFilhoEsq().setCor(false);
                }
                else{
                    rotacao(x);
                    rotacao(x);
                    x.getFilhoDir().setCor(false);
                    x.getFilhoEsq().setCor(false);
                }                      
            } 
        }            
    }
    public void rotacao(No x){   
        No y=x.getPai();
        if(x.getChave()<y.getChave()){            
            if(y.getChave()<y.getPai().getChave()){y.getPai().setFilhoEsq(x);}
            else {y.getPai().setFilhoDir(x);}
            y.setFilhoEsq(x.getFilhoDir());
            x.setFilhoDir(y);               
        }
        else{
            if(y.getChave()<y.getPai().getChave()){y.getPai().setFilhoDir(x);}
            else {y.getPai().setFilhoEsq(x);}
            y.setFilhoDir(x.getFilhoEsq());
            x.setFilhoEsq(y);            
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
                verificaCor(b);
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
                verificaCor(a);
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
            verificaCor(a);
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
            verificaCor(a);
        }
        else{
            if(x!=raiz){
                No a=x.getPai();
                verificaCor(a);
            }
            else
                raiz=null;            
        }
        
    }
}