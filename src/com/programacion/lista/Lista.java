package com.programacion.lista;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<T> {
    private final T head;
    private final Lista<T> tail;

    public T head() {
        return this.head;
    }

    public Lista<T> tail() {
        return tail;
    }

    public Lista(T head, Lista<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Lista(T head) {
        this(head, null);
    }

    @SafeVarargs
    public Lista(T... elems) {
        Lista<T> colaTemp = null;

        for(int i=elems.length-1;i>=0;i--) {
            colaTemp = new Lista<>(elems[i], colaTemp);
        }

        this.head = colaTemp.head;
        this.tail = colaTemp.tail;
    }

    public Lista<T> eliminar(T elem) {
        Lista<T> tmp = this;

        Lista<T> retList = null;
        while(tmp!=null) {
            if( !tmp.head.equals(elem) ) {
                retList = new Lista<>(tmp.head, retList);
            }
            tmp = tmp.tail;
        }

        return retList;
    }

    public static <T> Lista<T> eliminar(Lista<T> athis, T elem) {
        Lista<T> tmp = athis;

        Lista<T> retList = null;
        while(tmp!=null) {
            if( !tmp.head.equals(elem) ) {
                retList = new Lista<>(tmp.head, retList);
            }
            tmp = tmp.tail;
        }

        return retList;
    }

    public Lista<T> filtrar(Predicate<T> filtro) {
        Lista<T> tmp = this;

        Lista<T> retList = null;
        while(tmp!=null) {
            if( filtro.test(tmp.head) ) {
                retList = new Lista<>(tmp.head, retList);
            }
            tmp = tmp.tail;
        }

        return retList;
    }

    public Lista<T> invertir() {
        Lista<T> tmp = this;

        Lista<T> retList = null;
        while(tmp!=null) {
            retList = new Lista<>(tmp.head, retList);
            tmp = tmp.tail;
        }

        return retList;
    }

    public void forEach(Consumer<T> action) {
        Lista<T> tmp = this;

        while(tmp!=null) {
            action.accept(tmp.head);
            tmp = tmp.tail;
        }
    }
    
    /**
     * Agregar un elemento a la lista(final)
     * l={1,2,3,4}
     * append: 5
     * result: {1,2,3,4,5}
     */
    public Lista<T> append(T e) {
    	Lista<T>tmp=this;
    	Lista<T>retTmp=null;
    	
    	while(tmp!=null) {
    		retTmp=new Lista(tmp.head, retTmp);
    		tmp=tmp.tail;   		          
    	}
    	return retTmp=new Lista<>(e,retTmp).invertir();	
    }
    
    /**
     * Agregar un elemento a la lista(inicio)
     * l={1,2,3,4,5}
     * prepend: 0
     * result: {0,1,2,3,4,5}
     */
    public Lista<T> prepend(T e) {
    	return new Lista<>(e,this);
    }
    
    //Aplica una funcion a los elementos de una lista
    public <U> Lista<U> mapIt(Function <T,U> fn){
    	Lista<T>tmp=this;
    	Lista<U>retTmp=null;
    	while(tmp!=null) {
    		T ele=tmp.head;
    		U newEle=fn.apply(ele);
    		
    		retTmp=new Lista<>(newEle,retTmp);	
    		tmp=tmp.tail;
    	}
    	return retTmp.invertir();
    }
    
    
    public <U> Lista<U> mapRe(Function <T,U> fn){
    	
    	//Version 1
    	return this.tail==null
    			? new Lista<>(fn.apply(this.head))
    			: this.tail.mapRe(fn).prepend(fn.apply(this.head));
    	
    	//Version 2
    	/*
    	if(this.tail==null) {
    		return new Lista<>(fn.apply(this.head));
    	}
    	else {
    		return this.tail.mapRe(fn).prepend(fn.apply(this.head));
    	}
    	
    	//Version 3
    	/*
    	if(this.tail==null) {
    		//U newHead=fn.apply(this.head);
    		return new Lista<>(fn.apply(this.head));
    	}
    	else {
        	T h=this.head;
        	Lista<T> t=this.tail;
        	U newHead=fn.apply(h);
        	var tmp= t.mapRe(fn);
        	tmp=tmp.prepend(newHead);
        	return tmp;
        	
    	}*/
    }
  
    
    public String toString() {
    	return String.format("[%s,%s]", 
    			this.head.toString(),
    			this.tail==null?"null":this.tail.toString());
    }
    
    //LTR
    public <U> U foldLeft(U identity,Function<U, Function<T, U>> fn){
    	
    	Lista<T> tmp =this;
    	U res= identity;
    	
    	while (tmp!=null) {
			res=fn.apply(res).apply(tmp.head);
			tmp=tmp.tail;
		}
    	return res;
    }
    
    //RTL
    public <U> U foldRight(U identity,Function<T, Function<U, U>> fn){
    	
    	return this.tail==null
    			?fn.apply(this.head).apply(identity)
    			:fn.apply(this.head).apply(this.tail.foldRight(identity, fn));
    	
    	
    	
//    	if (this.tail==null) {
//			//{6}==> fn(6,identity)
//    		return fn.apply(this.head).apply(identity);
//		}
//    	else {
//    		T h =this.head;
//    		Lista<T> t=this.tail;
//    		
//    		return fn.apply(this.head).apply(this.tail.foldRight(identity, fn));
//    		
    		
    	//}
    	
    	
    }
    
    //Right-To-Left
    public <U> Lista<U> mapFoldRight(Function<T, U> fn) {
		Lista<T> tmp = this;

		Lista<U> retTmp = null;
		if (tmp.tail != null) {
			retTmp = new Lista<>(fn.apply(this.head));
			return tmp.tail.invertir().foldRight(retTmp, x -> l -> l.append(fn.apply(x)));

		} else {
			return new Lista<U>(fn.apply(this.head));

		}

	}
    
    //Left-To-Right
    public <U> Lista<U> mapFolfLeft(Function<T, U> fn) {
    	Lista<T> tmp = this;

		Lista<U> retTmp = null;
		if (tmp.tail != null) {
			retTmp = new Lista<>(fn.apply(this.head));
			return tmp.tail.invertir().foldRight(retTmp, x -> l -> l.append(fn.apply(x)));

		} else {
			return new Lista<U>(fn.apply(this.head));

		}

	}

    
    
    
}
