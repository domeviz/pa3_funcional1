package com.programacion.lista;

import java.util.function.Function;

public class Principal {

	public static void main(String[] args) {
		
		Lista<Integer>l1=new Lista<>(1,2,3,4);
		System.out.println("Lista original: ");
		l1.forEach(System.out::println);
		
		//--append
		var l2=l1.append(5);
		System.out.println("l1 + {5}: ");
		l2.forEach(System.out::println);
		
		//--prepend
		var l3=l2.prepend(0);
		System.out.println("{0} + l2: ");
		l3.forEach(System.out::println);
		
		//--map: Lista<I>->Lista<S>
		//Function<Integer,String> fn=x->"valor: "+x;
		Lista<String> l4=l3.mapIt(x->"valor: "+x);
		System.out.println("l3.map(): ");
		l4.forEach(System.out::println);
		
		//--map: Lista<I>->Lista<Lista<I>>
		System.out.println("l3.map(): Lista<I>->Lista<Lista<I>>");
		Function<Integer,Lista<Integer>> fn=x->new Lista<>(x);
		Lista<Lista<Integer>> l5=l3.mapIt(fn);
		l5.forEach(System.out::println);
		
		
		//--mapRe:
		System.out.println("l1.mapRe(): ");
		var l6=l1.mapRe(x->x*2);
		l6.forEach(System.out::println);
		
		Lista<Integer> list = new Lista<>(1,2,3,4,5,6);
		
		Double res1= list.foldLeft(0.0, x->y->x+y);
		System.out.println(res1);
		
		String res2= list.foldLeft("0",  s -> x -> String.format("(%s + %d)", s, x));
		System.out.println(res2);
		
		//-------
		var res3 =list.foldRight("0",  x -> s -> String.format("(%d + %s)", x, s));
		System.out.println(res3);
		
	}

}
